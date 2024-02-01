import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LaunchInterceptorTest {

    private CMV cmv;

    @Before
    public void setUp() {

        Parameters parameters = new Parameters();

        LaunchInterceptor li = new LaunchInterceptor();

        cmv = new CMV(parameters);

    }

    @Test
    public void testLaunchInterceptorWithNoCorrelationLCM() {

        Connectors[][] lcm = {
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD }
        };

        boolean[] PUV = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };
        double[] x = { 0, 1, 2, 3 };
        double[] y = { 3, 2, 1, 3 };

        cmv.parameters.x = x;
        cmv.parameters.y = y;

        cmv.parameters.LENGTH1 = 0.8;
        cmv.parameters.RADIUS1 = 0.01;
        cmv.parameters.EPSILON = 3.13534;
        cmv.parameters.AREA1 = 3;
        cmv.parameters.QPTS = 4;
        cmv.parameters.QUADS = 4;
        cmv.parameters.DIST = 8;
        cmv.parameters.NPTS = 4;
        cmv.parameters.KPTS = 1;
        cmv.parameters.APTS = 1;
        cmv.parameters.BPTS = 1;
        cmv.parameters.CPTS = 1;
        cmv.parameters.DPTS = 1;
        cmv.parameters.EPTS = 1;
        cmv.parameters.FPTS = 1;
        cmv.parameters.GPTS = 1;
        cmv.parameters.LENGTH2 = 0;
        cmv.parameters.RADIUS2 = 0;
        cmv.parameters.AREA2 = 0;

        cmv.parameters.numPoints = 4;

        assertTrue(decide);

    }

    @Test
    public void testLaunchInterceptorWithFalsePUV() {

        // prettier-ignore
        Connectors[][] lcm = {
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD }
        };

        boolean[] PUV = { false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false };

        double[] x = { 0, 1, 2, 3 };
        double[] y = { 3, 2, 1, 3 };

        cmv.parameters.x = x;
        cmv.parameters.y = y;

        cmv.parameters.LENGTH1 = 0.8;
        cmv.parameters.RADIUS1 = 0.01;
        cmv.parameters.EPSILON = 3.13534;
        cmv.parameters.AREA1 = 3;
        cmv.parameters.QPTS = 4;
        cmv.parameters.QUADS = 4;
        cmv.parameters.DIST = 8;
        cmv.parameters.NPTS = 4;
        cmv.parameters.KPTS = 1;
        cmv.parameters.APTS = 1;
        cmv.parameters.BPTS = 1;
        cmv.parameters.CPTS = 1;
        cmv.parameters.DPTS = 1;
        cmv.parameters.EPTS = 1;
        cmv.parameters.FPTS = 1;
        cmv.parameters.GPTS = 1;
        cmv.parameters.LENGTH2 = 0;
        cmv.parameters.RADIUS2 = 0;
        cmv.parameters.AREA2 = 0;

        cmv.parameters.numPoints = 4;

        assertFalse(decide);

    }

    @Test
    public void testLaunchInterceptorWithConditionsMetAndTruePUV() {

        // prettier-ignore
        Connectors[][] lcm = {
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.ANDD,
                        Connectors.NOTUSED },
                { Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED, Connectors.NOTUSED,
                        Connectors.NOTUSED, Connectors.ANDD }
        };

        boolean[] PUV = { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };

        double[] x = { 0, 1, 2, 3 };
        double[] y = { 3, 2, 1, 3 };

        cmv.parameters.x = x;
        cmv.parameters.y = y;

        cmv.parameters.LENGTH1 = 0.8;
        cmv.parameters.RADIUS1 = 0.01;
        cmv.parameters.EPSILON = 3.13534;
        cmv.parameters.AREA1 = 3;
        cmv.parameters.QPTS = 4;
        cmv.parameters.QUADS = 4;
        cmv.parameters.DIST = 8;
        cmv.parameters.NPTS = 4;
        cmv.parameters.KPTS = 1;
        cmv.parameters.APTS = 1;
        cmv.parameters.BPTS = 1;
        cmv.parameters.CPTS = 1;
        cmv.parameters.DPTS = 1;
        cmv.parameters.EPTS = 1;
        cmv.parameters.FPTS = 1;
        cmv.parameters.GPTS = 1;
        cmv.parameters.LENGTH2 = 0;
        cmv.parameters.RADIUS2 = 0;
        cmv.parameters.AREA2 = 0;

        cmv.parameters.numPoints = 4;

        assertTrue(decide);

    }

    public boolean launch(boolean[] fuv) {

        int check = 0;

        for (int i = 0; i < fuv.length; i++) {
            if (fuv[i]) {
                check++;
            }
        }

        if (check == 15) {
            return true;
        }

        return false;
    }
}