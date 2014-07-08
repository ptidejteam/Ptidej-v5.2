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

import java.util.Iterator;
import sad.rule.creator.model.IConstituent;
import sad.rule.creator.model.IRuleCard;
import sad.rule.creator.model.IVisitor;

/**
 * @author Pierre Leduc
 */
public class RuleCard extends Container implements IRuleCard {
	public RuleCard(final String anID) {
		super(anID);
	}

	public void accept(final IVisitor aVisitor) {
		aVisitor.open(this);

		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).accept(aVisitor);
		}

		aVisitor.close(this);
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		//Util.addTabs(tab, buffer);
		buffer.append("\n\n*****************************************\n");
		buffer.append(this.getClass().getName());
		buffer.append("\nName: ");
		buffer.append(this.getID());
		buffer.append("\n-------------------------------------\n");
		buffer.append(super.toString());

		buffer.append("\n*****************************************");

		return buffer.toString();

	}
}
