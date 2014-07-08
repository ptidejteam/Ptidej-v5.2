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
package padl.statement.creator.aol;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import padl.analysis.IAnalysis;
import padl.analysis.UnsupportedSourceModelException;
import padl.kernel.IAbstractModel;

public abstract class ModelAnnotator implements IAnalysis {
	private final String[] fileNames;
	private final Map qualifedMethodsMetrics;

	public ModelAnnotator(final String[] someFileNames) {
		this.fileNames = someFileNames;
		this.qualifedMethodsMetrics = new HashMap();
	}
	private void annotateFromDir(final IAbstractModel anAbstractModel) {
		final IMetricValueAdder adder = this.getMetricValueAdder();
		adder.setQualifiedMethodsMetrics(this.qualifedMethodsMetrics);
		anAbstractModel.walk(adder);
	}
	private String findLine(final LineNumberReader reader, final String aHeader) {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(aHeader)) {
					return line.substring(aHeader.length());
				}
			}
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getName() {
		return "Annotator with Conditionals (AOL)";
	}
	public IAbstractModel invoke(final IAbstractModel anAbstractModel)
			throws UnsupportedSourceModelException {

		for (int i = 0; i < this.fileNames.length; i++) {
			final String fileName = this.fileNames[i];
			this.relateQualifiedMethodMetric(fileName);
		}
		this.annotateFromDir(anAbstractModel);
		return anAbstractModel;
	}
	private void relateQualifiedMethodMetric(final String aFileName) {
		final String fileName =
			aFileName.replaceAll("concat_", "").replaceAll(".aol", ".met");
		try {
			final LineNumberReader reader =
				new LineNumberReader(new FileReader(fileName));
			String line;
			while ((line = this.findLine(reader, "	METHOD ")) != null) {
				final String qualifiedName =
					line.replace('!', '.').replace(' ', '_');
				final String metricValue =
					this.findLine(reader, this.getMetricHeader());
				this.qualifedMethodsMetrics.put(qualifiedName, metricValue);
			}
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public abstract String getMetricHeader();
	public abstract IMetricValueAdder getMetricValueAdder();
}
