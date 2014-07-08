/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.motif.repository;

import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IMethod;
import padl.motif.models.StructuralMotifModel;
import util.multilingual.MultilingualManager;

public class Adapter extends StructuralMotifModel implements Cloneable {
	private static final char[] ADAPTEE = "Adaptee".toCharArray();
	private static final char[] ADAPTER = "Adapter".toCharArray();
	private static final char[] CLIENT = "Client".toCharArray();
	private static final char[] FOO = "foo".toCharArray();
	private static final char[] REQUEST = "request".toCharArray();
	private static final long serialVersionUID = -8439597980811548922L;
	private static final char[] SPECIFIC_REQUEST = "specificRequest"
		.toCharArray();
	private static final char[] TARGET = "Target".toCharArray();

	public Adapter() {
		super(Adapter.ADAPTER);

		final IClass client =
			this.getFactory().createClass(Adapter.CLIENT, Adapter.CLIENT);
		final IClass target =
			this.getFactory().createClass(Adapter.TARGET, Adapter.TARGET);
		final IClass adapter =
			this.getFactory().createClass(Adapter.ADAPTER, Adapter.ADAPTER);
		final IClass adaptee =
			this.getFactory().createClass(Adapter.ADAPTEE, Adapter.ADAPTEE);

		final IMethod specificRequest =
			this.getFactory().createMethod(
				Adapter.SPECIFIC_REQUEST,
				Adapter.SPECIFIC_REQUEST);
		adaptee.addConstituent(specificRequest);

		final IAssociation associationAdapterAdaptee =
			this.getFactory().createAssociationRelationship(
				Adapter.REQUEST,
				adaptee,
				1);
		adapter.addConstituent(associationAdapterAdaptee);
		final IDelegatingMethod request =
			this.getFactory().createDelegatingMethod(
				Adapter.REQUEST,
				associationAdapterAdaptee,
				specificRequest);
		adapter.addConstituent(request);
		adapter.addInheritedEntity(target);

		final IMethod abstractRequest =
			this.getFactory().createMethod(Adapter.REQUEST, Adapter.REQUEST);
		abstractRequest.setAbstract(true);
		target.setAbstract(true);
		target.addConstituent(abstractRequest);

		final IAssociation associationClientTarget =
			this.getFactory().createAssociationRelationship(
				Adapter.REQUEST,
				target,
				1);
		client.addConstituent(associationClientTarget);
		final IDelegatingMethod method =
			this.getFactory().createDelegatingMethod(
				Adapter.FOO,
				associationClientTarget,
				abstractRequest);
		client.addConstituent(method);

		this.addConstituent(client);
		this.addConstituent(target);
		this.addConstituent(adapter);
		this.addConstituent(adaptee);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Adapter.class);
	}

	public char[] getName() {
		return Adapter.ADAPTER;
	}
}
