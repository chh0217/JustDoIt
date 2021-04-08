package ch;

import java.io.Serializable;
import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2020/12/3 10:39 上午
 */
public class HealthPointInfoDTO implements Serializable {
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 商品ID列表
     */
    private List<Long> itemIds;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
