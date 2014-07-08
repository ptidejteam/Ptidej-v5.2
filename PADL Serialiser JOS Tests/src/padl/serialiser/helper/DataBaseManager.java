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

import java.io.IOException;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.IAbstractModelSerialiser;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.serialiser.JOSSerialiser;
import util.io.ProxyConsole;

public class DataBaseManager {
	public static void main(final String[] args) {
		long beginningTime = 0;
		long endTime = 0;
		long beginningFreeMemory = 0;
		long endFreeMemory = 0;

		beginningTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		beginningFreeMemory = Runtime.getRuntime().freeMemory();

		IAbstractModel abstractModel =
			DataBaseManager.createAbstractModel(
				"ArgoUML v0.20",
				"../../P-MARt Workspace/ArgoUML v0.20/");
		//	IAbstractModel abstractModel =
		//		DataBaseManager.createAbstractModel(
		//			"JHotDraw v5.1",
		//			"../../P-MARt Workspace/JHotDraw v5.1/bin/");

		endTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		endFreeMemory = Runtime.getRuntime().freeMemory();

		System.out.print("Model created in ");
		System.out.print(endTime - beginningTime);
		System.out.print("ms. with ");
		System.out.print(endFreeMemory - beginningFreeMemory);
		System.out.println(" bytes.");

		System.out.println("Waiting for user input...");
		try {
			System.in.read();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		System.out.println("\nSerialising model...");

		final IAbstractModelSerialiser serialiser = JOSSerialiser.getInstance();

		beginningTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		beginningFreeMemory = Runtime.getRuntime().freeMemory();

		final String dataBaseName =
			serialiser.serialiseWithAutomaticNaming(abstractModel);

		endTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		endFreeMemory = Runtime.getRuntime().freeMemory();

		System.out.print("Model serialised in ");
		System.out.print(endTime - beginningTime);
		System.out.print("ms. with ");
		System.out.print(endFreeMemory - beginningFreeMemory);
		System.out.println(" bytes.");

		beginningTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		beginningFreeMemory = Runtime.getRuntime().freeMemory();

		abstractModel = serialiser.deserialise(dataBaseName);

		endTime = System.currentTimeMillis();
		Runtime.getRuntime().gc();
		endFreeMemory = Runtime.getRuntime().freeMemory();

		System.out.print("Model deserialised in ");
		System.out.print(endTime - beginningTime);
		System.out.print("ms. with ");
		System.out.print(endFreeMemory - beginningFreeMemory);
		System.out.println(" bytes.");
	}
	private static IAbstractModel createAbstractModel(
		final String aName,
		final String aClassPath) {

		System.out.println("Creating code-level model...");
		ICodeLevelModel codeLevelModel;
		try {
			codeLevelModel = Factory.getInstance().createCodeLevelModel(aName);
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { aClassPath },
				true));

			//	System.out.println("Creating idiom-level model...");
			//	final IIdiomLevelModel idiomLevelModel =
			//		(IIdiomLevelModel) new AACRelationshipsAnalysis()
			//			.invoke(codeLevelModel);
			//	System.out.println("Idiom-level model with "
			//			+ idiomLevelModel.getNumberOfTopLevelEntities()
			//			+ " entities.");

			return codeLevelModel;
		}
		catch (final CreationException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		//	catch (final UnsupportedSourceModelException e) {
		//		e.printStackTrace(Output.getInstance().errorOutput());
		//	}
		return null;
	}
}
