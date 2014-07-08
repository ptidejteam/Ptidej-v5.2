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

import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.IUseRelationship;
import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class Mediator extends BehaviouralMotifModel implements Cloneable {
	public static final char[] CLIENT1 = "Client1".toCharArray();
	private static final char[] CLIENT1_MEDIATOR = "client1Mediator"
		.toCharArray();
	public static final char[] CLIENT2 = "Client2".toCharArray();
	private static final char[] CLIENT2_MEDIATOR = "client2Mediator"
		.toCharArray();
	public static final char[] MEDIATOR = "Mediator".toCharArray();
	private static final char[] MEDIATOR_CLIENT1 = "mediatorClient1"
		.toCharArray();
	private static final char[] MEDIATOR_CLIENT2 = "mediatorClient2"
		.toCharArray();
	private static final long serialVersionUID = 5608009702431565343L;

	public Mediator() {
		super(Mediator.MEDIATOR);

		final IClass client1 =
			this.getFactory().createClass(Mediator.CLIENT1, Mediator.CLIENT1);
		final IClass mediator =
			this.getFactory().createClass(Mediator.MEDIATOR, Mediator.MEDIATOR);
		final IClass client2 =
			this.getFactory().createClass(Mediator.CLIENT2, Mediator.CLIENT2);

		final IUseRelationship client1Mediator =
			this.getFactory().createUseRelationship(
				Mediator.CLIENT1_MEDIATOR,
				mediator,
				Constants.CARDINALITY_MANY);
		client1.addConstituent(client1Mediator);
		final IUseRelationship mediatorClient1 =
			this.getFactory().createUseRelationship(
				Mediator.MEDIATOR_CLIENT1,
				client1,
				Constants.CARDINALITY_MANY);
		mediator.addConstituent(mediatorClient1);

		final IUseRelationship client2Mediator =
			this.getFactory().createUseRelationship(
				Mediator.CLIENT2_MEDIATOR,
				mediator,
				Constants.CARDINALITY_MANY);
		client2.addConstituent(client2Mediator);
		final IUseRelationship mediatorClient2 =
			this.getFactory().createUseRelationship(
				Mediator.MEDIATOR_CLIENT2,
				client2,
				Constants.CARDINALITY_MANY);
		mediator.addConstituent(mediatorClient2);

		this.addConstituent(client1);
		this.addConstituent(mediator);
		this.addConstituent(client2);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Mediator.class);
	}

	public char[] getName() {
		return Mediator.MEDIATOR;
	}
}
