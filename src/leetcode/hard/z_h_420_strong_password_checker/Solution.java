package leetcode.hard.z_h_420_strong_password_checker;

//https://leetcode.com/problems/strong-password-checker/description/

class Solution {
    public static int strongPasswordChecker(String password) {
        boolean[] conditionValidate = validate(password);
        boolean isValid = true;
        for (int i = 0; i < 3; i++) {
            if (!conditionValidate[i]) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            return 0;
        }

        StringBuilder sb = new StringBuilder(password);

        return solve(sb);
    }

    private static int solve(StringBuilder password) {


        return 0;
    }

    private static boolean[] validate(String password) {
        boolean[] conditionValidate = new boolean[3];
        if (password.length() >= 6 && password.length() <= 20) {
            conditionValidate[0] = true;
        }
        boolean isDigitPresent = false, isLowerCasePresent = false, isUpperCasePresent = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                isLowerCasePresent = true;
            } else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                isUpperCasePresent = true;
            } else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                isDigitPresent = true;
            }
        }
        conditionValidate[1] = isDigitPresent && isLowerCasePresent && isUpperCasePresent;

        for (int i = 2; i < password.length(); i++) {
            if (!(password.charAt(i) == password.charAt(i - 1) && password.charAt(i - 1) == password.charAt(i - 2))) {
                conditionValidate[2] = true;
                break;
            }
        }
        return conditionValidate;
    }

    public static void main(String[] args) {
        String password = "aA1";
        System.out.println(strongPasswordChecker(password));
    }
}
