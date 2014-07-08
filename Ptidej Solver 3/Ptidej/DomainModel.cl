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



// Model of an entity within the constraint solver.
Entity <: ephemeral_object
Entity <: ephemeral_object(
    name:            								string,

	// Yann 2002/12/20: Diet...
	// It seems that the C++ code generator
	// has some limitations with respect to
	// handling field as property... I can't
	// use reflection to get the cache from
	// the field name... I must use a dumb
	// counter... Actually, maybe not... It
	// seems that the following line bug the
	// compiler off:
	//	listOfCachedReachableEntities:list<Entity> := get(cachedProperty, entity) as list<Entity>
	// while this following does not!?
	//	listOfCachedReachableEntities:list<Entity> := get(cachedProperty, entity)

	// 1
    superEntities:   								list<Entity>,
    cachedReachableSuperEntities:					list<Entity>,

	// 2
	aggregatedEntities: 							list<Entity>,
	cachedReachableAggregatedEntities:				list<Entity>,

	// 3
	associatedEntities: 							list<Entity>,
	cachedReachableAssociatedEntities:				list<Entity>,

	// 4
    componentsType:  								list<Entity>,
    composedEntities:								list<list<Entity>>,

	// 5
    containerAggregatedEntities:					list<Entity>,
    cachedReachableContainerAggregatedEntities:		list<Entity>,

	// 6
    containerComponentsType:  						list<Entity>,
    containerComposedEntities:						list<list<Entity>>,
    cachedReachableContainerComposedEntities:		list<Entity>,

	// 7
    createdEntities: 								list<Entity>,
    cachedReachableCreatedEntities:					list<Entity>,

	// 8
    knownEntities:   								list<Entity>,
    cachedReachableKnownEntities:					list<Entity>,

	// 9
    unknownEntities: 								list<Entity>,
    cachedReachableUnknownEntities:				list<Entity>
)
[self_print(e:Entity) : void
    ->  if known?(name, e) (
            printf("~S", e.name)
        )
        else (
            printf("Unnamed instance of Entity?!")
        )
]
// Yann 2002/12/08: Cache!
// The following method returns the
// cache associated with all
// reachable entities throug a given
// relationship.
[getCachedReachableRelationship(relationship:property) : property
	->	// [0] relationship = ~S // relationship,
		let
			cache:property := relationship
		in (
			if (relationship = superEntities) (
				cache := cachedReachableSuperEntities
			),
			if (relationship = aggregatedEntities) (
				cache := cachedReachableAggregatedEntities
			),
			if (relationship = associatedEntities) (
				cache := cachedReachableAssociatedEntities
			),
			if (relationship = containerAggregatedEntities) (
				cache := cachedReachableContainerAggregatedEntities
			),
			if (relationship = containerComposedEntities) (
				cache := cachedReachableContainerComposedEntities
			),
			if (relationship = createdEntities) (
				cache := cachedReachableCreatedEntities
			),
			if (relationship = knownEntities) (
				cache := cachedReachableKnownEntities
			),
			if (relationship = unknownEntities) (
				cache := cachedReachableUnknownEntities
			),
			// [0] cache = ~S // cache,
			cache
		)
]

// Global entities.
// Yann 2002/08/11: Entity declarations!
// I do not treat specially the java.lang.Object entity anymore
// because the meta-model now possesses the notion of Ghost:
// An entity that is references by some entities of the model
// but that is not really part of the model (i.e., about which
// we know nothing but its existence and name).
// 		peJavaLangObject:Entity := Entity(name = "java.lang.Object", componentsType = list(peNil), components = list(), knownEntities = list(), unknownEntities = list(), createdEntities = list())
//                                 (peJavaLangObject.superEntities := list(peJavaLangObject))
// Yann 2002/10/08: Back!
// I now use arrays instead of lists to handle entities properties.
// Thus, I need a 'neutral' entity which I can use for array declarations.

peNil:Entity          := Entity(name = "Nil")
minBoundValue:integer := 0
maxBoundValue:integer := 999999

// This global variable is define into the domain file (Domain.cl).

listOfEntities:list<Entity> := list<Entity>()

[self_print(l:list<Entity>) : void
    ->  printf("("),
        for i in (1 .. length(l)) (
            printf("~S", l[i]),
            if (i < length(l)) (
                printf(", ")
            )
        ),
        printf(")")
]