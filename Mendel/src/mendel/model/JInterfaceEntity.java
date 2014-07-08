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
package mendel.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;


public class JInterfaceEntity extends JAbstractEntity {
	
	private List<IEntity> subEntities;

	public JInterfaceEntity(String entityName) {
		super(entityName);
		this.subEntities = new Vector<IEntity>();
	}
	
	// TODO: what about java.lang.Object? Default super if 
	
	/*
	 * Basic accessors
	 */
	
//	public ClassEntity flatten() {
//		boolean previousFlag = recursiveLookup(true);
//		ClassEntity flattenEntity = new ClassEntity(this.entityName, null);
//		flattenEntity.localMethods = new HashSet<String>(getAllMethods());
//		recursiveLookup(previousFlag);
//		return flattenEntity;
//	}
//	
////	public EntityInterface flatten() {
////		return flatten(inheritanceDepth());
////	}
//	
//	public ClassEntity flatten(int depth) {
//		ClassEntity flattenEntity = new ClassEntity(this.entityName, null);
//		flattenEntity.localMethods = ( depth==0 ) ?
//			new HashSet<String>(getLocalMethods()) :
//			SetOps.union(getLocalMethods(), 
//						 this.superEntity.flatten(depth-1).getLocalMethods());
//		return flattenEntity;
//	}
	
	/*
	 * Accessor to groups and categories
	 */
	
	public Set<String> getSuperMethods() {
		if( isRootInterface() ) {
			return new HashSet<String>();
		} else {
			if (recursiveLookup())
				return getAllMethodsFromSuperInterfaces();
			else
				return getLocalMethodsFromSuperInterfaces();
		}
	}

	public Set<String> getAllMethods() {
		if( !this.isRootInterface() ) {
				return SetOps.union(getLocalMethods(),
									getSuperMethods());
		} else return getLocalMethods();
	}

	public Set<String> getAbstractMethods() {
		return getLocalMethods();
	}
	
	public Set<String> getDuplicateMethods() {
		return getSharedMethods();
	}

	
	public StringBuffer toStringBuffer() {
		StringBuffer buffer = super.toStringBuffer();
		buffer.insert(0, "JInterface");
	
		buffer.append( isRootInterface() ?
			"   ROOT INTERFACE\n" :
			"   Super Interfaces: " + getSuperInterfacesNames() + "\n");
		
		// TODO: remove duplication. Need some factorisation of Group/Category accessor
		appendMethodSet(buffer, "New Methods", getNewMethods());
		appendMethodSet(buffer, "Duplicate Methods", getDuplicateMethods());
		appendMethodSet(buffer, "Abstract Methods", getAbstractMethods());
		return buffer;
	}
	
	public String toString(final boolean recursive) {
		StringBuffer buffer = toStringBuffer();
		if( !isRootInterface() ) {
			if (recursive) {
				buffer.append("---------------------------------------\n");
				new SuperFunctor<StringBuffer>(buffer) {
					public void compute(JInterfaceEntity entity, StringBuffer buf) {
						buf.append("---------------\n");
						buf.append(entity.toString(recursive));
					}
				};
			}
			else {
				buffer.append("***\n");
				appendMethodSet(buffer, "Inherited Methods (direct)", getInheritedMethods());
			}
		}
		return buffer.toString();
	}

	public void addSubentity(IEntity entity) {
		this.subEntities.add(entity);
	}

	public List<IEntity> getSubentities() {
		return new Vector<IEntity>(this.subEntities);
	}

	public boolean hasSubentities() {
		return !this.subEntities.isEmpty();
	}
	
}
