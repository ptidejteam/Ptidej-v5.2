package pom.test.classfile.general;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Factory;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

/**
 * @author Farouk Zaidi
 */
public class TestUnaryMetrics extends TestCase {
	private static String actorID = "java.lang.LinkageError";
	private static IFirstClassEntity firstClassEntity;
	private static MetricsRepository metrics;
	private static ICodeLevelModel model = null;
	private static final String root = "../POM Tests/data/java.lang.jar";

	public TestUnaryMetrics(final String aName) {
		super(aName);
	}

	protected void setUp() throws Exception {
		super.setUp();
		if (model == null) {
			model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			model.create(new CompleteClassFileCreator(new String[] { root }));
			metrics = MetricsRepository.getInstance();
			firstClassEntity =
				(IFirstClassEntity) model.getTopLevelEntityFromID(actorID);
		}
	}

	public void testAID() {
		Assert.assertEquals("AID", 3.0d, ((IUnaryMetric) metrics
			.getMetric("AID")).compute(model, firstClassEntity), 0);
	}
	public void testCLD() {
		Assert.assertEquals(
			"CLD",
			2d,
			((IUnaryMetric) metrics.getMetric("CLD")).compute(
				model,
				firstClassEntity),
			0);
	}
	/*
		public void testDIT() {
			Assert.assertEquals("DIT", 3d, metrics.compute("DIT", firstClassEntity), 0);
		}*/

	public void testNMA() {
		Assert.assertEquals(
			"NMA",
			2d,
			((IUnaryMetric) metrics.getMetric("NMA")).compute(
				model,
				firstClassEntity),
			0);
	}
	/*
		public void testNMI() {
			System.out.println(">>>>   " + firstClassEntity.getDisplayName());
			TestCase
				.assertEquals("NMI", 18d, metrics.compute("NMI", firstClassEntity), 0);
		}
	*/
	public void testNMO() {
		Assert.assertEquals(
			"NMO",
			0d,
			((IUnaryMetric) metrics.getMetric("NMO")).compute(
				model,
				firstClassEntity),
			0);
	}
	public void testSIX() {
		Assert.assertEquals("SIX", 0.0d, ((IUnaryMetric) metrics
			.getMetric("SIX")).compute(model, firstClassEntity), 0d);
	}
}
