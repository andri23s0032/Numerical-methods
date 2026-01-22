import java.util.function.DoubleUnaryOperator;

public class TrapezoidalMethod {
    public static double trapezoidalIntegral(double a, double b, int n, DoubleUnaryOperator f) {
        double width = (b - a) / n;
        double integral = 0.0;
        
        for (int i = 0; i < n; i++) {
            double x1 = a + i * width;
            double x2 = a + (i + 1) * width;
            integral += 0.5 * (x2 - x1) * (f.applyAsDouble(x1) + f.applyAsDouble(x2));
        }
        
        return integral;
    }
}