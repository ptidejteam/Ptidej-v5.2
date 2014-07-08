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


/**
 * @author Janice Ng
 */
public class ScenarioDiagramGenerator {
	public static void main(final String[] args) {
		//final String traceFileName = "MementoModel.trace";
		//final String traceFileName = "Memento_DCClient.trace";
		//final String traceFileName = "Memento_GOF_EVALUATION.trace"; 	
		//final String traceFileName = "Memento_RunMementoPattern_EVALUATION.trace";
		//final String traceFileName = "Memento_RunMementoPattern_1-5_EVALUATION.trace"; 
		//final String traceFileName = "ObserverModel.trace";
		//final String traceFileName = "Observer_SupervisorView_EVALUATION.trace";

		//final String traceFileName = "Visitor_GOF_EVALUATION.trace";	
		//final String traceFileName = "Visitor_OrderManager_EVALUATION.trace";
		//final String traceFileName = "Evaluation_JRefactory_Visitor_PrettyPrinter.trace";
		//final String traceFileName = "Evaluation_JRefactory_Visitor_Metrics_shorten.trace";
		//final String traceFileName = "Evaluation_QuickUML_Command_SaveAction.trace";
		//final String traceFileName = "Evaluation_JHotDraw_Observer_SetTextField.trace";
		//final String traceFileName = "Evaluation_QuickUML_Builder_BuildJavaTataClass.trace";
		//final String traceFileName = "Evaluation_PMD_Visitor_VerifyNaming.trace";
		//final String traceFileName = "Evaluation_PMD_Visitor_VerifyNaming_shorten2.trace";
		//final String traceFileName = "Evaluation_PMD_Builder_VerifyNaming_shorten.trace";
		//		final String traceFileName =
		//			"Evaluation_QuickUML_Builder_BuildJavaTataClass_shorten.trace";
		//		final String traceFileName =
		//			"Evaluation_QuickUML_Command_ResizeADiagram.trace";
		//		final String traceFileName =
		//			"Evaluation_QuickUML_Command_ResizeADiagram.trace";
		//		final String traceFileName =
		//			"Evaluation_QuickUML_Command_ToggleRefreshADiagram.trace";
		//		final String traceFileName =
		//			"Evaluation_QuickUML_Command_ToggleRefreshADiagram_TOTOTOT.trace";
		
		//final String traceFileName = "Builder_SearchManager_EVALUATION.trace";
		final String traceFileName = "Evaluation_SearchManager_Builder_GetSQLStatement.trace";
		//final String traceFileName = "JHotDraw_EVALUATION.trace";
		//final String traceFileName = "JHotDraw_EVALUATION_court.trace";
		//final String traceFileName = "JHotDraw_EVALUATION_CutPasteGOOD.trace";
		//final String traceFileName = "JANICE_court_2.trace";
		//final String traceFileName = "JANICE.trace";
		//final String traceFileName = "JHotDraw_Command_BringToFront.trace";
		//final String traceFileName = "JHotDraw_Command_BringToFront_shorten.trace";
		//final String traceFileName = "Evaluation_JHotDraw_Command_SendToBack.trace";
		//final String traceFileName = "Evaluation_JHotDraw_Visitor_CutAndPasteRectangle.trace";

		//final String traceFileName = "Command_FTPGUI.trace"; // n'applique pas de patrons command !
		//final String traceFileName = "Command_RunCommandPattern.trace";	

		//final String traceFileName = "Loops.trace";
		//final String traceFileName = "TestFilms_EVALUATION.trace";
		//final String traceFileName = "TestFilms_1-5_EVALUATION.trace";
		//final String traceFileName = "TestCallers.trace";

		ExecutionTraceParser.main(
			new String[] {
				 "../MoDeC Bytecode Instrumentation Tests/" + traceFileName });
	}
}