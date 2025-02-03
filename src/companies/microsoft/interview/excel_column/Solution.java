package companies.microsoft.interview.excel_column;

public class Solution {

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");

        String column = "AA";

        int columnNumber = getColumnNumber(column);
        System.out.println("Column No " + columnNumber);
    }

    public static int getColumnNumber(String column) {
        char[] charArray = column.toCharArray();

        int result = 0;
        int power = 0;
        for (int i = charArray.length - 1; i >=0; i--) {
            result = result + (int)Math.pow(26, power) * (charArray[i] - 'A' + 1);
            power++;
        }
        return result;
    }
}


// 1- 26

// 2 - 26 * 26

// 3 - 26 * 26 * 26

// 4 - 26 * 26 * 26 * 26

// CDA
// 3 + 4 + 1

// A B



// 1 2 = 26 * 1 + 26 * 0 + 2


// AZ = 27 + 25 = 52
// 1 26
// (26 ^ 1 * 1) + (26 ^ 0 * 26) = 27 + 26 = 53
// 26 + 26 = 52

// 26 + 0


// AY =27 + 24 = 51

// 26 ^ 1 * 1 + 26 ^ 0 * 25
// 26 + 25 = 51

// AB
// (26 * 1 + 1) + (26 * 0 + 2) = 27 + 2 = 29


// CDA

// (26 ^ 2 * 3) + (26 ^ 1 * 4) + (26 ^ 0 * 1)

//  +104+1


// BA -
// BB
// BC
// .
// BZ

// CA
// CB
// CC
// .
// CZ

// ZA
// ZB
// ZC
// .
// ZZ


// AAA
// .
// AAZ




// .
// AA Z 53 + 25 = 78

// 3