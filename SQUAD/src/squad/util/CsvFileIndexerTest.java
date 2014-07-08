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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CsvFileIndexerTest {

	/**
	 * @author Foutse Khomh
	 * @since 2008/12/09
	 * 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		/*final Directory index = FSDirectory.getDirectory("rsc/Bugs/raw_bugs/index.dat");
		ResultsFileIndexer csvindex = new ResultsFileIndexer("rsc/Bugs/raw_bugs/index.dat");
		*///csvindex.indexFileOrDirectory("D:/Documents/Workspace/ToDo/linkssuperset.csv");

		//final Directory indexbug = FSDirectory.getDirectory("rsc/Bugs/raw_bugs/indexbug.dat");
		//ResultsFileIndexer csvindex1 = new ResultsFileIndexer("rsc/Bugs/raw_bugs/indexbug.dat");
		//csvindex.indexFileOrDirectory("D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Bugs/JDT.bug.mapping");

		//csvindex.indexFileOrDirectory("D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Bugs/Eclipse.bug.mapping");
		//csvindex1.indexFileOrDirectory("D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Bugs/raw_bugs/bug-classification_max.csv");

		final Directory index =
			FSDirectory
				.getDirectory("D:/Documents/Workspace/changes/index1.dat");
		ResultsFileIndexer csvindex =
			new ResultsFileIndexer("D:/Documents/Workspace/changes/index1.dat");

		csvindex
			.indexFileOrDirectory("D:/Documents/Workspace/changes/JHD.potentialbug.mapping");

		final Analyzer analyser = new StandardAnalyzer();
		Query q = new QueryParser("filename", analyser).parse("equinox");
		IndexSearcher s = new IndexSearcher(index);
		Hits hits = s.search(q);
		System.out.println("Found " + hits.length() + " hits.");
		for (int i = 0; i < hits.length(); ++i) {
			System.out.println((i + 1) + ". " + hits.doc(i).get("filename"));
		}
		s.close();
	}

}
