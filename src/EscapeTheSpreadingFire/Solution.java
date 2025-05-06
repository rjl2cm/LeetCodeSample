package EscapeTheSpreadingFire;

// 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：
//
//0 表示草地。
//1 表示着火的格子。
//2 表示一座墙，你跟火都不能通过这个格子。
//一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。
//
//请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 -1 。如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。
//
//注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
//
//如果两个格子有共同边，那么它们为 相邻 格子。

// https://leetcode-cn.com/contest/biweekly-contest-77/problems/escape-the-spreading-fire/

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        
        int[][] grid =
                {
                {0,2,0,0,0,0,0},
                {0,0,0,2,2,1,0},
                {0,2,0,0,1,2,0},
                {0,0,2,2,2,0,2},
                {0,0,0,0,0,0,0}
                };
        System.out.println(maximumMinutes(grid));

    }

    public static int maximumMinutes(int[][] grid) {
        int time = 0;
        while(canRunAfter(grid.clone(), time++)) {
            System.out.println("延迟 " + time +"s : 仍可通过。");
        }
        return time - 1;
    }

    private static boolean canRunAfter(int[][] grid, int time) {
        if (time < 0 ) return false;
        while (time-- > 0) {
            burn(grid);
        }
        List<Pair<Integer, Integer>> path = new ArrayList<>();
        // 从(0,0)出发
        return runStep(grid, 0, 0, path);
    }

    private static boolean runStep(int[][] grid, int i, int j, List<Pair<Integer, Integer>> path) {
        // 抵达终点
        if (i == (grid.length - 1) && j == (grid[0].length - 1)) {
            return true;
        }
        // 当前位置已着火
        if (grid[i][j] == 1) return false;
        boolean flag = false;
        List<Pair<Integer, Integer>> newFires = null;
        // 左
        if (i - 1 > 0 && !path.contains(new Pair<>(i - 1, j)) && grid[i-1][j] == 0) {
            newFires = burn(grid);
            path.add(new Pair<>(i - 1, j));
            if (runStep(grid, i - 1, j, path)) {
                flag = true;
            }
        }
        // 上
        if (j - 1 > 0 && !path.contains(new Pair<>(i, j - 1)) && grid[i][j-1] == 0) {
            newFires = burn(grid);
            path.add(new Pair<>(i, j - 1));
            if (runStep(grid, i, j - 1, path)) {
                flag = true;
            }
        }
        // 右
        if (j + 1 < grid[0].length && !path.contains(new Pair<>(i, j + 1)) && grid[i][j + 1] == 0) {
            newFires = burn(grid);
            path.add(new Pair<>(i, j + 1));
            if (runStep(grid, i, j + 1, path)) {
                flag = true;
            }
        }
        // 下
        if (i + 1 < grid.length && !path.contains(new Pair<>(i + 1, j))
                && grid[i + 1][j] == 0) {
            newFires = burn(grid);
            path.add(new Pair<>(i + 1, j));
            if (runStep(grid, i + 1, j, path)) flag = true;
        }

        // 如果此路不通
        if (!flag) {
            resetGrid(grid, newFires);
            path.remove(path.remove(path.size() - 1));
        }
        return flag;
    }

    private static void resetGrid(int[][] grid, List<Pair<Integer, Integer>> newFires) {
        if (newFires == null || newFires.size() == 0) return;
        for (Pair<Integer, Integer> fire: newFires) {
            grid[fire.getKey()][fire.getValue()] = 0;
        }
    }

    private static List<Pair<Integer, Integer>> burn(int[][] grid) {
        List<Pair<Integer, Integer>> fires = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) fires.add(new Pair(i, j));
            }
        }
        List<Pair<Integer, Integer>> newFires = new ArrayList<>();
        for (Pair<Integer, Integer> pair : fires) {
            int i = pair.getKey();
            int j = pair.getValue();
            // 左
            if (i - 1 >= 0 && grid[i-1][j] == 0) {
                grid[i-1][j] = 1;
                newFires.add(new Pair<>(i-1, j));
            }
            // 上
            if (j - 1 >= 0 && grid[i][j-1] == 0) {
                grid[i][j-1] = 1;
                newFires.add(new Pair<>(i, j-1));
            }
            // 右
            if (j + 1 < grid[0].length && grid[i][j+1] == 0) {
                grid[i][j+1] = 1;
                newFires.add(new Pair<>(i, j+1));
            }
            // 下
            if (i + 1 < grid.length && grid[i+1][j] == 0) {
                grid[i+1][j] = 1;
                newFires.add(new Pair<>(i+1, j));
            }
        }
        return newFires;
    }
}