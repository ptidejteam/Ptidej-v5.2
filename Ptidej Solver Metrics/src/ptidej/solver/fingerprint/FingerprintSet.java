/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
package ptidej.solver.fingerprint;

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.MetricsRepository;

/**
 * @author Jean-Yves Guillomarc'h
 */
public class FingerprintSet {
	private final Fingerprint[] rules;

	public FingerprintSet(final Fingerprint[] rules) {
		this.rules = rules;
	}
	public boolean compute(
		final MetricsRepository metrics,
		final IAbstractLevelModel anAbstractLevelModel,
		final IFirstClassEntity firstClassEntity) {

		boolean accept = true;
		for (int i = 0; i < this.rules.length && accept; i++) {
			accept =
				this.rules[i].compute(
					metrics,
					anAbstractLevelModel,
					firstClassEntity);
		}
		return accept;
	}
	public Fingerprint[] getRules() {
		return this.rules;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < this.rules.length; i++) {
			buffer.append('(');
			buffer.append(this.rules[i]);
			buffer.append(')');
		}

		return buffer.toString();
	}
}
