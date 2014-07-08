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
package ptidej.solver.test.java.fingerprint.reduction;

import java.io.PrintWriter;
import ptidej.solver.test.java.fingerprint.util.Logger;
import ptidej.solver.test.java.fingerprint.util.TestSolver;
import util.io.ProxyDisk;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2004/11/12
 */
public class ComputeDomainReduction extends TestSolver {
	public ComputeDomainReduction(
		final String path,
		final String name,
		final String motif) {
		this(path, null, name, motif);
	}
	public ComputeDomainReduction(
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
					Class.forName(TestSolver.FingerprintPackageName
							+ this.getMotifName());
			}
			else {
				motif =
					Class.forName(TestSolver.NoFingerprintPackageName
							+ this.getMotifName());
			}
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		return motif;
	}
	public static void main(final String[] args) {
		final ComputeDomainReduction ganttProject =
			new ComputeDomainReduction(
				"../GanttProject v1.10.2/bin/",
				"GanttProject",
				"CompositeMotif");
		final ComputeDomainReduction holubSQL =
			new ComputeDomainReduction(
				"../HolubSQL v1.0/bin/",
				"HolubSQL",
				"CompositeMotif");
		final ComputeDomainReduction johtdraw =
			new ComputeDomainReduction(
				"../JHotDraw v5.1/bin/",
				new String[] { "CH.ifa.draw.figures.", "CH.ifa.draw.standard." },
				"JHotDraw ( limited )",
				"CompositeMotif");
		final ComputeDomainReduction jsettler =
			new ComputeDomainReduction(
				"../JSettlers v1.0.5/bin/",
				new String[] { "soc.robot." },
				"JSettler( soc.robot )",
				"CompositeMotif");
		final ComputeDomainReduction jtans =
			new ComputeDomainReduction(
				"../jTans v1.0/bin/",
				"jTans",
				"CompositeMotif");
		final ComputeDomainReduction junit =
			new ComputeDomainReduction(
				"../JUnit v3.7/bin/",
				"JUnit",
				"CompositeMotif");
		final ComputeDomainReduction juzzle =
			new ComputeDomainReduction(
				"../Juzzle v0.5/bin/",
				"Juzzle",
				"CompositeMotif");
		final ComputeDomainReduction lexi =
			new ComputeDomainReduction(
				"../Lexi v0.1.1 alph/bin/",
				"Lexi",
				"CompositeMotif");
		final ComputeDomainReduction quick =
			new ComputeDomainReduction(
				"../QuickUML 2001/bin/",
				"QuickUML",
				"CompositeMotif");
		final ComputeDomainReduction risk =
			new ComputeDomainReduction(
				"../Risk v1.0.7.5/bin/",
				"Risk",
				"CompositeMotif");

		final PrintWriter out =
			new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
				"rsc/DomainReductionFor" + junit.getMotifName() + "2.txt"));

		ganttProject.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		holubSQL.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		johtdraw.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		jsettler.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		jtans.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		junit.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		juzzle.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		lexi.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		quick.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		risk.computeDomainReduction(out);
		out.println("--------------------------------------------------\n");
		out.flush();

		out.close();
	}
}
