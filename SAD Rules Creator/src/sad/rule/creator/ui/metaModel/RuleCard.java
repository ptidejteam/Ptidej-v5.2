/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Amandine & Jean-Luc
 *
 */
public final class RuleCard {
	private String name;
	private ListRules listRules;
	public RuleCard() {
	}
	public RuleCard(final String aName, final ListRules aListRules) {
		this.name = aName;
		this.listRules = aListRules;
	}

	/**
	 * @return Returns the listRules.
	 */
	public ListRules getListRules() {
		return this.listRules;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param listRules The listRules to set.
	 */
	public void setListRules(final ListRules listRules) {
		this.listRules = listRules;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
