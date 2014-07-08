/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package ptidej.viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import padl.kernel.IAbstractModel;
import ptidej.ui.Constants;
import ptidej.ui.IVisibility;
import ptidej.ui.kernel.Entity;
import ptidej.ui.kernel.Ghost;
import ptidej.ui.kernel.ModelGraph;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.layout.IUILayout;

public class LaidoutModelGraph extends ModelGraph {
	private IUILayout graphModelLayout;

	public LaidoutModelGraph(
		final Builder aBuilder,
		final IAbstractModel aModel,
		final IUILayout aLayout) {

		super(aBuilder, aModel);
		this.graphModelLayout = aLayout;
	}
	public LaidoutModelGraph(
		final Builder aBuilder,
		final IAbstractModel aModel,
		final Set aSetOfEntitiesToDisplay,
		final IUILayout aLayout) {

		super(aBuilder, aModel, aSetOfEntitiesToDisplay);
		this.graphModelLayout = aLayout;
	}
	protected void buildSpecifics() {
		super.buildSpecifics();

		// I reset the dimensions and compute the set of allEntities.
		final List entities = new ArrayList();
		for (int i = 0; i < this.graphModelConstituents.length; i++) {
			this.graphModelConstituents[i]
				.setPosition(Constants.DEFAULT_POSITION);

			// This is where I decide whether to show ghosts or not! 
			if (this.graphModelConstituents[i] instanceof Entity
					&& !(this.graphModelConstituents[i] instanceof Ghost && (this.graphModelConstituents[i]
						.getVisibleElements() & IVisibility.GHOST_ENTITIES_DISPLAY) == 0)) {

				entities.add(this.graphModelConstituents[i]);
			}
		}
		final Entity[] allEntities = new Entity[entities.size()];
		entities.toArray(allEntities);

		// I do the layout (oh yeah, baby, yeah...)
		// (This means that I do the build and then set up the positions.)
		this.graphModelConstituents =
			this.graphModelLayout.doLayout(allEntities);
	}
	public IUILayout getGraphLayout() {
		return this.graphModelLayout;
	}
	public void reset(
		final Builder aBuilder,
		final IAbstractModel anAbstractModel,
		final Set aSetOfEntitiesToDisplay,
		final Set aSetOfEntitiesToSelect,
		final IUILayout aLayout) {

		super.reset(
			aBuilder,
			anAbstractModel,
			aSetOfEntitiesToDisplay,
			aSetOfEntitiesToSelect);
		this.graphModelLayout = aLayout;
	}
	public void setGraphLayout(final IUILayout aLayout) {
		this.graphModelLayout = aLayout;
	}
}