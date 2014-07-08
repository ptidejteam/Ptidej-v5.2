/*
 * (c) Copyright 2003-2004 Jean-Yves Guyomarc'h,
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
package padl.aspectj.kernel.impl;

import padl.aspectj.kernel.IInterTypeMethod;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import padl.kernel.impl.Method;

/**
 * @author Jean-Yves Guyomarc'h
 */
public class InterTypeMethod extends Method implements IInterTypeMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6216609307716703816L;
	private IFirstClassEntity target = null;
	private IMethod method = null;

	public InterTypeMethod(final char[] anID) {
		super(anID);
	}

	//	public InterTypeMethod(MethodInfo aMethod) {
	//		super(aMethod);
	//	}

	public InterTypeMethod(final char[] anID, final IMethod anAttachedMethod) {
		super(anID, anAttachedMethod);
	}

	public InterTypeMethod(final IMethod attachedMethod) {
		super(attachedMethod);
	}

	//	public InterTypeMethod(String anID, MethodInfo aMethod) {
	//		super(anID, aMethod);
	//	}

	public IMethod getMethod() {
		return this.method;
	}

	public IFirstClassEntity getTargetEntity() {
		return this.target;
	}

	public void setMethod(final IMethod method) {
		this.method = method;
	}

	public void setTargetEntity(final IFirstClassEntity anEntity) {
		this.target = anEntity;

	}

}
