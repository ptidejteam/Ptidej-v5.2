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
package modec.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modec.tool.instrumentation.MoDeCInstrumentationTool;

/**
 * @author Janice Ng
 */
public class Instrumentor {
	public static void main(final String[] args) throws IOException {
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation_QuickUML_Command_ToggleRefreshADiagram.txt");
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation_SearchManager_Builder_GetSQLStatement.txt");
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation_JHotDraw_Visitor_CutAndPasteRectangle.txt");
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation_QuickUML_Command_ResizeADiagram.txt");
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation_SupervisorView_Observer_SelectDepartment.txt");
		//	Instrumentor
		//		.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of Rhino.txt");
		// Instrumentor.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of Gombos.txt");
		//	Instrumentor
		//		.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of JHotDraw v5.4b1.txt");
		//	Instrumentor
		//		.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of JHotDraw v5.1.txt");
		//	Instrumentor
		//		.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of JEdit v4.2.txt");
		Instrumentor
			.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of ArgoUML v0.19.8 (without tracing the Cognitive, Configuration, Events, UI).txt");
		//	Instrumentor
		//		.instrumentClasses("../MoDeC Bytecode Instrumentation Tests/Input Files/Evaluation of JFreeChart v1.0.13.txt");
	}
	public static void instrumentClasses(final String filename)
			throws IOException {

		FileReader fr = null;
		try {
			fr = new FileReader(filename);
		}
		catch (final FileNotFoundException e) {
			System.out
				.println("Error occured while opening file (file not found): "
						+ filename);
		}

		final BufferedReader inFile = new BufferedReader(fr);
		final String sourcePathname = inFile.readLine().trim();
		final String targetPathname = inFile.readLine().trim();
		final String libsPathname = inFile.readLine().trim();
		libsPathname.trim();
		final String traceFilename = inFile.readLine().trim();
		inFile.readLine().trim();

		final List listOfClasses = new ArrayList();
		// The first object of the array is the name of the trace file
		listOfClasses.add(traceFilename);
		String classname = inFile.readLine().trim();
		while (classname != null) {
			listOfClasses.add(classname.trim());
			classname = inFile.readLine();
		}

		inFile.close();

		final String[] classesToInstrument = new String[listOfClasses.size()];
		for (int i = 0; i < listOfClasses.size(); i++) {
			classesToInstrument[i] = (String) listOfClasses.get(i);
		}
		MoDeCInstrumentationTool.instrumentBytecode(
			traceFilename,
			sourcePathname,
			classesToInstrument,
			targetPathname);
	}
}