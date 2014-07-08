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
package mendel.parser;

import mendel.model.IEntity;
import mendel.model.JInterfaceEntity;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;


public abstract class DelegateParser {
	/*
	 * Only for reuse purpose
	 * 
	 * LifeCycle (partial, see subclasses for complete)
	 * - parseEntity: currentEntity builder (straight lc)
	 */

	
	private JParser parent; // delegation-based inheritance 

	private IEntity currentEntity;
	
	public DelegateParser(JParser aParser) {
		this.parent = aParser;
	}

	/*
	 * Basic protocol
	 */
	
	public IEntity parseEntity(JavaClass aClass, boolean deepParse) {
		this.currentEntity = createEntity(aClass);
		for (String superInt : aClass.getInterfaceNames()) {
			JInterfaceEntity sint = (JInterfaceEntity) getEntityByName(superInt);
			this.currentEntity.addSuperInterface(sint);
			sint.addSubentity(this.currentEntity);
		}
		if( deepParse )
			visitMethods(aClass);
		return currentEntity();
	}
	
	public abstract IEntity createEntity(JavaClass aClass);
	
	// warning, currentEntity ref is updated AFTER call to createEntity
	protected IEntity currentEntity() {
		return this.currentEntity;
	}
	

	/*
	 * Helper methods
	 */
	
	public boolean isConstructor(Method m) {
		return m.getName().equals("<init>") || m.getName().equals("<clinit>");
	}

	public void visitMethods(JavaClass aClass) {
		for (Method method : aClass.getMethods())
			if( !isConstructor(method) )
				visitMethod(method);
	}
	
	public abstract void visitMethod(Method m);

	
	/*
	 * Delegation-based inheritance
	 */
	
	public String getFullSignature(Method method) {
		return this.parent.getFullSignature(method);
	}

	public String buildTypeSignature(Type[] argumentTypes, Type returnType) {
		return this.parent.buildTypeSignature(argumentTypes, returnType);
	}

	public IEntity getEntityByName(String className) {
		return this.parent.getEntityByName(className);
	}

	// could implement more delegation...
	
}
