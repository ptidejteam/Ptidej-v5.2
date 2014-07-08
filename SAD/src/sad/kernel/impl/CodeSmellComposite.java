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

package sad.kernel.impl;

/**
 * This class represents a code smell composite that is composed of a set
 * of others code smells. A code smell composite is necessarily composed
 * of code smells that deisgned the same class.
 * 
 * @author Naouel Moha
 * @version 1.0
 * @since 2005/12/01
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import padl.kernel.IClass;
import sad.codesmell.property.impl.ClassProperty;
import sad.kernel.ICodeSmell;
import util.io.ProxyConsole;

public class CodeSmellComposite implements ICodeSmell {

	final private ClassProperty targetEntity;

	final private Set setOfCodeSmellsOfComposite;

	final private static String nameGen = "CodeSmellComposite";

	final private static String definitionGen = "Code smell composed of other code smells";

	public CodeSmellComposite() {
		this.setOfCodeSmellsOfComposite = new HashSet();
		this.targetEntity = null;
	}

	public ClassProperty getClassProperty() {
		return this.targetEntity;
	}

	public String getDefinition() {
		return CodeSmellComposite.definitionGen;
	}

	public String getName() {
		return CodeSmellComposite.nameGen;
	}

	public Set getSetOfCodeSmellsOfGeneric() {
		return this.setOfCodeSmellsOfComposite;
	}

	public HashMap getTableOfValueMetrics() {
		return null;
	}

	public HashMap getTableOfthresholdValues() {
		return null;
	}

	/*
	 * This class corresponds to the main code smell of the composite
	 */
	public IClass getIClass() {
		IClass iclass = null;
		final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
		while (iter.hasNext()) {
			final ICodeSmell cs = (ICodeSmell) iter.next();
			if (cs.isMainCodeSmell()) {
				iclass = cs.getIClass();
			}
		}
		return iclass;
	}

	public String getIClassID() {
		String idClass = null;
		try {
			idClass = this.getIClass().getDisplayID();
		} catch (final NullPointerException e) {
			ProxyConsole.getInstance().warningOutput().println(
					"Composite Code Smell has no main class.");
		}
		return idClass;
	}

	/*
	 * The notion of main code smell is not applicable for the composite
	 */
	public boolean isMainCodeSmell() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMainCodeSmell(final boolean mainCodeSmell) {
		// TODO Auto-generated method stub
	}

	public Set getIClasses() {
		final Set setOfClasses = new HashSet();

		final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
		while (iter.hasNext()) {
			final ICodeSmell cs = (ICodeSmell) iter.next();
			setOfClasses.add(cs.getClassProperty().getIClass());
		}
		return setOfClasses;
	}

	public void addCodeSmell(final ICodeSmell codeSmell) {
		this.setOfCodeSmellsOfComposite.add(codeSmell);
	}

	public void removeCodeSmell(final ICodeSmell codeSmell) {
		this.setOfCodeSmellsOfComposite.remove(codeSmell);
	}

	public String toString(final int count) {
		return this.toString(count, 0);
	}

	public String toString(final int count, final int test) {
		String s = "\n#";
		int compositeCount = 0;
		final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
		while (iter.hasNext()) {
			final ICodeSmell cs = (ICodeSmell) iter.next();
			s += cs.toString(count, compositeCount);
			compositeCount++;
		}

		return s;
	}

	public String toString() {
		String s = "\n#";
		int count = 0;
		final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
		while (iter.hasNext()) {
			count++;
			final ICodeSmell cs = (ICodeSmell) iter.next();
			s += cs.toString(count);
		}
		return s;
	}

	public boolean equals(final ICodeSmell cs) {
		boolean isSame = false;

		if (cs instanceof CodeSmellComposite) {
			final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
			while (iter.hasNext()) {
				final ICodeSmell compositCs = (ICodeSmell) iter.next();

				// Seach for this CodeSmell
				if (compositCs
						.contains(((CodeSmellComposite) cs).setOfCodeSmellsOfComposite)) {
					isSame = true;
				} else {
					isSame = false;
					break;
				}
			}
		} else {
			final String csClassID = cs.getClassProperty().getIDClass();
			final String currentClassID = ((ICodeSmell) this.setOfCodeSmellsOfComposite
					.iterator().next()).getClassProperty().getIDClass();
			if (currentClassID.equals(csClassID)) {
				isSame = true;
			}
		}

		return isSame;
	}

	public boolean equalsPartialCodeSmell(final String codeSmellName,
			final ICodeSmell cs) {
		boolean isSame = false;

		final Iterator iter = this.setOfCodeSmellsOfComposite.iterator();
		while (iter.hasNext()) {
			final ICodeSmell compositCs = (ICodeSmell) iter.next();

			// Seach for this CodeSmell
			if (compositCs.containsPartially(codeSmellName,
					((CodeSmellComposite) cs).setOfCodeSmellsOfComposite)) {
				isSame = true;
			}
		}

		return isSame;
	}

	public boolean contains(final Set csSet) {
		boolean isSame = false;

		final Iterator iter = csSet.iterator();
		while (iter.hasNext() && !isSame) {
			final ICodeSmell cs = (ICodeSmell) iter.next();

			// Only need to check equality with CodeSmellComposite
			// since we are in a CodeSmellComposite.
			if (cs instanceof CodeSmellComposite) {
				isSame = this.equals(cs);
			}
		}
		return isSame;
	}

	public boolean containsPartially(final String codeSmellName, final Set csSet) {
		boolean isSame = false;

		final Iterator iter = csSet.iterator();
		while (iter.hasNext() && !isSame) {
			final ICodeSmell cs = (ICodeSmell) iter.next();

			// Only need to check equality with CodeSmellComposite
			// since we are in a CodeSmellComposite.
			if (cs instanceof CodeSmellComposite) {
				isSame = this.equalsPartialCodeSmell(codeSmellName, cs);
			}
		}
		return isSame;
	}
}
