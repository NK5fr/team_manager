package util;

public enum Position {
    G(1), DLD(2), DD(3), DC(4), DG(5), DLG(6), MDC(7), MD(8), MC(9), MG(10), MOC(11), AT(12), AD(13), BU(14), AG(15);

    private int value;

    private Position(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
