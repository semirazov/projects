public class BruteForceAlgorithms implements Algorithms {

    public int threeSum(int[] array) {
        int n = array.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        count++;
                    }
                }

            }
        }
        return count;
    }
}
