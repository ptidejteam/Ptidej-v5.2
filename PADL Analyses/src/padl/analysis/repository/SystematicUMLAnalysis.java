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
import padl.analysis.repository.systematicuml.SystematicUMLDataTypeCleaner;
import padl.analysis.repository.systematicuml.SystematicUMLElementGenerator;
import padl.analysis.repository.systematicuml.SystematicUMLEntityGenerator;
import padl.analysis.repository.systematicuml.SystematicUMLOperationGenerator;
import padl.analysis.repository.systematicuml.SystematicUMLStatistics;
import padl.kernel.IAbstractModel;
import padl.kernel.IFactory;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.impl.Factory;
import util.help.IHelpURL;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/05/20
 */
public final class SystematicUMLAnalysis implements IAnalysis, IHelpURL {
	public static final String OPERATION = "/* OPERATION */ ";
	public static final String TEMPLATE_METHOD = "/* TEMPLATE METHOD */ ";

	public IAbstractModel invoke(final IAbstractModel anAbstractModel)
			throws UnsupportedSourceModelException {

		if (anAbstractModel instanceof IIdiomLevelModel) {
			final long startTime = System.currentTimeMillis();

			final IFactory factory = Factory.getInstance();
			final IIdiomLevelModel newIdiomLevelModel =
				factory
					.createIdiomLevelModel(("Systematic UML analysis of " + anAbstractModel
						.getDisplayName()).toCharArray());
			final SystematicUMLStatistics statistics =
				new SystematicUMLStatistics();

			final SystematicUMLEntityGenerator entityGenerator =
				new SystematicUMLEntityGenerator(
					factory,
					newIdiomLevelModel,
					statistics);
			anAbstractModel.walk(entityGenerator);

			final SystematicUMLElementGenerator elementGenerator =
				new SystematicUMLElementGenerator(
					factory,
					newIdiomLevelModel,
					statistics);
			anAbstractModel.walk(elementGenerator);

			final SystematicUMLDataTypeCleaner dataTypeCleaner =
				new SystematicUMLDataTypeCleaner(
					factory,
					newIdiomLevelModel,
					statistics);
			newIdiomLevelModel.walk(dataTypeCleaner);

			final SystematicUMLOperationGenerator operationGenerator =
				new SystematicUMLOperationGenerator(
					factory,
					newIdiomLevelModel,
					statistics);
			newIdiomLevelModel.walk(operationGenerator);

			System.out.print(statistics);
			System.out.println(MultilingualManager
				.getString(
					"ANALYSIS_TIME",
					SystematicUMLAnalysis.class,
					new Object[] { new Long(System.currentTimeMillis()
							- startTime) }));

			return newIdiomLevelModel;
		}
		else {
			throw new UnsupportedSourceModelException();
		}
	}
	public String getName() {
		return MultilingualManager.getString(
			"NAME",
			SystematicUMLAnalysis.class);
	}
	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/APSEC04.doc.pdf";
	}
}
