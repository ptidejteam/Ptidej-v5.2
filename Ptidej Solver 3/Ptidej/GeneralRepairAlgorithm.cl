// (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
// Ecole des Mines de Nantes and Object Technology International, Inc.
// 
// Use and copying of this software and preparation of derivative works
// based upon this software are permitted. Any copy of this software or
// of any derivative work must include the above copyright notice of
// Yann-Gaël Guéhéneuc, this paragraph and the one after it.
// 
// This software is made available AS IS, and THE AUTHOR DISCLAIMS
// ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
// LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
// EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
// NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGES.
// 
// All Rights Reserved.

// **************************************************************************************
// * The new repair algorithm, a generalization of the algorithm provided in PaLM v1.07 *
// **************************************************************************************

[repair(pb:PtidejProblem): void
	->	repair(pb.palmSolver)
]

[repair(algo:PtidejSolver) : void
    ->  // [0] repair(PtidejSolver),
        let pb       := algo.problem,
            pe       := pb.propagationEngine,
            state    := algo.state, 
            learner  := algo.learning,
            repairer := algo.repairing
        in (
			printStatus(),
            // [0] algo.finished = ~S // algo.finished,
            // [0] pe.contradictory = ~S // pe.contradictory,
            if (pe.contradictory) (
                let
                    e  := Explanation(),    
                    fv := pe.contradictionCause
                in (
                    self_explain(fv, DOM, e),
                    assert(valid?(e)),
                    pe.contradictory := false,
                    learnFromContradiction(learner, e),
                    // [0] e = ~S // e,
                    if (empty?(e)) (
                        raiseSystemContradiction(pe)
                    )
                    else (
                        when
                        	re := selectDecisionToUndo(repairer, e)
                        in (
                            // [0] re = ~S // re, 
                            // First, I deal with the removal of a constraint.
                            let hasAnyConstraintBeenRemoved:boolean := false in (
                                for ct:AbstractConstraint in re[1] (
	                            	printStatus(),
                                    // [0] Willing to remove: ~S // ct,
                                    // [0] weight(ct) < pb.maxRelaxLvl = ~S < ~S = ~S // weight(ct), pb.maxRelaxLvl, weight(ct) < pb.maxRelaxLvl,
                                    if (weight(ct) < pb.maxRelaxLvl) (
                                    	// the constraint is relaxable
                                        incRuntimeStatistic(algo, RLX, 1),
                                        if (weight(ct) > 0) (
                                            // Not an enumeration constraint.
                                            // [0] Removing constraint ~S (w:~S) // ct, weight(ct),
                                            nil
                                        ) 
                                        else (
                                            // I need to maintain the current search state .
                                            removeDecision(state, ct)
                                        ),
                                        // [0] Removing: ~S // ct,
                                        remove(pb, ct),
                                        remove(repairer, ct),
                                        hasAnyConstraintBeenRemoved := true
                                    )
                                ),
                                // Yann 01/11/06: Naren says!
                                // If no constraint has been removed, this means
                                // that there is no solution to the problem:
                                // Whatever we do, we will not get a solution.
                                // Then, I need to raise a system contradiction
                                // to stop any further computations.
                                // [0] hasAnyConstraintBeenRemoved = ~S // hasAnyConstraintBeenRemoved,
                                if (not(hasAnyConstraintBeenRemoved)) (
                                    raiseSystemContradiction(pe)
                                )
                            ),
						    try (
						    	// [0] Propagate,
						    	// Yann 2003/03/12: Over-propagated!
						    	// We need to propagate now in case the
						    	// problem is still over-constrained
						    	// despite the removed contraints.
						    	printStatus(),
						        propagate(pb)
						    )
						    catch PalmContradiction (
						        repair(algo)
						    ),
                            for ct:AbstractConstraint in re[1] (
                                // [0] Willing to negate: ~S // ct,
                                let temporaryExplanation:Explanation := e in (
                                    if (weight(ct) = 0) (
                                        // An enumeration constraint.
                                        // [0] Trying to negate the constraint: ~S // ct,
                                        temporaryExplanation := copy(e),
                                        temporaryExplanation :delete ct,
    
                                        when negct := negate(ct) in (
                                            if valid?(temporaryExplanation) (
                                                clean(temporaryExplanation),
                                                // [0] Posting: ~S // negct, 
                                                post(pb, negct, temporaryExplanation)
                                            )
                                        )
                                    )
                                )
                            ),
					    	// Yann 2003/03/12: Over-propagated!
					    	// We can merge the following two propagation
					    	// because we only post constraints there
					    	// (decision constraints and previously-removed
					    	// constraints).
							// 	try (
							//		printStatus(),
							//		propagate(pb)
							// 	)
							// 	catch PalmContradiction (
							// 		repair(algo)
							// 	),
                            // Second, I deal with the addition of constraints.
                            // [0] Posting back relaxed constraints: ~S // re[2],
                            for ct in re[2] (
                                // [0] Putting back: ~S of weight: ~S // ct, weight(ct),
                                post(pb, ct, weight(ct))
                            ),
                            try (
                            	printStatus(),
                                propagate(pb)
                            )
                            catch PalmContradiction (
                                repair(algo)
                            )
                        )
                        else (
                            raiseSystemContradiction(pe)
                        )
                    )
                )
            )
        )
]
