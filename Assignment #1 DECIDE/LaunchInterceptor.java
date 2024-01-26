public class LaunchInterceptor {
    // CONSTANT
    public static final double PI = 3.1415926535;

    // TYPE DECLARATIONS
    public enum Connectors { 
        NOTUSED(777),
        ORR(778),
        ANDD(779); 

        private int value;
        private Connectors(int value) {
            this.value = value;
        }
    }

    public enum CompType { 
        LT(1111),
        EQ(1112), 
        GT(1113); 
        
        private int value;
        private CompType(int value) {
            this.value = value;
        }    
    }

    // INPUTS TO THE DECIDE() FUNCTION
    public static class Parameters {
        double LENGTH1;
        double RADIUS1;
        double EPSILON;
        double AREA1;
        int QPTS;
        int QUADS;
        double DIST;
        int NPTS;
        int KPTS;
        int APTS;
        int BPTS;
        int CPTS;
        int DPTS;
        int EPTS;
        int FPTS;
        int GPTS;
        double LENGTH2;
        double RADIUS2;
        double AREA2;
    }

    // GLOBAL VARIABLE DECLARATIONS
    public Parameters parameters = new Parameters();
    public static Parameters parameters2 = new Parameters();

    // X coordinates of data points
    public double[] x;
    public static double[] x2;
    // Y coordinates of data points

    public double[] y;    
    public static double[] y2;    

    // Number of data points
    public int numPoints;
    public static int numPoints2;

    // Logical Connector Matrix
    public Connectors[][] lcm = new Connectors[15][15];
    public static Connectors[][] lcm2 = new Connectors[15][15];

    // Preliminary Unlocking Matrix
    public boolean[][] pum = new boolean[15][15];
    public static boolean[][] pum2 = new boolean[15][15];

    // Conditions Met Vector
    public boolean[] cmv = new boolean[15];
    public static boolean[] cmv2 = new boolean[15];

    // Final Unlocking Vector
    public boolean[] fuv = new boolean[15];
    public static boolean[] fuv2 = new boolean[15];

    // Decision: Launch or No Launch
    public boolean launch;
    public static boolean launch2;

    // Compare floating-point numbers
    public static CompType doubleCompare(double a, double b) {
        if (Math.abs(a - b) < 0.000001)
            return CompType.EQ;
        if (a < b)
            return CompType.LT;
        return CompType.GT;
    }

    // Function you must write
    public static void decide() {
        // Your implementation here
    }

    // SKRIV FUNKTIONER NEDAN

    // There exists at least one set of two consecutive data points that are a distance greater than
    // the length, LENGTH1, apart.
    // (0 ≤ LENGTH1)

    /**
     * Calculates distance between two points
     * @param xCord1 x-coordinate for point 1
     * @param xCord2 x-coordinate for point 2
     * @param yCord1 y-coordinate for point 1
     * @param yCord2 y-coordinate for point 2
     * @return a double that is the distance
     */
    public double calcDistance(double xCord1, double xCord2, double yCord1, double yCord2){
        return Math.sqrt(Math.pow(xCord1-xCord2, 2) + Math.pow(yCord1-yCord2, 2));
    }

    /**
    * Sees if there exists at least one set of two consecutive data points that are a distance greater than LENGTH1 apart.
    * @return true if it exists, false if it doesn't
    */
    public boolean lic0(){
        for(int i = 0; i < numPoints-1; i++){
            if(calcDistance(x[i], x[i+1], y[i], y[i+1]) > parameters.LENGTH1){
                return true;
            }
        }
        return false;
    }


    // Main method
    public static void main(String[] args) {
        decide();
    }
}