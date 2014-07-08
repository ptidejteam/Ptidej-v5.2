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
package ptidej.viewer.awt;

import padl.event.IModelListener;
import ptidej.ui.awt.AWTCanvas;
import ptidej.viewer.IRepresentation;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/08/20
 */
public interface IAWTRepresentation extends IRepresentation {
	AWTCanvas getAWTCanvas();
	IModelListener getModelStatistics();
	// Yann 2013/09/09: Dual-hierarchical view!
	// By adding the dual-hierarchical representation, I realised
	// that it does not make sense for the representation to have
	// its AWTCanvas set outside, much to risky for inconsistency
	// so I removed this public method. 
	//	void setAWTCanvas(final AWTCanvas anAWTCanvas);
}
