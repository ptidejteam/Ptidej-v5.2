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
package padl.creator.test.relationships.providers;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.impl.Factory;

public class Provider_INSTANCE_INSTANCE_FROM_FIELD_ONE extends AbstractProvider
		implements ITestProvider {

	public String getHelperClassName() {
		return "padl.creator.test.relationships.providers.A";
	}
	public IMethodInvocation getExpectedMethodInvocation() {
		final IFirstClassEntity targetEntity =
			Factory.getInstance().createClass(
				"padl.creator.test.relationships.providers.A".toCharArray(),
				"A".toCharArray());

		final IFirstClassEntity fieldDeclaringEntity =
			Factory
				.getInstance()
				.createClass(
					"padl.creator.test.relationships.providers.Test_INSTANCE_INSTANCE_FROM_FIELD_ONE"
						.toCharArray(),
					"Test_INSTANCE_INSTANCE_FROM_FIELD_ONE".toCharArray());

		final IMethodInvocation methodInvocation =
			Factory.getInstance().createMethodInvocation(
				IMethodInvocation.INSTANCE_INSTANCE_FROM_FIELD,
				padl.kernel.Constants.CARDINALITY_ONE,
				Modifier.PUBLIC,
				targetEntity,
				fieldDeclaringEntity);

		final IMethod calledMethod =
			Factory.getInstance().createMethod(
				"instanceMethod()".toCharArray(),
				"instanceMethod".toCharArray());
		methodInvocation.setCalledMethod(calledMethod);
		final IField invocationField =
			Factory.getInstance().createField(
				"a".toCharArray(),
				"a".toCharArray(),
				"padl.creator.test.relationships.providers.A".toCharArray(),
				padl.kernel.Constants.CARDINALITY_ONE);
		final List listCallingFields = new ArrayList();
		listCallingFields.add(invocationField);
		methodInvocation.setCallingField(listCallingFields);

		return methodInvocation;
	}
}

class Test_INSTANCE_INSTANCE_FROM_FIELD_ONE {
	private A a;

	public void foo() {
		this.a.instanceMethod();
	}
}