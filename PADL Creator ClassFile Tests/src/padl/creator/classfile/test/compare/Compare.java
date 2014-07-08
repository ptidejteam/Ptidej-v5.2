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
package padl.creator.classfile.test.compare;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Factory;
import padl.util.ModelStatistics;

/**
 * @author Yann-Gäel Guéhéneuc
 * @since  2004/02/18
 */
public class Compare extends ClassFilePrimitive {
	public Compare(String aName) {
		super(aName);
	}
	public void testCompare() {
		final String path = "../Ptidej Tests/bin/ptidej/example/composite2/";
		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel(path);
		final ModelStatistics patternStatistics = new ModelStatistics();
		codeLevelModel.addModelListener(patternStatistics);

		try {
			// Building the program representation.
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { path }));
			System.out.println();
			System.out.println(patternStatistics);

			// Detecting design pattern.
			System.out.println();
			final Map solutions = new HashMap();
			//	idiomLevelModel.compare(
			//		PatternRepository.getCurrentPatternRepository(
			//			fileRepository));

			// Display solutions... 
			final Iterator iterator = solutions.keySet().iterator();
			while (iterator.hasNext()) {
				final String currentPattern = (String) iterator.next();
				final List patternSolutions =
					(List) solutions.get(currentPattern);
				System.out.println("* Model: " + currentPattern + ": "
						+ patternSolutions.size() + " instance(s).");
				if (patternSolutions.size() > 0) {
					final Iterator iterator2 = patternSolutions.iterator();
					while (iterator2.hasNext()) {
						final Map currentSol = (Map) iterator2.next();
						final Iterator iterator3 =
							currentSol.keySet().iterator();
						while (iterator3.hasNext()) {
							final String currentConstituent =
								(String) iterator3.next();
							System.out.print(currentConstituent);
							System.out.println(':');
							final Iterator iterator4 =
								((List) currentSol.get(currentConstituent))
									.iterator();
							while (iterator4.hasNext()) {
								System.out.print('\t');
								System.out
									.println(((IFirstClassEntity) iterator4
										.next()).getName());
							}
							System.out.println();
						}
						System.out.println("----");
					}
				}
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
		}

		// TODO: Convert the output into assertions...
	}
}
