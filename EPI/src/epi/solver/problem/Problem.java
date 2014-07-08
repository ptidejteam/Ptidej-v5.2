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
package epi.solver.problem;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import ptidej.solver.fingerprint.ReducedDomainBuilder;

/**
 * @author OlivierK
 */
public abstract class Problem {
	protected Hashtable domain;
	protected ReducedDomainBuilder rdg;

	public Problem(final ICodeLevelModel aCodeLevelModel) {
		this.rdg = new ReducedDomainBuilder(aCodeLevelModel);
		this.domain = new Hashtable();
	}

	public Hashtable getDomains() {
		return this.domain;
	}

	protected List getSimpleConstituentList(
		final IAbstractLevelModel anAbstractModel) {

		final List simpleList = new ArrayList();
		final Iterator entities = anAbstractModel.getIteratorOnConstituents();
		while (entities.hasNext()) {
			simpleList.add(((IFirstClassEntity) entities.next()).getID());
		}
		return simpleList;
	}

	public List getVariableDomain(final String var) {
		return (List) this.domain.get(var.toLowerCase());
	}
}
