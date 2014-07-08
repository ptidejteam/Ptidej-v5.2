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
package caffeine.analysis.toplevelfields;

import caffeine.Caffeine;
import caffeine.Constants;

public final class NoTopLevelFieldsCaffeineLauncher {
	public static void main(final String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				"../Caffeine/Rules/AACTrace.pl",
				"../Caffeine/javassist.jar;../Caffeine/cfparse.jar;../Caffeine",
				"caffeine.analysis.toplevelfields.TopLevelFields",
				new String[] { "caffeine.analysis.toplevelfields.*", },
				Constants.GENERATE_FIELD_MODIFICATION_EVENT
					| Constants.GENERATE_FINALIZER_EXIT_EVENT,
				new String[][] {
					new String[] {
						"caffeine.analysis.toplevelfields.TopLevelFields",
						"caffeine.analysis.toplevelfields.ContainedObject",
						"object1" },
					new String[] {
						"caffeine.analysis.toplevelfields.TopLevelFields",
						"caffeine.analysis.toplevelfields.ContainedObject",
						"object2" }
		});
	}
}