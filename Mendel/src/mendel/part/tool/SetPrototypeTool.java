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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 
 * Input: Family
 * Output: Family
 * 
 * @author deniersi
 *
 */
public class SetPrototypeTool extends AbstractPrototypeTool {

	public Set<String> buildPrototypeInterface(List<Set<String>> interfaces) {
		Set<String> protoInterface = null;
		for (Set<String> itface : interfaces) {
			if( protoInterface==null ) {
				protoInterface = new HashSet<String>(itface);
 			} else {
				protoInterface.retainAll(itface);
			}
		}
		return protoInterface;
	}

	@Override
	public String getUniqueKey() {
		return "prototype set";
	}

}
