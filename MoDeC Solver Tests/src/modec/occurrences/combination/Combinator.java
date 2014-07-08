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
package modec.occurrences.combination;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.Util;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.OccurrenceComponent;
import util.io.ProxyDisk;

public class Combinator {
	public static void main(final String[] args) {
		final String aName = "JHotDraw v5.4b1";
		final String aClassPath = "../../P-MARt Workspace/JHotDraw v5.4b1/bin/";
		final String someOccurrencesFile =
			"rsc/JSME'08 (bis)/ConstraintResults in JHotDraw v5.4b1 for Command.ini";
		final String anOutputFile =
			"rsc/JSME'08 (bis)/Cleaned ConstraintResults in JHotDraw v5.4b1 for Command.ini";

		try {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(aName);
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { aClassPath },
				true));

			final Properties occurrencesProperties = new Properties();
			occurrencesProperties
				.load(new FileInputStream(someOccurrencesFile));
			final OccurrenceBuilder builder = OccurrenceBuilder.getInstance();
			final Occurrence[] canonicalStaticOccurrences =
				builder.getCanonicalOccurrences(occurrencesProperties);

			System.out.println("Number of canonical static occurrences: "
					+ canonicalStaticOccurrences.length);

			final IFirstClassEntity superEntity =
				codeLevelModel
					.getTopLevelEntityFromID("CH.ifa.draw.application.DrawApplication");
			final List validOccurrences = new ArrayList();
			for (int i = 0; i < canonicalStaticOccurrences.length; i++) {
				final Occurrence occurrence = canonicalStaticOccurrences[i];
				final OccurrenceComponent occurrenceComponent =
					occurrence.getComponent("client".toCharArray());
				final String clientClass =
					occurrenceComponent.getDisplayValue();
				final IFirstClassEntity client =
					codeLevelModel.getTopLevelEntityFromID(clientClass);

				if (client != null
						&& (client.equals(superEntity) || Util
							.isEntityInheritingFrom(client, superEntity))) {
					validOccurrences.add(occurrence);
				}
			}

			System.out.println("Number of valid static occurrences: "
					+ validOccurrences.size());
			final Occurrence[] validOccurrencesArray =
				new Occurrence[validOccurrences.size()];
			validOccurrences.toArray(validOccurrencesArray);
			Occurrence.print(validOccurrencesArray, ProxyDisk
				.getInstance()
				.fileTempOutput(anOutputFile));
		}
		catch (final CreationException e) {
			e.printStackTrace();
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
