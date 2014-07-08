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
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class ChainOfResponsibility extends BehaviouralMotifModel implements
		Cloneable {

	private static final char[] CHAIN_OF_RESPONSIBILITY =
		"ChainOfResponsibility".toCharArray();
	private static final char[] CLIENT = "Client".toCharArray();
	private static final char[] CONCRETE_HANDLER = "ConcreteHandler"
		.toCharArray();
	private static final char[] FOO = "foo".toCharArray();
	private static final char[] HANDLE_REQUEST = "HandleRequest".toCharArray();
	private static final char[] HANDLER = "Handler".toCharArray();
	private static final long serialVersionUID = -6201681806591176334L;
	private static final char[] SUCCESSOR = "successor".toCharArray();

	public ChainOfResponsibility() {
		super(ChainOfResponsibility.CHAIN_OF_RESPONSIBILITY);

		final IClass Client =
			this.getFactory().createClass(
				ChainOfResponsibility.CLIENT,
				ChainOfResponsibility.CLIENT);
		final IInterface Handler =
			this.getFactory().createInterface(
				ChainOfResponsibility.HANDLER,
				ChainOfResponsibility.HANDLER);
		final IMethod HandleRequest =
			this.getFactory().createMethod(
				ChainOfResponsibility.HANDLE_REQUEST,
				ChainOfResponsibility.HANDLE_REQUEST);
		Handler.addConstituent(HandleRequest);

		final IAssociation associationHandlerSuccessor =
			this.getFactory().createAssociationRelationship(
				ChainOfResponsibility.SUCCESSOR,
				Handler,
				1);
		Handler.addConstituent(associationHandlerSuccessor);

		final IClass concreteHandler =
			this.getFactory().createClass(
				ChainOfResponsibility.CONCRETE_HANDLER,
				ChainOfResponsibility.CONCRETE_HANDLER);
		concreteHandler.addImplementedInterface(Handler);
		concreteHandler.assumeAllInterfaces();

		final IAssociation associationClientReceiver =
			this.getFactory().createAssociationRelationship(
				ChainOfResponsibility.HANDLE_REQUEST,
				Handler,
				1);
		Client.addConstituent(associationClientReceiver);
		final IDelegatingMethod method =
			this.getFactory().createDelegatingMethod(
				ChainOfResponsibility.FOO,
				associationClientReceiver,
				HandleRequest);
		Client.addConstituent(method);

		this.addConstituent(Client);
		this.addConstituent(Handler);
		this.addConstituent(concreteHandler);

	}

	public String getIntent() {
		return MultilingualManager.getString(
			"INTENT",
			ChainOfResponsibility.class);
	}

	public char[] getName() {
		return ChainOfResponsibility.CHAIN_OF_RESPONSIBILITY;
	}
}
