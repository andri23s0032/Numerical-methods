public class SumResult {
    private final long start;
    private final long end;
    private final long sum;
    private final long count;
    private final int threadId;
    
    public SumResult(long start, long end, long sum, int threadId) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.count = end - start + 1;
        this.threadId = threadId;
    }
    
    public long getStart() {
        return start;
    }
    
    public long getEnd() {
        return end;
    }
    
    public long getSum() {
        return sum;
    }
    
    public long getCount() {
        return count;
    }
    
    public int getThreadId() {
        return threadId;
    }
    
    @Override
    public String toString() {
        return String.format("Поток %d: числа %d..%d | сумма = %d | кол-во = %d",
                            threadId, start, end, sum, count);
    }
}