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
package epi.solver;

import java.util.StringTokenizer;
import util.io.ProxyConsole;

/**
 * @author OlivierK
 */
public class NFA implements Cloneable {

	public static void main(final String[] args) {
		final NFA nfa =
			new NFA(
				"Component inheritance Leaf dummyRelationship Component inheritance Composite containerComposition Component");
		ProxyConsole.getInstance().debugOutput().println(nfa.toString());
		//Vector test = nfa.match("A inheritance B association A inheritance B containerComposition A inheritance D", nfa.getInitalState(), null, new Approximation());
		//System.out.println(test);
	}

	public State[] states;

	private NFA(final int size) {
		this.states = new State[size];
	}

	private NFA(final NFA nfa) {
		this(nfa.states.length);

		for (int i = 0; i < nfa.states.length; i++) {
			this.states[i] = new State(nfa.states[i]);
		}
	}

	public NFA(final String string) {
		final StringTokenizer st = new StringTokenizer(string);
		this.states = new State[st.countTokens() / 2 + 1];

		String entity = st.nextToken();
		String relation = st.nextToken();
		String targetEntity = st.nextToken();

		this.states[0] = new State(0);
		this.states[1] = new State(1);
		this.states[0].transitions[0] =
			new Transition(Transition.loopTransition, this.states[0]);
		this.states[0].transitions[1] =
			new Transition(
				entity + " " + relation + " " + targetEntity,
				this.states[1]);

		int i;
		for (i = 2; st.hasMoreTokens(); i++) {
			entity = targetEntity;
			relation = st.nextToken();
			targetEntity = st.nextToken();

			//		    if(relation.equals("dummyRelationship") || relation.equals("dm")){
			//		    	i--;
			//		       	continue;
			//		    }

			this.states[i] = new State(i);
			this.states[i - 1].transitions[0] =
				new Transition(Transition.loopTransition, this.states[i - 1]);
			this.states[i - 1].transitions[1] =
				new Transition(
					entity + " " + relation + " " + targetEntity,
					this.states[i]);
		}
		this.states[i - 1].setFinal(true);
	}

	public Object clone() {
		return new NFA(this);
	}

	public State getFinalState() {
		return this.states[this.states.length - 1];
	}

	public State getInitalState() {
		return this.states[0];
	}

	public String toString() {
		final StringBuffer result = new StringBuffer();
		for (int i = 0; i < this.states.length; i++) {
			result.append(this.states[i].toString()
					+ System.getProperty("line.separator"));
		}
		return result.toString();
	}
}