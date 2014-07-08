/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel;

import java.util.Iterator;

/**
 * @author Yann-Gaël Guéhéneuc
 */
/* Yann 2004/04/09
 * The implementation must ensure that constituents
 * are ordered in the same way across platforms.
 * Yann 2013/07/15
 * Actually, since the implementation of the 
 * GenericContainer and its subclasses, this is not
 * a problem anymore :-)
 */
public interface IContainer {
	// Yann 2013/07/15: Add!
	// I add this method to treat all container uniformly,
	// however, each container can reject certain constituents
	// and enforce the call to its own overloaded method(s).
	void addConstituent(final IConstituent aConstituent);
	boolean doesContainConstituentWithID(final char[] anID);
	boolean doesContainConstituentWithName(final char[] aName);
	Iterator getConcurrentIteratorOnConstituents();
	Iterator getConcurrentIteratorOnConstituents(final IFilter filter);
	Iterator getConcurrentIteratorOnConstituents(
		final java.lang.Class aConstituentType);
	IConstituent getConstituentFromID(final char[] anID);
	IConstituent getConstituentFromID(final String anID);
	IConstituent getConstituentFromName(final char[] aName);
	IConstituent getConstituentFromName(final String aName);
	Iterator getIteratorOnConstituents();
	Iterator getIteratorOnConstituents(final IFilter filter);
	Iterator getIteratorOnConstituents(final java.lang.Class aConstituentType);
	int getNumberOfConstituents();
	int getNumberOfConstituents(final java.lang.Class aConstituentType);
	void removeAllConstituent();
	//	/**
	//	 * The implementation must ensure that actors
	//	 * are ordered in the same way across platforms.
	//	 * (Yann 2004/04/09)
	//	 * 
	//	 * @deprecated This method is dangerous because it exposes the
	//	 * inner working of all the constituents that are able to contains
	//	 * other constituents. Thus, it is now replaced with the two
	//	 * getIterator() and getIterator(Class aConstituentType) methods
	//	 * (and related methods).
	//	 */
	//	List listOfConstituents();
	void removeConstituentFromID(final char[] anID);
}
