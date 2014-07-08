/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package padl.creator.cppfile.eclipse.misc;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.eclipse.osgi.baseadaptor.BaseAdaptor;
import org.eclipse.osgi.framework.adaptor.BundleData;
import org.eclipse.osgi.framework.adaptor.BundleOperation;
import org.eclipse.osgi.framework.adaptor.BundleWatcher;
import org.eclipse.osgi.framework.adaptor.EventPublisher;
import org.eclipse.osgi.framework.adaptor.FrameworkAdaptor;
import org.eclipse.osgi.framework.adaptor.PermissionStorage;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.eclipse.osgi.service.resolver.PlatformAdmin;
import org.eclipse.osgi.service.resolver.State;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import util.io.ProxyConsole;
import util.io.UnclosablePrintWriter;

public class EclipseCPPParserAdaptor implements FrameworkAdaptor {
	private static boolean HasTheLogBeenAlreadyUsed;

	private final BaseAdaptor defaultAdaptor;

	public EclipseCPPParserAdaptor(final String[] args) {
		this.defaultAdaptor = new BaseAdaptor(args);
	}
	@Override
	public void initialize(final EventPublisher eventPublisher) {
		this.defaultAdaptor.initialize(eventPublisher);
	}
	@Override
	public void initializeStorage() throws IOException {
		this.defaultAdaptor.initializeStorage();
	}
	@Override
	public void compactStorage() throws IOException {
		this.defaultAdaptor.compactStorage();
	}
	@Override
	public Properties getProperties() {
		return this.defaultAdaptor.getProperties();
	}
	@Override
	public BundleData[] getInstalledBundles() {
		return this.defaultAdaptor.getInstalledBundles();
	}
	@Override
	public URLConnection mapLocationToURLConnection(final String location)
			throws BundleException {

		return this.defaultAdaptor.mapLocationToURLConnection(location);
	}
	@Override
	public BundleOperation installBundle(
		final String location,
		final URLConnection source) {

		return this.defaultAdaptor.installBundle(location, source);
	}
	@Override
	public BundleOperation updateBundle(
		final BundleData bundledata,
		final URLConnection source) {

		return this.defaultAdaptor.updateBundle(bundledata, source);
	}
	@Override
	public BundleOperation uninstallBundle(final BundleData bundledata) {
		return this.defaultAdaptor.uninstallBundle(bundledata);
	}
	@Override
	public long getTotalFreeSpace() throws IOException {
		return this.defaultAdaptor.getTotalFreeSpace();
	}
	@Override
	public PermissionStorage getPermissionStorage() throws IOException {
		return this.defaultAdaptor.getPermissionStorage();
	}
	@Override
	public void frameworkStart(final BundleContext context)
			throws BundleException {

		this.defaultAdaptor.frameworkStart(context);
	}
	@Override
	public void frameworkStop(final BundleContext context)
			throws BundleException {

		this.defaultAdaptor.frameworkStop(context);
	}
	@Override
	public void frameworkStopping(final BundleContext context) {
		this.defaultAdaptor.frameworkStopping(context);
	}
	@Override
	public int getInitialBundleStartLevel() {
		return this.defaultAdaptor.getInitialBundleStartLevel();
	}
	@Override
	public void setInitialBundleStartLevel(final int value) {
		this.defaultAdaptor.setInitialBundleStartLevel(value);
	}
	@Override
	public FrameworkLog getFrameworkLog() {
		if (!EclipseCPPParserAdaptor.HasTheLogBeenAlreadyUsed) {
			EclipseCPPParserAdaptor.HasTheLogBeenAlreadyUsed = true;
			this.defaultAdaptor.getFrameworkLog().setWriter(
				new UnclosablePrintWriter(ProxyConsole
					.getInstance()
					.errorOutput()),
				true);
		}
		return this.defaultAdaptor.getFrameworkLog();
	}
	@Override
	public BundleData createSystemBundleData() throws BundleException {
		return this.defaultAdaptor.createSystemBundleData();
	}
	@Override
	public BundleWatcher getBundleWatcher() {
		return this.defaultAdaptor.getBundleWatcher();
	}
	@Override
	public PlatformAdmin getPlatformAdmin() {
		return this.defaultAdaptor.getPlatformAdmin();
	}
	@Override
	public State getState() {
		return this.defaultAdaptor.getState();
	}
	@Override
	public ClassLoader getBundleClassLoaderParent() {
		return this.defaultAdaptor.getBundleClassLoaderParent();
	}
	@Override
	public void handleRuntimeError(final Throwable error) {
		this.defaultAdaptor.handleRuntimeError(error);
	}
	@Override
	public Enumeration<URL> findEntries(
		final List<BundleData> datas,
		final String path,
		final String filePattern,
		final int options) {

		return this.defaultAdaptor.findEntries(
			datas,
			path,
			filePattern,
			options);
	}
}
