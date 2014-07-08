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
package ptidej.ui.analysis.repository.comparator;

import java.util.HashMap;
import java.util.Map;
import padl.kernel.IConstituentOfModel;
import ptidej.ui.kernel.Entity;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/17
 */
public class AnalysesBuilder extends Builder {
	private static Map UniqueInstances;
	public static ptidej.ui.kernel.builder.Builder getCurrentBuilder(
		final IPrimitiveFactory aPrimitiveFactory) {

		if (AnalysesBuilder.UniqueInstances == null) {
			AnalysesBuilder.UniqueInstances = new HashMap();
		}
		if (AnalysesBuilder.UniqueInstances.get(aPrimitiveFactory) == null) {
			AnalysesBuilder.UniqueInstances.put(
				aPrimitiveFactory,
				new AnalysesBuilder(aPrimitiveFactory));
		}

		return (AnalysesBuilder) AnalysesBuilder.UniqueInstances
			.get(aPrimitiveFactory);
	}

	protected AnalysesBuilder(IPrimitiveFactory aPrimitiveFactory) {
		super(aPrimitiveFactory);
	}
	protected Entity createEntity(final IConstituentOfModel anEntity) {
		Entity entity;

		if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.AddedClass) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.AddedClass(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.RemovedClass) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.RemovedClass(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.ModifiedClass) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.ModifiedClass(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.AddedInterface) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.AddedInterface(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.RemovedInterface) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.RemovedInterface(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else if (anEntity instanceof ptidej.ui.analysis.repository.comparator.model.ModifiedInterface) {
			entity =
				new ptidej.ui.analysis.repository.comparator.ui.ModifiedInterface(
					this.getPrimitiveFactory(),
					this,
					anEntity);
		}
		else {
			entity = super.createEntity(anEntity);
		}

		return entity;
	}
}
