package companies.hashicorp.simpledb;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SimpleDBImpl implements SimpleDB {
    Map<String, Integer> database;

    Stack<Map<String, Integer>> snapshots;

    public SimpleDBImpl() {
        database = new HashMap<>();
        snapshots = new Stack<>();
    }

    @Override
    public void Set(String key, Integer value) {
        database.put(key, value);
    }

    @Override
    public Integer Get(String key) {
        if (database.containsKey(key)) {
            return database.get(key);
        }
        return null;
    }

    @Override
    public void Unset(String key) {
        database.remove(key);
    }

    @Override
    public void Begin() {
        Map<String, Integer> snapshot = new HashMap<>(database);
        snapshots.push(snapshot);
    }

    @Override
    public void Commit() throws Exception {
        if (snapshots.isEmpty()) {
            throw new Exception();
        } else {
            snapshots.clear();
        }
    }

    @Override
    public void Rollback() throws Exception {
        if (snapshots.isEmpty()) {
            throw new Exception();
        } else {
            database = snapshots.pop();
        }
    }
}
