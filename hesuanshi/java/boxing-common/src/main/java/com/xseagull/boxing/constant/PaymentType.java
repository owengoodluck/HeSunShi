package com.xseagull.boxing.constant;

public enum PaymentType {
    //1:全款; 2:定金; 3:尾款
    ALL("1" ),
    ADVANCE("2" ),
    REST("3");

    private final String value;

    private PaymentType(String i) {
        this.value = i;
    }

    public String getValue(){
        return this.value;
    }

}
