/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
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
package padl.kernel.impl;

import padl.kernel.IConstructor;
import padl.kernel.IElementMarker;
import padl.kernel.IMethod;

// Angelino Picone 21/08/2012:
// Changed the visibility to public in order to allow other project to extend from this class in the particular case of Eclipse bundle loader (avoid IllegalAccessError).
// TODO Can we do without this ugly hack?
public class Constructor extends Operation implements IElementMarker,
		IConstructor {

	private static final long serialVersionUID = -3313404261410898384L;

	public Constructor(final char[] anID) {
		super(anID);
	}
	public Constructor(final char[] anID, final IMethod anAttachedMethod) {
		super(anID, anAttachedMethod);
	}
	public Constructor(final IMethod anAttachedMethod) {
		super(anAttachedMethod);
	}
}
