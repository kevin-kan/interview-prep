package com.kevinkan.hard;

/**
* On the sea represented by a cartesian plane, each ship is located at an integer point, and each integer point may contain at most one ship.
* You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true if there is at least one ship in the rectangle defined by the two points, including on the boundary.
* Given two points representing the top-right and bottom-left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are at most 10 ships in that rectangle.
*
* Constraints:
* Submissions making more than 400 calls to hasShips will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
* 0 <= bottomLeft[0] <= topRight[0] <= 1000
* 0 <= bottomLeft[1] <= topRight[1] <= 1000
*/
public class NumberOfShipsInARectangle {    // Sea Class
    boolean[][] sea;

    public NumberOfShipsInARectangle() {
        this.sea = new boolean[1001][1001];
    }

    public NumberOfShipsInARectangle(int[][] ships) {
        this();
        setShips(ships);
    }
    
    public NumberOfShipsInARectangle(boolean[][] sea) {
        this.sea = sea;
    }

    private void setShips(int[][] ships) {
        for (int[] coordinate : ships) {
            this.sea[coordinate[0]][coordinate[1]] = true;
        }
    }

    /**
     * Determines if there are ships in the rectangle defined by topRight and bottomLeft points.
     * @param topRight The top-right coordinate of the rectangle.
     * @param bottomLeft The bottom-left coordinate of the rectangle.
     * @return true if there is at least one ship in the rectangle, false otherwise.
     */
    public boolean hasShips(int[] topRight, int[] bottomLeft) {
        for (int i = bottomLeft[0]; i <= topRight[0]; i++) {
            for (int j = bottomLeft[1]; j <= topRight[1]; j++) {
                if (sea[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Counts the number of ships in the rectangle defined by topRight and bottomLeft points without exceeding the allowed number of hasShips calls.
     * If we perform this recursively, dividing into quadrants, we can efficiently count the (at most 10) ships.
     * The worst-case scenario for calls to hasShips is when all ships are distributed such that each quadrant always contains ships until we reach single points.
     * 1000 -> 500 -> 250 -> 125 -> 62 -> 31 -> 15 -> 7 -> 3 -> 1 (10 calls)
     * 4 quadrants times 10 calls times 10 ships = 400 calls max.
     */
    public int getNumShips(int[] topRight, int[] bottomLeft) {
        // Validate Coordinates and Rectangle
        if (!isRectangleValid(topRight, bottomLeft)) {
            throw new IllegalArgumentException("Provided Corner Coordinates are Invalid");
        }

        // Check if there are any ships in the current rectangle
        if (!hasShips(topRight, bottomLeft)) {
            return 0;
        }

        // Base case: single point
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            return 1;
        }

        int centerX = (topRight[0] + bottomLeft[0]) / 2;
        int centerY = (topRight[1] + bottomLeft[1]) / 2;

        // Recursively count ships in the four quadrants
        return getNumShips(new int[]{centerX, topRight[1]}, new int[]{bottomLeft[0], centerY + 1}) + // Top left quadrant
            getNumShips(topRight, new int[]{centerX + 1, centerY + 1}) + // top right quadrant
            getNumShips(new int[]{centerX, centerY}, bottomLeft) + // bottom left quadrant
            getNumShips(new int[]{topRight[0], centerY}, new int[]{centerX + 1, bottomLeft[1]}); // bottom right quadrant

    }

    /**
     * Helper method to validate coordinate points. Must be of length 2 and within [0, 1000].
     */
    private boolean isCoordinateValid(int[] coordinate) {
        return coordinate.length == 2 && 
            coordinate[0] >= 0 && coordinate[0] <= 1000 && 
            coordinate[1] >= 0 && coordinate[1] <= 1000;
    }

    /**
     * Helper method to validate rectangle defined by topRight and bottomLeft points.
     * Must have topRight coordinates greater than or equal to bottomLeft coordinates.
     */
    private boolean isRectangleValid(int[] topRight, int[] bottomLeft) {
        
        return isCoordinateValid(topRight) && isCoordinateValid(bottomLeft) &&
            topRight[0] >= bottomLeft[0] && topRight[1] >= bottomLeft[1];
    }

}
