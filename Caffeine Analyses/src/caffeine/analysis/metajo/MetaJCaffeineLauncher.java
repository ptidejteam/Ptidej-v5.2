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
package caffeine.analysis.metajo;

import caffeine.Caffeine;

/**
 * @version 0.2
 * @author	Yann-Gaël Guéhéneuc
 */
public class MetaJCaffeineLauncher {
	public static void main(final String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				// "../Caffeine Analyses/src/caffeine/analysis/metajo/Original.Multiple.trace",
				"Rules/AllEvents.pl",
				// "Rules/Composition.pl",
				"../Caffeine/cfparse.jar;../Caffeine/javassist.jar;../Caffeine;../MetaJ/bin",
				"fr.emn.metaj.Main",
				new String[] {
					"fr.emn.metaj.original.BaseInstance",
					"fr.emn.metaj.original.Instance",
					"fr.emn.metaj.original.BaseClass",
					"fr.emn.metaj.original.MethodList",
					"fr.emn.metaj.original.DataList" },
				new String[][] {
					new String[] {
						"fr.emn.metaj.original.BaseInstance",
						"fr.emn.metaj.original.Instance",
						"referent" },
					new String[] {
						"fr.emn.metaj.original.BaseClass",
						"fr.emn.metaj.original.DataList",
						"dataList" },
					new String[] {
						"fr.emn.metaj.original.BaseClass",
						"fr.emn.metaj.original.MethodList",
						"methodList" }
		});
	}
}