/*
 * (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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

package padl.micropatterns.examples;

public class DataManager {
	public int pubAtt1;
	public long pubAtt2;
	
	DataManager () {
		this.pubAtt1 = 0;
		this.pubAtt2 = 0;
	}

	public long getpubAtt2() {
		return this.pubAtt2;
	}

	public void setpubAtt2(long pubAtt2) {
		this.pubAtt2 = pubAtt2;
	}

	public int getPubAtt1() {
		return this.pubAtt1;
	}

	public void setPubAtt1(int pubAtt1) {
		this.pubAtt1 = pubAtt1;
	}
}
