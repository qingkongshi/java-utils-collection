package red.kea.javautilscollection.design_pattern.observer_pattern;

import org.springframework.core.annotation.Order;

/**
 * @author： KeA
 * @date： 2021-04-11 16:51:22
 * @version: 1.0
 * @describe: 观察者模式
 *
 * 观察者模式很大的一个作用，在于实现业务的解耦
 *
 * 以用户注册为例，注册成功后有有一系列其他操作，比如发送邮件，短信，发送优惠券等
 *
 *      ┌ ┐└ ┘│  ├ ─ ┤
 *
 *                        ┌─────────────┐
 *                =====>  │EmailService │
 * ┌────────────┐         ├─────────────┤
 * │userService │ =====>  │CouponService│
 * └────────────┘         ├─────────────┤
 *                =====>  │OtherService │
 *                        └─────────────┘
 *
 * 使用观察者模式之后：
 *
 *                                                     ┌─────────────┐
 *                                             =====>  │EmailService │
 * ┌────────────┐         ┌─────────────────┐          ├─────────────┤
 * │userService │ =====>  │UserRegisterEvent│  =====>  │CouponService│
 * └────────────┘         └─────────────────┘          ├─────────────┤
 *                                             =====>  │OtherService │
 *                                                     └─────────────┘
 *
 * UserService 在完成自身的用户注册逻辑之后，仅仅只需要发布一个 UserRegisterEvent 事件，而无需关注其它拓展逻辑。
 * 其它 Service 可以自己订阅 UserRegisterEvent 事件，实现自定义的拓展逻辑
 *
 *
 * 订阅相同事件的服务可以使用@Order注解实现指定顺序执行，应该需要使用同样的方式才行
 * @Order(0) -> @Order(1)
 */
public class Readme {
}
