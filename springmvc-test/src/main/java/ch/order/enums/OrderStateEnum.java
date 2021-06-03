package ch.order.enums;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午5:48
 */
public enum OrderStateEnum {
    INIT("初始化"),
    NEW("新建"),
    ;

    OrderStateEnum(String state) {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
