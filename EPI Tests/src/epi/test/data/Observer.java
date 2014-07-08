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
public final class Observer implements BitVectorPattern {
	private static Observer UniqueInstance;
	public static Observer getInstance() {
		if (Observer.UniqueInstance == null) {
			Observer.UniqueInstance = new Observer();
		}
		return Observer.UniqueInstance;
	}

	private static final String OBSERVER_NAME = "Observer";
	private static final String OBSERVER_STRING_NONE =
		"Subject association EPI_Abstract_Observer inheritance ConcreteObserver association Subject dummyRelationship EPI_Abstract_Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_1 =
		"Subject association EPI_Abstract_Observer inheritance ConcreteObserver association Subject dummyRelationship EPI_Abstract_Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_2 =
		"Subject association EPI_Abstract_Observer inheritance ConcreteObserver association Subject dummyRelationship EPI_Abstract_Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_3 =
		"Subject association Observer inheritance ConcreteObserver association Subject dummyRelationship Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_4 = "";
	// Same as NONE because all roles must be played.
	private static final String OBSERVER_STRING_1_AND_2 =
		"Subject association EPI_Abstract_Observer inheritance ConcreteObserver association Subject dummyRelationship EPI_Abstract_Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_1_AND_3 =
		"Subject association Observer inheritance ConcreteObserver association Subject dummyRelationship Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_1_AND_4 = "";
	// Same as 1 and 4.
	private static final String OBSERVER_STRING_2_AND_3 =
		"Subject association Observer inheritance ConcreteObserver association Subject dummyRelationship Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_2_AND_4 = "";
	// Same as 2 and 4.
	private static final String OBSERVER_STRING_3_AND_4 = "";
	// Same as 3 and 4.
	private static final String OBSERVER_STRING_1_AND_2_AND_3 =
		"Subject association Observer inheritance ConcreteObserver association Subject dummyRelationship Observer ignorance Subject ignorance ConcreteObserver";
	private static final String OBSERVER_STRING_ALL = "";
	// Same as 1 and 2 and 3.
	private static final String[] OBSERVER_STRINGS =
		new String[] {
			OBSERVER_STRING_NONE,
			OBSERVER_STRING_1,
			OBSERVER_STRING_2,
			OBSERVER_STRING_3,
			OBSERVER_STRING_4,
			OBSERVER_STRING_1_AND_2,
			OBSERVER_STRING_1_AND_3,
			OBSERVER_STRING_1_AND_4,
			OBSERVER_STRING_2_AND_3,
			OBSERVER_STRING_2_AND_4,
			OBSERVER_STRING_3_AND_4,
			OBSERVER_STRING_1_AND_2_AND_3,
			OBSERVER_STRING_ALL };

	public String getName(){
		return OBSERVER_NAME;
	}
	public String[] getStrings(){
		return OBSERVER_STRINGS;
	}
}
