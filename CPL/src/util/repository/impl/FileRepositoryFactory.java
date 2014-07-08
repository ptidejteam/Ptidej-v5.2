package util.repository.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import util.io.ProxyConsole;
import util.repository.FileAccessException;
import util.repository.IFileRepository;
import util.repository.IRepository;

public class FileRepositoryFactory {
	private static final IRepository DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES =
		new IRepository() {};
	private static int ECLIPSE_BUNDLE_REPOSITORY = 1;
	private static int FILE_FOLDER_REPOSITORY = 0;
	private static int FLAT_JAR_FILE_REPOSITORY = 3;
	private static int JAR_IN_JAR_FILE_REPOSITORY = 2;
	private static FileRepositoryFactory UniqueInstance;
	private static File RunningPath;
	private static int UNKNOWN = -1;
	public static FileRepositoryFactory getInstance() {
		if (FileRepositoryFactory.UniqueInstance == null) {
			FileRepositoryFactory.UniqueInstance = new FileRepositoryFactory();
		}
		return FileRepositoryFactory.UniqueInstance;
	}
	public static File getRunningPath() {
		if (FileRepositoryFactory.RunningPath == null) {
			String path;
			// Yann 2013/08/14: Test of the JAR-in-JAR version of Ptidej
			// If this system property is set, then I use it and assume
			// that it points to the JAR-in-JAR :-)
			final String pathToJarInJar = System.getenv("ptidej.jarinjar.path");
			if (pathToJarInJar == null) {
				// Yann 2013/07/06: Crapy piece of code because of Sun!
				// See http://stackoverflow.com/questions/4114702/how-to-get-name-of-jar-which-is-a-desktop-application-run-from
				path =
					FileRepositoryFactory.class
						.getProtectionDomain()
						.getCodeSource()
						.getLocation()
						.getPath();
				final File tentativePath = new File(path);
				if (tentativePath.isDirectory()) {
					//	final String commandArgs =
					//		System.getProperty("sun.java.command");
					// Yann: I set the path plus add the arguments if any.
					// path += commandArgs;
				}
				else {
					try {
						path = URLDecoder.decode(path, "UTF-8");
					}
					catch (final UnsupportedEncodingException e) {
						e.printStackTrace(ProxyConsole
							.getInstance()
							.errorOutput());
					}
				}
			}
			else {
				path = pathToJarInJar;
			}

			ProxyConsole
				.getInstance()
				.warningOutput()
				.print(FileRepositoryFactory.class.getName());
			ProxyConsole
				.getInstance()
				.warningOutput()
				.print(" has for running path: ");

			ProxyConsole.getInstance().warningOutput().println(path);
			FileRepositoryFactory.RunningPath = new File(path);
		}
		return FileRepositoryFactory.RunningPath;
	}

