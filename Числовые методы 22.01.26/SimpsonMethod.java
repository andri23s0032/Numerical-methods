import java.util.function.DoubleUnaryOperator;

public class SimpsonMethod {
    public static double simpsonIntegral(double a, double b, int n, DoubleUnaryOperator f) {
        double h = (b - a) / n;
        double sum = f.applyAsDouble(a) + f.applyAsDouble(b);
        
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2.0 * f.applyAsDouble(x);
            } else {
                sum += 4.0 * f.applyAsDouble(x);
            }
        }
        
        return sum * h / 3.0;
    }
}