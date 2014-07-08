/*
 * (c) Copyright Yann-Gaël Guéhéneuc,
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
package epi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import padl.kernel.IFirstClassEntity;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/04/05
 */
public class Matrix {
	private final List indexes;
	private final int[][] values;

	public Matrix(final int size) {
		this.indexes = new ArrayList();
		this.values = new int[size][size];
	}
	/**
	 * This method computes the difference between the out-degree and
	 * the in-degree for all the entities in a idiom-level model.
	 */
	public Map computeDifference() {
		final Map map = new HashMap();
		final int size = this.values.length;
		int difference;
		for (int i = 0; i < size; i++) {
			difference = 0;
			for (int j = 0; j < size; j++) {
				difference += this.values[i][j];
			}
			for (int j = 0; j < size; j++) {
				difference -= this.values[j][i];
			}
			map.put(this.indexes.get(i), new Integer(difference));
		}
		return map;
	}
	public void decrementValue(
		final IFirstClassEntity anEntity1,
		final IFirstClassEntity anEntity2) {
		//if(this.values[this.getIndex(anEntity1)][this.getIndex(anEntity2)] == 1)
		this.values[this.getIndex(anEntity1)][this.getIndex(anEntity2)]--;
	}
	private int getIndex(final IFirstClassEntity anEntity) {
		final String ID = anEntity.getDisplayID();
		if (!this.indexes.contains(ID)) {
			this.indexes.add(ID);
		}
		return this.indexes.indexOf(ID);
	}
	//TODO: Temporaire
	public int getInRelationNb(final IFirstClassEntity anEntity) {
		final int index = this.getIndex(anEntity);
		int inNb = 0;
		for (int j = 0; j < this.values.length; j++) {
			inNb += this.values[j][index];
		}
		return inNb - this.values[index][index];
	}
	public int getValue(
		final IFirstClassEntity anEntity1,
		final IFirstClassEntity anEntity2) {
		return this.values[this.getIndex(anEntity1)][this.getIndex(anEntity2)];
	}
	public void incrementValue(
		final IFirstClassEntity anEntity1,
		final IFirstClassEntity anEntity2) {

		//if(this.values[this.getIndex(anEntity1)][this.getIndex(anEntity2)] == 0)
		//System.out.println("1: " + this.getIndex(anEntity1) + " " + anEntity1.getName());
		//System.out.println("2: " + this.getIndex(anEntity2) + " " + anEntity2.getName());
		//System.out.println("3: " + this.indexes);

		this.values[this.getIndex(anEntity1)][this.getIndex(anEntity2)]++;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		final int size = this.values.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buffer.append(this.values[i][j]);
				buffer.append(' ');
			}
			buffer.append('\t');
			buffer.append(this.indexes.get(i));
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
