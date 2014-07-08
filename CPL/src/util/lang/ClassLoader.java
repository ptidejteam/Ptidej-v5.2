/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package util.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;

public class ClassLoader extends java.lang.ClassLoader {
	private final String directory;

	public ClassLoader(
		final java.lang.ClassLoader parent,
		final String directory) {

		super(parent);
		this.directory = directory;
	}

	private Class<?> defineClasses(final String name, final InputStream inputStream) {
		try {
			int b;
			int length = 0;
			byte[] bytes = new byte[4];
			while ((b = inputStream.read()) != -1) {
				if (length == bytes.length) {
					final byte[] temp = new byte[length + 4];
					System.arraycopy(bytes, 0, temp, 0, length);
					bytes = temp;
				}
				bytes[length] = (byte) b;
				length++;
			}

			System.out.println(name);
			final Class<?> newClass = this.defineClass(name, bytes, 0, length);
			return newClass;
		}
		catch (final IOException ioe) {
			return null;
		}
		catch (final NoClassDefFoundError ncdfe) {
			// This error happens when the defineClass(...) method
			// defines a class for which it cannot find dependent
			// classes. For example, this error happens when the
			// defineClass(...) method defines class:
			//     fr.emn.claire.ClairePlugin
			// while the following class is absent:
			//     org.eclipse.ui.plugin.AbstractUIPlugin

			ncdfe.printStackTrace(ProxyConsole.getInstance().errorOutput());
			return null;
		}
	}
	protected Class<?> findClass(final String name) {
		Class<?> newClass = null;
		try {
			//	newClass = this.getParent().loadClass(name);
			newClass = super.findClass(name);
		}
		catch (final ClassNotFoundException cnfe) {
			final String osName =
				this.directory + name.replace('.', '/') + ".class";

			try {
				final FileInputStream fis = new FileInputStream(osName);
				newClass = this.defineClasses(name, fis);
			}
			catch (final ClassFormatError cfe) {
				ProxyConsole
					.getInstance()
					.errorOutput()
					.print(
						MultilingualManager.getString(
							"Err_FILE",
							ClassLoader.class,
							new Object[] { osName }));
				cfe.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final FileNotFoundException fnfe) {
				// fnfe.printStackTrace();
			}
		}

		return newClass;
	}
	protected URL findResource(final String name) {
		URL url = super.findResource(name);

		if (url == null) {
			try {
				url = new URL("file", "", "/" + this.directory);
			}
			catch (final MalformedURLException mfue) {
				mfue.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}

		return url;
	}
	//	public Class loadClass(String name, boolean resolve)
	//		throws ClassNotFoundException {
	//		// First, check if the class has already been loaded
	//		Class c = this.findLoadedClass(name);
	//
	//		if (c == null || resolve) {
	//			try {
	//				c = this.getParent().loadClass(name);
	//			}
	//			catch (final ClassNotFoundException e) {
	//				// If still not found, then call findClass in order
	//				// to find the class.
	//				c = this.findClass(name);
	//			}
	//		}
	//		if (resolve) {
	//			this.resolveClass(c);
	//		}
	//
	//		return c;
	//	}
	//	public Class loadClass(final String name, final JarFile jarFile) {
	//		Class newClass = null;
	//		try {
	//			newClass = super.findClass(name);
	//		}
	//		catch (final ClassNotFoundException cnfe) {
	//			final String osName = name.replace('.', '/') + ".class";
	//
	//			try {
	//				newClass =
	//					this.defineClasses(name, jarFile.getInputStream(jarFile
	//						.getJarEntry(osName)));
	//			}
	//			catch (final ClassFormatError cfe) {
	//				Output.getInstance().errorOutput().print(
	//					MultilingualManager.getString(
	//						"Err_FILE",
	//						ClassLoader.class,
	//						new Object[] { osName }));
	//				cfe.printStackTrace(Output.getInstance().errorOutput());
	//			}
	//			catch (final FileNotFoundException fnfe) {
	//				// fnfe.printStackTrace();
	//			}
	//			catch (final IOException ioe) {
	//				// ioe.printStackTrace();
	//			}
	//		}
	//
	//		return newClass;
	//	}
}