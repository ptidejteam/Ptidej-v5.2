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

public class Ranking {
	private final int count;
	private final String entityName;
	private final float pageRankValue;
	private final int rank;
	private final int numberOfEdges;
	private final int numberOfIncomingEdges;
	private final int numberOfOutgoingEdges;

	public Ranking(
		final int aCount,
		final String anEntityName,
		final float aPageRankValue,
		final int aRank,
		final int aNumberOfEdges,
		final int aNumberOfIncomingEdges,
		final int aNumberOfOutgoingEdges) {

		this.count = aCount;
		this.entityName = anEntityName;
		this.pageRankValue = aPageRankValue;
		this.rank = aRank;
		this.numberOfEdges = aNumberOfEdges;
		this.numberOfIncomingEdges = aNumberOfIncomingEdges;
		this.numberOfOutgoingEdges = aNumberOfOutgoingEdges;
	}

	public int getCount() {
		return this.count;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public float getPageRankValue() {
		return this.pageRankValue;
	}

	public int getRank() {
		return this.rank;
	}

	public int getNumberOfEdges() {
		return this.numberOfEdges;
	}

	public int getNumberOfIncomingEdges() {
		return this.numberOfIncomingEdges;
	}

	public int getNumberOfOutgoingEdges() {
		return this.numberOfOutgoingEdges;
	}
}
