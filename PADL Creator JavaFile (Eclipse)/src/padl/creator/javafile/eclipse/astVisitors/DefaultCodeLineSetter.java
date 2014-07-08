/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.javafile.eclipse.astVisitors;

import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IGetter;
import padl.kernel.IMethod;
import padl.kernel.ISetter;
import padl.util.adapter.WalkerAdapter;

public class DefaultCodeLineSetter extends WalkerAdapter {
	private void open(final IConstituent aConstituent) {
		if (!aConstituent.isAbstract()) {
			aConstituent.setCodeLines(new String[0]);
		}
	}
	public void open(final IConstructor aMethod) {
		this.open((IConstituent) aMethod);
	}
	public void open(final IDelegatingMethod aMethod) {
		this.open((IConstituent) aMethod);
	}
	public void open(final IGetter aMethod) {
		this.open((IConstituent) aMethod);
	}
	public void open(final IMethod aMethod) {
		this.open((IConstituent) aMethod);
	}
	public void open(final ISetter aMethod) {
		this.open((IConstituent) aMethod);
	}
}
