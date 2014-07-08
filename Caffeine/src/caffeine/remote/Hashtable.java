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

import java.util.Map;

/**
 * @version	0.1
 * @author 	Yann-Gaël Guéhéneuc
 */
public final class Hashtable extends java.util.Hashtable {
	private static final long serialVersionUID = -8257844447163010732L;

	public static synchronized java.util.Hashtable newInstance() {
		return Hashtable.newInstance(11);
	}
	public static synchronized java.util.Hashtable newInstance(
		int initialCapacity) {

		return Hashtable.newInstance(initialCapacity, 0.75f);
	}
	public static synchronized java.util.Hashtable newInstance(
		final int initialCapacity,
		final float loadFactor) {

		return new Hashtable(initialCapacity, loadFactor);
	}
	public static synchronized java.util.Hashtable newInstance(final Map t) {
		return new Hashtable(t);
	}

	private Hashtable(final int initialCapacity, final float loadFactor) {
		super(initialCapacity, loadFactor);
	}
	private Hashtable(final Map t) {
		super(t);
	}

	public synchronized Object put(final Object key, final Object value) {
		return super.put(key, value);
	}
}
