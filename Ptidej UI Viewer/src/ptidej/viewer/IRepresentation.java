/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
package ptidej.viewer;

import java.util.List;
import java.util.Set;
import padl.kernel.IAbstractModel;
import ptidej.solver.Occurrence;
import ptidej.ui.canvas.Canvas;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/07/17
 */
public interface IRepresentation {
	String TYPE_AOL_CODE = "AOLCodeModel";
	String TYPE_AOL_IDIOM = "AOLIdiomModel";
	String TYPE_ASPECTJ = "AspectJIdiomModel";
	String TYPE_CPP = "CppIdiomModel";
	String TYPE_ECLIPSE_JDT_PROJECT = "EclipseJDTProjectIdiomModel";
	String TYPE_JAVA_CLASSFILES = "JavaClassfilesIdiomModel";
	String TYPE_JAVA_JAVAFILES = "JavaSourcefilesIdiomModel";
	String TYPE_MSE = "MSE Idiom Model";

	void addOccurrences(final Occurrence[] someOccurrences);
	void addSourceFile(final String aFileType, final String aPath);
	void addSourceFileName(final String aFileType, final String aName);
	void clearOccurrences();
	Builder getBuilder();
	Canvas getCanvas();
	// IGraphLayout getGraphLayout();
	Occurrence[] getOccurrences();
	IPrimitiveFactory getPrimitiveFactory();
	List getSourceFiles(final String aFileType);
	List getSourceFilesNames(final String aFileType);
	Set getSourceFileTypes();
	LaidoutModelGraph getSourceGraph();
	IAbstractModel getSourceModel();
	// void setBuilder(final Builder aBuilder);
	// void setCanvas(final Canvas aCanvas);
	// void setGraphLayout(final IGraphLayout aGraphLayout);
	// void setPrimitiveFactory(final IPrimitiveFactory aPrimitiveFactory);
	// void setSourceGraph(final ModelGraph anAbstractModelGraph);
	// void setSourceModel(final IAbstractModel anAbstractModel);
}