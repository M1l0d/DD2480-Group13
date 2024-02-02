public class CMV {

    Parameters parameters;
    public boolean[] cmv;

    public CMV(Parameters parameters) {
        this.parameters = parameters; 
        this.cmv = new boolean[]{
            lic0(),
            lic1(),
            lic2(),
            LIC3(),
            lic4(),
            lic5(),
            lic6(),
            lic7(),
            LIC8(),
            lic9(),
            lic10(),
            lic11(),
            lic12(),
            LIC13(),
            lic14()
        };
    }

    public boolean[] getCmv() {
        return cmv;
    }

    // Compare floating-point numbers
    public static CompType doubleCompare(double a, double b) {
        if (Math.abs(a - b) < 0.000001)
            return CompType.EQ;
        if (a < b)
            return CompType.LT;
        return CompType.GT;
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
    public double computeAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double BtoA = Math.hypot(x2 - x1, y2 - y1);
        double BtoC = Math.hypot(x2 - x3, y2 - y3);
        double AtoC = Math.hypot(x1 - x3, y1 - y3);
        return Math.acos((Math.pow(BtoA, 2) + Math.pow(BtoC, 2) - Math.pow(AtoC, 2))
                / (2 * BtoA * BtoC));
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
    public boolean isTriangleValid(double x1, double y1, double x2, double y2, double x3, double y3) {
        double d1 = Math.hypot(x2 - x1, y2 - y1);
        double d2 = Math.hypot(x3 - x2, y3 - y2);
        double d3 = Math.hypot(x1 - x3, y1 - y3);
        return d1 + d2 > d3 && d2 + d3 > d1 && d3 + d1 > d2;
    }

    /**
     * Checks if three points are collinear
     * 
     * @param x1 - x coordinate of first point
     * @param y1 - y coordinate of first point
     * @param x2 - x coordinate of second point
     * @param y2 - y coordinate of second point
     * @param x3 - x coordinate of third point
     * @param y3 - y coordinate of third point
     * @return - True if points are collinear, False otherwise
     */
    public boolean collinear(double x1, double y1, double x2, double y2, double x3, double y3) {
        return ((y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2));
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
    public double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Calculation the area of triangle
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) / 2.0);
    }

    /**
     * Calculates distance between two points
     * 
     * @param xCord1 x-coordinate for point 1
     * @param xCord2 x-coordinate for point 2
     * @param yCord1 y-coordinate for point 1
     * @param yCord2 y-coordinate for point 2
     * @return a double that is the distance
     */
    public double calcDistance(double xCord1, double xCord2, double yCord1, double yCord2) {
        return Math.sqrt(Math.pow(xCord1 - xCord2, 2) + Math.pow(yCord1 - yCord2, 2));
    }

    /**
     * Calculates the radius of the circumcircle of a triangle
     * 
     * @param xCord1 x-coordinate for point 1
     * @param xCord2 x-coordinate for point 2
     * @param xCord3 x-coordinate for point 3
     * @param yCord1 y-coordinate for point 1
     * @param yCord2 y-coordinate for point 2
     * @param yCord3 y-coordinate for point 3
     * @return a double that is the radius
     */
    public double calculateCircumCircleRadius(double xCord1, double xCord2, double xCord3, double yCord1, double yCord2,
            double yCord3) {
        double a = calcDistance(xCord1, xCord2, yCord1, yCord2);
        double b = calcDistance(xCord1, xCord3, yCord1, yCord3);
        double c = calcDistance(xCord2, xCord3, yCord2, yCord3);
        return (a * b * c) / Math.sqrt((a + b + c) * (b + c - a) * (c + a - b) * (a + b - c));
    }

    /**
     * Checks if a point is inside a circle
     * 
     * @param midPointOfCircle the midpoint of the circle
     * @param radius           the radius of the circle
     * @param point            the point to check
     * @return true if the point is inside the cicle, false otherwise
     */
    public boolean pointInsideCircle(double[] midPointOfCircle, double radius, double[] point) {
        return (calcDistance(midPointOfCircle[0], point[0], midPointOfCircle[1], point[1]) / 2) < radius;
    }


    /**
     * LIC0
     * Sees if there exists at least one set of two consecutive data points that are
     * a distance greater than LENGTH1 apart.
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic0() {
        for (int i = 0; i < parameters.numPoints - 1; i++) {
            if (calcDistance(parameters.x[i], parameters.x[i + 1], parameters.y[i],
                    parameters.y[i + 1]) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC1
     * Sees if there exists at least one set of three consecutive data points that
     * cannot all be contained
     * within or on a circle of radius RADIUS1.
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic1() {
        double inf = 1e18;
        for (int i = 0; i < parameters.numPoints - 2; i++) {

            double x1 = parameters.x[i];
            double x2 = parameters.x[i + 1];
            double x3 = parameters.x[i + 2];
            double y1 = parameters.y[i];
            double y2 = parameters.y[i + 1];
            double y3 = parameters.y[i + 2];

            double minRadius = inf;

            double[] midPoint1 = new double[] { x1 - x2 / 2, y1 - y2 / 2 };
            double radius1 = calcDistance(x1, x2, y1, y2) / 2;
            double[] lastPoint1 = new double[] { x3, y3 };
            boolean last1InCircle = pointInsideCircle(midPoint1, radius1, lastPoint1);

            if (last1InCircle && radius1 < minRadius) {
                minRadius = radius1;
            }

            double[] midPoint2 = new double[] { x1 - x3 / 2, y1 - y3 / 2 };
            double radius2 = calcDistance(x1, x3, y1, y3) / 2;
            double[] lastPoint2 = new double[] { x2, y2 };
            boolean last2InCircle = pointInsideCircle(midPoint2, radius2, lastPoint2);

            if (last2InCircle && radius2 < minRadius) {
                minRadius = radius2;
            }

            double[] midPoint3 = new double[] { x2 - x3 / 2, y2 - y3 / 2 };
            double radius3 = calcDistance(x2, x3, y2, y3) / 2;
            double[] lastPoint3 = new double[] { x1, y1 };
            boolean last3InCircle = pointInsideCircle(midPoint3, radius3, lastPoint3);

            if (last3InCircle && radius3 < minRadius) {
                minRadius = radius3;
            }

            if (minRadius == inf) {
                minRadius = calculateCircumCircleRadius(parameters.x[i], parameters.x[i + 1], parameters.x[i + 2],
                        parameters.y[i], parameters.y[i + 1], parameters.y[i + 2]);
            }

            if (minRadius > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC2
     * There exists at least one set of three consecutive data points which form an
     * angle such that: angle < (PI − EPSILON)
     * or angle > (PI + EPSILON)
     * The second of the three consecutive points is always the vertex of the angle.
     * If either the first point or the last point
     * (or both) coincides with the vertex, the angle is undefined and the LIC is
     * not satisfied by those three points.
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic2() {

        for (int i = 0; i < parameters.numPoints - 2; i++) {

            if (!collinear(parameters.x[i], parameters.y[i], parameters.x[i + 1], parameters.y[i + 1],
                    parameters.x[i + 2], parameters.y[i + 2])) {

                double angle = computeAngle(parameters.x[i], parameters.y[i], parameters.x[i + 1], parameters.y[i + 1],
                        parameters.x[i + 2], parameters.y[i + 2]);

                // check if condition is satisfied
                if ((angle < (Math.PI - parameters.EPSILON)) || (angle > (Math.PI + parameters.EPSILON))) {
                    return true;
                }
            }
        }
        return false;

    }

    // SKRIV FUNKTIONER NEDAN
    /*
     * LIC 3
     * There exists at least one set of three
     * consecutive data points that are the vertices of a triangle
     * area greater than AREA1.
     */
    public boolean LIC3() {
        if (parameters.AREA1 < 0){
            return false;
        }
        for (int i = 0, j = 0; i < parameters.numPoints- 2 && j < parameters.numPoints- 2; i++, j++) {
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
        }
        return false;
    }

    /**
     * LIC4
     * Check lic4 by finding one set of qPts consecutive data points that lie in
     * more than QUADS quadrants
     * 
     * @return - True if condition is met, False otherwise
     */
    public boolean lic4() {

        if (parameters.QPTS < 2 || parameters.QPTS > parameters.numPoints || parameters.QUADS < 1
                || parameters.QUADS > 3) {
            return false;
        }

        for (int i = 0; i <= parameters.numPoints - parameters.QPTS; i++) {
            int[] quadrants = { 0, 0, 0, 0 };
            for (int j = i; j < i + parameters.QPTS; j++) {
                double xCord = parameters.x[j];
                double yCord = parameters.y[j];

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
            if (counter > parameters.QUADS) {
                return true;
            }

        }

        return false;
    }

    /**
     * LIC5
     * Sees if there exists at least one set of two consecutive data points
     * (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0. (where i = j-1)
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic5() {
        for (int i = 0; i < parameters.numPoints - 1; i++) {
            if (parameters.x[i + 1] - parameters.x[i] < 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * LIC6
     * There exists at least one set of N PTS consecutive data points such that at
     * least one of the
     * points lies a distance greater than DIST from the line joining the first and
     * last of these N PTS
     * points. If the first and last points of these N PTS are identical, then the
     * calculated distance
     * to compare with DIST will be the distance from the coincident point to all
     * other points of
     * the N PTS consecutive points. The condition is not met when NUMPOINTS <3.
     * (3 ≤N PTS ≤NUMPOINTS), (0 ≤DIST)
     * 
     * @return - True if condition is met, False otherwise
     */
    public boolean lic6() {
        if (3 < parameters.numPoints) {
            for (int i = 0; i < parameters.numPoints - parameters.NPTS; i++) {
                if (parameters.x[i] == parameters.x[i + parameters.NPTS]
                        && parameters.y[i] == parameters.y[i + parameters.NPTS]) {
                    double distBetweenPoints = Math
                            .sqrt(Math.pow(parameters.x[i] - parameters.x[i + parameters.NPTS], 2)
                                    + Math.pow(parameters.y[i] - parameters.y[i + parameters.NPTS], 2));
                    if (parameters.DIST < distBetweenPoints) {
                        return true;
                    }
                } else {
                    for (int j = i; j < i + parameters.NPTS; j++) {
                        double a = (parameters.y[i] - parameters.y[i + parameters.NPTS]);
                        double b = (parameters.x[i + parameters.NPTS] - parameters.x[i]);
                        double c = (parameters.x[i] * parameters.y[i + parameters.NPTS]
                                - parameters.y[i] * parameters.x[i + parameters.NPTS]);
                        double distFromLineToPoint = Math.abs(((a * parameters.x[j] + b * parameters.y[j] + c)
                                / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))));
                        if (parameters.DIST < distFromLineToPoint) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * LIC7
     * There exists at least one set of two data points separated by exactly K PTS
     * consecutive
     * intervening points that are a distance greater than the length, LENGTH1,
     * apart.
     * The condition is not met when NUMPOINTS < 3.
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic7() {

        if ((parameters.numPoints < 3)) {
            return false;
        }

        if (parameters.KPTS < 1 || parameters.KPTS > (parameters.numPoints - 2)) {
            return false;
        }

        for (int i = 0; i < parameters.numPoints - 2; i++) {
            double distance = calcDistance(parameters.x[i], parameters.x[i + parameters.KPTS + 1], parameters.y[i],
                    parameters.y[i + parameters.KPTS + 1]);

            if (distance > parameters.LENGTH1) {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC8
     * Checks to see if there exists at least one set of three data points separated
     * by exactly A PTS and B PTS
     * consecutive intervening points, respectively, that cannot be contained within
     * or on a circle of radius RADIUS1.
     * The condition is not met when NUMPOINTS < 5.
     * 
     * @return true if it exists, false if it doesn't
     *
     */
    public boolean LIC8() {
        /*
         * CONDITION-CHECKS
         * // Check if NUMPOINTS is less than 5 */
         if (parameters.numPoints < 5) {
         return false; // Condition not met
         }
         // Check if A and B are less than 1
         if (parameters.APTS < 1 || parameters.BPTS < 1) {
         return false; // Condition not met
         }
         // Check if A + B is greater than NUMPOINTS - 3
         if (parameters.APTS + parameters.BPTS > parameters.numPoints - 3) {
         return false; // Condition not met
         }
  
         /* Check if there exists at least one set of three data points separated by
         * exactly A_PTS and B_PTS consecutive intervening points, respectively,
         * that cannot be contained within or on a circle of radius RADIUS1
         */
        for (int i = 0; i < parameters.numPoints - parameters.APTS - parameters.BPTS - 2; i++) { 
                                                                                            
            double x1 = parameters.x[i];
            double y1 = parameters.y[i];
            double x2 = parameters.x[i + parameters.APTS + 1];
            double y2 = parameters.y[i + parameters.APTS + 1];
            double x3 = parameters.x[i + parameters.APTS + parameters.BPTS + 2];
            double y3 = parameters.y[i + parameters.APTS + parameters.BPTS + 2];

            double inf = 1e18;
            double minRadius = inf;

            double[] midPoint1 = new double[] { x1 - x2 / 2, y1 - y2 / 2 };
            double radius1 = calcDistance(x1, x2, y1, y2) / 2;
            double[] lastPoint1 = new double[] { x3, y3 };
            boolean last1InCircle = pointInsideCircle(midPoint1, radius1, lastPoint1);

            if (last1InCircle && radius1 < minRadius) {
                minRadius = radius1;
            }

            double[] midPoint2 = new double[] { x1 - x3 / 2, y1 - y3 / 2 };
            double radius2 = calcDistance(x1, x3, y1, y3) / 2;
            double[] lastPoint2 = new double[] { x2, y2 };
            boolean last2InCircle = pointInsideCircle(midPoint2, radius2, lastPoint2);

            if (last2InCircle && radius2 < minRadius) {
                minRadius = radius2;
            }

            double[] midPoint3 = new double[] { x2 - x3 / 2, y2 - y3 / 2 };
            double radius3 = calcDistance(x2, x3, y2, y3) / 2;
            double[] lastPoint3 = new double[] { x1, y1 };
            boolean last3InCircle = pointInsideCircle(midPoint3, radius3, lastPoint3);

            if (last3InCircle && radius3 < minRadius) {
                minRadius = radius3;
            }

            if (minRadius == inf) {
                minRadius = calculateCircumCircleRadius(x1, x2, x3, y1, y2, y3);
            }

            if (minRadius > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC9
     * Checks lic9 by finding one set of three points separated by exactly C_PTS &
     * D_PTS consecutive intervening points respectively that form an angle such
     * that angle < (PI - Epsilon) or angle > (PI + Epsilon)
     * 
     * @return - True if condition is met, False otherwise
     */
    public boolean lic9() {

        if (parameters.numPoints < 5 || parameters.CPTS < 1 || parameters.DPTS < 1
                || parameters.CPTS + parameters.DPTS > parameters.numPoints - 3) {
            return false;
        }

        for (int i = 0; i < parameters.numPoints; i++) {

            int secondPtPos = i + parameters.CPTS + 1;
            int thirdPtPos = secondPtPos + parameters.DPTS + 1;

            if (thirdPtPos >= parameters.numPoints) {
                break;
            }

            double xCord1 = parameters.x[i];
            double yCord1 = parameters.y[i];

            double xCord2 = parameters.x[secondPtPos];
            double yCord2 = parameters.y[secondPtPos];

            double xCord3 = parameters.x[thirdPtPos];
            double yCord3 = parameters.y[thirdPtPos];

            if (((xCord1 == xCord2) && (yCord1 == yCord2)) || ((xCord2 == xCord3) && (yCord2 == yCord3))) {
                continue;
            }

            double angle = computeAngle(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3);
            if (angle < (Math.PI - parameters.EPSILON) || angle > (Math.PI + parameters.EPSILON)) {
                return true;
            }

        }
        return false;
    }

    /**
     * LIC10
     * Sees if there exists at least one set of three data points seperated by
     * exactly E_PTS and F_PTS consecutive intervening points,
     * respectively, that are the vertices of a triangle with area greater than
     * AREA1. The condition is not met when NUMPOINTS < 5
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean lic10() {
        if (parameters.numPoints >= 5) {
            for (int i = 0; i < parameters.numPoints - parameters.EPTS - parameters.FPTS - 2; i++) {
                int EPoint = i + parameters.EPTS + 1;
                int FPoint = i + EPoint + parameters.FPTS + 1;
                double verticeE = calcDistance(parameters.x[i], parameters.x[EPoint], parameters.y[i],
                        parameters.y[EPoint]);
                double verticeF = calcDistance(parameters.x[EPoint], parameters.x[FPoint], parameters.y[EPoint],
                        parameters.y[FPoint]);

                if ((verticeE * verticeF) / 2 > parameters.AREA1) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * There exists at least one set of two data points, (X[i],Y[i]) and
     * (X[j],Y[j]), separated by
     * exactly G PTS consecutive intervening points, such that X[j] - X[i] <0.
     * (where i <j ) The
     * condition is not met when NUMPOINTS <3.
     * 1 ≤G PTS ≤NUMPOINTS−2
     * 
     * @return - True if condition is met, False otherwise
     */
    public boolean lic11() {

        if (parameters.numPoints > 3) {
            for (int i = 0; i < parameters.numPoints - parameters.GPTS; i++) {
                if (parameters.x[i + parameters.GPTS] - parameters.x[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * LIC12
     * There exists at least one set of two data points, separated by exactly K PTS
     * consecutive
     * intervening points, which are a distance greater than the length, LENGTH1,
     * apart.
     * In addition, there exists at least one set of two data points (which can be
     * the same or different
     * from the two data points just mentioned), separated by exactly K PTS
     * consecutive intervening points,
     * that are a distance less than the length, LENGTH2, apart. Both parts must be
     * true for the LIC to be true.
     * The condition is not met when NUMPOINTS < 3.
     * 
     * @return true if it exists, false if it doesn't
     */

    public boolean lic12() {

        if (parameters.numPoints < 3) {
            return false;
        }

        if (parameters.LENGTH2 < 0) {
            return false;
        }

        boolean check1 = false;
        boolean check2 = false;

        for (int i = 0; i < parameters.numPoints - parameters.KPTS - 1; i++) {
            double distance = calcDistance(parameters.x[i], parameters.x[i + parameters.KPTS + 1], parameters.y[i],
                    parameters.y[i + parameters.KPTS + 1]);

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

    /**
     * LIC13
     * There exists at least one set of three data points, separated by exactly A
     * PTS and B PTS
     * consecutive intervening points, respectively, that cannot be contained within
     * or on a circle
     * of radius RADIUS1. In addition, there exists at least one set of three data
     * points
     * (which can be the same or different from the three data points just
     * mentioned) separated by
     * exactly A PTS and B PTS consecutive intervening points, respectively, that
     * can be contained in
     * or on a circle of radius RADIUS2. Both parts must be true for the LIC to be
     * true.
     * The condition is not met when NUMPOINTS < 5.
     * 
     * @return true if it exists, false if it doesn't
     */
    public boolean LIC13() {
        int a = 5;
        // CONDITION-CHECKS
        /*Check if NUMPOINTS is less than 5
        if (parameters.numPoints == 0) {
            return false; // Condition not met
        }
        //Check if RADIUS2 is less than 0
        if (parameters.RADIUS2 < 0) {
            return false; // Condition not met
        }*/
        if (LIC8()) { // Check that the first part of the condition is met*/
            for (int i = 0; i < parameters.numPoints - parameters.APTS - parameters.BPTS - 2; i++) { 
                double x1 = parameters.x[i];
                double y1 = parameters.y[i];
                double x2 = parameters.x[i + parameters.APTS + 1];
                double y2 = parameters.y[i + parameters.APTS + 1];
                double x3 = parameters.x[i + parameters.APTS + parameters.BPTS + 2];
                double y3 = parameters.y[i + parameters.APTS + parameters.BPTS + 2];

                double inf = 1e18;
                double minRadius = inf;

                double[] midPoint1 = new double[] { x1 - x2 / 2, y1 - y2 / 2 };
                double radius1 = calcDistance(x1, x2, y1, y2) / 2;
                double[] lastPoint1 = new double[] { x3, y3 };
                boolean last1InCircle = pointInsideCircle(midPoint1, radius1, lastPoint1);

                if (last1InCircle && radius1 < minRadius) {
                    minRadius = radius1;
                }

                double[] midPoint2 = new double[] { x1 - x3 / 2, y1 - y3 / 2 };
                double radius2 = calcDistance(x1, x3, y1, y3) / 2;
                double[] lastPoint2 = new double[] { x2, y2 };
                boolean last2InCircle = pointInsideCircle(midPoint2, radius2, lastPoint2);

    
                if (last2InCircle && radius2 < minRadius) {
                    minRadius = radius2;
                }

                double[] midPoint3 = new double[] { x2 - x3 / 2, y2 - y3 / 2 };
                double radius3 = calcDistance(x2, x3, y2, y3) / 2;
                double[] lastPoint3 = new double[] { x1, y1 };
                boolean last3InCircle = pointInsideCircle(midPoint3, radius3, lastPoint3);

                if (last3InCircle && radius3 < minRadius) {
                    minRadius = radius3;
                }

                if (minRadius == inf) {
                    minRadius = calculateCircumCircleRadius(x1, x2, x3, y1, y2, y3);
                }
                //double biggestRadius = Math.max(radius1, Math.max(radius2, radius3)); //find biggest radius

                if (minRadius <= parameters.RADIUS2){
                        return true;
                }
            }
        }
    return false;
 }
    /**
     * Checks lic14 by finding one set of three points separated by exactly E_PTS &
     * F_PTS consecutive intervening points, respectively, that are three vertices
     * of a triangle with area greater than AREA1 and with area less than AREA2
     * 
     * @return - True if condition is met, False otherwise
     */
    public boolean lic14() {

        if (parameters.numPoints < 5 || parameters.AREA2 < 0) {
            return false;
        }

        for (int i = 0; i < parameters.numPoints; i++) {

            int secondPtPos = i + parameters.EPTS + 1;
            int thirdPtPos = secondPtPos + parameters.FPTS + 1;

            if (thirdPtPos >= parameters.numPoints) {
                break;
            }

            double xCord1 = parameters.x[i];
            double yCord1 = parameters.y[i];

            double xCord2 = parameters.x[secondPtPos];
            double yCord2 = parameters.y[secondPtPos];

            double xCord3 = parameters.x[thirdPtPos];
            double yCord3 = parameters.y[thirdPtPos];

            if (!isTriangleValid(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3)) {
                continue;
            }

            double area = calculateTriangleArea(xCord1, yCord1, xCord2, yCord2, xCord3, yCord3);
            if (area > parameters.AREA1 && area < parameters.AREA2) {
                return true;
            }
        }
        return false;
    }

}
