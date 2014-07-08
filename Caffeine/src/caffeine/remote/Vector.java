/*
 * (c) Copyright 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package caffeine.remote;

import java.util.List;
import java.util.RandomAccess;

/**
 * @version 	0.2
 * @author		Yann-Gaël Guéhéneuc
 */
public final class Vector extends java.util.Vector implements List,
		RandomAccess, Cloneable, java.io.Serializable {

	private static final long serialVersionUID = 2432101711764574488L;

	public static synchronized java.util.Vector newInstance() {
		return Vector.newInstance(10);
	}
	public static synchronized java.util.Vector newInstance(
		final int initialCapacity) {

		return Vector.newInstance(initialCapacity, 0);
	}
	public static synchronized java.util.Vector newInstance(
		final int initialCapacity,
		final int capacityIncrement) {

		return new Vector(initialCapacity, capacityIncrement);
	}

	private Vector(final int initialCapacity, final int capacityIncrement) {
		super(initialCapacity, capacityIncrement);
	}

	public void add(final int index, final Object element) {
		super.add(index, element);
	}
	public synchronized boolean add(final Object o) {
		return super.add(o);
	}
	//	public synchronized boolean addAll(Collection c) {
	//		return super.addAll(c);
	//	}
	//	public synchronized boolean addAll(int index, Collection c) {
	//		return super.addAll(index, c);
	//	}
	public synchronized void addElement(final Object obj) {
		super.addElement(obj);
	}
}
