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
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

public class TestDIT extends TestCase {
	private static MetricsRepository MetricsRepository;
	private static ICodeLevelModel Model = null;
	private static final String root =
		"../POM Tests/data/Metric Specific for Java/bin/pom/test/rsc/specific/testDIT/";
	public TestDIT(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		if (TestDIT.Model == null) {
			TestDIT.Model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			TestDIT.Model.create(new CompleteClassFileCreator(
				new String[] { root }));

			TestDIT.MetricsRepository =
				pom.metrics.MetricsRepository.getInstance();
		}
	}
	public void testRefClass() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestDIT.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testDIT.TestSingleClass");

		Assert.assertEquals(1d, ((IUnaryMetric) MetricsRepository
			.getMetric("DIT")).compute(TestDIT.Model, firstClassEntity), 0d);
	}
	public void testRefInterface() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestDIT.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testDIT.TestSingleInterface");

		Assert.assertEquals(1.0d, ((IUnaryMetric) MetricsRepository
			.getMetric("DIT")).compute(TestDIT.Model, firstClassEntity), 0d);
	}
	public void testInheritanceOnClass() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestDIT.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testDIT.TestAChild03");

		Assert.assertEquals(4d, ((IUnaryMetric) MetricsRepository
			.getMetric("DIT")).compute(TestDIT.Model, firstClassEntity), 0d);
	}
	public void testInheritanceOnInterface01() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestDIT.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testDIT.TestBChild03");

		Assert.assertEquals(4d, ((IUnaryMetric) MetricsRepository
			.getMetric("DIT")).compute(TestDIT.Model, firstClassEntity), 0d);
	}
	public void testInheritanceOnInterface02() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestDIT.Model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testDIT.TestBChild11");

		Assert.assertEquals(2d, ((IUnaryMetric) MetricsRepository
			.getMetric("DIT")).compute(TestDIT.Model, firstClassEntity), 0d);
	}
}
