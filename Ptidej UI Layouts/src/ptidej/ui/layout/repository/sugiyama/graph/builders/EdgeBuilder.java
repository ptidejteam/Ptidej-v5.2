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
package ptidej.ui.layout.repository.sugiyama.graph.builders;

import ptidej.ui.layout.repository.sugiyama.graph.Edge;
import ptidej.ui.layout.repository.sugiyama.graph.EdgeSet;
import ptidej.ui.layout.repository.sugiyama.graph.INode;

/**
 * @author kahlamoh
 * @since 25/07/2006
 */
// TODO: This class does not enough or should be removed?
// TODO Should be a Singleton!
public class EdgeBuilder {
	private final EdgeSet edges;

	public EdgeBuilder() {
		this.edges = new EdgeSet();
	}
	public EdgeSet getEdgeSet() {
		return this.edges;
	}
	//	public void buildEdges(Graph aGraph) {
	//
	//		// for all the levels
	//		for (int i = 0; i < aGraph.getNbLevels(); i++) {
	//			
	//		}
	public void buildEdge(
		final INode aParent,
		final INode aChild,
		int aDirection) {

		this.edges.addEdge(new Edge(aDirection, null, aParent, aChild));
		// Node aParent, Node aChild, int aDirection
	}
}
