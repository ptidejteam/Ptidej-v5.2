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

/*
 * Modification :
 * ---------------------------------------------------------
 * Naouel Moha: 2008/07/17
 * we need to identify explicitely the name of the main class
 * suspected of having an antipattern 
 */

package sad.kernel;

import java.util.Set;

public interface IDesignSmell {

	/** 
	 * Get the name of the antipattern
	 */
	String getName();

	/** 
	 * Set the name of the antipattern
	 */
	void setName(final String name);

	/** 
	 * Get the name of the main class of the antipattern
	 */
	String getMainClassName();

	/** 
	 * Set the name of the main class of the antipattern
	 */
	void setMainClassName(final String mainClassName);

	/**
	 * Get the definition of the antipattern
	 */
	String getDefinition();

	/**
	 * Set the definition of the antipattern
	 */
	void setDefinition(final String definition);

	/**
	 * Get the set of code smells that constitute the antipattern
	 */
	Set listOfCodeSmells();

	/**
	 * Check if this antipattern is contained in the specified set of antipatterns
	 */
	boolean contains(final Set setAnt);

	/**
	 * Compares the specified antipattern with this antipattern for equality
	 */
	boolean equals(final IDesignSmell anAntiPattern);

	/**
	 * Check if this antipattern is contained in the specified set of antipatterns
	 */
	boolean containsPartially(
		final String identicalCodeSmellName,
		final Set setAnt);

	/**
	 * Compares the specified antipattern with this antipattern for equality
	 */
	boolean equalsPartially(
		final String identicalCodeSmellName,
		final IDesignSmell ant);

	/**
	 * Return a string that describes the antipattern
	 */
	String toString();
}
