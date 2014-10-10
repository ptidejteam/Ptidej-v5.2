/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
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
