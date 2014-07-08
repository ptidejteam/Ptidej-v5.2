package parser.defuse;

public class ModificationAndUseStatement {

	private final char[] lineNumber;
	private final char[] statement;
	private char[] location;

	public ModificationAndUseStatement(
		final char[] statement,
		final char[] lineNumber) {
		this.statement = statement;
		this.lineNumber = lineNumber;

	}

	public char[] getLineNumber() {
		return this.lineNumber;
	}

	public char[] getLocation() {
		return this.location;
	}

	public char[] getStatement() {
		return this.statement;
	}

	public String toString() {
		final String result =
			new String(this.getLineNumber()) + ";"
					+ new String(this.getStatement());
		//				+";"+ new String(this.getLocation());

		return result;
	}

}
