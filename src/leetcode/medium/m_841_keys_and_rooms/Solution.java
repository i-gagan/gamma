package leetcode.medium.m_841_keys_and_rooms;

//https://leetcode.com/problems/keys-and-rooms/description/

import java.util.*;

class Solution {
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> setOfRoomsOpenedByKey = new TreeSet<>();
        setOfRoomsOpenedByKey.add(0);
        dfs(rooms.get(0), setOfRoomsOpenedByKey, rooms);
        return setOfRoomsOpenedByKey.size() == rooms.size();
    }

    public static void dfs(List<Integer> keys, Set<Integer> setOfRoomsOpenedByKey, List<List<Integer>> rooms) {
        for (int key : keys) {
            if (setOfRoomsOpenedByKey.add(key)) {
                dfs(rooms.get(key), setOfRoomsOpenedByKey, rooms);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(new ArrayList<>(List.of(1)));
        rooms.add(new ArrayList<>(List.of(2)));
        rooms.add(new ArrayList<>(List.of(3)));
        rooms.add(new ArrayList<>(List.of()));
        System.out.println(rooms);
        System.out.println(canVisitAllRooms(rooms));
    }
}
