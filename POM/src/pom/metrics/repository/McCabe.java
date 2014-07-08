/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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
package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IConstituent;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPrimitiveEntity;
import padl.statement.kernel.IIfInstruction;
import padl.statement.kernel.IStatementWalker;
import padl.statement.kernel.ISwitchInstruction;
import padl.statement.kernel.impl.StatementWalkerAdapter;
import pom.metrics.IDependencyIndependentMetric;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;
import util.io.ProxyConsole;

public class McCabe extends AbstractMetric implements IMetric, IUnaryMetric, IDependencyIndependentMetric {
	// Must be public because of reflection use to traverse the entity below...
	public class McCabeComputer extends StatementWalkerAdapter {
		private int mcCabe;

		public String getName() {
			return "McCabe Computer";
		}
		public Object getResult() {
			return new Integer(this.mcCabe);
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

		public void visit(final IIfInstruction anIfInstruction) {
			this.mcCabe++;
		}

		public void visit(final IPrimitiveEntity aPrimitiveEntity) {
			// Do nothing for uninteresting primitive types.
		}
		public void visit(final ISwitchInstruction switchInstruction) {
			this.mcCabe += switchInstruction.getNumberOfCases();
		}
	}
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		final IStatementWalker walker = new McCabeComputer();
		firstClassEntity.accept(walker);
		return ((Integer) walker.getResult()).doubleValue();
	}
	public String getDefinition() {
		return "McCabe Complexity: Number of points of decision + 1";
	}
}
