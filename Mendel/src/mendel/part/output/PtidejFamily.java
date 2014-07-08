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
package mendel.part.output;

import java.util.HashMap;
import java.util.Map;

import mendel.family.Family;
import mendel.part.AbstractPart;

/**
 * 
 * Input: Family
 * Output: Map
 * 
 * @author deniersi
 *
 */
public class PtidejFamily extends AbstractPart {

	public Object compute(Object data) {
		Family family = (Family) data;
		Map map = new HashMap();
		
		map.put("Name", "Family");
		map.put("Size", family.familySize());
		map.put("Parent", family.parent().getEntityName());
		map.put("Hm Behavior", family.behaviorTag());
		map.put("Hm Index", family.homogeneity());
		// TODO: manage color
		map.put("Sign", "Positive");
		map.put("ColorInt", new Integer((int) (family.homogeneity() * 100)));
		
		return map;
	}

}
