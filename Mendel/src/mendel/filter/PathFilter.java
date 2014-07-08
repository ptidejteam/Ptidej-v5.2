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

public class PathFilter extends EntityFilter {

	private String[] prefixes;
	
	public PathFilter() {
		setPrefixes("");
	}
	
	/**
	 * 
	 * @param prefixes classpath format list of prefix
	 */
	public PathFilter(String prefixes) {		
		setPrefixes(prefixes);
	}
	
	public PathFilter(String[] prefixes) {
		setPrefixes(prefixes);
	}
	
	public boolean accept(IEntity entity) {
		String name = entity.getEntityName();
		String packagePrefix = name.substring(0, name.lastIndexOf('.'));
		String subpath = packagePrefix.replace('.', '/');
		
		boolean pass = false;
		int i = 0;
		while( !pass && i < this.prefixes.length ) {
			pass = this.prefixes[i].endsWith(subpath);
			i++;
		}
		return pass;
	}
	
	public String toString() {
		return "Accept Entities in Path";
	}

	
	public String[] getPrefixes() {
		return this.prefixes;
	}

	public void setPrefixes(String[] prefixes) {
		this.prefixes = prefixes;
	}
	
	public void setPrefixes(String prefixes) {
		setPrefixes(prefixes.split(File.pathSeparator));
	}
	
}