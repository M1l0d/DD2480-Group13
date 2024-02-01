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
    public void lic0ReturnsFalseIfDistanceBetweeenTwoConsecutiveDataPointsLessThanLENGTH1() {
        double[] xCoordinates = new double[] { 1, 1, 4, 7, 10 };
        double[] yCoordinates = new double[] { 1, 1, 5, 8, 10 };
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
    public void lic0ReturnsTrueIfDistanceBetweeenTwoConsecutiveDataPointsMoreThanLENGTH1() {
        double[] xCoordinates = new double[] { 1, 5, 10, 20, 100 };
        double[] yCoordinates = new double[] { 1, 5, 10, 20, 100 };
        double length1 = 5;

        emptyCMV.parameters.LENGTH1 = length1;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertTrue(emptyCMV.lic0());
    }

    @Test
    public void lic1ReturnsFalseIfAllSetOfThreeConsecutiveDataPointsWithinRadius() {
        double[] xCoordinates = new double[] { 2, 1, 3, 4 };
        double[] yCoordinates = new double[] { 2, 4, 3, 4 };
        double radius = 20;

        emptyCMV.parameters.RADIUS1 = radius;
        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;
        emptyCMV.parameters.y = yCoordinates;

        assertFalse(emptyCMV.lic1());
    }

    @Test
    public void lic1ReturnsTrueIfThreeConsecutiveDataPointsNotWithinRadius() {
        double[] xCoordinates = new double[] { 1, 2, 3, 4 };
        double[] yCoordinates = new double[] { 1, 2, 3, 4 };
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

    // TESTS FOR LIC3
    @Test
    public void testLIC3TriangleWithGreaterAreaThanAREA1() { // test for LIC3
        // Test case 1: Triangle with area greater than AREA1
        // should return true
        double[] x1 = { 0.0, 1.0, 2.0 };
        double[] y1 = { 0.0, 1.0, 0.0 };
        double area1 = 0.5; // smaller than the actual area
        emptyCMV.parameters.numPoints = x1.length;
        emptyCMV.parameters.AREA1 = area1;
        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        assertTrue(emptyCMV.LIC3());
    }

    @Test
    public void testLIC3TriangleWithLessAreaThanAREA1() { // test for LIC3
        // Test case 2: Triangle with area less than AREA1
        // should return false
        double[] x2 = { 0.0, 1.0, 2.0 };
        double[] y2 = { 0.0, 0.0, 0.0 };
        double area2 = 2.0; // larger than the actual area
        emptyCMV.parameters.AREA1 = area2;
        emptyCMV.parameters.numPoints = x2.length;
        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;
        assertFalse(emptyCMV.LIC3());
    }

    @Test
    public void testLIC3TriangleWithGreaterAreaThanAREA1AndNotEnoughPoints() { // test for LIC3
        // Test case 3: Not enough points for a triangle
        // should return false
        double[] x3 = { 0.0, 1.0 };
        double[] y3 = { 0.0, 1.0 };
        double area3 = 1.0;
        emptyCMV.parameters.numPoints = x3.length;
        emptyCMV.parameters.x = x3;
        emptyCMV.parameters.y = y3;
        emptyCMV.parameters.AREA1 = area3;
        assertFalse(emptyCMV.LIC3());
    }

    /**
     * Test if lic4 is met with appropriate data points and valid parameters
     * First assertion checks if condition is met with appropriate data points
     * Second assertion checks if condition is not met with data points that do not
     * make the condition true
     */
    @Test
    public void testLic4WithQptsInMoreThanQuadsQuadrantsWithValidPoints() {

        emptyCMV.parameters.QPTS = 3;
        emptyCMV.parameters.QUADS = 2;
        emptyCMV.parameters.numPoints = 10;

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;

        assertTrue(emptyCMV.lic4());

    }

    @Test
    public void testLic4WithQptsInMoreThanQuadsQuadrantsWithInvalidPoints() {

        emptyCMV.parameters.QPTS = 3;
        emptyCMV.parameters.QUADS = 2;
        emptyCMV.parameters.numPoints = 10;

        double[] x2 = new double[emptyCMV.parameters.numPoints];
        double[] y2 = new double[emptyCMV.parameters.numPoints];

        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;

        assertFalse(emptyCMV.lic4());

    }

    @Test
    public void testLic4WithInvalidQpts() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.QUADS = 2;

        emptyCMV.parameters.QPTS = 1;

        assertFalse(emptyCMV.lic4());

        emptyCMV.parameters.QPTS = 20;

        assertFalse(emptyCMV.lic4());
    }

    @Test
    public void testLic4WithInvalidQuads() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.QPTS = 3;

        emptyCMV.parameters.QUADS = 0;

        assertFalse(emptyCMV.lic4());

        emptyCMV.parameters.QUADS = 10;

        assertFalse(emptyCMV.lic4());
    }

    @Test
    public void testLic4WithInvalidQptsAndQuads() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.QPTS = 1;
        emptyCMV.parameters.QUADS = 0;

        assertFalse(emptyCMV.lic4());

        emptyCMV.parameters.QPTS = 20;
        emptyCMV.parameters.QUADS = 10;

        assertFalse(emptyCMV.lic4());
    }

    @Test
    public void lic5ReturnsTrueIfTwoConsecutiveXCoordsX2MinusX1Negative() {
        double[] xCoordinates = new double[] { 100, 10, 5, 3, 1 };

        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;

        assertTrue(emptyCMV.lic5());
    }

    @Test
    public void lic5ReturnsFalseIfTwoConsecutiveXCoordsX2MinusX1Positive() {
        double[] xCoordinates = new double[] { 1, 1, 3, 5, 10, 100 };
        // should return false if x[j]-x[i]=0

        emptyCMV.parameters.numPoints = xCoordinates.length;
        emptyCMV.parameters.x = xCoordinates;

        assertFalse(emptyCMV.lic5());
    }

    @Test
    public void lic6ReturnsTrueIfOnePointWithinRangeOfN_PTSPointsIsAtADistanceGreaterThanDIST() {
        double[] xCoordinates = new double[] { 2, 3, 6, 4, 5, 6 };
        double[] yCoordinates = new double[] { 2, 3, 1, 4, 5, 6 };

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

    // TESTS FOR LIC8
    @Test
    public void testLIC8ConditionsMetandCannotBeContained() {
        // Test case 1: Conditions met, data points cannot be contained in or on a
        // circle of radius RADIUS1
        double[] x1 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y1 = { 0.0, 1.0, 2.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        int A = 1;
        int B = 1;
        emptyCMV.parameters.APTS = A;
        emptyCMV.parameters.BPTS = B;
        int NUMPOINTS = x1.length;
        emptyCMV.parameters.numPoints = NUMPOINTS;
        double RADIUS1 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1;
        // assertFalse(emptyCMV.LIC8());
        assertTrue(emptyCMV.LIC8());
    }

    @Test
    public void testLIC8ConditionNotMetnumPointsLessThan5() {
        // Test case 2: Conditions not met, NUMPOINTS < 5
        double[] x2 = { 0.0, 1.0, 2.0 };
        double[] y2 = { 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;
        int A2 = 2;
        int B2 = 1;
        emptyCMV.parameters.APTS = A2;
        emptyCMV.parameters.BPTS = B2;
        int NUMPOINTS2 = 3; // Less than 5
        emptyCMV.parameters.numPoints = NUMPOINTS2;
        double RADIUS1_2 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_2;
        assertFalse(emptyCMV.LIC8());
    }

    @Test
    public void testLIC8nConditionNotMetAandBLessThanOne() {
        // Test case 3: Conditions not met, A or B is less than 1
        double[] x3 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y3 = { 0.0, 1.0, 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x3;
        emptyCMV.parameters.y = y3;
        int A3 = 0; // Less than 1
        int B3 = 2;
        emptyCMV.parameters.APTS = A3;
        emptyCMV.parameters.BPTS = B3;
        int NUMPOINTS3 = 5;
        emptyCMV.parameters.numPoints = NUMPOINTS3;
        double RADIUS1_3 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_3;
        assertFalse(emptyCMV.LIC8());
    }

    @Test
    public void testLIC8ConditionNotMetAplusB() {
        // Test case 4: Conditions not met, A + B > NUMPOINTS - 3
        double[] x4 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y4 = { 0.0, 1.0, 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x4;
        emptyCMV.parameters.y = y4;
        int A4 = 3;
        int B4 = 2;
        emptyCMV.parameters.APTS = A4;
        emptyCMV.parameters.BPTS = B4;
        int NUMPOINTS4 = 5; // A + B > 2
        emptyCMV.parameters.numPoints = NUMPOINTS4;
        double RADIUS1_4 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_4;
        assertFalse(emptyCMV.LIC8());
    }

    @Test
    public void testLic9WithCorrectlySeparatedValidPoints() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.EPSILON = 1.25;
        emptyCMV.parameters.CPTS = 2;
        emptyCMV.parameters.DPTS = 3;

        assertTrue(emptyCMV.lic9());
    }

    @Test
    public void testLic9WithInvalidPoints() {

        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.EPSILON = 1.25;
        emptyCMV.parameters.CPTS = 2;
        emptyCMV.parameters.DPTS = 3;

        double[] x2 = new double[emptyCMV.parameters.numPoints];
        double[] y2 = new double[emptyCMV.parameters.numPoints];

        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;

        assertFalse(emptyCMV.lic9());

    }

    @Test
    public void testLic9WithInvalidNumberOfPoints() {

        double[] x2 = new double[] { 0, 5, 10, 15, 20 };
        double[] y2 = new double[] { 1, 3, 5, 7, 9 };

        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;
        emptyCMV.parameters.EPSILON = 1.25;
        emptyCMV.parameters.CPTS = 2;
        emptyCMV.parameters.DPTS = 3;

        emptyCMV.parameters.numPoints = emptyCMV.parameters.x.length - 1;

        assertFalse(emptyCMV.lic9());
    }

    @Test
    public void testLic9WithInvalidCptsAndDpts() {

        double[] x2 = new double[] { 0, 5, 10, 15, 20 };
        double[] y2 = new double[] { 1, 3, 5, 7, 9 };

        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;
        emptyCMV.parameters.EPSILON = 1.25;
        emptyCMV.parameters.numPoints = emptyCMV.parameters.x.length;

        emptyCMV.parameters.CPTS = 0;
        emptyCMV.parameters.DPTS = 0;

        assertFalse(emptyCMV.lic9());

        emptyCMV.parameters.CPTS = 5;
        emptyCMV.parameters.DPTS = 5;

        assertFalse(emptyCMV.lic9());
    }

    @Test
    public void lic10ReturnsTrueIfAreaOfTriangleGreaterThanAREA1() {
        double[] xCoordinates = new double[] { 1, 1, 4, 6, 7 };
        double[] yCoordinates = new double[] { 1, 2, 5, 7, 9 };
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
    public void lic10ReturnsFalseIfAreaOfTriangleLessThanOrEqualToAREA1() {
        double[] xCoordinates = new double[] { 1, 1, 4, 6, 7 };
        double[] yCoordinates = new double[] { 1, 2, 5, 7, 9 };
        double area1 = 12.5; // calculated area with these points is exactly 12.5, thereby should return
                             // false
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
    public void lic10ReturnsFalseIfNumPointsLessThan5() {
        double[] xCoordinates = new double[] { 1, 1, 4, 6 };
        double[] yCoordinates = new double[] { 1, 2, 5, 7 };
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
        double[] xCoordinates = new double[] { 3, 4, 5, 6, 1, 7 };
        double[] yCoordinates = new double[] { 1, 1, 1, 1, 1, 1 };

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

    // TESTS FOR LIC8
    @Test
    public void testLIC13ConditionsMetPointsCannotContained() {
        // Test case 1: Conditions met, data points cannot be contained in or on a
        // circle of radius RADIUS1
        double[] x1 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y1 = { 0.0, 1.0, 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        int A1 = 1;
        int B1 = 1;
        emptyCMV.parameters.APTS = A1;
        emptyCMV.parameters.BPTS = B1;
        int NUMPOINTS = 5;
        emptyCMV.parameters.numPoints = NUMPOINTS;
        double RADIUS1 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1;
        double RADIUS2 = 1.0;
        emptyCMV.parameters.RADIUS2 = RADIUS2;
        assertTrue(emptyCMV.LIC13());
    }

    @Test
    public void testLIC13ConditionsNotMetnumPointsLessThanFive() {
        // Test case 2: Conditions not met, NUMPOINTS < 5
        double[] x2 = { 0.0, 1.0, 2.0 };
        double[] y2 = { 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;
        int A2 = 2;
        int B2 = 1;
        emptyCMV.parameters.APTS = A2;
        emptyCMV.parameters.BPTS = B2;
        int NUMPOINTS2 = 3; // Less than 5
        emptyCMV.parameters.numPoints = NUMPOINTS2;
        double RADIUS1_2 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_2;
        double RADIUS2_2 = 1.0;
        emptyCMV.parameters.RADIUS2 = RADIUS2_2;
        assertFalse(emptyCMV.LIC13());
    }

    @Test
    public void testLIC13ConditionsNotMetAandBLessThanOne() {
        // Test case 3: Conditions not met, A or B is less than 1
        double[] x3 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y3 = { 0.0, 1.0, 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x3;
        emptyCMV.parameters.y = y3;
        int A3 = 0; // Less than 1
        int B3 = 2;
        emptyCMV.parameters.APTS = A3;
        emptyCMV.parameters.BPTS = B3;
        int NUMPOINTS3 = 5;
        emptyCMV.parameters.numPoints = NUMPOINTS3;
        double RADIUS1_3 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_3;
        double RADIUS2_3 = -1.0;
        emptyCMV.parameters.RADIUS2 = RADIUS2_3;
        assertFalse(emptyCMV.LIC13());
    }

    @Test
    public void testLIC13ConditionsNotMetAplusB() {
        // Test case 4: Conditions not met, A + B > NUMPOINTS - 3
        double[] x4 = { 0.0, 1.0, 2.0, 3.0, 4.0 };
        double[] y4 = { 0.0, 1.0, 0.0, 1.0, 0.0 };
        emptyCMV.parameters.x = x4;
        emptyCMV.parameters.y = y4;
        int A4 = 3;
        int B4 = 2;
        emptyCMV.parameters.APTS = A4;
        emptyCMV.parameters.BPTS = B4;
        int NUMPOINTS4 = 5; // A + B > 2
        emptyCMV.parameters.numPoints = NUMPOINTS4;
        double RADIUS1_4 = 0.5;
        emptyCMV.parameters.RADIUS1 = RADIUS1_4;
        double RADIUS2_4 = 1.0;
        emptyCMV.parameters.RADIUS2 = RADIUS2_4;
        assertFalse(emptyCMV.LIC13());
    }

    @Test
    public void testLic14WithCorrectlySeparatedValidPoints() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.AREA1 = 3;
        emptyCMV.parameters.AREA2 = 1000;
        emptyCMV.parameters.EPTS = 2;
        emptyCMV.parameters.FPTS = 3;

        assertTrue(emptyCMV.lic14());
    }

    @Test
    public void testLic14WithInvalidPoints() {

        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.AREA1 = 3;
        emptyCMV.parameters.AREA2 = 1000;
        emptyCMV.parameters.EPTS = 2;
        emptyCMV.parameters.FPTS = 3;

        double[] x2 = new double[emptyCMV.parameters.numPoints];
        double[] y2 = new double[emptyCMV.parameters.numPoints];

        emptyCMV.parameters.x = x2;
        emptyCMV.parameters.y = y2;

        assertFalse(emptyCMV.lic14());
    }

    @Test
    public void testLic14WithInvalidNumberOfPoints() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = x1.length - 9;
        emptyCMV.parameters.AREA1 = 3;
        emptyCMV.parameters.AREA2 = 1000;
        emptyCMV.parameters.EPTS = 2;
        emptyCMV.parameters.FPTS = 3;

        assertFalse(emptyCMV.lic14());
    }

    @Test
    public void testLic14WithInvalidArea2() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        emptyCMV.parameters.x = x1;
        emptyCMV.parameters.y = y1;
        emptyCMV.parameters.numPoints = 10;
        emptyCMV.parameters.AREA1 = 3;
        emptyCMV.parameters.AREA2 = -1;
        emptyCMV.parameters.EPTS = 2;
        emptyCMV.parameters.FPTS = 3;

        assertFalse(emptyCMV.lic14());
    }
}