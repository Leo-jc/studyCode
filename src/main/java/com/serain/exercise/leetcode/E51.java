package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise
 * @Author: Serain
 * @CreateTime: 2026-03-14  21:21
 * @Description: N 皇后问题 - 在 n×n 的棋盘上放置 n 个皇后，使得它们不能相互攻击
 * @Version: 1.0
 */
public class E51 {
    /**
     * 求解 N 皇后问题
     * @param n 皇后的数量，也是棋盘的大小 (n×n)
     * @return 所有可行的解决方案列表
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queenPositions = new int[n]; // queens[row] = col 表示第 row 行的皇后放在第 col 列
        boolean[] occupiedCols = new boolean[n]; // 标记已占用的列
        boolean[] occupiedDiag1 = new boolean[2 * n - 1]; // 标记已占用的主对角线 (左上到右下)
        boolean[] occupiedDiag2 = new boolean[2 * n - 1]; // 标记已占用的副对角线 (右上到左下)
        
        backtrack(0, n, queenPositions, occupiedCols, occupiedDiag1, occupiedDiag2, solutions);
        return solutions;
    }

    /**
     * 回溯算法核心方法
     * @param currentRow 当前处理的行号
     * @param boardSize 棋盘大小
     * @param queenPositions 皇后位置数组
     * @param occupiedCols 列占用标记数组
     * @param occupiedDiag1 主对角线占用标记数组
     * @param occupiedDiag2 副对角线占用标记数组
     * @param solutions 存储所有解决方案的结果列表
     */
    private void backtrack(int currentRow, int boardSize, int[] queenPositions, 
                          boolean[] occupiedCols, boolean[] occupiedDiag1, 
                          boolean[] occupiedDiag2, List<List<String>> solutions) {
        // 基础情况：如果所有行都已成功放置皇后，则找到一个解
        if (currentRow == boardSize) {
            solutions.add(buildBoard(queenPositions, boardSize));
            return;
        }
        
        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < boardSize; col++) {
            int diag1Index = currentRow + col; // 主对角线索引 (r+c)
            int diag2Index = currentRow - col + boardSize - 1; // 副对角线索引 (r-c+n-1)
            
            // 检查当前位置是否可以放置皇后
            if (canPlaceQueen(col, diag1Index, diag2Index, occupiedCols, occupiedDiag1, occupiedDiag2)) {
                placeQueen(currentRow, col, diag1Index, diag2Index, queenPositions, 
                          occupiedCols, occupiedDiag1, occupiedDiag2);
                
                // 递归处理下一行
                backtrack(currentRow + 1, boardSize, queenPositions, occupiedCols, occupiedDiag1, occupiedDiag2, solutions);
                
                // 回溯：撤销当前选择，恢复状态
                removeQueen(col, diag1Index, diag2Index, occupiedCols, occupiedDiag1, occupiedDiag2);
            }
        }
    }

    /**
     * 检查是否可以在指定位置放置皇后
     * @param col 列索引
     * @param diag1 主对角线索引
     * @param diag2 副对角线索引
     * @param occupiedCols 列占用标记数组
     * @param occupiedDiag1 主对角线占用标记数组
     * @param occupiedDiag2 副对角线占用标记数组
     * @return 如果可以放置返回 true，否则返回 false
     */
    private boolean canPlaceQueen(int col, int diag1, int diag2, 
                                  boolean[] occupiedCols, boolean[] occupiedDiag1, 
                                  boolean[] occupiedDiag2) {
        return !occupiedCols[col] && !occupiedDiag1[diag1] && !occupiedDiag2[diag2];
    }

    /**
     * 放置皇后并标记占用的位置
     * @param row 行索引
     * @param col 列索引
     * @param diag1 主对角线索引
     * @param diag2 副对角线索引
     * @param queenPositions 皇后位置数组
     * @param occupiedCols 列占用标记数组
     * @param occupiedDiag1 主对角线占用标记数组
     * @param occupiedDiag2 副对角线占用标记数组
     */
    private void placeQueen(int row, int col, int diag1, int diag2, 
                           int[] queenPositions, boolean[] occupiedCols, 
                           boolean[] occupiedDiag1, boolean[] occupiedDiag2) {
        queenPositions[row] = col;
        occupiedCols[col] = true;
        occupiedDiag1[diag1] = true;
        occupiedDiag2[diag2] = true;
    }

    /**
     * 移除皇后并恢复位置标记
     * @param col 列索引
     * @param diag1 主对角线索引
     * @param diag2 副对角线索引
     * @param occupiedCols 列占用标记数组
     * @param occupiedDiag1 主对角线占用标记数组
     * @param occupiedDiag2 副对角线占用标记数组
     */
    private void removeQueen(int col, int diag1, int diag2, 
                            boolean[] occupiedCols, boolean[] occupiedDiag1, 
                            boolean[] occupiedDiag2) {
        occupiedCols[col] = false;
        occupiedDiag1[diag1] = false;
        occupiedDiag2[diag2] = false;
    }

    /**
     * 根据皇后位置构建棋盘表示
     * @param queenPositions 皇后位置数组
     * @param boardSize 棋盘大小
     * @return 棋盘的字符串表示列表
     */
    private List<String> buildBoard(int[] queenPositions, int boardSize) {
        List<String> board = new ArrayList<>(boardSize);
        for (int col : queenPositions) {
            char[] row = new char[boardSize];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
