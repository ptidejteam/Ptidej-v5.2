/* (c) Copyright 2014 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.cppfile.eclipse.helper;

import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import padl.util.ModelStatistics;

public class Chrome {
	public static void main(final String[] args) {
		final ModelStatistics statistics = new ModelStatistics();
		final IIdiomLevelModel idiomLevelModel =
			ModelGenerator
				.generateModelFromCppFilesUsingEclipse(
					"Various chromium/src/chrome/browser/ subfolders",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/net/",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/history/",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/sync/",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/chromeos/",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/extensions/",
					"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/ui/",
					//	"C:/Temp/Chromium/chromium.r197479/home/src_tarball/tarball/chromium/src/chrome/browser/",
					statistics);
		System.out.println(idiomLevelModel);
		System.out.println(statistics);
	}
}
