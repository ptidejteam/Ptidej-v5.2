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

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import ptidej.solver.test.java.fingerprint.util.Logger;
import ptidej.solver.test.java.fingerprint.util.TestSolver;

/**
 * @author Jean-Yves Guillomarc'h
 * @since 2004/11/12
 */
public class FastComputeDomainReduction extends TestSolver {

	public FastComputeDomainReduction(
		final String path,
		final String name,
		final String motif) {
		this(path, null, name, motif);
	}
	public FastComputeDomainReduction(
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
						FastComputeDomainReduction.FingerprintPackageName
							+ this.getMotifName());
			}
			else {
				motif =
					Class.forName(
						FastComputeDomainReduction.NoFingerprintPackageName
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
		// args: -src path [-pkg [packageName]] -name programName -motif motif
		FastComputeDomainReduction compute = null;
		if (args[2].equals("-pkg")) {
			
			//Creation of pkg tab
			int cpt = 3;
			ArrayList pkgList = new ArrayList();
			while( !args[cpt].equals("-name") ){
				pkgList.add(args[cpt]);
				cpt++;
			}
			String[] pkgTab = new String[pkgList.size()];
			for( int i = 0; i < pkgList.size() ;i++)
				pkgTab[i] = (String)pkgList.get(i);
				
			compute =
				new FastComputeDomainReduction(
					args[1],
					pkgTab,
					args[cpt+1],
					args[cpt+3]);

		}
		else {
			if (args.length == 6) {
				compute =
					new FastComputeDomainReduction(args[1], args[3], args[5]);
			}
			else {
				System.out.println(
					"Usage: FastComputeDomainReduction -src path [-pkg [packageName]] -name programName -motif motif");
				System.exit(0);
			}
		}

		try {
			final PrintWriter out =
				new PrintWriter(
					new FileWriter(
						"rsc/DomainReductionFor"
							+ compute.getMotifName()
							+ "On"
							+ compute.getProgramName()
							+ ".txt"));

			compute.computeDomainReduction(out);
			out.flush();

			out.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
