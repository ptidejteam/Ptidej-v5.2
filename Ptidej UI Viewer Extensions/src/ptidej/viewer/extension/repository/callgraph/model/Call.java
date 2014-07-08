/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
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
package ptidej.viewer.extension.repository.callgraph.model;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/09/24
 */
public class Call {
	private final Method calledMethod;

	public Call(final Method aCalledMethod) {
		this.calledMethod = aCalledMethod;
	}
	public Method getMethod() {
		return this.calledMethod;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.calledMethod.toString());
		return buffer.toString();
	}
	public void accept(final IVisitor aVisitor) {
		aVisitor.enter(this);
		this.calledMethod.accept(aVisitor);
		aVisitor.exit(this);
	}
}
