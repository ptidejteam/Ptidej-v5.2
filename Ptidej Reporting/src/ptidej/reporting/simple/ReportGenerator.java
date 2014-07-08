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
package ptidej.reporting.simple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import ptidej.reporting.simple.datasource.OccurrencesDataSource;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * 
 * @author Yann
 * @since  2008/09/26
 *
 */
public class ReportGenerator {
	public static void main(final String[] args) {
		final String fileName =
			"rsc/simple/DetectionResults in Eclipse v3.1.2 for Blob.ini";
		final Properties properties = new Properties();
		Occurrence[] occurrences = null;
		try {
			properties.load(new FileInputStream(fileName));
			occurrences =
				OccurrenceBuilder.getInstance().getCanonicalOccurrences(
					properties);
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		try {
			final Map parameters = new HashMap();
			parameters.put("SUBREPORT_DIR", "./rsc/simple/");
			parameters.put("REPORT_ORIGIN_FILE", fileName);
			JasperFillManager.fillReportToFile(
				"rsc/simple/CodeandDesignSmells.jasper",
				parameters,
				new OccurrencesDataSource(occurrences));

			JasperFillManager.fillReportToFile(
				"rsc/simple/CodeandDesignSmells_CodeSmells.jasper",
				new HashMap());
		}
		catch (final JRException e) {
			e.printStackTrace();
		}

		try {
			JasperExportManager
				.exportReportToHtmlFile("rsc/simple/CodeandDesignSmells.jrprint");
			//	JasperExportManager
			//		.exportReportToHtmlFile("rsc/CodeandDesignSmells_CodeSmells.jrprint");
			JasperExportManager
				.exportReportToPdfFile("rsc/simple/CodeandDesignSmells.jrprint");
		}
		catch (final JRException e) {
			e.printStackTrace();
		}
	}
}
