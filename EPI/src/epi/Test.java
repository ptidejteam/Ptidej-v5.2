/*
 * Created on 2005-10-08
 *
 */
package epi;

import java.util.Iterator;
import java.util.List;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;
import epi.solver.problem.Composite;

/**
 * @author OlivierK
 *
 */
public class Test {

	public static ICodeLevelModel createModel(
		final String path,
		final String[] packageNames) {
		String[] computePaths = null;

		if (packageNames != null) {
			computePaths = new String[packageNames.length];
			for (int i = 0; i < packageNames.length; i++) {
				computePaths[i] = path + packageNames[i].replace('.', '/');
			}
		}
		else {
			computePaths = new String[1];
			computePaths[0] = path;
		}

		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("JHotDraw");
		//Factory.getUniqueInstance().createIdiomLevelModel("Model");
		try {
			codeLevelModel.create(new CompleteClassFileCreator(
				computePaths,
				true));

			if (packageNames != null) {
				// TODO: This code must be tested!
				final Iterator entities =
					codeLevelModel.getIteratorOnTopLevelEntities();
				while (entities.hasNext()) {
					final IFirstClassEntity firstClassEntity =
						(IFirstClassEntity) entities.next();
					final String entityName = firstClassEntity.getDisplayName();
					final String packageName =
						entityName.substring(0, entityName.lastIndexOf('.'));
					boolean toBeRemoved = true;
					for (int i = 0; i < packageNames.length && toBeRemoved; i++) {
						if (packageName.equals(packageNames[i])) {
							toBeRemoved = false;
						}
					}
					if (toBeRemoved) {
						codeLevelModel.removeConstituentFromID(entityName
							.toCharArray());
					}
				}
			}
		}
		catch (final CreationException e) {
		}
		return codeLevelModel;
	}

	public static void main(final String[] args) {
		final ICodeLevelModel model =
			Test.createModel("../JHotDraw v5.1/bin/CH/ifa/", null);
		final Composite co = new Composite(model);
		final List leafCandidate = co.getVariableDomain("leaf");
		ProxyConsole.getInstance().normalOutput().println(leafCandidate);
	}
}
