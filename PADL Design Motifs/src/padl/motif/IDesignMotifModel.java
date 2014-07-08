/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.motif;

import padl.kernel.IAbstractModel;
import padl.kernel.IConstituent;
import padl.motif.detector.IDetector;

/**
 * @author Yann-Gaël Guéhéneuc
 */
public interface IDesignMotifModel extends IAbstractModel, IConstituent {
	// Classification type constants.
	int BEHAVIORAL_DESIGN_MOTIF = 1;
	int CREATIONAL_DESIGN_MOTIF = 2;
	int DEFECT_MOTIF = 4;
	int SOLUTION_MOTIF = 8;
	int STRUCTURAL_DESIGN_MOTIF = 16;
	int TEST_MOTIF = 32;
	int UNDEFINED_MOTIF = 64;

	int getClassification();
	IDetector getDetector();
	String getIntent();
	void setClassification(final int aClassification);
	void setDetector(final IDetector aDetector);
	void setIntent(final String anIntent);
}