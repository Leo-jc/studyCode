package com.serain.exercise.leetcode.special;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.special
 * @Author: Serain
 * @CreateTime: 2026-05-14  15:04
 * @Description: 面试题17.07 婴儿名字 - 并查集合并同义词
 * @Version: 1.0
 */
public class M1707 {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        int n=names.length;
        NameUnionFind nameUnionFind=new NameUnionFind(n);
        Map<String,Integer> nameAndIndexMap=new HashMap<>();
        for(int i=0;i<names.length;i++){
            int left=names[i].indexOf('(');
            int right=names[i].indexOf(')');
            String name=names[i].substring(0,left);
            int count=Integer.parseInt(names[i].substring(left+1,right));
            nameUnionFind.parent[i].name=name;
            nameUnionFind.parent[i].count=count;
            nameAndIndexMap.put(name,i);
        }
        for (String synonym : synonyms) {
            int left = synonym.indexOf(',');
            int right = synonym.indexOf(')');
            String name1 = synonym.substring(1, left);
            String name2 = synonym.substring(left + 1, right);
            nameUnionFind.union(nameAndIndexMap.get(name1), nameAndIndexMap.get(name2));
        }
        List<String> result=new ArrayList<>();
        for(int i=0;i< nameUnionFind.parent.length;i++){
            int index=nameUnionFind.find(i);
            if(index==i){
                result.add(nameUnionFind.parent[i].name+"("+nameUnionFind.parent[i].count+")");
            }
        }
        return result.toArray(new String[0]);
    }
}
