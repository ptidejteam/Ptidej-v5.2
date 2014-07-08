package sad.misc;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IInterface;
import padl.kernel.IOperation;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import util.io.ProxyDisk;

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

/**
 * @author Naouel Moha
 * @since  2007/10/29
 * 
 * Return a csv file in the following format:
 * Name of Program ; number of classes ; number of interfaces ; number of methods 
 *  
 */
public class ComputeSizeMetrics {

	public static void main(final String[] args) {
		final String MAIN_PATH = "../DPL - Evolution/";
		final String RESULT_PATH = "rsc/evolution/ComputeSizeMetrics2.csv";

		final ComputeSizeMetrics sizeMetrics = new ComputeSizeMetrics();
		final PrintWriter aWriter =
			new PrintWriter(ProxyDisk.getInstance().fileTempOutput(RESULT_PATH));
		sizeMetrics.analyseCodeLevelModels(aWriter, MAIN_PATH);
		aWriter.close();
	}
	private void analyseCodeLevelModel(
		final PrintWriter aWriter,
		final String aClassPath,
		final String aName) {

		int numberOfClasses = 0;
		int numberOfInterfaces = 0;
		int numberOfMethods = 0;

		aWriter.print(aName + ";");

		if (aName.indexOf("Xalan") > -1) {
			try {
				System.out.print("Creating model of ");
				System.out.print(aName);
				System.out.println("...");

				try {
					final ICodeLevelModel codeLevelModel =
						Factory.getInstance().createCodeLevelModel(aName);
					codeLevelModel.create(new CompleteClassFileCreator(
						new String[] { aClassPath },
						true));
					//	final ModelAnnotatorInstructions annotator =
					//		new ModelAnnotatorInstructions();
					//	annotator.annotateFromDirs(
					//		new String[] { aClassPath },
					//		codeLevelModel);
					//	final IIdiomLevelModel idiomLevelModel =
					//		(IIdiomLevelModel) new AACRelationshipsAnalysis().invoke(
					//			codeLevelModel);

					System.out.print("Computing metrics on ");
					System.out.print(aName);
					System.out.println("...");
					final Iterator iterOnClasses =
						codeLevelModel.getIteratorOnConstituents(IClass.class);
					while (iterOnClasses.hasNext()) {
						final IClass aClass = (IClass) iterOnClasses.next();
						numberOfClasses++;
						numberOfMethods +=
							aClass.getNumberOfConstituents(IOperation.class);
					}
					aWriter.print(numberOfClasses + ";");

					numberOfInterfaces =
						codeLevelModel
							.getNumberOfConstituents(IInterface.class);
					aWriter.print(numberOfInterfaces + ";");
					aWriter.println(numberOfMethods);
				}
				catch (final NullPointerException e) {
					e.printStackTrace();
				}
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}
		}
	}
	private void analyseCodeLevelModels(
		final PrintWriter aWriter,
		final String aWorkspacePath) {
		final File workspaceDir = new File(aWorkspacePath);
		final String[] projectList = workspaceDir.list();
		for (int i = 0; i < projectList.length; i++) {
			final String project = projectList[i];
			final String binPath = aWorkspacePath + project + "/bin/";
			final File binFile = new File(binPath);
			if (binFile.exists()) {
				this.analyseCodeLevelModel(aWriter, binPath, project);
			}
		}
	}
}
