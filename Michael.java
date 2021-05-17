import java.util.*;
public class Michael {


    public static void main(String[] args) {
        testRPN();
    }
    
    public static void testRPN() {
        String[] tests = {
                "2 2 +", 
                "2 3 -", 
                "4 5 *", 
                "6 5 /", 
                "1 2 3 4 5 6 7 8 9 + + + + + + + +",
        "5 2 2 * - 1 2 + /"};
        double[] results = {4.0, -1, 20, 1.2, 45, 0.3333333333333333};

        for (int i = 0; i < tests.length; i++) {
            System.out.println(String.format("Evaluating => %s", tests[i]));
            double result = evaluateRPN(tests[i]);
            System.out.println(String.format("Result => %s", result));
            if (result != results[i]) {
                System.out.println(String.format("Error on test %s expected %s, received %s", 
                        tests[i], results[i], evaluateRPN(tests[i])));
                return;
            }
        }
        System.out.println("Congratulations - you passed the tests");

    }
    
       public static double evaluateRPN(String input) {
        // Create queue, transfer input into it "2 3 +" -> [2 3 +]
        
        Queue<String> qString = new LinkedList<String>();      
        
        // You'll want two for loops, one for each index of tests and one for each character of each index in tests. 
        // You'll also want to use String.trim() to eliminate spacing so it doesn't add empty spaces to the queue. 
        
        for (int i = 0; i < input.length(); i++) {
            qString.add(input.charAt(i) + ""); // + "" turns any character into a string... (thats standard formating, fyi)
        }
        

        // Print input
        System.out.println(qString);

        // Create new empty stack
        Stack<Double> stringS = new Stack<Double>(); // not sure if Stack is supposed to be doubles or Strings either way 
                                                     //you can convert back and forth with the functions he gives you.
        

        // Pop off each item in the queue and evaluate it
                // if operator such as '*' - pop two operands, evaluate, push result: Queue [+] Stack [2 3] => [][5]

                // else operands such as "5" just need to be pushed [3 +][2]=>[+][2 3]
                
         while(!qString.isEmpty()) {
            String temp = qString.remove();
            
            if (!isOperator(temp)) { // if not an operator
               stringS.push(Double.parseDouble(temp));// add number to stack, and convert to a double
            } else { // if it is an operator
               String result = evaluateBinaryOperator(stringS.pop(), temp, stringS.pop()); // find result of last two number evaluated by operator
               stringS.push(Double.parseDouble(result)); // add result to the stack
            }
         }

        // return last item in stack [][5]
        return stringS.pop();
    }

    /**
     * TODO:     
     *
     * @param input - TODO: 
     * @return boolean - TODO: 
     */
    private static boolean isOperator(String input) {
        // Check if the String is one of the + - * / characters 
        // Either compare against each character individually or use String.contains
        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
            return true;
        } 
        return false;
    }

    /**
     * TODO:     
     * 
     * evaluateBinaryOperator(2, "+", 3)=> "5"
     * 
     * @param op1 - TODO: 
     * @param operator - TODO: 
     * @param op2 - TODO: 
     * @return TODO: 
     */
    private static String evaluateBinaryOperator(Double op1, String operator, Double op2) {
        // Individual if/else or case switch to find operator + - * /
        if (operator.equals("+")) {
           op1 += op2;
        } else if (operator.equals("-")) {
           op1 -= op2;
        } else if (operator.equals("*")) {
           op1 *= op2;
        } else if (operator.equals("/")) {
           op1 /= op2;
        } else {
           throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        // Return op1 <operator>
        return String.valueOf(op1);
    }

}
