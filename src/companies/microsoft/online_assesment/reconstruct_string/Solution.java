package companies.microsoft.online_assesment.reconstruct_string;

import java.util.*;

class Position {
    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Result {
    int minMoves;

    public Result() {
        this.minMoves = Integer.MAX_VALUE;
    }
}

class Solution {
    public static int solution(String target, String[] grid) {
        Map<Character, List<Position>> charPositions = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                char c = grid[i].charAt(j);
                if (c != '.') {
                    charPositions.putIfAbsent(c, new ArrayList<>());
                    charPositions.get(c).add(new Position(i, j));
                }
            }
        }

        for (char c : target.toCharArray()) {
            if (!charPositions.containsKey(c)) {
                return -1;
            }
        }

        Result result = new Result();
        dfs(target, charPositions, 0, 0, null, result);

        return result.minMoves == Integer.MAX_VALUE ? -1 : result.minMoves;
    }

    private static void dfs(String target, Map<Character, List<Position>> charPositions,
                            int steps, int index, Position lastPosition, Result result) {
        if (index == target.length()) {
            result.minMoves = Math.min(result.minMoves, steps);
            return;
        }

        if (steps >= result.minMoves) {
            return;
        }

        char currentChar = target.charAt(index);

        for (Position position : charPositions.get(currentChar)) {
            int x = position.x;
            int y = position.y;

            int manhattenDistance = (lastPosition == null) ? 0 : Math.abs(lastPosition.x - x) + Math.abs(lastPosition.y - y);

            dfs(target, charPositions, steps + manhattenDistance, index + 1, position, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("ABCA", new String[]{".A.C", ".B..", "....", "...A"}));
        System.out.println(solution("KLLRML", new String[]{"K....", "S...L", "....R", "LX...", "XM..S"}));
        System.out.println(solution("XZZY", new String[]{".Z.", "XBB", "..A"}));
    }
}

