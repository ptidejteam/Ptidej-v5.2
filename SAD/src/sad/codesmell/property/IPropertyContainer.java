/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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

package sad.codesmell.property;

import java.util.Iterator;
import java.util.Set;

/**
 * Allow a property to contain other properties.
 *
 * @author Pierre Leduc
 * @version 1.0
 * @since 2006/05/26
 */
public interface IPropertyContainer {

	/**
	 * Get an Iterator on the contained property
	 * 
	 * @return The Iterator on the contained property
	 */
	public Iterator getIteratorOnProperty();

	/**
	 * Add a nested property to this property.
	 * 
	 * @param prop The property to be added
	 */
	public void addProperty(final ICodeSmellProperty prop) throws Exception;

	/**
	 * Add a collection of properties to this property.
	 * 
	 * @param propSet The collection to be added
	 */
	public void addProperties(final Set propSet) throws Exception;
}
