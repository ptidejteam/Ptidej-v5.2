/**
 * @author Mathieu Lemoine
 * @created 2009-05-07 (木)
 * 
 * Licensed under 4-clause BSD License:
 * Copyright © 2009, Mathieu Lemoine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    This product includes software developed by Mathieu Lemoine and contributors.
 *  * Neither the name of Mathieu Lemoine nor the
 *    names of contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Mathieu Lemoine ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Mathieu Lemoine BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jct.nonjunittest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import jct.examples.JCTNodeCounter;
import jct.kernel.IJCTRootNode;
import jct.test.rsc.Constant;
import jct.test.rsc.snpsht.SnpShtConstant;
import jct.util.misc.Counter;

public final class CompareJavaCAndJCTNodeCount {
	/**
	 * {First Result :}
	 * [Thu May 07 13:42:47 EDT 2009] Start -- with Statement
	 * [Thu May 07 13:45:03 EDT 2009] JavaC Visited Node Count : 46892
	 * [Thu May 07 13:45:03 EDT 2009] JCT Node Count : 46301
	 * {Duration => 2:16}
	 * [Thu May 07 13:45:03 EDT 2009] Start -- without Statement
	 * [Thu May 07 13:45:09 EDT 2009] JavaC Visited Node Count : 18922
	 * [Thu May 07 13:45:09 EDT 2009] JCT Node Count : 18689
	 * {Duration => 0:06}
	 */
	public static void main(String args[]) throws IOException {
		final File files[] = new File[SnpShtConstant.files.length];
		for (int i = 0; i < SnpShtConstant.files.length; ++i)
			files[i] = new File(Constant.SRC_PATH + SnpShtConstant.files[i]);

		System.out.println("[" + new Date() + "] Start -- complete");
		Counter c = new Counter();
		IJCTRootNode jct =
			JCTDistordedCreatorFromSourceCode
				.createJCT(
					"JCTPrettyPrinterTest",
					false,
					EnumSet
						.allOf(JCTDistordedCreatorFromSourceCode.VisitingLevel.class),
					null,
					Constant.OPTIONS,
					c,
					files);
		System.out.println("[" + new Date() + "] JavaC Visited Node Count : "
				+ c.get());
		Integer jctNodeCount = jct.accept(new JCTNodeCounter());
		System.out.println("[" + new Date() + "] JCT Node Count : "
				+ jctNodeCount);

		System.out.println("[" + new Date() + "] Start -- sparse");
		c.reset();
		jct =
			JCTDistordedCreatorFromSourceCode
				.createJCT(
					"JCTPrettyPrinterTest",
					false,
					EnumSet
						.of(JCTDistordedCreatorFromSourceCode.VisitingLevel.STATEMENTS),
					null,
					Constant.OPTIONS,
					c,
					files);
		System.out.println("[" + new Date() + "] JavaC Visited Node Count : "
				+ c.get());
		jctNodeCount = jct.accept(new JCTNodeCounter());
		System.out.println("[" + new Date() + "] JCT Node Count : "
				+ jctNodeCount);
	}
}