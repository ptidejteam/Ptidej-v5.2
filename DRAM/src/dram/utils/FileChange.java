/*
 * Created on 2005-05-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dram.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import dram.ui.DRAMAdjacencyMatrix;

/**
 * @author rachedsa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileChange {
	private FileReader rd;
	private BufferedReader buf;
	private FileOutputStream fos;
	private OutputStreamWriter osw;	
	private File outputFile;
	
	public FileChange(String nameofFile){
		String chaine = null;
		String token;
		String token1;
		String token2;
		String token3;
		String token4;
		String token5;
		String record;
		int tt = 0;
		
		try {
			this.rd = new FileReader(nameofFile);					
			this.buf = new BufferedReader(this.rd);	
			
			this.outputFile = new File(DRAMAdjacencyMatrix.tempFile);
			this.fos = new FileOutputStream(this.outputFile);
			this.osw = new OutputStreamWriter(this.fos);
			
			while ((chaine = this.buf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(chaine, "|");
				token = st.nextToken();
				token1 = st.nextToken();
				token2 = st.nextToken();	
				tt = tt+ 1;
				if (token.equals("constructorEntry0") || token.equals("constructorExit0")){
					token3 = st.nextToken();
					token4 = st.nextToken();
					record = token1 + " " + token2 + " " + token3 + " " + token4 + '\n';	
					//record = token1 + " " + token2 + " " + token3 + " " + tt + '\n';
				} else {
					token3 = st.nextToken();
					token4 = st.nextToken();
					token5 = st.nextToken();
					record = token1 + " " + token2 + " " + token4 + " " + token5 + '\n';
					//record = token1 + " " + token2 + " " + token4 + " " + tt + '\n';	
				}
				this.osw.write(record);
			}
			
			this.buf.close();
			this.rd.close();
			this.osw.close();
			this.fos.close();
			
		} catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}
	public String getfileName(){
		return DRAMAdjacencyMatrix.tempFile;
	}
}
