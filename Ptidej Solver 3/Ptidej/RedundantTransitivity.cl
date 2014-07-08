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

[customProblemForRedundantTransitivityPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Redundant Transitivity Pattern Problem", length(listOfEntities), 90),
            entity1 := makePtidejVar(pb, "Entity1", 1, length(listOfEntities)),
            entity2 := makePtidejVar(pb, "Entity2", 1, length(listOfEntities)),
            entity3 := makePtidejVar(pb, "Entity3", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeUseConstraint(
                    "Entity1 -k--> Entity2",
                    "throw new RuntimeException(\"Entity1 -k--> Entity2\");",
                    entity1,
                    entity2),
                 100),
            post(pb,
                 makeUseConstraint(
                    "Entity2 -k--> Entity3",
                    "throw new RuntimeException(\"Entity2 -k--> Entity3\");",
                    entity2,
                    entity3),
                 100),
            post(pb,
                 makeIgnoranceConstraint(
                    "Entity1 -/--> Entity3",
                    "throw new RuntimeException(\"Entity1 -/--> Entity3\");",
                    entity1,
                    entity3),
                 50),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity1 <> Entity2",
                    "throw new RuntimeException(\"Entity1 <> Entity2\");",
                    entity1,
                    entity2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity2 <> Entity3",
                    "throw new RuntimeException(\"Entity2 <> Entity3\");",
                    entity2,
                    entity3),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity1 <> Entity3",
                    "throw new RuntimeException(\"Entity1 <> Entity3\");",
                    entity1,
                    entity3),
                 100),
            pb
        )
]
[ac4ProblemForRedundantTransitivityPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Redundant Transitivity Pattern Problem", length(listOfEntities), 90),
            entity1 := makePtidejVar(pb, "Entity1", 1, length(listOfEntities)),
            entity2 := makePtidejVar(pb, "Entity2", 1, length(listOfEntities)),
            entity3 := makePtidejVar(pb, "Entity3", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeAssociationAC4Constraint(
                    "Entity1 ----> Entity2",
                    "throw new RuntimeException(\"Entity1 ----> Entity2\");",
                    entity1,
                    entity2),
                 100),
            post(pb,
                 makeAssociationAC4Constraint(
                    "Entity2 ----> Entity3",
                    "throw new RuntimeException(\"Entity2 ----> Entity3\");",
                    entity2,
                    entity3),
                 100),
            post(pb,
                 makeAssociationAC4Constraint(
                    "Entity1 -/--> Entity3",
                    "throw new RuntimeException(\"Entity1 -/--> Entity3\");",
                    entity1,
                    entity3),
                 50),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity1 <> Entity2",
                    "throw new RuntimeException(\"Entity1 <> Entity2\");",
                    entity1,
                    entity2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity2 <> Entity3",
                    "throw new RuntimeException(\"Entity2 <> Entity3\");",
                    entity2,
                    entity3),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Entity1 <> Entity3",
                    "throw new RuntimeException(\"Entity1 <> Entity3\");",
                    entity1,
                    entity3),
                 100),
            pb
        )
]