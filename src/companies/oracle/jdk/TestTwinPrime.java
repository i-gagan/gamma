package companies.oracle.jdk;

import java.util.Arrays;
import java.util.List;

public class TestTwinPrime {
    TwinPrime twinPrime;

    TestTwinPrime() {
        twinPrime = new TwinPrime();
    }

    public static void main(String[] args) {
        TestTwinPrime testTwinPrime = new TestTwinPrime();
        testTwinPrime.runTestCase1();
        testTwinPrime.runTestCase2();
        testTwinPrime.runTestCase3();
    }

    public void runTestCase1() {
        int n =  20;
        List<int[]> result = twinPrime.getTwinPrime(n);
        displayList(result);
    }

    public void runTestCase2() {
        int n =  17;
        List<int[]> result = twinPrime.getTwinPrime(n);
        displayList(result);
    }

    public void runTestCase3() {
        int n =  1000;
        List<int[]> result = twinPrime.getTwinPrime(n);
        displayList(result);
    }

    public void displayList(List<int[]> list) {
        for (int[] pair: list) {
            System.out.print(Arrays.toString(pair) + " ");
        }
        System.out.println();
    }
}
