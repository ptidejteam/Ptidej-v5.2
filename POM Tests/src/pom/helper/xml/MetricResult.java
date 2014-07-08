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
 * @author Jean-Yves Guyomarc'h
 *
 */
public class MetricResult {

	private String entityName;
	private double[] values;

	/**
	 * 
	 */
	public MetricResult() {
		this.entityName = null;
		this.values = null;
	}
	/**
	 * 
	 */
	public MetricResult(final String entityName, final double[] values) {
		this.entityName = entityName;
		this.values = values;
	}
	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return this.entityName;
	}
	/**
	 * @return Returns the values.
	 */
	public double[] getValues() {
		return this.values;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @param values The values to set.
	 */
	public void setValues(double[] values) {
		this.values = values;
	}
}
