package red.kea.javautilscollection.design_pattern.observer_pattern;

import org.springframework.context.ApplicationEvent;

/**
 * @author： KeA
 * @date： 2021-04-11 16:36:01
 * @version: 1.0
 * @describe: 用户注册事件
 */
public class UserRegisterEvent extends ApplicationEvent {

    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username){
        super(source);
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
