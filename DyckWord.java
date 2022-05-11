package applications.arithmetic;

import datastructures.sequential.Stack;

/**
 * This class is mainly served to check if a expression is a dyckword with balanced brackets.
 * DyckWord is used to store the expression in ArithmeticeExpression.
 *
 * @author Ritwik Banerjee
 * @author Allen Chen
 */
public class DyckWord {

    private final String word;

    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }

    /**
     * Checks if a word is a Dyckword
     * @param word the word that we want to check if its a DyckWord
     * @return <code>true</code> if and only if word is a dyckword, a right bracket doesn't come before a left bracket <code>false</code> otherwise.
     */
    private static boolean isDyckWord(String word) {
        Stack<String> brackets = new Stack<>();

        for(int i = 0; i < word.length(); i++){
            if(Brackets.isLeftBracket(word.charAt(i))) {
               brackets.push(word.charAt(i));
            }
            if(Brackets.isRightBracket(word.charAt(i))) {
                if(brackets.isEmpty()){
                    return false;
                }else {
                    if (!Brackets.correspond((Character) brackets.pop(), word.charAt(i))) {
                        return false;
                    }
                }
            }
        }

        if(brackets.isEmpty()){
            return true;
        }
        return false;
    }

    public String getWord() {
        return word;
    }
}
