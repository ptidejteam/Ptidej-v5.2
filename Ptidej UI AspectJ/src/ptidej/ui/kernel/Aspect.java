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
package ptidej.ui.kernel;

import padl.aspectj.kernel.IAspect;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2005/08/16
 */
public final class Aspect extends Entity {

	/**
	 * @param aPrimitiveFactory
	 * @param aBuilder
	 * @param anEntity
	 */
	public Aspect(
		final IPrimitiveFactory aPrimitiveFactory,
		final Builder aBuilder,
		final IAspect anAspect) {

		super(aPrimitiveFactory, aBuilder, anAspect);
	}
	protected String getStereotype() {
		return "<<Aspect>>";
	}
	protected String supplementalFieldText() {
		final Element[] elements = this.getElements();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			final Element element = elements[i];
			if (element instanceof Pointcut
					|| element instanceof InterTypeDeclareParents
					|| element instanceof InterTypeField) {
				buffer.append(element.getName());
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
	protected String supplementalMethodText() {
		final Element[] elements = this.getElements();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			final Element element = elements[i];
			if (element instanceof Advice || element instanceof InterTypeMethod
					|| element instanceof InterTypeConstructor) {
				buffer.append(element.getName());
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
}
