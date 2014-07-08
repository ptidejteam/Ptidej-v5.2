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
package ptidej.solver.domain.test;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.ModelStatistics;
import util.io.ProxyDisk;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/05/16
 */
public class Manager extends TestCase {
	private static ICodeLevelModel CodeLevelModel;

	public Manager(final String aName) {
		super(aName);
	}
	protected void setUp() {
		if (Manager.CodeLevelModel == null) {
			final String path =
				"../Ptidej Tests/bin/ptidej/example/composite2/";

			// Instantiating model.
			Manager.CodeLevelModel =
				Factory.getInstance().createCodeLevelModel(path);
			final ModelStatistics patternStatistics = new ModelStatistics();
			Manager.CodeLevelModel.addModelListener(patternStatistics);

			// Building the program representation.
			try {
				Manager.CodeLevelModel.create(new CompleteClassFileCreator(
					new String[] { path }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println(patternStatistics);

			try {
				final Writer writer =
					ProxyDisk.getInstance().fileTempOutput("Domain.ini");
				ptidej.solver.domain.Manager.save(
					Manager.CodeLevelModel,
					writer);
				writer.close();
			}
			catch (final IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	public void testLoader() {
		try {
			final Reader reader =
				ProxyDisk.getInstance().fileTempInput("Domain.ini");
			final List listOfEntities =
				ptidej.solver.domain.Manager.load(reader);
			reader.close();

			Assert.assertEquals(
				"Number of entities",
				Manager.CodeLevelModel.getNumberOfTopLevelEntities(),
				listOfEntities.size());
		}
		catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
