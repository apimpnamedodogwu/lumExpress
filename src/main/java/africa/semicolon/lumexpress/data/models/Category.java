package africa.semicolon.lumexpress.data.models;

public enum Category {
    CAT_TYPE_1("FASHION"),
    CAT_TYPE_2("GAME"),
    CAT_TYPE_3("FURNITURE"),
    CAT_TYPE_4("ELECTRONICS"),
    CAT_TYPE_5("FOOD");

    private String type;
    Category (String type) {
        this.type = type;
    }
}
