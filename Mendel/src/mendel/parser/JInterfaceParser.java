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

import mendel.model.JInterfaceEntity;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;


public class JInterfaceParser extends DelegateParser {

	public JInterfaceParser(JParser aParser) {
		super(aParser);
	}

	@Override
	protected JInterfaceEntity currentEntity() {
		return (JInterfaceEntity) super.currentEntity();
	}

	/*
	 * Visit
	 */

	@Override
	public JInterfaceEntity createEntity(JavaClass aClass) {
		return new JInterfaceEntity(aClass.getClassName());
	}

	@Override
	public void visitMethod(Method aMethod) {
		currentEntity().addLocalMethod(getFullSignature(aMethod));
	}

}
