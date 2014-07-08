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
package padl.analysis.repository;

import padl.analysis.IAnalysis;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.aacrelationships.AACBuilder;
import padl.analysis.repository.aacrelationships.AACRemover;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.util.adapter.IdiomLevelModelAdapter;
import util.help.IHelpURL;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/05/20
 */
public final class AACRelationshipsAnalysis implements IAnalysis, IHelpURL {
	private final boolean removeOldRelationships;

	public AACRelationshipsAnalysis() {
		this.removeOldRelationships = true;
	}
	public AACRelationshipsAnalysis(final boolean removeOldRelationships) {
		this.removeOldRelationships = removeOldRelationships;
	}
	public IAbstractModel invoke(final IAbstractModel anAbstractModel)
			throws UnsupportedSourceModelException {

		// Yann 2009/03/07: Cloning is bad!
		// I cannot clone within this method the source model
		// because I cannot foresee what user would want to
		// use this model and this method for! So, I now only
		// create a wrapper around the source model, the cloning
		// if needed, should be done by the caller.
		// (Recall that the convert() and upgrade() methods used
		// to clone the source model!!!)
		//	final IIdiomLevelModel idiomLevelModel =
		//		((ICodeLevelModel) anAbstractModel).convert(anAbstractModel
		//			.getName());
		if (anAbstractModel instanceof ICodeLevelModel) {
			try {
				// Yann 2013/09/26: Do not change original...
				// I clone the original model to make sure that
				// I do not modify it accidentally...
				final ICodeLevelModel clonedModel =
					(ICodeLevelModel) anAbstractModel.clone();
				final IIdiomLevelModel idiomLevelModel =
					new IdiomLevelModelAdapter(clonedModel);
				if (this.removeOldRelationships) {
					idiomLevelModel.walk(new AACRemover());
				}
				idiomLevelModel.walk(new AACBuilder());
				return idiomLevelModel;
			}
			catch (final CloneNotSupportedException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				return null;
			}
		}
		else {
			throw new UnsupportedSourceModelException();
		}
	}
	public String getName() {
		return "AAC Relationships";
	}
	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/OOPSLA04.doc.pdf";
	}
}
