/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
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
import padl.event.IModelListener;
import padl.kernel.Constants;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.motif.models.StructuralMotifModel;
import util.multilingual.MultilingualManager;

public class Visitor extends StructuralMotifModel implements Cloneable {
	private static final char[] ACCEPT = "accept".toCharArray();
	private static final char[] ACCEPT_VISITOR = "acceptVisitor".toCharArray();
	private static final char[] ENTITY = "entity".toCharArray();
	private static final char[] NODE = "Node".toCharArray();
	private static final char[] NODE_HIERARCHY_ROOT = "NodeHierarchyRoot"
		.toCharArray();
	private static final long serialVersionUID = 5399517484944809460L;
	private static final char[] STRING = "visitor".toCharArray();
	private static final char[] VISIT = "visit".toCharArray();
	private static final char[] VISIT_ENTITY = "visitEntity".toCharArray();
	private static final char[] VISITOR = "Visitor".toCharArray();
	private static final char[] VISITOR_HIERARCHY_ROOT = "VisitorHierarchyRoot"
		.toCharArray();

	//	public static void main(final String[] args)
	//			throws CloneNotSupportedException, ModelDeclarationException {
	//
	//		final IModelListener patternListener = new ModelStatistics();
	//		final Visitor visitor = new Visitor(patternListener);
	//
	//		// I generate the Java source code associated with this pattern.
	//		final JavaGenerator javaGenerator = new JavaGenerator();
	//		// I generate the constraints associated with this pattern.
	//		final PtidejSolverAC4ConstraintGenerator ac4ConstraintGenerator =
	//			new PtidejSolverAC4ConstraintGenerator();
	//		// I generate the constraints associated with this pattern.
	//		final PtidejSolverCustomConstraintGenerator constraintGenerator =
	//			new PtidejSolverCustomConstraintGenerator();
	//		// I generate this pattern as a domain for the constraints.
	//		final PtidejSolver2AC4DomainGenerator domainGenerator =
	//			new PtidejSolver2AC4DomainGenerator();
	//
	//		ProxyConsole.getInstance().normalOutput().println(visitor);
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		visitor.generate(javaGenerator);
	//		ProxyConsole.getInstance().normalOutput().println(javaGenerator.getCode());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		visitor.generate(ac4ConstraintGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(ac4ConstraintGenerator.getCode());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		visitor.generate(constraintGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(constraintGenerator.getCode());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//		visitor.walk(domainGenerator);
	//		ProxyConsole
	//			.getInstance()
	//			.normalOutput()
	//			.println(domainGenerator.getResult());
	//		ProxyConsole.getInstance().normalOutput().println("----");
	//	}

	public Visitor() {
		this(null);
	}
	public Visitor(final IModelListener patternListener) {
		super(Visitor.VISITOR);

		this.addModelListener(patternListener);

		final IInterface nodeHierarchyRootInterface =
			this.getFactory().createInterface(
				Visitor.NODE_HIERARCHY_ROOT,
				Visitor.NODE_HIERARCHY_ROOT);
		this.addConstituent(nodeHierarchyRootInterface);

		final IInterface visitorRootInterface =
			this.getFactory().createInterface(
				Visitor.VISITOR_HIERARCHY_ROOT,
				Visitor.VISITOR_HIERARCHY_ROOT);
		this.addConstituent(visitorRootInterface);

		final IMethod acceptMethod =
			this.getFactory().createMethod(Visitor.ACCEPT, Visitor.ACCEPT);
		acceptMethod.addConstituent(this.getFactory().createParameter(
			visitorRootInterface,
			Visitor.STRING,
			Constants.CARDINALITY_ONE));
		nodeHierarchyRootInterface.addConstituent(acceptMethod);

		final IMethod visitMethod =
			this.getFactory().createMethod(Visitor.VISIT, Visitor.VISIT);
		visitMethod.addConstituent(this.getFactory().createParameter(
			nodeHierarchyRootInterface,
			Visitor.ENTITY,
			Constants.CARDINALITY_ONE));
		visitorRootInterface.addConstituent(visitMethod);

		final IAssociation acceptVisitorAssociation =
			this.getFactory().createAssociationRelationship(
				Visitor.ACCEPT_VISITOR,
				visitorRootInterface,
				1);
		// acceptVisitorAssociation.attachTo(acceptMethod);
		nodeHierarchyRootInterface.addConstituent(acceptVisitorAssociation);

		final IAssociation visitEntityAssociation =
			this.getFactory().createAssociationRelationship(
				Visitor.VISIT_ENTITY,
				nodeHierarchyRootInterface,
				1);
		// visitEntityAssociation.attachTo(visitMethod);
		visitorRootInterface.addConstituent(visitEntityAssociation);

		this.addNode(Visitor.NODE);
	}
	public void addNode(final char[] nodeName) {
		final IClass node = this.getFactory().createClass(nodeName, nodeName);
		node.addInheritedEntity((IFirstClassEntity) this
			.getConstituentFromName(Visitor.NODE_HIERARCHY_ROOT));
		this.addConstituent(node);

		final IClass visitor =
			this.getFactory().createClass(
				ArrayUtils.addAll(nodeName, Visitor.VISITOR),
				ArrayUtils.addAll(nodeName, Visitor.VISITOR));
		visitor.addInheritedEntity((IFirstClassEntity) this
			.getConstituentFromName(Visitor.VISITOR_HIERARCHY_ROOT));
		this.addConstituent(visitor);

		final IMethod acceptMethod =
			this.getFactory().createMethod(Visitor.ACCEPT, Visitor.ACCEPT);
		acceptMethod.addConstituent(this.getFactory().createParameter(
			visitor,
			Visitor.STRING,
			Constants.CARDINALITY_ONE));
		node.addConstituent(acceptMethod);

		final IMethod visitMethod =
			this.getFactory().createMethod(Visitor.VISIT, Visitor.VISIT);
		visitMethod.addConstituent(this.getFactory().createParameter(
			node,
			Visitor.ENTITY,
			Constants.CARDINALITY_ONE));
		visitor.addConstituent(visitMethod);

		final IAssociation acceptVisitorAssociation =
			this.getFactory().createAssociationRelationship(
				Visitor.ACCEPT_VISITOR,
				visitor,
				1);
		// acceptVisitorAssociation.attachTo(acceptMethod);
		node.addConstituent(acceptVisitorAssociation);

		final IAssociation visitEntityAssociation =
			this.getFactory().createAssociationRelationship(
				Visitor.VISIT_ENTITY,
				node,
				1);
		// visitEntityAssociation.attachTo(visitMethod);
		visitor.addConstituent(visitEntityAssociation);
	}
	public String getIntent() {
		return MultilingualManager.getString("INTENT", Visitor.class);
	}
	public char[] getName() {
		return Visitor.VISITOR;
	}
}
