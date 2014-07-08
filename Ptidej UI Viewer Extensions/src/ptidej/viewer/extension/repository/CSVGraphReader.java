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
package ptidej.viewer.extension.repository;

import infovis.Column;
import infovis.Graph;
import infovis.Table;
import infovis.column.FloatColumn;
import infovis.column.StringColumn;
import infovis.graph.DefaultGraph;
import infovis.graph.io.GraphReaderFactory;
import infovis.io.AbstractReader;
import infovis.io.AbstractTableReader;
import infovis.io.WrongFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

import util.multilingual.MultilingualManager;

/**
 * This class reads a graph written in the CSV format.
 * 
 * @author	Mohammad Ghoniem
 * @author	Yann-Gaël Guéhéneuc
 */
public final class CSVGraphReader extends AbstractTableReader {
	public static void main(final String[] args) {
		final Graph g = new DefaultGraph();
		final AbstractReader reader =
			GraphReaderFactory.createReader(args[0], g);
		reader.load();
		for (int j = 0; j < g.getRowCount(); j++) {
			for (int i = 0; i < g.getColumnCount(); i++) {
				final Column c = g.getColumnAt(i);
				System.out.print(c.getValueAt(j) + "\t");
			}
			System.out.print("\n");
		}
	}
	// private StringColumn nodeIdColumn;
	private StringColumn edgeIdColumn;
	private Graph graph;
	private Map nodeMap;
	// private StringColumn nodeNamesColumn;
	private FloatColumn weightColumn;

	/**
	 * Constructor for CSVGraphReader.
	 * @param in
	 * @param name
	 * @param table
	 */
	public CSVGraphReader(
		final BufferedReader in,
		final String name,
		final Table table) {

		super(in, name, table);
		this.graph = (Graph) table;
	}

	private void init() {
		this.nodeMap = new HashMap();
		// this.nodeIdColumn = StringColumn.findColumn(graph, "Name");
		this.edgeIdColumn =
			StringColumn.findColumn(this.graph.getEdgeTable(), "Name");
		//	this.nodeNamesColumn = StringColumn.findColumn(graph, "nodeNames");
		this.weightColumn =
			FloatColumn.findColumn(this.graph.getEdgeTable(), "Weight");
	}

	/**
	 * @see infovis.io.AbstractReader#load()
	 */
	public boolean load() throws WrongFormatException {
		this.init();

		final StreamTokenizer st = new StreamTokenizer(this.in);
		st.eolIsSignificant(true);
		st.wordChars('$', '$');
		st.wordChars('_', '_');

		// Characters that may appear in URLs.
		st.wordChars('?', '?');
		st.wordChars('=', '=');
		st.wordChars('#', '#');
		st.wordChars(':', ':');
		st.wordChars('/', '/');
		st.wordChars('&', '&');
		st.wordChars('+', '+');
		try {
			while (this.readLine(st));
		}
		catch (final Exception e) {
			System.err.println(
				MultilingualManager.getString(
					"Err_SYNTAX_ERR",
					CSVGraphReader.class,
					new Object[] { new Integer(st.lineno())}));
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Creates the vertices stated in the line if necessary and
	 * links them.
	 */
	private boolean readLine(final StreamTokenizer st) throws Exception {
		int ttype = st.nextToken();
		// Special cases for the end of line and of stream.
		if (ttype == StreamTokenizer.TT_EOL) {
			return true;
		}
		if (ttype == StreamTokenizer.TT_EOF) {
			return false;
		}
		if (ttype != StreamTokenizer.TT_WORD) {
			throw new IOException(
				MultilingualManager.getString(
					"Err_UNEXPECTED_TOKEN",
					CSVGraphReader.class,
					new Object[] { st.toString()}));
		}
		final String name1 = st.sval.substring(st.sval.lastIndexOf('.') + 1);
		// If the first vertex is not yet in the graph
		// add it to the graph and to the node map
		// then add its name to the column of names.
		Integer id1 = (Integer) this.nodeMap.get(name1);
		if (id1 == null) {
			id1 = new Integer(this.graph.addVertex());
			this.nodeMap.put(name1, id1);
			// this.nodeIdColumn.setExtend(id1.intValue(), name1);
			// this.nodeNamesColumn.setExtend(id1.intValue(), name1);
		}

		ttype = st.nextToken();
		if (ttype != StreamTokenizer.TT_WORD) {
			throw new IOException(
				MultilingualManager.getString(
					"Err_UNEXPECTED_TOKEN",
					CSVGraphReader.class,
					new Object[] { st.toString()}));
		}
		final String name2 = st.sval.substring(st.sval.lastIndexOf('.') + 1);
		// If the first vertex is not yet in the graph
		// add it to the graph and to the node map
		// then add its name to the column of names.
		Integer id2 = (Integer) this.nodeMap.get(name2);
		if (id2 == null) {
			id2 = new Integer(this.graph.addVertex());
			this.nodeMap.put(name2, id2);
			// this.nodeIdColumn.setExtend(id2.intValue(), name2);
			// this.nodeNamesColumn.setExtend(id2.intValue(), name2);
		}

		// The edge and its weight are added too.
		ttype = st.nextToken();
		if (ttype != StreamTokenizer.TT_NUMBER) {
			throw new IOException(
				MultilingualManager.getString(
					"Err_UNEXPECTED_TOKEN",
					CSVGraphReader.class,
					new Object[] { st.toString()}));
		}
		final float w = (float) st.nval;
		final int edgeId = this.graph.addEdge(id1.intValue(), id2.intValue());
		this.edgeIdColumn.setExtend(edgeId, name1 + '-' + name2);
		this.weightColumn.setExtend(edgeId, w);

		return true;
	}
}
