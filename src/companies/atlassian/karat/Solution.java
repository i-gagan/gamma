package companies.atlassian.karat;

/*
You are with your friends in a castle, where there are multiple rooms named after flowers. Some of the rooms contain treasures - we call them the treasure rooms.

Each room contains a single instruction that tells you which room to go to next.

 *** instructions_1 and treasure_rooms_1 ***

 lily* ---------      daisy  sunflower
               |        |     |
               v        v     v
 jasmin --> tulip*      violet* ----> rose* -->
            ^    |      ^             ^       |
            |    |      |             |       |
            ------    iris            ---------

* denotes a treasure room, e.g., rose is a treasure room, but jasmin isn't.

This is given as a list of pairs of (source_room, destination_room)

instructions_1 = [
    ["jasmin", "tulip"],
    ["lily", "tulip"],
    ["tulip", "tulip"],
    ["rose", "rose"],
    ["violet", "rose"],
    ["sunflower", "violet"],
    ["daisy", "violet"],
    ["iris", "violet"]
]

treasure_rooms_1 = ["lily", "tulip", "violet", "rose"]

Write a function that takes two parameters as input:
* a list of instructions represented as pairs of (source_room, destination_room), and
* a list containing the treasure rooms,

and returns a collection of all the rooms that satisfy the following two conditions:
* at least two *other* rooms have instructions pointing to this room
* this room's instruction immediately points to a treasure room

filter_rooms(instructions_1, treasure_rooms_1) => ["tulip", "violet"]
* tulip can be accessed from rooms lily and jasmin. Tulip's instruction points to a treasure room (tulip itself)
* violet can be accessed from daisy, sunflower and iris. Violet's instruction points to a treasure room (rose)

Additional inputs

treasure_rooms_2 = ["lily", "jasmin", "violet"]

filter_rooms(instructions_1, treasure_rooms_2) => []
* none of the rooms reachable from tulip or violet are treasure rooms

 *** instructions_2 and treasure_rooms_3 ***

 lily ---------          --------
              |          |      |
              v          v      |
 jasmin --> tulip ---> violet*--^

instructions_2 = [
    ["jasmin", "tulip"],
    ["lily", "tulip"],
    ["tulip", "violet"],
    ["violet", "violet"]
]

treasure_rooms_3 = ["violet"]

filter_rooms(instructions_2, treasure_rooms_3) => [tulip]
* tulip can be accessed from rooms lily and jasmin. Tulip's instruction points to a treasure room (violet)

All the test cases:
filter_rooms(instructions_1, treasure_rooms_1)    => ["tulip", "violet"]
filter_rooms(instructions_1, treasure_rooms_2)    => []
filter_rooms(instructions_2, treasure_rooms_3)    => [tulip]

Complexity Analysis variables:
T: number of treasure rooms
I: number of instructions given
*/

import java.util.*;

class Solution {
    public static void main(String[] argv) {
        String[][] instructions_1 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "tulip"},
                {"rose", "rose"},
                {"violet", "rose"},
                {"sunflower", "violet"},
                {"daisy", "violet"},
                {"iris", "violet"}
        };

        String[][] instructions_2 = {
                {"jasmin", "tulip"},
                {"lily", "tulip"},
                {"tulip", "violet"},
                {"violet", "violet"}
        };

        String[] treasure_rooms_1 = {"lily", "tulip", "violet", "rose"};
        String[] treasure_rooms_2 = {"lily", "jasmin", "violet"};
        String[] treasure_rooms_3 = {"violet"};


        List<String> result1 = filter_rooms(instructions_1, treasure_rooms_1);
        System.out.println(result1);

        List<String> result2 = filter_rooms(instructions_1, treasure_rooms_2);
        System.out.println(result2);

        List<String> result3 = filter_rooms(instructions_2, treasure_rooms_3);
        System.out.println(result3);

    }

    public static List<String> filter_rooms(String[][] instructions, String[] treasure_rooms) {
        List<String> result = new ArrayList<>();

        //Key Destination, Value - Set of Sources
        Map<String, Set<String>> hashMap = new HashMap<>();  // O(I)
        //==2Bytes * <Set> No * Rooms * 2. Bytes

        for (String[] instruction : instructions) {
            String source = instruction[0];
            String destination = instruction[1];
            if (hashMap.containsKey(destination)) {
                hashMap.get(destination).add(source);
            } else {
                hashMap.put(destination, new HashSet<>());
                hashMap.get(destination).add(source);
            }
        }

        for (String treasure_room : treasure_rooms) { // O(T) * O (1)
            if (hashMap.containsKey(treasure_room)) {
                Set<String> roomsToTarget = hashMap.get(treasure_room);
                for (String roomToTarget : roomsToTarget) {
                    if (hashMap.containsKey(roomToTarget)) {
                        Set<String> sourceRooms = hashMap.get(roomToTarget);
                        int noOfRoomsReachableToSourceRoom = sourceRooms.size();
                        if (sourceRooms.contains(roomToTarget)) {
                            noOfRoomsReachableToSourceRoom--;
                        }
                        if (noOfRoomsReachableToSourceRoom >= 2) {
                            result.add(roomToTarget);
                        }
                    }
                }
            }
        }

        return result;
    }

    // public static List<String> filter_rooms(String[][] instructions, String[] treasure_rooms)  {
    //    List<String> result = new ArrayList<>();

    //    //Key Destination, Value - List of Sources
    //    Map<String, List<String>> hashMap = new HashMap<>();  // O(I)

    //    for (int i = 0; i < instructions.length; i++) {
    //      String[] instruction = instructions[i];
    //      String source = instruction[0];
    //      String destination = instruction[1];
    //      if (hashMap.containsKey(destination)) {
    //        hashMap.get(destination).add(source);
    //      } else {
    //        hashMap.put(destination, new ArrayList<>());
    //        hashMap.get(destination).add(source);
    //      }
    //    }

    //     for (int i = 0; i < treasure_rooms.length; i++) { // O(T) * O (N)
    //       String treasure_room = treasure_rooms[i];

    //       if (hashMap.containsKey(treasure_room)) {
    //         List<String> sourceRoomsList = hashMap.get(treasure_room);
    //         for (int j = 0; j < sourceRoomsList.size(); j++) {
    //           String sourceRoom = sourceRoomsList.get(j);
    //           if (hashMap.containsKey(sourceRoom)) {
    //             List<String> internalSourceRoomList = hashMap.get(sourceRoom);
    //             int count = 0;
    //             for (int k = 0; k < internalSourceRoomList.size(); k++) {
    //               if (!internalSourceRoomList.get(k).equals(sourceRoom)) {
    //                 count++;
    //               }
    //             }
    //             if (count >= 2) {
    //               result.add(sourceRoom);
    //             }
    //           }
    //         }
    //       } else {
    //         continue;
    //       }
    //     }

    //   return result;
    // }
}
