public class QuadraticOrbit extends Orbit {
    public QuadraticOrbit(double[] coefficients) {
        super(coefficients);
    }
    
    public double yCoordinateAt(double x) {
        return coefficients[0] * Math.pow(x, 2) + coefficients[1] * x + coefficients[2];
    }
    
    public String toString() {
        return coefficients[0] + "x² + " + coefficients[1] + "x + " + coefficients[2];
    }
}