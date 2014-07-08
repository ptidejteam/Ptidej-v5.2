/*
 * Created on Aug 29, 2004
 *
 *
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
 * University of Montréal - ESSI.
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

import padl.aspectj.kernel.IAdvice;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Element;

/**
 * @author Jean-Yves Guyomarc'h
 *
 */
public class Advice extends Element implements IAdvice {
	private static final long serialVersionUID = -8098735233777241698L;

	public Advice(final char[] anID) {
		super(anID);
	}

	/* (non-Javadoc)
	 * @see padl.kernel.IAdvice#getTargets()
	 */
	public IFirstClassEntity[] getTargets() {
		return null;
	}

	/* (non-Javadoc)
	 * @see padl.kernel.IAdvice#setTargets(padl.kernel.IEntity[])
	 */
	public void setTargets(final IFirstClassEntity[] entities) {

	}

}
