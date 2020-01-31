package main.java.com.seventimes;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{-40, -20, -10, 0, 10, 20, 30, 40, 50};
        Algorithms algs = new BruteForceAlgorithms();

        Algorithms algsFast = new LinearithmAlgorithms();


        System.out.println(algs.threeSum(array));

        System.out.println(algsFast.threeSum(array));

    }
}
