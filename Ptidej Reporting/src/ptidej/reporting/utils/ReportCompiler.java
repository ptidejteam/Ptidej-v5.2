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
package ptidej.reporting.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class ReportCompiler {
	public static void main(String[] args) {
		try {
			//	JasperCompileManager.compileReportToFile("rsc/simple/CodeandDesignSmells.jrxml");
			//	JasperCompileManager.compileReportToFile("rsc/simple/CodeandDesignSmells_CodeSmells.jrxml");
			//	JasperCompileManager.compileReportToFile("rsc/mixing/CodeandDesignSmells.jrxml");
			//	JasperCompileManager.compileReportToFile("rsc/mixing/CodeandDesignSmells_CodeSmells.jrxml");
			//	JasperCompileManager
			//		.compileReportToFile("rsc/typed/CodeandDesignSmells.jrxml");
			//	JasperCompileManager
			//		.compileReportToFile("rsc/typed/CodeandDesignSmells_CodeSmells.jrxml");
			//	JasperCompileManager
			//		.compileReportToFile("rsc/typed/CodeandDesignSmells_CodeSmells_Causes.jrxml");
			JasperCompileManager
				.compileReportToFile("rsc/charttyped/CodeandDesignSmells.jrxml");
			JasperCompileManager
				.compileReportToFile("rsc/charttyped/CodeandDesignSmells_CodeSmells.jrxml");
			JasperCompileManager
				.compileReportToFile("rsc/charttyped/CodeandDesignSmells_CodeSmells_Causes.jrxml");
			JasperCompileManager
				.compileReportToFile("rsc/charttyped/CodeandDesignSmells_Summary.jrxml");
			JasperCompileManager
				.compileReportToFile("rsc/charttyped/CodeandDesignSmells_Summary_Smells.jrxml");
		}
		catch (final JRException e) {
			e.printStackTrace();
		}
	}
}
