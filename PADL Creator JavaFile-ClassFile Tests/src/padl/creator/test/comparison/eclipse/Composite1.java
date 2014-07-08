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
package padl.creator.test.comparison.eclipse;

import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.javafile.javac.JavaFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.test.helper.ModelComparator;

public class Composite1 extends TestCase {
	private static final String SRC_PATH =
		"../PADL Creator JavaFile-ClassFile Tests/src/padl/example/composite1/";
	private static final String BIN_PATH =
		"../PADL Creator JavaFile-ClassFile Tests/bin/padl/example/composite1/";
	//	private static final List OPTIONS = Arrays.asList(new String[] {
	//			"-classpath",
	//			System.getProperty("java.class.path", ".") + ":"
	//					+ Composite1.SRC_PATH, "-d", Composite1.SRC_PATH,
	//			"-source", "6" });
	private static ICodeLevelModel JavaFileCodeLevelModel;
	private static ICodeLevelModel ClassFileCodeLevelModel;

	public Composite1(final String name) {
		super(name);
	}
	protected void setUp() {
		if (Composite1.JavaFileCodeLevelModel == null) {
			try {
				Composite1.JavaFileCodeLevelModel =
					Factory.getInstance().createCodeLevelModel("");
				Composite1.JavaFileCodeLevelModel.create(new JavaFileCreator(
					Composite1.SRC_PATH));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}
		}
		if (Composite1.ClassFileCodeLevelModel == null) {
			try {
				Composite1.ClassFileCodeLevelModel =
					Factory.getInstance().createCodeLevelModel("");
				Composite1.ClassFileCodeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { Composite1.BIN_PATH }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}
		}
	}
	public void testCompare() {
		Composite1.ClassFileCodeLevelModel.walk(new ModelComparator(
			Composite1.JavaFileCodeLevelModel));
	}
}
