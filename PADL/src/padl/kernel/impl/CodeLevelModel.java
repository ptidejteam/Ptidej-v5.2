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
import padl.kernel.ICodeLevelModel;
import padl.kernel.ICodeLevelModelCreator;
import padl.kernel.exception.CreationException;
import util.lang.ConcreteReceiverGuard;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/08/05
 */
class CodeLevelModel extends AbstractLevelModel implements ICodeLevelModel,
		Cloneable {

	private static final long serialVersionUID = 1645964216980637259L;

	public CodeLevelModel() {
		this(Constants.DEFAULT_CODELEVELMODEL_ID);
	}
	public CodeLevelModel(final char[] aName) {
		super(aName);
	}
	public void create(final ICodeLevelModelCreator aCodeLevelCreator)
			throws CreationException {

		ConcreteReceiverGuard.getInstance().checkCallingClassName(
			"padl.generator.helper.ModelGenerator",
			"Please use the methods in \"padl.generator.helper.ModelGenerator\" to obtain code-level models.");

		aCodeLevelCreator.create(this);
	}
	//	public IIdiomLevelModel convert(final String aName) {
	//		final IIdiomLevelModel idiomLevelModel =
	//			Factory.getInstance().createIdiomLevelModel(aName);
	//
	//		this.clone(idiomLevelModel);
	//
	//		return idiomLevelModel;
	//	}
}
