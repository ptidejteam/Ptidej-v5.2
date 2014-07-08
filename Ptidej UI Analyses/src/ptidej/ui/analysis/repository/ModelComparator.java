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

import padl.creator.aolfile.AOLCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import ptidej.ui.analysis.IUIAnalysis;
import ptidej.ui.analysis.repository.comparator.Comparator;
import ptidej.ui.analysis.repository.comparator.Manager;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;
import util.io.ExtensionBasedFilenameFilter;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/12
 */
public class ModelComparator implements IUIAnalysis {
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
		return MultilingualManager.getString("NAME", ModelComparator.class);
	}
	public IAbstractModel invoke(final IAbstractModel abstractModel) {
		final FileDialog fileDialog = new FileDialog(new Frame());
		fileDialog.setTitle(
			MultilingualManager.getString(
				"DIALOG_TITLE",
				ModelComparator.class));
		fileDialog.setFilenameFilter(
			new ExtensionBasedFilenameFilter(".aol"));
		fileDialog.setFile("*.aol");
		fileDialog.setMode(FileDialog.LOAD);
		fileDialog.setModal(true);
		fileDialog.setVisible(true);
		if (fileDialog.getFile() == null) {
			return abstractModel;
		}
		final String path =
			util.io.Files.normalizePath(
				fileDialog.getDirectory() + fileDialog.getFile());

		final String[] aolFilesArray = new String[] { path };
		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel(
				"Dummy AOL code-level model");
		try {
			codeLevelModel.create(new AOLCreator(aolFilesArray));
		}
		catch (final CreationException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final IAbstractModel newAbstractModel =
			(IAbstractModel) abstractModel.walk(
				new Comparator(codeLevelModel));

		ProxyConsole.getInstance().normalOutput().println(
			"Number of added elements: "
				+ Manager.getUniqueInstance().getNumberOfAddedElements());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of added entities: "
				+ Manager.getUniqueInstance().getNumberOfAddedEntities());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of added methods: "
				+ Manager.getUniqueInstance().getNumberOfAddedMethods());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of modified entities: "
				+ Manager.getUniqueInstance().getNumberOfModifiedEntities());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of removed elements: "
				+ Manager.getUniqueInstance().getNumberOfRemovedElements());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of removed entities: "
				+ Manager.getUniqueInstance().getNumberOfRemovedEntities());
		ProxyConsole.getInstance().normalOutput().println(
			"Number of remove methods: "
				+ Manager.getUniqueInstance().getNumberOfRemovedMethods());

		return newAbstractModel;
	}
}
