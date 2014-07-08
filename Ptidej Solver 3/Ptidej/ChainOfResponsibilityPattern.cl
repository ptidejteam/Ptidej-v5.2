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

[customProblemForChainOfResponsibilityPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Chain Of Responsibility Pattern Problem", length(listOfEntities), 90),
            client          := makePtidejVar(pb, "Client",          1, length(listOfEntities)),
            handler         := makePtidejVar(pb, "Handler",         1, length(listOfEntities)),
            concreteHandler := makePtidejVar(pb, "ConcreteHandler", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            printf("Setting constraints for Chain Of Responsibility pattern...\n"),
            post(pb,
                 makeStrictInheritanceConstraint(
                    "ConcreteHandler -|>- Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    concreteHandler,
                    handler),
                 50),
            post(pb,
                 makeUseConstraint(
                    "Handler ---> Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    handler,
                    handler),
                 90),
            post(pb,
                 makeUseConstraint(
                    "Client ---> Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    client,
                    handler),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Client != Handler",
                    "throw new RuntimeException(\"Client != Handler\");",
                    client,
                    handler),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Handler != ConcreteHandler",
                    "throw new RuntimeException(\"Handler != ConcreteHandler\");",
                    handler,
                    concreteHandler),
                 100),
            pb
        )
]
[ac4ProblemForChainOfResponsibilityPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Chain Of Responsibility Pattern Problem", length(listOfEntities), 90),
            client          := makePtidejVar(pb, "Client",          1, length(listOfEntities)),
            handler         := makePtidejVar(pb, "Handler",         1, length(listOfEntities)),
            concreteHandler := makePtidejVar(pb, "ConcreteHandler", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeStrictInheritanceAC4Constraint(
                    "ConcreteHandler -|>- Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    concreteHandler,
                    handler),
                 50),
            post(pb,
                 makeUseAC4Constraint(
                    "Handler ---> Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    handler,
                    handler),
                 90),
            post(pb,
                 makeUseAC4Constraint(
                    "Client ---> Handler",
                    "System.out.println(\"Not implemented yet.\");",
                    client,
                    handler),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Client != Handler",
                    "throw new RuntimeException(\"Client != Handler\");",
                    client,
                    handler),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Handler != ConcreteHandler",
                    "throw new RuntimeException(\"Handler != ConcreteHandler\");",
                    handler,
                    concreteHandler),
                 100),
            pb
        )
]