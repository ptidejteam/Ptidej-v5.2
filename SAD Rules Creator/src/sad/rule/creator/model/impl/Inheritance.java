/*
 * (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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

package sad.rule.creator.model.impl;

import sad.rule.creator.model.IConstituent;
import sad.rule.creator.model.IInheritance;

/**
 * @author Pierre Leduc
 */
public class Inheritance extends Relationship implements IInheritance {

	public Inheritance(
		final String anID,
		final IConstituent aSourceConstituent,
		final IConstituent aTargetConstituent,
		final int aSourceCardinality,
		final int aTargetCardinality) {
		super(
			anID,
			aSourceConstituent,
			aTargetConstituent,
			aSourceCardinality,
			aTargetCardinality);
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.getClass().getName());
		buffer.append("\nName: ");
		buffer.append(this.getID());
		buffer.append("\nSource: ");
		buffer.append(this.getSourceConstituent().getID());
		buffer.append("\nCardinality: ");
		buffer.append(this.getSourceCardinality());
		buffer.append("\nTarget: ");
		buffer.append(this.getTargetConstituent().getID());
		buffer.append("\nCardinality: ");
		buffer.append(this.getTargetCardinality());

		return buffer.toString();
	}
}