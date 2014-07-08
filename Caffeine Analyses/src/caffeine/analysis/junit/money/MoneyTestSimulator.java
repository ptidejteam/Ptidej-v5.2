/*
 * (c) Copyright 2002-2003 Yann-Gaël Guéhéneuc,
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
package caffeine.analysis.junit.money;

import caffeine.simulator.Caffeine;

/**
 * DO NOT FORGET TO INCREASE THE STACK SIZE WITH -Xss256k.
 * 
 * @version 0.2
 * @author	Yann-Gaël Guéhéneuc
 */
public class MoneyTestSimulator {
	public static void main(String[] args) {
		Caffeine.getUniqueInstance().run(
			// "Rules/Composition.pl",
			"Rules/MethodCalls.pl",
			// "Caffeine/Analysis/JUnit/Money/MoneyTest-MoneyBag.trace");
			// "Caffeine/Analysis/JUnit/Money/TestResult-TestFailure.trace");
			"Caffeine/Analysis/JUnit/Money/TestRunner-TestRunView.trace");
			// "Caffeine/Analysis/JUnit/Money/TestSuite-Test.trace");
			// "Caffeine/Analysis/JUnit/Money/TestSuite-Test_AWT.trace");
			// "Caffeine/Analysis/JUnit/Money/TestSuite-Test_Swing.trace");
	}
}