/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.viewer.extension.repository.callgraph;

import ptidej.viewer.extension.repository.callgraph.model.Call;

import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.MethodGen;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/10/04
 */
public class CreatorOfMethod extends EmptyVisitor {
	private JavaClass javaClass;
	private ptidej.viewer.extension.repository.callgraph.model.Method method;
	private String methodName;

	public CreatorOfMethod(final String aMethodName) {
		this.methodName = aMethodName;
	}
	public void visitJavaClass(final JavaClass aJavaClass) {
		this.javaClass = aJavaClass;
		final Method[] methods = aJavaClass.getMethods();
		boolean found = false;
		for (int i = 0; i < methods.length; i++) {
			final String methodName =
				CallGraphGenerator.createMethodName(
					methods[i].getReturnType(),
					methods[i].getName(),
					methods[i].getArgumentTypes());
			if (methodName.equals(this.methodName)) {
				methods[i].accept(this);
				found = true;
				break;
			}
		}
		if (!found) {
			// If I cannot found the method, 
			// I look into the superclasses.
			try {
				this.visitJavaClass(aJavaClass.getSuperClasses()[0]);
			}
			catch (final ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void visitMethod(final Method aMethod) {
		this.method =
			CallGraphGenerator.createMethod(
				this.javaClass.getClassName(),
				this.javaClass.isClass(),
				aMethod.getReturnType(),
				aMethod.getName(),
				aMethod.getArgumentTypes());

		final MethodGen methodGen =
			new MethodGen(
				aMethod,
				this.javaClass.getClassName(),
				new ConstantPoolGen(this.javaClass.getConstantPool()));
		final CreatorOfCalls callCreator = new CreatorOfCalls(methodGen);
		callCreator.start();
		for (int i = 0; i < callCreator.getCalls().length; i++) {
			final Call call = callCreator.getCalls()[i];
			this.method.addCall(call);
		}
	}
	public ptidej
		.viewer
		.extension
		.repository
		.callgraph
		.model
		.Method getMethod() {

		return this.method;
	}
}
