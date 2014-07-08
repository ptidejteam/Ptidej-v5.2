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
package ptidej.viewer.awt.occurrences;

import java.awt.Color;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/08
 */
interface Constants {
	Color NORMAL_COLOR = Color.BLUE;
	Color HIGHLIGHT_COLOR = Color.RED;

	String NO_OCCURRENCES_LABEL = MultilingualManager.getString(
		"NO_OCCURRENCES_LABEL",
		Constants.class);
	String SHOW_HIDE = MultilingualManager.getString(
		"SHOW_HIDE",
		Constants.class);
	String FOLD_UNFOLD = MultilingualManager.getString(
		"FOLD_UNFOLD",
		Constants.class);
	String FOLD_UNFOLD_ALL = MultilingualManager.getString(
		"FOLD_UNFOLD_ALL",
		Constants.class);
	String FOLD_UNFOLD_OBJECT = MultilingualManager.getString(
		"FOLD_UNFOLD_OBJECT",
		Constants.class);
	String FOLD_UNFOLD_GHOSTS = MultilingualManager.getString(
		"FOLD_UNFOLD_GHOSTS",
		Constants.class);
}
