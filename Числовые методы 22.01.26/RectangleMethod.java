import java.util.function.DoubleUnaryOperator;

public class RectangleMethod {
    public static double rectangleIntegral(double a, double b, int n, 
                                          DoubleUnaryOperator f, String methodType) {
        double width = (b - a) / n;
        double integral = 0.0;
        
        for (int i = 0; i < n; i++) {
            double x;
            if (methodType.equals("left")) {
                x = a + i * width;
            } else if (methodType.equals("right")) {
                x = a + (i + 1) * width;
            } else { // midpoint
                x = a + (i + 0.5) * width;
            }
            
            integral += f.applyAsDouble(x) * width;
        }
        
        return integral;
    }
}