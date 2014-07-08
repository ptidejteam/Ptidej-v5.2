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

public class TestWMC extends TestCase {
	private static MetricsRepository metrics;
	private static ICodeLevelModel model;
	private static final String root =
		"../POM Tests/data/Metric Specific for Java/bin/pom/test/rsc/specific/testWMC/";

	public TestWMC(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		if (TestWMC.model == null) {
			TestWMC.model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			TestWMC.model.create(new CompleteClassFileCreator(
				new String[] { root }));
			TestWMC.metrics = MetricsRepository.getInstance();
		}
	}
	public void testRefClass() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testWMC.TestSingleClass");

		Assert.assertEquals(2.0d, ((IUnaryMetric) metrics.getMetric("WMC"))
			.compute(model, firstClassEntity), 0d);
	}
	public void testRefInterface() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testWMC.TestSingleInterface");

		Assert.assertEquals(0d, ((IUnaryMetric) metrics.getMetric("WMC"))
			.compute(model, firstClassEntity), 0d);
	}
	/**
	 * To Check with Yann
	 */
	public void test01() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testWMC.TestAClass01");

		Assert.assertEquals(8.0d, ((IUnaryMetric) metrics.getMetric("WMC"))
			.compute(model, firstClassEntity), 0d);
	}
	/**
	 * An empty method has a complexity of 1 !
	 */
	public void test02() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testWMC.TestAClass02");

		Assert.assertEquals(6.0d, ((IUnaryMetric) metrics.getMetric("WMC"))
			.compute(model, firstClassEntity), 0d);
	}
}
