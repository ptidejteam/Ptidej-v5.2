/*
 * (c) Copyright 2001-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.event;

import java.util.List;

public final class AnalysisEvent implements IEvent {
	private final char[] name;
	private final char[] elementType;

	/**
	 * @deprecated by AnalysisEvent(final char[])
	 */
	public AnalysisEvent(final String aName) {
		this.name = aName.toCharArray();
		this.elementType = new char[0];
	}
	/**
	 * @deprecated by AnalysisEvent(final char[], final char[])
	 */
	public AnalysisEvent(final char[] aName) {
		this.name = aName;
		this.elementType = new char[0];
	}
	public AnalysisEvent(final char[] aName, final char[] anElementType) {
		this.name = aName;
		this.elementType = anElementType;
	}
	public char[] getConstituentName() {
		return this.name;
	}
	public List getSubmittedConstituents() {
		return null;
	}
	public String toString() {
		final StringBuffer buffer =
			new StringBuffer(this.name.length + this.elementType.length + 1);
		buffer.append(this.name);
		buffer.append(';');
		buffer.append(this.elementType);
		return buffer.toString();
	}
}
