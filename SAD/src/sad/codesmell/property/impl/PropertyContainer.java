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

package sad.codesmell.property.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sad.codesmell.property.ICodeSmellProperty;
import sad.codesmell.property.IPropertyContainer;

/**
 * This class encapsulate the fonctionnalty to
 * manage nested properties
 *
 * @author Pierre Leduc
 * @version 1.0
 * @since 2006/05/29
 */
public class PropertyContainer implements IPropertyContainer {
	final private Set contents;

	public PropertyContainer() {
		super();

		this.contents = new HashSet();
	}

	public Iterator getIteratorOnProperty() {
		return this.contents.iterator();
	}

	public void addProperty(final ICodeSmellProperty prop) throws Exception {
		this.contents.add(prop);
	}

	public void addProperties(final Set propSet) throws Exception {
		this.contents.addAll(propSet);
	}

	public String toString(final int count, final String codesmellName) {
		final StringBuffer buffer = new StringBuffer();
		final Iterator iter = this.contents.iterator();

		int propertyCount = 0;
		while (iter.hasNext()) {
			
			final ICodeSmellProperty prop = (ICodeSmellProperty) iter.next();
			buffer.append(prop.toString(count, propertyCount, codesmellName));
			
			propertyCount ++;
		}

		return buffer.toString();
	}
}
