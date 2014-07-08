package padl.creator.csharpfile.v2.parser.exceptions;

/**
 * Generic exception used for all errors occurring through
 * the visit of the AST tree.
 */
@SuppressWarnings("serial")
public class ParserException extends Exception {

    public ParserException() {
    }

    public ParserException(final String message) {
        super(message);
    }

    public ParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParserException(final Throwable cause) {
        super(cause);
    }
}
