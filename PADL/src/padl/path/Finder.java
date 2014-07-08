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
package padl.path;

import java.util.Arrays;
import java.util.StringTokenizer;
import org.apache.commons.lang.ArrayUtils;
import padl.kernel.IAbstractModel;
import padl.kernel.IConstituent;
import padl.kernel.IContainer;

public class Finder {
	private static final StringBuffer Header = new StringBuffer();

	public static IConstituent find(
		final String aPath,
		final IAbstractModel anAbstractModel) throws FormatException {

		return (IConstituent) Finder.find0(aPath, anAbstractModel);
	}
	private static Object find0(
		final String aPath,
		final IAbstractModel anAbstractModel) throws FormatException {

		final StringTokenizer tokenizer =
			new StringTokenizer(aPath, IConstants.ALL_SYMBOLS, true);
		Finder.Header.setLength(0);

		if (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			Finder.Header.append(token);

			if (token.charAt(0) == IConstants.ABSTRACT_MODEL_SYMBOL) {
				token = tokenizer.nextToken();
				Finder.Header.append(token);

				// Yann 2009/06/05: Name!
				// We do not need to check the name of the 
				// model because it does not mean anything. 
				//	if (token.equals(anAbstractModel.getDisplayName())) {
				if (tokenizer.hasMoreTokens()) {
					token = tokenizer.nextToken();
					Finder.Header.append(token);
					token = tokenizer.nextToken();
					Finder.Header.append(token);

					final IConstituent constituent =
						anAbstractModel.getConstituentFromID(token
							.toCharArray());
					if (constituent != null) {
						if (tokenizer.hasMoreTokens()) {
							return Finder.find0(
								aPath.substring(Finder.Header.length() + 1),
								constituent,
								token.charAt(0));
						}
						else {
							return constituent;
						}
					}
					else {
						throw new FormatException(
							"Abstract model does not contain first constituent in the path.");
					}
					//	}
					//	else {
					//		throw new FormatException(
					//			"Abstract model name and path are one and the same.");
					//	}
				}
				else {
					if (Arrays.equals(
						anAbstractModel.getName(),
						token.toCharArray())) {

						return anAbstractModel;
					}
					else {
						throw new FormatException(
							"Abstract model name and path do not match.");
					}
				}
			}
			else {
				throw new FormatException("Not an abstract model-level path.");
			}
		}
		else {
			throw new FormatException("Empty path.");
		}
	}
	private static IConstituent find0(
		final String aPath,
		final IConstituent aConstituent,
		final char aDelimiter) throws FormatException {

		final StringTokenizer tokenizer =
			new StringTokenizer(aPath, IConstants.ALL_SYMBOLS, true);
		Finder.Header.setLength(0);

		if (tokenizer.hasMoreTokens()) {
			if (aConstituent instanceof IContainer) {
				String token = tokenizer.nextToken();
				Finder.Header.append(token);

				// Yann 2014/04/21: Member parameter types!
				// It could be that the path contains:
				//	AssociateWithTest(ProfileSyncService$SyncTest *[])
				// In that case, I want to look for that constituent
				// (method) now, not split on the $ sign *inside* the
				// parentheses! Ah, C++...
				if (token.indexOf("(") > -1) {
					final char[] id = aPath.toCharArray();
					final IConstituent constituent =
						((IContainer) aConstituent).getConstituentFromID(id);
					if (constituent == null) {
						throw new FormatException("Cannot find ID: \"" + token
								+ "\" in \"" + aConstituent.getDisplayID()
								+ "\".");
					}
					else {
						return constituent;
					}
				}

				final IConstituent constituent;
				if (aDelimiter == IConstants.MEMBER_ENTITY_SYMBOL) {
					// Yann 2010/06/29: Name vs. ID.
					// A member entity has for name its "simple name"
					// and for ID is fully qualified name, as any
					// other entity now...
					// Yann 2013/09/28: Name vs. ID, reloaded!
					// So, as per the previous comment, I need to
					// create the fully-qualified name of the member
					// entity OR look for it by name rather than by
					// ID BUT, if looking per name, I can retrieve
					// the wrong entity, in case a field of method
					// would have the same name as a member entity.
					// Yann 2014/04/21: Yet again...
					char[] id = aConstituent.getID();
					id = ArrayUtils.add(id, IConstants.MEMBER_ENTITY_SYMBOL);
					id = ArrayUtils.addAll(id, token.toCharArray());
					constituent =
						((IContainer) aConstituent).getConstituentFromID(id);
				}
				else {
					// Yann 2013/09/12: A bit of mismatch!
					// The path of an operation includes parentheses
					// and parameter types while the ID of an operation
					// is just that, it is ID, without anything else.
					// Therefore, if the token includes parentheses,
					// I must remove them...
					// ... which is really NOT a good idea: ID must
					// include parenthesis and parameter types to
					// distinguish a field "a" from a method "a()"
					// from the overloaded method "a(int)!
					//	final int startOpeningParenthesisInclusive =
					//		ArrayUtils.indexOf(id, '(');
					//	if (startOpeningParenthesisInclusive > 0) {
					//		id =
					//			ArrayUtils.subarray(
					//				id,
					//				0,
					//				startOpeningParenthesisInclusive);
					//	}
					char[] id = token.toCharArray();
					constituent =
						((IContainer) aConstituent).getConstituentFromID(id);
				}
				if (constituent != null) {
					if (tokenizer.hasMoreTokens()) {
						token = tokenizer.nextToken();
						Finder.Header.append(token);

						return Finder.find0(
							aPath.substring(Finder.Header.length()),
							constituent,
							token.charAt(0));
					}
					else {
						return constituent;
					}
				}
				else {
					throw new FormatException("Cannot find ID: \"" + token
							+ "\" in \"" + aConstituent.getDisplayID() + "\".");
				}
			}
			else {
				throw new FormatException("Constituent with ID: \""
						+ aConstituent.getDisplayID()
						+ "\" is not a container.");
			}
		}
		else {
			throw new FormatException("Empty path.");
		}
	}
	public static IContainer findContainer(
		final String aPath,
		final IAbstractModel anAbstractModel) throws FormatException {

		final String newPath =
			aPath.substring(0, aPath.lastIndexOf(IConstants.ELEMENT_SYMBOL));

		return (IContainer) Finder.find0(newPath, anAbstractModel);
	}
}
