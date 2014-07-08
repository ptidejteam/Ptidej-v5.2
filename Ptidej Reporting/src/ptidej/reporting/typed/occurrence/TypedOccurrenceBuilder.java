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
package ptidej.reporting.typed.occurrence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceComponent;

/**
 * 
 * @author Yann
 * @since  2008/10/03
 *
 */
public class TypedOccurrenceBuilder {
	private static TypedOccurrenceBuilder UniqueInstance;
	public static TypedOccurrenceBuilder getInstance() {
		if (TypedOccurrenceBuilder.UniqueInstance == null) {
			TypedOccurrenceBuilder.UniqueInstance =
				new TypedOccurrenceBuilder();
		}
		return TypedOccurrenceBuilder.UniqueInstance;
	}

	private TypedOccurrenceBuilder() {
	}
	public TypedOccurrence[] getTypedOccurrences(
		final Occurrence[] someNonTypedOccurrences) {
		// Yann 2008/10/03: Assumption!
		// The building ofthe typed occurrences follow some
		// strong (?) assumption: any component whose name contains 
		// "Class" is considered as representing a class name.

		final Map entityNamesDesignSmells = new HashMap();
		for (int i = 0; i < someNonTypedOccurrences.length; i++) {
			final Occurrence occurrence = someNonTypedOccurrences[i];
			final List listOfCauses = new ArrayList();

			String entityName = "UNKNOWN";
			final Iterator componentsIterator =
				occurrence.getComponents().iterator();
			while (componentsIterator.hasNext()) {
				final OccurrenceComponent component =
					(OccurrenceComponent) componentsIterator.next();
				final String componentName = component.getDisplayName();
				final String componentValue = component.getDisplayValue();

				if ((componentName.indexOf("Class") > -1
						|| componentName.indexOf("HasChildren") > -1
						|| componentName.indexOf("NoInheritance") > -1
						|| componentName.indexOf("LowCohesion") > -1
						|| componentName.indexOf("FieldPublic") > -1
						|| componentName.indexOf("ManyAttributes") > -1
						|| componentName.indexOf("MultipleInterface") > -1
						|| componentName.indexOf("LongMethod") > -1 || componentName
					.indexOf("LongParameterList") > -1)
						&& componentName.indexOf('.') == -1) {

					entityName = componentValue;
				}
				else {
					listOfCauses.add(new DesignSmellCause(
						componentName,
						componentValue));
				}
			}

			TypedOccurrenceBuilder.appendDesignSmell(
				entityNamesDesignSmells,
				entityName,
				occurrence.getDisplayName(),
				occurrence.getConfidence(),
				listOfCauses);
		}

		final TypedOccurrence[] typedOccurrences =
			new TypedOccurrence[entityNamesDesignSmells.keySet().size()];
		final Iterator iteratorOnEntities =
			entityNamesDesignSmells.keySet().iterator();
		int i = 0;
		while (iteratorOnEntities.hasNext()) {
			final String entityName = (String) iteratorOnEntities.next();
			final List listOfExplainedDesignSmells =
				(List) entityNamesDesignSmells.get(entityName);
			typedOccurrences[i] =
				new TypedOccurrence(entityName, listOfExplainedDesignSmells);
			i++;
		}

		return typedOccurrences;
	}
	private static void appendDesignSmell(
		final Map anEntityNamesDesignSmells,
		final String anEntityName,
		final String aDesignSmellName,
		final int aConfidenceValue,
		final List listOfCauses) {

		List listOfExplainedDesignSmells =
			(List) anEntityNamesDesignSmells.get(anEntityName);
		if (listOfExplainedDesignSmells == null) {
			listOfExplainedDesignSmells = new ArrayList();
			anEntityNamesDesignSmells.put(
				anEntityName,
				listOfExplainedDesignSmells);
		}
		listOfExplainedDesignSmells.add(new ExplainedDesignSmell(
			aDesignSmellName,
			aConfidenceValue,
			listOfCauses));
	}
}
