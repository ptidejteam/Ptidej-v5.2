/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package padl.creator.classfile.util;

import java.util.List;
import padl.kernel.IFactory;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethodInvocation;
import padl.kernel.IOperation;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/02
 */
public class ExtendedMethodInvocation {
	private final IFirstClassEntity enclosingEntity;
	private final IOperation enclosingMethod;
	private IMethodInvocation methodInvocation;

	public ExtendedMethodInvocation(
		final IFactory aFactory,
		final IFirstClassEntity enclosingEntity,
		final IOperation enclosingMethod,
		final int type,
		final int cardinality,
		final IFirstClassEntity targetEntity) {

		this.enclosingEntity = enclosingEntity;
		this.enclosingMethod = enclosingMethod;
		this.methodInvocation =
			aFactory.createMethodInvocation(
				type,
				cardinality,
				enclosingMethod.getVisibility(),
				targetEntity);
	}
	public ExtendedMethodInvocation(
		final IFactory aFactory,
		final IFirstClassEntity enclosingEntity,
		final IOperation enclosingMethod,
		final int type,
		final int cardinality,
		final IFirstClassEntity targetEntity,
		final IFirstClassEntity entityDeclaringField) {

		this.enclosingEntity = enclosingEntity;
		this.enclosingMethod = enclosingMethod;
		this.methodInvocation =
			aFactory.createMethodInvocation(
				type,
				cardinality,
				enclosingMethod.getVisibility(),
				targetEntity,
				entityDeclaringField);
	}
	public int getCardinality() {
		return this.methodInvocation.getCardinality();
	}
	public IOperation getEnclosingMethod() {
		return this.enclosingMethod;
	}
	public IMethodInvocation getMethodInvocation() {
		return this.methodInvocation;
	}
	public IFirstClassEntity getOriginEntity() {
		return this.enclosingEntity;
	}
	public IFirstClassEntity getTargetEntity() {
		return this.methodInvocation.getTargetEntity();
	}
	public int getType() {
		return this.methodInvocation.getType();
	}
	public int getVisibility() {
		return this.methodInvocation.getVisibility();
	}
	public void setCalledMethod(final IOperation calledMethod) {
		this.methodInvocation.setCalledMethod(calledMethod);
	}
	public void setCallingField(final List callingFields) {
		this.methodInvocation.setCallingField(callingFields);
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Enclosing entity: ");
		buffer.append(this.enclosingEntity.getName());
		buffer.append("\nEnclosing method: ");
		buffer.append(this.enclosingMethod.getName());
		buffer.append('\n');
		buffer.append(this.methodInvocation.toString());
		return buffer.toString();
	}
}
