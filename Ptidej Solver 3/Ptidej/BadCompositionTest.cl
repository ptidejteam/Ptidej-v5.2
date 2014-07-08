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

[customProblemForBadCompositionTestPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("BadCompositionTest Pattern Problem", length(listOfEntities), 90),
            aggregate := makePtidejVar(pb, "Aggregate", 1, length(listOfEntities), true),
            aggregated := makePtidejVar(pb, "Aggregated", 1, length(listOfEntities), true) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionConstraint(
                    "Aggregate <#>-> Aggregated",
                    "throw new RuntimeException(\"Composite <#>-> Component\");",
                    aggregate,
                    aggregated),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> Aggregated",
                    "throw new RuntimeException(\"Aggregate <> Aggregated\");",
                    aggregate,
                    aggregated),
                 100),
            pb
        )
]

[ac4ProblemForBadCompositionTestPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("BadCompositionTest Pattern Problem", length(listOfEntities), 90),
            aggregate := makePtidejVar(pb, "Aggregate", 1, length(listOfEntities), true),
            aggregated := makePtidejVar(pb, "Aggregated", 1, length(listOfEntities), true) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionAC4Constraint(
                    "Aggregate <#>-> Aggregated",
                    "throw new RuntimeException(\"Composite <#>-> Component\");",
                    aggregate,
                    aggregated),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Aggregate <> Aggregated",
                    "throw new RuntimeException(\"Aggregate <> Aggregated\");",
                    aggregate,
                    aggregated),
                 100),
            pb
        )
]