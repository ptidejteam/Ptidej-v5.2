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

import java.util.Iterator;
import java.util.List;
import ptidej.ui.layout.repository.sugiyama.graph.Graph;
import ptidej.ui.layout.repository.sugiyama.graph.Node;

/**
 * @author kahlamoh
 */
// TODO: Is this class really needed? Why not create an instance
// of Graph right away in the "doLayout" method?
// TODO Should be a Singleton!
public class GraphBuilder {
	private List graphNodes;
	private int nbLevels;
	private Graph graph;

	public GraphBuilder(final List aGraphNodes, final int anNbLevels) {
		this.graphNodes = aGraphNodes;
		this.nbLevels = anNbLevels;
	}
	public void buildGraph() {
		this.graph = new Graph(this.nbLevels);
		final Iterator nodesItr = this.graphNodes.iterator();
		while (nodesItr.hasNext()) {
			Node next = (Node) nodesItr.next();
			this.graph.addNode(next);
		}
	}
	public Graph getGraph() {
		return this.graph;
	}
}
