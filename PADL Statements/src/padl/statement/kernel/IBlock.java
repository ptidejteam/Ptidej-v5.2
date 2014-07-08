package padl.statement.kernel;

import java.util.Iterator;
import padl.kernel.IStatement;

/**
 * @author Yousra
 */
public interface IBlock extends IStatement {
	void addConsituent(final IStatement anStatement);
	Iterator getIteratorOnConstituents();
}
