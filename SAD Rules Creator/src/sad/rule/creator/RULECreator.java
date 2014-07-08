/* (c) Copyright 2001 and following years, Yann-Gal Guhneuc,
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
package sad.rule.creator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sad.rule.creator.model.IRuleCard;
import sad.rule.creator.model.IVisitor;
import sad.rule.creator.visitor.CodeSmellGenerator;
import sad.rule.creator.visitor.DesignSmellGenerator;
import util.io.ProxyConsole;

public class RULECreator {
	public static void main(final String[] args) throws Exception {
		final String[] listOfRuleCards =
			new File("../SAD Rules Creator/rsc/").list();
		for (int i = 0; i < listOfRuleCards.length; i++) {
			final String ruleCard = listOfRuleCards[i];
			if (ruleCard.endsWith(".rules")) {
				final RULECreator ruleCreator =
					new RULECreator(new String[] { "../SAD Rules Creator/rsc/"
							+ ruleCard });
				ruleCreator.parse();
			}
		}
		//	ruleCreator =
		//		new RULECreator(
		//			new String[] {
		//				PropertyManager.getRulecardDirectory()
		//					+ "SpaghettiCode.rules" });
		//	ruleCreator.parse();
		//
		//	ruleCreator =
		//		new RULECreator(
		//			new String[] {
		//				PropertyManager.getRulecardDirectory()
		//					+ "SwissArmyKnife.rules" });
		//	ruleCreator.parse();
		//
		//	ruleCreator =
		//		new RULECreator(
		//			new String[] {
		//				PropertyManager.getRulecardDirectory()
		//					+ "FunctionalDecomposition.rules" });
		//	ruleCreator.parse();
		//
		//	ruleCreator =
		//		new RULECreator(
		//			new String[] {
		//				PropertyManager.getRulecardDirectory()
		//					+ "DummyRuleCard.rules" });
		//	ruleCreator.parse();
	}

	private final String fileName;
	public RULECreator(final String[] fileNames) {
		// TODO: Must deal with multiple files!
		this.fileName = fileNames[0];
	}
	public String[] parse() {
		final ArrayList listOfRuleCard = new ArrayList();

		try {
			final LineNumberReader reader =
				new LineNumberReader(new InputStreamReader(new FileInputStream(
					this.fileName)));

			final StringBuffer buffer = new StringBuffer();
			String readLine;
			while ((readLine = reader.readLine()) != null) {
				buffer.append(readLine);
				buffer.append('\n');
			}
			reader.close();

			final RULEParser parser =
				new RULEParser(new RULELexer(
					new StringReader(buffer.toString())));
			parser.parse();
			if (parser.hasError()) {
				return new String[0];
			}

			final IVisitor visitor =
				new DesignSmellGenerator(this.fileName, true);
			final List rulesSet = parser.getRulesSet();
			final Iterator iterRule = rulesSet.iterator();
			while (iterRule.hasNext()) {
				final IRuleCard aRuleCard = (IRuleCard) iterRule.next();
				aRuleCard.accept(visitor);

				final StringBuffer generatedCode =
					(StringBuffer) ((DesignSmellGenerator) visitor).getResult();
				System.out.println(generatedCode);
				generatedCode.delete(0, generatedCode.length());

				// ADD TO TEST CodeSmellGenerator
				final IVisitor visitor2 =
					new CodeSmellGenerator(aRuleCard.getID());

				listOfRuleCard.add(aRuleCard.getID());

				// ADD TO TEST CodeSmellGenerator
				aRuleCard.accept(visitor2);
			}
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final String[] temp =
			(String[]) listOfRuleCard
				.toArray(new String[listOfRuleCard.size()]);
		return temp;
	}
}
