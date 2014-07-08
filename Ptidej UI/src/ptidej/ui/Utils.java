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
package ptidej.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import util.io.ProxyConsole;

public final class Utils {
	private static Map MapOfNameIcon = new HashMap();

	public static Icon getIcon(final String aImageName) {
		// Yann 2014/04/27: Speed!
		// I cache the icons to reuse them because creating
		// a new one at each call is too expansive in time.
		Icon icon = null;

		if ((icon = (Icon) Utils.MapOfNameIcon.get(aImageName)) == null) {
			try {
				icon = new ImageIcon(Utils.getImage(aImageName));
				Utils.MapOfNameIcon.put(aImageName, icon);
			}
			catch (final RuntimeException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}

		return icon;
	}
	public static Image getImage(final String aImageName) {
		try {
			return Toolkit.getDefaultToolkit().getImage(
				Utils.getImagePath(aImageName));
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return null;
		}
	}
	public static URL getImagePath(final String aImageName) {
		// Yann 2007/03/27: URL and files.
		// I replaced the use of the class loader by a call
		// to the file system to access resources: simpler
		// and cleaner :-)
		// Yann 2013/07/06: URL and files broken!
		// However, this simpler and cleaner version only
		// works with the file-system; it does not work
		// with JAR files... so I must resort to manage the
		// resources using Java... 
		//	try {
		//		return new File(util.io.Files.getClassPath(Desktop.class)
		//				+ Utils.IMAGES_PATH + strImageKey).toURI().toURL();
		//	}
		//	catch (final MalformedURLException e) {
		//		e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		//		return null;
		//	}
		final URL url = Utils.class.getResource("/images/" + aImageName);
		ProxyConsole.getInstance().debugOutput().print(Utils.class.getName());
		ProxyConsole.getInstance().debugOutput().print(" uses the image URL: ");
		ProxyConsole.getInstance().debugOutput().print(url);
		ProxyConsole.getInstance().debugOutput().print(" (");
		ProxyConsole.getInstance().debugOutput().print(aImageName);
		ProxyConsole.getInstance().debugOutput().println(')');
		return url;
	}
	private Utils() {
	}
}
