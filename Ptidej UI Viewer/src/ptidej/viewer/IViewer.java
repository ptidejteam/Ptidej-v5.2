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
package ptidej.viewer;

import java.awt.event.ActionListener;

import padl.kernel.IAbstractModel;
import ptidej.ui.kernel.builder.Builder;
import util.help.IHelpURL;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/07/17
 */
public interface IViewer {
	void addButton(
		final String buttonLabel,
		final ActionListener listener,
		final boolean isEnabled,
		final boolean isSourceChangeListerner,
		final IHelpURL helpURL);
	void addButton(
		final String buttonLabel,
		final ActionListener listener,
		final boolean isEnabled,
		final boolean isSourceChangeListerner);
	void addSource(
		final IAbstractModel anAbstractModel,
		final Builder aBuilder);
	IRepresentation getRepresentation();
}
