package padl.serialiser;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import padl.kernel.IAbstractModel;
import padl.kernel.IAbstractModelSerialiser;
import padl.serialiser.util.MethodInvocationDeserialiserHelper;
import padl.serialiser.util.MethodInvocationSerialiserHelper;
import util.io.ProxyConsole;
import util.io.ProxyDisk;

public class JOSSerialiser implements IAbstractModelSerialiser {
	private static final String DATE_FORMAT = "yyyyMMdd'H'HHmmss";
	private static IAbstractModelSerialiser UniqueInstance;
	public static IAbstractModelSerialiser getInstance() {
		if (JOSSerialiser.UniqueInstance == null) {
			JOSSerialiser.UniqueInstance = new JOSSerialiser();
		}
		return JOSSerialiser.UniqueInstance;
	}
	private JOSSerialiser() {
	}
	public IAbstractModel deserialise(final String aSerialisedPADLModelFileName) {
		System.out.println("Deserialising model...");
		final long beginning = System.currentTimeMillis();

		IAbstractModel abstractModel = null;
		ObjectInputStream ois = null;
		try {
			ois =
				new ObjectInputStream(new FileInputStream(
					aSerialisedPADLModelFileName));
			abstractModel = (IAbstractModel) ois.readObject();
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (ois != null) {
				try {
					ois.close();
				}
				catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Yann 2009/03/20: Serialisation!
		// I must "cut" the serialisation in various places to prevent stack overflow,
		// for example because IMethodInvocation objects reference entities that may
		// not have been serialised yet... So, the MethodInvocation class must be saved
		// aside and the pieces put back together after...
		abstractModel.walk(new MethodInvocationDeserialiserHelper(
			aSerialisedPADLModelFileName));

		final long end = System.currentTimeMillis();
		System.out.print("Model deserialised in ");
		System.out.print(end - beginning);
		System.out.println(" ms.");

		return abstractModel;
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
	private String getODBName(
		final IAbstractModel anAbstractModel,
		final String aTargetPath) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(aTargetPath);
		buffer.append(anAbstractModel.getName());
		buffer.append('-');

		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat sdf =
			new SimpleDateFormat(JOSSerialiser.DATE_FORMAT);
		buffer.append(sdf.format(calendar.getTime()));

		return buffer.toString();
	}
	public void serialise(
		final IAbstractModel anAbstractModel,
		final String aFileName) {

		ProxyConsole
			.getInstance()
			.debugOutput()
			.println("Serialising model...");
		final long beginning = System.currentTimeMillis();

		try {
			final ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream(aFileName));
			try {
				oos.writeObject(anAbstractModel);
			}
			catch (final IOException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			finally {
				oos.close();
			}

			// Yann 2009/03/20: Serialisation!
			// I must "cut" the serialisation in various places to prevent stack overflow,
			// for example because IMethodInvocation objects reference entities that may
			// not have been serialised yet... So, the MethodInvocation class must be saved
			// aside and the pieces put back together after...
			anAbstractModel
				.walk(new MethodInvocationSerialiserHelper(aFileName));
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final long end = System.currentTimeMillis();
		ProxyConsole.getInstance().debugOutput().print("Model serialised in ");
		ProxyConsole.getInstance().debugOutput().print(end - beginning);
		ProxyConsole.getInstance().debugOutput().println(" ms.");
	}
	public String serialiseWithAutomaticNaming(
		final IAbstractModel anAbstractModel,
		final String aTargetPath) {

		final String fileName = this.getODBName(anAbstractModel, aTargetPath);
		this.serialise(anAbstractModel, fileName);
		return fileName;
	}
}
