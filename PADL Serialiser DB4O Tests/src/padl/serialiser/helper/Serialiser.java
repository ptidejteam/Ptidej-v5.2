/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package padl.serialiser.helper;

import java.io.File;
import java.io.IOException;
import padl.analysis.UnsupportedSourceModelException;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.exception.CreationException;
import padl.serialiser.DB4OSerialiser;
import padl.test.helper.ModelComparator;
import padl.util.ModelStatistics;
import util.io.ProxyConsole;
import util.io.ProxyDisk;

public class Serialiser {
	public static void main(String[] args) throws CreationException,
			UnsupportedSourceModelException, IOException {

		final String mainPath = "../../P-MARt Workspace/";
		final String[] programNames = new File(mainPath).list();
		for (int i = 0; i < programNames.length; i++) {
			final String programName = programNames[i];

			if (programName.startsWith("Eclipse JDT")) {
				final String programPath = mainPath + programName + "/libs/";

				ProxyConsole.getInstance().setDebugOutput(
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/" + programName + ".dbg"));
				ProxyConsole.getInstance().setErrorOutput(
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/" + programName + ".err"));
				ProxyConsole.getInstance().setNormalOutput(
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/" + programName + ".out"));

				ProxyConsole
					.getInstance()
					.normalOutput()
					.println("Creating model...");
				System.out.println(programName);
				long beginning = System.currentTimeMillis();

				final ModelStatistics statistics = new ModelStatistics();
				final IAbstractLevelModel abstractLevelModel =
					ModelGenerator.generateModelFromJARs(
						programName,
						programPath,
						statistics);

				long end = System.currentTimeMillis();
				ProxyConsole.getInstance().normalOutput().println(statistics);
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print("Model created in ");
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print(end - beginning);
				ProxyConsole.getInstance().normalOutput().println(" ms.");
				beginning = System.currentTimeMillis();

				final String serialisedFileName =
					DB4OSerialiser.getInstance().serialiseWithAutomaticNaming(
						abstractLevelModel);

				end = System.currentTimeMillis();
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print("Model serialised in ");
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print(end - beginning);
				ProxyConsole.getInstance().normalOutput().println(" ms.");
				beginning = System.currentTimeMillis();

				final IAbstractLevelModel serialisedAbstractLevelModel =
					(IAbstractLevelModel) DB4OSerialiser
						.getInstance()
						.deserialise(serialisedFileName);

				end = System.currentTimeMillis();
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print("Model deserialised in ");
				ProxyConsole
					.getInstance()
					.normalOutput()
					.print(end - beginning);
				ProxyConsole.getInstance().normalOutput().println(" ms.");

				abstractLevelModel.walk(new ModelComparator(
					serialisedAbstractLevelModel));

				ProxyConsole.getInstance().debugOutput().close();
				ProxyConsole.getInstance().errorOutput().close();
				ProxyConsole.getInstance().normalOutput().close();
			}
		}
	}
}
