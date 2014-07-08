/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package util.lang;

import util.io.ProxyConsole;

public final class ConcreteReceiverGuard {
	private static ConcreteReceiverGuard UniqueInstance;

	public static ConcreteReceiverGuard getInstance() {
		if (ConcreteReceiverGuard.UniqueInstance == null) {
			ConcreteReceiverGuard.UniqueInstance = new ConcreteReceiverGuard();
		}
		return ConcreteReceiverGuard.UniqueInstance;
	}
	private ConcreteReceiverGuard() {
	}
	private void doCheck(
		final String aConcreteReceiverClassToEnforce,
		final String anErrorMessage) {

		class ConcreteReceiverGuardThrownException extends RuntimeException {
			private static final long serialVersionUID = -4100342857707204144L;
		}

		try {
			throw new ConcreteReceiverGuardThrownException();
		}
		catch (final ConcreteReceiverGuardThrownException e) {
			final StackTraceElement[] stackTrace = e.getStackTrace();
			if (stackTrace.length < 4) {
				ProxyConsole
					.getInstance()
					.errorOutput()
					.print(this.getClass().getName());
				ProxyConsole
					.getInstance()
					.errorOutput()
					.print(" cannot protect ");
				ProxyConsole
					.getInstance()
					.errorOutput()
					.println(aConcreteReceiverClassToEnforce);
			}
			else {
				// Yann 2013/07/10: Intermediaries
				// It is possible that the method calling the ConcreteReceivedGard
				// be actually an overloaded method or a super constructor. Hence,
				// I must first, based on the method name, search for the real 
				// "external" caller by traversing the stack as long as the method
				// called as the same name as the method directly calling the guard
				// as in:
				//	util.lang.ConcreteReceiverGuard.doCheck(ConcreteReceiverGuard.java:44)
				//	util.lang.ConcreteReceiverGuard.checkCallingClassName(ConcreteReceiverGuard.java:128)
				//	pom.metrics.repository.AbstractMetric.<init>(AbstractMetric.java:62)
				//	pom.metrics.repository.USELESS.<init>(USELESS.java:12)
				//	pom.test.general.TestMetricRepository.testRuntimeDeprecation(TestMetricRepository.java:66)
				final String nameOfTheMethodDirectlyCallingTheGuard =
					stackTrace[2].getMethodName();
				int positionOfNextInterestingMethodCall;
				for (positionOfNextInterestingMethodCall = 3; positionOfNextInterestingMethodCall < stackTrace.length
						&& stackTrace[positionOfNextInterestingMethodCall]
							.getMethodName()
							.equals(nameOfTheMethodDirectlyCallingTheGuard); positionOfNextInterestingMethodCall++)
					;
				positionOfNextInterestingMethodCall--;

				final StringBuffer buffer = new StringBuffer();
				buffer.append(stackTrace[positionOfNextInterestingMethodCall]
					.getClassName());
				buffer.append('.');
				buffer.append(stackTrace[positionOfNextInterestingMethodCall]
					.getMethodName());
				final String nameOfGuardedMethod = buffer.toString();
				final String nameOfClassCallingGuardedMethod =
					stackTrace[positionOfNextInterestingMethodCall + 1]
						.getClassName();

				boolean legit = false;
				if (nameOfClassCallingGuardedMethod
					.equals(aConcreteReceiverClassToEnforce)) {

					legit = true;
				}

				if (!legit) {
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(this.getClass().getName());
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(
							" reports a runtime deprecation: calling method \"");
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(nameOfGuardedMethod);
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print("()\" from class \"");
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(nameOfClassCallingGuardedMethod);
					ProxyConsole.getInstance().warningOutput().println('\"');
					ProxyConsole
						.getInstance()
						.warningOutput()
						.println(anErrorMessage);
				}
			}
		}
	}
	public void checkCallingClassName(
		final String aConcreteReceiverClassToEnforce,
		final String anErrorMessage) {

		// Yann 2013/04/05: Stack!
		// I added this spurious method to make sure that the 
		// doCheck() method is always called with the same stack
		// depth from this class, i.e., 2 :-)
		this.doCheck(aConcreteReceiverClassToEnforce, anErrorMessage);
	}
	public void checkCallingClassName(
		final Class<?> aConcreteReceiverClassToEnforce,
		final String anErrorMessage) {

		this.checkCallingClassName(
			aConcreteReceiverClassToEnforce.getName(),
			anErrorMessage);
	}
}
