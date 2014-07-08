/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.solver.occurrence;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/03/10
 */
public class PrincipalComponents {
	public static final char[][][] Composite =
		new char[][][] { new char[][] { "composite".toCharArray(),
				"component".toCharArray() } };
	public static final char[][][] AbstractFactory =
		new char[][][] { new char[][] { "abstractfactory".toCharArray(),
				"abstractproduct".toCharArray() } };
	public static final char[][][] Blob =
		new char[][][] { new char[][] { "LargeClass".toCharArray() },
				new char[][] { "LowCohesion".toCharArray() },
				new char[][] { "ControllerClass".toCharArray() } };
	public static final char[][][] FactoryMethod =
		new char[][][] { new char[][] { "creator".toCharArray(),
				"product".toCharArray() } };
	public static final char[][][] FunctionalDecomposition =
		new char[][][] { new char[][] { "NoInheritance".toCharArray() },
				new char[][] { "FunctionMethodClass".toCharArray() },
				new char[][] { "FunctionClass".toCharArray() } };
	public static final char[][][] SpaghettiCode =
		new char[][][] { new char[][] { "ClassGlobalVariable".toCharArray() },
				new char[][] { "LongMethod".toCharArray() } };
	public static final char[][][] SwissArmyKnife =
		new char[][][] { new char[][] { "MultipleInterface".toCharArray() },
				new char[][] { "StaticMethodClass".toCharArray() },
				new char[][] { "DefaultProtectedMethodClass".toCharArray() } };
	public static void print(final ReducedOccurrence[] someReducedOccurrences) {
		for (int i = 0; i < someReducedOccurrences.length; i++) {
			final ReducedOccurrence principalComponent =
				someReducedOccurrences[i];
			System.out.println(principalComponent);
		}
	}
}
