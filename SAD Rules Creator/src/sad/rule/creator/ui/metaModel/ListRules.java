/**
 * 
 */
package sad.rule.creator.ui.metaModel;

import java.util.Vector;

/**
 * @author Family
 *
 */
public final class ListRules {

	private Vector rules;
	public ListRules() {
	}
	public ListRules(final Vector aRules) {
		this.rules = aRules;
	}
	/**
	 * @return Returns the rules.
	 */
	public Vector getRules() {
		return this.rules;
	}
	/**
	 * @param rules The rules to set.
	 */
	public void setRules(final Vector rules) {
		this.rules = rules;
	}
}
