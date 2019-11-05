package red.kea.javautilscollection.localDateTime_utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 实体类字段使用LocalDateTime时，如果前端传输形如2019-11-5格式的时间时
 * 如果不做处理，LocalDateTime不能直接处理。
 * 在参数传入方法前，对时间补全，不存在的时分秒的直接加上00:00:00
 * 该方法deserialize，实现的不是很好
 * 网上原文是使用hutool工具类进行了简化，但是没看懂
 *
 *
 * @ClassName MyLocalDateTimeDeserializer
 * @Description
 * @Author KeA
 * @Date 2019/11/5 17:23
 * @Version 1.0
 */
public class MyLocalDateTimeDeserializer  extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateStr = parser.getText();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = null;
        String[] split = dateStr.split(" ");
        if (split.length==1){
            dateStr = split[0]+" 00:00:00";
        }
        localDateTime = LocalDateTime.parse(dateStr,df);
        return localDateTime;
    }

    @Override
    public Class<?> handledType() {
        return LocalDateTime.class;
    }

//    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
//        String dateStr = parser.getText();
//        DateTime dateTime = null;
//        try{
//            dateTime = DateUtil.parse(dateStr);
//        }catch (Exception e){
//            dateTime = DateUtil.parseDateTime(dateStr.replaceAll("T"," "));
//        }
//        Date date = dateTime.toJdkDate();
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
//        return localDateTime;
//    }
}

