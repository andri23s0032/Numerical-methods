import java.util.Random;
import java.util.concurrent.Callable;

public class SlowCalculator implements Callable<SumResult> {
    private final long start;
    private final long end;
    private final int threadId;
    private final Random random;
    
    public SlowCalculator(long start, long end, int threadId) {
        this.start = start;
        this.end = end;
        this.threadId = threadId;
        this.random = new Random();
    }
    
    @Override
    public SumResult call() throws Exception {
        long sum = 0;
        
        // Синтетическая задержка перед началом работы
        int initialDelay = random.nextInt(100);
        System.out.println("Поток " + threadId + " начинает через " + initialDelay + " мс");
        Thread.sleep(initialDelay);
        
        for (long i = start; i <= end; i++) {
            sum += i;
            
            if (i % 5_000_000 == 0 && i > start) {
                int pause = random.nextInt(50);
                System.out.println("  Поток " + threadId + " делает паузу " + pause + " мс на числе " + i);
                Thread.sleep(pause);
            }
        }
        
        Thread.sleep(random.nextInt(50));
        
        return new SumResult(start, end, sum, threadId);
    }
}