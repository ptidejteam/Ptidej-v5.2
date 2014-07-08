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

/**
 * @author OlivierK
 */
public class State {
	private int number = -1;
	private boolean isFinal = false;
	public Transition[] transitions = new Transition[2];

	public State(final int number) {
		this.number = number;
	}

	public State(final State state) {
		if (state.number != -1) {
			this.number = state.number;
		}
		if (state.transitions[0] != null) {
			this.transitions[0] = new Transition(state.transitions[0]);
		}
		if (state.transitions[1] != null) {
			this.transitions[1] = new Transition(state.transitions[1]);
		}
	}

	public int getNumber() {
		return this.number;
	}

	public boolean isFinal() {
		return this.isFinal;
	}
	public void setFinal(final boolean isFinal) {
		this.isFinal = isFinal;
	}
	public void setNumber(final int number) {
		this.number = number;
	}
	public String toString() {
		final StringBuffer result = new StringBuffer();
		if (this.number != -1) {
			result.append(this.number + ":");
		}
		if (this.transitions[0] != null) {
			result.append(this.transitions[0].toString());
		}
		if (this.transitions[1] != null) {
			result.append(this.transitions[1].toString());
		}
		if (this.isFinal == true) {
			result.append("final state");
		}
		return result.toString();
	}
}
