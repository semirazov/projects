package com.seven.uf;

import com.seven.uf.alorithms.QuickFindUF;
import com.seven.uf.alorithms.QuickUnionUF;
import com.seven.uf.alorithms.RoughUF;
import com.seven.uf.alorithms.UF;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final int N = 1_000_000;
        final int unions = 500_000;
        List<List<Integer>> pairs = generateRandomPairs(unions, N);

        long start = System.nanoTime();
        UF quickUnion = pairsToUF(QuickUnionUF.class, N, pairs);
        long finish = System.nanoTime();
        System.out.println("QuickUnion - construct: " + ((finish - start) / 1_000_000_000.0) + "s");

        start = System.nanoTime();
        UF quickFind = pairsToUF(QuickFindUF.class, N, pairs);
        finish = System.nanoTime();
        System.out.println("QuickFind - construct: " + (finish - start) / 1_000_000_000.0 + "s");

        int p = ThreadLocalRandom.current().nextInt(0, N);
        int q = ThreadLocalRandom.current().nextInt(0, N);

        start = System.nanoTime();
        quickUnion.connected(p, q);
        finish = System.nanoTime();
        System.out.println("QuickUnion - find: " + (finish - start) / 1_000_000_000.0 + "s");

        start = System.nanoTime();
        quickFind.connected(p, q);
        finish = System.nanoTime();
        System.out.println("QuickFind - find: " + (finish - start) / 1_000_000_000.0 + "s");
    }

    private static UF fileToUF(Class<? extends UF> ufClass) {
        List<List<Integer>> pairs = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("resources/nodes-data.txt"))) {
            pairs = convertToNumbers(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer nodesNumber = pairs.get(0).get(0);
        pairs.remove(0);

        return pairsToUF(ufClass, nodesNumber, pairs);
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

    private static List<List<Integer>> convertToNumbers(Stream<String> stream) {
        return stream
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
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
