/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
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
package padl.creator.cppfile.eclipse.misc;

import org.eclipse.core.runtime.adaptor.EclipseStarter;

@SuppressWarnings("restriction")
public class SimpleStarter {
	public static void main(final String[] args) throws Exception {
		//	FrameworkProperties.setProperty(
		//		Constants.OSGI_COMPATIBILITY_BOOTDELEGATION,
		//		"false");
		EclipseStarter.run(args, null);
		EclipseStarter.shutdown();
		//	final Thread thread = new Thread(new Runnable() {
		//		@Override
		//		public void run() {
		//			try {
		//				final Object o = EclipseStarter.run(args, null);
		//				EclipseStarter.shutdown();
		//				SimpleStarter.displayMessage(o.toString());
		//			}
		//			catch (final Exception e) {
		//				SimpleStarter.displayMessage(e.toString());
		//			}
		//		}
		//	}, "EclipseStarter");
		//	thread.start();
		//
		//	SimpleStarter.displayMessage(ProxyConsole.class
		//		.getClassLoader()
		//		.toString()
		//			+ "\n"
		//			+ Arrays.toString(args)
		//			+ "\n"
		//			+ System.getProperty("java.class.path"));
		//}
		//private static void displayMessage(final String aMessage) {
		//	final JFrame frame = new JFrame("SimpleMain");
		//	frame.addWindowListener(new WindowListener() {
		//		@Override
		//		public void windowOpened(final WindowEvent e) {
		//		}
		//		@Override
		//		public void windowIconified(final WindowEvent e) {
		//		}
		//		@Override
		//		public void windowDeiconified(final WindowEvent e) {
		//		}
		//		@Override
		//		public void windowDeactivated(final WindowEvent e) {
		//		}
		//		@Override
		//		public void windowClosing(final WindowEvent e) {
		//		}
		//		@Override
		//		public void windowClosed(final WindowEvent e) {
		//			frame.setVisible(false);
		//			frame.dispose();
		//		}
		//		@Override
		//		public void windowActivated(final WindowEvent e) {
		//		}
		//	});
		//	frame.getContentPane().add(new JScrollPane(new JTextArea(aMessage)));
		//	final Dimension screenSize =
		//		Toolkit.getDefaultToolkit().getScreenSize();
		//	frame.setLocation(
		//		(screenSize.width - 200) / 2,
		//		(screenSize.height - 100) / 2);
		//	frame.setSize(200, 100);
		//	frame.setVisible(true);
	}
}
