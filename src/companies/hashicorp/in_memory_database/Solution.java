package companies.hashicorp.in_memory_database;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class InMemoryDatabase {
    private Map<String, String> database;
    private final Stack<Map<String, String>> transactionStack;

    public InMemoryDatabase() {
        database = new HashMap<>();
        transactionStack = new Stack<>();
    }

    public void beginTransaction() {
        Map<String, String> databaseSnapshot = new HashMap<>(database);
        transactionStack.push(databaseSnapshot);
        System.out.println("Transaction started.");
    }

    public void commit() {
        if (transactionStack.isEmpty()) {
            System.out.println("No active transaction to commit.");
        } else {
            transactionStack.clear();
            System.out.println("Transaction committed.");
        }
    }

    public void rollback() {
        if (transactionStack.isEmpty()) {
            System.out.println("No active transaction to rollback.");
        } else {
            database = transactionStack.pop();
            System.out.println("Transaction rolled back.");
        }
    }

    public void set(String key, String value) {
        database.put(key, value);
        System.out.println("Set: " + key + " = " + value);
    }

    public String get(String key) {
        return database.getOrDefault(key, "null");
    }

    public void delete(String key) {
        database.remove(key);
        System.out.println("Deleted: " + key);
    }

    public void printDatabase() {
        System.out.println("Current Database: " + database);
    }
}

class Solution {
    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();

        db.set("name", "Alice");
        db.set("age", "30");

        db.printDatabase();

        db.beginTransaction();
        db.set("name", "Bob");
        db.printDatabase();

        db.rollback();
        db.printDatabase();

        db.beginTransaction();
        db.set("name", "Charlie");
        //db.set("age", "10");
        db.delete("age");
        db.printDatabase();
        db.rollback();
        db.commit();

        db.printDatabase();
    }
}
