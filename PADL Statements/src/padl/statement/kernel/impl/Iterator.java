/*
 * Created on 2007-09-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package padl.statement.kernel.impl;

import padl.kernel.IField;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.kernel.impl.Statement;
import padl.statement.kernel.IIterator;

/**
 * @author tagmouty
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
class Iterator extends Statement implements IIterator {
	private static final char[] ITERATOR = "Iterator".toCharArray();
	private static final long serialVersionUID = 4413591962431115345L;

	private IField field;
	private IInterface anInterface;
	private char[] condition;
	private IMethod method;
	private char[] returnTypeName;

	public Iterator(final IField aField, final char[] aCondition) {
		super(Iterator.ITERATOR);
		this.field = aField;
	}
	public Iterator(final IInterface anInterface, final char[] aCondition) {
		super(Iterator.ITERATOR);
		this.anInterface = anInterface;
	}
	public Iterator(final IMethod aMethod, final char[] aCondition) {
		super(Iterator.ITERATOR);

		this.method = aMethod;
		this.condition = aCondition;
	}
	public char[] getCondition() {
		return this.condition;
	}
	public IField getDeclaratingFiled() {
		return this.field;
	}
	public IMethod getDeclaringMethod() {
		return this.method;
	}
	public IInterface getDeclaratingInterface() {
		return this.anInterface;
	}
	public char[] getReturnTypeName() {
		/*if (this.getAttachedElement() == null) {
			return this.returnType;
		}
		return ((IConditionelS) this.getAttachedElement()).getReturn();*/
		return this.returnTypeName;
	}
	public void setReturnTypeName(final char[] aTypeName) {
		this.returnTypeName = aTypeName;
	}
}
