/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
package ptidej.solver.test.java.fingerprint.solution;

import ptidej.solver.test.java.fingerprint.util.Logger;
import ptidej.solver.test.java.fingerprint.util.TestSolver;



/**
 * @author Jean-Yves Guyomarc'h
 * @since 2004/11/12
 */
public class ComputeSolutions extends TestSolver {
	public ComputeSolutions(
		final String path,
		final String name,
		final String motif) {
		this(path, null, name, motif);
	}
	public ComputeSolutions(
		final String path,
		final String[] packageNames,
		final String name,
		final String motif) {
		super(path, packageNames, name, motif);
	}
	public Class getMotif(final int mode) {
		Class motif = null;
		try {
			if (mode == Logger.WITH_RULES) {
				motif =
					Class.forName(
						ComputeSolutions.FingerprintPackageName
							+ this.getMotifName());
			}
			else {
				motif =
					Class.forName(
						ComputeSolutions.NoFingerprintPackageName
							+ this.getMotifName());
			}
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			return motif;
		}

	}
	public static void main(final String[] args) {
		//			final ComputeSolutions ganttProject =
		//				new ComputeSolutions(
		//					"../GanttProject v1.10.2/bin/",
		//					"AF time for GanttProject",
		//					"AbstractFactoryMotif");
		//			final ComputeSolutions holubSQL =
		//				new ComputeSolutions("../HolubSQL v1.0/bin/", "AF for HolubSQL", "AbstractFactoryMotif");
		//				new ComputeSolutions("../JHotDraw v5.1/bin/", new String[] { "CH.ifa.draw.figures.", "CH.ifa.draw.framework.", "CH.ifa.draw.standard."},"C for JHotDraw Auto (fig,frame, std)", "CompositeMotif");
		//		final ComputeSolutions jsettler =
		//			new ComputeSolutions(
		//				"../JSettlers v1.0.5/bin/",
		//				"A for JSettlers Auto",
		//				"AdapterMotif");
		//			final ComputeSolutions jtans1 =
		//				new ComputeSolutions("../jTans v1.0/bin/", "C time for jTans Auto", "CompositeMotif");
		//				final ComputeSolutions jtans2 =
		//				new ComputeSolutions("../jTans v1.0/bin/", "A for jTans Auto", "AdapterMotif");
		//				final ComputeSolutions jtans3 =
		//				new ComputeSolutions("../jTans v1.0/bin/", "AF for jTans Auto", "AbstractFactoryMotif");
		//			final ComputeSolutions junit =
		//				new ComputeSolutions("../JUnit v3.7/bin/", new String[] { "junit.samples."}, "C for JUnit(samples)", "CompositeMotif");
//					final ComputeSolutions juzzle =
//						new ComputeSolutions("../Juzzle v0.5/bin/", "C for Juzzle", "CompositeMotif");
//					juzzle.computeSolutions();
		//			final ComputeSolutions lexi =
		//				new ComputeSolutions("../Lexi v0.1.1 alph/bin/", "AF for Lexi Auto", "AbstractFactoryMotif");
		//			final ComputeSolutions quick =
		//				new ComputeSolutions("../QuickUML 2001/bin/", "C for QuickUML Auto", "CompositeMotif");
		//			final ComputeSolutions risk =
		//				new ComputeSolutions("../Risk v1.0.7.5/bin/", "C time for Risk Auto", "CompositeMotif");


		ComputeSolutions test = null;
//		
//DONE		for (int i = 0; i < 7; i++) {
//			test =
//				new ComputeSolutions(
//					"../Lexi v0.1.1 alph/bin/", "A for Lexi" + i, "AdapterMotif");
//			test.computeSolutions();
//		}
//		gc();
//		for (int i = 0; i < 7; i++) {
//DONE			test =
//				new ComputeSolutions(
//					"../JUnit v3.7/bin/", "A for JUnit", "AdapterMotif");
//			test.computeSolutions();
//		}
//		gc();
//		for (int i = 0; i < 7; i++) {
//			test =
//				new ComputeSolutions(
//					"../GanttProject v1.10.2/bin/", "A for Gant" + i, "AdapterMotif");
//			test.computeSolutions();
//		}
//		gc();
//		for (int i = 0; i < 7; i++) {
//			test =
//				new ComputeSolutions(
//					"../JSettlers v1.0.5/bin/", "A for JSettler", "AdapterMotif");
//			test.computeSolutions();
//		}
//		
//		
//		gc();
//DONE		for (int i = 0; i < 7; i++) {
//			test =
//				new ComputeSolutions(
//					"../JUnit v3.7/bin/", "AF for JUnit" + i, "AbstractFactoryMotif");
//			test.computeSolutions();
//		}
//		
//		gc();
//		for (int i = 0; i < 7; i++) {
			test =
				new ComputeSolutions(
					"../GanttProject v1.10.2/bin/", "C for Gant", "CompositeMotif");
			test.computeSolutions();
//		}
//		gc();
//		for (int i = 0; i < 7; i++) {
//DONE			test =
//				new ComputeSolutions(
//					"../HolubSQL v1.0/bin/", "C for Holub", "CompositeMotif");
//			test.computeSolutions();
//		}
//		gc();
//		for (int i = 0; i < 7; i++) {
//DONE			test =
//				new ComputeSolutions(
//					"../Lexi v0.1.1 alph/bin/", "C for Lexi", "CompositeMotif");
//			test.computeSolutions();
//			gc();
//		}
	}
	
	public static void gc(){
		for(int i=0 ; i<3 ; i++)
			System.gc();
	}
}
