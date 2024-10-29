package companies.grab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class UniqueUserGroups {
    private final Map<String, String> parent = new HashMap<>();

    // Find with path compression
    private String find(String node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
        }
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    // Union by setting the parent of one node to another
    private void union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
        }
    }

    // Process the input to find unique groups
    public Set<Set<String>> findUniqueGroups(String[][] input) {
        // Union usernames and emails that belong to the same person
        for (String[] entry : input) {
            String username = entry[0];
            String email = entry[1];

            union(username, email);  // Union username and email ID of each entry
        }

        // Collect each unique group based on their root
        Map<String, Set<String>> uniqueGroups = new HashMap<>();
        for (String node : parent.keySet()) {
            String root = find(node);
            uniqueGroups.computeIfAbsent(root, k -> new HashSet<>()).add(node);
        }

        return new HashSet<>(uniqueGroups.values());
    }

    public static void main(String[] args) {
        String[][] input = {
                {"user1", "user1@gmail.com"},
                {"user2", "user2@gmail.com"},
                {"user3", "user1@gmail.com"}, // Same email as user1
                {"user4", "user2@gmail.com"}, // Same email as user2
                {"user5", "user5@gmail.com"}
        };

        UniqueUserGroups finder = new UniqueUserGroups();
        Set<Set<String>> uniqueGroups = finder.findUniqueGroups(input);

        // Output the unique groups
        System.out.println("Unique groups:");
        for (Set<String> group : uniqueGroups) {
            System.out.println(group);
        }
    }
}
