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

import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2009/05/22
 */
public class WarningPrintWriter extends PrintWriter {
	private static final String PREFIX = "## ";
	private boolean withinSomeCallsToPrintWithouLN;
	private boolean withinSomeCallsToPrintLN;
	public WarningPrintWriter(final Writer writer) {
		super(writer, true);
		this.withinSomeCallsToPrintWithouLN = false;
		this.withinSomeCallsToPrintLN = false;
	}
	public void print(final char aChar) {
		if (!this.withinSomeCallsToPrintWithouLN
				&& !this.withinSomeCallsToPrintLN) {

			super.print(WarningPrintWriter.PREFIX);
			this.withinSomeCallsToPrintWithouLN = true;
		}
		super.print(aChar);
	}
	public void print(final String aString) {
		if (!this.withinSomeCallsToPrintWithouLN
				&& !this.withinSomeCallsToPrintLN) {

			super.print(WarningPrintWriter.PREFIX);
			this.withinSomeCallsToPrintWithouLN = true;
		}
		super.print(aString);
	}
	public void println(final char aChar) {
		if (!this.withinSomeCallsToPrintWithouLN) {
			super.print(WarningPrintWriter.PREFIX);
		}
		this.withinSomeCallsToPrintWithouLN = false;
		this.withinSomeCallsToPrintLN = true;
		super.println(aChar);
		this.withinSomeCallsToPrintLN = false;
	}
	public void println(final String aString) {
		if (!this.withinSomeCallsToPrintWithouLN) {
			super.print(WarningPrintWriter.PREFIX);
		}
		this.withinSomeCallsToPrintWithouLN = false;
		this.withinSomeCallsToPrintLN = true;
		super.println(aString);
		this.withinSomeCallsToPrintLN = false;
	}
}
