package companies.oracle.jdk;

import java.util.List;

public class TestAnagrams {
    Anagrams anagrams;

    public TestAnagrams() {
        anagrams = new Anagrams();
    }

    public static void main(String[] args) {
        TestAnagrams testAnagrams = new TestAnagrams();
        testAnagrams.runTestCase1();
        testAnagrams.runTestCase2();
        testAnagrams.runTestCase3();
        testAnagrams.runTestCase4();
        testAnagrams.runTestCase5();
    }

    public void runTestCase1() {
        String[] strs = {""};
        List<List<String>> result = anagrams.groupAnagrams(strs);
        System.out.println(result);
    }

    public void runTestCase2() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = anagrams.groupAnagrams(strs);
        System.out.println(result);
    }

    public void runTestCase3() {
        String[] strs = {"a"};
        List<List<String>> result = anagrams.groupAnagrams(strs);
        System.out.println(result);
    }

    public void runTestCase4() {
        String[] strs = {"Tea", "ate", "tae", "dsf", "fDS", null};
        List<List<String>> result = anagrams.groupAnagrams(strs);
        System.out.println(result);
    }

    public void runTestCase5() {
        String[] strs = {"kbc", "kad", "dsf", "fDS"};
        List<List<String>> result = anagrams.groupAnagrams(strs);
        System.out.println(result);
    }
}
