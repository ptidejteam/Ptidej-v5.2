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
import org.apache.commons.lang.ArrayUtils;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGhost;
import padl.kernel.IIdiomLevelModel;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import util.io.ProxyDisk;

public class SimpleMetricsGenerator {

	/**
	 * @param args
	 */
	private static final String[] METRICS_TO_AVOID = new String[] { "CP",
			"EIC", "EIP", "NCP", "PIIR", "PP", "REIP", "RFP", "RPII", "RRFP",
			"RRTP", "RTP" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Computing metrics for Azureus
		//Azureus3.1.0.0.jar
		//String dirName1 = "C:/Documents Foutse/Back_05_02_2010/Software/Workspace3/SQUAD/rsc/ArgoUML - New/ArgoUML-0.12/argouml.jar";
		//		String dirName2 = "rsc/Azureus v." + args[1] + ".jar";
		//		String dirName3 = "rsc/Azureus v." + args[2] + ".jar";
		//		String dirName4 = "rsc/Azureus v." + args[3] + ".jar";
		//		String dirName5 = "rsc/Azureus v." + args[4] + ".jar";
		//		String dirName6 = "rsc/Azureus v." + args[5] + ".jar";
		//		String dirName7 = "rsc/Azureus v." + args[6] + ".jar";
		//		String dirName8 = "rsc/Azureus v." + args[7] + ".jar";
		//		String dirName9 = "rsc/Azureus v." + args[8] + ".jar";

		//		System.out.println(dirName1+"\n");
		//		System.out.println(dirName2 +"\n");
		//		System.out.println(dirName3+"\n");
		//		System.out.println(dirName4+"\n");
		//		System.out.println(dirName5+"\n");
		//		System.out.println(dirName6+"\n");
		//		System.out.println(dirName7+"\n");
		//		System.out.println(dirName8+"\n");
		//		System.out.println(dirName9+"\n");

		/*
		 *  Computation of metrics for Mylyn 
		 */

		//		String dirName1 = "rsc/Mylyn/mylyn-" + args[0] + "-e3.3/e3.3/plugins";	
		//		ComputeQMOOD gen = new ComputeQMOOD();
		//		List files = new ArrayList();
		//
		//		List jarfiles = gen.extractJarsFromDir(dirName1, files);
		//		String [] someJARFiles= gen.createArrayOfJars(jarfiles);
		//		
		//		SimpleMetricsGenerator.computeMetrics(
		//			someJARFiles,
		//			"rsc/QMOOD/MetricsForMylynv." + args[0]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[0]);
		//		
		/*
		*  Computation of metrics for Rhino 
		*/

		String dirName2 =
			"C:/Documents Foutse/Back_05_02_2010/Software/P-Mart Workspace/rhino-14R3/bin/";

		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName2 },
		//			"rsc/QMOOD/MetricsForRhinov14R3.csv");

		System.out.println("Computation completed for version 14R3");
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName3 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[2]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[2]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName4 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[3]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[3]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName5 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[4]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[4]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName6 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[5]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[5]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName7 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[6]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[6]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName8 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[7]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[7]);
		//
		//		SimpleMetricsGenerator.computeMetrics(
		//			new String[] { dirName9 },
		//			"rsc/Results of Smells/Metrics Values/Azureus/Metricsv." + args[8]
		//					+ ".csv");
		//
		//		System.out.println("Computation completed for version" + args[8]);

	}

	/*
	 * We compute metrics from an Array of Jars
	 * 
	 */

	private static void computeMetrics(
		final IIdiomLevelModel idiomLevelModel,
		final String anOutputFileName) {
		try {
			final MetricsRepository metricsRepository =
				MetricsRepository.getInstance();
			final Writer writer =
				ProxyDisk.getInstance().fileTempOutput(
					anOutputFileName + ".metrics");

			// Header.
			{
				final IUnaryMetric[] metrics =
					metricsRepository.getUnaryMetrics();
				for (int i = 0; i < metrics.length; i++) {
					final IUnaryMetric metric = metrics[i];
					final String metricName = metric.getName();
					if (!ArrayUtils.contains(
						SimpleMetricsGenerator.METRICS_TO_AVOID,
						metricName)) {
						//	if (ArrayUtils.contains(
						//		MetricsGenerator.METRICS_TO_COMPUTE,
						//		metricName)) {

						writer.write(metricName);
						if (i < metrics.length - 1) {
							writer.write(';');
						}
					}
				}
				writer.write(";Entities\n");
			}

			// Metric Values.
			final Iterator<?> entityIterator =
				idiomLevelModel.getIteratorOnTopLevelEntities();
			while (entityIterator.hasNext()) {
				final IFirstClassEntity firstClassEntity =
					(IFirstClassEntity) entityIterator.next();

				if (!(firstClassEntity instanceof IGhost)) {
					//	&& !(firstClassEntity instanceof IInterface)) {

					//	System.out.print("Computing metrics for: ");
					//	System.out.println(firstClassEntity.getName());
					//	System.out.print('\t');

					final IUnaryMetric[] metrics =
						metricsRepository.getUnaryMetrics();
					for (int i = 0; i < metrics.length; i++) {
						final IUnaryMetric metric = metrics[i];
						final String metricName = metric.getName();
						if (!ArrayUtils.contains(
							SimpleMetricsGenerator.METRICS_TO_AVOID,
							metricName)) {
							//						if (ArrayUtils.contains(
							//							MetricsGenerator.METRICS_TO_COMPUTE,
							//							metricName)) {

							//	System.out.print(metricName);
							try {
								final double value =
									((IUnaryMetric) metricsRepository
										.getMetric(metricName)).compute(
										idiomLevelModel,
										firstClassEntity);
								//	System.out.print("");
								//	System.out.print(value);
								writer.write(String.valueOf(value));
							}
							catch (final Exception e) {
								writer.write("N/C");
							}

							if (i < metrics.length) {
								//	System.out.print(", ");
								writer.write(';');
							}
						}
					}

					//	System.out.println();
					writer.write(';');
					writer.write(firstClassEntity.getName());
					if (entityIterator.hasNext()) {
						writer.write('\n');
					}
					writer.flush();
				}
			}

			writer.close();
		}
		catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}
	private static void computeMetricsFromJARs(
		final String[] someJARs,
		final String anOutputFileName) {

		System.out.println("Analysing");
		for (int i = 0; i < someJARs.length; i++) {
			final String aJAR = someJARs[i];
			System.out.print('\t');
			System.out.print(aJAR);
			if (i < someJARs.length - 1) {
				System.err.println(',');
			}
		}
		System.out.println("...");

		final IIdiomLevelModel idiomLevelModel =
			ModelGenerator.generateModelFromJARs(someJARs);
		if (idiomLevelModel == null
				|| idiomLevelModel.getNumberOfConstituents() == 0) {

			System.err.println("--");
			System.err.println("Problem with ");
			for (int i = 0; i < someJARs.length; i++) {
				final String aJAR = someJARs[i];
				System.err.print('\t');
				System.err.print(aJAR);
				if (i < someJARs.length - 1) {
					System.err.println(',');
				}
			}
			System.err.println("--");
		}
		else {
			computeMetrics(idiomLevelModel, anOutputFileName);
		}
	}
}
