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
package padl.statement.kernel.impl;

import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.statement.kernel.IIfInstruction;
import padl.statement.kernel.IStatementWalker;
import padl.statement.kernel.ISwitchInstruction;

public class StatementWalkerAdapter implements IStatementWalker {

	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close(IAbstractModel anAbstractModel) {
		// TODO Auto-generated method stub
		
	}

	public void close(IClass aClass) {
		// TODO Auto-generated method stub
		
	}

	public void close(IConstructor aConstructor) {
		// TODO Auto-generated method stub
		
	}

	public void close(IDelegatingMethod aDelegatingMethod) {
		// TODO Auto-generated method stub
		
	}

	public void close(IGetter aGetter) {
		// TODO Auto-generated method stub
		
	}

	public void close(IGhost aGhost) {
		// TODO Auto-generated method stub
		
	}

	public void close(IInterface anInterface) {
		// TODO Auto-generated method stub
		
	}

	public void close(IMemberClass aMemberClass) {
		// TODO Auto-generated method stub
		
	}

	public void close(IMemberGhost aMemberGhost) {
		// TODO Auto-generated method stub
		
	}

	public void close(IMemberInterface aMemberInterface) {
		// TODO Auto-generated method stub
		
	}

	public void close(IMethod aMethod) {
		// TODO Auto-generated method stub
		
	}

	public void close(IPackage aPackage) {
		// TODO Auto-generated method stub
		
	}

	public void close(IPackageDefault aPackage) {
		// TODO Auto-generated method stub
		
	}

	public void close(ISetter aSetter) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void open(IAbstractModel anAbstractModel) {
		// TODO Auto-generated method stub
		
	}

	public void open(IClass aClass) {
		// TODO Auto-generated method stub
		
	}

	public void open(IConstructor aConstructor) {
		// TODO Auto-generated method stub
		
	}

	public void open(IDelegatingMethod aDelegatingMethod) {
		// TODO Auto-generated method stub
		
	}

	public void open(IGetter aGetter) {
		// TODO Auto-generated method stub
		
	}

	public void open(IGhost aGhost) {
		// TODO Auto-generated method stub
		
	}

	public void open(IInterface anInterface) {
		// TODO Auto-generated method stub
		
	}

	public void open(IMemberClass aMemberClass) {
		// TODO Auto-generated method stub
		
	}

	public void open(IMemberGhost aMemberGhost) {
		// TODO Auto-generated method stub
		
	}

	public void open(IMemberInterface aMemberInterface) {
		// TODO Auto-generated method stub
		
	}

	public void open(IMethod aMethod) {
		// TODO Auto-generated method stub
		
	}

	public void open(IPackage aPackage) {
		// TODO Auto-generated method stub
		
	}

	public void open(IPackageDefault aPackage) {
		// TODO Auto-generated method stub
		
	}

	public void open(ISetter aSetter) {
		// TODO Auto-generated method stub
		
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void unknownConstituentHandler(
		String aCalledMethodName,
		IConstituent aConstituent) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IAggregation anAggregation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IAssociation anAssociation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IComposition aComposition) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IContainerAggregation aContainerAggregation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IContainerComposition aContainerComposition) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ICreation aCreation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IField aField) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IMethodInvocation aMethodInvocation) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IParameter aParameter) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IPrimitiveEntity aPrimitiveEntity) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IUseRelationship aUse) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IIfInstruction anIfInstruction) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ISwitchInstruction aSwitchInstruction) {
		// TODO Auto-generated method stub
		
	}

}
