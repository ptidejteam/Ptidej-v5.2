package padl.cpp.kernel.impl;

import padl.cpp.kernel.IEnumValue;
import padl.kernel.Constants;
import padl.kernel.impl.Field;

public class EnumValue extends Field implements IEnumValue {
	private static final long serialVersionUID = 1L;

	public EnumValue(final char[] anID) {
		super(anID, anID, "EnumValue".toCharArray(), Constants.CARDINALITY_ONE);
	}
	private EnumValue(
		final char[] anID,
		final char[] aName,
		final char[] aFieldType,
		final int aCardinality) {

		super(anID, aName, aFieldType, aCardinality);
	}
}
