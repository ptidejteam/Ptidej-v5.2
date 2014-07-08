/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Family
 *
 */
public final class ListAttributes {
	private Attribute attribute;
	private OperatorAttributeAttribute operatorAttributeAttribute;
	public ListAttributes() {
	}
	public ListAttributes(
		final OperatorAttributeAttribute anOperatorAttributeAttribute,
		final Attribute anAtrribute) {
		this.attribute = anAtrribute;
		this.operatorAttributeAttribute = anOperatorAttributeAttribute;
	}
	/**
	 * @return Returns the attributes.
	 */
	public Attribute getAttribute() {
		return this.attribute;
	}
	/**
	 * @return Returns the operatorAttributeAttribute.
	 */
	public OperatorAttributeAttribute getOperatorAttributeAttribute() {
		return this.operatorAttributeAttribute;
	}
	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttributes(final Attribute attribute) {
		this.attribute = attribute;
	}
	/**
	 * @param operatorAttributeAttribute The operatorAttributeAttribute to set.
	 */
	public void setOperatorAttributeAttribute(
		final OperatorAttributeAttribute operatorAttributeAttribute) {
		this.operatorAttributeAttribute = operatorAttributeAttribute;
	}

}
