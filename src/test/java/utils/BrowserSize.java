package utils;

public enum BrowserSize {
    quarter("350x720"),
    half("680x720"),
    big("680x720"),
    max("max")
    ;

    private String[] winSize;

    BrowserSize(String...winSize) {
        this.winSize = winSize;
    }

    public String getWinSize() {
        return winSize[0];
    }
}
