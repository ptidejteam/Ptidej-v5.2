/*
 * (c) Copyright 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.creator.classfile.util;

import util.lang.Modifier;
import com.ibm.toad.cfparse.ClassFile;
import com.ibm.toad.cfparse.ConstantPool;
import com.ibm.toad.cfparse.MethodInfo;
import com.ibm.toad.cfparse.attributes.AttrInfoList;

/**
 * @version	0.1
 * @author 	Yann-Gaël Guéhéneuc
 */
public final class ExtendedMethodInfo {
	private final ConstantPool constantPool;
	private final char[] declaringClassFileName;
	private final AttrInfoList methodAttributes;
	private final char[] methodName;
	private final char[][] methodParameters;
	private final char[] methodReturnType;
	private final int methodVisibility;

	public ExtendedMethodInfo(
		final ClassFile declaringClassFile,
		final MethodInfo methodInfo) {

		this.constantPool = declaringClassFile.getCP();
		this.declaringClassFileName =
			declaringClassFile.getName().toCharArray();
		this.methodAttributes = methodInfo.getAttrs();
		this.methodName = methodInfo.getName().toCharArray();
		this.methodParameters = new char[methodInfo.getParams().length][];
		for (int i = 0; i < methodInfo.getParams().length; i++) {
			final String paramDisplayName = methodInfo.getParams()[i];
			final char[] paramName = paramDisplayName.toCharArray();
			this.methodParameters[i] = paramName;
		}
		this.methodReturnType = methodInfo.getReturnType().toCharArray();
		this.methodVisibility = methodInfo.getAccess();
	}
	public AttrInfoList getAttributes() {
		return this.methodAttributes;
	}
	public ConstantPool getDeclaringClassConstantPool() {
		return this.constantPool;
	}
	public char[] getDeclaringClassName() {
		return this.declaringClassFileName;
	}
	public char[] getName() {
		return this.methodName;
	}
	public char[][] getParameters() {
		return this.methodParameters;
	}
	public char[] getReturnType() {
		return this.methodReturnType;
	}
	public int getVisibility() {
		return this.methodVisibility;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(this.getVisibility()));
		buffer.append(' ');
		buffer.append(this.getReturnType());
		buffer.append(' ');
		buffer.append(this.getDeclaringClassName());
		buffer.append('.');
		buffer.append(this.getName());
		buffer.append("(...)");
		return buffer.toString();
	}
}
