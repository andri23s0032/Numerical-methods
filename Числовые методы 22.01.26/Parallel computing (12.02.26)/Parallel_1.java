import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class Parallel_1 {
    
    public static void main(String[] args) {
        int[][] arrays = new int[3][1000];
        Random random = new Random();
        
        System.out.println("Generating 3 arrays of 1000 numbers from 1 to 9...");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1000; j++) {
                arrays[i][j] = random.nextInt(9) + 1;
            }
        }
    
        System.out.println();
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> futures = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            final int arrayIndex = i;
            Future<Long> future = executor.submit(() -> {
                long sum = 0;
                for (int num : arrays[arrayIndex]) {
                    sum += num;
                }
                System.out.println("Thread " + (arrayIndex + 1) + " finished. Sum = " + sum);
                return sum;
            });
            futures.add(future);
        }
        
        long totalSum = 0;
        try {
            for (int i = 0; i < futures.size(); i++) {
                long sum = futures.get(i).get();
                totalSum += sum;
                System.out.println("Got sum from thread " + (i + 1) + ": " + sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        
        System.out.println("\n========================================");
        System.out.println("Total sum of all 3 arrays = " + totalSum);
        System.out.println("========================================");
    }
}