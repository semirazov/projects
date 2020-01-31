package com.seventimes;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>(Comparator.nullsFirst(String::compareTo));

        treeMap.put(null, 1);

        treeMap.put("qwer", 4);
        treeMap.put(null, 2);


        System.out.println(treeMap);
    }
}
