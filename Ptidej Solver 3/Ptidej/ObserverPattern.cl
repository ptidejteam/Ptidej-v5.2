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

[customProblemForObserverPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Observer Pattern Problem", length(listOfEntities), 90),
            observer := makePtidejVar(pb, "Observer", 1, length(listOfEntities)),
            subject := makePtidejVar(pb, "Subject", 1, length(listOfEntities)),
            concreteSubject := makePtidejVar(pb, "ConcreteSubject", 1, length(listOfEntities)),
            concreteObserver := makePtidejVar(pb, "ConcreteObserver", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionConstraint(
                    "Subject <>--> Observer",
                    "throw new RuntimeException(\"Subject <>--> Observer\");",
                    subject,
                    observer),
                 50),
            post(pb,
                 makeUseConstraint(
                    "Subject ----> Observer",
                    "throw new RuntimeException(\"Subject <>--> Observer\");",
                    subject,
                    observer),
                 90),
            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteSubject -|>- Subject",
                    "ConcreteSubject, Subject |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    concreteSubject,
                    subject),
                 90),
            post(pb,
                 makeInheritancePathConstraint(
                    "ConcreteObserver -|>- Observer",
                    "ConcreteObserver, Observer |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    concreteObserver,
                    observer),
                 90),
            post(pb,
                 makeUseConstraint(
                    "ConcreteObserver ----> ConcreteSubject",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    concreteObserver,
                    concreteSubject),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> Subject",
                    "throw new RuntimeException(\"Observer <> Subject\");",
                    observer,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> ConcreteSubject",
                    "throw new RuntimeException(\"Observer <> ConcreteSubject\");",
                    observer,
                    concreteSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> ConcreteObserver",
                    "throw new RuntimeException(\"Observer <> ConcreteObserver\");",
                    observer,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> Observer",
                    "throw new RuntimeException(\"Subject <> Observer\");",
                    subject,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> ConcreteSubject",
                    "throw new RuntimeException(\"Subject <> ConcreteSubject\");",
                    subject,
                    concreteSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> ConcreteObserver",
                    "throw new RuntimeException(\"Subject <> ConcreteObserver\");",
                    subject,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> Observer",
                    "throw new RuntimeException(\"ConcreteSubject <> Observer\");",
                    concreteSubject,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> Subject",
                    "throw new RuntimeException(\"ConcreteSubject <> Subject\");",
                    concreteSubject,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> ConcreteObserver",
                    "throw new RuntimeException(\"ConcreteSubject <> ConcreteObserver\");",
                    concreteSubject,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> Observer",
                    "throw new RuntimeException(\"ConcreteObserver <> Observer\");",
                    concreteObserver,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> Subject",
                    "throw new RuntimeException(\"ConcreteObserver <> Subject\");",
                    concreteObserver,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> ConcreteSubject",
                    "throw new RuntimeException(\"ConcreteObserver <> ConcreteSubject\");",
                    concreteObserver,
                    concreteSubject),
                 100),
            pb
        )
]
[ac4ProblemForObserverPattern() : PtidejProblem
    ->  verbose() := 0,
        let pb := makePtidejProblem("Observer Pattern Problem", length(listOfEntities), 90),
            observer := makePtidejVar(pb, "Observer", 1, length(listOfEntities)),
            subject := makePtidejVar(pb, "Subject", 1, length(listOfEntities)),
            concreteSubject := makePtidejVar(pb, "ConcreteSubject", 1, length(listOfEntities)),
            concreteObserver := makePtidejVar(pb, "ConcreteObserver", 1, length(listOfEntities)) in (

            setVarsToShow(pb.globalSearchSolver, pb.vars),

            post(pb,
                 makeCompositionAC4Constraint(
                    "Subject <>--> Observer",
                    "throw new RuntimeException(\"Subject <>--> Observer\");",
                    subject,
                    observer),
                 50),
            post(pb,
                 makeUseAC4Constraint(
                    "Subject ----> Observer",
                    "throw new RuntimeException(\"Subject <>--> Observer\");",
                    subject,
                    observer),
                 90),
            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteSubject -|>- Subject",
                    "ConcreteSubject, Subject |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    concreteSubject,
                    subject),
                 90),
            post(pb,
                 makeInheritancePathAC4Constraint(
                    "ConcreteObserver -|>- Observer",
                    "ConcreteObserver, Observer |\\n\\t\\t\\tjavaXL.XClass c1, javaXL.XClass c2 |\\n\\t\\t\\tc1.setSuperclass(c2.getName());",
                    concreteObserver,
                    observer),
                 90),
            post(pb,
                 makeUseAC4Constraint(
                    "ConcreteObserver ----> ConcreteSubject",
                    "throw new RuntimeException(\"Composite <>--> Component\");",
                    concreteObserver,
                    concreteSubject),
                 90),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> Subject",
                    "throw new RuntimeException(\"Observer <> Subject\");",
                    observer,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> ConcreteSubject",
                    "throw new RuntimeException(\"Observer <> ConcreteSubject\");",
                    observer,
                    concreteSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Observer <> ConcreteObserver",
                    "throw new RuntimeException(\"Observer <> ConcreteObserver\");",
                    observer,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> Observer",
                    "throw new RuntimeException(\"Subject <> Observer\");",
                    subject,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> ConcreteSubject",
                    "throw new RuntimeException(\"Subject <> ConcreteSubject\");",
                    subject,
                    concreteSubject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "Subject <> ConcreteObserver",
                    "throw new RuntimeException(\"Subject <> ConcreteObserver\");",
                    subject,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> Observer",
                    "throw new RuntimeException(\"ConcreteSubject <> Observer\");",
                    concreteSubject,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> Subject",
                    "throw new RuntimeException(\"ConcreteSubject <> Subject\");",
                    concreteSubject,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteSubject <> ConcreteObserver",
                    "throw new RuntimeException(\"ConcreteSubject <> ConcreteObserver\");",
                    concreteSubject,
                    concreteObserver),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> Observer",
                    "throw new RuntimeException(\"ConcreteObserver <> Observer\");",
                    concreteObserver,
                    observer),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> Subject",
                    "throw new RuntimeException(\"ConcreteObserver <> Subject\");",
                    concreteObserver,
                    subject),
                 100),
            post(pb,
                 makeNotEqualConstraint(
                    "ConcreteObserver <> ConcreteSubject",
                    "throw new RuntimeException(\"ConcreteObserver <> ConcreteSubject\");",
                    concreteObserver,
                    concreteSubject),
                 100),
            pb
        )
]