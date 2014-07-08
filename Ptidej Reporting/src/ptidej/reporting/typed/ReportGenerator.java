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
package ptidej.reporting.typed;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import ptidej.reporting.typed.datasource.TypedOccurrencesDataSource;

/**
 * 
 * @author Yann
 * @since  2008/09/26
 *
 */
public class ReportGenerator {
	public static void main(final String[] args) {
		final String sourceDirectoryName =
			"D:/Documents/Collaborations/Industries/Google Montréal/140421 - chrome.browser.net Anti-patterns Identification/";
		final String tempDirectoryName =
			"D:/Documents/Collaborations/Industries/Google Montréal/140421 - chrome.browser.net Anti-patterns Reports/Temp/";
		final String destinationDirectoryName =
			"D:/Documents/Collaborations/Industries/Google Montréal/140421 - chrome.browser.net Anti-patterns Reports/";
		try {
			final Map parameters = new HashMap();
			parameters.put("SUBREPORT_DIR", tempDirectoryName);
			parameters.put("REPORT_ORIGIN_FILE", sourceDirectoryName);

			JasperFillManager.fillReportToFile(
				tempDirectoryName + "CodeandDesignSmells.jasper",
				parameters,
				new TypedOccurrencesDataSource(sourceDirectoryName));

			JasperFillManager.fillReportToFile(tempDirectoryName
					+ "CodeandDesignSmells_CodeSmells.jasper", parameters);

			JasperFillManager.fillReportToFile(
				tempDirectoryName
						+ "CodeandDesignSmells_CodeSmells_Causes.jasper",
				new HashMap());
		}
		catch (final JRException e) {
			e.printStackTrace();
		}

		try {
			JasperExportManager.exportReportToHtmlFile(tempDirectoryName
					+ "CodeandDesignSmells.jrprint", destinationDirectoryName
					+ "CodeandDesignSmells.html");
			JasperExportManager.exportReportToPdfFile(tempDirectoryName
					+ "CodeandDesignSmells.jrprint", destinationDirectoryName
					+ "CodeandDesignSmells.pdf");
			JasperExportManager.exportReportToXmlFile(tempDirectoryName
					+ "CodeandDesignSmells.jrprint", destinationDirectoryName
					+ "CodeandDesignSmells.xml", false);
		}
		catch (final JRException e) {
			e.printStackTrace();
		}
	}
}
