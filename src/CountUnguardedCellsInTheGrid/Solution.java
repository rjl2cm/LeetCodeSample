package CountUnguardedCellsInTheGrid;

// 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。同时给你两个二维整数数组 guards 和 walls ，其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，分别表示第 i 个警卫和第 j 座墙所在的位置。
//
//一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，除非他们被一座墙或者另外一个警卫 挡住 了视线。如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。
//
//请你返回空格子中，有多少个格子是 没被保卫 的。

// https://leetcode-cn.com/contest/biweekly-contest-77/problems/count-unguarded-cells-in-the-grid/

import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public static void main(String[] args) {
        countUnguarded(4,6, new int[][] {{0,0}, {1,1}, {2,3}}, new int[][] {{0,1}, {2,2}, {1,4}});
//        countUnguarded(2,7, new int[][] {{1,5}, {1,1}, {1,6}, {0,2}}, new int[][] {{0,6}, {0,3}, {0,5}});
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] chessboard = new char[m][n];
        // init chessboard
        for (int i = 0; i < guards.length; i++) {
            chessboard[guards[i][0]][guards[i][1]] = 'G';
        }
        for (int i = 0; i < walls.length; i++) {
            chessboard[walls[i][0]][walls[i][1]] = 'W';
        }
        int pCount = 0;
        // 逐个检测guard的行与列
        for (int i = 0; i < guards.length; i++) {
            int[] guard = guards[i];
            // 行号
            int row = guard[0];
            int col = guard[1];
            // 行检测
            for (int j = col - 1; j >= 0; j--) {
                // 往左check
                if (chessboard[row][j] == 'W') break;
                if (chessboard[row][j] == 'G') break;
                if (chessboard[row][j] == 'P') continue;
                chessboard[row][j] = 'P';
                pCount++;
            }
            for (int j = col + 1; j < n; j++) {
                // 往右check
                if (chessboard[row][j] == 'W') break;
                if (chessboard[row][j] == 'G') break;
                if (chessboard[row][j] == 'P') continue;
                chessboard[row][j] = 'P';
                pCount++;
            }
            // 列检测
            for (int j = row - 1; j >= 0; j--) {
                // 往上check
                if (chessboard[j][col] == 'W') break;
                if (chessboard[j][col] == 'G') break;
                if (chessboard[j][col] == 'P') continue;
                chessboard[j][col] = 'P';
                pCount++;
            }
            for (int j = row + 1; j < m; j++) {
                // 往下check
                if (chessboard[j][col] == 'W') break;
                if (chessboard[j][col] == 'G') break;
                if (chessboard[j][col] == 'P') continue;
                chessboard[j][col] = 'P';
                pCount++;
            }
        }
        return m*n - guards.length - walls.length - pCount;
    }
}