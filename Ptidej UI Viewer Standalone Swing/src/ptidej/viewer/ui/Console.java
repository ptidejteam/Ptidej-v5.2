/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
package ptidej.viewer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/03/23
 */
public class Console extends JTextPane {
	private static final SimpleAttributeSet Attributes =
		new SimpleAttributeSet();
	private static final long serialVersionUID = 4044627552311493225L;
	private static Console UniqueInstance;
	private static void addTextToConsole(
		final Console aConsole,
		final String someText) {

		final Document document = aConsole.getDocument();
		try {
			document.insertString(
				document.getLength(),
				someText,
				Console.Attributes);
			aConsole.setCaretPosition(document.getLength());
		}
		catch (final BadLocationException e) {
			e.printStackTrace();
		}
	}
	public static Console getInstance() {
		if (Console.UniqueInstance == null) {
			StyleConstants.setFontSize(Console.Attributes, 10);
			Console.UniqueInstance = new Console();
		}
		return Console.UniqueInstance;
	}

	private final Writer errorWriter = new Writer() {
		public void close() throws IOException {
		}
		public void flush() throws IOException {
		}
		public void write(final char[] cbuf, final int off, final int len)
				throws IOException {

			if (!Console.this.isWritingError) {
				StyleConstants.setForeground(Console.Attributes, Color.RED);
				Console.this.isWritingError = true;
			}

			final String newOutput = String.valueOf(cbuf, off, len);
			if (Console.this.log != null) {
				Console.this.log.write(newOutput);
				Console.this.log.flush();
			}

			Console.addTextToConsole(Console.this, newOutput);
		}
	};
	private boolean isWritingError;
	private Writer log;
	private final Writer normalWriter = new Writer() {
		public void close() throws IOException {
		}
		public void flush() throws IOException {
		}
		public void write(final char[] cbuf, final int off, final int len)
				throws IOException {

			if (Console.this.isWritingError) {
				StyleConstants.setForeground(Console.Attributes, Color.BLACK);
				Console.this.isWritingError = false;
			}

			final String newOutput = String.valueOf(cbuf, off, len);
			if (Console.this.log != null) {
				Console.this.log.write(newOutput);
				Console.this.log.flush();
			}

			Console.addTextToConsole(Console.this, newOutput);
		}
	};
	private Console() {
		this.isWritingError = false;

		this.setEditable(false);
		this.setBackground(Color.WHITE);
		this.setSelectionColor(super.getBackground());
		this.setSelectedTextColor(this.getBackground().darker());

		try {
			this.log = new FileWriter("Ptidej.log", true);
		}
		catch (final IOException e) {
			this.log = null;
		}

		if (this.log != null) {
			try {
				this.log.write("\n\n---------------------------------\n");
				this.log.write("Ptidej run on ");
				final Calendar calendar = Calendar.getInstance();
				final SimpleDateFormat simpleDataFormat =
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				this.log.write(simpleDataFormat.format(calendar.getTime()));
				this.log.write("\n---------------------------------\n\n");
				this.log.flush();
			}
			catch (final IOException e) {
			}
		}
	}
	public Writer getErrorWriter() {
		return this.errorWriter;
	}
	public Writer getNormalWriter() {
		return this.normalWriter;
	}
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(this.getWidth(), 70);
	}
}
