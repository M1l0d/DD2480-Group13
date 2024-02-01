import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;	


public class PUMTest {

    @Test 
    public void PUMgetsCorrectMatrixWithLCMandCMVInput() {
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

        boolean[] cmv = new boolean[]{
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

        boolean[][] expectedPUM = new boolean[][] {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,},
        };

        PUM pum = new PUM(cmv, lcm);
        boolean[][] actualPUM = pum.getPUM();
        assertArrayEquals(expectedPUM, actualPUM);
    }

}
