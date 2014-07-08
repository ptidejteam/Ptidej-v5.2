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
package padl.statement.creator.aol.helper;

import java.util.Map;
import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.statement.creator.aol.IMetricValueAdder;
import util.io.ProxyConsole;

public class LOCAdder implements IMetricValueAdder {
	// Yann 2008/10/13: Members...
	// No need to take care of members classes/interfaces
	// using a Stack because we are in AOL...
	private IFirstClassEntity currentEntity;
	private Map qualifiedMethodsMetrics;

	public void close(final IAbstractModel anAbstractModel) {
	}
	public void close(final IClass aClass) {
	}
	public void close(final IConstructor constructor) {
	}
	public void close(final IDelegatingMethod delegatingMethod) {
	}
	public void close(final IGetter getter) {
	}
	public void close(final IGhost ghost) {
	}
	public void close(final IInterface anInterface) {
	}
	public void close(final IMemberClass memberClass) {
	}
	public void close(final IMemberGhost memberGhost) {
	}
	public void close(final IMemberInterface memberInterface) {
	}
	public void close(final IMethod method) {
	}
	public void close(final IPackage aPackage) {
	}
	public void close(final IPackageDefault aPackage) {
	}
	public void close(final ISetter setter) {
	}
	public String getName() {
		return "If/Switch Instructions Creator for AOL";
	}
	public Object getResult() {
		return null;
	}
	public void open(final IAbstractModel anAbstractModel) {
	}
	public void open(final IClass aClass) {
		this.currentEntity = aClass;
	}
	public void open(final IConstructor constructor) {
	}
	public void open(final IDelegatingMethod delegatingMethod) {
	}
	public void open(final IGetter getter) {
	}
	public void open(final IGhost ghost) {
	}
	public void open(final IInterface anInterface) {
	}
	public void open(final IMemberClass memberClass) {
	}
	public void open(final IMemberGhost memberGhost) {
	}
	public void open(final IMemberInterface memberInterface) {
	}
	public void open(final IMethod method) {
		final String qualifiedName =
			this.currentEntity.getDisplayName() + '.' + method.getDisplayName();
		final String metricValue =
			(String) this.qualifiedMethodsMetrics.get(qualifiedName);

		if (metricValue != null) {
			final int value = Integer.parseInt(metricValue);
			method.setCodeLines(new String[value]);
		}
		else {
			ProxyConsole
				.getInstance()
				.errorOutput()
				.print("Cannot find LOC for ");
			ProxyConsole.getInstance().errorOutput().println(qualifiedName);
			method.setCodeLines(new String[0]);
		}
	}
	public void open(final IPackage aPackage) {
	}
	public void open(final IPackageDefault aPackage) {
	}
	public void open(final ISetter setter) {
	}
	public void reset() {
	}
	public void setQualifiedMethodsMetrics(final Map someQualifiedMethodsMetrics) {
		this.qualifiedMethodsMetrics = someQualifiedMethodsMetrics;
	}
	public final void unknownConstituentHandler(
		final String aCalledMethodName,
		final IConstituent aConstituent) {

		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(this.getClass().getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(" does not know what to do for \"");
		ProxyConsole.getInstance().debugOutput().print(aCalledMethodName);
		ProxyConsole.getInstance().debugOutput().print("\" (");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(aConstituent.getDisplayID());
		ProxyConsole.getInstance().debugOutput().println(')');
	}
	public void visit(final IAggregation anAggregation) {
	}
	public void visit(final IAssociation anAssociation) {
	}
	public void visit(final IComposition composition) {
	}
	public void visit(final IContainerAggregation containerAggregation) {
	}
	public void visit(final IContainerComposition containerComposition) {
	}
	public void visit(final ICreation creation) {
	}
	public void visit(final IField field) {
	}
	public void visit(final IMethodInvocation methodInvocation) {
	}
	public void visit(final IParameter parameter) {
	}
	public void visit(final IPrimitiveEntity aPrimitiveEntity) {
		// Do nothing for uninteresting primitive types.
	}
	public void visit(final IUseRelationship use) {
	}
}
