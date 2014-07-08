package padl.statement.kernel.impl;

import java.util.Iterator;
import padl.kernel.IStatement;
import padl.kernel.impl.AbstractGenericContainerOfConstituents;
import padl.kernel.impl.GenericContainerConstants;
import padl.kernel.impl.GenericContainerOfInsertionOrderedConstituents;
import padl.kernel.impl.Statement;
import padl.statement.kernel.IBlock;

/**
 * @author tagmouty
 */
class Block extends Statement implements IBlock {
	private static final long serialVersionUID = 788273358202402182L;

	private final AbstractGenericContainerOfConstituents container =
		new GenericContainerOfInsertionOrderedConstituents(
			this,
			GenericContainerConstants.INITIAL_SIZE_ENTITIES);
	public Block(final char[] anID) {
		super(anID);
	}
	public void addConsituent(final IStatement anStatement) {
		this.container.directlyAddConstituentWithUniqueID(anStatement);
	}
	public Iterator getIteratorOnConstituents() {
		return this.container.getIteratorOnConstituents();
	}
}