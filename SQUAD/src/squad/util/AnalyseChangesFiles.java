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

public class AnalyseChangesFiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void CombineBlobProbsandChanges() {

		DataFileTableModel DesignChurn =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/DesignChurn.csv");

		DataFileTableModel NIM =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/NIM.csv");
		DataFileTableModel NNM =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/NNM.csv");
		DataFileTableModel NSM =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/NSM.csv");
		DataFileTableModel NPMPrev =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/NPMPrev.csv");
		DataFileTableModel NPMNext =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/NPMNext.csv");
		DataFileTableModel Instability =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/Instability.csv");
		DataFileTableModel Stress =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/Stress.csv");
		DataFileTableModel InstructionsAdded =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InstructionsAdded.csv");
		DataFileTableModel InstructionsDeleted =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InstructionsDeleted.csv");
		DataFileTableModel InstructionsModified =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InstructionsModified.csv");
		DataFileTableModel InstructionsChange =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InstructionsChange.csv");
		DataFileTableModel InstructionsChangeRatio =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InstructionsChangeRatio.csv");
		DataFileTableModel ClassAdded =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/ClassAdded.csv");
		DataFileTableModel ClassRemoved =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/ClassRemoved.csv");
		DataFileTableModel ClassToInterface =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/ClassToInterface.csv");
		DataFileTableModel InterfaceToClass =
			new DataFileTableModel(
				"D:/Documents/Workspace/Defects evolution/Analysis/metrics of change/InterfaceToClass.csv");

		//System.out.println(data_0_role.getValueAt(1, 3));

		//		for (int i = 0; i < data_ND_role.getColumnCount(); i++) {
		//			try {
		//				final FileWriter out =
		//					new FileWriter("rsc/composition/"
		//							+ data_ND_role.getColumnName(i) + "ND_0.csv");
		//				// now the R script file
		//
		//				final FileWriter Rout =
		//					new FileWriter("rsc/composition/RSript_"
		//							+ data_ND_role.getColumnName(i) + "ND_0.R");
		//
		//				System.out.println("source(\"" + "RSript_"
		//						+ data_ND_role.getColumnName(i) + "ND_0.R\"" + ")");
		//
		//		

	}

}
