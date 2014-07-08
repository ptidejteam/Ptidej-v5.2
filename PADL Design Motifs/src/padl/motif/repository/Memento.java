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

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.Constants;
import padl.kernel.IAggregation;
import padl.kernel.IClass;
import padl.kernel.ICreation;
import padl.kernel.IMethod;
import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class Memento extends BehaviouralMotifModel implements Cloneable {
	private static final char[] CARETAKER = "Caretaker".toCharArray();
	private static final char[] CREATE = "create".toCharArray();
	private static final char[] GET_STATE = "GetState".toCharArray();
	private static final char[] MEMENTO = "Memento".toCharArray();
	private static final char[] ORIGINATOR = "Originator".toCharArray();
	private static final long serialVersionUID = 3879779843304557078L;
	private static final char[] SET_STATE = "SetState".toCharArray();
	private static final char[] STRING = "memento".toCharArray();

	public Memento() {
		super(Memento.MEMENTO);

		final IClass originator =
			this.getFactory().createClass(
				Memento.ORIGINATOR,
				Memento.ORIGINATOR);
		final IClass memento =
			this.getFactory().createClass(Memento.MEMENTO, Memento.MEMENTO);
		final ICreation creationLink =
			this.getFactory().createCreationRelationship(
				Memento.CREATE,
				memento,
				Constants.CARDINALITY_ONE);
		originator.addConstituent(creationLink);

		final IMethod originatorCreateMethod =
			this.getFactory().createMethod(
				ArrayUtils.addAll(Memento.CREATE, Memento.MEMENTO),
				ArrayUtils.addAll(Memento.CREATE, Memento.MEMENTO));

		originatorCreateMethod.setCodeLines("return new "
				+ String.valueOf(Memento.MEMENTO) + "();");

		originator.addConstituent(originatorCreateMethod);

		final IMethod getState =
			this
				.getFactory()
				.createMethod(Memento.GET_STATE, Memento.GET_STATE);
		memento.addConstituent(getState);
		final IMethod setState =
			this
				.getFactory()
				.createMethod(Memento.SET_STATE, Memento.SET_STATE);
		memento.addConstituent(setState);

		final IClass caretaker =
			this.getFactory().createClass(Memento.CARETAKER, Memento.CARETAKER);
		final IAggregation aComposition =
			this.getFactory().createAggregationRelationship(
				Memento.STRING,
				memento,
				Constants.CARDINALITY_MANY);
		caretaker.addConstituent(aComposition);

		this.addConstituent(originator);
		this.addConstituent(memento);
		this.addConstituent(caretaker);

	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Memento.class);
	}

	public char[] getName() {
		return Memento.MEMENTO;
	}
}
