package com.serain.exercise;

public class E1009 {
    public int bitwiseComplement(int n) {
        String binary = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder();
        for(char c : binary.toCharArray()){
            sb.append(c == '1' ? '0' : '1');
        }
        return Integer.parseInt(sb.toString(), 2);
    }
}
