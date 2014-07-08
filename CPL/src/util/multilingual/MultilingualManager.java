/*
 * (c) Copyright 2001-2004 Yann-Gaï¿½l Guï¿½hï¿½neuc,
 * University of Montrï¿½al.
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
package util.multilingual;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import util.io.ProxyConsole;

/**
 * Since we will not supply any Locale when getting the Bundle,
 * the program will then use the default Locale, and for our case,
 * the bundle will be "PtidejResourceBundle".
 * If you want to hardcode a specific "Locale" (i.e, Japanese),
 * you can do so by
 * <code>ResourceBundle res = ResourceBundle.getBundle("util.multilanguage.PtidejResourceBundle", new Locale("ja","JP"));</code>
 * Also, don't forget to import the java.util.Locale class.
 * <code>import java.util.Locale</code>
 * 
 * @author Mehdi Lahlou
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/07/07
 */
public class MultilingualManager {
	private static final String BUNDLE_NAME =
		"util.multilingual.PtidejResourceBundle";
	private static ResourceBundle Bundle;
	static {
		try {
			MultilingualManager.Bundle =
				ResourceBundle.getBundle(MultilingualManager.BUNDLE_NAME);
		}
		catch (final MissingResourceException mre) {
			mre.printStackTrace(ProxyConsole.getInstance().errorOutput());
			ProxyConsole
				.getInstance()
				.errorOutput()
				.println("Use default english locale");
			MultilingualManager.Bundle =
				ResourceBundle.getBundle(
					MultilingualManager.BUNDLE_NAME,
					Locale.ENGLISH);
		}
	}
	public static String getString(final String aKey) {
		try {
			return MultilingualManager.Bundle.getString(aKey);
		}
		catch (final MissingResourceException mre) {
			mre.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return "<MISSING>";
		}
	}
	public static String getString(final String aKey, final Class<?> aClass) {
		try {
			return MultilingualManager.Bundle.getString(aClass.getName() + "::"
					+ aKey);
		}
		catch (final MissingResourceException mre) {
			mre.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return "<MISSING>";
		}
	}
	public static String getString(
		final String aKey,
		final Class<?> aClass,
		final Object[] someArguments) {

		try {
			final MessageFormat formatter =
				new MessageFormat(MultilingualManager.getString(aKey, aClass));
			return formatter.format(someArguments);
		}
		catch (final MissingResourceException mre) {
			mre.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return "<MISSING>";
		}
	}

	private MultilingualManager() {
	}
}
