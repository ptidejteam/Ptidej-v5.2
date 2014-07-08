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
package ptidej.ui.analysis.repository;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import padl.kernel.IAbstractModel;
import ptidej.ui.analysis.IUIAnalysis;
import ptidej.ui.analysis.repository.comparator.HighlighterFromMethods;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;
import util.io.ExtensionBasedFilenameFilter;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/20
 */
public class DifferenceHighlighterFromMethods implements IUIAnalysis {
	public Builder createBuilder(final IPrimitiveFactory aPrimitiveFactory) {
		return ptidej
			.ui
			.analysis
			.repository
			.comparator
			.AnalysesBuilder
			.getCurrentBuilder(
			aPrimitiveFactory);
	}
	public String getName() {
		return MultilingualManager.getString(
			"NAME",
			DifferenceHighlighterFromMethods.class);
	}
	public IAbstractModel invoke(final IAbstractModel abstractModel) {
		final FileDialog fileDialog = new FileDialog(new Frame());
		fileDialog.setTitle(
			MultilingualManager.getString(
				"DIALOG_TITLE",
				DifferenceHighlighterFromMethods.class));
		fileDialog.setFilenameFilter(
			new ExtensionBasedFilenameFilter(".ini"));
		fileDialog.setFile("*.ini");
		fileDialog.setMode(FileDialog.LOAD);
		fileDialog.setModal(true);
		fileDialog.setVisible(true);
		if (fileDialog.getFile() == null) {
			return abstractModel;
		}
		final String path =
			util.io.Files.normalizePath(
				fileDialog.getDirectory() + fileDialog.getFile());

		final Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
			return (IAbstractModel) abstractModel.walk(
				new HighlighterFromMethods(properties));
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		return abstractModel;
	}
}
