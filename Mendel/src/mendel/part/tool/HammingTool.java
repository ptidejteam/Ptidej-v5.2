/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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
package mendel.part.tool;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import mendel.family.BitInterface;
import mendel.family.Family;
import mendel.family.Prototype;
import mendel.model.JClassEntity;
import mendel.part.AbstractPart;

/**
 * 
 * Input: Family
 * Output: Family
 * 
 * @author deniersi
 *
 */
public class HammingTool extends AbstractPart {

	public Object compute(Object entry) {
		Family family = ((Family) entry);
		Set<String> keys = family.dataKeys();
		for (String key : keys) {
			if( key.startsWith("prototype ")) {
				Prototype prototype = family.getPrototype(key);
				computeHammingData(family.parent(), prototype, true);				
			}
		}
		return family;			
	}
	
	public Prototype computeHammingData(JClassEntity parent, Prototype prototype) {
		return computeHammingData(parent, prototype, false);
	}

	/**
	 * @param classEntity 
	 * @param prototype
	 */
	public Prototype computeHammingData(JClassEntity parent, Prototype prototype, boolean distances) {
		List<Set<String>> interfaces = getChildrenInterfaces(parent);
		// This deals with the case where the prototype declares a method non-declared by any
		// of the direct children (like it happens in recursive mode with methods from grandchildren)
		interfaces.add(prototype.getInterface());
		List<String> sigVector = computeSigVector(interfaces);
		BitInterface protoBits = new BitInterface(prototype.getInterface(), sigVector);
		prototype.setBitInterface(protoBits);
		if( distances ) {
			Vector<BitInterface> childrenBI = computeBitInterfaces(interfaces, sigVector);
			protoBits.hammingDistances(childrenBI);			
		}
		return prototype;
		
//		BitInterface[] _vectors = vectors.toArray(new BitInterface[0]);
//		int[] distances = new int[_vectors.length];
//			BitInterface prototype = new BitInterface(sigVector).commonBits(_vectors);
//			prototype.optimizeHamming(_vectors, distances);			
	}
	
	public List<Set<String>> getChildrenInterfaces(JClassEntity parent) {
		Vector<Set<String>> interfaces = new Vector<Set<String>>();
		for (JClassEntity child : parent.getChildren()) {
			interfaces.add(child.getLocalMethods());
		}
		return interfaces;
	}

	/*
	 *  create a sorted vector of all methods in children.
	 */
	public List<String> computeSigVector(List<Set<String>> interfaces) {
		SortedSet<String> _sigVector = new TreeSet<String>();
		for (Set<String> itface : interfaces) {
			_sigVector.addAll(itface);
		}
		List<String> sigVector = new Vector<String>();
		sigVector.addAll(_sigVector);
		return sigVector;
	}

	/*
	 * compute a bit vector for each child interface according to a given sig vector.
	 */
	public Vector<BitInterface> computeBitInterfaces(List<Set<String>> interfaces, List<String> sigVector) {
		Vector<BitInterface> vectors = new Vector<BitInterface>();
		for (Set<String> itface : interfaces) {
			vectors.add(new BitInterface(itface, sigVector));
		}
		return vectors;
	}

}
