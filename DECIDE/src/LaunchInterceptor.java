public class LaunchInterceptor {

    // Logical Connector Matrix
    public Connectors[][] lcm = new Connectors[15][15];

    // Preliminary Unlocking Matrix
    public boolean[][] pum = new boolean[15][15];

    // Preliminary Unlocking Vector
    public boolean[] puv = new boolean[15];

    // Conditions Met Vector
    public boolean[] cmv = new boolean[15];

    // Final Unlocking Vector
    public boolean[] fuv = new boolean[15];

    // Decision: Launch or No Launch
    public boolean launch;


    /** Function that Combines all components and input to decide wheter to launch or not. 
     *  @param numPoints - Number of datapoints
     *  @param parameters - Input parameters that are used in calculations of all Launch Interceptor Conditions
     *  @param lcm - Logical Connector Matrix describes how individual LIC’s should be logically combined.
     *  @param puv - Preliminary Unlocking Vector indicates whether the corresponding LIC is to be considered as a factor in signaling
                    interceptor launch
     *  @return  - true if launch, false if not
     */
    public boolean decide(int numPoints, Parameters parameters, Connectors[][] lcm,
            boolean[] puv) {

        CMV CMVOBject = new CMV(parameters);
        cmv = CMVOBject.getCmv();
        PUM PUMObject = new PUM(cmv, lcm);
        pum = PUMObject.pum;

        boolean[] fuvArr = new boolean[15];

        FUV FUVObject = new FUV(fuvArr, puv, PUMObject);

        return launch(FUVObject.getFUV());
    }

    // Final decision to launch or not
    /** Function that returns the final decision to launch or not
     *  @param fuv - Final Unlocking Vector
     *  @return boolean - true if launch, false if not
     */
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
