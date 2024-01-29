public class issues {
    /*issue 3. There exists at least one set of three 
    consecutive data points that are the vertices of a triangle
    area greater than AREA1. */
public boolean triangleArea(double[] x, double [] y, double AREA1) {
    for (int i = 0, j = 0; i < x.length - 2 && j < y.length - 2; i++, j++) {
        double x1 = x[i];
        double y1 = y[j];
        double x2 = x[i + 1];
        double y2 = y[j + 1];
        double x3 = x[i + 2];
        double y3 = y[j + 2];
        
        double area = calculateTriangleArea(x1, y1, x2, y2, x3, y3);
        if (area > AREA1) {
            return true;
        } 
    } return false;
}
//checks that the points form a triangle
public double calculateTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2);
}
/*issue 8. There exists at least one set of three data points 
separated by exactly A PTS and B PTS consecutive intervening points, 
respectively, that cannot be contained within or on a circle of 
radius RADIUS1. The condition is not met when NUMPOINTS < 5.
1 ≤ A PTS, 1 ≤ B PTS
A PTS+B PTS ≤ (NUMPOINTS−3)*/


/*issue 13. There exists at least one set of three data points, 
separated by exactly A PTS and B PTS consecutive intervening points, 
respectively, that cannot be contained within or on a circle of 
radius RADIUS1. In addition, there exists at least one set of three 
data points (which can be the same or different from the three data points 
just mentioned) separated by exactly A PTS and B PTS consecutive intervening points,
respectively, that can be contained in or on a circle of radius RADIUS2. 
Both parts must be true for the LIC to be true. The condition is not met when NUMPOINTS < 5.
0 ≤ RADIUS2*/

}