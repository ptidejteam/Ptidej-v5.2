/*
 * (c) Copyright 2002-2003 Yann-Gaël Guéhéneuc,
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
package caffeine.analysis.junit.money;

import caffeine.Caffeine;
import caffeine.Constants;

/**
 * @version 0.1
 * @author	Yann-Gaël Guéhéneuc
 */
public final class CL_TestTreeModel {
	public static void main(final String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				"Rules/AllStaticEvents.pl",
				"../Caffeine/cfparse.jar;../Caffeine/javassist.jar;../Caffeine;../JUnit",
				"junit.samples.money.MoneyTest",
				new String[] { "junit.swingui.TestTreeModel" },
				Constants.GENERATE_METHOD_ENTRY_EVENT
					| Constants.GENERATE_METHOD_EXIT_EVENT
					| Constants.GENERATE_FIELD_ACCESS_EVENT
					| Constants.GENERATE_FIELD_MODIFICATION_EVENT,
				new String[][] {
					new String[] {
						"junit.swingui.TestTreeModel",
						"java.util.Hashtable",
						"fFailures" },
					new String[] {
						"junit.swingui.TestTreeModel",
						"java.util.Hashtable",
						"fErrors" },
					new String[] {
						"junit.swingui.TestTreeModel",
						"java.util.Hashtable",
						"fRunTests" }
		});
	}
}