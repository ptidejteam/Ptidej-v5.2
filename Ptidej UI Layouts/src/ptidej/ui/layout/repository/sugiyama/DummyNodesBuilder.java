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

package ptidej.ui.layout.repository.sugiyama;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import ptidej.ui.layout.repository.sugiyama.graph.DummyNode;
import ptidej.ui.layout.repository.sugiyama.graph.INode;
import ptidej.ui.layout.repository.sugiyama.graph.Node;

/**
 * @author kahlamoh
 * @date 23-06-2006
 */
// TODO Should be a Singleton!
public class DummyNodesBuilder {
	private List graphNode;
	private List graphModel;

	public DummyNodesBuilder(final List aGraphNode) {
		this.graphNode = aGraphNode;
	}

	// 29-05-2006
	// add the dummy sources wehre they shoud be added 
	// The dummy nodes are connected 
	// from the parents to the child where are separeted whith
	// more one level from one to the other 
	// to satisfie the 'Element D' of the Sugiyama's readability elements.
	// NO LONG SPAN edges !	
	public void addDummy() {
		this.graphModel = new ArrayList();
		this.graphModel.addAll(this.graphNode); // we use wath it was done before 
		final ListIterator vertexItr = this.graphNode.listIterator(0);
		while (vertexItr.hasNext()) {
			Node vertex = (Node) vertexItr.next();
			// if not dummy
			if (!(vertex instanceof DummyNode)) {
				final INode[] tabChildren = vertex.getTabChildren();
				final Children children = vertex.getChildren();
				//				final ListIterator childrenItr = children.listIterator(0);
				//				while (childrenItr.hasNext()) {
				for (int i = 0; i < tabChildren.length; i++) {
					final INode child = tabChildren[i]; //(Node) childrenItr.next();
					// if not dummy
					if (!(child instanceof DummyNode)) {

						// if a long span edge between the two nodes
						if (child.getLevel() - 1 > vertex.getLevel()) {
							// if only one level is between the .... == one single dummy node!
							if (child.getLevel() - 2 == vertex.getLevel()) {
								DummyNode dummy =
									new DummyNode(
										child.getLevel() - 1,
										vertex,
										child);
								this.graphModel.add(dummy);
								//								childrenItr.set(dummy);
								children.remove(child);
								children.add(dummy);
								child.getParents().remove(vertex);
								child.addParent(dummy);
							}
							else {
								DummyNode dummyFirst =
									new DummyNode(
										vertex.getLevel() + 1,
										vertex,
										null);
								//								childrenItr.set(dummyFirst); // child are replaced by dummyFirst

								children.remove(child);
								children.add(dummyFirst);

								this.graphModel.add(dummyFirst);

								DummyNode lastDummy = dummyFirst;
								// we add dummies as necessary in evry level	
								for (int j = vertex.getLevel() + 2; j < child
									.getLevel(); j++) {
									DummyNode dummy =
										new DummyNode(j, lastDummy, null);
									// this null child must be removed after 
									// before adding the real child!
									this.graphModel.add(dummy);

									//	 this correction is done 07-07-2006
									// when you a null to a list 
									// this null object must be cleared !
									lastDummy.getChildren().clear();
									lastDummy.addChild(dummy);

									lastDummy = dummy;
								}

								// this correction is done 07-07-2006
								// when you a null to a list 
								// this null object must be cleared !
								lastDummy.getChildren().clear();
								lastDummy.addChild(child);
								child.getParents().remove(vertex);
								child.addParent(lastDummy);
							}
						}
					}
				}
			}
		}
	}

	public List getGraphModel() {
		return this.graphModel;
	}
}
