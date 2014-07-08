/* (c) Copyright 2001 and following years, Simon Denier, Yann-Gaël Guéhéneuc,
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

package ptidej.viewer.extension.repository;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import mendel.Driver;
import mendel.IRepository;
import mendel.MendelProject;
import mendel.Util;
import mendel.family.RoleTag;
import mendel.filter.AnonymousFilter;
import mendel.filter.ClassFilter;
import mendel.filter.EntityFilter;
import mendel.filter.PackageFilter;
import mendel.metric.IEntityMetric;
import mendel.metric.branch.NoveltyIndex;
import mendel.metric.branch.NoveltyScore;
import mendel.metric.entity.BaseName;
import mendel.metric.entity.EntityName;
import mendel.metric.entity.InhCount;
import mendel.metric.entity.LocalGroupCount;
import mendel.metric.entity.NewCount;
import mendel.metric.entity.OverGroupCount;
import mendel.part.EntitySelection;
import mendel.part.output.PtidejOutput;
import mendel.part.tool.MetricsTool;
import mendel.part.tool.ThresholdTool;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.ViewerCommons;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;
import util.help.IHelpURL;

public final class MendelBloomingClass implements IViewerExtension, IHelpURL {
	public String createMendelParts(String projectName, List parts) {
		EntitySelection sel = new EntitySelection();
		sel.setFilters(new EntityFilter[] { new ClassFilter(),
				new AnonymousFilter(), new PackageFilter(), });

		MetricsTool metrics = new MetricsTool();
		metrics.setMetrics(new IEntityMetric[] { new EntityName(),
				new BaseName(), new NoveltyIndex(), new LocalGroupCount(),
				new NoveltyScore(), new NewCount(), new OverGroupCount(),
				new InhCount(), new RoleTag(), });

		ThresholdTool threshold = new ThresholdTool();
		threshold.initialize(new NoveltyScore().getName(), "Blooming Class");

		PtidejOutput fileOutput = new PtidejOutput();
		fileOutput.setFilename(projectName);
		fileOutput.setQualifier("mendel/metrics/");
		fileOutput.setSuffix("_blooming.ini");

		parts.add(sel);
		parts.add(metrics);
		parts.add(threshold);
		parts.add(fileOutput);

		return fileOutput.fullPath();
	}

	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/ICPC08a.doc.pdf";
	}

	public String getName() {
		return "Mendel Blooming Classes";
	}

	public void invoke(IRepresentation representation) {
		Collection sources =
			representation.getSourceFiles(IRepresentation.TYPE_JAVA_CLASSFILES);
		String projectName = representation.getSourceModel().getDisplayName();
		String[] paths = (String[]) sources.toArray(new String[0]);

		String resultFile = runMendel(projectName, paths);

		Properties prop = Util.loadPropertiesFile(resultFile);
		Occurrence[] occ =
			OccurrenceBuilder.getInstance().getCanonicalOccurrences(prop);
		representation.addOccurrences(occ);
		ViewerCommons.createGroupSolutions(representation);
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Configure Mendel and compute metrics.
	 * @param classpath
	 * @param paths
	 * @return
	 */
	public String runMendel(String projectName, String[] paths) {
		List parts = new Vector();
		MendelProject project = new MendelProject();
		Driver driver = new Driver();

		String resultFile = createMendelParts(projectName, parts);

		project.initialize(projectName, new Driver[] { driver }, paths);
		driver.initialize(project, parts);
		setupRepository(projectName, project);

		project.batchRun();

		return resultFile;
	}

	public void setupRepository(String projectName, MendelProject project) {
		// Bug potential if we change the model creation process
		IRepository repository =
			MendelRepositories.getProjectRepository(projectName);
		if (repository == null) {
			project.populate();
			MendelRepositories.setProjectRepository(
				projectName,
				project.getRepository());
		}
		else {
			project.setRepository(repository);
		}
	}

	public void setVisible(boolean visibility) {
		// TODO Auto-generated method stub

	}

	public void sourceModelAvailable(SourceAndGraphModelEvent sourceModelEvent) {
		// TODO implement?

	}

	public void sourceModelChanged(SourceAndGraphModelEvent sourceModelEvent) {
		// TODO Auto-generated method stub

	}

	public void sourceModelUnavailable() {
		// TODO Auto-generated method stub

	}
}
