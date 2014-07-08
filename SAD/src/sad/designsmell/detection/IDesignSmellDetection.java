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

package sad.designsmell.detection;

import java.io.PrintWriter;
import java.util.Set;
import padl.kernel.IAbstractLevelModel;
import util.help.IHelpURL;

public interface IDesignSmellDetection extends IHelpURL {
	String getName();
	String getRuleCardFile();
	// TODO: We may have a vocabulary problem: 
	// Do we detect antipatterns or rather micro-architectures 
	// similar to some anti-patters? Similarly to what happens 
	// with design patterns...
	Set getDesignSmells();
	void output(final PrintWriter aWriter);
	void detect(final IAbstractLevelModel anAbstractLevelModel);
}
