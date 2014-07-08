/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel.part.tool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mendel.CountMap;


/**
 * @author Simon Denier
 * @since Sep 6, 2008
 *
 */
public class MajorityPrototypeTool extends AbstractPrototypeTool {

	private static final double THRESHOLD = .5;

	public Set<String> buildPrototypeInterface(List<Set<String>> interfaces) {
		CountMap countMap = new CountMap();
		for (Set<String> itface : interfaces) {
			for (String sig : itface) {
				countMap.inc(sig);
			}
		}
		return new HashSet<String>(countMap.toFreqList().collectFrequentValues(THRESHOLD, interfaces.size()));
	}

	@Override
	public String getUniqueKey() {
		return "prototype ham";
	}
	
}
