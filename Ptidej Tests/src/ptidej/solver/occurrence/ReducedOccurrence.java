/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.solver.occurrence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/08/09
 */
public class ReducedOccurrence implements Comparable {
	private Map nameValueMap;
	public ReducedOccurrence() {
		this.nameValueMap = new HashMap();
	}
	public void addComponent(
		final char[] aComponentName,
		final char[] aComponentValue) {

		this.nameValueMap.put(String.valueOf(aComponentName), String
			.valueOf(aComponentValue));
	}
	public Set getComponentNames() {
		return this.nameValueMap.keySet();
	}
	public Set getComponentValues() {
		return this.nameValueMap.entrySet();
	}
	public char[] getComponentValue(final char[] aComponentName) {
		return ((String) this.nameValueMap.get(aComponentName)).toCharArray();
	}
	public String getKey() {
		final StringBuffer buffer = new StringBuffer();
		final Iterator keys = this.nameValueMap.keySet().iterator();
		while (keys.hasNext()) {
			final String key = (String) keys.next();
			buffer.append(this.nameValueMap.get(key));
			if (keys.hasNext()) {
				buffer.append('+');
			}
		}
		return buffer.toString();
	}
	public boolean equals(final Object anObject) {
		if (anObject instanceof ReducedOccurrence) {
			return this.equals((ReducedOccurrence) anObject);
		}
		return false;
	}
	public boolean equals(final ReducedOccurrence aPrincipalComponent) {
		return this.nameValueMap.equals(aPrincipalComponent.nameValueMap);
	}
	public int compareTo(final Object anObject) {
		if (anObject instanceof ReducedOccurrence) {
			return this.compareTo((ReducedOccurrence) anObject);
		}
		return 0;
	}
	public int compareTo(final ReducedOccurrence aPrincipalComponent) {
		return this.getKey().compareTo(aPrincipalComponent.getKey());
	}
	public String toString() {
		return this.getKey();
	}
}