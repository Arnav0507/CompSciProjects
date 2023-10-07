import java.util.Stack;

public class DecimalToBinary {
    public static void main(String[] args) {
        final int decimalValue = 9;//YOU CHOOSE

        //code
        Stack<Integer> stack = new Stack<Integer>();
        int value = decimalValue;
        while(value > 0){
            if(value%2==0){
                stack.push(0);
            }
            if(value%2==1){
                stack.push(1);
            }
            value/=2;
        }
        // Last in first out means reverse order

        /*System.out.println("The size of the Stack is "+stack.size()+".");
        System.out.println("What's on top? " +stack.peek());
        System.out.println("Unstack the stack!");*/
        while (!stack.empty())
        {
            System.out.print (stack.pop());
        }
    }
}