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
package padl.analysis.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import padl.analysis.IAnalysis;
import padl.analysis.UnsupportedSourceModelException;
import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/10/02
 * @deprecated Because the PADL meta-model now supports packages natively. 
 */
public class PackageIdentifierAnalysis implements IAnalysis {
	private static String getPackageName(final IFirstClassEntity anEntity) {
		return anEntity.getDisplayID().substring(
			0,
			anEntity.getDisplayID().lastIndexOf('.'));
	}

	private final Map packageNames = new HashMap();

	public IAbstractModel invoke(final IAbstractModel anAbstractModel)
			throws UnsupportedSourceModelException {

		// Yann 2005/10/07: Co-modification.
		// I copy the current list of actors to be allowed to
		// add/remove entities and packages from it during the
		// building of packages.
		// Yann 2005/10/12: Iterator!
		// I have now an iterator able to iterate over a
		// specified type of constituent of a list.
		final Iterator iterator =
			anAbstractModel.getIteratorOnTopLevelEntities();
		while (iterator.hasNext()) {
			final IFirstClassEntity firstClassEntity =
				(IFirstClassEntity) iterator.next();
			final String packageName =
				PackageIdentifierAnalysis.getPackageName(firstClassEntity);

			IPackage packaje = (IPackage) this.packageNames.get(packageName);
			if (packaje == null) {
				packaje =
					Factory.getInstance().createPackage(
						packageName.toCharArray());
				anAbstractModel.addConstituent(packaje);
				this.packageNames.put(packageName, packaje);
			}

			anAbstractModel
				.removeTopLevelEntityFromID(firstClassEntity.getID());
			packaje.addConstituent(firstClassEntity);
		}
		return anAbstractModel;
	}
	public String getName() {
		return "Package builder";
	}
}
