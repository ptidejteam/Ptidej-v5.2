/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.pagerank.utils;

import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.ICPPGhost;
import padl.cpp.kernel.ICPPMemberClass;
import padl.cpp.kernel.ICPPMemberGhost;
import padl.cpp.kernel.IDestructor;
import padl.cpp.kernel.IEnum;
import padl.cpp.kernel.IGlobalField;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.kernel.IMemberStructure;
import padl.cpp.kernel.IStructure;
import padl.cpp.kernel.IUnion;
import padl.cpp.visitor.ICPPGenerator;
import padl.kernel.IClass;
import padl.kernel.IConstituent;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGhost;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMethodInvocation;
import padl.kernel.IOperation;

public class InputDataGeneratorWith9RelationsForCPP extends
		InputDataGeneratorWith9Relations implements ICPPGenerator {

	public InputDataGeneratorWith9RelationsForCPP(
		boolean withGhosts,
		boolean withMembers) {

		super(withGhosts, withMembers);
	}
	public void close(final ICPPClass p) {
		this.close((IConstituent) p);
	}
	public void close(final ICPPGhost p) {
		this.close((IConstituent) p);
	}
	public void close(final ICPPMemberClass p) {
		this.close((IConstituent) p);
	}
	public void close(final ICPPMemberGhost p) {
		this.close((IConstituent) p);
	}
	public void close(final IDestructor p) {
		this.close((IOperation) p);
	}
	public void close(final IEnum p) {
		this.close((IFirstClassEntity) p);
	}
	public void close(final IGlobalField p) {
		// Do nothing because I already treat a global field as a field in open(IGlobalField).  
	}
	public void close(final IGlobalFunction p) {
		this.close((IOperation) p);
	}
	public void close(final IMemberStructure p) {
		this.close((IConstituent) p);
	}
	public void close(final IStructure p) {
		this.close((IFirstClassEntity) p);
	}
	public void close(final IUnion p) {
		this.close((IFirstClassEntity) p);
	}
	public void open(ICPPClass p) {
		this.open((IClass) p);
	}
	public void open(final ICPPGhost p) {
		this.open((IGhost) p);
	}
	public void open(ICPPMemberClass p) {
		this.open((IMemberClass) p);
	}
	public void open(final ICPPMemberGhost p) {
		this.open((IMemberGhost) p);
	}
	public void open(final IDestructor p) {
		this.open((IOperation) p);
	}
	public void open(final IEnum p) {
		this.open((IFirstClassEntity) p);
	}
	public void open(final IGlobalField p) {
		this.visit(p);
	}
	public void open(final IGlobalFunction p) {
		this.open((IOperation) p);
	}
	public void open(IMemberStructure p) {
		this.open((IStructure) p);
	}
	public void open(final IStructure p) {
		this.open((IFirstClassEntity) p);
	}
	public void open(final IUnion p) {
		this.open((IFirstClassEntity) p);
	}
	public void visit(final IMethodInvocation p) {
		super.visit(p);
		if (p.getCalledMethod() != null) {
			final IFirstClassEntity entity = p.getTargetEntity();
			if (this.isInteresting(entity)
					&& this.isInteresting(this.peekLastButOneConstituent())
					&& entity instanceof IGlobalFunction) {

				this.addRelationFromCurrentConstituent(
					entity,
					this.relationsCalledMethods);
			}
		}
	}
}
