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
public class Transition {
	static String loopTransition = "loop";
	private State destination;
	private String token;

	public Transition(final String token, final State dest) {
		this.token = token;
		this.destination = dest;
	}

	public Transition(final Transition transition) {
		if (transition.token != null) {
			this.token = transition.token;
		}
		if (transition.destination != null) {
			this.destination = transition.destination;
		}
	}

	public State getDestinationState() {
		return this.destination;
	}

	public String getToken() {
		return this.token;
	}

	public String toString() {
		final StringBuffer result = new StringBuffer();

		result.append("(");
		if (this.token != null) {
			result.append(this.token);
		}
		else {
			result.append("null");
		}

		if (this.destination != null) {
			result.append("," + this.destination.getNumber());
		}
		else {
			result.append(", null");
		}
		result.append(")");
		return result.toString();
	}
}
