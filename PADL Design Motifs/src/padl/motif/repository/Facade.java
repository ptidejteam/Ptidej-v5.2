/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIM
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.motif.repository;

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IMethod;
import padl.motif.models.StructuralMotifModel;
import util.multilingual.MultilingualManager;

public class Facade extends StructuralMotifModel implements Cloneable {
	private static final char[] AFACADE_SIDE_OPERATION_ON =
		"aFacadeSideOperationOn".toCharArray();
	private static final char[] AFACADE_SIDE_OPERATION_ON_A_REAL_OPERATION =
		"aFacadeSideOperationOnARealOperation".toCharArray();
	private static final char[] AREAL_OPERATION = "aRealOperation"
		.toCharArray();
	public static final char[] CLIENT = "Client".toCharArray();
	private static final char[] CLIENT_FACADE_ASSOCIATION =
		"clientFacadeAssociation".toCharArray();
	private static final char[] CLIENT_SIDE_OPERATION_ON =
		"clientSideOperationOn".toCharArray();
	public static final char[] FACADE = "Facade".toCharArray();
	private static final char[] FACADE_SUBSYSTEM_CLASS_ASSOCIATION =
		"facadeSubsystemClassAssociation".toCharArray();
	private static final long serialVersionUID = 6609202447252904895L;
	public static final char[] SUBSYSTEM_ENTITY = "SubsystemEntity"
		.toCharArray();

	//	public static void main(final String[] args)
	//			throws CloneNotSupportedException, ModelDeclarationException {
	//
	//		final Facade facade = new Facade();
	//
	//		// I generate the constraints associated with this model.
	//		final PtidejSolverAC4ConstraintGenerator constraintGenerator =
	//			new PtidejSolverAC4ConstraintGenerator();
	//		// I generate this model as a domain for the constraints.
	//		final PtidejSolver2AC4DomainGenerator domainGenerator =
	//			new PtidejSolver2AC4DomainGenerator();
	//
	//		facade.generate(constraintGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(constraintGenerator.getCode());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		facade.walk(domainGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(domainGenerator.getResult());
	//	}

	public Facade() {
		super(Facade.FACADE);

		// Facade class.
		final IClass aFacade =
			this.getFactory().createClass(Facade.FACADE, Facade.FACADE);
		this.addConstituent(aFacade);

		// Subsystem class.
		this.addSubsystemClass(
			Facade.SUBSYSTEM_ENTITY,
			new char[][] { Facade.AREAL_OPERATION });

		// Client class.
		this.addClient(new char[][] { Facade.CLIENT,
				Facade.AFACADE_SIDE_OPERATION_ON_A_REAL_OPERATION });
	}

	public void addClient(final char[][] names) {
		final char[] clientName = names[0];
		final char[][] facadeMethodNames = new char[names.length - 1][];
		System.arraycopy(names, 1, facadeMethodNames, 0, names.length - 1);

		final IClass facadeClass =
			(IClass) this.getConstituentFromID(Facade.FACADE);

		final IClass aClient =
			this.getFactory().createClass(clientName, clientName);
		final IAssociation aClientFacadeAssoc =
			this.getFactory().createAssociationRelationship(
				Facade.CLIENT_FACADE_ASSOCIATION,
				facadeClass,
				1);
		aClient.addConstituent(aClientFacadeAssoc);

		for (int i = 0; i < facadeMethodNames.length; i++) {
			final IDelegatingMethod facadeMethod =
				(IDelegatingMethod) facadeClass
					.getConstituentFromID(facadeMethodNames[i]);
			final IDelegatingMethod clientMethod =
				this.getFactory().createDelegatingMethod(
					ArrayUtils.addAll(
						Facade.CLIENT_SIDE_OPERATION_ON,
						facadeMethodNames[i]),
					aClientFacadeAssoc,
					facadeMethod);
			clientMethod.attachTo(facadeMethod);
			aClient.addConstituent(clientMethod);
		}

		this.addConstituent(aClient);
	}

	public void addSubsystemClass(
		final char[] subsystemClassName,
		final char[][] subsystemClassMethodNames) {

		final IClass facadeClass =
			(IClass) this.getConstituentFromID(Facade.FACADE);

		final IClass aSubsystemClass =
			this.getFactory().createClass(
				subsystemClassName,
				subsystemClassName);

		final IAssociation aFacadeSubsystemClassAssoc =
			this.getFactory().createAssociationRelationship(
				Facade.FACADE_SUBSYSTEM_CLASS_ASSOCIATION,
				aSubsystemClass,
				1);
		facadeClass.addConstituent(aFacadeSubsystemClassAssoc);

		for (int i = 0; i < subsystemClassMethodNames.length; i++) {
			final IMethod subsystemClassMethod =
				this.getFactory().createMethod(
					subsystemClassMethodNames[i],
					subsystemClassMethodNames[i]);
			aSubsystemClass.addConstituent(subsystemClassMethod);

			final IDelegatingMethod facadeMethod =
				this
					.getFactory()
					.createDelegatingMethod(
						(String.valueOf(Facade.AFACADE_SIDE_OPERATION_ON)
								+ String
									.valueOf(subsystemClassMethodNames[i])
									.substring(0, 1)
									.toUpperCase() + String.valueOf(
							subsystemClassMethodNames[i]).substring(1)).toCharArray(),
						aFacadeSubsystemClassAssoc,
						subsystemClassMethod);
			facadeMethod.attachTo(subsystemClassMethod);
			facadeClass.addConstituent(facadeMethod);
		}

		this.addConstituent(aSubsystemClass);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Facade.class);
	}

	public char[] getName() {
		return Facade.FACADE;
	}

	public void removeClient(final char[][] names) {
		final char[] clientName = names[0];
		this.removeConstituentFromID(clientName);
	}

	public void removeSubsystemClass(final char[] subsystemClassName) {
		this.removeConstituentFromID(subsystemClassName);
	}
}
