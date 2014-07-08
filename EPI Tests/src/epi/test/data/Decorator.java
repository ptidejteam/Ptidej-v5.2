/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package epi.test.data;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  08/01/15
 */
public final class Decorator implements BitVectorPattern {
	private static Decorator UniqueInstance;
	public static Decorator getInstance() {
		if (Decorator.UniqueInstance == null) {
			Decorator.UniqueInstance = new Decorator();
		}
		return Decorator.UniqueInstance;
	}

	private static final String DECORATOR_NAME = "Decorator";
	private static final String DECORATOR_STRING_NONE =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_1 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_2 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_3 =
		"Decorator containerComposition Component inheritance Decorator inheritance ConcreteDecorator dummyRelationship Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_4 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component";
	private static final String DECORATOR_STRING_1_AND_2 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_1_AND_3 =
		"Decorator containerComposition Component inheritance Decorator inheritance ConcreteDecorator dummyRelationship Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_1_AND_4 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component";
	private static final String DECORATOR_STRING_2_AND_3 =
		"Decorator containerComposition Component inheritance Decorator inheritance ConcreteDecorator dummyRelationship Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_2_AND_4 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component";
	private static final String DECORATOR_STRING_3_AND_4 =
		"EPI_Abstract_Decorator containerComposition EPI_Abstract_Component inheritance EPI_Abstract_Decorator inheritance ConcreteDecorator dummyRelationship EPI_Abstract_Component";
	private static final String DECORATOR_STRING_1_AND_2_AND_3 =
		"Decorator containerComposition Component inheritance Decorator inheritance ConcreteDecorator dummyRelationship Component inheritance ConcreteComponent";
	private static final String DECORATOR_STRING_ALL =
		"Decorator containerComposition Component inheritance Decorator inheritance ConcreteDecorator dummyRelationship Component";
	private static final String[] DECORATOR_STRINGS =
		new String[] {
			DECORATOR_STRING_NONE,
			DECORATOR_STRING_1,
			DECORATOR_STRING_2,
			DECORATOR_STRING_3,
			DECORATOR_STRING_4,
			DECORATOR_STRING_1_AND_2,
			DECORATOR_STRING_1_AND_3,
			DECORATOR_STRING_1_AND_4,
			DECORATOR_STRING_2_AND_3,
			DECORATOR_STRING_2_AND_4,
			DECORATOR_STRING_3_AND_4,
			DECORATOR_STRING_1_AND_2_AND_3,
			DECORATOR_STRING_ALL };

	public String getName(){
		return DECORATOR_NAME;
	}
	public String[] getStrings(){
		return DECORATOR_STRINGS;
	}
}
