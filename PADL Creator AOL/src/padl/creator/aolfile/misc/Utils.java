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
package padl.creator.aolfile.misc;

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.IFirstClassEntity;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/11/28
 */
public class Utils {
	public static IFirstClassEntity searchForEntity(
		final IAbstractLevelModel anAbstractModel,
		final String anEntityName) {

		IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) anAbstractModel
				.getConstituentFromName(anEntityName.toCharArray());

		if (firstClassEntity == null) {
			firstClassEntity =
				anAbstractModel.getFactory().createGhost(
					anEntityName.toCharArray(),
					anEntityName.toCharArray());
			anAbstractModel.addConstituent(firstClassEntity);
		}

		return firstClassEntity;
	}
	public static char[] cleanCppEntityName(char[] anEntityName) {
		final int lastCharIndex = anEntityName.length - 1;
		if (anEntityName[lastCharIndex] == '&'
				|| anEntityName[lastCharIndex] == '*') {

			return ArrayUtils.subarray(anEntityName, 0, lastCharIndex);
		}
		return anEntityName;
	}
}
