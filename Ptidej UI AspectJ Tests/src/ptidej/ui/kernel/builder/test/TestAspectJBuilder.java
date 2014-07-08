/*
 * (c) Copyright 2003-2006 Jean-Yves Guyomarc'h,
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
package ptidej.ui.kernel.builder.test;

import java.util.Iterator;
import junit.framework.Assert;
import padl.aspectj.kernel.IAdvice;
import padl.aspectj.kernel.IAspect;
import padl.aspectj.kernel.IAspectElement;
import padl.aspectj.kernel.IInterTypeConstructor;
import padl.aspectj.kernel.IInterTypeDeclareParents;
import padl.aspectj.kernel.IInterTypeField;
import padl.aspectj.kernel.IInterTypeMethod;
import padl.aspectj.kernel.IPointcut;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfModel;
import ptidej.ui.kernel.Advice;
import ptidej.ui.kernel.Aspect;
import ptidej.ui.kernel.InterTypeConstructor;
import ptidej.ui.kernel.InterTypeDeclareParents;
import ptidej.ui.kernel.InterTypeField;
import ptidej.ui.kernel.InterTypeMethod;
import ptidej.ui.kernel.Pointcut;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2005/08/16
 */
public class TestAspectJBuilder extends PrimitiveBuilder {
	public TestAspectJBuilder(final String aName) {
		super(aName);
	}
	public void testTypematching() {
		final Iterator iterator =
			this.getCodeLevelModel().getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent c = (IConstituent) iterator.next();
			if (c instanceof IAspect) {
				Assert.assertEquals("Test type building: Aspect", true, this
					.getBuilder()
					.getEntity((IConstituentOfModel) c) instanceof Aspect);

				final Iterator iterAspect =
					((IAspect) c).getIteratorOnConstituents();
				while (iterAspect.hasNext()) {
					final IConstituent aElement =
						(IConstituent) iterAspect.next();
					if (aElement instanceof IAdvice) {
						Assert.assertTrue(
							"Test type building: Advice",
							this.getBuilder().getElement(
								(IAspect) c,
								(IAspectElement) aElement) instanceof Advice);
					}
					if (aElement instanceof IPointcut) {
						Assert.assertTrue(
							"Test type building: Pointcut",
							this.getBuilder().getElement(
								(IAspect) c,
								(IAspectElement) aElement) instanceof Pointcut);
					}

					if (aElement instanceof IInterTypeField) {
						Assert
							.assertTrue(
								"Test type building: InterTypeField",
								this.getBuilder().getElement(
									(IAspect) c,
									(IAspectElement) aElement) instanceof InterTypeField);
					}
					if (aElement instanceof IInterTypeMethod) {
						Assert
							.assertTrue(
								"Test type building: InterTypeMethod",
								this.getBuilder().getElement(
									(IAspect) c,
									(IAspectElement) aElement) instanceof InterTypeMethod);
					}
					else {
						if (aElement instanceof IInterTypeConstructor) {
							Assert
								.assertTrue(
									"Test type building: InterTypeConstructor",
									this.getBuilder().getElement(
										(IAspect) c,
										(IAspectElement) aElement) instanceof InterTypeConstructor);

						}
					}
					if (aElement instanceof IInterTypeDeclareParents) {
						Assert
							.assertTrue(
								"Test type building: InterTypeDeclareParents",
								this.getBuilder().getElement(
									(IAspect) c,
									(IAspectElement) aElement) instanceof InterTypeDeclareParents);
					}
				}
			}
			else {
				System.out.println("Non Aspect Element: " + c.getDisplayName()
						+ " -> " + c.getClass());
			}
		}
	}
}
