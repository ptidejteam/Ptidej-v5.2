/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
package ptidej.solver.fingerprint;

import java.util.Iterator;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Factory;
import pom.metrics.MetricsRepository;
import util.io.ProxyConsole;

/**
 * @author Jean-Yves Guillomarc'h
 * @since  2004/10/14
 */
public class ReducedDomainBuilder {
	private final IAbstractLevelModel abstractLevelModel;
	private final MetricsRepository metrics;

	// Yann 2004/12/03: Dependencies
	// I remove the following to constructors because they
	// depend on the CompleteClassFileCreator (from project
	// PADL ClassFile Creator) which we don't want: This
	// domain builder should work the same way with C++,
	// Java, or... languages.
	//	public ReducedDomainBuilder(
	//		final String path,
	//		final String[] packageNames) {
	//
	//		this.idiomLevelModel =
	//			Factory.getUniqueInstance().createIdiomLevelModel("localModel");
	//		this.idiomLevelModel.create(
	//			new CompleteClassFileCreator(
	//				DefaultFileRepository.getDefaultFileRepository(),
	//				new String[] { path },
	//				true));
	//
	//		this.packageNames = packageNames;
	//		this.metrics = Metrics.getUniqueInstance(idiomLevelModel);
	//		this.listOfMetrics = Metrics.class.getDeclaredMethods();
	//	}
	//	public ReducedDomainBuilder(final String path) {
	//		this(path, null);
	//
	//	}
	public ReducedDomainBuilder(final IAbstractLevelModel anAbstractLevelModel) {
		this.abstractLevelModel = anAbstractLevelModel;
		this.metrics = MetricsRepository.getInstance();
	}

	//	public List computeReducedDomain(final Rule rule) {
	//		final ArrayList restrictedList = new ArrayList();
	//
	//		// Test rules on each entity of the model
	//		final Iterator iterator =
	//			this.idiomLevelModel.listOfConstituents().iterator();
	//		try {
	//			while (iterator.hasNext()) {
	//				final IEntity entity = (IEntity) iterator.next();
	//				if (!rule
	//					.compute(this.metrics, this.listOfMetrics, entity)) {
	//
	//					restrictedList.add(entity);
	//				}
	//			}
	//		}
	//		catch (final Exception e) {
	//			e.printStackTrace();
	//		}
	//		finally {
	//			// Return restricted list
	//			return restrictedList;
	//		}
	//	}
	public IAbstractLevelModel computeReducedDomain(final Rule rule) {
		final ICodeLevelModel newCodeLevelModel =
			Factory.getInstance().createCodeLevelModel("restricted");
		try {
			// Yann 2005/10/07: Packages!
			// A model may now contrain entities and packages.
			// Yann 2005/10/12: Iterator!
			// I have now an iterator able to iterate over a
			// specified type of constituent of a list.
			final Iterator iterator =
				this.abstractLevelModel
					.getIteratorOnConstituents(IFirstClassEntity.class);
			while (iterator.hasNext()) {
				final IFirstClassEntity firstClassEntity =
					(IFirstClassEntity) iterator.next();
				//	if (packageNames != null) {
				//		if (rule
				//			.compute(
				//				this.metrics,
				//				this.listOfMetrics,
				//				(IEntity) entity)) {
				//			boolean accept = false;
				//			int i = 0;
				//			while (!accept && i < this.packageNames.length) {
				//				if (entity
				//					.getID()
				//					.startsWith(this.packageNames[i]))
				//					accept = true;
				//				i++;
				//			}
				//			if (accept) {
				//				newCodeLevelModel.addConstituent(entity);
				//			}
				//		}
				//	}
				//	else {
				if (rule.compute(
					this.metrics,
					this.abstractLevelModel,
					firstClassEntity)) {

					newCodeLevelModel.addConstituent(firstClassEntity);
				}
			}
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		return newCodeLevelModel;
	}
}