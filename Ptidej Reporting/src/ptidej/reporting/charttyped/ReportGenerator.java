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
package ptidej.reporting.charttyped;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import ptidej.reporting.charttyped.datasource.TypedOccurrencesDataSource;

/**
 * 
 * @author Yann
 * @since  2008/09/26
 *
 */
public final class ReportGenerator {
	// final String summaryFileName =
	//	"../Z - Integratik/misc/NbDefectsPerClasses inIntegratik.csv";
	// final String sourceDirectoryName = "../Z - Integratik/rsc/smells/";
	// final String summaryFileName =
	// 	"../Z - Integratik/misc/NbDefectsPerClasses inIntegratik.csv";
	// final String destinationDirectoryName =
	// 	"../Z - Integratik/rsc/reports/";
	public final void generateReport(
		final String aSourceDirectoryName,
		final String aDestinationFileName) {
		try {
			final Map parameters = new HashMap();
			parameters.put(
				"SUBREPORT_DIR",
				"../Ptidej Reporting/rsc/charttyped/");
			parameters.put("REPORT_ORIGIN_FILE", aSourceDirectoryName);
			parameters.put("REPORT_SUMMARY_FILE", "");
			JasperFillManager
				.fillReportToFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells.jasper",
					parameters,
					new TypedOccurrencesDataSource(aSourceDirectoryName));

			JasperFillManager
				.fillReportToFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells_CodeSmells.jasper",
					parameters);

			JasperFillManager
				.fillReportToFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells_CodeSmells_Causes.jasper",
					new HashMap());

			JasperFillManager
				.fillReportToFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells_Summary.jasper",
					new HashMap());

			JasperFillManager
				.fillReportToFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells_Summary_Smells.jasper",
					new HashMap());
		}
		catch (final JRException e) {
			e.printStackTrace();
		}

		try {
			JasperExportManager
				.exportReportToHtmlFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells.jrprint",
					aDestinationFileName + ".html");
			JasperExportManager
				.exportReportToPdfFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells.jrprint",
					aDestinationFileName + ".pdf");
			JasperExportManager
				.exportReportToXmlFile(
					"../Ptidej Reporting/rsc/charttyped/CodeandDesignSmells.jrprint",
					aDestinationFileName + ".xml",
					false);
		}
		catch (final JRException e) {
			e.printStackTrace();
		}
	}
}
