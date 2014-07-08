/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package padl.serialiser.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import padl.kernel.IConstituent;
import padl.kernel.IFirstClassEntity;
import util.io.ProxyConsole;

public class TransientFieldManager {
	static final String NULL_VALUE = "NULL";
	public static final String METHOD_INVOCATION_EXTENSION = ".methodinvocations";

	public static final Map getTransientEntityFieldNamesEntityIDs(
		final Field[] someTransientFieldsNames,
		final IConstituent aConstituent) {

		final Map mapOfFieldNamesIDs = new HashMap();
		for (int i = 0; i < someTransientFieldsNames.length; i++) {
			try {
				final Field field = someTransientFieldsNames[i];
				field.setAccessible(true);
				final IFirstClassEntity firstClassEntity = ((IFirstClassEntity) field.get(aConstituent));
				if (firstClassEntity == null) {
					mapOfFieldNamesIDs.put(
						field.getName(),
						TransientFieldManager.NULL_VALUE);
				}
				else {
					mapOfFieldNamesIDs.put(field.getName(), firstClassEntity.getDisplayID());
				}
			}
			catch (final IllegalArgumentException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final IllegalAccessException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final SecurityException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}

		return mapOfFieldNamesIDs;
	}
	public static final Field[] getTransientEntityFields(
		final Class aConstituentClass) {

		final Field[] declaredFields = aConstituentClass.getDeclaredFields();
		final List listOfTransientFields = new ArrayList();
		for (int i = 0; i < declaredFields.length; i++) {
			final Field field = declaredFields[i];
			if (Modifier.isTransient(field.getModifiers())) {
				listOfTransientFields.add(field);
			}
		}

		final Field[] transientFields = new Field[listOfTransientFields.size()];
		listOfTransientFields.toArray(transientFields);
		return transientFields;
	}
}
