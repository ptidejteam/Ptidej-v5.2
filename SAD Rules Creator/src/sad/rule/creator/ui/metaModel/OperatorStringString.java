/**
 * 
 */
package sad.rule.creator.ui.metaModel;

/**
 * @author Family
 *
 */
public final class OperatorStringString {

	private String name;

	private String firstString;
	private String secondString;
	public OperatorStringString(
		final String aName,
		final String aFirstString,
		final String aSecondString) {
		this.name = aName;
		this.firstString = aFirstString;
		this.secondString = aSecondString;
	}

	/**
	 * @return Returns the firstString.
	 */
	public String getFirstString() {
		return this.firstString;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return Returns the secondString.
	 */
	public String getSecondString() {
		return this.secondString;
	}
	/**
	 * @param firstString The firstString to set.
	 */
	public void setFirstString(final String firstString) {
		this.firstString = firstString;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @param secondString The secondString to set.
	 */
	public void setSecondString(final String secondString) {
		this.secondString = secondString;
	}

}
