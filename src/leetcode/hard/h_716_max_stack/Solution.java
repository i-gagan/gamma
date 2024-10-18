package leetcode.hard.h_716_max_stack;

//https://leetcode.com/problems/max-stack/description/

import java.util.*;

class MaxStack {
    private Deque<Integer> stack;
    private PriorityQueue<Integer> maxValueStack;

    public MaxStack() {
        stack = new ArrayDeque<>();
        maxValueStack = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void push(int x) {
        stack.addFirst(x);
        maxValueStack.add(x);
    }

    public int pop() {
        int poppedValue = stack.pop();
        maxValueStack.remove(poppedValue);
        return poppedValue;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxValueStack.peek();
    }

    public int popMax() {
        int max = maxValueStack.poll();
        stack.remove(max);
        return max;
    }
}

class Solution {
    public static void main(String[] args) {
//        String[] sequence = {"MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"};
//        int[][] input = {{}, {5}, {1}, {5}, {}, {}, {}, {}, {}, {}};
        String[] sequence = {"MaxStack","push","push","popMax","peekMax"};
        int[][] input = {{}, {5}, {1}, {}, {}};
        MaxStack maxStack = null;
        int inputIndex = 0;
        for (String s : sequence) {
            switch (s) {
                case "MaxStack":
                    maxStack = new MaxStack();
                    inputIndex++;
                    break;
                case "push":
                    assert maxStack != null;
                    maxStack.push(input[inputIndex][0]);
                    inputIndex++;
                    break;
                case "top":
                    assert maxStack != null;
                    maxStack.top();
                    inputIndex++;
                    break;
                case "popMax":
                    assert maxStack != null;
                    maxStack.popMax();
                    inputIndex++;
                    break;
                case "peekMax":
                    assert maxStack != null;
                    maxStack.peekMax();
                    inputIndex++;
                    break;
            }
        }
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
