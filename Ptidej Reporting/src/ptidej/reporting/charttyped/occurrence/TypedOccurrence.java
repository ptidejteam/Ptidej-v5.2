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
package ptidej.reporting.charttyped.occurrence;

import java.util.List;

/**
 * 
 * @author Yann
 * @since  2008/10/03
 *
 */
public class TypedOccurrence {
	private final String entityName;
	private final ExplainedDesignSmell[] explainedDesignSmells;
	private final int confidenceValue;

	//	public TypedOccurrence(
	//		final String anEntityName,
	//		final ExplainedDesignSmell[] someExplainedDesignSmells) {
	//
	//		this.entityName = anEntityName;
	//		this.explainedDesignSmells = someExplainedDesignSmells;
	//	}
	public TypedOccurrence(
		final String anEntityName,
		final int aConfidenceValue,
		final List someExplainedDesignSmells) {

		this.entityName = anEntityName;
		this.confidenceValue = aConfidenceValue;
		this.explainedDesignSmells =
			new ExplainedDesignSmell[someExplainedDesignSmells.size()];
		someExplainedDesignSmells.toArray(this.explainedDesignSmells);
	}
	public String getEntityName() {
		return this.entityName;
	}
	public int getConfidence() {
		return this.confidenceValue;
	}
	public ExplainedDesignSmell[] getDesignSmells() {
		return this.explainedDesignSmells;
	}
	public String toString() {
		return this.entityName;
	}
}
