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

// +----------------------------------------------+
// | Hypothesis:                                  |
// |     There is (at least) one Mediator pattern |
// +----------------------------------------------+

[customProblemForMediatorPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb       := makePtidejProblem("Mediator Pattern Problem", length(listOfEntities), 90),
            mediator := makePtidejVar(pb, "Mediator", 1, length(listOfEntities)),
            client1  := makePtidejVar(pb, "Client1", 1, length(listOfEntities)),
            client2  := makePtidejVar(pb, "Client2", 1, length(listOfEntities))
        in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeUseConstraint(
                    "Client1 ----> Mediator",
                    "throw new RuntimeException(\"Client1 ----> Mediator\");",
                    client1,
                    mediator),
                 100),
            post(pb,
                 makeUseConstraint(
                    "Mediator ----> Client1",
                    "throw new RuntimeException(\"Mediator ----> Client1\");",
                    mediator,
                    client1),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Mediator <> Client1",
                    "throw new RuntimeException(\"Mediator <> Client1\");",
                    mediator,
                    client1),
                 100),
            post(pb,
                 makeUseConstraint(
                    "Client2 ----> Mediator",
                    "throw new RuntimeException(\"Client2 ----> Mediator\");",
                    client2,
                    mediator),
                 100),
            post(pb,
                 makeUseConstraint(
                    "Mediator ----> Client2",
                    "throw new RuntimeException(\"Mediator ----> Client2\");",
                    mediator,
                    client2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Mediator <> Client2",
                    "throw new RuntimeException(\"Mediator <> Client2\");",
                    mediator,
                    client2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client1 <> Client2",
                    "throw new RuntimeException(\"Client1 <> Client2\");",
                    client1,
                    client2),
                 100),
            post(pb,
                 makeIgnoranceConstraint(
                    "Client1 -/--> Client2",
                    "throw new RuntimeException(\"Client1 -/--> Client2\");",
                    client1,
                    client2),
                 50),
            post(pb,
                 makeIgnoranceConstraint(
                    "Client2 -/--> Client1",
                    "throw new RuntimeException(\"Client1 -/--> Client2\");",
                    client2,
                    client1),
                 50),
            pb
        )
]
[ac4ProblemForMediatorPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb       := makePtidejProblem("Mediator Pattern Problem", length(listOfEntities), 90),
            mediator := makePtidejVar(pb, "Mediator", 1, length(listOfEntities)),
            client1  := makePtidejVar(pb, "Client1", 1, length(listOfEntities)),
            client2  := makePtidejVar(pb, "Client2", 1, length(listOfEntities))
        in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeUseAC4Constraint(
                    "Client1 ----> Mediator",
                    "throw new RuntimeException(\"Client1 ----> Mediator\");",
                    client1,
                    mediator),
                 100),
            post(pb,
                 makeUseAC4Constraint(
                    "Mediator ----> Client1",
                    "throw new RuntimeException(\"Mediator ----> Client1\");",
                    mediator,
                    client1),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Mediator <> Client1",
                    "throw new RuntimeException(\"Mediator <> Client1\");",
                    mediator,
                    client1),
                 100),
            post(pb,
                 makeUseAC4Constraint(
                    "Client2 ----> Mediator",
                    "throw new RuntimeException(\"Client2 ----> Mediator\");",
                    client2,
                    mediator),
                 100),
            post(pb,
                 makeUseAC4Constraint(
                    "Mediator ----> Client2",
                    "throw new RuntimeException(\"Mediator ----> Client2\");",
                    mediator,
                    client2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Mediator <> Client2",
                    "throw new RuntimeException(\"Mediator <> Client2\");",
                    mediator,
                    client2),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client1 <> Client2",
                    "throw new RuntimeException(\"Client1 <> Client2\");",
                    client1,
                    client2),
                 100),
            post(pb,
                 makeIgnoranceAC4Constraint(
                    "Client1 -/--> Client2",
                    "throw new RuntimeException(\"Client1 -/--> Client2\");",
                    client1,
                    client2),
                 50),
            post(pb,
                 makeIgnoranceAC4Constraint(
                    "Client2 -/--> Client1",
                    "throw new RuntimeException(\"Client1 -/--> Client2\");",
                    client2,
                    client1),
                 50),
            pb
        )
]