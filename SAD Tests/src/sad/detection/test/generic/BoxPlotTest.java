/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package sad.detection.test.generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IGhost;
import padl.kernel.impl.Factory;
import sad.util.BoxPlot;

/**
 * Test for the BoxPlot
 * See rsc/BoxPlot Explained.pdf for the choice of 
 * dataset and the expected values tested.
 */
public final class BoxPlotTest extends TestCase {
	// 
	private static final Map<IGhost, Double[]> Data1 =
		new HashMap<IGhost, Double[]>();
	private static final Map<IGhost, Double[]> Data2 =
		new HashMap<IGhost, Double[]>();
	private static final double[] DoubleValues1 = new double[] { 11.0, 11.2,
			11.5, 11.6, 11.9, 12.0, 12.2, 12.8, 12.9, 12.9, 13.1, 13.3, 13.4,
			13.8, 13.9, 14.2, 14.5, 14.5, 14.6, 15.3, 15.5, 16.0 };
	private static final double[] DoubleValues2 = new double[] { 11.0, 11.2,
			11.5, 11.6, 11.9, 12.0, 12.2, 12.8, 12.9, 12.9, 13.1, 13.3, 13.4,
			13.8, 13.9, 14.2, 14.5, 14.5, 14.6, 15.3, 15.5, 19.0 };
	static {
		//	for (int i = 0; i < 10; i++) {
		//		Data.put(
		//			Factory.getUniqueInstance().createGhost(
		//				Integer.toString(i)),
		//			new Integer(i));
		//	}
		for (int i = 0; i < BoxPlotTest.DoubleValues1.length; i++) {
			final char[] id =
				(Double.toString(BoxPlotTest.DoubleValues1[i]) + " (" + i + ')')
					.toCharArray();
			BoxPlotTest.Data1.put(
				Factory.getInstance().createGhost(id, id),
				new Double[] { new Double(BoxPlotTest.DoubleValues1[i]),
						new Double(0) });
		}
		for (int i = 0; i < BoxPlotTest.DoubleValues2.length; i++) {
			final char[] id =
				(Double.toString(BoxPlotTest.DoubleValues2[i]) + " (" + i + ')')
					.toCharArray();
			BoxPlotTest.Data2.put(
				Factory.getInstance().createGhost(id, id),
				new Double[] { new Double(BoxPlotTest.DoubleValues2[i]),
						new Double(0) });
		}
	}
	private static double getHighestValue(final Map<IGhost, Double[]> someData) {
		double maxValue = Double.MIN_VALUE;
		final Iterator<Double[]> iterator = someData.values().iterator();
		while (iterator.hasNext()) {
			final Double[] d = iterator.next();
			final double value = d[0].doubleValue();
			if (value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}
	private static double getLowestValue(final Map<IGhost, Double[]> someData) {
		double minValue = Double.MAX_VALUE;
		final Iterator<Double[]> iterator = someData.values().iterator();
		while (iterator.hasNext()) {
			final Double[] d = iterator.next();
			final double value = d[0].doubleValue();
			if (value < minValue) {
				minValue = value;
			}
		}
		return minValue;
	}

	public BoxPlotTest(final String name) {
		super(name);
	}
	protected void setUp() {
	}
	@SuppressWarnings("unchecked")
	public void testCurrentHigherValue() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals(
			"Higher value",
			16.0,
			BoxPlotTest.getHighestValue(boxPlot.getHighValues()),
			0.0);
	}
	@SuppressWarnings("unchecked")
	public void testCurrentHigherValue2() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data2, 0.0);
		Assert.assertEquals(
			"Higher value",
			15.5,
			BoxPlotTest.getHighestValue(boxPlot.getHighValues()),
			0.0);
	}
	public void testCurrentHighValues() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Number of high values", 4, boxPlot
			.getHighValues()
			.size());
	}
	public void testCurrentIQR() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("IQR", 2.5, boxPlot.getInterQuartileRange(), 0.0);
	}
	public void testCurrentLowerQuartile() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals(
			"Lower quartile",
			12.0,
			boxPlot.getLowerQuartile(),
			0.0);
	}
	@SuppressWarnings("unchecked")
	public void testCurrentLowerValue() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals(
			"Lower value",
			11.0,
			BoxPlotTest.getLowestValue(boxPlot.getLowValues()),
			0.0);
	}
	public void testCurrentLowValues() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Number of low values", 5, boxPlot
			.getLowValues()
			.size());
	}
	public void testCurrentMaxBound() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Max bound", 18.25, boxPlot.getMaxBound(), 0.0);
	}
	public void testCurrentMedian() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Median", 13.2, boxPlot.getMedian(), 0.0);
	}
	public void testCurrentMinBound() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Min bound", 8.25, boxPlot.getMinBound(), 0.0);
	}
	public void testCurrentNormalValues() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Number of number values", 13, boxPlot
			.getNormalValues()
			.size());
	}
	public void testCurrentOutliers() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals("Number of outliers", 0, boxPlot
			.getHighOutliers()
			.size());
	}
	public void testCurrentOutliers2() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data2, 0.0);
		Assert.assertEquals("Number of outliers", 1, boxPlot
			.getHighOutliers()
			.size());
	}
	public void testCurrentUpperQuartile() {
		final BoxPlot boxPlot = new BoxPlot(BoxPlotTest.Data1, 0.0);
		Assert.assertEquals(
			"Higher quartile",
			14.5,
			boxPlot.getUpperQuartile(),
			0.0);
	}
	public void testEmptyData() {
		final BoxPlot boxPlot =
			new BoxPlot(new HashMap<IGhost, Double[]>(), 0.0);
		Assert.assertEquals(
			"Higher quartile",
			0.0,
			boxPlot.getUpperQuartile(),
			0.0);
	}
}
