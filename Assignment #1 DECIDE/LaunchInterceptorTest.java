import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LaunchInterceptorTest {
    private LaunchInterceptor emptyLI;
    private LaunchInterceptor randomLI;
    private static int randomBoundOnParameters = 50;
    private static int numPoints = 200;

    @Before
    public void setUp() {
        randomLI = generateLaunchInterceptorWithRandomParameters(numPoints, randomBoundOnParameters);
        emptyLI = generateEmptyLaunchInterceptor();
    }

    public LaunchInterceptor generateLaunchInterceptorWithRandomParameters(int numPoints, int randomBoundOnParameters) {
        LaunchInterceptor li = new LaunchInterceptor();

        Random r = new Random();
        // Gives every parameter a random value between 0-randomBoundOnParameters
        li.parameters.LENGTH1 = r.nextInt(randomBoundOnParameters);
        li.parameters.RADIUS1 = r.nextInt(randomBoundOnParameters);
        li.parameters.EPSILON = r.nextInt(randomBoundOnParameters);
        li.parameters.AREA1 = r.nextInt(randomBoundOnParameters);
        li.parameters.QPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.QUADS = r.nextInt(randomBoundOnParameters);
        li.parameters.DIST = r.nextInt(randomBoundOnParameters);
        li.parameters.NPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.KPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.APTS = r.nextInt(randomBoundOnParameters);
        li.parameters.BPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.CPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.DPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.EPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.FPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.GPTS = r.nextInt(randomBoundOnParameters);
        li.parameters.LENGTH2 = r.nextInt(randomBoundOnParameters);
        li.parameters.RADIUS2 = r.nextInt(randomBoundOnParameters);
        li.parameters.AREA2 = r.nextInt(randomBoundOnParameters);

        li.numPoints = numPoints;
        li.x = new double[numPoints];
        li.y = new double[numPoints];

        // Creates random coordinates and assures that there are no duplicates
        Set<int[]> coordinateSet = new HashSet<>();
        int upperBound = 100;

        for (int i = 0; i < numPoints; i++) {

            int randX = r.nextInt(upperBound);
            int randY = r.nextInt(upperBound);
            int[] coordPair = new int[] { randX, randY };

            // .add() returns false if there already exists an equal coordPAir in the set,
            // if so generates a new Coordpair until uniqe one is found
            while (!coordinateSet.add(coordPair)) {
                randX = r.nextInt(upperBound);
                randX = r.nextInt(upperBound);
                coordPair = new int[] { randX, randY };
            }

            coordinateSet.add(coordPair);
            li.x[i] = coordPair[0];
            li.y[i] = coordPair[1];
        }

        return li;
    }

    public LaunchInterceptor generateEmptyLaunchInterceptor() {
        return new LaunchInterceptor();
    }

    /*
     * Detta kan man köra bara för att testa sin funktion och se vad som sker med
     * random data
     */
    @Test
    public void printOutLaunchInterceptorRandomDataToTestFunctionOfChoice() {

        // Exempel
        // System.out.println("Funktion LIC1 med random data: " + randomLI.lic1());

        // Avkommentera för att se data
        // System.out.println("Amount of coordninates: " + coordinateSet.size());
        // for(int[] coordEntry : coordinateSet) {
        // System.out.println("(" + coordEntry[0] + ", " + coordEntry[1] + ")");
        // }

        // System.out.println("\nParameters: ");
        // System.out.println(" LENGTH1 " + li.parameters.LENGTH1);
        // System.out.println(" RADIUS1 " + li.parameters.RADIUS1);
        // System.out.println(" EPSILON " + li.parameters.EPSILON);
        // System.out.println(" AREA1 " + li.parameters.AREA1);
        // System.out.println(" QPTS " + li.parameters.QPTS);
        // System.out.println(" QUADS " + li.parameters.QUADS);
        // System.out.println(" DIST " + li.parameters.DIST);
        // System.out.println(" NPTS " + li.parameters.NPTS);
        // System.out.println(" KPTS " + li.parameters.KPTS);
        // System.out.println(" APTS " + li.parameters.APTS);
        // System.out.println(" BPTS " + li.parameters.BPTS);
        // System.out.println(" CPTS " + li.parameters.CPTS);
        // System.out.println(" DPTS " + li.parameters.DPTS);
        // System.out.println(" EPTS " + li.parameters.EPTS);
        // System.out.println(" FPTS " + li.parameters.FPTS);
        // System.out.println(" GPTS " + li.parameters.GPTS);
        // System.out.println(" LENGTH2 " + li.parameters.LENGTH2);
        // System.out.println(" RADIUS2 " + li.parameters.RADIUS2);
        // System.out.println(" AREA2 " + li.parameters.AREA2);
    }

    // test case for when the points are collinear and an angle is not formed
    @Test
    public void testLic2CollinearPoints() {
        double[] x = new double[] { 1, 1, 1, 1, 1 };
        double[] y = new double[] { 2, 3, 5, 6, 4 };

        emptyLI.parameters.EPSILON = 0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic2());

    }

    // test case for when an angle can be found
    @Test
    public void testLic2NonCollinearPoints() {
        double[] x = new double[] { 1, 2, 3, 4, 5 };
        double[] y = new double[] { 2, 3, 5, 6, 4 };

        emptyLI.parameters.EPSILON = 0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertTrue(emptyLI.lic2());

    }

    // test case for when the number of points are less than 3
    @Test
    public void testLic7NumPointsLessThan3() {
        double[] x = new double[] { 1, 1 };
        double[] y = new double[] { 2, 3 };

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic7());

    }

    // test case for when KPTS is less than 1
    @Test
    public void testLic7KPTSLessThan1() {
        double[] x = new double[] { 1, 1, 3, 4 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic7());

    }

    // test case for when KPTS is larger than numPoints - 2
    @Test
    public void testLic7KPTSLargerThanNumPointsMinus2() {
        double[] x = new double[] { 1, 1, 3, 4 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 5;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic7());

    }

    // test case for when distance is less than LENGTH1
    @Test
    public void testLic7DistanceLessThanLENGTH1() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 1;
        emptyLI.parameters.LENGTH1 = 10.0;
        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic7());
    }

    // test case for when all conditions are met
    @Test
    public void testLic7SatisfyConditions() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 1;
        emptyLI.parameters.LENGTH1 = 1.0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertTrue(emptyLI.lic7());

    }

    // test case for when the number of points are less than 3
    @Test
    public void testLic12NumPointsLessThan3() {
        double[] x = new double[] { 1, 1 };
        double[] y = new double[] { 2, 3 };

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic12());

    }

    // test case for when LENGTH2 is less than 0
    @Test
    public void testLic12Length2LessThan0() {
        emptyLI.parameters.LENGTH2 = -1;

        assertFalse(emptyLI.lic12());

    }

    // test case for when distance is greater than LENGTH1
    @Test
    public void testLic12DistanceGreaterThanLength1() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 1;
        emptyLI.parameters.LENGTH1 = 1.0;
        emptyLI.parameters.LENGTH2 = 1.0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic12());

    }

    // test case for when distance is less than LENGTH2
    @Test
    public void testLic12DistanceLessThanLength2() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 1;
        emptyLI.parameters.LENGTH1 = 10.0;
        emptyLI.parameters.LENGTH2 = 10.0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertFalse(emptyLI.lic12());

    }

    // test case for when all conditions are met
    @Test
    public void testLic12SatisfyConditions() {
        double[] x = new double[] { 1, 2, 4, 5 };
        double[] y = new double[] { 2, 3, 6, 7 };

        emptyLI.parameters.KPTS = 1;
        emptyLI.parameters.LENGTH1 = 1.0;
        emptyLI.parameters.LENGTH2 = 20.0;

        emptyLI.numPoints = x.length;
        emptyLI.x = x;
        emptyLI.y = y;

        assertTrue(emptyLI.lic12());

    }

}
