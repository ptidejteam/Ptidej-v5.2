/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Family
 *
 */
public final class Attribute {
	private String type;

	private String name;
	private String value;
	public Attribute(final String aType, final String aName, final String aValue) {
		this.type = aType;
		this.name = aName;
		this.value = aValue;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(final String type) {
		this.type = type;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(final String value) {
		this.value = value;
	}

}
