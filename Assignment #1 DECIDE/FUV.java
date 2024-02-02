public class FUV {

        public boolean[] fuvArr;
        public boolean[] puv;
        public boolean[][] pumArray;

        public FUV(boolean[] fuvArr, boolean[] puv, PUM pum) {

                this.fuvArr = fuvArr;
                this.puv = puv;
                this.pumArray = pum.getPUM();
                generateFUV();
        }

        /**
         * Generate FUV with 15 boolean values
         */
        public void generateFUV() {

                for (int i = 0; i < pumArray.length; i++) {
                        boolean pumConditions = true;
                        for (int j = 0; j < pumArray[0].length; j++) {
                                if (i != j) { // skip indexes with same row and column number
                                        if (!(pumArray[i][j]))
                                                pumConditions = false;
                                }
                        }
                        fuvArr[i] = pumConditions;
                }

                for (int i = 0; i < fuvArr.length; i++) {
                        if (!puv[i]) {
                                fuvArr[i] = true;
                        }
                }
        }

        public boolean[] getFUV() {
                return this.fuvArr;
        }

}