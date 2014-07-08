/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Family
 *
 */
public final class ContentRule {
	private ListRelationships listRelationships;
	private ListAttributes listAttributes;

	private OperatorStringString operatorStringString;
	public ContentRule() {
	}
	public ContentRule(
		final OperatorStringString anOperatorStringString,
		final ListRelationships aListRelationships,
		final ListAttributes aListAttributes) {
		this.operatorStringString = anOperatorStringString;
		this.listAttributes = aListAttributes;
		this.listRelationships = aListRelationships;
	}
	/**
	 * @return Returns the listAttributes.
	 */
	public ListAttributes getListAttributes() {
		return this.listAttributes;
	}
	/**
	 * @return Returns the listRelationships.
	 */
	public ListRelationships getListRelationships() {
		return this.listRelationships;
	}

	/**
	 * @return Returns the operatorStringString.
	 */
	public OperatorStringString getOperatorStringString() {
		return this.operatorStringString;
	}
	/**
	 * @param listAttributes The listAttributes to set.
	 */
	public void setListAttributes(final ListAttributes listAttributes) {
		this.listAttributes = listAttributes;
	}
	/**
	 * @param listRelationships The listRelationships to set.
	 */
	public void setListRelationships(final ListRelationships listRelationships) {
		this.listRelationships = listRelationships;
	}
	/**
	 * @param operatorStringString The operatorStringString to set.
	 */
	public void setOperatorStringString(
		final OperatorStringString operatorStringString) {
		this.operatorStringString = operatorStringString;
	}
}
