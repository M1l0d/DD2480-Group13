import java.lang.Math;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class LaunchInterceptor {
    // CONSTANT
    private static final double PI = 3.1415926535;

    // TYPE DECLARATIONS
    private enum Connectors { 
        NOTUSED(777),
        ORR(778),
        ANDD(779); 

        private int value;
        private Connectors(int value) {
            this.value = value;
        }
    }

    private enum CompType { 
        LT(1111),
        EQ(1112), 
        GT(1113); 
        
        private int value;
        private CompType(int value) {
            this.value = value;
        }    
    }

    // INPUTS TO THE DECIDE() FUNCTION
    public class Parameters {
        double  LENGTH1;
        double  RADIUS1;
        double  EPSILON;
        double  AREA1;
        int     QPTS;
        int     QUADS;
        double  DIST;
        int     NPTS;
        int     KPTS;
        int     APTS;
        int     BPTS;
        int     CPTS;
        int     DPTS;
        int     EPTS;
        int     FPTS;
        int     GPTS;
        double  LENGTH2;
        double  RADIUS2;
        double  AREA2;
    }

    // GLOBAL VARIABLE DECLARATIONS
    public Parameters parameters = new Parameters();
    //private static Parameters parameters2 = new Parameters();

    // X coordinates of data points
    public double[] x;
    private static double[] x2;
    // Y coordinates of data points

    public double[] y;    
    private static double[] y2;    

    // Number of data points
    public int numPoints;
    private static int numPoints2;

    // Logical Connector Matrix
    private Connectors[][] lcm = new Connectors[15][15];
    private static Connectors[][] lcm2 = new Connectors[15][15];

    // Preliminary Unlocking Matrix
    private boolean[][] pum = new boolean[15][15];
    private static boolean[][] pum2 = new boolean[15][15];

    // Conditions Met Vector
    private boolean[] cmv = new boolean[15];
    private static boolean[] cmv2 = new boolean[15];

    // Final Unlocking Vector
    private boolean[] fuv = new boolean[15];
    private static boolean[] fuv2 = new boolean[15];

    // Decision: Launch or No Launch
    private boolean launch;
    private static boolean launch2;

    // Compare floating-point numbers
    private static CompType doubleCompare(double a, double b) {
        if (Math.abs(a - b) < 0.000001)
            return CompType.EQ;
        if (a < b)
            return CompType.LT;
        return CompType.GT;
    }

    public static void main(String[] args) {
        decide();
    }

    // Function you must write
    private static void decide() {
        // Your implementation here
    }

    // SKRIV FUNKTIONER NEDAN

    /* There exists at least one set of N PTS consecutive data points such that at least one of the
    points lies a distance greater than DIST from the line joining the first and last of these N PTS
    points. If the first and last points of these N PTS are identical, then the calculated distance
    to compare with DIST will be the distance from the coincident point to all other points of
    the N PTS consecutive points. The condition is not met when NUMPOINTS <3.
    (3 ≤N PTS ≤NUMPOINTS), (0 ≤DIST) */
    public boolean lic6() {
        if( 3 < numPoints){
            for(int i = 0; i < numPoints - parameters.NPTS; i++) {
                if(x[i] == x[i+parameters.NPTS] && y[i] == y[i+parameters.NPTS]) {
                    double distBetweenPoints = Math.sqrt(Math.pow(x[i]-x[i+parameters.NPTS], 2) + Math.pow(y[i]-y[i+parameters.NPTS], 2));
                        if(parameters.DIST < distBetweenPoints) {
                            return true;
                        }
                }
                else {
                    for(int j = i; j < i+parameters.NPTS; j++ ) {
                        double a = (y[i] - y[i+parameters.NPTS]);
                        double b = (x[i+parameters.NPTS] - x[i]);
                        double c = (x[i]*y[i+parameters.NPTS] - y[i]*x[i+parameters.NPTS]);
                        double distFromLineToPoint =  Math.abs(((a*x[j] + b*y[j] + c) / Math.sqrt(Math.pow(a,2) + Math.pow(b,2))));
                        if(parameters.DIST < distFromLineToPoint) {
                            return true;
                        }
                    }
                }
            }   
        }
        return false;
    }
}
    
