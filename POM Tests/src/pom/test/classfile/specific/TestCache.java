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
import padl.kernel.impl.Factory;
import pom.metrics.IBinaryMetric;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import pom.util.CacheManager;

public class TestCache extends TestCase {
	private static ICodeLevelModel Model;
	private static MetricsRepository MetricsRepository;
	private static IBinaryMetric CBOMetric;
	private static IBinaryMetric CBOinMetric;
	private static IBinaryMetric CBOoutMetric;

	public TestCache(final String aName) {
		super(aName);
	}
	protected void setUp() throws Exception {
		super.setUp();

		if (TestCache.Model == null) {
			TestCache.Model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			TestCache.Model
				.create(new CompleteClassFileCreator(
					new String[] { "../POM Tests/data/Metric Specific for Java/bin/pom/test/rsc/specific/testCBO/" }));
			TestCache.MetricsRepository =
				pom.metrics.MetricsRepository.getInstance();

			TestCache.CBOMetric =
				(IBinaryMetric) TestCache.MetricsRepository.getMetric("CBO");
			TestCache.CBOinMetric =
				(IBinaryMetric) TestCache.MetricsRepository.getMetric("CBOin");
			TestCache.CBOoutMetric =
				(IBinaryMetric) TestCache.MetricsRepository.getMetric("CBOout");

			// Pre-compute some metric values.
			((IUnaryMetric) TestCache.CBOMetric)
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleClass"));

			((IUnaryMetric) TestCache.CBOMetric)
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleInterface"));

			TestCache.CBOMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight"));

			TestCache.CBOMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft"));

			TestCache.CBOinMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"));

			TestCache.CBOoutMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"));
		}
	}
	public void testCachingOfCBOValues() {
		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleClass")));

		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleInterface")));

		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight")));

		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteRight")));
	}
	public void testCachingOfCBOinValues() {
		Assert
			.assertTrue(
				"Value should be in cache",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleClass")));

		Assert
			.assertTrue(
				"Value should be in cache",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleInterface")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight")));
		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft")));

		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight")));
		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft")));

		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight")));
		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOinMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteRight")));
	}
	public void testCachingOfCBOoutValues() {
		Assert
			.assertTrue(
				"Value should be in cache",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleClass")));

		Assert
			.assertTrue(
				"Value should be in cache",
				CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestSingleInterface")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight")));
		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft")));

		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight")));
		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft")));

		Assert
			.assertTrue(
				"Is value in cache?",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight")));
		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isUnaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft")));

		Assert
			.assertTrue(
				"Value should NOT be in cache",
				!CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOoutMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteLeft"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestDPlaneteRight")));
	}
	public void testCachingOfReversedCBOValues() {
		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestAPlaneteLeft")));

		Assert
			.assertTrue(
				"Is value in cache?",
				CacheManager
					.getInstance()
					.isBinaryMetricValueInCache(
						TestCache.CBOMetric,
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteRight"),
						TestCache.Model
							.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestBPlaneteLeft")));
	}
	public void testCBOinAndOut() {
		final double leftRightCBO =
			TestCache.CBOinMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"))
					+ TestCache.CBOoutMetric
						.compute(
							TestCache.Model,
							TestCache.Model
								.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"),
							TestCache.Model
								.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"));
		final double rightLeftCBO =
			TestCache.CBOinMetric
				.compute(
					TestCache.Model,
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"),
					TestCache.Model
						.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"))
					+ TestCache.CBOoutMetric
						.compute(
							TestCache.Model,
							TestCache.Model
								.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteRight"),
							TestCache.Model
								.getTopLevelEntityFromID("pom.test.rsc.specific.testCBO.TestCPlaneteLeft"));
		Assert.assertEquals(leftRightCBO, rightLeftCBO, 0d);
	}
}
