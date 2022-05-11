package applications.arithmetic;

import datastructures.sequential.Stack;

/**
 * This class contains the convert method that will convert infix expressions into postfix. This class is particularly used to
 * take an ArithmeticExpression as the input and converts the infix expression into postfix.
 *
 * @author Allen Chen
 */
public class ToPostfixConverter implements Converter{

    /**
     * Converts given infix expression to postfix
     *
     * @param expression  the given arithmetic infix expression
     */
    @Override
    public String convert(ArithmeticExpression expression) {
        Stack<String> operators = new Stack<>();
        String result = "";
        String expressionString = expression.getExpression();
        for(int i = 0; i < expressionString.length(); i++){
            if(Operator.isOperator(expressionString.charAt(i)) || Brackets.isLeftBracket(expressionString.charAt(i)) || Brackets.isRightBracket(expressionString.charAt(i))){
                if(Brackets.isLeftBracket(expressionString.charAt(i))) {
                    operators.push(expressionString.charAt(i));
                }else if(Brackets.isRightBracket(expressionString.charAt(i))){
                    while(!Brackets.isLeftBracket((Character)operators.peek())){
                        result += " " + operators.pop();
                    }
                        operators.pop(); // pops that last leftBracket
                }else if(!operators.isEmpty() && Operator.of(expressionString.charAt(i)).getRank() >= Operator.of((Character)operators.peek()).getRank()){
                        while(!operators.isEmpty() && Operator.of(expressionString.charAt(i)).getRank() >= Operator.of((Character)operators.peek()).getRank()){
                            result += " " + operators.pop();
                        }
                        operators.push(expressionString.charAt(i));
                }else if(!operators.isEmpty() && Operator.of(expressionString.charAt(i)).getRank() <= Operator.of((Character)operators.peek()).getRank()){
                    operators.push(expressionString.charAt(i));
                }else{
                    operators.push(expressionString.charAt(i));
                }
            }else{ //we are at an operand
                String next = nextToken(expressionString, i);
                result += " " + next;
                i += next.length()-1;
            }
        }
        while(!operators.isEmpty()){
            result += " " + operators.pop();
        }

        return numOfOperands(expressionString)==0 ? result : result.substring(1);
    }

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     *
     * @param s     the given string
     * @param start the given index
     * @return the next token starting at the given index in the given string
     */
    @Override
    public String nextToken(String s, int start) {
        TokenBuilder builder = new TokenBuilder();
        for(int i = start; i< s.length(); i ++){
            if(Operator.isOperator(s.charAt(i)) || Brackets.isLeftBracket(s.charAt(i)) || Brackets.isRightBracket(s.charAt(i))){
                break;
            }
            builder.append(s.charAt(i));
        }
        return builder.build();
    }

    /**
     * Determines whether or not a string is a valid operand.
     *
     * @param s the given string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    @Override
    public boolean isOperand(String s) {
        //make sure I go through every part of this string to make sure no operator is mixed in there.
        for(int i = 0; i < s.length(); i++){
            if(Operator.isOperator(s.charAt(i)) && Brackets.isLeftBracket(s.charAt(i)) && Brackets.isRightBracket(s.charAt(i))){
               return false;
            }
        }
        return true;
    }

    /**
     * Determines the number of operands in the string s.
     *
     * @param s the given string
     * @return the number of operands in the string s.
     */
    public int numOfOperands(String s) {
        //make sure I go through every part of this string to make sure no operator is mixed in there.
        int sum =0;
        for(int i = 0; i < s.length(); i++){
            if(!Operator.isOperator(s.charAt(i)) && !Brackets.isLeftBracket(s.charAt(i)) && !Brackets.isRightBracket(s.charAt(i))){
                sum++;
            }
        }
        return sum;
    }
}
