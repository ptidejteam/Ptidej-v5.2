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
package padl.kernel.cpp.antlr.impl;

import padl.kernel.IFactory;
import padl.kernel.cpp.antlr.ICPPFactoryANTLR;
import padl.kernel.cpp.antlr.IDestructor;
import padl.kernel.cpp.antlr.IEnum;
import padl.kernel.cpp.antlr.IGlobalField;
import padl.kernel.cpp.antlr.IStructure;
import padl.kernel.cpp.antlr.IUnion;
import padl.kernel.impl.Factory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/07/11
 */
public class CPPFactoryANTLR extends Factory implements ICPPFactoryANTLR {
	private static final long serialVersionUID = 3342247491732965777L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (CPPFactoryANTLR.UniqueInstance == null) {
			CPPFactoryANTLR.UniqueInstance = new CPPFactoryANTLR();
		}
		return CPPFactoryANTLR.UniqueInstance;
	}

	private CPPFactoryANTLR() {
		super();
	}
	public IDestructor createDestructor(final char[] anID, final char[] aName) {
		return new Destructor(anID, aName);
	}
	public IEnum createEnum(final char[] aName) {
		return new Enum(aName);
	}
	public IGlobalField createGlobalField(
		final char[] aName,
		final char[] aType,
		final int aCardinality) {

		return new GlobalField(aName, aType, aCardinality);
	}
	public IStructure createStructure(final char[] aName) {
		return new Structure(aName);
	}
	public IUnion createUnion(final char[] aName) {
		return new Union(aName);
	}
}
