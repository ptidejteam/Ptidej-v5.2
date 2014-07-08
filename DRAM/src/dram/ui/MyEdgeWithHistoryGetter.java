/*
 * Created on 2005-05-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dram.ui;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import fr.emn.oadymppac.graph.EdgeWeightGetter;
import fr.emn.oadymppac.graph.EdgeWithHistory;
import salvo.jesus.graph.Edge;

/**
 * @author Jean-Daniel Fekete
 * @version $Revision: 1.4 $
 */

/* je ne l'ai plus utilisé, j'ai apporté les mod dans EdgeWithHistoryGetter
 * 
 */

public class MyEdgeWithHistoryGetter implements EdgeWeightGetter {
	BoundedRangeModel range;

	public MyEdgeWithHistoryGetter() {
		this(new DefaultBoundedRangeModel());
	}
	
	public MyEdgeWithHistoryGetter(BoundedRangeModel model) {
		this.range = model;
	}
	/**
	 * @see fr.emn.oadymppac.graph.EdgeWeightGetter#getWeight(Edge)
	 */
	public float getWeight(Edge e) {		
		EdgeWithHistory edge = (EdgeWithHistory)e;	
		return (float) edge.getWeight();
		
	}

}

