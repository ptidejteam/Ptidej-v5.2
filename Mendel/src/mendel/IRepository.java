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
package mendel;

import java.util.Collection;

import mendel.model.IEntity;


/**
 * 
 * TODO: genericity of IRepository and repository architecture of Mendel
 * 
 * - generalize IRepository with a type parameter instead of IEntity
 *   - use Item instead of Entity as a generic word
 * - create different implementations of IRepository: IEntity, Map, other model....
 * - the key format is unspecified
 * - each repository at instantiation registers itself in the Mendel object as a repository for:
 *   - a project
 *   - and the type of item stored
 *   - need perhaps some uniformization for the key format
 * - whenever a part needs access to a repository, he should give the project id and the item type.
 * 
 * @author Simon Denier
 * @since Jun 13, 2008
 *
 */
public interface IRepository extends Iterable<IEntity> {

	public IEntity getEntity(String qualifiedName);

	public boolean hasEntityRegistered(String qualifiedName);

	public void registerEntity(String qualifiedName, IEntity entity);

	public int size();

	public Collection getAllEntities();

}