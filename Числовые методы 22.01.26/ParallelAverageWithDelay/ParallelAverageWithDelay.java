import java.util.*;
import java.util.concurrent.*;

public class ParallelAverageWithDelay {
    
    public static void main(String[] args) {
        final long TOTAL_NUMBERS = 100_000_000;
        final int THREAD_COUNT = 4;
        
        System.out.println("Вычисление среднего от 1 до " + TOTAL_NUMBERS);
        System.out.println("Потоков: " + THREAD_COUNT);
        System.out.println("----------------------------------------");
        
        long startTime = System.currentTimeMillis();
        
        long partSize = TOTAL_NUMBERS / THREAD_COUNT;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<SumResult>> futures = new ArrayList<>();
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            long start = i * partSize + 1;
            long end = (i == THREAD_COUNT - 1) ? TOTAL_NUMBERS : (i + 1) * partSize;
            futures.add(executor.submit(new SlowCalculator(start, end, i + 1)));
        }
        
        long totalSum = 0;
        long totalCount = 0;
        
        try {
            for (Future<SumResult> future : futures) {
                SumResult result = future.get();
                totalSum += result.getSum();
                totalCount += result.getCount();
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        executor.shutdown();
        
        double average = (double) totalSum / totalCount;
        long endTime = System.currentTimeMillis();
        
        System.out.println("----------------------------------------");
        System.out.println("Общая сумма: " + totalSum);
        System.out.println("Среднее значение: " + average);
        System.out.println("Время: " + (endTime - startTime) + " мс");
        System.out.println("Ожидаемое среднее: " + (TOTAL_NUMBERS + 1) / 2.0);
    }
}