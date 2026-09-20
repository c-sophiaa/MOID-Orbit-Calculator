import java.util.*;

public class MOIDDetermination {
    // Calculate the distance between the Earth and the NEO given their x-coordinates
    public static double distanceBetween(double xEarth, double xNEO, Orbit earthOrbit, Orbit NEOOrbit) {
        // Calculate the y-coordinates of the Earth and the NEO
        double yEarth = earthOrbit.yCoordinateAt(xEarth);
        double yNEO = NEOOrbit.yCoordinateAt(xNEO);
        
        // Return the Euclidean distance between the points (xEarth, yEarth) and (xNEO, yNEO)
        return Math.sqrt(Math.pow(xNEO - xEarth, 2) + Math.pow(yNEO - yEarth, 2));
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the NEO and MOID determination program.");
        System.out.println("——————————————————————————————————————————————————");
        System.out.println();
        System.out.print("Enter the name of the NEO: ");
        final String NEO_NAME = input.nextLine();
        
        System.out.println();
        System.out.print("Enter the coefficient of x², the coefficient of x, and the constant term of the quadratic orbital trajectory of " + NEO_NAME + ", separated by spaces: ");
        double[] NEOOrbitCoefficients = {input.nextDouble(), input.nextDouble(), input.nextDouble()};
        QuadraticOrbit NEOOrbit = new QuadraticOrbit(NEOOrbitCoefficients);
        
        System.out.println();
        System.out.println("The orbital trajectory of " + NEO_NAME + " is defined by the equation: " + NEOOrbit);
        
        System.out.println();
        System.out.print("Enter the semi-major and semi-minor axes of the elliptical orbital trajectory of Earth, separated by spaces: " );
        double[] earthOrbitCoefficients = {input.nextDouble(), input.nextDouble()};
        EllipticalOrbit earthOrbit = new EllipticalOrbit(earthOrbitCoefficients);
        
        System.out.println();
        System.out.println("The orbital trajectory of Earth is defined by the equation: " + earthOrbit);
        
        System.out.println();
        System.out.print("Enter the x-values the MOID determination should start and end with, separated by spaces: ");
        final double X_START_EARTH = input.nextDouble();
        final double X_END_EARTH = input.nextDouble();
        
        System.out.println();
        System.out.print("Enter the incrementation value: ");
        final double INCREMENT = input.nextDouble();
        
        System.out.println();
        System.out.print("Enter the maximum difference between the x-coordinates of Earth and the NEO: ");
        final double MAX_DX = input.nextDouble();
        
        // Declare variables for iteration
        double xStartNEO;
        double xEndNEO;
        double currentDistance;
        double MOID = Double.POSITIVE_INFINITY;
        
        System.out.println();
        System.out.println("All possible MOID values:");
        
        // Iterate through Earth's x-coordinates from X_START_EARTH to X_END_EARTH, incrementing 
        for (double xEarth = X_START_EARTH; xEarth <= X_END_EARTH; xEarth += INCREMENT) {
            // Define the range of the NEO's x-coordinates
            xStartNEO = xEarth - MAX_DX;
            xEndNEO = xEarth + MAX_DX;
            
            // Iterate through the NEO's x-coordinates
            for (double xNEO = xStartNEO; xNEO <= xEndNEO; xNEO += INCREMENT) {
                // Calculate and print the distance between Earth and the NEO
                currentDistance = distanceBetween(xEarth, xNEO, earthOrbit, NEOOrbit);
                System.out.println(currentDistance + " million km");
                
                // Update the MOID is a smaller value is found
                if (currentDistance < MOID) {
                    MOID = currentDistance;
                }
            }
        }
        
        // Print the MOID in millions of km and AU
        double MOIDAU = MOID * 0.00668458712;
        System.out.println("\nThe worst case MOID between Earth and " + NEO_NAME + " is " + MOID + " million km (" + MOIDAU + " AU).");
    }
}