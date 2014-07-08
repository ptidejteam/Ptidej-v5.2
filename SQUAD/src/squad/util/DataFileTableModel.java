/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package squad.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class DataFileTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 5949510568846640279L;
	protected Vector<String> data;
	protected Vector<String> columnNames;
	protected String datafile;

	public DataFileTableModel(String f) {
		this.datafile = f;
		initVectors();
	}

	public void initVectors() {
		String aLine;
		this.data = new Vector<String>();
		this.columnNames = new Vector<String>();
		try {
			FileInputStream fin = new FileInputStream(this.datafile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fin));
			// extract column names
			StringTokenizer st1 = new StringTokenizer(br.readLine(), ",");
			while (st1.hasMoreTokens())
				this.columnNames.addElement(st1.nextToken());
			// extract data
			while ((aLine = br.readLine()) != null) {
				StringTokenizer st2 = new StringTokenizer(aLine, ",");
				while (st2.hasMoreTokens())
					this.data.addElement(st2.nextToken());
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		return this.data.size() / getColumnCount();
	}

	public int getColumnCount() {
		return this.columnNames.size();
	}

	public String getColumnName(int columnIndex) {
		String colName = "";

		if (columnIndex <= getColumnCount())
			colName = (String) this.columnNames.elementAt(columnIndex);

		return colName;
	}

	public Class<String> getColumnClass(int columnIndex) {
		return String.class;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (String) this.data.elementAt((rowIndex * getColumnCount())
				+ columnIndex);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		return;
	}
}
