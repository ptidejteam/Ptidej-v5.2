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
package padl.cpp.kernel.impl;

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.IEntity;
import padl.kernel.IParameter;
import padl.kernel.impl.Parameter;

public class CPPParameter extends Parameter implements IParameter {
	private static final long serialVersionUID = -1979306638669159781L;

	private final char[] qualitification;

	public CPPParameter(
		final IEntity anEntity,
		final char[] aName,
		final char[] aQualification,
		final int aCardinality) {

		super(anEntity, aName, aCardinality);
		this.qualitification = aQualification;
	}
	public char[] getTypeName() {
		final char[] nakedName = super.getTypeName();
		return ArrayUtils.addAll(nakedName, this.qualitification);
	}
}
