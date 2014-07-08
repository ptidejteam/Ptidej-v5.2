/*
 * Created on 2005-05-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dram.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * @author rachedsa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClassVerify {
	private BufferedReader buf;

	public ClassVerify() {

		try {
			FileReader rd =
				new FileReader("C:\\Documents and Settings\\rachedsa\\Bureau\\resjunit1.txt");
			this.buf = new BufferedReader(rd);
			//			outputFile = new File(
			//					"C:\\Documents and Settings\\rachedsa\\Bureau\\toto1.txt");
			//
			//			fos = new FileOutputStream(outputFile);
			//			osw = new OutputStreamWriter(fos);
			verify();

			this.buf.close();
			rd.close();
			//osw.close();
			//fos.close();
		}
		catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}
	private void verify() {
		try {
			String chaine = null;
			String token;
			String token1;
			int nbre1 = 0;
			int nbre2 = 0;
			int nbre3 = 0;
			int nbre4 = 0;
			String subchaine = "junit.framework.TestCase";
			while ((chaine = this.buf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(chaine, "|");
				token = st.nextToken();
				token1 = st.nextToken();
				st.nextToken();
				if (token.equals("constructorEntry0")) {
					if (token1.equals(subchaine)) {
						nbre1 = nbre1 + 1;
					}
				}
				if (token.equals("constructorExit0")) {
					if (token1.equals(subchaine)) {
						nbre2 = nbre2 + 1;
					}
				}
				if (token.equals("methodEntry0")) {
					if (token1.equals(subchaine)) {
						nbre3 = nbre3 + 1;
					}
				}
				if (token.equals("methodExit0")) {
					if (token1.equals(subchaine)) {
						nbre4 = nbre4 + 1;
					}
				}
			}
			System.out.println("nbre constructorEntry0 " + nbre1);
			System.out.println("nbre constructorExit0 " + nbre2);
			System.out.println("nbre methodEntry0 " + nbre3);
			System.out.println("nbre methodExit0 " + nbre4);
		}
		catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}

	public static void main(final String[] args) {
		try {
			// String ff = "C:\\Documents and Settings\\rachedsa\\Bureau\\restestRelation2.txt";
			new ClassVerify();

		}
		catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}
}
