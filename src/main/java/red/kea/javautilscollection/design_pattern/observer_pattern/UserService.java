package red.kea.javautilscollection.design_pattern.observer_pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author： KeA
 * @date： 2021-04-11 16:39:38
 * @version: 1.0
 * @describe: 用户服务，注册完成后发布事件，订阅者接到通知执行相关逻辑操作
 */
@Slf4j
@Service
public class UserService implements ApplicationEventPublisherAware {

    // 实现 ApplicationEventPublisherAware 接口，从而将 ApplicationEventPublisher 注入本类。
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username){
        // 用户完成注册
        log.info("用户 {username} 完成注册",username);
        // 发布事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,username));
    }
}
