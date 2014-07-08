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
package pom.test.classfile.specific;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Factory;
import pom.metrics.IBinaryMetric;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

public class TestCBO extends TestCase {
	private static ICodeLevelModel Model;
	private static MetricsRepository MetricsRepository;

	public TestCBO(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();

		if (Model == null) {
			Model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			Model
				.create(new CompleteClassFileCreator(
					new String[] { "../POM Tests/data/Metric Specific for Java/bin/pom/test/rsc/specific/testCBO/" }));
			MetricsRepository = pom.metrics.MetricsRepository.getInstance();
		}
	}
	public void testRefClass() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestCBO.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleClass");

		Assert.assertEquals("", 0d, ((IUnaryMetric) MetricsRepository
			.getMetric("CBO")).compute(TestCBO.Model, firstClassEntity), 0d);
	}
	public void testRefInterface() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleInterface");

		Assert.assertEquals("", 0d, ((IUnaryMetric) MetricsRepository
			.getMetric("CBO")).compute(TestCBO.Model, firstClassEntity), 0d);
	}
	public void testAboutGetVar() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight");

		Assert.assertEquals(0d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBO")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutSetVar() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight");

		Assert.assertEquals(7d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBO")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutSetVarIn() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight");

		Assert.assertEquals(3d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBOin")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutSetVarOut() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight");

		Assert.assertEquals(4d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBOout")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutFunct() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight");

		Assert.assertEquals(7d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBO")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutFunctIn() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight");

		Assert.assertEquals(3d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBOin")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
	public void testAboutFunctOut() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft");
		final IFirstClassEntity firstClassEntity2 =
			(IFirstClassEntity) Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight");

		Assert.assertEquals(4d, ((IBinaryMetric) MetricsRepository
			.getMetric("CBOout")).compute(
			TestCBO.Model,
			firstClassEntity,
			firstClassEntity2), 0d);
	}
}
