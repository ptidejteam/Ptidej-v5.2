/*
 * (c) Copyright 2004 Sébastien Robidoux, Ward Flores,
 * Université de Montréal 
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
package padl.kernel.cpp.antlr.impl;

import padl.kernel.IElementMarker;
import padl.kernel.IMethod;
import padl.kernel.cpp.antlr.IDestructor;
import padl.kernel.impl.Operation;

/**
 * @author Sébastien Robidoux
 * @since  2004/08/10
 * Simple Copy/Paste of Constructor
 */
class Destructor extends Operation implements IElementMarker, IDestructor {
	private static final long serialVersionUID = 6658205364561982696L;

	public Destructor(final char[] anID, final char[] aName) {
		super(anID);
		this.setName(aName);
	}
	public Destructor(final char[] anID, final IMethod anAttachedMethod) {
		super(anID, anAttachedMethod);
	}
	public Destructor(final IMethod anAttachedMethod) {
		super(anAttachedMethod);
	}
}
