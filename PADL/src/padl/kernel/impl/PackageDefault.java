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
package padl.kernel.impl;

import padl.kernel.Constants;
import padl.kernel.IGhost;
import padl.kernel.IPackageDefault;
import padl.kernel.IPackageGhost;

public class PackageDefault extends Package implements IPackageDefault {
	private static final long serialVersionUID = 5993857112698114446L;

	public PackageDefault() {
		super(Constants.DEFAULT_PACKAGE_ID);
		this.setName(new char[0]);
	}
	public void addConstituent(final IGhost aGhost) {
		super.addConstituent(aGhost);
	}
	public void addConstituent(final IPackageGhost aPackageGhost) {
		super.addConstituent(aPackageGhost);
	}

	public String toString(final int tab) {
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append(super.toString(tab));
		codeEq.append('<');
		codeEq.append(this.getID());
		codeEq.append('>');
		return codeEq.toString();
	}
}
