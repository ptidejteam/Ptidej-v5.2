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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/09/24
 */
public class Method {
	// Yann 2007/10/06: Recursion
	// This table makes sure that we visit
	// each method in a call graph once
	// and only once.
	private static final List ListOfVisitedMethods = new ArrayList();

	private final boolean isClass;
	private final boolean isEntryMethod;
	private final List listOfCalls;
	private final String name;
	private final String type;
	private final String uniqueName;

	//	public Method(
	//		final String aTypeName,
	//		final boolean isClass,
	//		final String aMethodName) {
	//
	//		this(aTypeName, isClass, aMethodName, false);
	//	}
	public Method(
		final String aTypeName,
		final boolean isClass,
		final String aMethodName,
		final boolean isEntryMethod) {

		this.isClass = isClass;
		this.isEntryMethod = isEntryMethod;
		this.listOfCalls = new ArrayList();
		this.name = aMethodName;
		this.type = aTypeName;

		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.type);
		buffer.append('.');
		buffer.append(this.name);
		this.uniqueName = buffer.toString();
	}
	public void accept(final IVisitor aVisitor) {
		if (this.isEntryMethod) {
			Method.ListOfVisitedMethods.clear();
		}

		aVisitor.enter(this);
		if (!ListOfVisitedMethods.contains(this.uniqueName)) {
			ListOfVisitedMethods.add(this.uniqueName);
			final Iterator iterator = this.listOfCalls.iterator();
			while (iterator.hasNext()) {
				final Call call = (Call) iterator.next();
				call.accept(aVisitor);
			}
		}
		aVisitor.exit(this);
	}
	public void addCall(final Call aCall) {
		this.listOfCalls.add(aCall);
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.name);
		buffer.append("   [in");
		if (this.isClass) {
			buffer.append(" class ");
		}
		else {
			buffer.append(" interface ");
		}
		buffer.append(this.type);
		buffer.append(']');
		return buffer.toString();
	}
}
