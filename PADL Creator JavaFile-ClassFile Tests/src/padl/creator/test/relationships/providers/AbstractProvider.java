/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.test.relationships.providers;

import java.io.File;

abstract class AbstractProvider {
	private static final String BIN_FOLDER =
		"../PADL Creator JavaFile-ClassFile Tests/bin/";
	private static final String BYTE_CODE_EXTENSION = ".class";
	private static final String JAVA_CODE_EXTENSION = ".java";
	private static final String SRC_FOLDER =
		"../PADL Creator JavaFile-ClassFile Tests/src/";
	private static final String TEST_CLASS_PREFIX = "Test";

	public abstract String getHelperClassName();

	private String getPathTo(
		final String aTargetClassName,
		final String aFolder,
		final String aTestSuffix,
		final String anExtension) {

		final String testClassName =
			aFolder
					+ aTargetClassName.replaceAll(
						ITestProvider.PROVIDER_CLASS_PREFIX,
						aTestSuffix).replace('.', '/') + anExtension;
		final File pathTestClass = new File(testClassName);
		if (!pathTestClass.exists()) {
			throw new RuntimeException("Cannot find class: " + testClassName);
		}
		return pathTestClass.getAbsolutePath();
	}
	public String getPathToByteCodeOfHelperClass() {
		return this.getPathTo(
			this.getHelperClassName(),
			AbstractProvider.BIN_FOLDER,
			AbstractProvider.TEST_CLASS_PREFIX,
			AbstractProvider.BYTE_CODE_EXTENSION);
	}
	public String getPathToByteCodeOfTestClass() {
		return this.getPathTo(
			this.getTestFileName(),
			AbstractProvider.BIN_FOLDER,
			AbstractProvider.TEST_CLASS_PREFIX,
			AbstractProvider.BYTE_CODE_EXTENSION);
	}
	public String getPathToJavaCodeOfHelperClass() {
		return this.getPathTo(
			this.getHelperClassName(),
			AbstractProvider.SRC_FOLDER,
			ITestProvider.PROVIDER_CLASS_PREFIX,
			AbstractProvider.JAVA_CODE_EXTENSION);
	}
	public String getPathToJavaCodeOfTestClass() {
		return this.getPathTo(
			this.getTestFileName(),
			AbstractProvider.SRC_FOLDER,
			ITestProvider.PROVIDER_CLASS_PREFIX,
			AbstractProvider.JAVA_CODE_EXTENSION);
	}
	public String getTestClassName() {
		return this.getClass().getName().replaceAll(
			ITestProvider.PROVIDER_CLASS_PREFIX,
			AbstractProvider.TEST_CLASS_PREFIX);
	}
	private String getTestFileName() {
		return this.getClass().getName();
	}
}
