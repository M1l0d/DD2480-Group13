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

    // testing if the launch interceptor returns true when there is no correlation
    // in the LCM
    @Test
    public void testLaunchInterceptorWithNoCorrelationLCM() {

        LaunchInterceptor li = new LaunchInterceptor();

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

        assertTrue(li.decide(cmv.parameters.numPoints, cmv.parameters.x, cmv.parameters.y, cmv.parameters, lcm, PUV));

    }

    // testing if the launch interceptor returns true when the PUV is false for all
    // elements
    @Test
    public void testLaunchInterceptorWithFalsePUV() {

        LaunchInterceptor li = new LaunchInterceptor();
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

        assertTrue(li.decide(cmv.parameters.numPoints, cmv.parameters.x, cmv.parameters.y, cmv.parameters, lcm, PUV));

    }

    // testing if the launch interceptor returns false when the PUV is true but some
    // of the conditions are not met
    @Test
    public void testLaunchInterceptorWithWrongConditionsAndTruePUV() {

        LaunchInterceptor li = new LaunchInterceptor();

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

        assertFalse(li.decide(cmv.parameters.numPoints, cmv.parameters.x, cmv.parameters.y, cmv.parameters, lcm, PUV));

    }
}