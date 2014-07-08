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
package padl.analysis.repository.aacrelationships;

import padl.kernel.Constants;
import padl.kernel.IAbstractModel;
import padl.kernel.IField;
import com.ibm.toad.cfparse.utils.Access;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/01
 */
final class CardinalityManyAccessorsData implements IAccessorsData {
	public CardinalityManyAccessorsData(final IAbstractModel anAbstractModel) {
	}
	public int getCardinality() {
		return Constants.CARDINALITY_MANY;
	}
	public boolean matches(final String aTargetName, final IField aField) {
		// Yann 2007/10/31: Cardinality!
		// I now correctly use the cardinality of the field,
		// instead of its name!, to assess whether it can
		// play a role in a container aggregation.
		return !Access.isPublic(aField.getVisibility())
				&& aField.getCardinality() > 1;
	}
}
