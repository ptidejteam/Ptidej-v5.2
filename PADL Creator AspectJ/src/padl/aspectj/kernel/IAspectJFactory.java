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
package padl.aspectj.kernel;

import padl.kernel.IFactory;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/08/29
 */
public interface IAspectJFactory extends IFactory {
	IAdvice createAdvice(final char[] aName);
	IAspect createAspect(final char[] aName);
	IInterTypeConstructor createInterTypeConstructor(final char[] aName);
	IInterTypeDeclareParents createInterTypeDeclareParents(final char[] aName);
	IInterTypeField createInterTypeField(final char[] aName, final char[] type);
	IInterTypeMethod createInterTypeMethode(final char[] aName);
	IPointcut createPointcut(final char[] aName);
}
