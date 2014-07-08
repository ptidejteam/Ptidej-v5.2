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
package padl.pagerank.helper;

import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import padl.pagerank.PageRankRankingGenerator;
import padl.pagerank.utils.InputDataGeneratorWith9Relations;
import padl.visitor.IGenerator;

public class PageRankCallerWithParameters {
	public static void main(final String[] args) {
		final IGenerator generator =
			new InputDataGeneratorWith9Relations(false, true);

		final IIdiomLevelModel idiomLevelModel =
			ModelGenerator.generateModelFromClassFilesDirectory(args[0]);

		PageRankRankingGenerator.getInstance().generateModel(
			idiomLevelModel,
			args[1],
			generator);
		generator.reset();
	}
}
