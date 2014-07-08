/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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
package mendel.part.tool;

import java.util.Properties;

import mendel.family.Family;
import mendel.part.AbstractPart;

/**
 * 
 * Input: Family
 * Output: Family
 * 
 * @author deniersi
 *
 */
public class SelectHmFamilyTool extends AbstractPart {

	private float hmLimit;
	
	public SelectHmFamilyTool() {
		this.hmLimit = 0.5f;
	}
	
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		setHmLimit(new Float(prop.getProperty("hmFamily")));
	}



	public Object compute(Object data) {
		Family family = (Family) data;
		if( family.homogeneity() > homogeneityLimit() ) {
			return family; // family.familyTag();
		} else {
			return null; // Tag.Other;	
		}
	}

	public float homogeneityLimit() {
		return this.hmLimit;
	}
	
	public void setHmLimit(float limit) {
		this.hmLimit = limit;
	}

}
