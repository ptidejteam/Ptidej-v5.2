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

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * A list where items can be put together with a number (a frequency, a cardinality). 
 * Consequently, items can be retrieved by order of number.
 * 
 * TODO: use in MemoBag?
 * 
 * @author deniersi
 *
 */
public class FreqList {
	
	private Vector<Item> list;
	
	private boolean sorted; 

	public FreqList() {
		this.list = new Vector<Item>();
	}
	
	public void add(Object o, int number) {
		this.sorted = false;
		this.list.add(new Item(o, number));
	}
	
	public void add(Object o) {
		add(o, 1);
	}
	
	public List<Item> getSortedList() {
		if( !this.sorted ) { // small optimization - sort only if modifications
			Collections.sort(this.list, new Comparator<Item>() {
				public int compare(Item o1, Item o2) {
					// Natural ordering = descending order (higher number first) so we inverse the order here
					if( o1.getNumber() > o2.getNumber() )
						return -1;
					if( o1.getNumber() == o2.getNumber() )
						return 0;
					return 1;
				}
			});
			this.sorted = true;
		}
		return this.list;
	}
	
	public Iterator iterator() {
		return getSortedList().iterator();
	}
	
	public List values() {
		Vector v = new Vector();
		for (Item i : getSortedList()) {
			v.add(i.getObject());
		}
		return v;
	}
	
	public List numbers() {
		Vector v = new Vector();
		for (Item i : getSortedList()) {
			v.add(i.getNumber());
		}
		return v;		
	}
	

	
	public class Item {
		
		private Object object;
		
		private int number;
		
		public Item(Object object, int number) {
			this.object = object;
			this.number = number;
		}

		public Object getObject() {
			return this.object;
		}

		public int getNumber() {
			return this.number;
		}
		
	}



	/**
	 * @param threshold
	 * @return
	 */
	public List collectFrequentValues(double threshold, double total) {
		Vector v = new Vector();
		List<Item> list2 = getSortedList();
		for (Item item : list2) {
			double freq = item.getNumber() / total;
			if( freq > threshold ) {
				v.add(item.getObject());
			}
		}
		return v;
	}
	
}
