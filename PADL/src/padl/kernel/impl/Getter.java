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
package padl.kernel.impl;

import java.util.Iterator;
import padl.kernel.IConstituent;
import padl.kernel.IGetter;
import padl.kernel.IMethod;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/08/05
 */
class Getter extends Method implements IGetter {
	private static final long serialVersionUID = 3309069360684034235L;
	private int cardinality;
	public Getter(final IMethod aMethod) {
		super(aMethod.getID());

		this.setAbstract(aMethod.isAbstract());
		if (!aMethod.isAbstract()) {
			this.setCodeLines(aMethod.getCodeLines());
		}
		this.setComment(aMethod.getComment());
		this.setDisplayName(aMethod.getDisplayName());
		this.setName(aMethod.getName());
		this.setPrivate(aMethod.isPrivate());
		this.setPublic(aMethod.isPublic());
		this.setReturnType(aMethod.getReturnType());
		this.setStatic(aMethod.isStatic());
		this.setVisibility(aMethod.getVisibility());
		this.setWeight(aMethod.getWeight());

		final Iterator iterator = aMethod.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			// Yann 2007/11/14: Clone?
			// Why did I clone the constituent in the method
			// before "moving" them into the getter?
			//	constituent.startCloneSession();
			//	constituent.performCloneSession();
			//	final IConstituent clone = constituent.getClone();
			//	constituent.endCloneSession();
			//	this.addConstituent(clone);
			this.addConstituent(constituent);
		}
	}
	public Getter(final char[] anID) {
		super(anID);
	}
	public int getCardinality() {
		return this.cardinality;
	}
	public void setCardinality(final int aCardinality) {
		this.cardinality = aCardinality;
	}
}
