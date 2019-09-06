package red.kea.javautilscollection.lambda_handbook.test2.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName Student
 * @Author KeA
 * @Date 2019/9/6 9:51
 * @Version 1.0
 */
@Data
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    public Student() {
    }

    public Student(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
