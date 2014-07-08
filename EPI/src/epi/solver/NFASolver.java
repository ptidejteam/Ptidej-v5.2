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

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import util.io.ProxyConsole;

/**
 * @author OlivierK
 */
public class NFASolver extends EPISolver {

	public NFASolver(
		final String programString,
		final String patternString,
		final String motifName,
		final Approximation approximation,
		final Hashtable domains) {

		super(programString, patternString, motifName, approximation, domains);
	}

	public Solution[] computeSolutions() {

		final NFA nfa = new NFA(this.patternString);
		ProxyConsole.getInstance().debugOutput().println(nfa.toString());
		final Vector test =
			this.match(this.programString, nfa.getInitalState(), null);
		//System.out.println(test);
		return this.vectorToSolutions(test);
	}

	public int getSolverType() {
		return EPISolver.AUTOMATON_SOLVER;
	}

	public Vector match(
		final String input,
		final State currentState,
		final Solution currentOccurrence) {

		final Vector occurrencesVector = new Vector();
		if (currentState.isFinal()) {
			occurrencesVector.add(currentOccurrence);
			return occurrencesVector;
		}
		final StringTokenizer st = new StringTokenizer(input);

		// A dummy relationship is considered like a epsilon transition
		if (currentState.transitions[1].getToken().indexOf("dummyRelationship") > 0
				|| currentState.transitions[1].getToken().indexOf("dm") > 0) {
			return this.match(
				input,
				currentState.transitions[1].getDestinationState(),
				currentOccurrence);
		}

		if (st.countTokens() >= 3) {
			final String entity = st.nextToken();
			final String relation = st.nextToken();
			final String targetEntity = st.nextToken();

			//for all edges emanating from this node
			for (int i = 0; i < currentState.transitions.length; i++) {
				final String token = currentState.transitions[i].getToken();
				if (token.equals("loop")) {
					occurrencesVector
						.addAll(this.match(
							input.substring(entity.length() + relation.length()
									+ 2),
							currentState.transitions[i].getDestinationState(),
							currentOccurrence));
				}
				else {
					final StringTokenizer stToken = new StringTokenizer(token);
					final String entityToken = stToken.nextToken();
					final String relationToken = stToken.nextToken();
					final String targetToken = stToken.nextToken();
					if (this.approximationType.getApproximationList(
						relationToken).contains("null")) {
						occurrencesVector.addAll(this.match(
							input.substring(entity.length() + relation.length()
									+ 2),
							currentState.transitions[i].getDestinationState(),
							currentOccurrence));
					}
					else if (this.approximationType.getApproximationList(
						relationToken).contains(relation)) {
						Solution newSolution;
						String newProgramString = input;
						if (currentOccurrence == null) {
							newSolution = new Solution();

							//programString is circular so we add the beginning of the string to the input
							String beforeTriplet =
								this.programString.substring(
									0,
									this.programString.indexOf(input));
							if (!beforeTriplet.equals("")) {
								final String firstToken =
									input.substring(
										0,
										beforeTriplet.indexOf(" "));
								beforeTriplet =
									beforeTriplet
										.substring(firstToken.length());
								newProgramString =
									newProgramString.concat(beforeTriplet
											+ firstToken);
							}
						}
						else {
							newSolution = (Solution) currentOccurrence.clone();
						}
						if (newSolution.containsComponent(entityToken)
								&& newSolution.containsComponent(targetToken)) {
							if (newSolution.getComponent(entityToken).equals(
								entity)
									&& newSolution
										.getComponent(targetToken)
										.equals(targetEntity)) {
								occurrencesVector.addAll(this.match(
									newProgramString.substring(entity.length()
											+ relation.length() + 2),
									currentState.transitions[i]
										.getDestinationState(),
									newSolution));
							}
						}
						else if (newSolution.containsComponent(entityToken)) {
							if (newSolution.getComponent(entityToken).equals(
								entity)
									&& this
										.isRoleCandidate(entityToken, entity)) {
								newSolution.addComponent(
									targetToken,
									targetEntity);
								occurrencesVector.addAll(this.match(
									newProgramString.substring(entity.length()
											+ relation.length() + 2),
									currentState.transitions[i]
										.getDestinationState(),
									newSolution));
							}
						}
						else if (newSolution.containsComponent(targetToken)) {
							if (newSolution.getComponent(targetToken).equals(
								targetEntity)
									&& this.isRoleCandidate(
										targetToken,
										targetEntity)) {
								newSolution.addComponent(entityToken, entity);
								occurrencesVector.addAll(this.match(
									newProgramString.substring(entity.length()
											+ relation.length() + 2),
									currentState.transitions[i]
										.getDestinationState(),
									newSolution));
							}
						}
						else if (this.isRoleCandidate(entityToken, entity)
								&& this.isRoleCandidate(
									targetToken,
									targetEntity)) {
							newSolution.addComponent(entityToken, entity);
							newSolution.addComponent(targetToken, targetEntity);
							occurrencesVector.addAll(this.match(
								newProgramString.substring(entity.length()
										+ relation.length() + 2),
								currentState.transitions[i]
									.getDestinationState(),
								newSolution));
						}
					}
				}
			}
		}

		//System.out.println("NOOOO");
		return occurrencesVector;
	}
}
