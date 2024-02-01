import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class FUVTest {

    Connectors[][] lcm = new Connectors[][]{
        {Connectors.ANDD, Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR},
        {Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ORR, Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ORR, Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ORR, Connectors.ORR},
        {Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD},
        {Connectors.ANDD, Connectors.ORR, Connectors.ANDD,Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD},
        {Connectors.NOTUSED, Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR},
        {Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD},
        {Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR},
        {Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED},
        {Connectors.ANDD, Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR},
        {Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD},
        {Connectors.NOTUSED, Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD,Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR},
        {Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR, Connectors.ANDD},
        {Connectors.ORR, Connectors.ORR, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ORR},
        {Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD, Connectors.ANDD},
        {Connectors.ORR, Connectors.ORR, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ANDD, Connectors.ORR, Connectors.NOTUSED, Connectors.ORR, Connectors.ANDD, Connectors.ORR, Connectors.ANDD}
    };

    boolean[] cmv = new boolean[] {
            true,
            false,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
    };

    boolean[][] pumAllTrue = new boolean[15][15];
    boolean[][] pumAllFalse = new boolean[15][15];
    boolean[][] pumMixed = new boolean[][] {
        {true, true ,true ,true ,true ,false, true, true, true, true, true, true, true, true, true}, 
        {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
        {true, true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true ,true , true},
        {true, true ,true ,true ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , true},
        {true, true ,true ,false ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , false},
        {true, true ,true ,false ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , false}, 
        {true, true ,true ,false ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , false},
        {true, true ,true ,false ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , false},
        {true, true ,true ,false ,true ,false ,true ,true ,true ,true ,true ,true ,true ,true , false},
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true},
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true},
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true}, 
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true},
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true},
        {true, true ,false ,true ,true ,true ,true ,true ,false ,false ,true ,true ,true ,true , true},
    };

    boolean[] puvAllTrue = new boolean[] {
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
    };

    boolean[] puvAllFalse = new boolean[] {
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
    };

    boolean[] puvMixed = new boolean[] {
            true,
            true,
            false,
            true,
            true,
            false,
            false,
            true,
            true,
            true,
            false,
            true,
            true,
            true,
            true,
            false,
    };

    boolean[] fuvExpected1 = new boolean[] {
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
    };

    boolean[] fuvExpected2 = new boolean[] {
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
    };

    boolean[] fuvExpected3 = new boolean[] {
            false,
            false,
            true,
            false,
            false,
            true,
            true,
            false,
            false,
            false,
            true,
            false,
            false,
            false,
            false,
    };

    public void populatePUM(boolean[][] pum) {

        for(int i=0; i < pum.length; i++) {
            for(int j=0; j < pum[0].length; j++) {
                pum[i][j] = true;
            }
        }
    }

    
    
    @Before
    public void setUp() {
        
    }

    @Test
    public void testFUVWhenPUVAndPUMContainAllTrue() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        populatePUM(pumAllTrue);
        pum.setPUM(pumAllTrue);
        FUV fuvObj = new FUV(fuv, puvAllTrue, pum);

        assertArrayEquals(fuvExpected1, fuvObj.getFUV());
    }

    @Test
    public void testFUVWhenPUVContainsAllTrueAndPUMContainsAllFalse() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        pum.setPUM(pumAllFalse);
        FUV fuvObj = new FUV(fuv, puvAllTrue, pum);

        assertArrayEquals(fuvExpected2, fuvObj.getFUV());
    }

    @Test
    public void testFUVWhenPUVAndPUMContainAllFalse() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        pum.setPUM(pumAllFalse);
        FUV fuvObj = new FUV(fuv, puvAllFalse, pum);

        assertArrayEquals(fuvExpected1, fuvObj.getFUV());
    }

    @Test
    public void testFUVWhenPUMContainsMixedAndPUVContainsAllFalse() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        pum.setPUM(pumMixed);
        FUV fuvObj = new FUV(fuv, puvAllFalse, pum);

        assertArrayEquals(fuvExpected1, fuvObj.getFUV());
    }

    @Test
    public void testFUVWhenPUMContainsAllTrueAndPUVContainsMixed() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        populatePUM(pumAllTrue);
        pum.setPUM(pumAllTrue);
        FUV fuvObj = new FUV(fuv, puvMixed, pum);

        assertArrayEquals(fuvExpected1, fuvObj.getFUV());
    }

    @Test
    public void testFUVWhenPUMContainsAllFalseAndPUVContainsMixed() {
        PUM pum = new PUM(cmv, lcm);
        boolean[] fuv = new boolean[15];
        pum.setPUM(pumAllFalse);
        FUV fuvObj = new FUV(fuv, puvMixed, pum);

        assertArrayEquals(fuvExpected3, fuvObj.getFUV());

    }
}
