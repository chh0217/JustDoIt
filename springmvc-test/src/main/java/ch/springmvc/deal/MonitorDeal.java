package ch.springmvc.deal;

import ch.springmvc.po.MonitorDealDTO;

import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午2:23
 */
public interface MonitorDeal {
    /**
     * 业务监控处理
     */
    void deal(MonitorDealDTO dealDTO);
}
