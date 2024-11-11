package companies.linkedin.decode_depth;

import java.util.Stack;

class Solution {
    public static int calculateSum(String input) {
        int totalSum = 0, depth = 1;
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                stack.push(ch);
            } else {
                if (ch == '[') {
                    stack.push(ch);
                } else if (ch == ']') {
                    while (stack.peek() != '[') {
                        Character poppedElement = stack.pop();
                        totalSum = totalSum + ((poppedElement - '0') * depth);
                    }
                    stack.pop();
                    depth++;
                }
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        String input = "[1[2,[3,4]]]";
        int result = calculateSum(input);
        System.out.println("The result is: " + result);  // Expected output: 14
    }
}

