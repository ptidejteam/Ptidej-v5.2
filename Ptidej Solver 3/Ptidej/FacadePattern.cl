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

[customProblemForFacadePattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Facade Pattern Problem", length(listOfEntities), 90),
            facade := makePtidejVar(pb, "Facade", 1, length(listOfEntities)),
            subsystemEntity := makePtidejVar(pb, "SubsystemEntity", 1, length(listOfEntities), false),
            client := makePtidejVar(pb, "Client", 1, length(listOfEntities), false) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeAssociationConstraint(
                    "Facade ----> SubsystemEntity",
                    "throw new RuntimeException(\"Composite ----> Component\");",
                    facade,
                    subsystemEntity),
                 90),
            post(pb,
                 makeAssociationConstraint(
                    "Client ----> Facade",
                    "throw new RuntimeException(\"Composite ----> Component\");",
                    client,
                    facade),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Facade <> SubsystemEntity",
                    "throw new RuntimeException(\"Facade <> SubsystemEntity\");",
                    facade,
                    subsystemEntity),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Facade <> Client",
                    "throw new RuntimeException(\"Facade <> Client\");",
                    facade,
                    client),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "SubsystemEntity <> Facade",
                    "throw new RuntimeException(\"SubsystemEntity <> Facade\");",
                    subsystemEntity,
                    facade),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "SubsystemEntity <> Client",
                    "throw new RuntimeException(\"SubsystemEntity <> Client\");",
                    subsystemEntity,
                    client),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client <> Facade",
                    "throw new RuntimeException(\"Client <> Facade\");",
                    client,
                    facade),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client <> SubsystemEntity",
                    "throw new RuntimeException(\"Client <> SubsystemEntity\");",
                    client,
                    subsystemEntity),
                 100),
            pb
        )
]
[ac4ProblemForFacadePattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Facade Pattern Problem", length(listOfEntities), 90),
            facade := makePtidejVar(pb, "Facade", 1, length(listOfEntities)),
            subsystemEntity := makePtidejVar(pb, "SubsystemEntity", 1, length(listOfEntities), false),
            client := makePtidejVar(pb, "Client", 1, length(listOfEntities), false) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeAssociationAC4Constraint(
                    "Facade ----> SubsystemEntity",
                    "throw new RuntimeException(\"Composite ----> Component\");",
                    facade,
                    subsystemEntity),
                 90),
            post(pb,
                 makeAssociationAC4Constraint(
                    "Client ----> Facade",
                    "throw new RuntimeException(\"Composite ----> Component\");",
                    client,
                    facade),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Facade <> SubsystemEntity",
                    "throw new RuntimeException(\"Facade <> SubsystemEntity\");",
                    facade,
                    subsystemEntity),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Facade <> Client",
                    "throw new RuntimeException(\"Facade <> Client\");",
                    facade,
                    client),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "SubsystemEntity <> Facade",
                    "throw new RuntimeException(\"SubsystemEntity <> Facade\");",
                    subsystemEntity,
                    facade),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "SubsystemEntity <> Client",
                    "throw new RuntimeException(\"SubsystemEntity <> Client\");",
                    subsystemEntity,
                    client),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client <> Facade",
                    "throw new RuntimeException(\"Client <> Facade\");",
                    client,
                    facade),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Client <> SubsystemEntity",
                    "throw new RuntimeException(\"Client <> SubsystemEntity\");",
                    client,
                    subsystemEntity),
                 100),
            pb
        )
]