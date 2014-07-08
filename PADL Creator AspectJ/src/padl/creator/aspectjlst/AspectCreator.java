/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
 * University of Montréal - ESSI.
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
package padl.creator.aspectjlst;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import org.aspectj.asm.AsmManager;
import org.aspectj.asm.IHierarchy;
import org.aspectj.asm.IRelationshipMap;
import padl.aspectj.kernel.exception.AspectCreationException;
import padl.creator.aspectjlst.util.AjcCompilerWrapper;
import padl.creator.aspectjlst.util.AspectWalker;
import padl.kernel.ICodeLevelModel;
import padl.kernel.ICodeLevelModelCreator;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/08/29
 */
public class AspectCreator implements ICodeLevelModelCreator {
	public static String ASPECT_PACKAGE_ID = "(aspect package)";

	private final AspectWalker walker;
	private final IHierarchy ihierarchy;
	private final IRelationshipMap map;

	//somefileNames[0] = lst file, somefileNames[1]...somefileNames[n] = jar libs
	public AspectCreator(final String[] somefileNames)
			throws AspectCreationException {
		super();

		File tmp = null;
		File lst = null;
		File ddir = null;
		File[] classpath = null;

		try {
			tmp = File.createTempFile("foo", ".txt");
			tmp.deleteOnExit();
			lst = new File(somefileNames[0]);

			if (somefileNames.length > 1) {
				classpath = new File[somefileNames.length - 1];

				for (int i = 1; i < somefileNames.length; i++) {
					classpath[i - 1] = new File(somefileNames[i]);
				}
			}
			ddir =
				new File(tmp.getParentFile().getAbsolutePath() + File.separator
						+ "AspectCreatorTemp");
			ddir.mkdir();
			ddir.deleteOnExit();
		}
		catch (final IOException ioe) {
			ioe.printStackTrace(System.err);
		}

		if (ddir == null) {
			throw new AspectCreationException(
				"System temp directory unreachable.");
		}

		if (!lst.exists() || !lst.isFile()) {
			throw new AspectCreationException("lst file does not exists.");
		}
		if (classpath != null) {
			for (int i = 0; i < classpath.length; i++) {
				if (!classpath[i].exists()) {
					throw new AspectCreationException("jar file "
							+ classpath[i] + "does not exists.");
				}
			}
		}
		// Compile project and force Model Gen

		final Vector ajcOptions = new Vector();

		// PHASE 0: call ajc
		ajcOptions.addElement("-noExit");
		ajcOptions.addElement("-XjavadocsInModel");
		ajcOptions.addElement("-d");
		ajcOptions.addElement(ddir.getAbsolutePath());
		//ajcOptions.addElement(lst.getAbsolutePath());
		//Adding classpath
		if (classpath != null) {
			ajcOptions.addElement("-classpath");
			String classpathToString = "";
			for (int i = 0; i < classpath.length; i++) {
				classpathToString += classpath[i].getAbsolutePath() + ";";
			}
			ajcOptions.addElement(classpathToString);
		}

		ajcOptions.addElement("-argfile");
		// Create the String[] for the option
		final String[] argsToCompiler = new String[ajcOptions.size() + 1];
		int i = 0;
		for (; i < ajcOptions.size(); i++) {
			argsToCompiler[i] = (String) ajcOptions.elementAt(i);
		}
		argsToCompiler[i] = lst.getAbsolutePath();

		AjcCompilerWrapper.main(argsToCompiler);
		if (AjcCompilerWrapper.hasErrors()) {
			throw new AspectCreationException("Ajc Compiler - Fail");
		}

		final AsmManager asm = AsmManager.getDefault();
		this.ihierarchy = asm.getHierarchy();
		this.map = asm.getRelationshipMap();
		this.walker = new AspectWalker(this.ihierarchy, this.map);
	}

	public void create(final ICodeLevelModel aCodeLevelModel) {
		// Yann 2005/07/12: Factory!
		// From now on, the Factory is set according to the
		// programming language to make sure the Creator has
		// access to the needed constituents.
		//	aCodeLevelModel.setFactory(AspectJFactory.getInstance());

		// Create and add Aspect Hierarchy to the IdiomLevelModel
		// Call the Walker
		this.walker.addCodeLevelModel(aCodeLevelModel);
		this.walker.process(this.ihierarchy.getRoot());
	}

	//For JUNIT TETS
	public HashMap getImportMap() {
		return this.walker.getImportMap();
	}
}