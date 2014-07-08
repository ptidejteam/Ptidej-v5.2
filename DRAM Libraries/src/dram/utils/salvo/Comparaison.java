/*
 * Created on 2005-01-06
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dram.utils.salvo;

import java.util.Comparator;

import salvo.jesus.graph.Vertex;

/**
 * @author rachedsa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Comparaison implements  Comparator{
	public int compare(Object o1, Object o2) {
		String s1 = ((Vertex) o1).toString();
		String s2 = ((Vertex) o2).toString();
		return s1.compareTo(s2);
	}

}
