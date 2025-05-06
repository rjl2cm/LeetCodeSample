package pacificAtlanticWaterFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。“太平洋”处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
//
//这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵heights，heights[r][c]表示坐标 (r, c) 上单元格 高于海平面的高度 。
//
//岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
//
//返回网格坐标 result的 2D 列表 ，其中result[i] = [ri, ci]表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public static void main(String[] args) {
        int[][] isLand = new int[][] {
        {1,2,2,3,5},
        {3,2,3,4,4},
        {2,4,5,3,1},
        {6,7,1,4,5},
        {5,1,1,2,4}
        };
    }

    public int countPrefixes(String[] words, String s) {
        int count = 0;
        for(int i = 0, size = words.length; i < size; i++) {
            String word = words[i];
            if(word.equals(s.substring(0, word.length()))) count++;
        }
        return count;
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 有1表明可抵达Pacific、有2表明可抵达Atlantic，0标示未计算
        int[][] state = new int[heights.length][heights[0].length];
        int rSize = heights.length;
        int iSize = heights[0].length;
        // 首行可达太平洋
        for (int i = 0; i < iSize; i++) {
            updateMark(heights, state, 0, i, 1);
        }
        // 首列可达太平洋
        for (int r = 0; r < rSize; r++) {
            updateMark(heights, state, r, 0, 1);
        }
        // 末行可达大西洋
        for (int i = 0; i < iSize; i++) {
            updateMark(heights, state, rSize - 1, i, 2);
        }
        // 末列可达太平洋
        for (int r = 0; r < rSize; r++) {
            updateMark(heights, state, r, iSize - 1, 2);
        }
        // 存储结果
        List answer = new ArrayList<List>();
        for(int r = 0; r < rSize; r++) {
            for(int i = 0; i < iSize; i++) {
                int temp = state[r][i];
                int flag = 1 | 2;
                if((temp & flag) == flag) {
                    List list = new ArrayList<Integer>();
                    list.add(r);
                    list.add(i);
                    answer.add(list);
                }
            }
        }
        return answer;
    }

    private void updateMark(int[][] heights, int[][] state, int r, int i, int pFlag) {
        // 如果已经有标记
        if ((state[r][i] & pFlag) == pFlag) return;
        state[r][i] |= pFlag;
        // 上
        if(invalid(state, r-1, i, pFlag) && heights[r-1][i] >= heights[r][i]) {
            updateMark(heights, state, r-1, i, pFlag);
        }
        // 下
        if(invalid(state, r+1, i, pFlag) && heights[r+1][i] >= heights[r][i]) {
            updateMark(heights, state, r+1, i, pFlag);
        }
        // 左
        if(invalid(state, r, i-1, pFlag) && heights[r][i-1] >= heights[r][i]) {
            updateMark(heights, state, r, i-1, pFlag);
        }
        // 右
        if(invalid(state, r, i+1, pFlag) && heights[r][i+1] >= heights[r][i]) {
            updateMark(heights, state, r, i+1, pFlag);
        }
    }

    private boolean invalid(int[][] state, int r, int i, int pFlag) {
        if (r >= 0 && r < state.length &&
                i >= 0 && i < state[0].length) {
            // 位置有效，且暂无记录
            if ((state[r][i] & pFlag) != pFlag) {
                return true;
            }
        }
        return false;
    }

    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        // 有1表明可抵达Pacific、有2表明可抵达Atlantic，0标示未计算
        int[][] state = new int[heights.length][heights[0].length];
        int stateFlowToP = 1;
        int stateFlowToA = 2;
        final int rSize = heights.length;
        final int iSize = heights[0].length;
        // 存储结果
        List answer = new ArrayList<List>();
        for(int r = 0; r < rSize; r++) {
            for(int i = 0; i < iSize; i++) {
                if(recurrenceCalc(heights, state, r, i, true) && recurrenceCalc(heights, state, r, i, false)) {
                    List list = new ArrayList<Integer>();
                    list.add(r);
                    list.add(i);
                    answer.add(list);
                }
            }
        }
        return answer;
    }

    private boolean recurrenceCalc(int[][] heights, int[][] state, int r, int i, boolean flowToPacificOcean) {
        if (flowToPacificOcean) {
            if ((state[r][i] & 1) == 1) return true;
            // 左上
            if (r == 0 || i == 0) {
                state[r][i] |= 1;
                return true;
            }
            // 上
            if (heights[r][i] >= heights[r-1][i] && recurrenceCalc(heights, state, r-1, i, true)) {
                state[r][i] |= 1;
                return true;
            }
            // 下
            if (r + 1 < heights.length && heights[r][i] >= heights[r+1][i] && recurrenceCalc(heights, state, r+1, i, true)) {
                state[r][i] |= 1;
                return true;
            }
            // 左
            if (heights[r][i] >= heights[r][i-1] && recurrenceCalc(heights, state, r, i-1, true)) {
                state[r][i] |= 1;
                return true;
            }
            // 右
            if (i + 1 < heights[0].length && heights[r][i] >= heights[r][i+1] && recurrenceCalc(heights, state, r, i+1, true)) {
                state[r][i] |= 1;
                return true;
            }
        } else {
            if ((state[r][i] & 2) == 2) return true;
            // 右下
            if (r == heights.length - 1 || i == heights[0].length - 1) {
                state[r][i] |= 2;
                return true;
            }
            // 上
            if (r - 1 >= 0 && heights[r][i] >= heights[r-1][i] && recurrenceCalc(heights, state, r-1, i, false)) {
                state[r][i] |= 2;
                return true;
            }
            // 下
            if (heights[r][i] >= heights[r+1][i] && recurrenceCalc(heights, state, r+1, i, false)) {
                state[r][i] |= 2;
                return true;
            }
            // 左
            if (i - 1 >= 0 && heights[r][i] >= heights[r][i-1] && recurrenceCalc(heights, state, r, i-1, false)) {
                state[r][i] |= 2;
                return true;
            }
            // 右
            if (heights[r][i] >= heights[r][i+1] && recurrenceCalc(heights, state, r, i+1, false)) {
                state[r][i] |= 2;
                return true;
            }
        }
        return false;
    }


    public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        // 有1表明可抵达Pacific、有2表明可抵达Atlantic，0标示未计算
        int[][] state = new int[heights.length][heights[0].length];
        int stateFlowToP = 1;
        int stateFlowToA = 2;
        // 判断能否到达 PacificOcean
        final int rSize = heights.length;
        final int iSize = heights[0].length;
        for(int r = 0; r < rSize; r++) {
            for(int i = 0; i < iSize; i++) {
                if(r == 0 || i == 0) {
                    state[r][i] |= stateFlowToP;
                    continue;
                }
                if((state[r-1][i] & stateFlowToP) == stateFlowToP
                        && heights[r][i] >= heights[r-1][i]) {
                    state[r][i] |= stateFlowToP;
                    continue;
                }
                if((state[r][i-1] & stateFlowToP) == stateFlowToP
                        && heights[r][i] >= heights[r][i-1]) {
                    state[r][i] |= stateFlowToP;
                    continue;
                }
            }
        }

        // 判断能否到达 AtlanticOcean
        for(int r = rSize - 1; r >= 0; r--) {
            for(int i = iSize - 1; i >= 0; i--) {
                if(r == rSize - 1 || i == iSize - 1) {
                    state[r][i] |= stateFlowToA;
                    continue;
                }
                if((state[r+1][i] & stateFlowToA) == stateFlowToA
                        && heights[r][i] >= heights[r+1][i]) {
                    state[r][i] |= stateFlowToA;
                    continue;
                }
                if((state[r][i+1] & stateFlowToA) == stateFlowToA
                        && heights[r][i] >= heights[r][i+1]) {
                    state[r][i] |= stateFlowToA;
                    continue;
                }
            }
        }
        // 存储结果
        List answer = new ArrayList<List>();
        for(int r = 0; r < rSize; r++) {
            for(int i = 0; i < iSize; i++) {
                int tempState = state[r][i];
                if((tempState & stateFlowToA) == stateFlowToA
                        && (tempState & stateFlowToP) == stateFlowToP) {
                    List list = new ArrayList<Integer>();
                    list.add(r);
                    list.add(i);
                    answer.add(list);
                }
            }
        }
        return answer;
    }
}
