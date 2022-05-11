package applications.arithmetic;

import datastructures.sequential.Stack;

/**
 * This class contains the evaluate method, which evaluates postfix expressions. This class implements the Evaluator interface.
 * This class is particularly used to evaluate a string postfix expression using the structure of the Evaluator interface.
 *
 * @author Allen Chen
 */
public class PostfixEvaluator implements Evaluator{

    /**
     * This method will evaluate an postfix expression.
     *
     * @param expressionString the string expression that is to be evaluated
     * @return a double value of the result after evaluating the postfix expression.
     */
    @Override
    public double evaluate(String expressionString) {
        double result =0;
        Stack<String> operands= new Stack<>();
        for(int i = 0; i< expressionString.length(); i++){
            String currentToken = nextToken(expressionString, i);
            if(isOperand(currentToken)){
                operands.push(currentToken);
                i += currentToken.length();
            }else if(Operator.isOperator(currentToken)){
                double first = Double.parseDouble(operands.pop().toString());
                double second = Double.parseDouble(operands.pop().toString());
                double afterOperation = 0;
                if(Operator.of(currentToken) == Operator.ADDITION){
                    afterOperation= second + first;
                    operands.push(String.valueOf(afterOperation));
                }else if(Operator.of(currentToken) == Operator.SUBTRACTION){
                    afterOperation= second - first;
                    operands.push(String.valueOf(afterOperation));
                }else if(Operator.of(currentToken) == Operator.MULTIPLICATION){
                    afterOperation= second * first;
                    operands.push(String.valueOf(afterOperation));
                }else if(Operator.of(currentToken) == Operator.DIVISION){
                    afterOperation= second / first;
                    operands.push(String.valueOf(afterOperation));
                }
                i++;
            }
        }
        if(!operands.isEmpty()) {
            result = Double.parseDouble(operands.pop().toString());
        }
        return result;
    }

    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     * This implementation. Each token can be an operand or an operator.
     *
     * @param s     the given string
     * @param start the given index
     * @return the next token starting at the given index in the given string
     */
    @Override
    public String nextToken(String s, int start) {
        Converter.TokenBuilder builder = new Converter.TokenBuilder();
        for(int i = start; i< s.length(); i ++){
            if(s.charAt(i) == ' '){
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
    public boolean isOperand(String s){
        return !Operator.isOperator(s); //we don't need to check for brackets because all those are gone in postfix
    }
}
