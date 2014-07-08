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

import padl.kernel.IConstituent;
import padl.kernel.IEntity;
import padl.kernel.IParameter;
import padl.util.Util;

public class Parameter extends Element implements IParameter {
	private static final long serialVersionUID = -1688444809285895471L;
	private int cardinality = 1;
	private IEntity type;

	public Parameter(
		final IEntity anEntity,
		final char[] aName,
		final int aCardinality) {

		this(anEntity, aCardinality);
		this.setName(aName);
	}
	public Parameter(final IEntity aType, final int aCardinality) {
		super("Parameter".toCharArray());

		this.setType(aType);
		this.setNameFromType(aType);
		this.cardinality = aCardinality;
	}
	//	public Parameter(final String aName, final String aType) {
	//		super("Parameter");
	//
	//		this.setName(aName);
	//		this.setType(aType);
	//	}
	// Yann 2006/03/28: Need?
	// Do I need to have this method now that
	// I inherit from Consituent?
	//	public void accept(final IVisitor visitor) {
	//		visitor.visit(this);
	//	}
	//	private String clean(final String aString, final boolean computeCardinality) {
	//		final String cleanString;
	//		int index = aString.indexOf('[');
	//		if (index > -1) {
	//			cleanString = aString.substring(0, index);
	//
	//			// Yann 2004/08/10: Cardinality.
	//			// I compute the cardinality by counting the
	//			// number of opening brackets.
	//			if (computeCardinality) {
	//				this.cardinality++;
	//				while ((index = aString.indexOf('[', index + 1)) > -1) {
	//					this.cardinality++;
	//				}
	//			}
	//		}
	//		else {
	//			cleanString = aString;
	//		}
	//
	//		return cleanString;
	//	}
	public int getCardinality() {
		return this.cardinality;
	}
	public String getDisplayTypeName() {
		return String.valueOf(this.getTypeName());
	}
	public IEntity getType() {
		return this.type;
	}
	public char[] getTypeName() {
		return this.type.getID();
	}
	public void setCardinality(final int aCardinality) {
		this.cardinality = aCardinality;
	}
	public void setNameFromType(final IConstituent aType) {
		final char[] beautyName =
			Util.capitalizeFirstLetter(Util.stripAndCapQualifiedName(this
				.getTypeName()));
		final char[] name = new char[1 + beautyName.length];
		name[0] = 'a';
		System.arraycopy(beautyName, 0, name, 1, beautyName.length);

		this.setName(name);
	}
	public void setType(final IEntity aType) {
		this.type = aType;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.getTypeName());
		buffer.append(' ');
		buffer.append(this.getName());
		return buffer.toString();
	}
}
