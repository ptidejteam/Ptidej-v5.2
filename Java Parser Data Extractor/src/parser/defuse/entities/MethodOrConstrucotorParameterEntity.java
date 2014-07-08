package parser.defuse.entities;

import parser.defuse.Entity;

public class MethodOrConstrucotorParameterEntity extends Entity {

	public MethodOrConstrucotorParameterEntity(
		final char[] name,
		final char[] signature,
		final char[] type,
		final char[] lineNumber,
		final char[] parent,
		final char[] key) {
		super(name, signature, type, lineNumber, parent, key);
		// TODO Auto-generated constructor stub
	}

}
