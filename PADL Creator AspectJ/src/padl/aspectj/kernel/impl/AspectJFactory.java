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
package padl.aspectj.kernel.impl;

import padl.aspectj.kernel.IAdvice;
import padl.aspectj.kernel.IAspect;
import padl.aspectj.kernel.IAspectJFactory;
import padl.aspectj.kernel.IInterTypeConstructor;
import padl.aspectj.kernel.IInterTypeDeclareParents;
import padl.aspectj.kernel.IInterTypeField;
import padl.aspectj.kernel.IInterTypeMethod;
import padl.aspectj.kernel.IPointcut;
import padl.kernel.IFactory;
import padl.kernel.impl.Factory;

/**
 * @author Jean-Yves Guyomarc'h
 */
public class AspectJFactory extends Factory implements IAspectJFactory {
	private static final long serialVersionUID = -3321920211167680442L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (AspectJFactory.UniqueInstance == null) {
			AspectJFactory.UniqueInstance = new AspectJFactory();
		}
		return AspectJFactory.UniqueInstance;
	}

	private AspectJFactory() {
	}
	public IAdvice createAdvice(final char[] aName) {
		return new Advice(aName);
	}
	public IAspect createAspect(final char[] aName) {
		return new Aspect(aName);
	}
	public IInterTypeConstructor createInterTypeConstructor(final char[] aName) {
		return new InterTypeConstructor(aName);
	}
	public IInterTypeDeclareParents createInterTypeDeclareParents(
		final char[] aName) {

		return new InterTypeDeclareParents(aName);
	}
	public IInterTypeField createInterTypeField(
		final char[] aName,
		final char[] type) {

		return new InterTypeField(aName, type, 1);
	}
	public IInterTypeMethod createInterTypeMethode(final char[] aName) {
		return new InterTypeMethod(aName);
	}
	public IPointcut createPointcut(final char[] aName) {
		return new Pointcut(aName);
	}
}
