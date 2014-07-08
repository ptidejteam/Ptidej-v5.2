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
package padl.creator.msefile.misc;

import java.util.Map;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/06/10
 */
public class Package {
	private final String name;
	private final String enclosingPackageRef;

	public Package(final String aName) {
		this.name = aName;
		this.enclosingPackageRef = null;
	}
	public Package(final String aName, final String anEnclosingPackageRef) {
		this.name = aName;
		this.enclosingPackageRef = anEnclosingPackageRef;
	}
	public String getFullName(final Map aMapOfRefedPackages) {
		return this.getFullName0(aMapOfRefedPackages, this.enclosingPackageRef);
	}
	private String getFullName0(
		final Map aMapOfRefedPackages,
		final String anEnclosingPackageRef) {

		final Package enclosingPackage =
			(Package) aMapOfRefedPackages.get(anEnclosingPackageRef);
		if (enclosingPackage.enclosingPackageRef == null) {
			return enclosingPackage.getSimpleName();
		}
		else {
			return this.getFullName0(
				aMapOfRefedPackages,
				enclosingPackage.enclosingPackageRef)
					+ '.' + enclosingPackage.getSimpleName();
		}
	}
	public String getSimpleName() {
		return this.name;
	}
	public String toString() {
		return this.name;
	}
}
