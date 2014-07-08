package ptidej.viewer.extension.repository.loc;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;

/**
 * @author Stéphane Vaucher
 * @since  2006/03/09
 */
public class BCEL2PADLAdaptor {
	/**
	 * Should return a identifier readable by a PADL model
	 * @see umontreal.geodes.evomet.IdentifierAdaptor#adapt(org.apache.bcel.classfile.JavaClass, org.apache.bcel.classfile.Method)
	 */
	public String adapt(final JavaClass clazz, final Method method) {
		final Type[] args = method.getArgumentTypes();
		final StringBuffer buffer = new StringBuffer();

		buffer.append(method.getName());
		if (args.length != 0) {
			buffer.append('(');
			for (int i = 0; i < args.length; i++) {
				final Type type = args[i];
				buffer.append(type.toString());
				if (i + 1 < args.length) {
					buffer.append(", ");
				}
			}
			buffer.append(')');
		}
		else {
			buffer.append("()");
		}

		return buffer.toString();
	}
	/**
	 * Should return a identifier readable by a PADL model
	 * @see umontreal.geodes.evomet.IdentifierAdaptor#adapt(org.apache.bcel.classfile.JavaClass, org.apache.bcel.classfile.Field)
	 */
	public String adapt(final Field field) {
		return field.getName();
	}
	/**
	 * Should return a identifier readable by a PADL model
	 * @see umontreal.geodes.evomet.IdentifierAdaptor#adapt(org.apache.bcel.classfile.JavaClass)
	 */
	public String adapt(final JavaClass clazz) {
		return clazz.getClassName().replace('$', '.');
	}
}
