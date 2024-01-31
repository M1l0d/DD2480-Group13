import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CMVTest {
    private CMV emptyCMV;

    @Before
    public void setUp() {

        Parameters parameters = new Parameters();
        emptyCMV = new CMV(parameters);
    }

    @Test
    public void lic0ReturnsFalseIfDistanceBetweeenTwoConsecutiveDataPointsLessThanLENGTH1(){
        double[] xCoordinates = new double[]{1,1,4,7,10};
        double[] yCoordinates = new double[]{1,1,5,8,10};
        double length1 = 5;
        // if x1=1, x2=4, y1=1, y2=5, then distance will be exactly 5
        // this should return false as distance needs to be strictly more than LENGTH1

        emptyCMV.parameters.LENGTH1 = length1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertFalse(emptyCMV.lic0());
    }

    @Test
    public void lic0ReturnsTrueIfDistanceBetweeenTwoConsecutiveDataPointsMoreThanLENGTH1(){
        double[] xCoordinates = new double[]{1,5,10,20,100};
        double[] yCoordinates = new double[]{1,5,10,20,100};
        double length1 = 5;

        emptyCMV.parameters.LENGTH1 = length1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic0());
    }

    @Test
    public void lic1ReturnsFalseIfAllSetOfThreeConsecutiveDataPointsWithinRadius() {
        double[] xCoordinates = new double[]{2,1,3,4};
        double[] yCoordinates = new double[]{2,4,3,4};
        double radius = 20;

        emptyCMV.parameters.RADIUS1 = radius;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertFalse(emptyCMV.lic1());
    }

    @Test
    public void lic1ReturnsTrueIfThreeConsecutiveDataPointsNotWithinRadius() {
        double[] xCoordinates = new double[]{1,2,3,4};
        double[] yCoordinates = new double[]{1,2,3,4};
        double radius = 0.5;

        emptyCMV.parameters.RADIUS1 = radius;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic1());
    }

    // test case for when the points are collinear and an angle is not formed
    @Test
    public void testLic2CollinearPoints() {
        double[] x = new double[] { 1, 1, 1, 1, 1 };
        double[] y = new double[] { 2, 3, 5, 6, 4 };


        emptyCMV.parameters.EPSILON = 0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic2());

    }

    // test case for when an angle can be found
    @Test
    public void testLic2NonCollinearPoints() {
        double[] x = new double[] { 1, 2, 3, 4, 5 };
        double[] y = new double[] { 2, 3, 5, 6, 4 };

        emptyCMV.parameters.EPSILON = 0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertTrue(emptyCMV.lic2());

    }

    @Test
    public void testLic3(){
    }

    /**
     * Test if lic4 is met with appropriate data points and valid parameters
     * First assertion checks if condition is met with appropriate data points
     * Second assertion checks if condition is not met with data points that do not
     * make the condition true
     */
    @Test
    public void checkLic4WithQptsInMoreThanQuadsQuadrants() {

        emptyCMV.parameters.QPTS = 3;
        emptyCMV.parameters.QUADS = 2;
        emptyCMV.parameters.numPoints = 10;

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;


        // assertTrue(emptyCMV.lic4());

        // double[] x2 = new double[numPoints];
        // double[] y2 = new double[numPoints];

        // assertFalse(emptyCMV.lic4());

    }

    @Test
    public void checkLic4WithInvalidInputs() {

       /*  double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        int validQpts = 3;
        int validQuads = 2;
        int numPoints = 10;

        int invalidQpts1 = 1;
        int invalidQpts2 = 20;

        int invalidQuads1 = 0;
        int invalidQuads2 = 10;

        assertFalse(emptyCMV.lic4());
        assertFalse(emptyCMV.lic4());

        assertFalse(emptyCMV.lic4());
        assertFalse(emptyCMV.lic4());

        assertFalse(emptyCMV.lic4());
        assertFalse(emptyCMV.lic4());
 */
    } 

    @Test
    public void lic5ReturnsTrueIfTwoConsecutiveXCoordsX2MinusX1Negative(){
        double[] xCoordinates = new double[]{100,10,5,3,1};

        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;

        assertTrue(emptyCMV.lic5());
    }

    @Test
    public void lic5ReturnsFalseIfTwoConsecutiveXCoordsX2MinusX1Positive(){
        double[] xCoordinates = new double[]{1,1,3,5,10,100};
        // should return false if x[j]-x[i]=0

        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;

        assertFalse(emptyCMV.lic5());
    }

    @Test
    public void lic6ReturnsTrueIfOnePointWithinRangeOfN_PTSPointsIsAtADistanceGreaterThanDIST() {
        double[] xCoordinates = new double[]{2,3,6,4,5,6};
        double[] yCoordinates = new double[]{2,3,1,4,5,6};

        emptyCMV.parameters.DIST = 2;
        emptyCMV.parameters.NPTS = 4;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic6());
    }

    // test case for when the number of points are less than 3
    @Test
    public void testLic7NumPointsLessThan3() {
        double[] x = new double[] { 1, 1 };
        double[] y = new double[] { 2, 3 };

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic7());

    }

    // test case for when KPTS is less than 1
    @Test
    public void testLic7KPTSLessThan1() {
        double[] x = new double[] { 1, 1, 3, 4 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic7());

    }

    // test case for when KPTS is larger than numPoints - 2
    @Test
    public void testLic7KPTSLargerThanNumPointsMinus2() {
        double[] x = new double[] { 1, 1, 3, 4 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 5;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic7());

    }

    // test case for when distance is less than LENGTH1
    @Test
    public void testLic7DistanceLessThanLENGTH1() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 1;
        emptyCMV.parameters.LENGTH1 = 10.0;
        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic7());
    }

    // test case for when all conditions are met
    @Test
    public void testLic7SatisfyConditions() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 1;
        emptyCMV.parameters.LENGTH1 = 1.0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertTrue(emptyCMV.lic7());

    }

    @Test
    public void testLic8(){
    }

    //@Test
    //public void checkLic9WithCorrectlySeparatedPoints() {

        // int numPoints = 10;
        // double epsilon = 1.25;
        // int cPts = 2;
        // int dPts = 3;

        // double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        // double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        // assertTrue(emptyCMV.lic9());

        // double[] x2 = new double[numPoints];
        // double[] y2 = new double[numPoints];

        // assertFalse(emptyCMV.lic9());

    //}

    //@Test
    //public void checkLic9WithInvalidInputs() {

        // double[] x2 = new double[] { 0, 5, 10, 15, 20 };
        // double[] y2 = new double[] { 1, 3, 5, 7, 9 };
        // double epsilon = 1.25;

        // int validCPts = 2;
        // int validDPts = 3;

        // int invalidNumPoints = 4;
        // int invalidCPts1 = 0;
        // int invalidDPts1 = 0;

        // int invalidCPts2 = 5;
        // int invalidDPts2 = 5;

        // assertFalse(emptyCMV.lic9());

        // assertFalse(emptyCMV.lic9());

        // assertFalse(emptyCMV.lic9());

    //}

    @Test
    public void lic10ReturnsTrueIfAreaOfTriangleGreaterThanAREA1(){
        double[] xCoordinates = new double[]{1,1,4,6,7};
        double[] yCoordinates = new double[]{1,2,5,7,9};
        double area1 = 12;
        int epts = 1;
        int fpts = 1;

        emptyCMV.parameters.EPTS = epts;
        emptyCMV.parameters.FPTS = fpts;
        emptyCMV.parameters.AREA1 = area1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic10());
    }

    @Test
    public void lic10ReturnsFalseIfAreaOfTriangleLessThanOrEqualToAREA1(){
        double[] xCoordinates = new double[]{1,1,4,6,7};
        double[] yCoordinates = new double[]{1,2,5,7,9};
        double area1 = 12.5; //calculated area with these points is exactly 12.5, thereby should return false
        int epts = 1;
        int fpts = 1;

        emptyCMV.parameters.EPTS = epts;
        emptyCMV.parameters.FPTS = fpts;
        emptyCMV.parameters.AREA1 = area1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertFalse(emptyCMV.lic10());
    }

    @Test
    public void lic10ReturnsFalseIfNumPointsLessThan5(){
        double[] xCoordinates = new double[]{1,1,4,6};
        double[] yCoordinates = new double[]{1,2,5,7};
        double area1 = 1;
        int epts = 2;
        int fpts = 2;

        emptyCMV.parameters.EPTS = epts;
        emptyCMV.parameters.FPTS = fpts;
        emptyCMV.parameters.AREA1 = area1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertFalse(emptyCMV.lic10());
    }

    @Test
    public void lic11ReturnsTrueIfDataPointHasLargerXValueThanDataPointGPTSLaterInXCoordinetList() {
        double[] xCoordinates = new double[]{3,4,5,6,1,7};
        double[] yCoordinates = new double[]{1,1,1,1,1,1};

        emptyCMV.parameters.GPTS = 4;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic11());
    }

    // test case for when the number of points are less than 3
    @Test
    public void testLic12NumPointsLessThan3() {
        double[] x = new double[] { 1, 1 };
        double[] y = new double[] { 2, 3 };

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic12());

    }

    // test case for when LENGTH2 is less than 0
    @Test
    public void testLic12Length2LessThan0() {
        emptyCMV.parameters.LENGTH2 = -1;

        assertFalse(emptyCMV.lic12());

    }

    // test case for when distance is greater than LENGTH1
    @Test
    public void testLic12DistanceGreaterThanLength1() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 1;
        emptyCMV.parameters.LENGTH1 = 1.0;
        emptyCMV.parameters.LENGTH2 = 1.0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic12());

    }

    // test case for when distance is less than LENGTH2
    @Test
    public void testLic12DistanceLessThanLength2() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 1;
        emptyCMV.parameters.LENGTH1 = 10.0;
        emptyCMV.parameters.LENGTH2 = 10.0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertFalse(emptyCMV.lic12());

    }

    // test case for when all conditions are met
    @Test
    public void testLic12SatisfyConditions() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyCMV.parameters.KPTS = 1;
        emptyCMV.parameters.LENGTH1 = 1.0;
        emptyCMV.parameters.LENGTH2 = 20.0;

        emptyCMV.parameters.numPoints = x.length;
        emptyCMV.parameters.x = x;
        emptyCMV.parameters.y = y;

        assertTrue(emptyCMV.lic12());
    }

    @Test
    public void testLic13(){
    }

    @Test
    public void checkLic14WithCorrectlySeparatedPoints() {

        // int numPoints = 10;
        // double area1 = 3;
        // double area2 = 1000;
        // int ePts = 2;
        // int fPts = 3;

        // double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        // double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        // assertTrue(emptyCMV.lic14());

        // double[] x2 = new double[numPoints];
        // double[] y2 = new double[numPoints];

        // assertFalse(emptyCMV.lic14());
    }

    //@Test
   /*  public void checkLic14WithInvalidInputs() {
        double area1 = 3;
        int ePts = 2;
        int fPts = 3;
        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        int invalidNumPoints = 1;
        double validArea2 = 1000;
        double invalidArea2 = -1;

        assertFalse(emptyCMV.lic14());
        assertFalse(emptyCMV.lic14());
        assertFalse(emptyCMV.lic14());

    } */
}