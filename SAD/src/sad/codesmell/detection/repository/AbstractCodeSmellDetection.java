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

package sad.codesmell.detection.repository;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import padl.kernel.IAbstractLevelModel;
import sad.kernel.ICodeSmell;
import sad.util.BoxPlot;
import util.io.ProxyConsole;

public abstract class AbstractCodeSmellDetection {
	private BoxPlot boxPlot;
	private Set setOfSmells;

	public AbstractCodeSmellDetection() {
	}
	public abstract void detect(final IAbstractLevelModel anAbtractLevelModel);
	protected BoxPlot getBoxPlot() {
		return this.boxPlot;
	}
	public Set getCodeSmells() {
		return this.setOfSmells;
	}
	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/TSE09.doc.pdf";
	}
	public abstract String getName();
	/** 
	 * Return a file that displays all the smells detected
	 */
	public void output(final PrintWriter aWriter) {
		try {
			aWriter.println();
			aWriter.println("Detection");
			aWriter.println();

			final Iterator iter = this.getCodeSmells().iterator();
			int count = 0;
			while (iter.hasNext()) {
				final ICodeSmell codeSmell = (ICodeSmell) iter.next();

				count++;

				aWriter.println(codeSmell.toString(count));

			}
			aWriter.println("----> Total:" + count);

			aWriter.close();
		}
		catch (final NumberFormatException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	protected void setBoxPlot(final BoxPlot boxPlot) {
		this.boxPlot = boxPlot;
	}
	protected void setSetOfSmells(final Set setOfSmells) {
		this.setOfSmells = setOfSmells;
	}
}
