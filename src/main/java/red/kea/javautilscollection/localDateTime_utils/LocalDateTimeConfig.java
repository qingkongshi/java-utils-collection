package red.kea.javautilscollection.localDateTime_utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

/**
 * @ClassName LocalDateTimeConfig
 * @Description
 * @Author KeA
 * @Date 2019/11/5 17:23
 * @Version 1.0
 */
@Configuration
public class LocalDateTimeConfig implements WebMvcConfigurer {

    /**
     * 更改jackson默认配置
     * @return
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LocalDateTime.class,new MyLocalDateTimeDeserializer());
        objectMapper.registerModule(simpleModule);

        //忽视请求报文中未匹配到的字段（多余的字段）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}