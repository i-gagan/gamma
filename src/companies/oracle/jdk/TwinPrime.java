package companies.oracle.jdk;

import java.util.ArrayList;
import java.util.List;

public class TwinPrime {
    public List<int[]> getTwinPrime(int n) {
        List<int[]> result = new ArrayList<>();

        for (int i = 2; i < n - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                result.add(new int[]{i, i + 2});
            }
        }
        return result;
    }

    public boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
//Given an integer n. print all twin prime number pairs between 2 to n.

//17, 19, 5, 7, 3, 5
//20 ->
//3, 5 -> diff 2
//5, 7
//        7, 11
//
//11, 13
//17, 19
//
//2, 3, 5, 7, 11, 13, 17, 19
