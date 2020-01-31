package com.seven.uf;

import com.seven.uf.alorithms.QuickUnionBalancedUF;
import com.seven.uf.alorithms.QuickUnionUF;
import com.seven.uf.alorithms.UF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Visualize {
    public static void main(String[] args) {
        int[] structures = new int[]{10, 100, 1000, 10000, 100000, 1000000 };

        for(int N : structures){
            int unions = N*10;
            List<List<Integer>> pairs = generateRandomPairs(unions, N);
            UF uf2 = pairsToUF(QuickUnionBalancedUF.class, N, pairs);
            System.out.println(uf2.toString());
        }

    }

    private static UF pairsToUF(Class<? extends UF> ufClass, int nodesNumber, List<List<Integer>> pairs) {
        try {
            UF uf = ufClass.getDeclaredConstructor(Integer.TYPE).newInstance(nodesNumber);
            pairs.stream().forEach(pair -> processPair(uf, pair.get(0), pair.get(1)));

            return uf;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void processPair(UF uf, int p, int q) {
        if (!uf.connected(p, q)) {
            uf.union(p, q);
        }
    }

    private static List<List<Integer>> generateRandomPairs(int number, int max) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            numbers.add(Arrays.asList(
                    ThreadLocalRandom.current().nextInt(0, max),
                    ThreadLocalRandom.current().nextInt(0, max)
            ));
        }

        return numbers;
    }
}
