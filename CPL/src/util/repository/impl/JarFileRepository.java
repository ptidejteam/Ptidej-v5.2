/*
 * (c) Copyright 2001-2008 Yann-Gaël Guéhéneuc, Nelson Cabral
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
package util.repository.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarFile;
import util.io.NamedInputStream;
import util.io.ProxyConsole;
import util.repository.IFileRepository;

/**
 * @author Yann-Gaël Guéhéneuc 
 * @since 2009/03/03
 */
class JarFileRepository implements IFileRepository {
	private static String JarFile;
	private static JarFileRepository UniqueInstance;
	// TODO Should not be a false Singleton!
	public static JarFileRepository getInstance(final String aJARFile) {
		if (JarFileRepository.UniqueInstance == null
				|| JarFileRepository.JarFile != aJARFile) {

			JarFileRepository.JarFile = aJARFile;
			JarFileRepository.UniqueInstance =
				new JarFileRepository(JarFileRepository.JarFile);
		}
		return JarFileRepository.UniqueInstance;
	}

	private final NamedInputStream[] fileStreams;
	private JarFileRepository(final String aJARFile) {
		ProxyConsole
			.getInstance()
			.warningOutput()
			.print(this.getClass().getName());
		ProxyConsole
			.getInstance()
			.warningOutput()
			.print(" is the current repository on: ");
		ProxyConsole.getInstance().warningOutput().println(aJARFile);

		final List<NamedInputStream> listOfStreams = new ArrayList<NamedInputStream>();
		try {
			final JarFile jarFile = new JarFile(aJARFile);
			final Enumeration<?> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				final JarEntry entry = (JarEntry) entries.nextElement();
				listOfStreams.add(new NamedInputStream(entry.getName(), jarFile
					.getInputStream(entry)));
			}
			jarFile.close();
		}
		catch (final JarException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		this.fileStreams = new NamedInputStream[listOfStreams.size()];
		listOfStreams.toArray(this.fileStreams);
	}
	public NamedInputStream[] getFiles() {
		return this.fileStreams;
	}
	public String toString() {
		return this.fileStreams.length + " files in repository.";
	}
}
