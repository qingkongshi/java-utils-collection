package red.kea.javautilscollection.design_pattern.observer_pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author： KeA
 * @date： 2021-04-11 16:48:55
 * @version: 1.0
 * @describe: 订阅者监听到用户注册事件后，执行发送优惠券服务
 * 方式二，注解
 */
@Slf4j
@Service
public class CouponService {

    @EventListener
    public void addCoupon(UserRegisterEvent userRegisterEvent) {
        log.info("给用户 {} 发放优惠劵", userRegisterEvent.getUsername());
    }

}
