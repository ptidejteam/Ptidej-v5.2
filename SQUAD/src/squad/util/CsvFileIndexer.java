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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

public class CsvFileIndexer {

	/**
	 * @author Foutse Khomh
	 * @since 2008/12/09
	 * 
	 */

	private IndexWriter writer;
	private ArrayList<?> queue = new ArrayList<Object>();

	/**
	 * Constructor
	 * 
	 * @param indexDir
	 *            the name of the folder in which the index should be created
	 */
	public CsvFileIndexer(String indexDir) throws CorruptIndexException,
			LockObtainFailedException, IOException {
		// the boolean true parameter means to create a new index everytime,
		// potentially overwriting any existing files there.
		this.writer = new IndexWriter(indexDir, new StandardAnalyzer(), true);
	}

	//def writer = new IndexWriter(new File("index"), new StandardAnalyzer(), true)

	/**
	 * Indexes a csv file * 
	 * @param fileName
	 *            the name of a csv file we wish to add to the
	 *            index
	 * the structure of the csv  file should contain the columns number, version1, version2, filename,time,author,type, id
	 * 
	 */
	public void indexFileOrDirectory(String fileName)
			throws FileNotFoundException, CorruptIndexException, IOException {

		LineNumberReader fr = null;
		try {

			// ===================================================
			// add contents of file
			// ===================================================
			fr = new LineNumberReader(new FileReader(fileName));

			String line;
			while ((line = fr.readLine()) != null) {

				Document doc = new Document();

				String[] ucharFields = line.split(",", 8);

				doc.add(new Field(
					"number",
					ucharFields[0],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"version1",
					ucharFields[1],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"version2",
					ucharFields[2],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"filename",
					ucharFields[3],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"time",
					ucharFields[4],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"author",
					ucharFields[5],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));
				doc.add(new Field(
					"type",
					ucharFields[6],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));

				doc.add(new Field(
					"id",
					ucharFields[7],
					Field.Store.COMPRESS,
					Field.Index.TOKENIZED));

				this.writer.addDocument(doc);
			}
			System.out.println("Added: " + fileName);
			this.writer.optimize();
			this.writer.close();
		}
		catch (Exception e) {
			System.out.println("Could not add: " + fileName);
		}
		finally {
			fr.close();
		}

	}
}