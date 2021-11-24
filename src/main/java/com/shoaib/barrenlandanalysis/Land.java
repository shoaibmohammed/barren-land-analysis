package com.shoaib.barrenlandanalysis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Land {

    private static final int LAND_WIDTH = 400;
    private static final int LAND_LENGTH = 600;

    private final int[][] land;

    public Land() {
        this.land = new int[LAND_WIDTH][LAND_LENGTH];
    }

    public void markLandAsBarren(Point a, Point b) throws Exception {
        if (isInvalidPoint(a) || isInvalidPoint(b)) {
            throw new Exception("Barren land coordinates must be with in the boundary.");
        }

        for (int i=Math.min(a.x,b.x); i<=Math.max(a.x,b.x); i++) {
            for (int j=Math.min(a.y,b.y); j<=Math.max(a.y,b.y); j++) {
                land[i][j] = -1;
            }
        }
    }

    private boolean isInvalidPoint(Point point) {
        return point.x >= LAND_WIDTH || point.y >= LAND_LENGTH;
    }

    public List<Integer> getFertileLand() {
        List<Integer> areas = new ArrayList<>();
        int nextFill = 1;
        for (int i=0; i<LAND_WIDTH; i++) {
            for (int j=0; j<LAND_LENGTH; j++) {
                if (land[i][j] == 0) {
                    areas.add(seedFill(new Point(i,j), nextFill));
                    nextFill++;
                }
            }
        }
        Collections.sort(areas);
        return areas;
    }

    private int seedFill(Point start, int fill) {

        int prev = land[start.x][start.y];

        if (prev == fill) {
            return 0;
        }
        int area = 0;
        land[start.x][start.y] = fill;
        Stack<Point> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()) {
            Point curr = stack.pop();

            spreadFertile(stack, curr, fill, prev);
            area++;
        }

        return area;
    }

    private void spreadFertile(Stack<Point> stack, Point curr, int fill, int prev) {

        if (needsFill(curr.x-1, curr.y, prev)) {
            land[curr.x-1][curr.y] = fill;
            stack.push(new Point(curr.x-1,curr.y));
        }

        if (needsFill(curr.x+1, curr.y, prev)) {
            land[curr.x+1][curr.y] = fill;
            stack.push(new Point(curr.x+1,curr.y));
        }

        if (needsFill(curr.x, curr.y+1, prev)) {
            land[curr.x][curr.y+1] = fill;
            stack.push(new Point(curr.x,curr.y+1));
        }

        if (needsFill(curr.x, curr.y-1, prev)) {
            land[curr.x][curr.y-1] = fill;
            stack.push(new Point(curr.x,curr.y-1));
        }
    }

    private boolean needsFill(int x, int y, int prev) {
        return x >= 0 && y >= 0 && x < LAND_WIDTH && y < LAND_LENGTH && land[x][y] == prev;
    }
}
