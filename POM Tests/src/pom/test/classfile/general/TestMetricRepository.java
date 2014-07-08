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
package pom.test.classfile.general;

import java.io.StringWriter;
import java.io.Writer;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.impl.Factory;
import pom.metrics.MetricsRepository;
import pom.metrics.repository.USELESS;
import util.io.ProxyConsole;

/**
 * @author Foutse Khomh
 *  since   2009-03-04
 *  
 */
public class TestMetricRepository extends TestCase {
	public TestMetricRepository(String aName) {
		super(aName);
	}

	private static ICodeLevelModel Model = null;
	private static final String Root = "../POM Tests/data/java.lang.jar";
	private static MetricsRepository metrics;

	protected void setUp() throws Exception {
		super.setUp();
		if (TestMetricRepository.Model == null) {
			TestMetricRepository.Model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			TestMetricRepository.Model.create(new CompleteClassFileCreator(
				new String[] { TestMetricRepository.Root }));
			TestMetricRepository.metrics = MetricsRepository.getInstance();
		}
	}
	public void testUnarymetricList() {
		Assert.assertNotNull(TestMetricRepository.metrics.getUnaryMetrics());
	}
	public void testRuntimeDeprecation() {
		final StringWriter writer = new StringWriter();
		final Writer oldOut = ProxyConsole.getInstance().normalOutput();
		ProxyConsole.getInstance().setNormalOutput(writer);
		final double result =
			new USELESS().compute(
				TestMetricRepository.Model,
				TestMetricRepository.Model
					.getTopLevelEntityFromID("java.lang.Object"));
		ProxyConsole.getInstance().setNormalOutput(oldOut);
		Assert.assertEquals((double) 1, result, 0d);
		Assert
			.assertEquals(
				"## util.lang.ConcreteReceiverGuard reports a runtime deprecation: calling method \"pom.metrics.repository.USELESS.<init>()\" from class \"pom.test.general.TestMetricRepository\"\r\n## Please do not instantiate metrics directly to allow efficient caching, use the methods of \"pom.metrics.MetricsRepository\" to obtain metric instances.\r\n",
				writer.toString());
	}
}
