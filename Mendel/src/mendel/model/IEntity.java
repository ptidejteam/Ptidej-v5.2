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
package mendel.model;

import java.util.List;
import java.util.Set;

public interface IEntity {
	
	public String getEntityName();
	
	public String getBaseName();
	
	public Set<JInterfaceEntity> getSuperInterfaces();

	public Set<JInterfaceEntity> getAllSuperInterfaces();
	
	public String getSuperInterfacesNames();

	public Set<String> getLocalMethods();
	
	public Set<String> getSuperMethods();
	
	public Set<String> getAllMethods();

	public Set<String> getNewMethods();	
	
	public Set<String> getInheritedMethods();	
	
	public Set<String> getSharedMethods();
	
	public Set<String> getAbstractMethods();
	
	public String toString(boolean recursive);

	public void addSuperInterface(JInterfaceEntity superInterface);
	
	public void addSubentity(IEntity entity);
	
	public List<IEntity> getSubentities();
	
	public boolean hasSubentities();

	public boolean recursiveLookup();

	public boolean recursiveLookup(final boolean recursive);

}
