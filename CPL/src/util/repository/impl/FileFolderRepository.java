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
package util.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import util.io.NamedInputStream;
import util.io.ProxyConsole;
import util.repository.FileAccessException;
import util.repository.IFileRepository;
import util.repository.IRepository;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/07/21
 */
class FileFolderRepository implements IFileRepository {
	private static final void storeFiles(
		final File theCurrentDirectory,
		final List<NamedInputStream> aListOfFiles) throws FileAccessException {

		final String[] files = theCurrentDirectory.list();
		if (files == null) {
			throw new FileAccessException(FileFolderRepository.class.getName()
					+ " cannot find any file in: "
					+ theCurrentDirectory.getAbsolutePath());
		}
		for (int i = 0; i < files.length; i++) {
			final File file =
				new File(theCurrentDirectory.getAbsolutePath()
						+ File.separatorChar + files[i]);
			if (file.isFile()) {
				// Stephane 2008/07/16: Too many FIS open!
				// The following code closes unused FIS
				// to allow analysing large number of files.
				FileInputStream fileInputStream = null;
				try {
					fileInputStream = new FileInputStream(file);
					aListOfFiles.add(new NamedInputStream(file
						.getCanonicalPath(), fileInputStream));
				}
				catch (final FileNotFoundException fnfe) {
					fnfe.printStackTrace(ProxyConsole
						.getInstance()
						.errorOutput());
				}
				catch (final IOException ioe) {
					ioe.printStackTrace(ProxyConsole
						.getInstance()
						.errorOutput());
				}
				finally {
					if (fileInputStream != null) {
						try {
							fileInputStream.close();
						}
						catch (final IOException ioe) {
							ProxyConsole
								.getInstance()
								.warningOutput()
								.print(FileFolderRepository.class.getName());
							ProxyConsole
								.getInstance()
								.warningOutput()
								.println(" cannot close a file!");
						}
					}
				}
			}
			else {
				FileFolderRepository.storeFiles(file, aListOfFiles);
			}
		}
	}
	private static final NamedInputStream[] getMetaModelFiles(final Class<? extends IRepository> aClass)
			throws FileAccessException {

		// Yann 2004/07/28: Demo!
		// I must catch the AccessControlException
		// thrown when attempting loading anything
		// from the applet viewer.
		try {
			// TODO Do not use getClassPath() and make code simpler?
			final StringBuffer directory = new StringBuffer();
			directory.append(util.io.Files.getClassPath(aClass));
			directory.append(aClass.getPackage().getName().replace('.', '/'));
			String finalDirectory = directory.toString().replace('\\', '/');

			ProxyConsole
				.getInstance()
				.warningOutput()
				.print(FileFolderRepository.class.getName());
			ProxyConsole
				.getInstance()
				.warningOutput()
				.print(" is the current repository on: ");
			ProxyConsole.getInstance().warningOutput().println(finalDirectory);

			final File directoryFile = new File(finalDirectory);
			final List<NamedInputStream> listOfFiles = new ArrayList<NamedInputStream>();
			FileFolderRepository.storeFiles(directoryFile, listOfFiles);
			final NamedInputStream[] arrayOfFiles =
				new NamedInputStream[listOfFiles.size()];
			listOfFiles.toArray(arrayOfFiles);
			return arrayOfFiles;
		}
		catch (final AccessControlException ace) {
			return new NamedInputStream[0];
		}
	}
	private NamedInputStream[] fileStreams;
	private final IRepository locator;
	public FileFolderRepository(final IRepository aLocator) {
		this.locator = aLocator;
	}
	public NamedInputStream[] getFiles() throws FileAccessException {
		if (this.fileStreams == null) {
			this.fileStreams =
				FileFolderRepository.getMetaModelFiles(this.locator.getClass());
		}
		return this.fileStreams;
	}
	public String toString() {
		return this.fileStreams.length + " files in repository.";
	}
}
