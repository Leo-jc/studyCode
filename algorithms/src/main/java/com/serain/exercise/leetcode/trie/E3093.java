package com.serain.exercise.leetcode.trie;



public class E3093 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIndex = -1; // 存储当前节点对应的最优下标
    }
    private TrieNode root = new TrieNode();


    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        // 先把所有container的字符串反转插入Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            String reversed = new StringBuilder(wordsContainer[i]).reverse().toString();
            insert(reversed, i, wordsContainer);
        }

        int[] ans = new int[wordsQuery.length];
        // 处理每个查询
        for (int i = 0; i < wordsQuery.length; i++) {
            String reversedQuery = new StringBuilder(wordsQuery[i]).reverse().toString();
            ans[i] = query(reversedQuery);
        }
        return ans;
    }

    private void insert(String word, int index, String[] wordsContainer) {
        TrieNode cur = root;
        // 根节点也要更新最优下标，应对空字符串或者完全不匹配的情况
        if (cur.bestIndex == -1 || isBetter(index, cur.bestIndex, wordsContainer)) {
            cur.bestIndex = index;
        }
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
            // 每个节点都更新最优下标
            if (cur.bestIndex == -1 || isBetter(index, cur.bestIndex, wordsContainer)) {
                cur.bestIndex = index;
            }
        }
    }

    private int query(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                break;
            }
            cur = cur.children[idx];
        }
        return cur.bestIndex;
    }

    // 判断新的index是否比原来的oldIndex更优
    private boolean isBetter(int newIndex, int oldIndex, String[] wordsContainer) {
        String newStr = wordsContainer[newIndex];
        String oldStr = wordsContainer[oldIndex];
        // 长度更短优先
        if (newStr.length() != oldStr.length()) {
            return newStr.length() < oldStr.length();
        }
        // 长度相同，下标更小优先
        return newIndex < oldIndex;
    }

    public static void main(String[] args) {
        E3093 solution = new E3093();
        // 测试用例示例
        String[] container = {"abcd","bcda","xyz"};
        String[] query = {"cd","da","zz"};
        int[] ans = solution.stringIndices(container, query);
        // 预期输出 [0,1,0]
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
