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
package squad.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IEntity;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import util.io.ProxyDisk;

public class ListofClassesGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		 ListofClassesGenerator.generateListofClasses(
		//		 "D:/Software/P-MARt Workspace/ArgoUML v.0.19.8/bin/",
		//		 "ArgoUML v0.19.8", "rsc/misc/ListOfClassesForArgoUML.txt");

		//*		 ListofClassesGenerator.generateListofClasses(
		//		 "D:/Software/P-MARt Workspace/Azureus v2.1.0.0/bin/",
		//		 "Azureus v2.1.0.0", "rsc/misc/ListOfClassesForAzureus.txt");

		//		 ListofClassesGenerator.generateListofClasses(
		//		 "D:/Software/P-MARt Workspace/org.eclipse.jdt.core_2.1.2/jdtcore.jar",
		//		 "Eclipse v2.1.2 (JDT)", "rsc/misc/ListOfClassesForJDT.txt");
		//
		//		ListofClassesGenerator.generateListofClasses(
		//				"D:/Software/P-MARt Workspace/JHotDraw v5.1/bin/",
		//				"JHotDraw v5.1", "rsc/misc/ListOfClassesForJHotDraw.txt");
		//		
		/*ListofClassesGenerator.generateListofClasses(
			"D:/Software/P-MARt Workspace/xalan v2.7.0/bin/",
			"Xalan v2.7.0",
			"rsc/misc/ListOfClassesForXalanJ.txt");*/

		//		 ListofClassesGenerator.generateListofClasses(
		//		 "D:/Software/P-MARt Workspace/xerces v.1.4.4/bin/",
		//		 "Xerces v1.4.4", "rsc/ListOfClassesForXercesJ.txt");

		//Generate classes for JHotDraw 		
		/*	String dirName1 = "D:/Software/P-MARt Workspace/JHotDraw v.7.1/bin/";
			
			ListofClassesGenerator.generateListofClasses(
				dirName1,
				"JHotDraw v7.1",
				"FinalResults/Azureus Classes/Classes v7.1 .csv");*/
		//Generate classes for  Azureus	
		String dirName2 = "D:/Software/P-MARt Workspace/Azureus v.4.2.0.2.jar";

		ListofClassesGenerator.generateListofClassesFromJar(
			new String[] { dirName2 },
			"Azureus v4.2.0.2",
			"FinalResults/Azureus Classes/Classes v4.2.0.2 .csv");

	}

	private static void generateListofClasses(
		final String aPath,
		final String aName,
		final String anOutputFile) {
		try {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(aName);
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { aPath },
				true));
			Iterator<?> iter = codeLevelModel.getIteratorOnTopLevelEntities();

			final Writer out =
				ProxyDisk.getInstance().fileTempOutput(anOutputFile, true);

			//BufferedWriter out = new BufferedWriter(fw);

			while (iter.hasNext()) {

				IEntity anElement = (IEntity) iter.next();

				out.write(anElement.getDisplayName());
				out.flush();
				out.write('\n');
				out.flush();

			}

			out.close();

		}
		catch (final CreationException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Generate list of classes from jar
	 * 
	 */
	private static void generateListofClassesFromJar(
		final String[] someJARFiles,
		final String aName,
		final String anOutputFile) {
		try {

			ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(aName);
			codeLevelModel.create(new CompleteClassFileCreator(
				someJARFiles,
				true));

			System.out.println("Construction of the model completed........");
			Iterator<?> iter = codeLevelModel.getIteratorOnTopLevelEntities();

			final Writer out =
				ProxyDisk.getInstance().fileTempOutput(anOutputFile, true);

			//BufferedWriter out = new BufferedWriter(fw);

			while (iter.hasNext()) {

				IEntity anElement = (IEntity) iter.next();

				out.write(anElement.getDisplayName());
				out.flush();
				out.write('\n');
				out.flush();

			}

			out.close();

		}
		catch (final CreationException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

	}

}