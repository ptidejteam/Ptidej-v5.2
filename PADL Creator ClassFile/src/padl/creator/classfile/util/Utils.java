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
package padl.creator.classfile.util;

import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.lang.ArrayUtils;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;
import padl.util.Util;
import com.ibm.toad.cfparse.ClassFile;
import com.ibm.toad.cfparse.utils.Access;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/01
 */
public class Utils {
	private static final String DEFAULT_PACKAGE_NAME_STRING =
		"(Default Package)";
	private static final char[] DEFAULT_PACKAGE_NAME =
		DEFAULT_PACKAGE_NAME_STRING.toCharArray();
	public static boolean ENABLE_BUILT_IN_AAC_ANALYSIS = false;

	/**
	 * Constructs the method signature according to the pattern.:
	 * methodName(param1, param2, param3,...)
	 * 
	 * @param a method info 
	 * @return the method signature
	 */
	public static char[] computeSignature(final ExtendedMethodInfo aMethodInfo) {
		final StringBuffer signature = new StringBuffer();
		signature.append(aMethodInfo.getName());
		signature.append('(');
		final char[][] params = aMethodInfo.getParameters();
		for (int i = 0; i < params.length; i++) {
			signature.append(params[i]);
			if (i != (params.length - 1))
				// Farouk 2004/04/14:
				// A space was missing. So allMethods and newMethods
				// in POM tests did not work properly.
				signature.append(", ");
		}
		signature.append(')');
		return signature.toString().toCharArray();
	}
	public static char[] computeSimpleName(final char[] aJVMClassName) {
		int index = ArrayUtils.lastIndexOf(aJVMClassName, '$');
		if (index == -1) {
			index = ArrayUtils.lastIndexOf(aJVMClassName, '.');
			if (index == -1) {
				return aJVMClassName;
			}
		}
		return ArrayUtils.subarray(
			aJVMClassName,
			index + 1,
			aJVMClassName.length);
	}
	public static IFirstClassEntity createGhost(
		final IAbstractLevelModel anAbstractLevelModel,
		final char[] anEntityName) {

		final IFirstClassEntity firstClassEntity =
			anAbstractLevelModel.getFactory().createGhost(
				anEntityName,
				Utils.computeSimpleName(anEntityName));
		final IPackage enclosingPackage =
			Utils.searchForPackage(
				anAbstractLevelModel,
				anEntityName,
				new PackageCreator() {
					public IPackage create(final char[] aName) {
						return Factory.getInstance().createPackageGhost(aName);
					}
				});
		enclosingPackage.addConstituent(firstClassEntity);
		return firstClassEntity;
	}
	public static char[] extractPackageName(final char[] aJVMClassName) {
		if (ArrayUtils.indexOf(aJVMClassName, '.') == -1) {
			return Utils.DEFAULT_PACKAGE_NAME;
		}
		else {
			return ArrayUtils.subarray(
				aJVMClassName,
				0,
				ArrayUtils.lastIndexOf(aJVMClassName, '.'));
		}
	}
	public static IFirstClassEntity getEntityOrCreateGhost(
		final IAbstractLevelModel anAbstractLevelModel,
		final char[] anEntityName,
		final Map aMapOfIDsEntities) {

		IFirstClassEntity firstClassEntity;

		// Yann 2010/06/30: Arrays...
		// For some reason, when analysing Xercesv2.7.0.jar,
		// the DeepRelationshipAnalyser attemps to add the
		// array java.lang.String[]... Don't know why!
		if (Util.isArray(anEntityName)) {
			firstClassEntity =
				(IFirstClassEntity) anAbstractLevelModel
					.getTopLevelEntityFromID(ArrayUtils.subarray(
						anEntityName,
						0,
						ArrayUtils.indexOf(anEntityName, '[')));
		}
		else {
			//	IFirstClassEntity firstClassEntity =
			//		(IFirstClassEntity) aMapOfIDsEntities.get(String
			//			.valueOf(anEntityName));
			firstClassEntity =
				(IFirstClassEntity) anAbstractLevelModel
					.getTopLevelEntityFromID(anEntityName);
		}

		if (firstClassEntity == null) {
			if (ArrayUtils.indexOf(anEntityName, '$') == -1) {
				firstClassEntity =
					Utils.createGhost(anAbstractLevelModel, anEntityName);
			}
			else {
				firstClassEntity =
					Utils.searchForEntity(anAbstractLevelModel, anEntityName);
			}
			aMapOfIDsEntities.put(
				String.valueOf(anEntityName),
				firstClassEntity);
		}
		return firstClassEntity;
	}
	public static IPackage getPackage(
		final Map aMapOfPackageNamesPackages,
		final char[] aJVMClassName) {

		return (IPackage) aMapOfPackageNamesPackages.get(String.valueOf(Utils
			.extractPackageName(aJVMClassName)));
	}
	public static boolean isAnonymousOrLocalEntity(final char[] anEntityName) {
		final int first = ArrayUtils.indexOf(anEntityName, '$');
		int last = ArrayUtils.lastIndexOf(anEntityName, '$');
		if (first > -1) {
			if (first == last) {
				last = anEntityName.length;
			}

			try {
				Integer.parseInt(String.valueOf(ArrayUtils.subarray(
					anEntityName,
					first + 1,
					last)));
			}
			catch (final NumberFormatException nfe) {
				return false;
			}
			return true;
		}

		return false;
	}
	public static boolean isLocalOrLocalMemberEntity(final char[] anEntityName) {
		final int first = ArrayUtils.indexOf(anEntityName, '$');
		int next = ArrayUtils.indexOf(anEntityName, '$', first + 1);
		if (first > -1 && next > -1) {
			try {
				Integer.parseInt(String.valueOf(ArrayUtils.subarray(
					anEntityName,
					first + 1,
					next)));
			}
			catch (final NumberFormatException nfe) {
				return false;
			}
			return true;
		}

		return false;
	}
	public static boolean isClass(final ClassFile classFile) {
		return !Utils.isInterface(classFile);
	}
	public static boolean isInterface(final ClassFile classFile) {
		return (classFile.getAccess() & Access.ACC_INTERFACE) == Access.ACC_INTERFACE;
	}
	public static boolean isMemberEntity(final char[] anEntityName) {
		int first = ArrayUtils.indexOf(anEntityName, '$') + 1;
		if (first > 0) {
			final int second = ArrayUtils.indexOf(anEntityName, '$', first);
			if (second == -1) {
				return true;
			}

			try {
				Integer.parseInt(String.valueOf(ArrayUtils.subarray(
					anEntityName,
					first,
					second)));
			}
			catch (final NumberFormatException nfe) {
				return true;
			}
		}

		return false;
	}
	public static boolean isMemberNameEmpty(final String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		}
		char c = s.charAt(0);
		if (c >= '0' && c <= '9') {
			return true;
		}
		if (s.endsWith("this") || s.equals("instanceof")) {
			return true;
		}
		return false;
	}
	public static boolean isMemberNameEmptyOrEqual(final String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		}
		char c = s.charAt(0);
		if (c >= '0' && c <= '9') {
			return true;
		}
		if (s.endsWith("this") || s.equals("instanceof") || s.equals("=")) {
			return true;
		}
		return false;
	}
	public static boolean isSpecialMethod(final char[] aMethodName) {
		return ArrayUtils.indexOf(aMethodName, '<') > -1;
	}
	public static IFirstClassEntity searchForEnclosingEntity(
		final IAbstractLevelModel anAbstractLevelModel,
		final char[] aMembreEntityName) {

		char[] enclosingEntityName =
			ArrayUtils.subarray(
				aMembreEntityName,
				0,
				ArrayUtils.indexOf(aMembreEntityName, '$'));
		IFirstClassEntity enclosingEntity =
			(IFirstClassEntity) anAbstractLevelModel
				.getTopLevelEntityFromID(enclosingEntityName);

		// Yann 2006/02/08: Ghosts...
		// If I cannot find an entity, then it is a ghost...
		if (enclosingEntity == null) {
			enclosingEntity =
				Utils.createGhost(anAbstractLevelModel, enclosingEntityName);
		}

		enclosingEntityName =
			ArrayUtils.subarray(
				aMembreEntityName,
				enclosingEntityName.length + 1,
				aMembreEntityName.length);
		while (ArrayUtils.indexOf(enclosingEntityName, '$') > 0) {
			// Yann 2014/02/21: Weird code!
			//	final char[] enclosingMemberEntityName =
			//		ArrayUtils.subarray(
			//			enclosingEntityName,
			//			0,
			//			ArrayUtils.indexOf(enclosingEntityName, '$'));
			IFirstClassEntity newMemberEnclosingEntity =
				(IFirstClassEntity) enclosingEntity
					.getConstituentFromID(aMembreEntityName);

			// Yann 2006/02/08: Ghosts...
			// If I cannot find an entity, then it is a ghost...
			// Thus, I built recursively Ghost entities and
			// member Ghost entities...
			if (newMemberEnclosingEntity == null) {
				newMemberEnclosingEntity =
					anAbstractLevelModel.getFactory().createMemberGhost(
						aMembreEntityName,
						Utils.computeSimpleName(aMembreEntityName));
				enclosingEntity
					.addConstituent((IConstituentOfEntity) newMemberEnclosingEntity);
			}
			enclosingEntity = newMemberEnclosingEntity;

			enclosingEntityName =
				ArrayUtils.subarray(
					enclosingEntityName,
					ArrayUtils.indexOf(enclosingEntityName, '$') + 1,
					enclosingEntityName.length);
		}

		return enclosingEntity;
	}
	public static IFirstClassEntity searchForEntity(
		final IAbstractLevelModel anAbstractModel,
		final char[] anEntityName) {

		if (Utils.isMemberEntity(anEntityName)) {
			final IFirstClassEntity enclosingEntity =
				(IFirstClassEntity) Utils.searchForEnclosingEntity(
					anAbstractModel,
					anEntityName);

			final char[] name =
				ArrayUtils.subarray(
					anEntityName,
					ArrayUtils.lastIndexOf(anEntityName, '$') + 1,
					anEntityName.length);
			// Yann 2010/09/25: Fix bug in a very particular case!
			// It is legal in Java for a member class to have the same
			// name as a field declared both in the same enclosing
			// class. This very particular case led to an exception
			// because an IField was being assigned to an IFirstClassEntity.
			// Assuming that a member entity will always have its
			// fully-qualified JVM name as ID, I changed the code from:
			//		IFirstClassEntity membreEntity =
			//			(IFirstClassEntity) enclosingEntity
			//				.getConstituentFromName(name);
			// to:
			// Yann 2014/02/21: Weird code!
			final StringBuffer memberEntityID = new StringBuffer();
			memberEntityID.append(enclosingEntity.getID());
			memberEntityID.append('$');
			memberEntityID.append(name);
			IFirstClassEntity membreEntity =
				(IFirstClassEntity) enclosingEntity
					.getConstituentFromID(memberEntityID
						.toString()
						.toCharArray());
			// TODO: Remove the unwritten constraint that a member class must of its fully-qualified JVM name as ID. 

			// Yann 2006/02/08: Ghosts...
			// If I cannot find a member entity, then it is a ghost...
			if (membreEntity == null) {
				membreEntity =
					anAbstractModel.getFactory().createMemberGhost(
						memberEntityID.toString().toCharArray(),
						name);
				enclosingEntity
					.addConstituent((IConstituentOfEntity) membreEntity);
			}

			return membreEntity;
		}
		else {
			IFirstClassEntity firstClassEntity =
				(IFirstClassEntity) anAbstractModel
					.getTopLevelEntityFromID(anEntityName);
			if (firstClassEntity == null) {
				firstClassEntity =
					Utils.createGhost(anAbstractModel, anEntityName);
			}
			return firstClassEntity;
		}
	}
	public static IPackage searchForPackage(
		final IAbstractLevelModel anAbstractLevelModel,
		final char[] anEntityName,
		final PackageCreator aPackageCreator) {

		// Yann 2008/11/04: Packages can be ghost, too!
		// Now that I handle packages, I must also create
		// appropriate ghost packages...
		final char[] packageName = Utils.extractPackageName(anEntityName);
		final StringTokenizer packageNameTokenizer =
			new StringTokenizer(String.valueOf(packageName), ".");
		IPackage enclosingPackage = null;

		if (!packageNameTokenizer.hasMoreTokens()) {
			enclosingPackage =
				aPackageCreator.create(Utils.DEFAULT_PACKAGE_NAME);
		}

		while (packageNameTokenizer.hasMoreTokens()) {
			final String packagePartDisplayName =
				packageNameTokenizer.nextToken();
			final char[] packagePartName = packagePartDisplayName.toCharArray();
			if (enclosingPackage == null) {
				enclosingPackage =
					(IPackage) anAbstractLevelModel
						.getConstituentFromID(packagePartName);

				if (enclosingPackage == null) {
					enclosingPackage = aPackageCreator.create(packagePartName);
					anAbstractLevelModel.addConstituent(enclosingPackage);
				}
			}
			else {
				IPackage newEnclosingPackage =
					(IPackage) enclosingPackage
						.getConstituentFromID(packagePartName);

				if (newEnclosingPackage == null) {
					newEnclosingPackage =
						aPackageCreator.create(packagePartName);
					enclosingPackage.addConstituent(newEnclosingPackage);
				}
				enclosingPackage = newEnclosingPackage;
			}
		}

		return enclosingPackage;
	}
	public static int[] sortByPriority(int[] listOfPriority) {
		int[] output = new int[listOfPriority.length];
		for (int x = 0; x < listOfPriority.length; x++) {
			output[x] = x;
		}
		for (int max = 0; max < listOfPriority.length - 1; max++) {
			for (int x = max + 1; x < listOfPriority.length; x++) {
				if (listOfPriority[output[x]] > listOfPriority[output[max]]) {
					int tmp = output[max];
					output[max] = output[x];
					output[x] = tmp;
				}
			}
		}
		return output;
	}
	private Utils() {
	}
}
