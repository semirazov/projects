import java.util.Arrays;

public class LinearithmAlgorithms implements Algorithms {

    @Override
    public int threeSum(int[] array) {
        int count = 0;
        int n = array.length;
        Arrays.sort(array);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int subtractor = -(array[i] + array[j]);

                if (array[j] < subtractor && Arrays.binarySearch(array, subtractor) >= 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
