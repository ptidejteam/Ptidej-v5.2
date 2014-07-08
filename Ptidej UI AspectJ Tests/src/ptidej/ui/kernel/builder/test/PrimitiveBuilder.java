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

import padl.creator.aspectj.test.AspectJPrimitive;
import padl.kernel.exception.CreationException;
import ptidej.ui.kernel.builder.AspectJBuilder;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;
import ptidej.ui.primitive.awt.PrimitiveFactory;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2005-08-16
 */
public class PrimitiveBuilder extends AspectJPrimitive {
	private Builder ajBuilder;

	public PrimitiveBuilder(final String aName) {
		super(aName);
	}
	protected void setUp() throws CreationException {
		super.setUp();

		final IPrimitiveFactory primitiveFactory =
			PrimitiveFactory.getInstance();
		this.ajBuilder = AspectJBuilder.getCurrentBuilder(primitiveFactory);
	}
	public Builder getBuilder() {
		return this.ajBuilder;
	}
}
