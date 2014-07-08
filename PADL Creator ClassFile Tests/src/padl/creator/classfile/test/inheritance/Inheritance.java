/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.creator.classfile.test.inheritance;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;

public final class Inheritance extends ClassFilePrimitive {
	private ICodeLevelModel codeLevelModel;

	public Inheritance(String name) {
		super(name);
	}
	protected void setUp() throws CreationException {
		this.codeLevelModel =
			Factory.getInstance().createCodeLevelModel("Inheritance");

		this.codeLevelModel
			.create(new CompleteClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/Inheritance/jdiui.jar" }));
	}

	public void testFieldAccess() {
		final IFirstClassEntity entity =
			(IFirstClassEntity) this.codeLevelModel
				.getTopLevelEntityFromID("org.eclipse.jdt.internal.debug.ui.snippeteditor.ScrapbookMain");
		Assert.assertTrue(entity.getIteratorOnInheritedEntities().hasNext());
	}
}
