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

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public abstract class FamilyPlotViewer extends ApplicationFrame {

    public FamilyPlotViewer(String title, String filename) {
        super(title);
        
        final CategoryDataset dataset = buildDatasetFromCsv(filename);

        final CategoryAxis xAxis = new CategoryAxis("Family");
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(true);
        final CategoryItemRenderer renderer = setupRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        final JFreeChart chart = new JFreeChart(
            filename,
            new Font("SansSerif", Font.BOLD, 14),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(450, 270));
        setContentPane(chartPanel);
    }

	public abstract CategoryItemRenderer setupRenderer();

    public CategoryDataset buildDatasetFromCsv(String filename) {
        CategoryDataset dataset = createDataset();
		try {
			BufferedReader input = new BufferedReader( new FileReader(filename) );	
			String classLine = input.readLine();
			String dataLine = input.readLine();
						
			while (classLine!=null) {
				String[] classes = classLine.split(",");
				String[] data = dataLine.split(",");
				// csv data: one line = dataset for one family
				fillDataset(dataset, classes, data);
				classLine = input.readLine();
				dataLine = input.readLine();
			}
			
			input.close();
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		        

        return dataset;
    }

	public abstract void fillDataset(CategoryDataset dataset, String[] classes, String[] data);
//	{
//		final List<Double> list = new ArrayList<Double>(data.length-1);
//		for (int k = 1; k < data.length; k++) {
//			list.add(new Double(data[k]));
//		}
//		dataset.add(list, data[0], classes[0]);
//	}

	public abstract CategoryDataset createDataset();
    
    public void go() {
		pack();
	    RefineryUtilities.centerFrameOnScreen(this);
	    setVisible(true);
	}

}
