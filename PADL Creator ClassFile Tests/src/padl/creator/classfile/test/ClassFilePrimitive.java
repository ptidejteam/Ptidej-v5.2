/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.creator.classfile.test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IElement;
import padl.kernel.IFactory;
import padl.kernel.impl.Factory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2003/12/09
 */
public abstract class ClassFilePrimitive extends TestCase {
	public static void assertAssigable(
		final String aMessage,
		final Class anInterface,
		final Class aClass) {

		if (!anInterface.isAssignableFrom(aClass)) {
			Assert.fail(aMessage);
		}
	}
	public static IFactory getFactory() {
		return Factory.getInstance();
	}
	public ClassFilePrimitive(final String aName) {
		super(aName);
	}
	public static int[] getConstituentPosition(
		final Class aConstituentType,
		final IElement[] someElements) {

		final Set positionsSet = new TreeSet();
		for (int i = 0; i < someElements.length; i++) {
			final IElement element = someElements[i];
			final Class[] interfaces = element.getClass().getInterfaces();
			for (int j = 0; j < interfaces.length; j++) {
				final Class interfece = interfaces[j];
				if (interfece.equals(aConstituentType)) {
					positionsSet.add(new Integer(i));
				}
			}
		}

		final int[] positionsArray = new int[positionsSet.size()];
		final Iterator iterator = positionsSet.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			final Integer position = (Integer) iterator.next();
			positionsArray[i] = position.intValue();
			i++;
		}
		return positionsArray;
	}
}
