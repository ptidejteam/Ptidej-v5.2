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

import padl.aspectj.kernel.IPointcut;
import padl.kernel.impl.Element;

/**
 * @author Jean-Yves Guyomarc'h
 *
 */
public class Pointcut extends Element implements IPointcut {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7274528226987106694L;

	public Pointcut(final char[] anID) {
		super(anID);
	}

}
