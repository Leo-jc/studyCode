package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-14  15:04
 * @Description: TODO
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
class NameAndIndex{
    public String name;
    public int count;
    public int index;
    public NameAndIndex(int  index){
        this.index=index;
    }
    public NameAndIndex(String name,int index,int count){
        this.name=name;
        this.index=index;
        this.count=count;
    }
}

class NameUnionFind{
    public NameAndIndex[] parent;
    public NameUnionFind(int n){
        parent=new NameAndIndex[n];
        for(int i=0;i<n;i++){
            parent[i]=new NameAndIndex(i);
        }
    }
    public int find(int index){
        if(parent[index].index==index){
            return index;
        }
        return find(parent[index].index);
    }
    public void union(int nameAndIndex1,int nameAndIndex2){
        NameAndIndex parent1=parent[find(nameAndIndex1)];
        NameAndIndex parent2=parent[find(nameAndIndex2)];
        if(parent1.index==parent2.index){
            return;
        }
        if(parent1.name.compareTo(parent2.name)<0) {
            parent2.index=parent1.index;
            parent1.count+=parent2.count;
        } else {
            parent1.index=parent2.index;
            parent2.count+=parent1.count;
        }
    }
}
