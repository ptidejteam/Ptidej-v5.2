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
package padl.kernel;

import java.util.Iterator;
import java.util.List;

/**
 * @author Farouk Zaidi
 * @since  2004/04/02
 */
public interface IMethodInvocation extends IConstituentOfOperation {
	// Yann 2004/07/31: Field access.
	// Method invocation are currently used to describe
	// field access as well. I need a UNKNWOWN value
	// for such cases.
	// TODO: Method invocation should describe methods only!
	int CLASS_CLASS = 1;
	int CLASS_CLASS_FROM_FIELD = 2;
	int CLASS_INSTANCE = 3;
	int CLASS_INSTANCE_FROM_FIELD = 4;
	int INSTANCE_CLASS = 5;
	int INSTANCE_CLASS_FROM_FIELD = 6;
	int INSTANCE_CREATION = 9;
	int INSTANCE_INSTANCE = 7;
	int INSTANCE_INSTANCE_FROM_FIELD = 8;
	int OTHER = 10;

	IOperation getCalledMethod();
	Iterator getIteratorOnCallingFields();
	int getCardinality();
	IFirstClassEntity getFieldDeclaringEntity();
	IField getFirstCallingField();
	IFirstClassEntity getTargetEntity();
	int getType();
	
	
	// TODO: Should be removed
	void setCalledMethod(final IOperation calledMethod);
	void setCallingField(final List callingFields);
	void addCallingField(final IField field);
}