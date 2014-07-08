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
package mendel;

import java.util.Map;

public class MapToArray {
	
	private String[] keys;
	
	public MapToArray() {
	}
	
	public MapToArray(String[] keys) {
		this.keys = keys;
	}
	
	public MapToArray(Map data) {
		initialize(data);
	}

	public void initialize(Map map) {
		this.keys = keysFor(map);
	}
	
	public String[] keys() {
		return this.keys;
	}
	
	public Object[] record(Map map) {
		return recordFor(map, keys());
	}
	
	public static String[] keysFor(Map map) {
		return (String[]) map.keySet().toArray(new String[0]);
	}
	
	public static Object[] recordFor(Map map, String[] keys) {
		Object[] record = new Object[keys.length];
		for (int i = 0; i < record.length; i++) {
			record[i] = map.get(keys[i]);
		}
		return record;
	}
	
	

	
}
