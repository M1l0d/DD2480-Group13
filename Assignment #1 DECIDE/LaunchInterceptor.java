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

    // --------------------FUNCTIONS FOR ISSUE 3 (lic2)--------------------
    public boolean lic2() {

        for (int i = 0; i < numPoints - 2; i++) {

            if (!collinear(x[i], y[i], x[i + 1], y[i + 1], x[i + 2], y[i + 2])) {

                double angle = calculateAngle(x[i], y[i], x[i + 1], y[i + 1], x[i + 2], y[i + 2]);

                // check if condition is satisfied
                if ((angle < (PI - parameters.EPSILON)) || (angle > (PI + parameters.EPSILON))) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean collinear(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ((y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2));
    }

    // function to calculate angle between three data points
    public double calculateAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double angle = Math.atan2(y3 - y2, x3 - x2) - Math.atan2(y1 - y2, x1 - x2);
        if (angle < 0) { // adjusting for negative angles
            angle += 2 * PI;
        }
        return angle;
    }

    // --------------------FUNCTIONS FOR ISSUE 8 (lic7)--------------------
    public boolean lic7() {

        if ((numPoints < 3)) {
            return false;
        }

        if (parameters.KPTS < 1 || parameters.KPTS > (numPoints - 2)) {
            return false;
        }

        for (int i = 0; i < numPoints - 2; i++) {
            double distance = calculateDistance(x[i], y[i], x[i + parameters.KPTS + 1],
                    y[i + parameters.KPTS + 1]);

            if (distance > parameters.LENGTH1) {
                return true;
            }
        }

        return false;
    }

    // function to calculate euclidean distance formula
    public double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    // --------------------FUNCTIONS FOR ISSUE 13 (lic12)--------------------

    public boolean lic12() {

        if (numPoints < 3) {
            return false;
        }

        if (parameters.LENGTH2 < 0) {
            return false;
        }

        boolean check1 = false;
        boolean check2 = false;

        for (int i = 0; i < numPoints - parameters.KPTS - 1; i++) {
            double distance = calculateDistance(x[i], y[i], x[i + parameters.KPTS + 1],
                    y[i + parameters.KPTS + 1]);

            if (distance > parameters.LENGTH1) {
                check1 = true;
            }

            if (distance < parameters.LENGTH2) {
                check2 = true;
            }

            if (check1 && check2) {
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