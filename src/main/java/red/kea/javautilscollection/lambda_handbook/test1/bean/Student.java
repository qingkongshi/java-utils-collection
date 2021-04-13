package red.kea.javautilscollection.lambda_handbook.test1.bean;

import lombok.Data;

/**
 * @ClassName Student
 * @Author KeA
 * @Date 2019/8/16 11:21
 * @Version 1.0
 */
@Data
public class Student {
    private String name;
    private Integer age;
    // 身高
    private Integer stature;
    // 特长
    private SpecialityEnum specialityEnum;

    public Student() {
    }

    public Student(String name, Integer age, Integer stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    public Student(String name, Integer age, Integer stature, SpecialityEnum specialityEnum) {
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.specialityEnum = specialityEnum;
    }
}
