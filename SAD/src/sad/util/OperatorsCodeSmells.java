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
package sad.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import sad.kernel.ICodeSmell;
import sad.kernel.impl.CodeSmellComposite;

/**
 * @author Naouel Moha
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2005/12/05
 *
 * The class OperatorsCodeSmells uses the Singleton pattern.
 * It allows operations on sets of code smells.
 * The class to describe a set is HashSet (from package java.util). 
 */
public final class OperatorsCodeSmells {
	/**
	 * UNIQUE_INSTANCE is the unique possible OperatorsCodeSmells object.
	 */
	private static OperatorsCodeSmells UniqueInstance;
	/**
	 *  Returns an unique instance of the OperatorsCodeSmells class
	 * @return the unique instance of a Singleton
	 */
	public static OperatorsCodeSmells getInstance() {
		if (OperatorsCodeSmells.UniqueInstance == null) {
			OperatorsCodeSmells.UniqueInstance = new OperatorsCodeSmells();
		}
		return OperatorsCodeSmells.UniqueInstance;
	}

	private OperatorsCodeSmells() {
	}

	/**
	 * Returns a set of code smells composite. A code smell composite
	 * is composed of an element of each of the specified sets that
	 * designed the same class. We perform here the union of all 
	 * specifed code smells.
	 * 
	 * @param setA : set of code smells
	 * @param setB : set of code smells
	 * @return a set of code smells composite
	 */
	public Set union(final Set setA, final Set setB) {
		final Set resultOfUnion = new HashSet();

		resultOfUnion.addAll(setA);
		resultOfUnion.addAll(setB);

		return resultOfUnion;
	}

	/**
	 * Returns a set of code smells composite. A code smell composite
	 * is composed of an element of each of the specified sets that
	 * designed the same class. We perform here the intersection of all 
	 * specifed code smells.
	 * 
	 * @param setA : set of code smells
	 * @param setB : set of code smells
	 * @return a set of code smells composite. If there is no intersection
	 * between the specified code smells, it returns a code smell composite
	 * empty
	 * @deprecated This method has to be removed because we could make the
	 * intersection of codeSmellComposite so the intersection should be done 
	 * on all 
	 */
	//	public Set intersection(final Set setA, final Set setB) {
	//
	//		// We construct a map<IClass, codeSmell> for the setA 
	//		final HashMap mapSetA = createMapClassCodeSmell(setA);
	//
	//		// We construct a map<IClass, codeSmell> for the setB 
	//		final HashMap mapSetB = createMapClassCodeSmell(setB);
	//
	//		// We do the intersection of the set of classes
	//		final Set resultOfIntersectionClasses = new HashSet(mapSetA.keySet());
	//		resultOfIntersectionClasses.retainAll(mapSetB.keySet());
	//
	//		final Set resultOfIntersection = new HashSet();
	//		if (!resultOfIntersectionClasses.isEmpty()) {
	//			// We get the code smells of the classes of the result of 
	//			// intersection of classes
	//
	//			final Iterator iter = resultOfIntersectionClasses.iterator();
	//			while (iter.hasNext()) {
	//				final CodeSmellComposite csc = new CodeSmellComposite();
	//				final IClass iClass = (IClass) iter.next();
	//				csc.addCodeSmell((ICodeSmell) mapSetA.get(iClass));
	//				csc.addCodeSmell((ICodeSmell) mapSetB.get(iClass));
	//				resultOfIntersection.add(csc);
	//			}
	//		} else {
	//			final CodeSmellComposite csc = new CodeSmellComposite();
	//			resultOfIntersection.add(csc);
	//		}
	//
	//		return resultOfIntersection;
	//	}

	public ICodeSmell intersectionCodeSmell(
		final ICodeSmell csA,
		final ICodeSmell csB) {
		if (csA.equals(csB)) {
			final CodeSmellComposite csc = new CodeSmellComposite();
			csc.addCodeSmell(csA);
			csc.addCodeSmell(csB);
			return csc;
		} else
			return null;
	}

	public Set intersection(final Set setA, final Set setB) {

		final Set resultOfIntersection = new HashSet();
		final Iterator iterSetA = setA.iterator();
		while (iterSetA.hasNext()) {
			final ICodeSmell csA = (ICodeSmell) iterSetA.next();
			final Iterator iterSetB = setB.iterator();
			while (iterSetB.hasNext()) {
				final ICodeSmell csB = (ICodeSmell) iterSetB.next();
				final ICodeSmell csInter = intersectionCodeSmell(csA, csB);
				if (csInter != null) {
					resultOfIntersection.add(csInter);
				}
			}
		}
		return resultOfIntersection;
	}

	/**
	 * Create a map<IClass, ICodeSmell> to memorize which class is related to
	 * which code smell.
	 * 
	 */
	//		private HashMap createMapClassCodeSmell(final Set setOfCS) {
	//			final HashMap mapClassCS = new HashMap();
	//			final Iterator iter = setOfCS.iterator();
	//			while (iter.hasNext()) {
	//				final ICodeSmell cs = (ICodeSmell) iter.next();
	//				mapClassCS.put(cs.getIClass(), cs);
	//			}
	//			return mapClassCS;
	//		}

	/**
	 * Returns the set of code smells setA without any
	 * code smells pointing on a class that has a code
	 * smell in setB.
	 * 
	 * @param setA : set of code smells
	 * @param setB : set of code smells
	 * @return setA \ setB
	 */
//	public Set substraction(final Set setA, final Set setB) {
//
//		// We construct a map<IClass, codeSmell> for the setA 
//		final HashMap mapSetA = createMapClassCodeSmell(setA);
//
//		// We construct a map<IClass, codeSmell> for the setB 
//		final HashMap mapSetB = createMapClassCodeSmell(setB);
//
//		// We do the substraction of the set of classes
//		final Set resultOfSubstractionClasses = new HashSet(mapSetA.keySet());
//		resultOfSubstractionClasses.removeAll(mapSetB.keySet());
//
//		final Set resultOfSubstraction = new HashSet();
//		final Iterator iterator = resultOfSubstractionClasses.iterator();
//		while (iterator.hasNext()) {
//			final IClass iClass = (IClass) iterator.next();
//			resultOfSubstraction.add(mapSetA.get(iClass));
//		}
//
//		return resultOfSubstraction;
//	}
}
