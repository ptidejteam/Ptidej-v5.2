/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel.impl;

import padl.kernel.Constants;
import padl.kernel.IAssociation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IElementMarker;
import padl.kernel.IMethod;
import padl.kernel.exception.ModelDeclarationException;
import padl.util.Util;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;
import com.ibm.toad.cfparse.utils.Access;

class DelegatingMethod extends Method implements IElementMarker,
		IDelegatingMethod {

	private static final long serialVersionUID = 3999083087438818257L;
	private IAssociation targetAssoc;
	private IMethod targetMethod;

	// Yann: This is a patch for the clone().
	// The problem is that when cloning a model, it may happen
	// that the target association (respectively target method)
	// is cloned before this DelegatingMethod (that is linked to it)
	// is cloned. A clean implementation would be to generalize
	// the use of PropertyChangeEvents: a model would notify
	// its PEntities that a clone event happened, in turn, the 
	// PEntities would notify their PElements of this clone event.
	// Thus, if a Association is cloned before the DelegatingMethod
	// that is linked to it, the Association will sent a clone
	// event to this DelegatingMethod, that will in turn clone itself,
	// and then accept the clone of its target Association.
	// Wonderful, isn't it?
	private Association temporaryTargetAssociation;
	private Method temporaryTargetMethod;

	// Yann 2011/09/06: Duh!
	// A DelegatingMethod cannot be delegating to itself, 
	// else it would create a kind of circular call...
	//	public DelegatingMethod(final char[] anID, final IAssociation targetAssoc)
	//			 {
	//
	//		this(anID, targetAssoc, null);
	//	}
	public DelegatingMethod(
		final char[] anID,
		final IAssociation aTargetAssociation,
		final IMethod aTargetMethod) {

		super(anID, aTargetMethod);

		this.setTargetAssoc(aTargetAssociation);
		// Yann 2011/09/06: Duh!
		// A DelegatingMethod cannot be delegating to itself, 
		// else it would create a kind of circular call...
		//	if (aTargetMethod == null) {
		//		this.setTargetMethod(this);
		//	}
		//	else {
		this.setTargetMethod(aTargetMethod);
		//	}
	}
	public void endCloneSession() {
		super.endCloneSession();

		this.temporaryTargetAssociation = null;
		this.temporaryTargetMethod = null;
	}
	public IAssociation getTargetAssoc() {
		return this.targetAssoc;
	}
	public IMethod getTargetMethod() {
		return this.targetMethod;
	}
	public void setTargetAssoc(final IAssociation anAssociation) {
		this.targetAssoc = anAssociation;
	}
	public void setTargetMethod(final IMethod aMethod) {
		this.targetMethod = aMethod;
	}
	public void setVisibility(int visibility) {
		if (Access.isAbstract(visibility)) {
			throw new ModelDeclarationException(MultilingualManager.getString(
				"ABSTRACT",
				DelegatingMethod.class,
				new Object[] { this.getClass() }));
		}
		super.setVisibility(visibility);
	}
	public void startCloneSession() {
		super.startCloneSession();
		DelegatingMethod clone = (DelegatingMethod) this.getClone();

		// Yann: This is a beautiful, beautiful hack...
		if (this.temporaryTargetAssociation != null) {
			clone.targetAssoc = this.temporaryTargetAssociation;
		}
		if (this.temporaryTargetMethod != null) {
			clone.targetMethod = this.temporaryTargetMethod;
		}
	}
	public String toString() {
		if (Constants.DEBUG) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println("// PDelegatingMethod.toString()");
		}
		return this.toString(0);
	}
	public String toString(final int tab) {
		if (getTargetAssoc().getCardinality() > 1) {
			setCodeLines("for (Enumeration enum = "
					+ this.targetAssoc.getDisplayName()
					+ ".elements(); enum.hasMoreElements(); (("
					+ this.getTargetAssoc().getTargetEntity().getDisplayName()
					+ ") enum.nextElement())."
					+ this.getTargetMethod().getCallDeclaration() + ");");
		}
		else {
			setCodeLines(this.getTargetAssoc().getDisplayName() + "."
					+ this.getTargetMethod().getCallDeclaration() + ";");
		}

		StringBuffer codeEq = new StringBuffer();
		Util.addTabs(tab, codeEq);
		codeEq.append("// Method linked to: ");
		codeEq.append(getTargetAssoc().getName());
		codeEq.append('\n');
		codeEq.append(super.toString(tab));

		return codeEq.toString();
	}
}