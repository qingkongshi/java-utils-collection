package red.kea.javautilscollection.lambda_handbook.test2.usage;

import red.kea.javautilscollection.lambda_handbook.test2.bean.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName Usage
 * @Author KeA
 * @Date 2019/9/6 10:23
 * @Version 1.0
 */
public class Usage {

    public static void main(String[] args) {
        List<Student> students = getList();
//        //filter（筛选）
//        testFilter(students);
//        //map(转换)
//        testMap(students);
//        //distinct(去重)
//        testDistinct1();
//        testDistinct2(students);
//        //sorted(排序)
//        testSort1();
//        testSort2(students);
//        //limit（限制返回个数）
//        testLimit(students);
//        //skip(删除元素)
//        testSkip(students);
//        //reduce(聚合)
//        testReduce();
//        //min(求最小值)
//        testMin(students);
        //anyMatch/allMatch/noneMatch（匹配）
        testMatch(students);
    }

    /**
     * 集合的筛选
     * @param students
     * @return
     */
    private static void testFilter(List<Student> students) {
        //筛选年龄大于15岁的学生
//        List<Student> streamStudents = students.stream().filter(s -> s.getAge()>24).collect(Collectors.toList());
        //筛选住在浙江省的学生
        List<Student> streamStudents = students.stream().filter(s ->"英国".equals(s.getAddress())).collect(Collectors.toList());
        streamStudents.forEach(System.out::println);
    }

    /**
     * 集合转换
     * @param students
     */
    private static void testMap(List<Student> students){
        List<String> streamAddress =  students.stream().map(student -> "国籍:"+student.getAddress()).collect(Collectors.toList());
        streamAddress.forEach(System.out::println);
    }

    /**
     * 集合去重(基本类型)
     */
    private static void testDistinct1(){
        List<String> strings = Arrays.asList("111", "222", "333", "111", "222");
        strings.stream().distinct().forEach(System.out::println);
    }

    /**
     * 集合去重（引用对象）
     */
    private static void testDistinct2(List<Student> students){
        students.add(new Student(1, "Saber", 20, "英国"));
        students.stream().distinct().forEach(System.out::println);
    }

    /**
     * 集合排序（默认排序）
     */
    private static void testSort1(){
        List<String> list = Arrays.asList("333","222","111");
        list.stream().sorted().forEach(System.out::println);
    }

    /**
     * 集合排序（指定排序规则）
     */
    private static void testSort2(List<Student> students){
        //自定义比较，可按照想要的顺序进行排序
        students.stream()
                .sorted((stu1,stu2) ->Integer.compare(stu2.getId(),stu1.getId()))
                .sorted((stu1,stu2) ->Integer.compare(stu2.getAge(),stu1.getAge()))
                .forEach(System.out::println);
        System.out.println("===================================================");
        //使用Comparator,但是这样只能按照字典序排序，不能倒叙
        students.stream().sorted(Comparator.comparingInt(student -> student.getAge())).forEach(System.out::println);
        System.out.println("===================================================");
        //使用Comparator的另一种方法，可以使用reversed()进行倒叙
        students.stream().sorted(Comparator.comparing(Student::getAge)).forEach(System.out::println);
        System.out.println("===================================================");
        students.stream().sorted(Comparator.comparing(Student::getAge).reversed()).forEach(System.out::println);

    }

    /**
     * 集合limit，返回前几个元素
     */
    private static void testLimit(List<Student> students) {
        students.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 集合skip，删除前n个元素
     */
    private static void testSkip(List<Student> students) {
        students.stream().skip(2).forEach(System.out::println);
    }

    /**
     * 集合reduce,将集合中每个元素聚合成一条数据
     */
    private static void testReduce() {
        List<String> list = Arrays.asList("欢","迎","你");
        String appendStr = list.stream().reduce("北京",(a,b) -> a+b);
        System.out.println(appendStr);
    }

    /**
     * 求集合中元素的最小值
     */
    private static void testMin(List<Student> students){
        Student minS = students.stream().min(Comparator.comparingInt(student -> student.getAge())).get();
        System.out.println(minS.toString());
    }

    /**
     * 匹配集合是否有符合条件的值
     */
    private static void testMatch(List<Student> students) {
        Boolean anyMatch = students.stream().anyMatch(s ->"英国".equals(s.getAddress()));
        if (anyMatch) {
            System.out.println("有英国人");
        }
        Boolean allMatch = students.stream().allMatch(s -> s.getAge()>=15);
        if (allMatch) {
            System.out.println("所有人都满15周岁");
        }
        Boolean noneMatch = students.stream().noneMatch(s -> "Avenger".equals(s.getName()));
        if (noneMatch) {
            System.out.println("没有Avenger");
        }
    }

    public static List getList(){
        List<Student> students = new ArrayList<>(10);
        students.add(new Student(1, "Saber", 20, "英国"));
        students.add(new Student(2, "Archer", 25, "古巴比伦"));
        students.add(new Student(3, "Lancer", 23, "爱尔兰"));
        students.add(new Student(4, "Rider", 30, "古希腊"));
        students.add(new Student(5, "Caster", 28, "法国"));
        students.add(new Student(6, "Assassin", 30, "伊拉克"));
        students.add(new Student(7, "Berserker", 700, "英国"));
        return students;
    }
}
