import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {
        // Функция
        DoubleUnaryOperator f = x -> x * x;
        
        // Параметры
        double a = 0;
        double b = 56;
        int n = 1000;
        
        double rectResult = RectangleMethod.rectangleIntegral(a, b, n, f, "midpoint");
        double trapResult = TrapezoidalMethod.trapezoidalIntegral(a, b, n, f);
        double simpsonResult = SimpsonMethod.simpsonIntegral(a, b, n, f);
        
        System.out.println("Метод прямоугольников: " + rectResult);
        System.out.println("Метод трапеций: " + trapResult);
        System.out.println("Метод Симпсона: " + simpsonResult);
    }
}