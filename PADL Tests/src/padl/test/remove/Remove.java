/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.test.remove;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.motif.IDesignMotifModel;
import padl.motif.repository.Composite;

public final class Remove extends TestCase {
	private IDesignMotifModel compositePattern;
	private IDesignMotifModel clonedCompositePattern1;
	private IDesignMotifModel clonedCompositePattern2;
	public Remove(String name) {
		super(name);
	}
	protected void setUp() throws CloneNotSupportedException {
		this.compositePattern = new Composite();

		this.clonedCompositePattern1 =
			(IDesignMotifModel) this.compositePattern.clone();
		this.clonedCompositePattern1.removeAllConstituent();

		this.clonedCompositePattern2 =
			(IDesignMotifModel) this.compositePattern.clone();
		this.clonedCompositePattern2.removeConstituentFromID("Composite"
			.toCharArray());
	}
	public void test1() {
		Assert.assertEquals(
			"Number of entities",
			0,
			this.clonedCompositePattern1.getNumberOfConstituents());
	}
	public void test2() {
		Assert.assertEquals(
			"Number of entities",
			2,
			this.clonedCompositePattern2.getNumberOfConstituents());
	}
}
