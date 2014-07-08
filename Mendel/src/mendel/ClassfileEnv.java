/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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
package mendel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.bcel.Repository;
import org.apache.bcel.util.ClassPath;
import org.apache.bcel.util.SyntheticRepository;


/**
 * There is a bootstrap process where we load classes with BCEL to retrieve
 * packages, classes, and classpath.
 * 
 * Unfortunately this does not work if some classes share the same base name
 * (but belong to different packages). Then we will always hit the same class
 * when looking only the base name.
 * 
 * Solution: we assume that the path in constructor is the classpath.
 * We dont not compute the classpath anymore.
 * 
 * 
 * @author deniersi
 *
 */
public class ClassfileEnv {
	
	private File[] classfiles;
	
	private String[] classpath;
	
	private String classpathStr;
	
	private String[] packages;
	
	private String[] classnames;
	
	
	public ClassfileEnv(String[] classpath) {
		this.classpath = classpath;
		this.classpathStr = Util.join(Arrays.asList(classpath), File.pathSeparator, new StringBuffer());
		this.classfiles = Util.extractClassFiles(classpath);
		initialize();
	}

	public File[] getClassfiles() {
		return this.classfiles;
	}
	
	public String getClasspath() {
		return this.classpathStr;
	}
	
	public String[] getPackages() {
		return this.packages;
	}
	
	
	public String[] getClassnames() {
		return this.classnames;
	}


	public void initialize() {
		setupBcelRepository(getClasspath());
		extractClassnames();
		extractPackages();
		
//		String lookupPaths = extractPath(this.classfiles);
//		extractClassnames();
//		extractPackages(lookupPaths);
//		extractClasspath(lookupPaths);
//		setupBcelRepository(getClasspath());
	}
	
	
	public static String getQualifiedName(String classname) {
		if( classname.endsWith(".class") ) {
			classname = classname.substring(0, classname.lastIndexOf('.'));
		}
		return classname.replace(File.separatorChar, '.');
	}

	private void setupBcelRepository(String classpath) {
		Repository.setRepository(SyntheticRepository.getInstance(new ClassPath(classpath)));
	}
	
	private void extractClassnames() {
		this.classnames = new String[this.classfiles.length];

		for (int i = 0; i < this.classfiles.length; i++) {
			try {
				String filepath = this.classfiles[i].getCanonicalPath();
				String classp = null;
				for (int j = 0; j < this.classpath.length; j++) {
					classp = filepath.replaceAll(this.classpath[j], "");	
				}
				this.classnames[i] = getQualifiedName(classp);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void extractPackages() {
		Set packages = new HashSet();
		for (int i = 0; i < this.classnames.length; i++) {
			String classname = this.classnames[i];
			try {
				packages.add(classname.substring(0, classname.lastIndexOf('.')));	
			} catch (StringIndexOutOfBoundsException e) {
				System.err.println("Warning: " + classname + " in default package");
			}
		}
		this.packages = (String[]) packages.toArray(new String[0]);
	}
	
	
//	private void extractClassnames() {
//		this.classnames = new String[this.classfiles.length];
//		for (int i = 0; i < this.classfiles.length; i++) {
//			this.classnames[i] = Util.extractBasename(this.classfiles[i]);
//		}
//		
//	}


//	private void extractPackages(String lookupPaths) {
//		setupBcelRepository(lookupPaths);
//		Set packages = new HashSet();
//		
//		for (int i = 0; i < this.classnames.length; i++) {
//			try {
//				JavaClass clazz = Repository.lookupClass(this.classnames[i]);
//				packages.add(clazz.getPackageName());
//				this.classnames[i] = clazz.getClassName(); // update full name
//			}
//			catch (ClassNotFoundException e) {
//				System.err.println(e.getMessage());
//			}			
//		}
//		this.packages = (String[]) packages.toArray(new String[0]);
//	}


//	private void extractClasspath(String lookupPaths) {
//		String paths = lookupPaths;
//		for (int i = 0; i < this.packages.length; i++) {
//			paths = paths.replace(this.packages[i].replace('.', File.separatorChar), "");	
//		}
//		// flatten classpath after deleting package suffix
//		String[] classpaths = paths.split(File.pathSeparator);
//		Set cpath = new HashSet();
//		for (int i = 0; i < classpaths.length; i++) {
//			cpath.add(classpaths[i]);
//		}
//		this.classpathStr = Util.join(cpath, File.pathSeparator, new StringBuffer());
//	}
	
	
//	public String extractPath(File[] classfiles) {
//		// Retrieve path from classfiles
//		Set cpaths = new HashSet();
//		for (int i = 0; i < classfiles.length; i++) {
//			cpaths.add(classfiles[i].getParent());
//		}
//		// Build path
//		return Util.join(cpaths, File.pathSeparator, new StringBuffer());		
//	}
	
}
