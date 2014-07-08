/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.creator.classfile.test.example;

import junit.framework.Assert;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.util.Util;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/01/23
 */
public class Ghost extends ClassFilePrimitive {
	private static IFirstClassEntity[] FirstClassEntities = null;
	private static IElement[] Elements = null;

	public Ghost(final String name) {
		super(name);
	}
	protected void setUp() throws CreationException,
			UnsupportedSourceModelException {

		if (Ghost.FirstClassEntities == null || Ghost.Elements == null) {
			final ICodeLevelModel codeLevelModel =
				ClassFilePrimitive.getFactory().createCodeLevelModel("Ghost");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/bin/padl/example/ghost/Simple.class" }));

			final IIdiomLevelModel idiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			Ghost.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(idiomLevelModel);

			Ghost.Elements =
				Util.getArrayOfElements(Ghost.FirstClassEntities[0]);
		}
	}
	public void testGhost() {
		/*
		 * public synchronized class padl.example.ghost.Simple extends java.lang.Object
		 * public ghost java.lang.Object;
		 * public ghost padl.example.ghost.A;
		 * public ghost java.lang.String;
		 * public ghost padl.example.ghost.B;
		 * public ghost java.lang.System;
		 * public ghost java.io.PrintStream;
		 */
		Assert.assertEquals(
			"Number of ghosts",
			9,
			Ghost.FirstClassEntities.length);
		Assert.assertEquals(
			"public synchronized class Simple extends Object",
			Ghost.FirstClassEntities[8].toString());
		Assert.assertEquals(
			"public ghost Object;",
			Ghost.FirstClassEntities[2].toString());
		Assert.assertEquals(
			"public ghost A;",
			Ghost.FirstClassEntities[5].toString());
		Assert.assertEquals(
			"public ghost String;",
			Ghost.FirstClassEntities[3].toString());
		Assert.assertEquals(
			"public ghost B;",
			Ghost.FirstClassEntities[6].toString());
		Assert.assertEquals(
			"public ghost Class;",
			Ghost.FirstClassEntities[1].toString());
		Assert.assertEquals(
			"public ghost System;",
			Ghost.FirstClassEntities[4].toString());
		Assert.assertEquals(
			"public ghost PrintStream;",
			Ghost.FirstClassEntities[0].toString());
	}
}
