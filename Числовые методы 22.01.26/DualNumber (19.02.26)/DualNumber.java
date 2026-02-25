import java.util.function.Function;

public class DualNumber {
    private double real; // действительная часть 
    private double dual; // дуальная часть 
    
    public DualNumber(double real, double dual) {
        this.real = real;
        this.dual = dual;
    }
    
    // Конструктор для константы 
    public static DualNumber constant(double c) {
        return new DualNumber(c, 0);
    }
    
    // Конструктор для переменной 
    public static DualNumber variable(double x) {
        return new DualNumber(x, 1);
    }
    
    public double getReal() { return real; }
    public double getDual() { return dual; }
    
    public DualNumber add(DualNumber other) {
        return new DualNumber(this.real + other.real, this.dual + other.dual);
    }
    
    public DualNumber subtract(DualNumber other) {
        return new DualNumber(this.real - other.real, this.dual - other.dual);
    }
    
    public DualNumber multiply(DualNumber other) {
        return new DualNumber(
            this.real * other.real,
            this.real * other.dual + this.dual * other.real
        );
    }
    
    public DualNumber divide(DualNumber other) {
        if (other.real == 0) throw new ArithmeticException("Деление на ноль");
        return new DualNumber(
            this.real / other.real,
            (this.dual * other.real - this.real * other.dual) / (other.real * other.real)
        );
    }
    
    public DualNumber sin() {
        return new DualNumber(Math.sin(real), dual * Math.cos(real));
    }
    
    public DualNumber cos() {
        return new DualNumber(Math.cos(real), -dual * Math.sin(real));
    }
    
    public DualNumber exp() {
        double expReal = Math.exp(real);
        return new DualNumber(expReal, dual * expReal);
    }
    
    public DualNumber log() {
        if (real <= 0) throw new IllegalArgumentException("Логарифм от неположительного числа");
        return new DualNumber(Math.log(real), dual / real);
    }
    
    public DualNumber pow(double n) {
        return new DualNumber(
            Math.pow(real, n),
            n * Math.pow(real, n - 1) * dual
        );
    }
    
    public DualNumber sqrt() {
        if (real < 0) throw new IllegalArgumentException("Корень из отрицательного числа");
        double sqrtReal = Math.sqrt(real);
        return new DualNumber(sqrtReal, dual / (2 * sqrtReal));
    }
    
    // Метод для вычисления производной функции в точке
    public static double derivative(Function<DualNumber, DualNumber> f, double x) {
        return f.apply(variable(x)).getDual();
    }
    
    // Метод для вычисления значения функции и её производной в точке
    public static double[] valueAndDerivative(Function<DualNumber, DualNumber> f, double x) {
        DualNumber result = f.apply(variable(x));
        return new double[]{result.getReal(), result.getDual()};
    }
}