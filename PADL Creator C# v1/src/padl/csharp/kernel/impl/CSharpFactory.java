/*
 * (c) Copyright 2009- Gerardo Cepeda.
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
package padl.csharp.kernel.impl;

import padl.csharp.kernel.ICSharpFactory;
import padl.kernel.IFactory;
import padl.kernel.impl.Factory;

//import padl.kernel.exception.ModelDeclarationException;

/**
 * @author Gerardo Cepeda
 * @since  2009/04/20
 */
public class CSharpFactory extends Factory implements ICSharpFactory {
	private static final long serialVersionUID = -1032539567080323691L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (CSharpFactory.UniqueInstance == null) {
			CSharpFactory.UniqueInstance = new CSharpFactory();
		}
		return CSharpFactory.UniqueInstance;
	}
	private CSharpFactory() {
	}
}
