/**
 * 
 */
package sad.rule.creator.ui.metaModel;

import java.util.Vector;

/**
 * @author Family
 *
 */
public final class ListRelationships {
	private Vector relationships;
	public ListRelationships() {
	}
	public ListRelationships(final Vector aRelationships) {
		this.relationships = aRelationships;
	}
	/**
	 * @return Returns the relationships.
	 */
	public Vector getRelationships() {
		return this.relationships;
	}
	/**
	 * @param relationships The relationships to set.
	 */
	public void setRelationships(final Vector relationships) {
		this.relationships = relationships;
	}

}
