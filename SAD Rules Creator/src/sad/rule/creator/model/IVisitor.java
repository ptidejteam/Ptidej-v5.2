/*
 * (c) Copyright 2001, 2002 Herv�Albin-Amiot and Yann-Ga� Gu��euc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */

package sad.rule.creator.model;

public interface IVisitor {
	void close(final IRule aRule);
	void close(final IRuleCard aRuleCard);
	Object getResult();
	void open(final IRule aRule);
	void open(final IRuleCard aRuleCard);
	void visit(final IAggregation anAggregation);
	void visit(final IAssociation anAssociation);
	void visit(final IComposition aComposition);
	void visit(final IInheritance anInheritance);
	void visit(final IMetric aMetric);
	void visit(final IOperator anOperator);
	void visit(final ISemantic aSemantic);
	void visit(final IStruct aStruct);
}
