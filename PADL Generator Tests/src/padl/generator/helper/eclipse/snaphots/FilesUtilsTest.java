/* (c) Copyright 2011 and following years, Aminata SABANÉ,
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
package padl.generator.helper.eclipse.snaphots;

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.utils.FilesUtils;

public class FilesUtilsTest extends TestCase {

	public FilesUtilsTest(final String name) {
		super(name);
	}

	public void testCountJavaFiles() {
		String pathDir = "./rsc/project1/";
		long expectedNumber = 19;
		long actualNumber = FilesUtils.countJavaFiles(new File(pathDir));

		Assert.assertEquals(expectedNumber, actualNumber);
	}

	public void testCleanDir() {
		String pathDir = "./rsc/project2/";
		long expectedNumber = 19;
		FilesUtils.filterDir(new File(pathDir));
		long actualNumber = FilesUtils.countJavaFiles(new File(pathDir));

		Assert.assertEquals(expectedNumber, actualNumber);
	}

}
