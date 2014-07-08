/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Family
 *
 */
public final class Rule {

	private String name;
	private ContentRule contentRule;

	public Rule() {
	}
	public Rule(final String aName, final ContentRule aContentRule) {
		this.name = aName;
		this.contentRule = aContentRule;
	}

	/**
	 * @return Returns the contentRule.
	 */
	public ContentRule getContentRule() {
		return this.contentRule;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param contentRule The contentRule to set.
	 */
	public void setContentRule(final ContentRule contentRule) {
		this.contentRule = contentRule;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
