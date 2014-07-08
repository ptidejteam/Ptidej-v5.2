/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package mendel.family;

import java.util.HashMap;
import java.util.Map;

import mendel.metric.DispatchMetric;
import mendel.model.JClassEntity;
import mendel.part.tool.AbstractPrototypeRecTool;
import mendel.part.tool.HammingTool;
import mendel.part.tool.MajorityPrototypeRecTool;

public class HammingDistance extends DispatchMetric {

	private HammingTool htool;
	private AbstractPrototypeRecTool ptool;
	private Map<JClassEntity, Prototype> memo;
	
	public HammingDistance() {
		this.htool = new HammingTool();
		this.ptool = new MajorityPrototypeRecTool();
		this.memo = new HashMap<JClassEntity, Prototype>();
	}
	
	public String compute(JClassEntity entity) {
		Prototype prototype = computeHammingPrototype(entity.getSuperClass());
		return new Integer(prototype.computeHammingFor(entity)).toString();
	}
	
	public Prototype computeHammingPrototype(JClassEntity parent) {
		if( !this.memo.containsKey(parent) ) {
			this.memo.put(parent, this.htool.computeHammingData(parent, this.ptool.buildPrototype(parent)));
		}
		return this.memo.get(parent);
	}

	public String getName() {
		return "Hamming";
	}

}
