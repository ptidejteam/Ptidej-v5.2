/*
 * (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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

package sad.rule.creator.model.impl;

import java.lang.reflect.Method;
import sad.rule.creator.model.IConstituent;
import sad.rule.creator.model.IVisitor;

/**
 * @author Pierre Leduc
 */
public class Constituent implements IConstituent {
	private final String id;

	public Constituent(final String anID) {
		this.id = anID;
	}

	public void accept(final IVisitor visitor) {
		this.accept(visitor, "visit");
	}

	protected void accept(final IVisitor visitor, final String methodName) {
		// I must match a class a the model to the 
		// corresponding interface: 
		//     rule.creator.model.impl.Association
		// ->
		//     rule.creator.model.IAssociation

		final String acceptMethodName =
			this.getClass().getName().replaceAll(".impl.", ".I");
		try {
			// Get the corresponding method by reflection
			final java.lang.Class[] argument =
				new java.lang.Class[] { java.lang.Class
					.forName(acceptMethodName) };
			final Method method =
				visitor.getClass().getMethod(methodName, argument);
			method.invoke(visitor, new Object[] { this });
		}
		catch (final Exception e) {
			// In case I add new constituent and forget to update
			// the method in the IVisitor interface, I foward such
			// exceptions (to fail the appropriate tests).
			System.err
				.print("Exception : The IVisitor class does not implement the visit method");
			throw new RuntimeException(e);
		}
	}

	public String getID() {
		return this.id;
	}
}
