/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.creator.classfile.test.visitor;

import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.motif.IDesignMotifModel;
import padl.visitor.IGenerator;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/04/10
 */
public class SimpleGenerator implements IGenerator {
	private final StringBuffer buffer = new StringBuffer();
	public void close(final IAbstractModel anAbstractModel) {
		this.buffer.append("close\t");
		this.buffer.append(anAbstractModel.getName());
		this.buffer.append('\n');
	}
	public void close(final IClass aClass) {
		this.buffer.append("close\t");
		this.buffer.append(aClass.getID());
		this.buffer.append('\n');
	}
	public void close(final IConstructor aConstructor) {
		this.buffer.append("close\t");
		this.buffer.append(aConstructor.getName());
		this.buffer.append('\n');
	}
	public void close(final IDelegatingMethod aDelegatingMethod) {
		this.buffer.append("close\t");
		this.buffer.append(aDelegatingMethod.getName());
		this.buffer.append('\n');
	}
	public void close(final IDesignMotifModel aPatternModel) {
		this.buffer.append("close\t");
		this.buffer.append(aPatternModel.getName());
		this.buffer.append('\n');
	}
	public void close(final IGetter aGetter) {
		this.buffer.append("close\t");
		this.buffer.append(aGetter.getName());
		this.buffer.append('\n');
	}
	public void close(final IGhost aGhost) {
		this.buffer.append("close\t");
		this.buffer.append(aGhost.getID());
		this.buffer.append('\n');
	}
	public void close(final IInterface anInterface) {
		this.buffer.append("close\t");
		this.buffer.append(anInterface.getID());
		this.buffer.append('\n');
	}
	public void close(final IMemberClass aMemberClass) {
		this.buffer.append("close\t");
		this.buffer.append(aMemberClass.getName());
		this.buffer.append('\n');
	}
	public void close(final IMemberGhost aMemberGhost) {
		this.buffer.append("close\t");
		this.buffer.append(aMemberGhost.getName());
		this.buffer.append('\n');
	}
	public void close(final IMemberInterface aMemberInterface) {
		this.buffer.append("close\t");
		this.buffer.append(aMemberInterface.getName());
		this.buffer.append('\n');
	}
	public void close(final IMethod aMethod) {
		this.buffer.append("close\t");
		this.buffer.append(aMethod.getName());
		this.buffer.append('\n');
	}
	public void close(final IPackage aPackage) {
		this.buffer.append("close\t");
		this.buffer.append(aPackage.getName());
		this.buffer.append('\n');
	}
	public void close(final IPackageDefault aPackage) {
		this.buffer.append("close\t");
		this.buffer.append(aPackage.getName());
		this.buffer.append('\n');
	}
	public void close(final ISetter aSetter) {
		this.buffer.append("close\t");
		this.buffer.append(aSetter.getName());
		this.buffer.append('\n');
	}
	public String getCode() {
		return this.buffer.toString();
	}
	public String getName() {
		return "Visitor for testing";
	}
	public void open(final IAbstractModel anAbstractModel) {
		this.buffer.append("open\t");
		this.buffer.append(anAbstractModel.getName());
		this.buffer.append('\n');
	}
	public void open(final IClass aClass) {
		this.buffer.append("open\t");
		this.buffer.append(aClass.getID());
		this.buffer.append('\n');
	}
	public void open(final IConstructor aConstructor) {
		this.buffer.append("open\t");
		this.buffer.append(aConstructor.getName());
		this.buffer.append('\n');
	}
	public void open(final IDelegatingMethod aDelegatingMethod) {
		this.buffer.append("open\t");
		this.buffer.append(aDelegatingMethod.getName());
		this.buffer.append('\n');
	}
	public void open(final IDesignMotifModel aPatternModel) {
		this.buffer.append("open\t");
		this.buffer.append(aPatternModel.getName());
		this.buffer.append('\n');
	}
	public void open(final IGetter aGetter) {
		this.buffer.append("open\t");
		this.buffer.append(aGetter.getName());
		this.buffer.append('\n');
	}
	public void open(final IGhost aGhost) {
		this.buffer.append("open\t");
		this.buffer.append(aGhost.getID());
		this.buffer.append('\n');
	}
	public void open(final IInterface anInterface) {
		this.buffer.append("open\t");
		this.buffer.append(anInterface.getID());
		this.buffer.append('\n');
	}
	public void open(final IMemberClass aMemberClass) {
		this.buffer.append("open\t");
		this.buffer.append(aMemberClass.getName());
		this.buffer.append('\n');
	}
	public void open(final IMemberGhost aMemberGhost) {
		this.buffer.append("open\t");
		this.buffer.append(aMemberGhost.getName());
		this.buffer.append('\n');
	}
	public void open(final IMemberInterface aMemberInterface) {
		this.buffer.append("open\t");
		this.buffer.append(aMemberInterface.getName());
		this.buffer.append('\n');
	}
	public void open(final IMethod aMethod) {
		this.buffer.append("open\t");
		this.buffer.append(aMethod.getName());
		this.buffer.append('\n');
	}
	public void open(final IPackage aPackage) {
		this.buffer.append("open\t");
		this.buffer.append(aPackage.getName());
		this.buffer.append('\n');
	}
	public void open(final IPackageDefault aPackage) {
		this.buffer.append("open\t");
		this.buffer.append(aPackage.getName());
		this.buffer.append('\n');
	}
	public void open(final ISetter aSetter) {
		this.buffer.append("open\t");
		this.buffer.append(aSetter.getName());
		this.buffer.append('\n');
	}
	public void reset() {
		this.buffer.append("Reset");
	}
	public final void unknownConstituentHandler(
		final String aCalledMethodName,
		final IConstituent aConstituent) {

		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(this.getClass().getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(" does not know what to do for \"");
		ProxyConsole.getInstance().debugOutput().print(aCalledMethodName);
		ProxyConsole.getInstance().debugOutput().print("\" (");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(aConstituent.getDisplayID());
		ProxyConsole.getInstance().debugOutput().println(')');
	}
	public void visit(final IAggregation anAggregation) {
		this.buffer.append("visit\t");
		this.buffer.append(anAggregation.getName());
		this.buffer.append('\n');
	}
	public void visit(final IAssociation anAssociation) {
		this.buffer.append("visit\t");
		this.buffer.append(anAssociation.getName());
		this.buffer.append('\n');
	}
	public void visit(final IComposition aComposition) {
		this.buffer.append("visit\t");
		this.buffer.append(aComposition.getName());
		this.buffer.append('\n');
	}
	public void visit(final IContainerAggregation aContainerAggregation) {
		this.buffer.append("visit\t");
		this.buffer.append(aContainerAggregation.getName());
		this.buffer.append('\n');
	}
	public void visit(final IContainerComposition aContainerComposition) {
		this.buffer.append("visit\t");
		this.buffer.append(aContainerComposition.getName());
		this.buffer.append('\n');
	}
	public void visit(final ICreation aCreation) {
		this.buffer.append("visit\t");
		this.buffer.append(aCreation.getName());
		this.buffer.append('\n');
	}
	public void visit(final IField aField) {
		this.buffer.append("visit\t");
		this.buffer.append(aField.getName());
		this.buffer.append('\n');
	}
	public void visit(final IMethodInvocation aMethodInvocation) {
		this.buffer.append("visit\t");
		this.buffer.append(aMethodInvocation.getName());
		this.buffer.append('\n');
	}
	public void visit(final IParameter aParameter) {
		this.buffer.append("visit\t");
		this.buffer.append(aParameter.getName());
		this.buffer.append('\n');
	}
	public void visit(final IPrimitiveEntity aPrimitiveEntity) {
		// Do nothing for uninteresting primitive types.
	}
	public void visit(final IUseRelationship aUse) {
		this.buffer.append("visit\t");
		this.buffer.append(aUse.getName());
		this.buffer.append('\n');
	}
}
