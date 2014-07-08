/** (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
/**
 * 
 */
package pom.helper.xml;

/**
 * Define properties for Pom Metrics Vizualisation.
 * 
 * @author Jean-Yves Guyomarc'h
 * @since 2006/03/10
 *
 */
public class MetricProp {

	private String name;
	private String comment;
	private String type;
	private double max;
	private double min;

	public MetricProp(
		final String name,
		final String comment,
		final String type,
		final double max,
		final double min) {
		this.name = name;
		this.comment = comment;
		this.type = type;
		this.max = max;
		this.min = min;
	}

	public MetricProp(final String name, final String comment, final String type) {
		this(name, comment, type, -1.0, -1.0);
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * @return Returns the max, -1.0 if not set.
	 */
	public double getMax() {
		return this.max;
	}

	/**
	 * @return Returns the min, -1.0 if not set.
	 */
	public double getMin() {
		return this.min;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param max The max to set.
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * @param min The min to set.
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
