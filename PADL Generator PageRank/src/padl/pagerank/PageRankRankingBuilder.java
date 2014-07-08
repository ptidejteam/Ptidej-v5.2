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
package padl.pagerank;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import util.io.ProxyConsole;

public class PageRankRankingBuilder {
	private static PageRankRankingBuilder UniqueInstance;
	public static PageRankRankingBuilder getInstance() {
		if (PageRankRankingBuilder.UniqueInstance == null) {
			PageRankRankingBuilder.UniqueInstance =
				new PageRankRankingBuilder();
		}
		return PageRankRankingBuilder.UniqueInstance;
	}
	private PageRankRankingBuilder() {
	}
	public Ranking[] getRankings(final FileReader aPageRankResultsReader) {
		try {
			final LineNumberReader pageRankResultsReader =
				new LineNumberReader(aPageRankResultsReader);

			// Skip the header.
			pageRankResultsReader.readLine();

			String line;
			final Set setOfRankings = new HashSet();
			while ((line = pageRankResultsReader.readLine()) != null) {
				final StringTokenizer tokenizer =
					new StringTokenizer(line, ";");
				final int count = Integer.parseInt(tokenizer.nextToken());
				final String entityName = tokenizer.nextToken();
				final float pageRankValue =
					Float.parseFloat(tokenizer.nextToken());
				final int rank = Integer.parseInt(tokenizer.nextToken());
				final int allEdges = Integer.parseInt(tokenizer.nextToken());
				final int incomingEdges =
					Integer.parseInt(tokenizer.nextToken());
				final int outgoingEdges =
					Integer.parseInt(tokenizer.nextToken());

				setOfRankings.add(new Ranking(
					count,
					entityName,
					pageRankValue,
					rank,
					allEdges,
					incomingEdges,
					outgoingEdges));
			}

			final Ranking[] rankings = new Ranking[setOfRankings.size()];
			setOfRankings.toArray(rankings);
			return rankings;
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return new Ranking[0];
		}
	}
}
