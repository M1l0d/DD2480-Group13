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

    /* LIC 1: There exists at least one set of three consecutive data points that cannot all be contained
    within or on a circle of radius RADIUS1.
    (0 ≤RADIUS1) */
    public double calculateCircumCircleRadius(double xCord1, double xCord2, double xCord3, double yCord1, double yCord2, double yCord3) {        
        double a = distBetweenPoint(xCord1, xCord2, yCord1, yCord2);
        double b = distBetweenPoint(xCord1, xCord3, yCord1, yCord3);
        double c = distBetweenPoint(xCord2, xCord3, yCord2, yCord3);
        return (a*b*c) / Math.sqrt((a+b+c)*(b+c-a)*(c+a-b)*(a+b-c));
    }

    public double distBetweenPoint(double xCord1, double xCord2, double yCord1, double yCord2) {
        return Math.sqrt(Math.pow(xCord1-xCord2, 2) + Math.pow(yCord1-yCord2, 2));   
    }

    public boolean pointInsideCircle(double[] midPointOfCircle, double radius, double[] point) {
        return  (distBetweenPoint(midPointOfCircle[0], point[0], midPointOfCircle[1], point[1]) / 2) < radius;
    }
    
    public boolean lic1() {
        double inf = 1e18;
        for(int i = 0; i < numPoints - 2; i++) {
            
            double x1 = x[i];
            double x2 = x[i+1];
            double x3 = x[i+2];
            double y1 = y[i];
            double y2 = y[i+1];
            double y3 = y[i+2];

            double minRadius = inf;

            double[] midPoint1 = new double[]{x1 - x2 / 2, y1 - y2 / 2};
            double radius1 = distBetweenPoint(x1, x2, y1, y2) / 2;
            double[] lastPoint1 = new double[]{x3, y3};
            boolean last1InCircle = pointInsideCircle(midPoint1, radius1, lastPoint1);
            
            if(last1InCircle && radius1 < minRadius) {
                minRadius = radius1;
            }

            double[] midPoint2 = new double[]{x1 - x3 / 2, y1 - y3 / 2};
            double radius2 = distBetweenPoint(x1, x3, y1, y3) / 2;
            double[] lastPoint2 = new double[]{x2, y2};
            boolean last2InCircle = pointInsideCircle(midPoint2, radius2, lastPoint2);

            if(last2InCircle && radius2 < minRadius) {
                minRadius = radius2;
            }

            double[] midPoint3 = new double[]{x2 - x3 / 2, y2 - y3 / 2};
            double radius3 = distBetweenPoint(x2, x3, y2, y3) / 2;
            double[] lastPoint3 = new double[]{x1, y1};
            boolean last3InCircle = pointInsideCircle(midPoint3, radius3, lastPoint3);

            if(last3InCircle && radius3 < minRadius) {
                minRadius = radius3;
            }

            if(minRadius == inf) {
                minRadius = calculateCircumCircleRadius(x[i], x[i+1], x[i+2], y[i], y[i+1], y[i+2]);
            }

            if(minRadius > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

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

    /*There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
    exactly G PTS consecutive intervening points, such that X[j] - X[i] <0. (where i <j ) The
    condition is not met when NUMPOINTS <3.
    1 ≤G PTS ≤NUMPOINTS−2 */
    public boolean lic11() {

        if(numPoints > 3) {
            for(int i = 0; i < numPoints - parameters.GPTS; i++) {
                if(x[i+parameters.GPTS] - x[i] < 0 ) {
                    return true;
                }
            }
        }

        return false;
    }
}
    
