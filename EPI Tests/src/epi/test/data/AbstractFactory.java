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
public final class AbstractFactory implements BitVectorPattern {
	private static AbstractFactory UniqueInstance;
	public static AbstractFactory getInstance() {
		if (AbstractFactory.UniqueInstance == null) {
			AbstractFactory.UniqueInstance = new AbstractFactory();
		}
		return AbstractFactory.UniqueInstance;
	}

	private static final String ABSTRACT_FACTORY_NAME = "AbstractFactory";
	private static final String ABSTRACT_FACTORY_STRING_NONE =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_1 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_2 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_3 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_4 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_1_AND_2 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_1_AND_3 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_1_AND_4 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_2_AND_3 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_2_AND_4 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship EPI_Abstract_AbstractProduct inheritance Product dummyRelationship EPI_Abstract_AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_3_AND_4 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_1_AND_2_AND_3 =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String ABSTRACT_FACTORY_STRING_ALL =
		"ConcreteFactory creation Product dummyRelationship ConcreteFactory association Product dummyRelationship AbstractProduct inheritance Product dummyRelationship AbstractFactory inheritance ConcreteFactory";
	private static final String[] ABSTRACT_FACTORY_STRINGS =
		new String[] {
			ABSTRACT_FACTORY_STRING_NONE,
			ABSTRACT_FACTORY_STRING_1,
			ABSTRACT_FACTORY_STRING_2,
			ABSTRACT_FACTORY_STRING_3,
			ABSTRACT_FACTORY_STRING_4,
			ABSTRACT_FACTORY_STRING_1_AND_2,
			ABSTRACT_FACTORY_STRING_1_AND_3,
			ABSTRACT_FACTORY_STRING_1_AND_4,
			ABSTRACT_FACTORY_STRING_2_AND_3,
			ABSTRACT_FACTORY_STRING_2_AND_4,
			ABSTRACT_FACTORY_STRING_3_AND_4,
			ABSTRACT_FACTORY_STRING_1_AND_2_AND_3,
			ABSTRACT_FACTORY_STRING_ALL };

	public String getName() {
		return ABSTRACT_FACTORY_NAME;
	}
	public String[] getStrings() {
		return ABSTRACT_FACTORY_STRINGS;
	}
}
