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
package caffeine.analysis.cacao;

import caffeine.Caffeine;

/**
 * @version 0.2
 * @author	Yann-Gaël Guéhéneuc
 */
public class CacaoCaffeineLauncher {
	public static void main(String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				"../Caffeine/Rules/AllEvents.pl",
				// "Rules/Composition.pl",
				"../Caffeine/cfparse.jar;../Caffeine/javassist.jar;../Caffeine/bin;../Cacao",
				"fr.emn.cacao.CacaoSolver",
				new String[] { "fr.emn.cacao.Variable", "fr.emn.cacao.Set" },
				//	"fr.emn.cacao.Pair",
				//	"fr.emn.cacao.Value" },
				//	"fr.emn.cacao.Relation",
				//	"fr.emn.cacao.Variable" },
				new String[][] { new String[] {
				"fr.emn.cacao.Variable",
				"fr.emn.cacao.Set",
				"domain" }
				//	new String[] {
				//		"fr.emn.cacao.Pair",
				//		"fr.emn.cacao.Value",
				//		"fst" },
				//	new String[] {
				//		"fr.emn.cacao.Pair",
				//		"fr.emn.cacao.Value",
				//		"snd" },
				//	new String[] {
				//		"fr.emn.cacao.Relation",
				//		"fr.emn.cacao.Variable",
				//		"var1" },
				//	new String[] {
				//		"fr.emn.cacao.Relation",
				//		"fr.emn.cacao.Variable",
				//		"var2" }
		});
	}
}