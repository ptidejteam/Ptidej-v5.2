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

import java.util.Properties;
import java.util.Set;

import mendel.Util;
import mendel.family.Family;
import mendel.part.AbstractPart;

/**
 * Properties:
 * - printinterface: print the interface of the prototype in the output (default false)
 * 
 * Input: Family
 * Output: String
 * 
 * @author deniersi
 *
 */
public class FamilyInterfaceOutput extends AbstractPart {

	private boolean firstLine = true;
	private boolean showInterface = false;
	
	
	@Override
	public void initialize(Properties prop) {
		super.initialize(prop);
		this.showInterface = new Boolean(prop.getProperty("printinterface")).booleanValue();
	}

	public Object compute(Object data) {
		Family family = (Family) data;
		StringBuffer buf = printStats(family, new StringBuffer());
		printInterface(family, buf);
		if( this.firstLine ) {
			buf.insert(0, headers(family));
			this.firstLine = false;
		}
		return buf.toString();
	}
	
	public StringBuffer printStats(Family family, StringBuffer buffer) {
		return buffer.append(family.toString(false));
	}

	public void printInterface(Family family, StringBuffer buf) {
		if( this.showInterface ) {
			Set<String> itProto = family.getPrototype("prototype").getInterface();
			buf.append("\n" + itProto.size() + ", ");
			Util.join(itProto, ", ", buf);
		}
	}

	public String headers(Family family) {
		return family.toStringHeaders() + "\n";
	}

}
