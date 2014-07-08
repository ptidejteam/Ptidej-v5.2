/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.test.listeners;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.kernel.Constants;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IObservable;
import padl.kernel.IPackage;
import padl.kernel.IParameter;
import padl.kernel.impl.Factory;
import padl.kernel.impl.FirstClassEntity;
import padl.test.helper.Reflector;
import padl.util.ModelStatistics;
import util.io.ProxyConsole;

public class TestListeners extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public TestListeners(final String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		if (TestListeners.IdiomLevelModel == null) {
			try {
				final char[] entityZName = "Z".toCharArray();
				final IFirstClassEntity anZEntity =
					Factory.getInstance().createClass(entityZName, entityZName);

				final IMethod aMethod =
					Factory.getInstance().createMethod(
						"foo".toCharArray(),
						"foo".toCharArray());
				aMethod.setReturnType(entityZName);
				anZEntity.addConstituent(aMethod);

				final char[] entityAName = "A".toCharArray();
				final IFirstClassEntity anAEntity =
					Factory.getInstance().createClass(entityAName, entityAName);

				final IMethod aGetter =
					Factory.getInstance().createMethod(
						"get".toCharArray(),
						"get".toCharArray());
				aGetter.setReturnType(entityAName);

				final IParameter aParameter =
					Factory.getInstance().createParameter(
						anAEntity,
						"a".toCharArray(),
						Constants.CARDINALITY_ONE);
				final IMethod aSetter =
					Factory.getInstance().createMethod(
						"set".toCharArray(),
						"set".toCharArray());
				aSetter.addConstituent(aParameter);

				anAEntity.addConstituent(aGetter);
				anAEntity.addConstituent(aSetter);

				final IPackage aPackage =
					Factory.getInstance().createPackage("p".toCharArray());
				aPackage.addConstituent(anZEntity);
				aPackage.addConstituent(anAEntity);

				final ICodeLevelModel aCodeLevelModel =
					Factory.getInstance().createCodeLevelModel("Model");
				aCodeLevelModel.addConstituent(aPackage);

				TestListeners.IdiomLevelModel =
					(IIdiomLevelModel) new AACRelationshipsAnalysis()
						.invoke(aCodeLevelModel);
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testAddListenerModel() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		TestListeners.IdiomLevelModel.addModelListener(new ModelStatistics());

		final IFirstClassEntity anA =
			TestListeners.IdiomLevelModel.getTopLevelEntityFromID("A"
				.toCharArray());
		final IObservable observable =
			(IObservable) Reflector.getObjectFromReflectedField(
				FirstClassEntity.class,
				"container",
				anA);
		final Iterator iterator = observable.getIteratorOnModelListeners();
		int count = 0;
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		Assert.assertEquals(2, count);
	}
	public void testAddListenerEntity() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		final IFirstClassEntity anZ =
			TestListeners.IdiomLevelModel.getTopLevelEntityFromID("Z"
				.toCharArray());

		anZ.addModelListener(new ModelStatistics());

		final IObservable observable =
			(IObservable) Reflector.getObjectFromReflectedField(
				FirstClassEntity.class,
				"container",
				anZ);
		final Iterator iterator = observable.getIteratorOnModelListeners();
		int count = 0;
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		Assert.assertEquals(3, count);
	}
}
