/**
 * The PUM class hold all functionality to create the Preliminary Unlocking Matrix.
 * The PUM is generated to, with use of the FUV, decide wheter to launch or not. 
 * @param pum - the Preliminary Unlocking Matrix
 * See constructor for description pf additional parameters. 
 */
public class PUM {

    public boolean[][] pum = new boolean[15][15];
    public boolean[] cmv;
    public Connectors[][] lcm;

    /**
     * Constructor for the PUM class
     * @param cmv The Conditions Met Vector (CMV)
     * @param lcm The Logical Connector Matrix (LCM)
     */
    public PUM(boolean[] cmv, Connectors[][] lcm) {
        this.cmv = cmv;
        this.lcm = lcm;
        CreatePUM();
    }

    /**
     * Return the PUM matrix
     */
    public boolean[][] getPUM() {
        return pum;
    }

    /**
     * Set the PUM matrix (used for testing)
     */
    public void setPUM(boolean[][] pum) {
        this.pum = pum;
    }

    /**
     * The Conditions Met Vector (CMV) is used in conjunction with the Logical
     * Connector Matrix (LCM) to form the Preliminary Unlocking Matrix (PUM).
     * PUM is created with this method.
     */
    public void CreatePUM() {

        // Fill PUM
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Connectors logicalConnector = lcm[i][j];

                switch (logicalConnector) {
                    case ANDD:
                        pum[i][j] = cmv[i] && cmv[j];
                        break;
                    case ORR:
                        pum[i][j] = cmv[i] || cmv[j];
                        break;
                    case NOTUSED:
                        pum[i][j] = true;
                        break;
                }
            }
        }
    }
}
