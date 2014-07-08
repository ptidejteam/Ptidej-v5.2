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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sad.rule.creator.model.IConstituent;
import sad.rule.creator.model.IContainer;

/**
 * @author Pierre Leduc
 */
abstract class Container extends Constituent implements IContainer {
	private final List listOfConstituents = new ArrayList();

	public Container(final String anID) {
		super(anID);
	}

	public void addConstituent(final IConstituent anEntity) {
		this.listOfConstituents.add(anEntity);
	}

	public IConstituent getConstituentFromID(final String anID) {
		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent entity = (IConstituent) iterator.next();
			if (entity.getID().equals(anID)) {
				return entity;
			}
		}
		return null;
	}

	public Iterator getIteratorOnConstituents() {
		return this.listOfConstituents.iterator();
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		final Iterator iterator = this.listOfConstituents.iterator();
		while (iterator.hasNext()) {
			buffer.append(iterator.next().toString());
			buffer.append("\n-------------------------------------");
			if (iterator.hasNext()) {
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
}