package red.kea.javautilscollection.mybatis_mapping_class_enum;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bmr
 * @time 2020-02-29 16:17
 */
@Getter
public enum SexEnum implements BaseEnum<SexEnum,Integer> {
    MALE(1,"男"),
    FEMALE(2,"女")
    ;

    private Integer code;
    private String name;

    static Map<Integer,SexEnum> enumMap=new HashMap<>();
    static {
        for (SexEnum sexEnum:SexEnum.values()){
            enumMap.put(sexEnum.getCode(),sexEnum);
        }
    }

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum getSexEnum(Integer code){
        for(SexEnum sexEnum:SexEnum.values()){
            if(code.equals(sexEnum.code)){
               return sexEnum;
            }
        }
        return null;
    }
}
