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

import java.io.IOException;
import java.io.Writer;
import util.io.ProxyDisk;

public class GroupRolesGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		groupDEMIMAWithValidatedRoles();

	}
	/*
	 * This method generates the cvs files and the R script for the DEMIMA norole and the 0-role, 1-role and 2-role.
	 * 
	 */

	public static void groupDEMIMAWithValidatedRoles() {

		DataFileTableModel data_ND_role =
			new DataFileTableModel("rsc/composition/NoRoleDEMIMAMetrics.csv");

		DataFileTableModel data_0_role =
			new DataFileTableModel("rsc/composition/ZeroRoleMetrics.csv");
		DataFileTableModel data_1_role =
			new DataFileTableModel("rsc/composition/OneRoleMetrics.csv");
		DataFileTableModel data_2_role =
			new DataFileTableModel("rsc/composition/TwoRoleMetrics.csv");

		//System.out.println(data_0_role.getValueAt(1, 3));

		for (int i = 0; i < data_ND_role.getColumnCount(); i++) {
			try {
				final Writer out =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_ND_role.getColumnName(i)
								+ "ND_0.csv");
				// now the R script file

				final Writer Rout =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_ND_role.getColumnName(i) + "ND_0.R");

				System.out.println("source(\"" + "RSript_"
						+ data_ND_role.getColumnName(i) + "ND_0.R\"" + ")");

				//and then the script
				Rout.write("rm(list=ls(all=TRUE)) \n");
				Rout.flush();
				Rout.write("metrics <- read.csv(file=\""
						+ data_ND_role.getColumnName(i) + "ND_0.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout.flush();
				Rout.write("attach(metrics) \n");
				Rout.flush();
				Rout.write("Ttest<- t.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout.flush();
				Rout.write("Ftest<- var.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout.flush();
				Rout.write("Wiltest<- wilcox.test("
						+ data_ND_role.getColumnName(i) + "~Group) \n");
				Rout.flush();
				Rout.write("outfile = \"" + data_ND_role.getColumnName(i)
						+ "ND_0.Rout\"" + " \n");
				Rout.flush();
				Rout.write("sink(outfile) \n");
				Rout.flush();
				Rout.write("print(Ttest) \n");
				Rout.flush();
				Rout.write("print(Ftest) \n");
				Rout.flush();
				Rout.write("print(Wiltest) \n");
				Rout.flush();
				Rout.write("detach(metrics) \n");
				Rout.flush();
				Rout.close();
				// end of script for 1_0		

				final Writer out1 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_ND_role.getColumnName(i)
								+ "ND_1.csv");

				// now the R script file

				final Writer Rout1 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_ND_role.getColumnName(i) + "ND_1.R");

				System.out.println("source(\"" + "RSript_"
						+ data_ND_role.getColumnName(i) + "ND_1.R\"" + ")");

				//and then the script
				Rout1.write("rm(list=ls(all=TRUE)) \n");
				Rout1.flush();
				Rout1.write("metrics <- read.csv(file=\""
						+ data_ND_role.getColumnName(i) + "ND_1.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout1.flush();
				Rout1.write("attach(metrics) \n");
				Rout1.flush();
				Rout1.write("Ttest<- t.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout1.flush();
				Rout1.write("Ftest<- var.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout1.flush();
				Rout1.write("Wiltest<- wilcox.test("
						+ data_ND_role.getColumnName(i) + "~Group) \n");
				Rout1.flush();
				Rout1.write("outfile = \"" + data_ND_role.getColumnName(i)
						+ "ND_1.Rout\"" + " \n");
				Rout1.flush();
				Rout1.write("sink(outfile) \n");
				Rout1.flush();
				Rout1.write("print(Ttest) \n");
				Rout1.flush();
				Rout1.write("print(Ftest) \n");
				Rout1.flush();
				Rout1.write("print(Wiltest) \n");
				Rout1.flush();
				Rout1.write("detach(metrics) \n");
				Rout1.flush();
				Rout1.close();
				// end of script for 2_0		

				final Writer out2 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_ND_role.getColumnName(i)
								+ "ND_2.csv");
				// now the R script file
				final Writer Rout2 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_ND_role.getColumnName(i) + "ND_2.R");

				System.out.println("source(\"" + "RSript_"
						+ data_ND_role.getColumnName(i) + "ND_2.R\"" + ")");

				//and then the script
				Rout2.write("rm(list=ls(all=TRUE)) \n");
				Rout2.flush();
				Rout2.write("metrics <- read.csv(file=\""
						+ data_ND_role.getColumnName(i) + "ND_2.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout2.flush();
				Rout2.write("attach(metrics) \n");
				Rout2.flush();
				Rout2.write("Ttest<- t.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout2.flush();
				Rout2.write("Ftest<- var.test(" + data_ND_role.getColumnName(i)
						+ "~Group) \n");
				Rout2.flush();
				Rout2.write("Wiltest<- wilcox.test("
						+ data_ND_role.getColumnName(i) + "~Group) \n");
				Rout2.flush();
				Rout2.write("outfile = \"" + data_ND_role.getColumnName(i)
						+ "ND_2.Rout\"" + " \n");
				Rout2.flush();
				Rout2.write("sink(outfile) \n");
				Rout2.flush();
				Rout2.write("print(Ttest) \n");
				Rout2.flush();
				Rout2.write("print(Ftest) \n");
				Rout2.flush();
				Rout2.write("print(Wiltest) \n");
				Rout2.flush();
				Rout2.write("detach(metrics) \n");
				Rout2.flush();
				Rout2.close();
				// end of script for 2_1		

				out.write(data_ND_role.getColumnName(i) + ", Group \n");
				out.flush();
				out1.write(data_ND_role.getColumnName(i) + ", Group \n");
				out1.flush();
				out2.write(data_ND_role.getColumnName(i) + ", Group \n");
				out2.flush();

				for (int j = 0; j < data_ND_role.getRowCount(); j++) {
					out.write(data_0_role.getValueAt(j, i) + ", 0-role \n");
					out.flush();
					out.write(data_ND_role.getValueAt(j, i)
							+ ", NoRoleDEMIMA \n");
					out.flush();

					out1.write(data_1_role.getValueAt(j, i) + ", 1-role \n");
					out.flush();
					out1.write(data_ND_role.getValueAt(j, i)
							+ ", NoRoleDEMIMA \n");
					out.flush();

					out2.write(data_2_role.getValueAt(j, i) + ", 2-role \n");
					out.flush();
					out2.write(data_ND_role.getValueAt(j, i)
							+ ", NoRoleDEMIMA \n");
					out.flush();
				}
				out.close();
				out1.close();
				out2.close();
			}
			catch (final IOException e) {
				e.printStackTrace();
			}

		}

		System.out.println("Execution completed !!!!!");

	}
	/*
	 * This method generates the csv files and the R script to compute the wilcoxon test for the 0-role, 1-role and 2-role.
	 * 
	 */

	public static void groupRolesGenerator() {
		DataFileTableModel data_0_role =
			new DataFileTableModel("rsc/composition/ZeroRoleMetrics.csv");
		DataFileTableModel data_1_role =
			new DataFileTableModel("rsc/composition/OneRoleMetrics.csv");
		DataFileTableModel data_2_role =
			new DataFileTableModel("rsc/composition/TwoRoleMetrics.csv");

		//System.out.println(data_0_role.getValueAt(1, 3));

		for (int i = 0; i < data_0_role.getColumnCount(); i++) {
			try {
				final Writer out =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_0_role.getColumnName(i)
								+ "1_0.csv");
				// now the R script file

				final Writer Rout =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_0_role.getColumnName(i) + "1_0.R");

				System.out.println("source(\"" + "RSript_"
						+ data_0_role.getColumnName(i) + "1_0.R\"" + ")");

				//and then the script
				Rout.write("rm(list=ls(all=TRUE)) \n");
				Rout.flush();
				Rout.write("metrics <- read.csv(file=\""
						+ data_0_role.getColumnName(i) + "1_0.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout.flush();
				Rout.write("attach(metrics) \n");
				Rout.flush();
				Rout.write("Ttest<- t.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout.flush();
				Rout.write("Ftest<- var.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout.flush();
				Rout.write("Wiltest<- wilcox.test("
						+ data_0_role.getColumnName(i) + "~Group) \n");
				Rout.flush();
				Rout.write("outfile = \"" + data_0_role.getColumnName(i)
						+ "1_0.Rout\"" + " \n");
				Rout.flush();
				Rout.write("sink(outfile) \n");
				Rout.flush();
				Rout.write("print(Ttest) \n");
				Rout.flush();
				Rout.write("print(Ftest) \n");
				Rout.flush();
				Rout.write("print(Wiltest) \n");
				Rout.flush();
				Rout.write("detach(metrics) \n");
				Rout.flush();
				Rout.close();
				// end of script for 1_0		

				final Writer out1 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_0_role.getColumnName(i)
								+ "2_0.csv");

				// now the R script file

				final Writer Rout1 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_0_role.getColumnName(i) + "2_0.R");

				System.out.println("source(\"" + "RSript_"
						+ data_0_role.getColumnName(i) + "2_0.R\"" + ")");

				//and then the script
				Rout1.write("rm(list=ls(all=TRUE)) \n");
				Rout1.flush();
				Rout1.write("metrics <- read.csv(file=\""
						+ data_0_role.getColumnName(i) + "2_0.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout1.flush();
				Rout1.write("attach(metrics) \n");
				Rout1.flush();
				Rout1.write("Ttest<- t.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout1.flush();
				Rout1.write("Ftest<- var.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout1.flush();
				Rout1.write("Wiltest<- wilcox.test("
						+ data_0_role.getColumnName(i) + "~Group) \n");
				Rout1.flush();
				Rout1.write("outfile = \"" + data_0_role.getColumnName(i)
						+ "2_0.Rout\"" + " \n");
				Rout1.flush();
				Rout1.write("sink(outfile) \n");
				Rout1.flush();
				Rout1.write("print(Ttest) \n");
				Rout1.flush();
				Rout1.write("print(Ftest) \n");
				Rout1.flush();
				Rout1.write("print(Wiltest) \n");
				Rout1.flush();
				Rout1.write("detach(metrics) \n");
				Rout1.flush();
				Rout1.close();
				// end of script for 2_0		

				final Writer out2 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/" + data_0_role.getColumnName(i)
								+ "2_1.csv");
				// now the R script file
				final Writer Rout2 =
					ProxyDisk.getInstance().fileTempOutput(
						"rsc/composition/RSript_"
								+ data_0_role.getColumnName(i) + "2_1.R");

				System.out.println("source(\"" + "RSript_"
						+ data_0_role.getColumnName(i) + "2_1.R\"" + ")");

				//and then the script
				Rout2.write("rm(list=ls(all=TRUE)) \n");
				Rout2.flush();
				Rout2.write("metrics <- read.csv(file=\""
						+ data_0_role.getColumnName(i) + "2_1.csv\""
						+ ",head=TRUE,sep=\"" + ",\"" + ") \n");
				Rout2.flush();
				Rout2.write("attach(metrics) \n");
				Rout2.flush();
				Rout2.write("Ttest<- t.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout2.flush();
				Rout2.write("Ftest<- var.test(" + data_0_role.getColumnName(i)
						+ "~Group) \n");
				Rout2.flush();
				Rout2.write("Wiltest<- wilcox.test("
						+ data_0_role.getColumnName(i) + "~Group) \n");
				Rout2.flush();
				Rout2.write("outfile = \"" + data_0_role.getColumnName(i)
						+ "2_1.Rout\"" + " \n");
				Rout2.flush();
				Rout2.write("sink(outfile) \n");
				Rout2.flush();
				Rout2.write("print(Ttest) \n");
				Rout2.flush();
				Rout2.write("print(Ftest) \n");
				Rout2.flush();
				Rout2.write("print(Wiltest) \n");
				Rout2.flush();
				Rout2.write("detach(metrics) \n");
				Rout2.flush();
				Rout2.close();
				// end of script for 2_1		

				out.write(data_0_role.getColumnName(i) + ", Group \n");
				out.flush();
				out1.write(data_0_role.getColumnName(i) + ", Group \n");
				out1.flush();
				out2.write(data_0_role.getColumnName(i) + ", Group \n");
				out2.flush();

				for (int j = 0; j < data_0_role.getRowCount(); j++) {
					out.write(data_1_role.getValueAt(j, i) + ", 1-role \n");
					out.flush();
					out.write(data_0_role.getValueAt(j, i) + ", 0-role \n");
					out.flush();

					out1.write(data_2_role.getValueAt(j, i) + ", 2-role \n");
					out.flush();
					out1.write(data_0_role.getValueAt(j, i) + ", 0-role \n");
					out.flush();

					out2.write(data_2_role.getValueAt(j, i) + ", 2-role \n");
					out.flush();
					out2.write(data_1_role.getValueAt(j, i) + ", 1-role \n");
					out.flush();
				}
				out.close();
				out1.close();
				out2.close();
			}
			catch (final IOException e) {
				e.printStackTrace();
			}

		}

		System.out.println("Execution completed !!!!!");

	}
}
