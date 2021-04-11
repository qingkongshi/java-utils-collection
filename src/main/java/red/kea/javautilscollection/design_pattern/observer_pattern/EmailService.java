package red.kea.javautilscollection.design_pattern.observer_pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author： KeA
 * @date： 2021-04-11 16:44:47
 * @version: 1.0
 * @describe: 订阅者监听到用户注册事件后，执行发送邮件服务
 * 方式一：实现事件监听类
 */
@Slf4j
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Async
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        // 执行发送邮件操作
        log.info("给用户 {} 发送邮件",userRegisterEvent.getUsername());
    }
}
