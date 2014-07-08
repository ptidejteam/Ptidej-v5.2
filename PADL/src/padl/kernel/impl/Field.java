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
import padl.kernel.IField;
import padl.kernel.exception.ModelDeclarationException;
import padl.util.Util;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;
import com.ibm.toad.cfparse.utils.Access;

// Sebastien Colladon 21/04/2012 : Change the visibility to public in order to allow other project to extend from this class in the particular case of eclipse bundle loader (avoid IllegalAccessError).
public class Field extends Element implements IElementMarker, IField {
	private static final long serialVersionUID = -2269333542392931119L;
	private int cardinality;
	private char[] fieldType;

	public Field(final String anID) {
		this(anID.toCharArray());
	}

	public Field(final char[] anID) {
		this(anID, anID, null, 1);
	}

	public Field(
		final char[] anID,
		final char[] aName,
		final char[] aFieldType,
		final int aCardinality) {

		super(anID);
		this.setType(aFieldType);
		this.setName(aName);
		this.setCardinality(aCardinality);
	}

	public int getCardinality() {
		return this.cardinality;
	}

	public String getDisplayTypeName() {
		return String.valueOf(this.getType());
	}

	public char[] getType() {
		return this.fieldType;
	}

	public void setCardinality(int cardinality) {
		if (cardinality < 1) {
			throw new ModelDeclarationException(MultilingualManager.getString(
				"CARDINALITY",
				Field.class,
				new Object[] { new Integer(cardinality) }));
		}

		this.cardinality = cardinality;
	}

	public void setType(final char[] fieldType) {
		this.fieldType = fieldType;
	}

	public void setVisibility(final int visibility) {
		super.setVisibility(visibility & ~Access.ACC_ABSTRACT);
	}

	public String toString() {
		if (Constants.DEBUG) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println("// Field.toString()");
		}
		return toString(0);
	}

	public String toString(final int tab) {
		if (Constants.DEBUG) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println("// Field.toString(int)");
		}
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append(super.toString(tab));
		if (this.getVisibility() != 0) {
			codeEq.append(' ');
		}
		codeEq.append(this.getType());
		codeEq.append(' ');
		codeEq.append(this.getName());
		final String[] codeLines = this.getCodeLines();
		// Yann: Can be null. The case "empty array" is dealt with in the loop. 
		if (codeLines != null) {
			codeEq.append(" = ");
			for (int i = 0; i < codeLines.length; i++) {
				codeEq.append('\n');
				Util.addTabs(tab + 1, codeEq);
				codeEq.append(codeLines[i]);
			}
		}
		codeEq.append(';');
		return codeEq.toString();
	}
}
