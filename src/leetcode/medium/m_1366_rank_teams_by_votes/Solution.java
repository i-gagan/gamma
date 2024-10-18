package leetcode.medium.m_1366_rank_teams_by_votes;

//https://leetcode.com/problems/rank-teams-by-votes/description/

import java.util.*;

class Team {
    String name;
    int[] votes;

    public Team(String name) {
        this.name = name;
        this.votes = new int[26];
    }

    @Override
    public String toString() {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (votes[i] != 0) {
                count++;
            }
        }
        return name + " " + count + "\n" + Arrays.toString(votes) + "\n";
    }
}

class Solution {
    public static String rankTeams(String[] votes) {
        Map<String, Team> teamMap = new HashMap<>();

        for (String vote : votes) {
            char[] voteChars = vote.toCharArray();
            for (int i = 0; i < voteChars.length; i++) {
                String team = String.valueOf(voteChars[i]);
                if (!teamMap.containsKey(team)) {
                    teamMap.put(team, new Team(team));
                }
                teamMap.get(team).votes[i]++;
            }
        }

        List<Team> teams = new ArrayList<>(teamMap.values());

        teams.sort(new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                for (int i = 0; i < 26; i++) {
                    if (t1.votes[i] != t2.votes[i]) {
                        return t2.votes[i] - t1.votes[i];
                    }
                }
                return t1.name.compareTo(t2.name);
            }
        });

        StringBuilder result = new StringBuilder();
        for (Team team : teams) {
            result.append(team.name);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeams(votes));
    }
}
