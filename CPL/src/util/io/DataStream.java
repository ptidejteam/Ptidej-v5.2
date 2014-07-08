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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author	Yann-Gaël Guéhéneuc
 * @since	2006/07/29
 */
public class DataStream implements DataOutput, DataInput {
	public boolean readBoolean() throws IOException {
		return false;
	}
	public byte readByte() throws IOException {
		return 0;
	}
	public char readChar() throws IOException {
		return 0;
	}
	public double readDouble() throws IOException {
		return 0;
	}
	public float readFloat() throws IOException {
		return 0;
	}
	public void readFully(final byte[] b) throws IOException {
	}
	public void readFully(final byte[] b, final int off, final int len)
			throws IOException {
	}
	public int readInt() throws IOException {
		return 0;
	}
	public String readLine() throws IOException {
		return null;
	}
	public long readLong() throws IOException {
		return 0;
	}
	public short readShort() throws IOException {
		return 0;
	}
	public int readUnsignedByte() throws IOException {
		return 0;
	}
	public int readUnsignedShort() throws IOException {
		return 0;
	}
	public String readUTF() throws IOException {
		return null;
	}
	public int skipBytes(final int n) throws IOException {
		return 0;
	}
	public void write(final byte[] b) throws IOException {
	}
	public void write(final byte[] b, final int off, final int len)
			throws IOException {
	}
	public void write(final int b) throws IOException {
	}
	public void writeBoolean(final boolean v) throws IOException {
	}
	public void writeByte(final int v) throws IOException {
	}
	public void writeBytes(final String s) throws IOException {
	}
	public void writeChar(final int v) throws IOException {
	}
	public void writeChars(final String s) throws IOException {
	}
	public void writeDouble(final double v) throws IOException {
	}
	public void writeFloat(final float v) throws IOException {
	}
	public void writeInt(final int v) throws IOException {
	}
	public void writeLong(final long v) throws IOException {
	}
	public void writeShort(final int v) throws IOException {
	}
	public void writeUTF(final String str) throws IOException {
	}
}
