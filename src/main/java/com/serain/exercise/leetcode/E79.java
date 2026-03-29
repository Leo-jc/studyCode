package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  19:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E79 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int pos) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if(word.charAt(pos)!=board[i][j]){
            return false;
        }
        if(pos==word.length()-1){
            return true;
        }
        char temp = board[i][j];
        board[i][j] = ' ';
        boolean res = dfs(board, i + 1, j, word, pos + 1) ||
                dfs(board, i - 1, j, word, pos + 1) ||
                dfs(board, i, j + 1, word, pos + 1) ||
                dfs(board, i, j - 1, word, pos + 1);
        board[i][j] = temp;
        return res;
    }
}
