public class PUM {
    
    public boolean[][] pum;
    public boolean[] cmv;
    public Connectors[][] lcm;

    public PUM(boolean[] cmv, Connectors[][] lcm) {
        this.cmv = cmv;
        this.lcm = lcm;
        CreatePUM();
    }

    public boolean[][] getPUM() {
        return pum;
    }

    /**
     * The Conditions Met Vector (CMV) is used in conjunction with the Logical Connector
        Matrix (LCM) to form the Preliminary Unlocking Matrix (PUM). 
     */
    public void CreatePUM() {

        // Fill PUM 
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                Connectors logicalConnector = lcm[i][j];

                switch(logicalConnector) {
                    case ANDD :
                        pum[i][j] = cmv[i] && cmv[j];
                    case ORR :
                        pum[i][j] = cmv[i] || cmv[j];
                    case NOTUSED :
                        pum[i][j] = true;
                }
            }
        }
    }
}
