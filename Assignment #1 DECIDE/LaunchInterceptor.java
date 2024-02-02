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

    public static void main(String[] args) {
        LaunchInterceptor launchInterceptor = new LaunchInterceptor();
        Parameters parameters = new Parameters();

        // launchInterceptor.decide();
    }

    // Function you must write
    public boolean decide(int numPoints, double xCoords[], double yCoords[], Parameters parameters, Connectors[][] lcm,
            boolean[] puv) {

        CMV CMVOBject = new CMV(parameters);
        cmv = CMVOBject.getCmv();
        PUM PUMObject = new PUM(cmv, lcm);
        pum = PUMObject.pum;

        boolean[] fuvArr = new boolean[15];

        FUV FUVObject = new FUV(fuvArr, puv, PUMObject);

        return launch(FUVObject.getFUV());
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
