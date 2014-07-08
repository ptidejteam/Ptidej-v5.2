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
package squad.quality;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import padl.util.Util;

public abstract class AbstractQualityAttribute {
	private final String name;

	public AbstractQualityAttribute() {
		this.name = Util.computeSimpleName(this.getClass().getName());
	}

	public final String computeNominalValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		final String result =
			this.concretelyComputeNomValue(anAbstractModel, anEntity);
		return result;
	}

	public final double computeNumericValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		final double result =
			this.concretelyComputeNumValue(anAbstractModel, anEntity);
		return result;
	}

	protected String concretelyComputeNomValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		return "N/A";
	}

	protected double concretelyComputeNumValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		return Double.NaN;
	}

	public final String getName() {
		return this.name;
	}
}
