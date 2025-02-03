package companies.confluent.restaurant;

import java.util.*;

class RestaurantOrderSimplified {

    // Find the best price for the items the user wants
    public static double findBestPrice(List<String[]> menu, List<String> userWants) {
        double cheapestPrice = Double.MAX_VALUE;

        // Generate all combinations of menu items
        int menuSize = menu.size();

        // Iterate over all subsets of menu items using their indices
        for (int subset = 0; subset < Math.pow(2, menuSize); subset++) {
            Set<String> itemsCovered = new HashSet<>();
            double totalPrice = 0.0;

            // Include items in the current subset
            for (int i = 0; i < menuSize; i++) {
                if (isIndexIncluded(subset, i)) { // Check if the current menu item is included
                    String[] menuItem = menu.get(i);
                    totalPrice += Double.parseDouble(menuItem[0]); // Add its price
                    itemsCovered.addAll(Arrays.asList(menuItem[1].split(", "))); // Add its items
                }
            }

            // Check if this combination satisfies all user wants
            if (itemsCovered.containsAll(userWants)) {
                cheapestPrice = Math.min(cheapestPrice, totalPrice);
            }
        }

        return (cheapestPrice == Double.MAX_VALUE) ? -1 : cheapestPrice;
    }

    // Helper function to check if an item is included in the current subset
    public static boolean isIndexIncluded(int subset, int index) {
        // Divide subset by 2^index and check if the remainder is 1
        return (subset / (int) Math.pow(2, index)) % 2 == 1;
    }

    public static void main(String[] args) {
        // Example menu (price, items)
        List<String[]> menu = Arrays.asList(
                new String[]{"5.00", "pizza"},
                new String[]{"8.00", "sandwich, coke"},
                new String[]{"4.00", "pasta"},
                new String[]{"2.00", "coke"},
                new String[]{"6.00", "pasta, coke, pizza"},
                new String[]{"8.00", "burger, coke, pizza"},
                new String[]{"5.00", "sandwich"}
        );

        // What the user wants to order
        List<String> userWants = Arrays.asList("burger", "pasta");

        // Find and display the best price
        double bestPrice = findBestPrice(menu, userWants);
        if (bestPrice == -1) {
            System.out.println("Sorry, there's no way to order all the items you want.");
        } else {
            System.out.printf("The cheapest price for your order is: $%.2f%n", bestPrice);
        }
    }
}
