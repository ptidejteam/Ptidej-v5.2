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

import padl.aspectj.kernel.IInterTypeField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Field;

/**
 * @author Jean-Yves Guyomarc'h
 */
public class InterTypeField extends Field implements IInterTypeField {
	private static final long serialVersionUID = 3041336627789230817L;

	private IFirstClassEntity target;

	public InterTypeField(final char[] anID) {
		super(anID);
		this.target = null;
	}

	public InterTypeField(
		final char[] anID,
		final char[] aFieldType,
		final int aCardinality) {

		super(anID, anID, aFieldType, aCardinality);
		this.target = null;
	}

	public IFirstClassEntity getTargetEntity() {
		return this.target;
	}

	public void setTargetEntity(final IFirstClassEntity anEntity) {
		this.target = anEntity;

	}
}
