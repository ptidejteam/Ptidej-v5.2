/*
 * (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.example.aggregation;

import java.util.List;

/**
 * @version	0.1
 * @author 	Yann-Gaël Guéhéneuc
 *
 * One container aggregation relationship to A through
 * List listOfAs and the two methods addA(), removeA().
 * 
 * Two aggregation relatiobships to A through the
 * add() and remove() methods of List listOfAs.
 * 
 * Two use relationships to A through the
 * parameters of methods addA() and removeA().
 */
public class Aggregation11 {
	private List listOfAs;
	void addA(final A newA) {
		this.listOfAs.add(newA);
	}
	void removeA(final A newA) {
		this.listOfAs.remove(newA);
	}
}
