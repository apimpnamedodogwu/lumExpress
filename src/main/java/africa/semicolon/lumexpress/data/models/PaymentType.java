package africa.semicolon.lumexpress.data.models;

public enum PaymentType {
    TYPE_1("CARD"),
    TYPE_2("PAY_ON_DELIVERY");
    
    private String type;

    PaymentType(String paymentType) {
        type = paymentType;
    }


}
