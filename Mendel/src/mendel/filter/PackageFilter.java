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
/**
 * 
 */
package mendel.filter;

import java.io.File;

import mendel.model.IEntity;

public class PackageFilter extends EntityFilter {

	private String[] packages;
	
	public PackageFilter() {
		setPackages("");
	}
	
	/**
	 * 
	 * @param packages classpath format list of prefix
	 */
	public PackageFilter(String packages) {		
		setPackages(packages);
	}
	
	public PackageFilter(String[] packages) {
		setPackages(packages);
	}
	
	public boolean accept(IEntity entity) {
		String name = entity.getEntityName();
		String packagePrefix;
		try {
			packagePrefix = name.substring(0, name.lastIndexOf('.'));			
		}
		catch (StringIndexOutOfBoundsException e) {
			packagePrefix = ""; //NOTE: dealing with the rare case where we have default package
		}
		
		boolean pass = false;
		int i = 0;
		while( !pass && i < this.packages.length ) {
			pass = packagePrefix.startsWith(this.packages[i]); 
//				this.packages[i].endsWith(subpath);
			i++;
		}
		return pass;
	}
	
	public String toString() {
		return "Accept Entities in Packages";
	}

	
	public String[] getPackages() {
		return this.packages;
	}

	public void setPackages(String[] packages) {
		this.packages = packages;
	}
	
	public void setPackages(String packages) {
		setPackages(packages.split(File.pathSeparator));
	}
	
}