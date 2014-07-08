/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.statement.kernel.impl;

import padl.kernel.IFactory;
import padl.kernel.IStatement;
import padl.kernel.impl.Factory;
import padl.kernel.impl.Statement;
import padl.statement.kernel.IIfInstruction;
import padl.statement.kernel.IStatementFactory;
import padl.statement.kernel.ISwitchInstruction;

/**
 * @author Yousra Tagmouti
 * @author Yann-Gaël Guéhéneuc
 */
public class StatementFactory extends Factory implements IStatementFactory {
	private static final long serialVersionUID = -7857386029686909308L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (StatementFactory.UniqueInstance == null) {
			StatementFactory.UniqueInstance = new StatementFactory();
		}
		return StatementFactory.UniqueInstance;
	}

	private StatementFactory() {
	}
	public IIfInstruction createIfInstruction(final char[] anExpression) {
		return new IfInstruction(anExpression);
	}
	public IStatement createStatement(final char[] aName) {
		return new Statement(aName);
	}
	public ISwitchInstruction createSwitchInstruction(
		final char[] anExpression,
		final int aNumberOfCases) {

		return new SwitchInstruction(anExpression, aNumberOfCases);
	}
}
