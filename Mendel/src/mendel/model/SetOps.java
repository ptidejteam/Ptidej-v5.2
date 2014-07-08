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
package mendel.model;

import java.util.HashSet;
import java.util.Set;

public class SetOps {

	public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
		Set<E> u = new HashSet<E>(s1);
		u.addAll(s2);
		return u;
	}
	
	public static <E> Set<E> inter(Set<E> s1, Set<E> s2) {
		Set<E> i = new HashSet<E>(s1);
		i.retainAll(s2);
		return i;
	}
	
	public static <E> Set<E> diff(Set<E> s1, Set<E> s2) {
		Set<E> d = new HashSet<E>(s1);
		d.removeAll(s2);
		return d;		
	}
	
	public static float jaccardIndex(Set s1, Set s2) {
		float unionSize = (float) union(s1, s2).size();
		if( unionSize==0 ) { // empty sets are equals! and avoid divide-by-zero error
			return 1;
		} else {
			return inter(s1, s2).size() / unionSize;
		}
	}
	
	public static float jaccardDistance(Set s1, Set s2) {
		return 1 - jaccardIndex(s1, s2);
	}
	
	public static float inclusionIndex(Set included, Set inclusive) {
		return inter(included, inclusive).size() / (float) included.size();
	}

}
