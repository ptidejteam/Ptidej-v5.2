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
import padl.kernel.IElementMarker;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IRelationship;
import padl.kernel.IUseRelationship;
import padl.kernel.exception.ModelDeclarationException;
import padl.util.Util;
import util.io.ProxyConsole;
import util.lang.Modifier;
import util.multilingual.MultilingualManager;

abstract class Relationship extends Element implements IElementMarker,
		IRelationship {

	private static final long serialVersionUID = -5857707891166836532L;
	private int cardinality;
	private IFirstClassEntity targetEntity;

	public Relationship(final char[] anID) {
		super(anID);
	}
	public int getCardinality() {
		return this.cardinality;
	}
	public IFirstClassEntity getTargetEntity() {
		return this.targetEntity;
	}
	public void performCloneSession() {
		super.performCloneSession();
		final IUseRelationship clone = (IUseRelationship) this.getClone();
		clone.setTargetEntity((IFirstClassEntity) this.targetEntity.getClone());
	}
	public void setCardinality(int aCardinality) {
		if (aCardinality < 1) {
			throw new ModelDeclarationException(MultilingualManager.getString(
				"CARDINALITY",
				Relationship.class,
				new Object[] { new Integer(this.cardinality) }));
		}

		this.cardinality = aCardinality;
	}
	public void setTargetEntity(final IFirstClassEntity anEntity) {
		this.targetEntity = anEntity;
	}
	public String toString() {
		if (Constants.DEBUG) {
			ProxyConsole.getInstance().debugOutput().print("// ");
			ProxyConsole.getInstance().debugOutput().print(this.getClass());
			ProxyConsole.getInstance().debugOutput().println(".toString()");
		}
		return this.toString(0);
	}
	public String toString(final int tab) {
		final StringBuffer buffer = new StringBuffer();
		Util.addTabs(tab, buffer);
		buffer.append(this.getClass().getName());
		buffer.append("\nName: ");
		buffer.append(this.getName());
		buffer.append("\nWith: ");
		buffer.append(this.getTargetEntity().getName());
		buffer.append("\nVisibility: ");
		buffer.append(Modifier.toString(this.getVisibility()));
		buffer.append(", cadinality: ");
		buffer.append(this.getCardinality());
		buffer.append('\n');
		return buffer.toString();
	}
}