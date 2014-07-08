/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.motif.models;

import padl.motif.IDesignMotifModel;
import padl.motif.util.adapter.DesignMotifModelAdapter;

public abstract class TestMotifModel extends DesignMotifModelAdapter {
	private static final long serialVersionUID = -3938778272080321394L;
	private static final String DEFAULT_ID_STRING = "TestPatternModel";
	private static final char[] DEFAULT_ID = DEFAULT_ID_STRING.toCharArray();

	public TestMotifModel() {
		this(TestMotifModel.DEFAULT_ID);
	}
	public TestMotifModel(final char[] aName) {
		super(aName);
	}
	public final int getClassification() {
		return IDesignMotifModel.TEST_MOTIF;
	}
	public String getIntent() {
		return TestMotifModel.DEFAULT_ID_STRING;
	}
	public char[] getName() {
		return TestMotifModel.DEFAULT_ID;
	}
}
