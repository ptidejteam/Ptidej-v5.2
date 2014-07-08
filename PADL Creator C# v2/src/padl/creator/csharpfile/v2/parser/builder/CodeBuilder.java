package padl.creator.csharpfile.v2.parser.builder;

import org.antlr.runtime.tree.CommonTree;
import padl.creator.csharpfile.v2.parser.exceptions.ParserException;
import padl.kernel.IConstituent;
import padl.kernel.exception.CreationException;

/**
 * A CodeBuilder is responsible to create and customize a specific type of
 * PADL model entity. The builder are mainly used to do the conversion from
 * an AST to PADL. Check ANTLR2PADLReader for a typical usage.
 */
public interface CodeBuilder {
	/**
	 * Add the given constituent to the builded entity.
	 * @param childElement
	 * @
	 */
	void add(IConstituent childElement);

	/**
	 * Returns the builded entity.
	 * @return
	 */
	IConstituent close();

	/**
	 * Creates the PADL entity.
	 * @param node
	 * @param context
	 * @throws ParserException
	 */
	void create(CommonTree node, BuilderContext context)
			throws CreationException;

	/**
	 * Returns the enveloping CodeBuilder, if any, null otherwise.
	 * @return the enveloping CodeBuilder, if any, null otherwise.
	 */
	CodeBuilder getParent();

	/**
	 * Returns true if this builder knows that the context is right for it to close it's entity being built.
	 * @param context
	 * @return
	 */
	boolean isReadyToClose(BuilderContext context);

	/**
	 * Records the context at the opening block of this entity.
	 * @param context
	 */
	void open(BuilderContext context);
}
