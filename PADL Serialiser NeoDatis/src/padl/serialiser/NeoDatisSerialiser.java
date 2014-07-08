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
package padl.serialiser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import padl.kernel.IAbstractModel;
import padl.kernel.IAbstractModelSerialiser;
import padl.serialiser.util.AbstractModelWrapper;
import util.io.ProxyConsole;
import util.io.ProxyDisk;

/**
 * 
 * @author Yann
 * @since  2009/02/23
 */

public class NeoDatisSerialiser implements IAbstractModelSerialiser {
	private static final String DATE_FORMAT_NOW = "yyyyMMdd'H'HHmmss";
	private static IAbstractModelSerialiser UniqueInstance;
	public static IAbstractModelSerialiser getInstance() {
		if (NeoDatisSerialiser.UniqueInstance == null) {
			NeoDatisSerialiser.UniqueInstance = new NeoDatisSerialiser();
		}
		return NeoDatisSerialiser.UniqueInstance;
	}
	private NeoDatisSerialiser() {
	}
	public IAbstractModel deserialise(final String aSerialisedPADLModelFileName) {
		ODB odb = null;
		IAbstractModel abstractModel = null;
		try {
			odb = ODBFactory.open(aSerialisedPADLModelFileName);
			//	abstractModel =
			//		((AbstractModelWrapper) odb.getObjects(
			//			AbstractModelWrapper.class).getFirst()).getAbstractModel();
			final IQuery query = new CriteriaQuery(AbstractModelWrapper.class);
			abstractModel =
				((AbstractModelWrapper) odb.getObjects(query))
					.getAbstractModel();
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
		return abstractModel;
	}
	private String getODBName(
		final IAbstractModel anAbstractModel,
		final String aTargetPath) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(aTargetPath);
		buffer.append(anAbstractModel.getName());
		buffer.append('-');

		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat sdf =
			new SimpleDateFormat(NeoDatisSerialiser.DATE_FORMAT_NOW);
		buffer.append(sdf.format(calendar.getTime()));

		return buffer.toString();
	}
	public String serialiseWithAutomaticNaming(
		final IAbstractModel anAbstractModel) {

		final String fileName =
			this.getODBName(anAbstractModel, ProxyDisk
				.getInstance()
				.directoryTempString());
		this.serialise(anAbstractModel, fileName);
		return fileName;
	}
	public void serialise(
		final IAbstractModel anAbstractModel,
		final String aFileName) {

		ODB odb = null;
		try {
			odb = ODBFactory.open(aFileName);
			odb.store(new AbstractModelWrapper(anAbstractModel));
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		finally {
			if (odb != null) {
				odb.close();
			}
		}
	}
	public String serialiseWithAutomaticNaming(
		final IAbstractModel anAbstractModel,
		final String aTargetPath) {

		final String fileName = this.getODBName(anAbstractModel, aTargetPath);
		this.serialise(anAbstractModel, fileName);
		return fileName;
	}
}
