public class LinearApproximationSimple {
    
    public static double[] getApprox(double[] x, double[] y, int n) {
        double sumx = 0, sumy = 0, sumx2 = 0, sumxy = 0;
        
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumy += y[i];
            sumx2 += x[i] * x[i];
            sumxy += x[i] * y[i];
        }
        
        double a = (n * sumxy - sumx * sumy) / (n * sumx2 - sumx * sumx);
        double b = (sumy - a * sumx) / n;
        
        return new double[]{a, b};
    }
    
    // Пример использования
    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {2, 4, 5, 7, 8};
        int n = 5;
        
        double[] coeff = getApprox(x, y, n);
        System.out.println("y = " + coeff[0] + " * x + " + coeff[1]);
    }
}