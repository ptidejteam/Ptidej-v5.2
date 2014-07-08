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

package mendel.family;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.ScatterRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;

public class ScatterPointViewer extends FamilyPlotViewer {

	private static final double JITTER = 0.1;

	public static void main(final String[] args) {
        FamilyPlotViewer viewer = new ScatterPointViewer("Family ScatterPoints", args[0]);
        viewer.go();
    }

	public ScatterPointViewer(String title, String filename) {
		super(title, filename);
	}

	/* (non-Javadoc)
	 * @see mendel.family.FamilyPlotViewer#createDataset()
	 */
	@Override
	public CategoryDataset createDataset() {
		return new DefaultMultiValueCategoryDataset();
	}

	/* (non-Javadoc)
	 * @see mendel.family.FamilyPlotViewer#fillDataset(org.jfree.data.category.CategoryDataset, java.lang.String[], java.lang.String[])
	 */
	@Override
	public void fillDataset(CategoryDataset dataset, String[] classes,
			String[] data) {
		DefaultMultiValueCategoryDataset set = (DefaultMultiValueCategoryDataset) dataset;
		final List<Double> list = new ArrayList<Double>(data.length-1);
		Random random = new Random();
		for (int k = 1; k < data.length; k++) {
			list.add(new Double(data[k]) + JITTER * (random.nextDouble() - .5));
		}
		set.add(list, data[0], classes[0]);
	}

	/* (non-Javadoc)
	 * @see mendel.family.FamilyPlotViewer#setupRenderer()
	 */
	@Override
	public CategoryItemRenderer setupRenderer() {
        ScatterRenderer renderer = new ScatterRenderer();
        renderer.setBaseShapesFilled(false);
        renderer.setDrawOutlines(true);
        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        return renderer;
	}

}
