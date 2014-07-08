/* (c) Copyright 2011 and following years, Aminata SABANÉ,
 * ÉCole Polytechnique de Montréal.
 * 
 * @author: Aminata SABANÉ
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
package padl.creator.javafile.eclipse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import padl.creator.javafile.eclipse.astVisitors.VisitorFirstParsing;
import padl.creator.javafile.eclipse.astVisitors.VisitorSecondParsing;
import padl.creator.javafile.eclipse.astVisitors.VisitorThirdParsing;
import padl.kernel.ICodeLevelModel;
import padl.kernel.ICodeLevelModelCreator;
import padl.kernel.exception.CreationException;
import parser.input.SourceInputsHolder;
import parser.input.impl.FileSystemJavaProject;
import parser.input.impl.FilesAndDirectoriesJavaProject;
import parser.wrapper.ExtendedASTVisitor;
import parser.wrapper.JavaParser;
import util.io.ProxyConsole;

public class CompleteJavaFileCreator implements ICodeLevelModelCreator {
	private JavaParser eclipseSourceCodeParser;
	private SourceInputsHolder javaProject;

	/**
	 * Constructor for creating a model from a source code
	 * @param aSourcePathEntry
	 * @param aClassPathEntry
	 */
	public CompleteJavaFileCreator(
		final String aSourcePathEntry,
		final String aClasspathEntry) {

		//the folder of the source code to analyse well organized like a project
		//final String sourcePathEntry = "./rsc/src/";
		//how to throw an exception here
		if (!new File(aSourcePathEntry).exists()) {
			throw new RuntimeException(new FileNotFoundException(
				"The source does not exist " + aSourcePathEntry));
		}
		final String[] sourcePathEntries = new String[] { aSourcePathEntry };

		//using librairies?

		final String[] classpathEntries = new String[] { aClasspathEntry };

		try {
			this.javaProject =
				new FileSystemJavaProject(
					Arrays.asList(classpathEntries),
					Arrays.asList(sourcePathEntries));

			this.eclipseSourceCodeParser = new JavaParser(this.javaProject);
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}

	public CompleteJavaFileCreator(
		final String aSourcePathEntry,
		final String aClasspathEntry,
		final String[] someSourceFiles) {

		this(
			new String[] { aSourcePathEntry },
			new String[] { aClasspathEntry },
			someSourceFiles);
	}

	public CompleteJavaFileCreator(
		final String[] someSourcePathEntries,
		final String[] someClasspathEntries,
		final String[] someSourceFiles) {

		for (final String path : someSourcePathEntries) {
			if (!new File(path).exists()) {
				throw new RuntimeException(new FileNotFoundException(
					"The source does not exist " + path));
			}
		}

		try {
			this.javaProject =
				new FilesAndDirectoriesJavaProject(
					Arrays.asList(someClasspathEntries),
					Arrays.asList(someSourcePathEntries),
					Arrays.asList(someSourceFiles));

			this.eclipseSourceCodeParser = new JavaParser(this.javaProject);
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}

	public void applyAnnotator(final ExtendedASTVisitor anAnnotator) {
		this.eclipseSourceCodeParser.parse(anAnnotator);
	}

	@Override
	public void create(final ICodeLevelModel aCodeLevelModel)
			throws CreationException {

		this.createModelFromSource(aCodeLevelModel, this.javaProject);

	}

	private void createModelFromSource(
		final ICodeLevelModel aCodeLevelModel,
		final SourceInputsHolder javaProject) {

		final VisitorFirstParsing firstParseVisitor =
			new VisitorFirstParsing(aCodeLevelModel);
		this.eclipseSourceCodeParser.parse(firstParseVisitor);

		//second visit: inheritance, methods, fields added to the model
		final VisitorSecondParsing secondParseVisitor =
			new VisitorSecondParsing(aCodeLevelModel);

		this.eclipseSourceCodeParser.parse(secondParseVisitor);

		//third visit : method invocations added to the model
		final VisitorThirdParsing thirdParseVisitor =
			new VisitorThirdParsing(aCodeLevelModel);
		this.eclipseSourceCodeParser.parse(thirdParseVisitor);

		// TODO: Make sure that wherever we call this Creator, 
		// then we also call the following annotators subsequently!
		//	final LOCModelAnnotator locAnnotator =
		//		new LOCModelAnnotator(aCodeLevelModel);
		//
		//	eclipseSourceCodeParser.parse(locAnnotator);
		//
		//	final ConditionalModelAnnotator conditionalAnnotator =
		//		new ConditionalModelAnnotator(aCodeLevelModel);
		//
		//	eclipseSourceCodeParser.parse(conditionalAnnotator);
	}
}
