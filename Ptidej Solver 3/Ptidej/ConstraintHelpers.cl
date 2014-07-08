// (c) Copyright 2000-2003 Yann-Gaël Guéhéneuc,
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
// PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
// ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
// EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
// NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGES.
// 
// All Rights Reserved.



[getSymbol(m:method) : string
	->	let
			symbol:string := "unknown"
		in (
			// [0] m = ~S // m,
			if (m = makeAggregationAC4Constraint @ string
				| m = makeAggregationConstraint @ string) (
				symbol := "[]-->"
			),
			if (m = makeAssociationAC4Constraint @ string
				| m = makeAssociationConstraint @ string) (
				symbol := "---->"
			),
			if (m = makeCompositionAC4Constraint @ string
				| m = makeCompositionConstraint @ string) (
				symbol := "[#]->"
			),
			if (m = makeContainerAggregationAC4Constraint @ string
				| m = makeContainerAggregationConstraint @ string) (
				symbol := "<>-->"
			),
			if (m = makeContainerCompositionAC4Constraint @ string
				| m = makeContainerCompositionConstraint @ string) (
				symbol := "<#>->"
			),
			if (m = makeCreationAC4Constraint @ string
				| m = makeCreationConstraint @ string) (
				symbol := "-*-->"
			),
			if (m = makeIgnoranceAC4Constraint @ string
				| m = makeIgnoranceConstraint @ string) (
				symbol := "-/-->"
			),
			if (m = makeInheritanceAC4Constraint @ string
				| m = makeInheritanceConstraint @ string) (
				symbol := "-|>- or ="
			),
			if (m = makeInheritancePathAC4Constraint @ string
				| m = makeInheritancePathConstraint @ string) (
				symbol := "-|>-...-|>- or ="
			),
			if (m = makeStrictInheritancePathConstraint @ string) (
				symbol := "-|>-...-|>-"
			),
			if (m = makeUseAC4Constraint @ string
				| m = makeUseConstraint @ string) (
				symbol := "-k-->"
			),
			if (m = makeCreationAC4Constraint @ string
				| m = makeCreationConstraint @ string) (
				symbol := "-*-->"
			),
			if (m = makeStrictInheritanceAC4Constraint @ string
				| m = makeStrictInheritanceConstraint @ string) (
				symbol := "-|>-"
			),
			symbol
		)
]