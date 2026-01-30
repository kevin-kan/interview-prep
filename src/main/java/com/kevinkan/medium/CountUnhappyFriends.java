package com.kevinkan.medium;

import java.util.HashMap;
import java.util.Map;

/**
* You are given a list of n friends, where n is always even. 
* For each person i, preferences[i] contains a list of all other friends sorted in the order of preference. In other words, a friend earlier in the list is more preferred than a friend later in the list. 
* Friends in each list are denoted by integers from 0 to n-1.
*
* All the friends are divided into pairs. The pairings are given in a 2D integer array pairs where pairs[i] = [xi, yi] denotes that friend xi is paired with friend yi and vice versa.
* However, this pairing may cause some of the friends to be unhappy. A friend x is unhappy if x is paired with y and there exists a friend u who is paired with v but:
* x prefers u over y, and 
* u prefers x over v.
* Return the number of unhappy friends.
* 
* Constraints:
* 2 <= n <= 500
* n is even.
* preferences.length == n
* preferences[i].length == n - 1
* 0 <= preferences[i][j] <= n - 1
* preferences[i] does not contain i.
* All values in preferences[i] are unique.
* pairs.length == n/2
* pairs[i].length == 2
* xi != yi
* 0 <= xi, yi <= n - 1
* Each person is contained in exactly one pair.

*/
public class CountUnhappyFriends {

    /**
     * Friend are unhappy if:
     * - there exists another pair (friendU, friendV) with a higher ranking friend (friendU) in friendX's list, and
     * - friendX is also higher than friendV on friendU's list. 
     * To be able to determine this, we need:
     * - pairs to be able to be searched both ways
     * - a way to quickly check who is higher ranking in the preferences. 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * Surprisingly, this is slower than the brute force O(n^3) solution due to the overhead of HashMaps.
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int unhappy = 0;

        // First convert int[][] pairs into a 2 way searchable map: (O(n/2))
        Map<Integer, Integer> pairsMap = new HashMap<>();
        for (int[] pair : pairs) {
            pairsMap.put(pair[0], pair[1]);
            pairsMap.put(pair[1],pair[0]);
        }

        // Convert Preferences into a ranking map for each friend (O(n * n))
        // Map<Friend, Map<Friend, Ranking>>
        Map<Integer, Map<Integer, Integer>> rankingMap = new HashMap<>();
        for (int i = 0; i < n; i++) {   
            Map<Integer, Integer> friendRanking = new HashMap<>();
            for (int j = 0; j < preferences[i].length; j++) {
                friendRanking.put(preferences[i][j], j);
            }
            rankingMap.put(i, friendRanking);
        }

        // Next we will go through all friends to see if they're unhappy O(n * n)
        for (int i = 0; i < n; i++) { 
            int matchup = pairsMap.get(i);
            for (int preference : preferences[i]) {
                // Check all the preferences of this friend up until the matchup (only higher ranking friends)
                if (preference == matchup) break;
                // if this friend also prefers i over their matchup, we've got an unhappy friend i
                int prefMatchup = pairsMap.get(preference);
                if (rankingMap.get(preference).get(i) < rankingMap.get(preference).get(prefMatchup)) {
                    unhappy += 1;
                    break;
                }

            }
        }

        return unhappy;
    }

     /**
     * Friend are unhappy if:
     * - there exists another pair (friendU, friendV) with a higher ranking friend (friendU) in friendX's list, and
     * - friendX is also higher than friendV on friendU's list. 
     * To be able to determine this, we need:
     * - pairs to be able to be searched both ways
     * Time Complexity: O(n^3)
     * Space Complexity: O(n)
     */
    public int unhappyFriendsNcubed(int n, int[][] preferences, int[][] pairs) {
        int unhappy = 0;
        boolean[] unhappyArray = new boolean[n];

        // First convert int[][] pairs into a 2 way searchable map: (O(n/2))
        Map<Integer, Integer> pairsMap = new HashMap<>();
        for (int[] pair : pairs) {
            pairsMap.put(pair[0], pair[1]);
            pairsMap.put(pair[1],pair[0]);
        }

        // Next we will go through all friends to see if they're unhappy O(n * n * n)
        for (int i = 0; i < n; i++) { 
            int matchup = pairsMap.get(i);
            for (int preference : preferences[i]) {
                // Check all the preferences of this friend up until the matchup (only higher ranking friends)
                if (preference == matchup) break;
                // if this friend also prefers i over their matchup, we've got an unhappy friend i
                int prefMatchup = pairsMap.get(preference);
                // Search the preferences of preference for i until we hit prefMatchup
                for (int prefpref : preferences[preference]) {
                    if (prefpref == prefMatchup) break;
                    else if (prefpref == i) {
                        unhappy += 1;
                        unhappyArray[i] = true;
                        break;
                    } 
                }
                if (unhappyArray[i]) break;
            }
        }

        return unhappy;
    }
}
