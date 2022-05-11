package datastructures.sequential;

import java.nio.file.StandardWatchEventKinds;
import java.util.EmptyStackException;

/**
 * This is a class that implements a Stack data structure.
 * It uses a nodes and pointers instead of an array.
 * Stacks will later be used to aid in the evaluation of arithmetic expressions.
 *
 * @author Allen Chen
 * @author Ritwik Banerjee
 */
public class Stack<T> implements LIFOQueue{
    private SNode<T> top;
    private int n; //size of stack

    /**
     * This constructor will construct a stack instance.
     * No params because we would like to track the top node in the stack so top will be updated with each push.
     */
    public Stack(){
        this.top = null;
    }

    /**
     * Retrieves and removes the element at the top of this stack.
     *
     * @return the element at the top of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public Object pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        SNode<T> temp = top;
        top = top.getNext();
        n--;
        return temp.getData();
    }

    /**
     * Pushes the specified element onto the top of this stack.
     * Changes the top node to the input element.
     *
     * @param element the element to be pushed onto the top of this stack.
     */
    @Override
    public void push(Object element) {
        SNode<T> newNode = new SNode<T>((T) element);
        newNode.setNext(top);
        this.top = newNode;
        n++;
    }
    /**
     * Retrieves, but does not remove, the element at the top of this stack.
     *
     * @return the element at the top of this stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public Object peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }

    /**
     * Returns the number of elements in this sequence. If this sequence has more than <code>Integer.MAX_VALUE</code>
     * elements, returns <code>Integer.MAX_VALUE</code>.
     *
     * @return the number of elements in this sequence
     */
    @Override
    public int size() {
        return this.n;
    }

    /**
     * This method lets the user know if the stack is empty or not.
     *
     * @return <code>true</code> if and only if the sequence contains no elements, <code>false</code> otherwise.
     */
    @Override
    public boolean isEmpty() {
        if(size() == 0){
            return true;
        }
        return false;
    }
}
