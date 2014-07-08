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

import padl.kernel.Constants;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.util.Util;
import com.ibm.toad.cfparse.utils.Access;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/01
 */
public final class CardinalityManyAccessorsData implements AccessorsData {
	private final ICodeLevelModel codeLevelModel;
	public CardinalityManyAccessorsData(final ICodeLevelModel aCodeLevelModel) {
		this.codeLevelModel = aCodeLevelModel;
	}
	public int getCardinality() {
		return Constants.CARDINALITY_MANY;
	}
	public boolean matches(
		final char[] targetName,
		final ExtendedFieldInfo fieldInfo) {

		final char[] typeName = fieldInfo.getType();
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) this.codeLevelModel
				.getConstituentFromID(typeName);
		boolean isArrayOrCollection = false;
		if (firstClassEntity != null) {
			isArrayOrCollection = Util.isArrayOrCollection(firstClassEntity);
		}
		else {
			isArrayOrCollection = Util.isArrayOrCollection(typeName);
		}
		return !Access.isPublic(fieldInfo.getVisibility())
				&& isArrayOrCollection;
	}
}
