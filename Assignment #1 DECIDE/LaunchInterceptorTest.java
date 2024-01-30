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

    /**
     * Test if lic4 is met with appropriate data points and valid parameters
     * First assertion checks if condition is met with appropriate data points
     * Second assertion checks if condition is not met with data points that do not
     * make the condition true
     */

    @Test
    public void checkLic4WithQptsInMoreThanQuadsQuadrants() {

        int qPts = 3;
        int quads = 2;
        int numPoints = 10;

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        assertTrue(LaunchInterceptor.lic4(x1, y1, qPts, quads, numPoints));

        double[] x2 = new double[numPoints];
        double[] y2 = new double[numPoints];

        assertFalse(LaunchInterceptor.lic4(x2, y2, qPts, quads, numPoints));

    }

    @Test
    public void checkLic4WithInvalidInputs() {

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        int validQpts = 3;
        int validQuads = 2;
        int numPoints = 10;

        int invalidQpts1 = 1;
        int invalidQpts2 = 20;

        int invalidQuads1 = 0;
        int invalidQuads2 = 10;

        assertFalse(LaunchInterceptor.lic4(x1, y1, invalidQpts1, invalidQuads1, numPoints));
        assertFalse(LaunchInterceptor.lic4(x1, y1, invalidQpts2, invalidQuads2, numPoints));

        assertFalse(LaunchInterceptor.lic4(x1, y1, validQpts, invalidQuads2, numPoints));
        assertFalse(LaunchInterceptor.lic4(x1, y1, validQpts, invalidQuads1, numPoints));

        assertFalse(LaunchInterceptor.lic4(x1, y1, invalidQpts1, validQuads, numPoints));
        assertFalse(LaunchInterceptor.lic4(x1, y1, invalidQpts1, validQuads, numPoints));

    }

    @Test
    public void checkLic9WithCorrectlySeparatedPoints() {

        int numPoints = 10;
        double epsilon = 1.25;
        int cPts = 2;
        int dPts = 3;

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        assertTrue(LaunchInterceptor.lic9(x1, y1, Math.PI, epsilon, cPts, dPts, numPoints));

        double[] x2 = new double[numPoints];
        double[] y2 = new double[numPoints];

        assertFalse(LaunchInterceptor.lic9(x2, y2, Math.PI, epsilon, cPts, dPts, numPoints));

    }

    @Test
    public void checkLic9WithInvalidInputs() {

        double[] x2 = new double[] { 0, 5, 10, 15, 20 };
        double[] y2 = new double[] { 1, 3, 5, 7, 9 };
        double epsilon = 1.25;

        int validCPts = 2;
        int validDPts = 3;

        int invalidNumPoints = 4;
        int invalidCPts1 = 0;
        int invalidDPts1 = 0;

        int invalidCPts2 = 5;
        int invalidDPts2 = 5;

        assertFalse(LaunchInterceptor.lic9(x2, y2, Math.PI, epsilon, invalidCPts1, invalidDPts1, x2.length));

        assertFalse(LaunchInterceptor.lic9(x2, y2, Math.PI, epsilon, validCPts, validDPts, invalidNumPoints));

        assertFalse(LaunchInterceptor.lic9(x2, y2, Math.PI, epsilon, invalidCPts2, invalidDPts2, x2.length));

    }

    @Test
    public void checkLic14WithCorrectlySeparatedPoints() {

        int numPoints = 10;
        double area1 = 3;
        double area2 = 1000;
        int ePts = 2;
        int fPts = 3;

        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        assertTrue(LaunchInterceptor.lic14(x1, y1, area1, area2, ePts, fPts, numPoints));

        double[] x2 = new double[numPoints];
        double[] y2 = new double[numPoints];

        assertFalse(LaunchInterceptor.lic14(x2, y2, area1, area2, ePts, fPts, numPoints));
    }

    @Test
    public void checkLic14WithInvalidInputs() {
        double area1 = 3;
        int ePts = 2;
        int fPts = 3;
        double[] x1 = new double[] { 0, 1, 2, -5, -78, 45, 30.0, 30, 45, 1 };
        double[] y1 = new double[] { 0, 1, 2, -6, 4, 67.5, -19, 90, 56, 7 };

        int invalidNumPoints = 1;
        double validArea2 = 1000;
        double invalidArea2 = -1;

        assertFalse(LaunchInterceptor.lic14(x1, y1, area1, invalidArea2, ePts, fPts, invalidNumPoints));
        assertFalse(LaunchInterceptor.lic14(x1, y1, area1, invalidArea2, ePts, fPts, x1.length));
        assertFalse(LaunchInterceptor.lic14(x1, y1, area1, validArea2, ePts, fPts, invalidNumPoints));

    }

}
