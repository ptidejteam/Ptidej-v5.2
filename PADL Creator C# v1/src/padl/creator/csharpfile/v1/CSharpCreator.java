/*
 * (c) Copyright 2009 Gerardo Cepeda Porras
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
package padl.creator.csharpfile.v1;

import java.io.File;
import java.io.IOException;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import padl.creator.csharpfile.v1.parser.CSharpLexer;
import padl.creator.csharpfile.v1.parser.CSharpParser;
import padl.csharp.kernel.ICSharpFactory;
import padl.kernel.ICodeLevelModel;
import padl.kernel.ICodeLevelModelCreator;
import padl.kernel.exception.CreationException;

/**
 * @author Gerardo Cepeda Porras
 * @since 2009/04/20
 *
 */
public class CSharpCreator implements ICodeLevelModelCreator {
	public static String CSHARP_PACKAGE_ID = "(C# default package)";

	// A list of files of the project to be parsed
	private String[] fileName;

	public CSharpCreator(final String[] afileName) {
		this.fileName = afileName;
	}

	public void create(final ICodeLevelModel aCodeLevelModel)
			throws CreationException {

		// Parse each file
		for (int i = 0; i < this.fileName.length; i++) {
			try {
				// Create a CSharpLexer that feeds from the file
				File file = new File("");
				CSharpLexer lexer =
					new CSharpLexer(new ANTLRFileStream(file.getAbsolutePath()
							+ File.separatorChar + this.fileName[i]));
				// Create a stream of tokens fed by the lexer
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				// Create a parser that feeds off the token stream
				CSharpParser parser = new CSharpParser(tokens);
				parser.setCodeLevelModel(aCodeLevelModel);
				parser.setFactory((ICSharpFactory) CSharpParser
					.getCodeLevelModel()
					.getFactory());
				// Begin parsing at rule compilationunit
				parser.compilationunit();
				//How to have a list of parameters?? do I need a reInitialize variable?
				//Maybe it's possible to handle the parser here in that case I need a
				//Factory here
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (RecognitionException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}

}
