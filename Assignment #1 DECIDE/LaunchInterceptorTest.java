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

        for(int i = 0; i < numPoints; i++) {
            
            int randX = r.nextInt(upperBound);
            int randY = r.nextInt(upperBound);
            int[] coordPair = new int[]{randX, randY};

            // .add() returns false if there already exists an equal coordPAir in the set, if so generates a new Coordpair until uniqe one is found
            while(!coordinateSet.add(coordPair)) {
                randX = r.nextInt(upperBound);
                randX = r.nextInt(upperBound);
                coordPair = new int[]{randX, randY};
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
    
    /* Detta kan man köra bara för att testa sin funktion och se vad som sker med random data */
    @Test 
    public void printOutLaunchInterceptorRandomDataToTestFunctionOfChoice() {
        
        // Exempel
        //System.out.println("Funktion LIC1 med random data: " + randomLI.lic1());
        
        // Avkommentera för att se data
        // System.out.println("Amount of coordninates: " + coordinateSet.size());
        // for(int[] coordEntry : coordinateSet) {
        //     System.out.println("(" + coordEntry[0] + ", " + coordEntry[1] + ")");
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

    //TESTS FOR LIC3
    @Test
    public void testLIC3TriangleWithGreaterAreaThanAREA1() { //test for LIC3
        // Test case 1: Triangle with area greater than AREA1
        //should return true
        double[] x1 = {0.0, 1.0, 2.0};
        double[] y1 = {0.0, 1.0, 0.0};
        double area1 = 0.5; // smaller than the actual area
        emptyLI.numPoints = x1.length;
        emptyLI.parameters.AREA1 = area1;
        emptyLI.x = x1;
        emptyLI.y = y1;
        assertTrue(emptyLI.LIC3());
    }

    @Test
    public void testLIC3TriangleWithLessAreaThanAREA1() { //test for LIC3
        // Test case 2: Triangle with area less than AREA1
        // should return false
        double[] x2 = {0.0, 1.0, 2.0};
        double[] y2 = {0.0, 0.0, 0.0};
        double area2 = 2.0; // larger than the actual area
        emptyLI.parameters.AREA1 = area2;
        emptyLI.numPoints = x2.length;
        emptyLI.x = x2;
        emptyLI.y = y2;
        assertFalse(emptyLI.LIC3());
    }
    @Test
    public void testLIC3TriangleWithGreaterAreaThanAREA1AndNotEnoughPoints() { //test for LIC3
        // Test case 3: Not enough points for a triangle
        //should return false
        double[] x3 = {0.0, 1.0};
        double[] y3 = {0.0, 1.0};
        double area3 = 1.0;
        emptyLI.numPoints = x3.length;
        emptyLI.x = x3;
        emptyLI.y = y3;
        emptyLI.parameters.AREA1 = area3;
        assertFalse(emptyLI.LIC3());
    }

    //TESTS FOR LIC8
    @Test
    public void testLIC8ConditionsMetandCannotBeContained() {
        // Test case 1: Conditions met, data points cannot be contained in or on a circle of radius RADIUS1
        double[] x1 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y1 = {0.0, 1.0, 2.0, 1.0, 0.0};
        emptyLI.x = x1;
        emptyLI.y = y1;
        int A = 1;
        int B = 1;
        emptyLI.parameters.APTS = A;
        emptyLI.parameters.BPTS = B;
        int NUMPOINTS = x1.length;
        emptyLI.numPoints = NUMPOINTS;
        double RADIUS1 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1;
        //assertFalse(emptyLI.LIC8());
        assertTrue(emptyLI.LIC8());
    }
    @Test
    public void testLIC8ConditionNotMetnumPointsLessThan5() {
        // Test case 2: Conditions not met, NUMPOINTS < 5
        double[] x2 = {0.0, 1.0, 2.0};
        double[] y2 = {0.0, 1.0, 0.0};
        emptyLI.x = x2;
        emptyLI.y = y2;
        int A2 = 2;
        int B2 = 1;
        emptyLI.parameters.APTS = A2;
        emptyLI.parameters.BPTS = B2;
        int NUMPOINTS2 = 3; // Less than 5
        emptyLI.numPoints = NUMPOINTS2;
        double RADIUS1_2 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_2;
        assertFalse(emptyLI.LIC8());
    }
    @Test
    public void testLIC8nConditionNotMetAandBLessThanOne() {
        // Test case 3: Conditions not met, A or B is less than 1
        double[] x3 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y3 = {0.0, 1.0, 0.0, 1.0, 0.0};
        emptyLI.x = x3;
        emptyLI.y = y3;
        int A3 = 0; // Less than 1
        int B3 = 2;
        emptyLI.parameters.APTS = A3;
        emptyLI.parameters.BPTS = B3;
        int NUMPOINTS3 = 5;
        emptyLI.numPoints = NUMPOINTS3;
        double RADIUS1_3 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_3;
        assertFalse(emptyLI.LIC8());
    }

    @Test
    public void testLIC8ConditionNotMetAplusB() {
        // Test case 4: Conditions not met, A + B > NUMPOINTS - 3
        double[] x4 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y4 = {0.0, 1.0, 0.0, 1.0, 0.0};
        emptyLI.x = x4;
        emptyLI.y = y4;
        int A4 = 3;
        int B4 = 2;
        emptyLI.parameters.APTS = A4;
        emptyLI.parameters.BPTS = B4;
        int NUMPOINTS4 = 5; // A + B > 2
        emptyLI.numPoints = NUMPOINTS4;
        double RADIUS1_4 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_4;
        assertFalse(emptyLI.LIC8());
    }

    //TESTS FOR LIC8
    @Test
    public void testLIC13ConditionsMetPointsCannotContained() {
        // Test case 1: Conditions met, data points cannot be contained in or on a circle of radius RADIUS1
        double[] x1 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y1 = {0.0, 1.0, 0.0, 1.0, 0.0};
        emptyLI.x = x1;
        emptyLI.y = y1;
        int A1 = 1;
        int B1 = 1;
        emptyLI.parameters.APTS = A1;
        emptyLI.parameters.BPTS = B1;
        int NUMPOINTS = 5;
        emptyLI.numPoints = NUMPOINTS;
        double RADIUS1 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1;
        double RADIUS2 = 1.0;
        emptyLI.parameters.RADIUS2 = RADIUS2;
        assertTrue(emptyLI.LIC13());
    }
    @Test
    public void testLIC13ConditionsNotMetnumPointsLessThanFive() {
        // Test case 2: Conditions not met, NUMPOINTS < 5
        double[] x2 = {0.0, 1.0, 2.0};
        double[] y2 = {0.0, 1.0, 0.0};
        emptyLI.x = x2;
        emptyLI.y = y2;
        int A2 = 2;
        int B2 = 1;
        emptyLI.parameters.APTS = A2;
        emptyLI.parameters.BPTS = B2;
        int NUMPOINTS2 = 3; // Less than 5
        emptyLI.numPoints = NUMPOINTS2;
        double RADIUS1_2 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_2;
        double RADIUS2_2 = 1.0;
        emptyLI.parameters.RADIUS2 = RADIUS2_2;
        assertFalse(emptyLI.LIC13());
    }
    @Test
    public void testLIC13ConditionsNotMetAandBLessThanOne() {
        // Test case 3: Conditions not met, A or B is less than 1
        double[] x3 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y3 = {0.0, 1.0, 0.0, 1.0, 0.0};
        emptyLI.x = x3;
        emptyLI.y = y3;
        int A3 = 0; // Less than 1
        int B3 = 2;
        emptyLI.parameters.APTS = A3;
        emptyLI.parameters.BPTS = B3;
        int NUMPOINTS3 = 5;
        emptyLI.numPoints = NUMPOINTS3;
        double RADIUS1_3 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_3;
        double RADIUS2_3 = -1.0;
        emptyLI.parameters.RADIUS2 = RADIUS2_3;
        assertFalse(emptyLI.LIC13());
    }
        
    @Test
    public void testLIC13ConditionsNotMetAplusB() {
        // Test case 4: Conditions not met, A + B > NUMPOINTS - 3
        double[] x4 = {0.0, 1.0, 2.0, 3.0, 4.0};
        double[] y4 = {0.0, 1.0, 0.0, 1.0, 0.0};
        emptyLI.x = x4;
        emptyLI.y = y4;
        int A4 = 3;
        int B4 = 2;
        emptyLI.parameters.APTS = A4;
        emptyLI.parameters.BPTS = B4;
        int NUMPOINTS4 = 5; // A + B > 2
        emptyLI.numPoints = NUMPOINTS4;
        double RADIUS1_4 = 0.5;
        emptyLI.parameters.RADIUS1 = RADIUS1_4;
        double RADIUS2_4 = 1.0;
        emptyLI.parameters.RADIUS2 = RADIUS2_4;
        assertFalse(emptyLI.LIC13());
    }



}

