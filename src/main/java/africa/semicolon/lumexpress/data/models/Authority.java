package africa.semicolon.lumexpress.data.models;

public enum Authority {
    AUTH_1("SELL"),
    AUTH_2("MODERATE"),
    AUTH_3("BUY");

    private String type;

    Authority (String type) {
        this.type = type;
    }

}
