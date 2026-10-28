package stackvisualizer;

public class StackArray {
    
    private final int MAX = 10;
    private int[] stack;
    private int top;
    
    public StackArray() {
        stack = new int[MAX];
        top = -1;
    }
    
    public String push(int value) {
        if (isFull()) {
            return "STACK OVERFLOW: Cannot push, stack is full";
        }
        
        stack[++top] = value;
        return "Succesfully pushed: " + value;
    }
    
    public String pop() {
        if (isEmpty()){
            return "STACK UNDERFLOW: Cannot pop, stack is empty" ;
        }
        
        int removed = stack[top];
        top--;

        return "Successfully popped: " + removed;
    }

    public String peek() {
        if (isEmpty()) {
            return "Stack is empty. Nothing to peek.";
        }
S
        return "Top element: " + stack[top];
    }

    public void clear() {
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == MAX - 1;
    }

    public int getSize() {
        return top + 1;
    }

    public int getCapacity() {
        return MAX;
    }

    public int getElement(int index) {
        if (index < 0 || index > top) {
            throw new IndexOutOfBoundsException("Invalid stack index");
        }

        return stack[index];
    }

    public String checkStack() {
        if (isEmpty()) {
            return "Stack is empty.";
        }

        return "Stack is not empty. Elements: " + getSize();
    }

    public String random() {
        clear();

        int count = (int) (Math.random() * MAX) + 1;

        for (int i = 0; i < count; i++) {
            int value = (int) (Math.random() * 100) + 1;
            push(value);
        }

        return "Generated " + count + " random elements.";
    }
}
    
