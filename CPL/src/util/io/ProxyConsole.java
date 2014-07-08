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
package util.io;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/07/15
 */
public class ProxyConsole {
	private static ProxyConsole UniqueInstance;
	public static ProxyConsole getInstance() {
		if (ProxyConsole.UniqueInstance == null) {
			ProxyConsole.UniqueInstance = new ProxyConsole();
		}

		return ProxyConsole.UniqueInstance;
	}

	private PrintWriter debugOutput;
	private PrintWriter errorOutput;
	private PrintWriter normalOutput;
	private PrintWriter warningOutput;

	private ProxyConsole() {
		this.setDebugOutput(new NullWriter());
		this.setErrorOutput(new AutoFlushPrintWriter(new OutputStreamWriter(
			System.err)));
		this.setNormalOutput(new AutoFlushPrintWriter(new OutputStreamWriter(
			System.out)));
	}
	public PrintWriter debugOutput() {
		return this.debugOutput;
	}
	public PrintWriter errorOutput() {
		return this.errorOutput;
	}
	public PrintWriter normalOutput() {
		return this.normalOutput;
	}
	public void printSetContent(final Writer writer, final Set<?> aSet) {
		final Iterator<?> iterator = aSet.iterator();
		while (iterator.hasNext()) {
			final Object object = iterator.next();
			try {
				writer.write(object.toString());
				writer.write('\n');
				writer.flush();
			}
			catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void setDebugOutput(final PrintWriter messageWriter) {
		this.debugOutput = messageWriter;
	}
	public void setDebugOutput(final Writer messageWriter) {
		this.setDebugOutput(new UnclosablePrintWriter(messageWriter));
	}
	private void setErrorOutput(final PrintWriter messageWriter) {
		this.errorOutput = messageWriter;
	}
	public void setErrorOutput(final Writer messageWriter) {
		// Yann 2014/06/22: Eclipse...
		// For some unknown reason, Eclipse decided to close the writer
		// given to it at startup, through its Adaptor (see for example
		// padl.creator.cppfile.eclipse.misc.EclipseCPPParserAdaptor).
		// It closes the writer at
		//	EclipseLogWriter.setOutput(File, Writer, boolean) line: 331
		// So, I make sure that it cannot close MY writers using my own
		// UnclosablePrintWriter.
		this.setErrorOutput(new UnclosablePrintWriter(messageWriter));
	}
	private void setNormalOutput(final PrintWriter messageWriter) {
		this.normalOutput = messageWriter;
		// this.warningOutput = new WarningPrintWriter(this.normalOutput);
		this.warningOutput = this.normalOutput;
	}
	public void setNormalOutput(final Writer messageWriter) {
		// Yann 2014/06/22: Eclipse...
		// For some unknown reason, Eclipse decided to close the writer
		// given to it at startup, through its Adaptor (see for example
		// padl.creator.cppfile.eclipse.misc.EclipseCPPParserAdaptor).
		// It closes the writer at
		//	EclipseLogWriter.setOutput(File, Writer, boolean) line: 331
		// So, I make sure that it cannot close MY writers using my own
		// UnclosablePrintWriter.
		this.setNormalOutput(new UnclosablePrintWriter(messageWriter));
	}
	public PrintWriter warningOutput() {
		return this.warningOutput;
	}
}
