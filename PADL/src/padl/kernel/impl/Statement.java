package padl.kernel.impl;

import padl.kernel.IStatement;
import padl.path.IConstants;

/**
 * @author tagmouty
 * @author Yann
 */
public class Statement extends Constituent implements IStatement {
	private static final long serialVersionUID = 6348055409249029367L;

	public Statement(char[] anID) {
		super(anID);
	}
	protected char getPathSymbol() {
		return IConstants.STATEMENT_SYMBOL;
	}
}
