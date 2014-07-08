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
package padl.serialiser.util;

import padl.kernel.IAbstractModel;

/**
 * A simple wrapper to easily retrieve an abstract model
 * from the database, whaterver it is really (a DesignMotif,
 * a ICodeLevelModel, and so on)
 * 
 * @author Yann
 * 2009/02/23
 */
public class AbstractModelWrapper {
	private final IAbstractModel abstractModel;
	public AbstractModelWrapper(final IAbstractModel anAbstractModel) {
		this.abstractModel = anAbstractModel;
	}
	public IAbstractModel getAbstractModel() {
		return this.abstractModel;
	}
}
