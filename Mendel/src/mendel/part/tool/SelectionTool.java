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

import java.util.Map;
import java.util.Properties;

import mendel.part.AbstractPart;

/**
 * Initializating Properties:
 * - selectTag: the tag which points to entities to select (value "Y")
 *
 * Input: Map<metric name, metric result>
 * Output: Map<metric name, metric result>
 * 
 * @author Simon Denier
 * @since Mar 17, 2008
 *
 */
public class SelectionTool extends AbstractPart {
	
	private String tag;
	

	public void initialize(String tag) {
		setTag(tag);
	}
	
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		setTag(prop.getProperty("selectTag"));
	}
	
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	

	public Object compute(Object data) {
		Map record = (Map) data;
		String bool = (String) record.get(this.tag);
		if( bool.equals("Y") ) {
			return record;
		} else
			return null;
	}

}
