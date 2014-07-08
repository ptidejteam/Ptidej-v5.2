/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
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
package padl.motif.kernel.impl;

import padl.event.IMotifModelListener;
import padl.event.PatternEvent;
import padl.kernel.Constants;
import padl.kernel.IConstituent;
import padl.kernel.IPackage;
import padl.kernel.exception.ModelDeclarationException;
import padl.kernel.impl.AbstractLevelModel;
import padl.motif.IDesignMotifModel;
import padl.motif.kernel.IDesignLevelModel;
import util.multilingual.MultilingualManager;

class DesignLevelModel extends AbstractLevelModel implements IDesignLevelModel,
		Cloneable {

	private static final long serialVersionUID = 3774766840113450647L;

	public DesignLevelModel() {
		this(Constants.DEFAULT_DESIGNLEVELMODEL_ID);
	}
	public DesignLevelModel(final char[] aName) {
		super(aName);
	}
	public final void addConstituent(final IConstituent aConstituent) {
		// Yann 2008/11/04: No no-package!
		// In the process of adding the packages consistently,
		// I choose that no entity can be without package. Even
		// though it may not make sense for languages like C++.
		//	if (aConstituent instanceof IEntity) {
		//		this.addConstituent((IEntity) aConstituent);
		//	}
		//	else 
		if (aConstituent instanceof IPackage) {
			this.addConstituent((IPackage) aConstituent);
		}
		else if (aConstituent instanceof IDesignMotifModel) {
			this.addConstituent((IDesignMotifModel) aConstituent);
		}
		else {
			throw new ModelDeclarationException(MultilingualManager.getString(
				"ENT_OR_PATTERN_ADD",
				DesignLevelModel.class));
		}
	}
	public final void addConstituent(final IDesignMotifModel aMotif) {
		this.addConstituent(aMotif);
		this.fireModelChange(
			IMotifModelListener.PATTERN_ADDED,
			new PatternEvent(this, aMotif));
	}
	public void addConstituent(final IPackage aPackage) {
		super.addConstituent(aPackage);
	}
}