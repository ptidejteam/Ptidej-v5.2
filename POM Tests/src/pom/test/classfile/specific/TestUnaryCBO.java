/**
 * Copyright (c) 2006, Stephane Vaucher
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the 
 * following conditions are met:
 * 
 *   * Redistributions of source code must retain the above 
 *     copyright notice, this list of conditions and the 
 *     following disclaimer.
 *   * Redistributions in binary form must reproduce the 
 *     above copyright notice, this list of conditions and 
 *     the following disclaimer in the documentation and/or 
 *     other materials provided with the distribution.
 *   * The name of its contributors may be used to endorse or promote 
 *     products derived from this software without specific 
 *     prior written permission.
 *   
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package pom.test.classfile.specific;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IField;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

/**
 * Basic test case for CBO that is implementented using 
 * simple PADL components like fields, methods, and 
 * method invocations.
 * 
 * This test would **NOT** function with an implementation
 * that uses complex entities like IUseRelationships.
 * 
 * @author vauchers
 */
public class TestUnaryCBO extends TestCase {
	/** CBO **/
	private static IUnaryMetric Metric;

	/** Class under test **/
	private static IClass TestedClass;

	/** Classes representing different types of relationships **/
	private static IClass InheritedClass;
	private static IClass FieldClass;
	private static IClass UsedClass;

	/** Test model **/
	private static IAbstractLevelModel Model;

	public TestUnaryCBO(final String aName) {
		super(aName);
	}

	/**
	 * Class with no incoming or outgoing coupling
	 *  
	 * Test method for 'pom.metrics.impl.CBO.compute(TestUnaryCBO.Model,IEntity)'
	 */
	public void testNoCoupling() {
		Assert.assertEquals(
			"No coupling from this class",
			0.0d,
			TestUnaryCBO.Metric.compute(
				TestUnaryCBO.Model,
				TestUnaryCBO.TestedClass),
			0.0d);

	}
	/**
	 * Class inherits of another with no method invocation /field access
	 * 
	 * Test method for 'pom.metrics.impl.CBO.compute(TestUnaryCBO.Model,IEntity)'
	 */
	public void testInheritence() {
		TestUnaryCBO.TestedClass
			.addInheritedEntity(TestUnaryCBO.InheritedClass);
		Assert.assertEquals(
			"A link through inheritence does not count",
			0.0d,
			TestUnaryCBO.Metric.compute(
				TestUnaryCBO.Model,
				TestUnaryCBO.TestedClass),
			0.0d);
	}
	/**
	 * Class has a field that is not used 
	 * 
	 * Test method for 'pom.metrics.impl.CBO.compute(TestUnaryCBO.Model,IEntity)'
	 */
	public void testNotUsedField() {
		final IField field = getUniqueField(TestUnaryCBO.TestedClass);
		field.setType(TestUnaryCBO.FieldClass.getID());

		Assert.assertEquals(
			"A field should not count unless it is used.",
			0.0d,
			TestUnaryCBO.Metric.compute(
				TestUnaryCBO.Model,
				TestUnaryCBO.TestedClass),
			0.0d);
	}
	/**
	 * Class has a field that it uses.
	 * 
	 * Test method for 'pom.metrics.impl.CBO.compute(TestUnaryCBO.Model,IEntity)'
	 */
	public void testUsedFieldByMethod() {
		final IField field = this.getUniqueField(TestUnaryCBO.TestedClass);
		field.setType(TestUnaryCBO.FieldClass.getID());

		final IMethod method = this.getUniqueMethod(TestUnaryCBO.TestedClass);
		final IMethodInvocation mi =
			Factory.getInstance().createMethodInvocation(
				IMethodInvocation.CLASS_INSTANCE_FROM_FIELD,
				1,
				0,
				TestUnaryCBO.FieldClass);
		mi.setPublic(true);

		final IMethod methodInvoked = getUniqueMethod(FieldClass);
		mi.setCalledMethod(methodInvoked);

		Assert.assertEquals(methodInvoked, mi.getCalledMethod());
		method.addConstituent(mi);

		Assert.assertEquals(
			"A field should not count unless it is used.",
			0.0d,
			TestUnaryCBO.Metric.compute(
				TestUnaryCBO.Model,
				TestUnaryCBO.TestedClass),
			0.0d);
	}
	protected void setUp() throws Exception {
		super.setUp();

		if (TestUnaryCBO.Model == null) {
			TestUnaryCBO.Model =
				Factory.getInstance().createCodeLevelModel("Model");

			TestUnaryCBO.TestedClass =
				Factory.getInstance().createClass(
					"Class".toCharArray(),
					"Class".toCharArray());
			TestUnaryCBO.InheritedClass =
				Factory.getInstance().createClass(
					"InheritedClass".toCharArray(),
					"InheritedClass".toCharArray());
			TestUnaryCBO.UsedClass =
				Factory.getInstance().createClass(
					"UsedClass".toCharArray(),
					"UsedClass".toCharArray());
			TestUnaryCBO.FieldClass =
				Factory.getInstance().createClass(
					"FieldClass".toCharArray(),
					"FieldClass".toCharArray());

			this.setupClass(TestUnaryCBO.TestedClass);
			this.setupClass(TestUnaryCBO.InheritedClass);
			this.setupClass(TestUnaryCBO.FieldClass);
			this.setupClass(TestUnaryCBO.UsedClass);

			final IPackage enclosingPackage =
				Factory.getInstance().createPackage(
					"(POM test default)".toCharArray());
			TestUnaryCBO.Model.addConstituent(enclosingPackage);

			enclosingPackage.addConstituent(TestUnaryCBO.TestedClass);
			enclosingPackage.addConstituent(TestUnaryCBO.InheritedClass);
			enclosingPackage.addConstituent(TestUnaryCBO.FieldClass);
			enclosingPackage.addConstituent(TestUnaryCBO.UsedClass);

			TestUnaryCBO.Metric =
				(IUnaryMetric) MetricsRepository.getInstance().getMetric("CBO");
		}
	}
	// Utility methods to access unique fields and methods
	// of different classes created
	private IField getUniqueField(final IClass clazz) {
		return (IField) clazz.getConstituentFromID(this.fieldId(clazz));
	}
	private IMethod getUniqueMethod(final IClass clazz) {
		return (IMethod) clazz.getConstituentFromID(this.methodId(clazz));
	}
	private String fieldId(final IClass clazz) {
		return clazz.getDisplayName() + "-field";
	}
	private String methodId(final IClass clazz) {
		return clazz.getDisplayName() + "-method";
	}
	/**
	 * Gives a class a field and a method
	 * 
	 * @param clazz
	 */
	private void setupClass(final IClass clazz) {
		final IMethod method =
			Factory.getInstance().createMethod(
				this.methodId(clazz).toCharArray(),
				this.methodId(clazz).toCharArray());
		final IField field =
			Factory.getInstance().createField(
				this.fieldId(clazz).toCharArray(),
				this.fieldId(clazz).toCharArray(),
				"int".toCharArray(),
				1);

		clazz.addConstituent(method);
		clazz.addConstituent(field);
	}

}
