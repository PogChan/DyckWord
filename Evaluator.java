package applications.arithmetic;

/**
 * This interface defines the structure of the evaluator for the evaluation of arithmetic
 * expressions. It will be implemented in the PostfixEvaluator class, but it could also be implemented in prefix and infix evaluator classes.
 *
 * @author Allen Chen
 * @author Ritwik Banerjee
 */
public interface Evaluator {

    /**
     * This method will evaluate an prefix, postfix, or infix expression based on the implementation.
     *
     * @param expressionString the expression that is to be evaluated
     * @return a double value of the result after evaluating the expression.
     */
    double evaluate(String expressionString);

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     *
     * @param s     the given string
     * @param start the given index
     * @return the next token starting at the given index in the given string
     */
    String nextToken(String s, int start);

    /**
     * Determines whether or not a string is a valid operand.
     *
     * @param s the given string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    boolean isOperand(String s);

    /**
     * This class handles the parsing of tokens from a string. This is helpful in situations where a
     * single token may take up more than one character in the string.
     */
    class TokenBuilder {

        /**
         * The {@link StringBuilder} object used internally. This is used because {@link String}s in
         * Java are immutable, while we may want to build a token as we parse from left to right one
         * character at a time.
         */
        private StringBuilder tokenBuilder = new StringBuilder();

        /**
         * @see StringBuilder#append(char)
         */
        public void append(char c) {
            tokenBuilder.append(c);
        }

        /**
         * @return the final string object that represents a single token
         * @see StringBuilder#toString()
         */
        public String build() {
            return tokenBuilder.toString();
        }

    }
}
