package com.xseagull.boxing.constant;

/**
 *  `status` int(4) DEFAULT 0 COMMENT '订单状态(0：打样待确认；1:待生产; 2:待收货；3:已完成；11：取消订单(关闭)；)',
 *   `pay_status` int(4) DEFAULT 0 COMMENT '付款状态(0：待付定金；1：已付定金；2：已付尾款；3：已付全款；4：已退定金；5：已退款)',
 *   `merchant_status` int(4) DEFAULT '0' COMMENT '厂家状态(0: 未派单; 1: 已派单;2: 已接单;3: 已驳回)',
 */
public enum OrderMerchantEnum {
    PENDING_DISPATCH(0,"未派单"),
    COMPELTE_DISPATCH(1," 已派单;"),
    ACCEPTED_DISPATCH(2,"已接单"),
    REJECT_DISPATCH(3,"已驳回");

    private final int value;
    private final String desc;

    private OrderMerchantEnum(int i, String desc) {
        this.value = i;
        this.desc = desc;
    }

    public int getValue(){
        return this.value;
    }

    public String toString() {
        return this.value+":"+this.desc;
    }

    public String getDescByValue(String value){
        String result = null;
        for(OrderMerchantEnum ent: OrderMerchantEnum.values()){
            if(String.valueOf(ent.getValue()).equalsIgnoreCase( value )){
                result= ent.desc;
            }
        }

        if(result == null){
            throw new RuntimeException("Invalid valule: "+value);
        }

        return result;
    }
}
