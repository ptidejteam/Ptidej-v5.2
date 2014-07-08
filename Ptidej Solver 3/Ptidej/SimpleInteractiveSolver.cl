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

// ************************************************
// * Standard interactive solver written by Naren *
// ************************************************

PtidejSimpleInteractiveRepair <: PtidejRepair()

[selectDecisionToUndo(rep:PtidejSimpleInteractiveRepair, e:Explanation) : tuple(list<AbstractConstraint>, list<AbstractConstraint>)
    ->  // [0] selectDecisionToUndo(SimpleInteractiveRepair, Explanation),
        let
            ct:AbstractConstraint := (min(standard_better_constraint @ AbstractConstraint, set!(e)) as AbstractConstraint),
            re:tuple(list<AbstractConstraint>, list<AbstractConstraint>) := tuple(list<AbstractConstraint>(), list<AbstractConstraint>())
        in (
            if (weight(ct) = 0) (
                add(re[1], ct),
                re
            )
            else  (
                let cts := nil in (
                    for c in e cts :add c,
                    cts := sort(isBetterConstraint @ AbstractConstraint, cts) as list<AbstractConstraint>,
                    printf("\nThere is no more solution because of the constraints:\n"),
                    for i in (1 .. length(cts)) (
                        printf("   ~S. ~S\n      (weight ~S)\n", i, cts[i], weight(cts[i]))
                    ),
                    printf("Which one do you want to relax? (0 -> none) "),
                    let
                    	selecC := read(stdin) as integer
                    in (
                        if (selecC = 0) (
                            PalmContradiction()
                        ) 
                        else (
                            add(re[1], cts[selecC]),
                            re
                        )
                    )
                )
            )
        )
]

[initSimpleInteractiveSolver(pb:PtidejProblem) : void
    ->  let ps := PtidejSolver(statistics = make_list(length(RuntimeStatistic), integer, 0))
        in (
            pb.palmSolver := ps,

            attachPalmState(pb, PalmState(path = Explanation())),
            attachPalmExtend(pb, PalmExtend()),
            attachPalmLearn(pb, PtidejLearn()),
            attachPalmRepair(pb, PtidejSimpleInteractiveRepair()),
            attachPalmBranchings(pb, list(PtidejAssignVar(), PtidejInteractiveBranching())),

            ps.problem := pb
        )
]

[simpleInteractiveSolve(pb:PtidejProblem) : void
    ->  // [0] simpleInteractiveSolve(PtidejProblem),
		printf("\n~A Computing solutions (simple interactive)\n", char!(179)),
		printFooter(),

		printHeader(),
    	initSimpleInteractiveSolver(pb),
        solve(pb, false),
		printFooter()
]