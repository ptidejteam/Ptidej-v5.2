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
package caffeine.analysis.metajr;

import caffeine.Caffeine;

/**
 * @version 0.2
 * @author	Yann-Gaël Guéhéneuc
 */
public class MetaJRCaffeineLauncher {
	public static void main(final String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				//"../Caffeine Analyses/src/caffeine/analysis/metajr/Multiple.trace",
				"Rules/AllEvents.pl",
				// "Rules/Composition.pl",
				"../Caffeine/cfparse.jar;../Caffeine/javassist.jar;../Caffeine;../MetaJ-R/bin",
				"fr.emn.metaj.reflective.Main",
				new String[] {
					"fr.emn.metaj.reflective.BaseInstance",
					"fr.emn.metaj.reflective.Instance",
					"fr.emn.metaj.reflective.BaseClass",
					"fr.emn.metaj.reflective.MethodList",
					"fr.emn.metaj.reflective.DataList" },
				new String[][] {
					new String[] {
						"fr.emn.metaj.reflective.BaseInstance",
						"fr.emn.metaj.reflective.Instance",
						"referent" },
					new String[] {
						"fr.emn.metaj.reflective.BaseClass",
						"fr.emn.metaj.reflective.DataList",
						"dataList" },
					new String[] {
						"fr.emn.metaj.reflective.BaseClass",
						"fr.emn.metaj.reflective.MethodList",
						"methodList" }
		});
	}
}