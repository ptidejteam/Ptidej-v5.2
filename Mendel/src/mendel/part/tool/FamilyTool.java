/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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
package mendel.part.tool;

import java.util.Properties;

import mendel.family.Family;
import mendel.family.BehaviorHomogeneity;
import mendel.family.RoleTag;
import mendel.model.JClassEntity;
import mendel.part.AbstractPart;

/**
 * 
 * Input: JClassEntity
 * Output: Family
 * 
 * @author deniersi
 *
 */
public class FamilyTool extends AbstractPart {

	protected RoleTag behaviorTagger;
	
	protected BehaviorHomogeneity behaviorMetric;
	
	private int familyThreshold = 1;
	
	public FamilyTool() {
		this.behaviorTagger = new RoleTag();
		this.behaviorMetric = new BehaviorHomogeneity();
	}
	
	/* (non-Javadoc)
	 * @see mendel.part.AbstractPart#initialize(java.util.Properties)
	 */
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		if( new Boolean(prop.getProperty("excludeSingleFamily")).booleanValue() ) {
			this.familyThreshold = 2;
		}
	}
	
	public Object compute(Object entry) {
		JClassEntity parent = (JClassEntity) entry;
		if( parent.getChildren().size() >= this.familyThreshold ) {
			Family family = new Family(parent);
			family.setFamilySets(this.behaviorTagger.tagSet(parent.getChildren()));
			this.behaviorMetric.homogeneity(family);
			this.behaviorMetric.checkHomogeneity(family);
			return family;
		} else
			return null;
	}

}
