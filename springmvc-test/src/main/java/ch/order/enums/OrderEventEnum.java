package ch.order.enums;

/**
 * @author 云岩
 * @description
 * @date 2021/5/27 上午10:04
 */
public enum OrderEventEnum {
    CREATE("创建"),
    ;

    OrderEventEnum(String state) {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
