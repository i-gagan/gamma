//package companies.expedia;
//
//public class Soltuion {
//}
//
//class Result {
//
//    /*
//     * Complete the 'compressWord' function below.
//     *
//     * The function is expected to return a STRING.
//     * The function accepts following parameters:
//     *  1. STRING word
//     *  2. INTEGER k
//     */
//
//    //abbcccb
//    //i - 3
//    //aba
//    //baac 2
//    // public static String compressWord(String word, int k) {
//    // // Write your code here
//    //     if (word.length() < k) {
//    //         return word;
//    //     } else {
//    //         for (int i = 0; i < word.length() - k; i++) { // 0 to 2
//    //             int count = 1;
//    //             int j;
//    //             for (j = i; j < i + k; j++) { // 1 to < 1 + 3
//    //                 if (word.charAt(j) == word.charAt(j + 1)) {
//    //                     count++;
//    //                 } else {
//    //                     break;
//    //                 }
//    //             }
//    //             if (count >= k) {
//    //                 word = word.substring(0, i) + word.substring(i + k);
//    //                 i = i - (k - 1);
//    //                 i--;
//    //                 //continue;
//    //                 //System.out.println("i " + i);
//    //             }
//    //         }
//    //         return word;
//    //     }
//    // }
//
//    //abbcccb
//    //i - 3
//    //aba
//    //baac 2
//
//    public static String compressWord(String word, int k) {
//        // Write your code here
//        if (word.length() < k) {
//            return word;
//        } else {
//            Stack<Character> stack = new Stack<>();
//            for (int i = 0; i < word.length(); i++) { // 0 to 2
//
//                if (!stack.isEmpty() && stack.size() >= k) {
//
//                    int count = 1;
//                    Character lastCharacter = stack.peek();
//                    if (lastCharacter == word.charAt(i)) {
//                        count++;
//                    }
//
//                    if (count >= k) {
//                        int temp = count;
//                        while (count > 0 && !stack.isEmpty()) {
//                            stack.pop();
//                            temp--;
//                        }
//                        temp = k - 1;
//                        while (temp >= 0 && !stack.isEmpty()) {
//                            stack.pop();
//                            temp--;
//                        }
//                        continue;
//                    }
//                }
//                stack.push(word.charAt(i));
//            }
//            String sb = new String();
//            while (!stack.isEmpty()) {
//                sb = stack.pop() + sb;
//            }
//            return sb;
//        }
//
//
//    }
//}
//
//
//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String word = bufferedReader.readLine();
//
//        int k = Integer.parseInt(bufferedReader.readLine().trim());
//
//        String result = Result.compressWord(word, k);
//
//        bufferedWriter.write(result);
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}