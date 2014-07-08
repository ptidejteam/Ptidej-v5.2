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
package padl.kernel.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import padl.kernel.IInterfaceImplementer;
import padl.kernel.exception.ModelDeclarationException;
import util.multilingual.MultilingualManager;

// David 2013/09/06
// Moved implements IInterface from Interface class to AbstractInterface class
// since some methods were already here.
// Yann 2013/09/26
// It is better to distinguish typing from implementation... this class
// is for reuse of implementation code, while Interface is actually the 
// implementing type.
class AbstractInterface extends AbstractClass {
	private static final long serialVersionUID = -8012857785737513296L;
	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final List listOfImplementingClasses = new ArrayList();
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private List listOfImplementingClasses = new ArrayList();

	public AbstractInterface(final char[] anID, final char[] aName) {
		super(anID, aName);
	}
	/**
	* This method add a new entity to the list of entities
	* inheriting from this entity.
	* 
	* @param anEntity
	*/
	// David 2013/09/06
	// Made method public
	protected void addImplementingClass(final IInterfaceImplementer aClass) {
		if (this.listOfImplementingClasses.contains(aClass)) {
			// Yann 2010/06/27: MemberClasses!
			// Now that I deal with MemberClasses, it is possible
			// that the "same" MemberClass is defined in two
			// classes, hence it would already be implementing the
			// current interface...
			throw new ModelDeclarationException(MultilingualManager.getString(
				"ALREADY_INHERITED",
				FirstClassEntity.class,
				new Object[] { aClass.getDisplayID(), this.getDisplayID() }));
		}
		this.listOfImplementingClasses.add(aClass);
	}

	// David 2013/09/06
	// Added method removeImplementingClass
	protected void removeImplementingClass(final IInterfaceImplementer aClass) {
		this.listOfImplementingClasses.remove(aClass);
	}

	public Iterator getIteratorOnImplementingClasses() {
		return this.listOfImplementingClasses.iterator();
	}
	public int getNumberOfInheritingEntities() {
		return this.listOfImplementingClasses.size()
				+ super.getNumberOfInheritingEntities();
	}
}
