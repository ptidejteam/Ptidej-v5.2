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
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.IContainerAggregation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

public class Observer extends BehaviouralMotifModel implements
		PropertyChangeListener, Cloneable {

	private static final char[] CONCRETE_OBSERVER = "ConcreteObserver"
		.toCharArray();
	private static final char[] CONCRETE_SUBJECT = "ConcreteSubject"
		.toCharArray();
	private static final char[] GET_STATE = "getState".toCharArray();
	private static final char[] NOTIFY = "Notify".toCharArray();
	private static final char[] OBSERVER = "Observer".toCharArray();
	private static final char[] OBSERVERS = "observers".toCharArray();
	private static final long serialVersionUID = 8984280250655412814L;
	private static final char[] STRING = "subject".toCharArray();
	private static final char[] SUBJECT = "Subject".toCharArray();
	private static final char[] UPDATE = "Update".toCharArray();

	//	public static void main(final String[] args)
	//			throws CloneNotSupportedException, ModelDeclarationException {
	//		final Observer observer = new Observer();
	//
	//		// I generate the constraints associated with this padl.pattern.
	//		final PtidejSolverAC4ConstraintGenerator constraintGenerator =
	//			new PtidejSolverAC4ConstraintGenerator();
	//		// I generate this padl.pattern as a domain for the constraints.
	//		final PtidejSolver2AC4DomainGenerator domainGenerator =
	//			new PtidejSolver2AC4DomainGenerator();
	//
	//		observer.generate(constraintGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(constraintGenerator.getCode());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		observer.walk(domainGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(domainGenerator.getResult());
	//	}

	private final IMethod abMethod, acMethod;
	private final IClass aClass;
	private IClass a2Class;
	private final IInterface anInterface;

	private IDelegatingMethod aPDelegatingMethod;
	public Observer() {
		super(Observer.OBSERVER);

		// Interface Observer 
		this.anInterface =
			this.getFactory().createInterface(
				Observer.OBSERVER,
				Observer.OBSERVER);
		this.abMethod =
			this.getFactory().createMethod(Observer.UPDATE, Observer.UPDATE);
		this.anInterface.addConstituent(this.abMethod);
		this.anInterface.setPurpose(MultilingualManager.getString(
			"Observer_PURPOSE",
			Observer.class));
		this.addConstituent(this.anInterface);

		// Association observers 
		final IContainerAggregation anAssoc =
			this.getFactory().createContainerAggregationRelationship(
				Observer.OBSERVERS,
				this.anInterface,
				Constants.CARDINALITY_MANY);

		// Classe Subject 
		this.aClass =
			this.getFactory().createClass(Observer.SUBJECT, Observer.SUBJECT);
		this.aClass.setAbstract(true);
		this.aClass.addConstituent(anAssoc);
		this.aPDelegatingMethod =
			this.getFactory().createDelegatingMethod(
				Observer.NOTIFY,
				anAssoc,
				this.abMethod);
		this.aClass.addConstituent(this.aPDelegatingMethod);
		this.aClass.assumeAllInterfaces();
		this.aClass.setPurpose(MultilingualManager.getString(
			"Subject_PURPOSE",
			Observer.class));
		this.addConstituent(this.aClass);

		// Classe Concrete Subject
		this.acMethod =
			this.getFactory().createMethod(
				Observer.GET_STATE,
				Observer.GET_STATE);
		this.a2Class =
			this.getFactory().createClass(
				Observer.CONCRETE_SUBJECT,
				Observer.CONCRETE_SUBJECT);
		this.a2Class.addInheritedEntity(this.aClass);
		this.a2Class.setPurpose(MultilingualManager.getString(
			"ConcreteSubject_CLASS_PURPOSE",
			Observer.class));
		this.a2Class.addConstituent(this.acMethod);
		this.a2Class.assumeAllInterfaces();
		this.addConstituent(this.a2Class);

		// Assoc subject
		final IContainerAggregation a2Assoc =
			this.getFactory().createContainerAggregationRelationship(
				Observer.STRING,
				this.a2Class,
				Constants.CARDINALITY_ONE);

		// Classe Concrete Observer
		this.aPDelegatingMethod =
			this.getFactory().createDelegatingMethod(
				Observer.UPDATE,
				a2Assoc,
				this.acMethod);
		this.aPDelegatingMethod.setComment(MultilingualManager.getString(
			"DELEG_METHOD_COMMENT",
			Observer.class));
		this.aPDelegatingMethod.attachTo(this.abMethod);
		this.a2Class =
			this.getFactory().createClass(
				Observer.CONCRETE_OBSERVER,
				Observer.CONCRETE_OBSERVER);
		this.a2Class.setPurpose(MultilingualManager.getString(
			"ConcreteObserver_CLASS_PURPOSE",
			Observer.class));
		this.a2Class.addImplementedInterface(this.anInterface);
		this.a2Class.addConstituent(a2Assoc);
		this.a2Class.addConstituent(this.aPDelegatingMethod);
		//	this.a2Class.assumeAllInterfaces();
		this.addConstituent(this.a2Class);
	}
	private void codeForSetMethod() {
		// Add code for the setMethod.
		if (this.getConstituentFromName(Observer.CONCRETE_OBSERVER) == null
				|| this.getConstituentFromName(Observer.SUBJECT) == null) {
			return;
		}

		final IContainerAggregation a2Assoc =
			(IContainerAggregation) ((IFirstClassEntity) this
				.getConstituentFromName(Observer.CONCRETE_OBSERVER))
				.getConstituentFromName(Observer.STRING);
		final IContainerAggregation anAssoc =
			(IContainerAggregation) ((IFirstClassEntity) this
				.getConstituentFromName(Observer.SUBJECT))
				.getConstituentFromName(Observer.OBSERVERS);

		final IMethod sMethod =
			(IMethod) a2Assoc.getConstituentFromID(IContainerAggregation.ID3);
		final String[] codeLines =
			new String[] {
					"if ("
							+ a2Assoc.getConstituentFromID(
								IContainerAggregation.ID1).getDisplayName()
							+ " != null)",
					"     "
							+ a2Assoc.getConstituentFromID(
								IContainerAggregation.ID1).getDisplayName()
							+ '.'
							+ anAssoc.getConstituentFromID(
								IContainerAggregation.ID3).getDisplayName()
							+ "(this);",
					a2Assoc
						.getConstituentFromID(IContainerAggregation.ID1)
						.getDisplayName()
							+ " = a"
							+ a2Assoc.getTargetEntity().getDisplayName() + ';',
					a2Assoc
						.getConstituentFromID(IContainerAggregation.ID1)
						.getDisplayName()
							+ '.'
							+ anAssoc.getConstituentFromID(
								IContainerAggregation.ID2).getDisplayName()
							+ "(this);" };
		sMethod.setCodeLines(codeLines);
	}
	public String getIntent() {
		return MultilingualManager.getString("INTENT", Observer.class);
	}
	public char[] getName() {
		return Observer.OBSERVER;
	}
	public void propertyChange(final PropertyChangeEvent e) {
		this.codeForSetMethod();
	}
}
