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

import com.ibm.toad.cfparse.ClassFile;
import com.ibm.toad.cfparse.FieldInfo;

/**
 * @version	0.1
 * @author 	Yann-Gaël Guéhéneuc
 */
public final class ExtendedFieldInfo {
	private final char[] declaringClassFileName;
	private final char[] fieldName;
	private final char[] fieldType;
	private final int fieldVisibility;

	public ExtendedFieldInfo(
		final ClassFile declaringClassFile,
		final FieldInfo fieldInfo) {

		this.declaringClassFileName =
			declaringClassFile.getName().toCharArray();
		this.fieldName = fieldInfo.getName().toCharArray();
		this.fieldType = fieldInfo.getType().toCharArray();
		this.fieldVisibility = fieldInfo.getAccess();
	}
	public char[] getDeclaringClassName() {
		return this.declaringClassFileName;
	}
	public char[] getName() {
		return this.fieldName;
	}
	public char[] getType() {
		return this.fieldType;
	}
	public int getVisibility() {
		return this.fieldVisibility;
	}
}
