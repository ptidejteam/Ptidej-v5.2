package padl.micropattern.repository;

import java.util.Iterator;
import padl.kernel.IClass;
import padl.kernel.IConstructor;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.micropattern.IMicroPatternDetection;

public final class RestrictedCreationDetection extends
		AbstractMicroPatternDetection implements IMicroPatternDetection {

	public String getName() {
		return "RestrictedCreationDetection";
	}

	/*
	 *  11. Restricted Creation. A class with no public constructors, and at
	 *	least one static ям?eld of the same type as the class, matches the
	 *	Restricted Creation micro pattern.
	 *	Many S I N G L E T O N classes satisfy this criteria. A famous example
	 *	is java.lang.Runtime.
	 */

	public boolean detect(final IFirstClassEntity anEntity) {
		// Only Class can be Restricted Creation
		if (anEntity instanceof IClass) {

			final Iterator iterator = anEntity.getIteratorOnConstituents();

			boolean found = false;
			final String className =
				((IFirstClassEntity) anEntity).getDisplayID();

			while (iterator.hasNext()) {
				final Object anOtherEntity = iterator.next();

				// Cannot have a plublic constructor
				if (anOtherEntity instanceof IConstructor) {

					// BIG HACK - WAZZ UP
					// TODO: Remove the hack...
					final IConstructor currentMethod =
						(IConstructor) anOtherEntity;

					if (currentMethod.getDisplayID().startsWith("<init>")) {
						if (currentMethod.isPublic()) {
							return false;
						}
					}
					// Need at least one static field of the same type of the class
				}
				else if ((anOtherEntity instanceof IField)
						&& (((IField) anOtherEntity).isStatic())
						&& (((IField) anOtherEntity).getDisplayTypeName()
							.equals(className))) {
					found = true;
				}
			}

			if (found) {
				this.addEntities(anEntity);
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}
