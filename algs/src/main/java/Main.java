import com.google.common.base.Stopwatch;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int n = 20000;
        int[] array = generateRandomSequence(n);

        Algorithms algs = new BruteForceAlgorithms();
        Algorithms algsFast = new LinearithmAlgorithms();

        Stopwatch stopwatch = Stopwatch.createStarted();

        stopwatch.reset();
        stopwatch.start();
        algsFast.threeSum(array);
        stopwatch.stop();

        System.out.println("time: " + stopwatch.elapsed(TimeUnit.SECONDS));

    }

    public static int[] generateRandomSequence(int n) {
        List<Integer> integerList = IntStream.range(-n, n)
                .boxed().collect(Collectors.toList());


        return integerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
