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
package ptidej.solver.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import util.io.ReaderInputStream;

/**
 * @author guehene
 */
public class NumberOfSolutionsCounter {
	public static void main(final String[] args) {
		try {
			final String[] solutionFiles =
				new String[] {
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070303 - ConstraintResults in MapperXML v1.9.7 for Composite WITH CARDINALITY CONSTRAINT.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070303 - ConstraintResults in QuickUML 2001 for Composite WITH CARDINALITY CONSTRAINT.ini",
				// "../Ptidej Solver Data/ConstraintResults in JHotDraw v5.1 for Observer1.ini",
				// "../Ptidej Solver Data/ConstraintResults in JHotDraw v5.1 for Observer2.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JHotDraw v5.1 for Observer1.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JHotDraw v5.1 for Observer2.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070310 - ConstraintResults in JHotDraw v5.1 for Command.ini",
				"../Ptidej Solver Data/TSE'07 (Open Source Systems)/070303 - ConstraintResults in JHotDraw v5.1 for State.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070310 - ConstraintResults in JHotDraw v5.1 for Composite.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JRefactory v2.6.24 for Observer1.ini",
				//"../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JRefactory v2.6.24 for Observer2.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JUnit v3.7 for Observer1.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in JUnit v3.7 for Observer2.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in MapperXML v1.9.7 for Observer1.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in MapperXML v1.9.7 for Observer2.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in QuickUML 2001 for Observer1.ini",
				// "../Ptidej Solver Data/TSE'07 (Open Source Systems)/070817 - ConstraintResults in QuickUML 2001 for Observer2.ini" 
				};

			final OccurrenceBuilder solutionBuilder =
				OccurrenceBuilder.getInstance();
			final Properties allOccurrences = new Properties();
			for (int i = 0; i < solutionFiles.length; i++) {
				final String file = solutionFiles[i];
				final Properties properties = new Properties();
				properties.load(new ReaderInputStream(new FileReader(file)));
				allOccurrences.putAll(properties);
			}
			final Occurrence[] occurrences =
				solutionBuilder.getCanonicalOccurrences(allOccurrences);
			System.out.println(occurrences.length);
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
