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
package padl.test.helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflector {
	public static void callMethodOnReflectedField(
		final Class aDeclaringClass,
		final String aFieldName,
		final Object aReceiver,
		final String aMethodName) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {

		final Object object =
			Reflector.getObjectFromReflectedField(
				aDeclaringClass,
				aFieldName,
				aReceiver);
		final Method method =
			object.getClass().getMethod(aMethodName, new Class[0]);
		method.invoke(object, new Object[0]);
	}
	public static void callMethodOnReflectedField(
		final Class aDeclaringClass,
		final String aFieldName,
		final Object aReceiver,
		final String aMethodName,
		final Class aMethodParameterType,
		final Object aMethodParameterValue) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {

		final Object object =
			Reflector.getObjectFromReflectedField(
				aDeclaringClass,
				aFieldName,
				aReceiver);
		final Method method =
			object.getClass().getMethod(
				aMethodName,
				new Class[] { aMethodParameterType });
		method.invoke(object, new Object[] { aMethodParameterValue });
	}
	public static Object getObjectFromReflectedField(
		final Class aDeclaringClass,
		final String aFieldName,
		final Object aReceiver) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		final Field containerField =
			aDeclaringClass.getDeclaredField(aFieldName);
		containerField.setAccessible(true);
		final Object value = containerField.get(aReceiver);
		return value;
	}
	public static void setReflectedFieldValue(
		final Class aDeclaringClass,
		final String aFieldName,
		final Object aReceiver,
		final Object aValue) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		final Field containerField =
			aDeclaringClass.getDeclaredField(aFieldName);
		containerField.setAccessible(true);
		containerField.set(aReceiver, aValue);
	}
}
