package com.kevinkan.medium;

import java.util.Arrays;

/**
* A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the i-th person to city A is aCosti, and the cost of flying the i-th person to city B is bCosti.
* Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
* 
* Constraints:
* 2n == costs.length
* 2 <= costs.length <= 100
* costs.length is even.
* 1 <= aCosti, bCosti <= 1000
*/
public class TwoCityScheduling {

    /**
     * There are two cities: A and B.
     * We have 2n people to be flown to these two cities, with exactly n people going to each city.
     * Each person has a cost associated with flying to each city and we want to minimize the total cost.
     * 
     * Time Complexity: O(nlogn) due to sorting the costs array.
     * Space Complexity: O(1) as we are sorting in place.
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (personX, personY) -> {
            // Sort by the difference in costs between city A and city B
            // The idea is to prioritize sending people to the city where the cost difference is greatest
            int diffX = personX[0] - personX[1]; 
            int diffY = personY[0] - personY[1];
            // cost to city A - cost to city B -> positive means city B is cheaper
            return Integer.compare(diffX, diffY);
        });

        // Integer.compare sorted the costs in ascending order
        // more negative difference means city A is cheaper for that person
        // So we can just choose A for the first n people and B for the last n people
        int total = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            total += costs[i][0] + costs[i + costs.length / 2][1];
        }
        return total;
    }
}
