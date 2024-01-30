import java.lang.Math;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
    public class Parameters {
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

    // private static Parameters parameters2 = new Parameters();

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

    /**
     * Check lic4 by finding one set of qPts consecutive data points that lie in
     * more than QUADS quadrants
     * 
     * @param x         - set of x-coordinates of data points
     * @param y         - set of y-coordinates of data points
     * @param qPts      - number of consecutive data points that satisfy lic4
     * @param qQuads    - number of min number of quadrants the points should lie in
     * @param numPoints - number of data points
     * @return - True if condition is met, False otherwise
     */
    public static boolean lic4(double[] x, double[] y, int qPts, int qQuads, int numPoints) {

        if (qPts < 2 || qPts > numPoints || qQuads < 1 || qQuads > 3) {
            return false;
        }

        for (int i = 0; i <= numPoints - qPts; i++) {
            int[] quadrants = { 0, 0, 0, 0 };
            for (int j = i; j < i + qPts; j++) {
                double xCord = x[j];
                double yCord = y[j];

                if (xCord >= 0 && yCord >= 0) { // first quadrant
                    quadrants[0]++;
                }

                else if (xCord < 0 && yCord >= 0) { // second quadrant
                    quadrants[1]++;
                }

                else if (xCord <= 0 && yCord < 0) { // third quadrant
                    quadrants[2]++;
                }

                else if (xCord > 0 && yCord < 0) { // fourth quadrant
                    quadrants[3]++;
                }
            }
            int counter = 0;
            for (int quadrant : quadrants) {
                if (quadrant > 0) {
                    counter++;
                }
            }
            if (counter > qQuads) {
                return true;
            }

        }

        return false;
    }

    /**
     * Method that takes coordinates of three points & computes
     * the angle at the middle point using the Law of Cosines
     * 
     * @param x1 - x coordinate of first point
     * @param y1 - y coordinate of first point
     * @param x2 - x coordinate of second point
     * @param y2 - y coordinate of second point
     * @param x3 - x coordinate of third point
     * @param y3 - y coordinate of third point
     * @return the angle in radians
     */
    public static double computeAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double BtoA = Math.hypot(x2 - x1, y2 - y1);
        double BtoC = Math.hypot(x2 - x3, y2 - y3);
        double AtoC = Math.hypot(x1 - x3, y1 - y3);
        return Math.acos((Math.pow(BtoA, 2) + Math.pow(BtoC, 2) - Math.pow(AtoC, 2))
                / (2 * BtoA * BtoC));
    }

    /**
     * Checks lic9 by finding one set of three points separated by exactly C_PTS &
     * D_PTS consecutive intervening points respectively that form an angle such
     * that angle < (PI - Epsilon) or angle > (PI + Epsilon)
     * 
     * @param x         - set of x-coordinates of data points
     * @param y         - set of y-coordinates of data points
     * @param pi        - the constant pi
     * @param epsilon   - a constant number that determines deviation from PI
     * @param cPts      - number of intervening points between first and second
     *                  point
     * @param dPts      - number of intervening points between second and third
     *                  point
     * @param numPoints - number of data points
     * @return - True if condition is met, False otherwise
     */
    public static boolean lic9(double[] x, double[] y, double pi, double epsilon, int cPts, int dPts, int numPoints) {

        if (numPoints < 5 || cPts < 1 || dPts < 1 || cPts + dPts > numPoints - 3) {
            return false;
        }

        for (int i = 0; i < numPoints; i++) {

            int secondPtPos = i + cPts + 1;
            int thirdPtPos = secondPtPos + dPts + 1;

            if (thirdPtPos >= numPoints) {
                break;
            }

            double xCord1 = x[i];
            double yCord1 = y[i];

            double xCord2 = x[secondPtPos];
            double yCord2 = y[secondPtPos];

            double xCord3 = x[thirdPtPos];
            double yCord3 = y[thirdPtPos];

            if (((xCord1 == xCord2) && (yCord1 == yCord2)) || ((xCord2 == xCord3) && (yCord2 == yCord3))) {
                continue;
            }

            double angle = computeAngle(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3);
            if (angle < (pi - epsilon) || angle > (pi + epsilon)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Checks if three data points construct a triangle that fulfills the triangle
     * inequality theorem
     * 
     * @param x1 - x coordinate of first point
     * @param y1 - y coordinate of first point
     * @param x2 - x coordinate of second point
     * @param y2 - y coordinate of second point
     * @param x3 - x coordinate of third point
     * @param y3 - y coordinate of third point
     * @return - True if triangle is valid, False otherwise
     */
    public static boolean isTriangleValid(double x1, double y1, double x2, double y2, double x3, double y3) {
        double d1 = Math.hypot(x2 - x1, y2 - y1);
        double d2 = Math.hypot(x3 - x2, y3 - y2);
        double d3 = Math.hypot(x1 - x3, y1 - y3);
        return d1 + d2 > d3 && d2 + d3 > d1 && d3 + d1 > d2;
    }

    /**
     * Computes the area of a triangle using the coordinates of its three vertices
     * 
     * @param x1 - x coordinate of first point
     * @param y1 - y coordinate of first point
     * @param x2 - x coordinate of second point
     * @param y2 - y coordinate of second point
     * @param x3 - x coordinate of third point
     * @param y3 - y coordinate of third point
     * @return - the area of the triangle
     */
    public static double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Calculation the area of triangle
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) / 2.0);
    }

    /**
     * Checks lic14 by finding one set of three points separated by exactly E_PTS &
     * F_PTS consecutive intervening points, respectively, that are three vertices
     * of a triangle with area greater than AREA1 and with area less than AREA2
     * 
     * @param x         - set of x-coordinates of data points
     * @param y         - set of Y-coordinates of data points
     * @param area1     - area1
     * @param area2     - area2
     * @param ePts      - number of intervening points between first and second
     *                  point
     * @param fPts      - number of intervening points between second and third
     *                  point
     * @param numPoints - number of data points
     * @return - True if condition is met, False otherwise
     */
    public static boolean lic14(double[] x, double[] y, double area1, double area2, int ePts, int fPts, int numPoints) {

        if (numPoints < 5 || area2 < 0) {
            return false;
        }

        for (int i = 0; i < numPoints; i++) {

            int secondPtPos = i + ePts + 1;
            int thirdPtPos = secondPtPos + fPts + 1;

            if (thirdPtPos >= numPoints) {
                break;
            }

            double xCord1 = x[i];
            double yCord1 = y[i];

            double xCord2 = x[secondPtPos];
            double yCord2 = y[secondPtPos];

            double xCord3 = x[thirdPtPos];
            double yCord3 = y[thirdPtPos];

            if (!isTriangleValid(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3)) {
                continue;
            }

            double area = calculateTriangleArea(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3);
            if (area > area1 && area < area2) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        decide();
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

    /**
     * Sees if there exists at least one set of two consecutive data points (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0. (where i = j-1)
     * @return true if it exists, false if it doesn't
     */
    public boolean lic5(){
        for(int i = 0; i < numPoints-1; i++){
            if(x[i+1] - x[i] < 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Sees if there exists at least one set of three data points seperated by exactly E_PTS and F_PTS consecutive intervening points, 
     * respectively, that are the vertices of a triangle with area greater than AREA1. The condition is not met when NUMPOINTS < 5
     * @return true if it exists, false if it doesn't
     */
    public boolean lic10(){
        if(numPoints >= 5){
            for(int i = 0; i < numPoints-parameters.EPTS-parameters.FPTS - 2; i++){
                int EPoint = i + parameters.EPTS + 1;
                int FPoint = i + EPoint + parameters.FPTS + 1;
                double verticeE = calcDistance(x[i], x[EPoint], y[i], y[EPoint]);
                double verticeF = calcDistance(x[EPoint], x[FPoint], y[EPoint], y[FPoint]);

                if((verticeE * verticeF)/2 > parameters.AREA1){
                    return true;
                }
            }
        }
        return false;
    }
}