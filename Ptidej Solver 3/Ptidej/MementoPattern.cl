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

[customProblemForMementoPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Memento Pattern Problem", length(listOfEntities), 90),
            originator   := makePtidejVar(pb, "Originator",   1, length(listOfEntities)),
            memento      := makePtidejVar(pb, "Memento",      1, length(listOfEntities)),
            caretaker    := makePtidejVar(pb, "Caretaker",    1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeUseConstraint(
                    "Originator ----> Memento",
                    "System.out.println(\"Not implemented yet.\");",
                    originator,
                    memento),
                 75),
            post(pb,
                 makeCompositionConstraint(
                    "Caretaker <>--> Memento",
                    "throw new RuntimeException(\"Caretaker <>--> Memento\");",
                    caretaker,
                    memento),
                 50),
            post(pb,
                 makeNotEqualConstraint(
                    "Originator <> Memento",
                    "throw new RuntimeException(\"Originator <> Memento\");",
                    originator,
                    memento),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Originator <> Caretaker",
                    "throw new RuntimeException(\"Originator <> Caretaker\");",
                    originator,
                    caretaker),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Memento <> Caretaker",
                    "throw new RuntimeException(\"Memento <> Caretaker\");",
                    memento,
                    caretaker),
                 100),
            pb
        )
]
[ac4ProblemForMementoPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Memento Pattern Problem", length(listOfEntities), 90),
            originator   := makePtidejVar(pb, "Originator",   1, length(listOfEntities)),
            memento      := makePtidejVar(pb, "Memento",      1, length(listOfEntities)),
            caretaker    := makePtidejVar(pb, "Caretaker",    1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeUseAC4Constraint(
                    "Originator ----> Memento",
                    "System.out.println(\"Not implemented yet.\");",
                    originator,
                    memento),
                 75),
            post(pb,
                 makeCompositionAC4Constraint(
                    "Caretaker <>--> Memento",
                    "throw new RuntimeException(\"Caretaker <>--> Memento\");",
                    caretaker,
                    memento),
                 50),
            post(pb,
                 makeNotEqualConstraint(
                    "Originator <> Memento",
                    "throw new RuntimeException(\"Originator <> Memento\");",
                    originator,
                    memento),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Originator <> Caretaker",
                    "throw new RuntimeException(\"Originator <> Caretaker\");",
                    originator,
                    caretaker),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Memento <> Caretaker",
                    "throw new RuntimeException(\"Memento <> Caretaker\");",
                    memento,
                    caretaker),
                 100),
            pb
        )
]