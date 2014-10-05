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
package padl.kernel;

import java.io.Serializable;
import padl.visitor.IVisitor;

/**
 * - startCloneSession() is somewhat equivallent to a shallow
 *   copy of the constituent. After this protocol is executed,
 *   it is guaranteed that all constituents have been
 *   shallow-copied. No assumption is made about the link among
 *   constituents.
 * - performCloneSession() updates the links among constituents,
 *   using the isCloned() and getClone() methods. After this
 *   protocol is executed, it is guaranteed that all the links
 *   have been updated, somewhat like a deep copy;
 * - endCloneSession() finished the updates and cleans the possible
 *   temporary values, mainly it sets to null all the "clone"
 *   instance variable.
 * 
 * @author Yann-Gaël Guéhéneuc
 */
public interface IConstituent extends Cloneable, INavigable, Serializable {
	void accept(final IVisitor aVisitor);
	void endCloneSession();
	boolean equals(final Object anObject);
	/**
	 * This method return a clone (deep copy) of the constituent.
	 */
	IConstituent getClone();
	String[] getCodeLines();
	String getComment();
	// Yann 2004/05/21: Flexibility with names.
	/**
	 * The method getDisplayName() returns the name to be
	 * displayed by graphical front-end, while the getName()
	 * method returns the (fully-qualified) name of the
	 * constituent, and getID() return a unique identifier.
	 * (By default, getDisplayName() and getName() are identical.)
	 */
	String getDisplayName();
	String getDisplayID();
	char[] getID();
	char[] getName();
	int getVisibility();
	int getWeight();
	int hashCode();
	boolean isAbstract();
	boolean isFinal();
	boolean isPrivate();
	boolean isProtected();
	boolean isPublic();
	boolean isStatic();
	void performCloneSession();
	void resetCodeLines();
	void setAbstract(final boolean aBoolean);
	void setCodeLines(final String someCode);
	void setCodeLines(final String[] someCode);
	void setComment(final String aComment);
	void setDisplayName(final String aName);
	void setFinal(final boolean aBoolean);
	void setName(final char[] aName);
	void setPrivate(final boolean aBoolean);
	void setProtected(final boolean aBoolean);
	void setPublic(final boolean aBoolean);
	void setStatic(final boolean aBoolean);
	void setVisibility(final int aVisibility);
	void setWeight(final int aWeight);
	void startCloneSession();
	String toString();
	String toString(final int aTab);
	/**
	 * Implementation of the Extension Pattern
	 */
	void addExtension(final IConstituentExtension anExtension);
	IConstituentExtension getExtension(final char[] anExtensionName);
}