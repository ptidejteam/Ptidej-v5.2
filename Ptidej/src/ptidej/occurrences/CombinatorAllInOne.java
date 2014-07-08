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
package ptidej.occurrences;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import util.io.ProxyDisk;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CombinatorAllInOne {
	private static final String FILE_EXTENSION = "csv";
	private static final String FILE_HEADER = "All Data for ";

	public static void main(final String[] args) {
		final CombinatorAllInOne combinatorPerProgramPerVersion =
			new CombinatorAllInOne();

		final TableModel dataTable = new TableModel();
		// By default, the table must include one cell (one row, one column)...
		dataTable.addColumn("Class Names");
		dataTable.addRow(new String[] { "Data Name" });

		combinatorPerProgramPerVersion.populateTable(
			"D:/Documents/Papers/2011/WCRE (Yann and Giulio)/Data/",
			dataTable);
		combinatorPerProgramPerVersion
			.writeTableToCSVFile(
				dataTable,
				"D:/Documents/Papers/2011/WCRE (Yann and Giulio)/Data/All Data In One File.csv");
	}
	private final Map mapOfColumnNamesWithColumnPositions = new HashMap();
	//	private final Map mapOfRowNamesWithRowPositions = new HashMap();
	private void addValueToTable(
		final TableModel aDataTable,
		final String aColumnName,
		final String aRowName,
		final String aValue) {

		// This is the only method that writes into the table.

		int columnNumber =
			this.findColumnNumberFromName(aDataTable, aColumnName);

		// Yann 2011/06/17: Assumption...
		// I assume that there is no duplicate inside a data file and between files!
		//	int rowNumber = this.findRowNumberFromName(aDataTable, aRowName);
		//	if (rowNumber == -1) {
		final int rowNumber = aDataTable.getRowCount() - 1;
		if (!aDataTable.getValueAt(rowNumber, 0).equals(aRowName)) {
			aDataTable.addRow(new String[0]);
			aDataTable.setValueAt(aRowName, rowNumber + 1, 0);
			aDataTable.setValueAt(aValue, rowNumber + 1, columnNumber);
		}
		else {
			aDataTable.setValueAt(aValue, rowNumber, columnNumber);
		}
	}
	private int findColumnNumberFromName(
		final TableModel aDataTable,
		final String aColumnName) {

		if (!this.mapOfColumnNamesWithColumnPositions.containsKey(aColumnName)) {
			System.out.print("\tAdding missing column: ");
			System.out.println(aColumnName);

			aDataTable.addColumn(aColumnName);
			final int columnNumber = aDataTable.getColumnCount() - 1;
			aDataTable.setValueAt(aColumnName, 0, columnNumber);

			this.mapOfColumnNamesWithColumnPositions.put(
				aColumnName,
				new Integer(columnNumber));
		}

		return ((Integer) this.mapOfColumnNamesWithColumnPositions
			.get(aColumnName)).intValue();
	}
	//	private int findRowNumberFromName(
	//		final TableModel aDataTable,
	//		final String aRowName) {
	//
	//		if (!this.mapOfRowNamesWithRowPositions.containsKey(aRowName)) {
	//			System.out.println(aRowName);
	//			int rowPosition = -1;
	//			final int rowCount = aDataTable.getRowCount();
	//			for (int i = 0; i < rowCount; i++) {
	//				final String rowValue = (String) aDataTable.getValueAt(i, 0);
	//				if (rowValue.equals(aRowName)) {
	//					break;
	//				}
	//			}
	//
	//			this.mapOfRowNamesWithRowPositions.put(aRowName, new Integer(
	//				rowPosition));
	//		}
	//
	//		return ((Integer) this.mapOfRowNamesWithRowPositions.get(aRowName))
	//			.intValue();
	//	}
	private void populateTable(
		final String aRootPath,
		final TableModel aDataTable) {

		final String[] fileNames = new File(aRootPath).list();
		for (int i = 0; i < fileNames.length; i++) {
			final String filePath = aRootPath + fileNames[i];
			final File file = new File(filePath);
			if (fileNames[i].startsWith(CombinatorAllInOne.FILE_HEADER)
					&& fileNames[i].endsWith(CombinatorAllInOne.FILE_EXTENSION)
					&& file.isFile()) {

				final String endOfFileName =
					fileNames[i].substring(
						CombinatorAllInOne.FILE_HEADER.length(),
						fileNames[i].length()
								- CombinatorAllInOne.FILE_EXTENSION.length()
								- 1);
				final String programName =
					endOfFileName.substring(0, endOfFileName.indexOf(' '));
				final String versionName =
					endOfFileName.substring(endOfFileName.indexOf(' ') + 1);

				System.out.print("Merging data of ");
				System.out.print(programName);
				System.out.print(" ");
				System.out.print(versionName);
				System.out.println("...");

				try {
					final CSVReader allDataForCurrentProgramAndVersion =
						new CSVReader(new FileReader(file));

					final String[] dataNames =
						allDataForCurrentProgramAndVersion.readNext();

					String[] currentDataValues;
					while ((currentDataValues =
						allDataForCurrentProgramAndVersion.readNext()) != null) {
						final int numberOfDataPoints =
							currentDataValues.length - 1;

						final StringBuffer rowName = new StringBuffer();
						rowName.append(programName);
						rowName.append(" ");
						rowName.append(versionName);
						rowName.append(" -- ");
						rowName.append(currentDataValues[0]);

						System.out.print("\tAdding ");
						System.out.print(numberOfDataPoints);
						System.out.print(" data points for: ");
						System.out.println(rowName);

						for (int j = 1; j < numberOfDataPoints; j++) {
							final String dataValue = currentDataValues[j];
							this.addValueToTable(
								aDataTable,
								dataNames[j],
								rowName.toString(),
								dataValue);
						}
					}

					allDataForCurrentProgramAndVersion.close();
				}
				catch (final FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void writeTableToCSVFile(
		final TableModel aDataTable,
		final String anOutputFile) {

		try {
			final CSVWriter csvWriter =
				new CSVWriter(ProxyDisk.getInstance().fileAbsoluteOutput(
					anOutputFile), ',');

			final List dataVector = aDataTable.getDataVector();
			final Iterator interatorOnRows = dataVector.iterator();
			while (interatorOnRows.hasNext()) {
				final List rowVector = (List) interatorOnRows.next();
				final String[] rowArray = new String[rowVector.size()];
				rowVector.toArray(rowArray);
				csvWriter.writeNext(rowArray);
			}
			csvWriter.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
