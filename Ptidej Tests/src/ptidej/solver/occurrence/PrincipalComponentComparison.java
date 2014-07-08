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
package ptidej.solver.occurrence;

import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import junit.framework.Assert;
import junit.framework.TestCase;
import util.io.NullWriter;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/03/06
 */
public class PrincipalComponentComparison extends TestCase {
	private final SortedSet automaticallyAndManuallyFound = new TreeSet();
	private final SortedSet automaticallyNotManuallyFound = new TreeSet();
	private final SortedSet manuallyNotAutomaticallyFound = new TreeSet();

	public PrincipalComponentComparison(final String aTestName) {
		super(aTestName);
	}
	private void compareForBlob() {
		this
			.testImprovements(
				"Xerces v2.7.0 (dom2)",
				"../SAD Tests/rsc/OOPSLA - DetectionResults in Xerces v2.7.0 (dom2) for Blob.ini",
				"Blob",
				PrincipalComponents.Blob,
				"../SAD Tests/rsc/Antipatterns in Xerces v2.7.0.xml",
				new char[][] { "blob".toCharArray() });
	}
	private void compareForFunctionalDecomposition() {
		this
			.testImprovements(
				"Xerces v2.7.0 (dom2)",
				"../SAD Tests/rsc/DetectionResults in Xerces v2.7.0 (dom2) for FunctionalDecomposition.ini",
				"FunctionalDecomposition",
				PrincipalComponents.FunctionalDecomposition,
				"../SAD Tests/rsc/Antipatterns in Xerces v2.7.0.xml",
				new char[][] { "functionalDecomposition".toCharArray() });
	}
	private void compareForSpaghettiCode() {
		this
			.testImprovements(
				"Xerces v2.7.0 (dom2)",
				"../SAD Tests/rsc/OOPSLA - DetectionResults in Xerces v2.7.0 (dom2) for SpaghettiCode.ini",
				"SpaghettiCode",
				PrincipalComponents.SpaghettiCode,
				"../SAD Tests/rsc/Antipatterns in Xerces v2.7.0.xml",
				new char[][] { "spaghettiCode".toCharArray() });
	}
	private void compareForSwissArmyKnife() {
		this
			.testImprovements(
				"Xerces v2.7.0 (dom2)",
				"../SAD Tests/rsc/OOPSLA - DetectionResults in Xerces v2.7.0 (dom2) for SwissArmyKnife.ini",
				"SwissArmyKnife",
				PrincipalComponents.SwissArmyKnife,
				"../SAD Tests/rsc/Antipatterns in Xerces v2.7.0.xml",
				new char[][] { "swissArmyKnife".toCharArray() });
	}
	protected void setUp() {
	}
	private void testImprovements(
		final String aName,
		final String aResultFile,
		final String aMotifName,
		final char[][][] somePrincipalComponents,
		final String aXMLFile,
		final char[][] someXMLTags) {

		ProxyConsole.getInstance().setNormalOutput(new NullWriter());

		final ReducedOccurrence[] automaticallyFound =
			new PrincipalComponentAnalysis().buildReducedOccurrences(
				aName,
				aResultFile,
				aMotifName,
				somePrincipalComponents);
		final ReducedOccurrence[] manuallyFound =
			new PrincipalComponentExtraction().xmlExtraction(
				aXMLFile,
				someXMLTags);

		for (int i = 0; i < automaticallyFound.length; i++) {
			final ReducedOccurrence solution = automaticallyFound[i];
			if (Arrays.binarySearch(manuallyFound, solution) >= 0) {
				this.automaticallyAndManuallyFound.add(solution);
			}
			else {
				this.automaticallyNotManuallyFound.add(solution);
			}
		}
		for (int i = 0; i < manuallyFound.length; i++) {
			final ReducedOccurrence occurrence = manuallyFound[i];
			if (Arrays.binarySearch(automaticallyFound, occurrence) < 0) {
				this.manuallyNotAutomaticallyFound.add(occurrence);
			}
		}

		System.out.println();
		System.out.print(this.automaticallyAndManuallyFound.size());
		System.out.println(" solutions automatically and manually found");
		Iterator iterator = this.automaticallyAndManuallyFound.iterator();
		while (iterator.hasNext()) {
			final String solution = (String) iterator.next();
			System.out.print("Automatically and manually found : ");
			System.out.println(solution);
		}

		System.out.println();
		System.out.print(this.automaticallyNotManuallyFound.size());
		System.out.println(" solutions automatically, not manually found");
		iterator = this.automaticallyNotManuallyFound.iterator();
		while (iterator.hasNext()) {
			final String solution = (String) iterator.next();
			System.out.print("Automatically, not manually found: ");
			System.out.println(solution);
		}

		System.out.println();
		System.out.print(this.manuallyNotAutomaticallyFound.size());
		System.out.println(" solutions manually, not automatically found");
		iterator = this.manuallyNotAutomaticallyFound.iterator();
		while (iterator.hasNext()) {
			final String solution = (String) iterator.next();
			System.out.print("Manually, not automatically found: ");
			System.out.println(solution);
		}
	}
	public void ztestPrecisionForBlob() {
		this.compareForBlob();

		final int retrievedDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.automaticallyNotManuallyFound.size();
		final float precision =
			this.automaticallyAndManuallyFound.size()
					/ (float) retrievedDocuments * 100;

		System.err.print("Precision of ");
		System.err.print(precision);
		System.err.println("% for Blob");

		Assert.assertTrue(
			"Is precision of " + precision + " > 33%?",
			precision > 33);
	}
	public void testPrecisionForFunctionalDecomposition() {
		this.compareForFunctionalDecomposition();

		final int retrievedDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.automaticallyNotManuallyFound.size();
		final float precision =
			this.automaticallyAndManuallyFound.size()
					/ (float) retrievedDocuments * 100;

		System.err.print("Precision of ");
		System.err.print(precision);
		System.err.println("% for FunctionalDecomposition");

		Assert.assertTrue(
			"Is precision of " + precision + " > 33%?",
			precision > 33);
	}
	public void ztestPrecisionForSpaghettiCode() {
		this.compareForSpaghettiCode();

		final int retrievedDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.automaticallyNotManuallyFound.size();
		final float precision =
			this.automaticallyAndManuallyFound.size()
					/ (float) retrievedDocuments * 100;

		System.err.print("Precision of ");
		System.err.print(precision);
		System.err.println("% for SpaghettiCode ");

		Assert.assertTrue(
			"Is precision of " + precision + " > 33%?",
			precision > 33);
	}
	public void ztestPrecisionForSwissArmyKnife() {
		this.compareForSwissArmyKnife();

		final int retrievedDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.automaticallyNotManuallyFound.size();
		final float precision =
			this.automaticallyAndManuallyFound.size()
					/ (float) retrievedDocuments * 100;

		System.err.print("Precision of ");
		System.err.print(precision);
		System.err.println("% for SwissArmyKnife ");

		Assert.assertTrue(
			"Is precision of " + precision + " > 33%?",
			precision > 33);
	}
	public void ztestRecallForBlob() {
		this.compareForBlob();

		final int relevantDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.manuallyNotAutomaticallyFound.size();
		final float recall =
			this.automaticallyAndManuallyFound.size()
					/ (float) relevantDocuments * 100;

		System.err.print("Recall of ");
		System.err.print(recall);
		System.err.println("% for Blob");

		Assert.assertTrue("Is recall of " + recall + " > 90%?", recall > 90);
	}
	public void testRecallForFunctionalDecomposition() {
		this.compareForFunctionalDecomposition();

		final int relevantDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.manuallyNotAutomaticallyFound.size();
		final float recall =
			this.automaticallyAndManuallyFound.size()
					/ (float) relevantDocuments * 100;

		System.err.print("Recall of ");
		System.err.print(recall);
		System.err.println("% for FunctionalDecomposition");

		Assert.assertTrue("Is recall of " + recall + " > 90%?", recall > 90);
	}
	public void ztestRecallForSpaghettiCode() {
		this.compareForSpaghettiCode();

		final int relevantDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.manuallyNotAutomaticallyFound.size();
		final float recall =
			this.automaticallyAndManuallyFound.size()
					/ (float) relevantDocuments * 100;

		System.err.print("Recall of ");
		System.err.print(recall);
		System.err.println("% for SpaghettiCode");

		Assert.assertTrue("Is recall of " + recall + " > 90%?", recall > 90);
	}
	public void ztestRecallForSwissArmyKnife() {
		this.compareForSwissArmyKnife();

		final int relevantDocuments =
			this.automaticallyAndManuallyFound.size()
					+ this.manuallyNotAutomaticallyFound.size();
		final float recall =
			this.automaticallyAndManuallyFound.size()
					/ (float) relevantDocuments * 100;

		System.err.print("Recall of ");
		System.err.print(recall);
		System.err.println("% for SwissArmyKnife ");

		Assert.assertTrue("Is recall of " + recall + " > 90%?", recall > 90);
	}
}