	private Map<IRepository, IFileRepository> repositories = new HashMap<IRepository, IFileRepository>();
	private int type = FileRepositoryFactory.UNKNOWN;
	public FileRepositoryFactory() {
		// Yann 2013/07/02: Cases
		// I manage three cases:
		// - the simple, regular case when running from class path;
		// - the case when running from a JAR file;
		// - the case when running from as an Eclipse plug-in.
		final ClassLoader cl = FileRepositoryFactory.class.getClassLoader();

		try {
			// Check if run within env like eclipse
			final Class<?> workspaceClass =
				cl.loadClass("org.eclipse.core.resources.IWorkspace");
			final Class<?> resourcesClass =
				cl.loadClass("org.eclipse.core.resources.ResourcesPlugin");
			if (workspaceClass != null || resourcesClass != null) {
				// In eclipse
				final Method getWorkspace =
					resourcesClass.getDeclaredMethod(
						"getWorkspace",
						new Class[0]);
				final Object o =
					getWorkspace.invoke(resourcesClass, new Object[] {});
				if (o != null) {
					// There is a workspace, we should use eclipse bundles
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(this.getClass().getName());
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(" is creating an EclipseBundleRepository for: ");
					ProxyConsole
						.getInstance()
						.warningOutput()
						.println(EclipseBundleRepository.class.getName());

					this.type = FileRepositoryFactory.ECLIPSE_BUNDLE_REPOSITORY;
				}
				else {
					throw new RuntimeException(
						"FileRepositoryBuilder cannot invoke ResourcesPlugin");
				}
			}
			else {
				throw new RuntimeException(
					"FileRepositoryBuilder cannot find either IWorkspace or ResourcesPlugin");
			}
		}
		catch (final Exception e) {
			// Warning! Eclipse of course does funny things...
			try {
				final Class<?> jarInJarLoader =
					cl
						.loadClass("org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader");

				ProxyConsole
					.getInstance()
					.warningOutput()
					.print(this.getClass().getName());
				ProxyConsole
					.getInstance()
					.warningOutput()
					.print(" is creating a JarFileRepository for: ");
				ProxyConsole
					.getInstance()
					.warningOutput()
					.println(jarInJarLoader.getName());

				this.type = FileRepositoryFactory.JAR_IN_JAR_FILE_REPOSITORY;
			}
			catch (final ClassNotFoundException cnfe) {
				final File path = FileRepositoryFactory.getRunningPath();
				if (path.isDirectory()) {
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(this.getClass().getName());
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(" is creating a ClassFileRepository for: ");
					ProxyConsole
						.getInstance()
						.warningOutput()
						.println(FileFolderRepository.class.getName());

					this.type = FileRepositoryFactory.FILE_FOLDER_REPOSITORY;
				}
				else {
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(this.getClass().getName());
					ProxyConsole
						.getInstance()
						.warningOutput()
						.print(" is creating a JarFileRepository for: ");
					ProxyConsole
						.getInstance()
						.warningOutput()
						.println(JarFileRepository.class.getName());

					this.type = FileRepositoryFactory.FLAT_JAR_FILE_REPOSITORY;
				}
			}
		}
	}
	public IFileRepository getFileRepository(final IRepository aRepository) {
		if (!this.repositories.containsKey(aRepository)
				&& !this.repositories
					.containsKey(FileRepositoryFactory.DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES)) {

			if (this.type == FileRepositoryFactory.ECLIPSE_BUNDLE_REPOSITORY) {
				// TODO Implement!
				//	final EclipseBundleRepository eclipseRepository =
				//		new EclipseBundleRepository(cl, resourceNames);
				final EclipseBundleRepository eclipseRepository = null;
				this.repositories.put(aRepository, eclipseRepository);
			}
			else if (this.type == FileRepositoryFactory.FILE_FOLDER_REPOSITORY) {
				this.repositories.put(aRepository, new FileFolderRepository(
					aRepository));
			}
			else if (this.type == FileRepositoryFactory.FLAT_JAR_FILE_REPOSITORY) {
				this.repositories
					.put(
						FileRepositoryFactory.DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES,
						JarFileRepository.getInstance(FileRepositoryFactory
							.getRunningPath()
							.getAbsolutePath()));
			}
			else if (this.type == FileRepositoryFactory.JAR_IN_JAR_FILE_REPOSITORY) {
				this.repositories
					.put(
						FileRepositoryFactory.DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES,
						JarFileRepository.getInstance(FileRepositoryFactory
							.getRunningPath()
							.getAbsolutePath()));
			}
			else {
				throw new RuntimeException(
					new FileAccessException(
						"FileRepositoryFactory cannot identify the type of repository!"));
			}
		}

		if (this.repositories
			.containsKey(FileRepositoryFactory.DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES)) {

			return (IFileRepository) this.repositories
				.get(FileRepositoryFactory.DUMMY_REPOSITORY_FOR_JAR_FILE_REPOSITORIES);
		}
		else {
			return (IFileRepository) this.repositories.get(aRepository);
		}
	}
}
