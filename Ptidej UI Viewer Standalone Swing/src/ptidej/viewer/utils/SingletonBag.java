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
package ptidej.viewer.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import ptidej.viewer.ui.IWindow;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/08/20
 */
public class SingletonBag {
	private static Map Windows = new HashMap();
	public static IWindow getInstance(final Class aWindow) {
		if (!SingletonBag.Windows.containsKey(aWindow)) {
			try {
				final IWindow window =
					(IWindow) aWindow
						.getDeclaredConstructor(new Class[0])
						.newInstance(new Object[0]);
				SingletonBag.Windows.put(aWindow, window);
			}
			catch (final IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (final SecurityException e) {
				e.printStackTrace();
			}
			catch (final InstantiationException e) {
				e.printStackTrace();
			}
			catch (final IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (final InvocationTargetException e) {
				e.printStackTrace();
			}
			catch (final NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return (IWindow) SingletonBag.Windows.get(aWindow);
	}
}
