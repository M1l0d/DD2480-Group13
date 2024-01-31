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
        launchInterceptor.decide();
    }

    // Function you must write
    public void decide() {
        Parameters parameters = new Parameters(); // Fill out fields of parameters to add data
        CMV CMVOBject = new CMV(parameters);
        cmv = CMVOBject.getCmv();
        PUM PUMObject = new PUM(cmv, lcm);
        pum = PUMObject.pum;

    }

}
