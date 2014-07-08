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
public final class Adapter implements BitVectorPattern {
	private static Adapter UniqueInstance;
	public static Adapter getInstance() {
		if (Adapter.UniqueInstance == null) {
			Adapter.UniqueInstance = new Adapter();
		}
		return Adapter.UniqueInstance;
	}

	private static final String ADAPTER_NAME = "Adapter";
	private static final String ADAPTER_STRING_NONE =
		// "EPI_Abstract_Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
		"";
	private static final String ADAPTER_STRING_1 =
		"EPI_Abstract_Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_2 =
		"EPI_Abstract_Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_3 =
		"Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_4 = "";
	// Same as NONE because all roles must be played.
	private static final String ADAPTER_STRING_1_AND_2 =
		"EPI_Abstract_Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_1_AND_3 =
		"Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_1_AND_4 = ""; // Same as 1
	private static final String ADAPTER_STRING_2_AND_3 =
		"Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_2_AND_4 = "";
	// Same as 2 and 4
	private static final String ADAPTER_STRING_3_AND_4 = "";
	// Same as 3 and 4
	private static final String ADAPTER_STRING_1_AND_2_AND_3 =
		"Target inheritance Adapter association Adaptee ignorance Adapter dummyRelationship Adaptee ignorance Target ignorance Adaptee";
	private static final String ADAPTER_STRING_ALL = "";
	// Same as 1 and 2 and 3
	private static final String[] ADAPTER_STRINGS =
		new String[] {
			ADAPTER_STRING_NONE,
			ADAPTER_STRING_1,
			ADAPTER_STRING_2,
			ADAPTER_STRING_3,
			ADAPTER_STRING_4,
			ADAPTER_STRING_1_AND_2,
			ADAPTER_STRING_1_AND_3,
			ADAPTER_STRING_1_AND_4,
			ADAPTER_STRING_2_AND_3,
			ADAPTER_STRING_2_AND_4,
			ADAPTER_STRING_3_AND_4,
			ADAPTER_STRING_1_AND_2_AND_3,
			ADAPTER_STRING_ALL };

	public String getName(){
		return ADAPTER_NAME;
	}
	public String[] getStrings(){
		return ADAPTER_STRINGS;
	}
}
