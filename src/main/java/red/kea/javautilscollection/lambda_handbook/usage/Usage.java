package red.kea.javautilscollection.lambda_handbook.usage;

import red.kea.javautilscollection.lambda_handbook.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @ClassName Usage
 * @Author KeA
 * @Date 2019/8/16 14:07
 * @Version 1.0
 */
public class Usage {

    /**
     * 惰性求值：只描述Stream，操作的结果也是Stream，这样的操作称为惰性求值。
     *          惰性求值可以像建造者模式一样链式使用，最后再使用及早求值得到最终结果。
     *
     * 及早求值：得到最终的结果而不是Stream，这样的操作称为及早求值。
     */

    public static void main(String[] args) {
//        testCollect();
//        testFilter();
//        testMap();
        testFlatMap();
    }

    /**
     * collect(Collectors.toList())
     * 将流转换为list。还有toSet()，toMap()等。
     * 及早求值。
     */
    public static void testCollect() {
        List<Student> studentList = Stream.of(
                new Student("路飞", 22, 175),
                new Student("红发", 40, 180),
                new Student("白胡子", 50, 185)).collect(Collectors.toList());
        System.out.println(studentList);
    }

    /**
     * 顾名思义，起过滤筛选的作用。内部就是Predicate接口。
     * 惰性求值。
     *
     * 比如我们筛选出出身高小于180的同学。
     */
    public static void testFilter(){
        List<Student> students = getList();

        List<Student> collect = students.stream().filter(n -> n.getStature() < 180).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 转换功能，内部就是Function接口。
     * 惰性求值
     */
    public static void testMap(){
        List<Student> students = getList();

        List<String> collect = students.stream().map(name -> name.getName()).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 将多个Stream合并为一个Stream。
     * 惰性求值
     */
    public static void testFlatMap(){
        List<Student> students = getList();

        List<Student> collect = Stream.of(students, asList(
                new Student("艾斯", 25, 183),
                new Student("雷利", 48, 176)))
                .flatMap(students1 -> students1.stream())
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    public static void test(){
        //        List<RepertoryFrozenNumberDTO> list = repertoryFrozenNumberDTOS.stream()
//                .map(repertoryFrozenNumberDTO -> outboundOrderNumberVOS.stream()
//                        .filter(outboundOrderNumberVO -> repertoryFrozenNumberDTO.getContractId().equals(outboundOrderNumberVO.getContractId()))
//                        .findFirst()
//                        .map(outboundOrderNumberVO -> {
//                            repertoryFrozenNumberDTO.setNotOutNumber(outboundOrderNumberVO.getNumber());
//                            return repertoryFrozenNumberDTO;
//                        }).orElse(repertoryFrozenNumberDTO))
//                .collect(Collectors.toList());
    }
    /**
     * 获取学生集合
     */
    public static List getList(){
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞",22,175));
        students.add(new Student("红发",40,180));
        students.add(new Student("白胡子",50,185));
        return students;
    }
}
