public class EllipticalOrbit extends Orbit {
    public EllipticalOrbit(double[] coefficients) {
        super(coefficients);
    }
    
    public double yCoordinateAt(double x) {
        return coefficients[1] * Math.sqrt(1 - Math.pow(x/coefficients[0], 2));
    }
    
    public String toString() {
        return "(x/" + coefficients[0] + ")² + (y/" + coefficients[1] + ")² = 1";
    }
}