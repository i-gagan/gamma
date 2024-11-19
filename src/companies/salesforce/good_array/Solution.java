package companies.salesforce.good_array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class Solution {
    public static List<Integer> getQueryResults(long n, List<List<Integer>> queries) {
        //System.out.println(Integer.toBinaryString((int) n));
        List<Long> goodArray = new ArrayList<>();
        long position = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                goodArray.add((long) Math.pow(2, position));
            }
            position++;
            n >>= 1;
        }

        List<Integer> results = new ArrayList<>();
        for (List<Integer> query : queries) {
            int l = query.get(0) - 1;
            int r = query.get(1) - 1;
            int m = query.get(2);

            if (m == 1) {
                results.add(0);
                continue;
            }

            l = Math.max(0, l);
            r = Math.min(goodArray.size() - 1, r);
            if (l > r) {
                results.add(1);
                continue;
            }

            BigInteger bProduct = BigInteger.ONE;
            BigInteger bMod = BigInteger.valueOf(m);
            for (int i = l; i <= r; i++) {
                bProduct = bProduct.multiply(BigInteger.valueOf(goodArray.get(i))).mod(bMod);
                if (bProduct.equals(BigInteger.ZERO)) {
                    break;
                }
            }
            results.add(bProduct.intValue());
        }

        return results;
    }

    public static void main(String[] args) {
        int n = 26;
        Integer[][] input = {{1, 2, 1009}, {3, 3, 5}};
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(input[0]));
        queries.add(Arrays.asList(input[1]));
        System.out.println(getQueryResults(n, queries));
    }
}
