/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
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
