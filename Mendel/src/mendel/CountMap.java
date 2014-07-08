/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * @author Simon Denier
 * @since Aug 12, 2008
 *
 */
public class CountMap {

	private TreeMap<String, Integer> map;
	
	/**
	 * 
	 */
	public CountMap() {
		this.map = new TreeMap<String, Integer>();
	}
	
	public void add(String key, int count) {
		if( !this.map.containsKey(key) ) {
			this.map.put(key, 0);
		}
		this.map.put(key, this.map.get(key) + count);
	}
	
	public void inc(String key) {
		add(key, 1);
	}
	
	public int getCount(String key) {
		return this.map.get(key);
	}
	
	public Set<String> getValues() {
		return this.map.keySet();
	}
	
	public Collection<Integer> getNumbers() {
		return this.map.values();
	}
	
	public Set<Entry<String, Integer>> getItems() {
		return this.map.entrySet();
	}
	
	public FreqList toFreqList() {
		FreqList list = new FreqList();
		for (String k : this.map.keySet()) {
			list.add(k, getCount(k));
		}
		return list;
	}
	
}
