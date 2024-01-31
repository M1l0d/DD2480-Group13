public enum CompType { 
    LT(1111),
    EQ(1112),
    GT(1113);

    private int value;

    private CompType(int value) {
        this.value = value;
    }
}