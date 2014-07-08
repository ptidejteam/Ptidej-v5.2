package padl.micropattern.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import padl.kernel.IClass;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethodInvocation;
import padl.kernel.IOperation;
import padl.micropattern.IMicroPatternDetection;

public final class ImmutableDetection extends AbstractMicroPatternDetection
		implements IMicroPatternDetection {

	public String getName() {
		return "ImmutableDetection";
	}

	private List modifiedField = new ArrayList();
	private List modifiedinConstructor = new ArrayList();

	/*
	 *  10. Immutable. An immutable class is class whose instance ям?elds
	 *	are only changed by its constructors.
	 *	The Canopy is an immutable class which has exactly one instance
	 *	ям?eld. Its description is placed under its other category, Wrappers
	 *	(Sec. 3.2.1). More general is the Immutable micro pattern, which
	 *	stands for immutable classes which have at least two instance ям?elds.
	 *	Class java.util.jar.Manifest is an Immutable class since
	 *	assignment to its two ям?elds takes place only in constructors code.
	 */

	public boolean detect(final IFirstClassEntity anEntity) {
		int NbField = 0;

		// Only Class can be Restricted Immutable
		if (anEntity instanceof IClass) {

			// Reset immutable field
			this.modifiedField.clear();
			this.modifiedinConstructor.clear();

			this.buildMethodModifiedFields(anEntity);

			final Iterator iterator = anEntity.getIteratorOnConstituents();
			while (iterator.hasNext()) {
				final Object anOtherEntity = iterator.next();

				if (anOtherEntity instanceof IField) {
					if (!((IField) anOtherEntity).isStatic()) {
						NbField++;
						if (this.modifiedField.contains(anOtherEntity)) {
							return false;
						}
						else if (!this.modifiedinConstructor
							.contains(anOtherEntity)) {
							return false;
						}
					}
				}
			}

			if (NbField >= 1) {
				this.addEntities(anEntity);
				return true;
			}
		}
		return false;
	}

	public void buildMethodModifiedFields(final IFirstClassEntity aClass) {
		this.modifiedField.clear();

		// Build list of modified field
		final Iterator iter = aClass.getIteratorOnConstituents();
		while (iter.hasNext()) {
			final Object constituent = iter.next();
			if (constituent instanceof IOperation) {
				// A class can contain only Constructor declaration
				//if (!((IAbstractMethod)constituent).getID().startsWith("<init>"))
				this.scanMethod((IOperation) constituent);
			}
		}
	}

	private void scanMethod(final IOperation aMethod) {
		// Check if the field is assign in this method
		final Iterator iterator = aMethod.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final Object entity = iterator.next();

			// Seach for MethodInvocation
			if (entity instanceof IMethodInvocation) {
				final IMethodInvocation aMethodInvocation =
					(IMethodInvocation) entity;
				final IOperation aCalledMethod =
					aMethodInvocation.getCalledMethod();
				if (aCalledMethod != null) {

					// Seach for variable assignation
					if (aCalledMethod.getDisplayName().equals("=")) {
						if (aMethod.getDisplayID().startsWith("<init>")) {
							this.modifiedField.add(aMethodInvocation
								.getFirstCallingField());
						}
						else {
							this.modifiedinConstructor.add(aMethodInvocation
								.getFirstCallingField());
						}
					}
				}
			}
		}
	}
}
