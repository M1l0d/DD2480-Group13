public class LaunchInterceptor {
    
    // Logical Connector Matrix
    public Connectors[][] lcm = new Connectors[15][15];

    // Preliminary Unlocking Matrix
    public boolean[][] pum = new boolean[15][15];

    // Preliminary Unlocking Vector
    public boolean[] puv = new boolean[15];

    // Conditions Met Vector
    public boolean[] cmv = new boolean[15];

    // Final Unlocking Vector
    public boolean[] fuv = new boolean[15];

    // Decision: Launch or No Launch
    public boolean launch;

    public static void main(String[] args) {
        decide();
    }

    // Function you must write
    public static void decide() {
        // Your implementation here
    }

    // SKRIV FUNKTIONER NEDAN
    /* LIC 3
    There exists at least one set of three 
    consecutive data points that are the vertices of a triangle
    area greater than AREA1. */
public boolean LIC3() {
    for (int i = 0, j = 0; i < x.length - 2 && j < y.length - 2; i++, j++) {
        double x1 = parameters.x[i];
        double y1 = parameters.y[j];
        double x2 = parameters.x[i + 1];
        double y2 = parameters.y[j + 1];
        double x3 = parameters.x[i + 2];
        double y3 = parameters.y[j + 2];
        
        double area = calculateTriangleArea(x1, y1, x2, y2, x3, y3);
        if (area > parameters.AREA1) {
            return true;
        } 
    } return false;
}
//checks that the points form a triangle
public static double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2);
}
/* LIC8
* There exists at least one set of three data points 
* separated by exactly A PTS and B PTS consecutive intervening points, 
* respectively, that cannot be contained within or on a circle of 
* radius RADIUS1. The condition is not met when NUMPOINTS < 5.
* 1 ≤ A PTS, 1 ≤ B PTS
* A PTS+B PTS ≤ (NUMPOINTS−3)
* @param x1 - x coordinate of first point
* @param y1 - y coordinate of first point
* @param x2 - x coordinate of second point
* @param y2 - y coordinate of second point
* @param x3 - x coordinate of third point
* @param y3 - y coordinate of third point
*
*/

public boolean LIC8() {
    /*CONDITION-CHECKS
    // Check if NUMPOINTS is less than 5
    if (numPoints < 5) {
        return false; // Condition not met
    }
    // Check if A and B are less than 1
    if (parameters.APTS < 1 || parameters.BPTS < 1) {
        return false; // Condition not met
    }
    // Check if A + B is greater than NUMPOINTS - 3
    if (parameters.APTS + parameters.BPTS > numPoints - 3) {
        return false; // Condition not met
    // }
    /* Check if there exists at least one set of three data points separated by 
    exactly A_PTS and B_PTS consecutive intervening points, respectively,
    that cannot be contained within or on a circle of radius RADIUS1*/
    for (int i = 0; i < numPoints - parameters.APTS-parameters.BPTS-2; i++) { // NUMPOINTS - 2 to ensure there's remaining data points for an iteration
        double x1 = parameters.x[i];
        double y1 = parameters.y[i];
        double x2 = parameters.x[i + parameters.APTS + 1];
        double y2 = parameters.y[i + parameters.APTS + 1];
        double x3 = parameters.x[i + parameters.APTS + parameters.BPTS + 2];
        double y3 = parameters.y[i + parameters.APTS + parameters.BPTS + 2];



        double inf = 1e18;
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
            minRadius = calculateCircumCircleRadius(x1, x2, x3, y1, y2, y3);
        }

        if(minRadius > parameters.RADIUS1) {
            return true;
        } 
    } return false;
    }

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

/* Issue 14. There exists at least one set of three data points, 
separated by exactly A PTS and B PTS consecutive intervening points, 
respectively, that cannot be contained within or on a circle of 
radius RADIUS1. In addition, there exists at least one set of three 
data points (which can be the same or different from the three data points 
just mentioned) separated by exactly A PTS and B PTS consecutive intervening points,
respectively, that can be contained in or on a circle of radius RADIUS2. 
Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 5.
0 ≤ RADIUS2*/
//public static boolean LIC13(double[] x, double[] y, int APTS, int BPTS, int NUMPOINTS, double RADIUS1, double RADIUS2/
public boolean LIC13() {
    //CONDITION-CHECKS
    // Check if NUMPOINTS is less than 5
    if (parameters.numPoints < 5) {
        return false; // Condition not met
    }
    // Check if RADIUS2 is less than 0
    if (parameters.RADIUS < 0) {
        return false; // Condition not met
    }
    if (LIC8()) { //Check that the first part of the condition is met
        for (int i = 0; i < parameters.APTS-parameters.BPTS-2; i++) { // NUMPOINTS - 2 to ensure there's remaining data points for an iteration
            double x1 = parameters.x[i];
            double y1 = parameters.y[i];
            double x2 = parameters.x[i + parameters.APTS + 1];
            double y2 = parameters.y[i + parameters.APTS + 1];
            double x3 = parameters.x[i + parameters.APTS + parameters.BPTS + 2];
            double y3 =parameters. y[i + parameters.APTS + parameters.BPTS + 2];
    
           
        double inf = 1e18;
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
            minRadius = calculateCircumCircleRadius(x1, x2, x3, y1, y2, y3);
        }

        if(minRadius > parameters.RADIUS1) {
            return true;
        } 
    } 
    } return false;
} 

}
