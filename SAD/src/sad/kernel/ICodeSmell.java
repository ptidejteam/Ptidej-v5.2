/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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

package sad.kernel;

import java.util.Set;

import padl.kernel.IClass;
import sad.codesmell.property.impl.ClassProperty;

/*
 * Modification :
 * ---------------------------------------------------------
 * Naouel Moha: 2008/07/17
 * we need to identify explicitely the name of the main class
 * suspected of having an antipattern 
 * 
 * Add the field : protected boolean mainCodeSmell;
 */

public interface ICodeSmell {
	String getName();

	String getDefinition();
	
	boolean isMainCodeSmell();

	void setMainCodeSmell(final boolean mainCodeSmell);

	ClassProperty getClassProperty();

	IClass getIClass();
	
	Set getIClasses();

	String getIClassID();

	String toString(final int count);
	
	String toString(final int count, final int compositeCount);

	/**
	 * Compares the specified codesmell with this codesmell for equality. We
	 * consider that a code smell is equal to another code smell if they involve
	 * the same class. we do not compare their other attributes.
	 */
	boolean equals(final ICodeSmell cs);

	/**
	 * Check if this code smell is contains in the specified set
	 * TODO : to remove!
	 */
	boolean contains(final Set csSet);

	boolean containsPartially(
		final String codeSmellName,
		final Set csSet);
}
