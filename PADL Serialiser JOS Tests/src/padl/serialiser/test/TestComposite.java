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
package padl.serialiser.test;

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.motif.IDesignMotifModel;
import padl.motif.repository.Composite;
import padl.serialiser.JOSSerialiser;
import padl.serialiser.util.TransientFieldManager;
import padl.test.helper.ModelComparator;

public class TestComposite extends TestCase {
	private static IDesignMotifModel CompositePattern;
	private static IDesignMotifModel SerialisedCompositePattern;
	private static String SerialisedFileName;

	public TestComposite(String name) {
		super(name);
	}
	protected void setUp() {
		if (TestComposite.CompositePattern == null) {
			TestComposite.CompositePattern = new Composite();
			TestComposite.SerialisedFileName =
				JOSSerialiser.getInstance().serialiseWithAutomaticNaming(
					TestComposite.CompositePattern,
					"../PADL Serialiser JOS Tests/rsc/");
			TestComposite.SerialisedCompositePattern =
				(IDesignMotifModel) JOSSerialiser.getInstance().deserialise(
					TestComposite.SerialisedFileName);
		}
	}
	protected void tearDown() {
		final File serialisedFile = new File(TestComposite.SerialisedFileName);
		serialisedFile.delete();

		final File serialisedHelperFile =
			new File(TestComposite.SerialisedFileName
					+ TransientFieldManager.METHOD_INVOCATION_EXTENSION);
		serialisedHelperFile.delete();
	}
	public void testNames() {
		Assert.assertEquals(
			TestComposite.CompositePattern.getName(),
			TestComposite.SerialisedCompositePattern.getName());
	}
	public void testComparator() {
		TestComposite.CompositePattern.walk(new ModelComparator(
			TestComposite.SerialisedCompositePattern));
	}
}
