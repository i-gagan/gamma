package companies.servicenow.mobile_phone;

public class Solution {
    public static void main(String[] args) {
        //String input = "aaaaa";
        //String input = "abcdefghijklmnopqrstuvwxyz";
        String input = "abcabc";
        //String input = "gagamn";
        //String input = "ppqrammnod";
        //String input = "gagamno";
        //String input = "pqrsghimnggh";

        String typedString = getTypedString(input);
        System.out.println("Typed String " + typedString);
    }

    public static String getTypedString(String input) {
        System.out.println("Input " + input);
        char[] charStr = input.toCharArray();
        StringBuilder result = new StringBuilder();

        int pressTimeCount, counter;
        for (int i = 0; i < charStr.length; i++) {
            char ch = charStr[i];
            pressTimeCount = 1;
            counter = 1;
            switch (ch) {
                case 'a', 'd', 'g', 'j', 'm', 't':
                    while (i + 1 < charStr.length && counter < 3 && charStr[i + 1] - charStr[i] == 1) {
                        pressTimeCount++;
                        counter++;
                        i++;
                    }
                    break;

                case 'p', 'w':
                    while (i + 1 < charStr.length && counter < 4 && charStr[i + 1] - charStr[i] == 1) {
                        pressTimeCount++;
                        counter++;
                        i++;
                    }
                    break;
            }
            char typedChar = (char)((int)ch + (pressTimeCount - 1));
            //System.out.println(typedChar);
            result.append(typedChar);
        }
        return result.toString();
    }
}

//abc - 2
//def - 3
//ghi - 4
//jkl - 5
//mno - 6
//pqrs - 7
//tuv - 8
//wxyz - 9

//gagamn  -> gagan
//ppqrammnod -> pramod
//pqrsghimngh -> singh


//gagamn
//
//->gagamno
//->gagan


