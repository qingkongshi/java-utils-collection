package red.kea.javautilscollection.lambda_handbook.test1.usage;


import red.kea.javautilscollection.lambda_handbook.test1.bean.SpecialityEnum;
import red.kea.javautilscollection.lambda_handbook.test1.bean.Student;

import java.util.*;
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

    public static void main(String[] args) throws InterruptedException {
//        testCollect();
//        testFilter();
//        testMap();
//        testFlatMap();
//        testMaxAndMin();
//        testCount();
//        testReduce();
//        listToMap();
//        testMergerList();

//        ThreadPoolExecutor threadPoolExecutor = TestFindAnyThreadPoolExecutor.getThreadPoolExecutor();
//        for (int i = 0 ;i<20;i++){
//
//            System.out.println("提交任务: " + i);
//            threadPoolExecutor.execute(new TestFindAnyTask(i));
//        }
//        System.out.println("主线程结束");
//        threadPoolExecutor.shutdown();
//        testBlocking();
//        testGrouping();
        testConnectString();
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
        List<Student> studentOne = getList();
        List<Student> studentTwo = asList(new Student("艾斯", 25, 183),
                                          new Student("雷利", 48, 176));

        List<Student> collect = Stream.of(studentOne, studentTwo)
                .flatMap(studentNew -> studentNew.stream())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 会在集合中求最大或最小值，使用流就很方便。
     * 及早求值。
     *
     * max、min接收一个Comparator（例子中使用java8自带的静态函数，只需要传进需要比较值即可。）并且返回一个Optional对象，
     * 该对象是java8新增的类，专门为了防止null引发的空指针异常。可以使用max.isPresent()判断是否有值；
     * 可以使用max.orElse(new Student())，当值为null时就使用给定值；
     * 也可以使用max.orElseGet(() -> new Student());
     * 这需要传入一个Supplier的lambda表达式。
     */
    public static void testMaxAndMin(){
        List<Student> student = getList();

        Optional<Student> max = student.stream().max(Comparator.comparing(stu -> stu.getAge()));
        Optional<Student> min = student.stream().min(Comparator.comparing(stu -> stu.getAge()));
        if (max.isPresent()){
            System.out.println(max.get());
        }
        if (min.isPresent()){
            System.out.println(min.get());
        }
    }

    /**
     * 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。
     * 及早求值
     */
    public static void testCount(){
        List<Student> student = getList();

        long count = student.stream().filter(stu -> stu.getAge() < 45).count();
        System.out.println("年龄小于45岁的人数是：" +count);
    }

    /**
     * reduce 操作可以实现从一组值中生成一个值。
     * 在上述例子中用到的 count 、 min 和 max 方法，因为常用而被纳入标准库中。
     * 事实上，这些方法都是 reduce 操作。
     * 及早求值。
     */
    public static void testReduce(){
        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc + x);
        System.out.println(reduce);
    }

    /**
     * list转map
     */
    public static void listToMap(){
        List<Student> student = getList();
        Map<String, Integer> collect = student.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
        System.out.println(collect);
    }

    /**
     * 对两个List进行循环,
     * 根据符合条件,
     * 进行相关的赋值操作并返回这个对象集合
     *
     * 例如：
     * 集合A中有
     * "路飞",22,175
     * "红发",40,180
     * "白胡子",50,185
     *
     * 集合B中有
     * "路飞", 22, 175,SING
     *
     * 要将集合B中的路飞会唱歌的属性赋给集合A中的路飞，条件是名称相同
     *
     *
     * 注意点。代码中的findFirst是集合B中第一个符合条件的对象
     * 与之对应的是findAny ，findAny并不是随机地选一个，
     * 如果是数据较少，串行地情况下，一般会返回第一个结果，
     * 如果是并行的情况，那就不能确保是第一个。
     */
    public static void testMergerList(){
        List<Student> students = getList();
        List<Student> studentTwos = getListTwo();

        List<Student> collect = students.stream().parallel()
                .map(student -> studentTwos.stream()
                        .filter(studentTwo -> student.getName().equals(studentTwo.getName()))
                        .findAny()
                        .map(studentTwo -> {
                            student.setSpecialityEnum(studentTwo.getSpecialityEnum());
                            return student;
                        }).orElse(student))
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    /**
     * 分块
     * 常用的流操作是将其分解成两个集合，Collectors.partitioningBy帮我们实现了，接收一个Predicate函数式接口。
     * □ ●   =>  □ ■
     * ○ ■       ○ ●
     */
    public static void testBlocking(){
        List<Student> students = getList();
        Map<Boolean,List<Student>> listMap = students.stream().collect(
                Collectors.partitioningBy(
                        student -> student.getSpecialityEnum().toString().contains(SpecialityEnum.SING.toString())
                )
        );
        System.out.println(listMap);
    }

    /**
     * 分组
     * 数据分组是一种更自然的分割数据操作，与将数据分成 true 和 false 两部分不同，可以使
     * 用任意值对数据分组。Collectors.groupingBy接收一个Function做转换。
     * □ ●      □ ■
     * ■ △  => △ ▲
     * ○ ▲      ○ ●
     * 如图，我们使用groupingBy将根据进行分组为圆形一组，三角形一组，正方形一组
     *
     * Collectors.groupingBy与SQL 中的 group by 操作是一样的。
     */
    public static void testGrouping(){
        List<Student> students = getList();
        Map<SpecialityEnum,List<Student>> listMap = students.stream().collect(
                Collectors.groupingBy(
                        student -> student.getSpecialityEnum()
                )
        );
        System.out.println(listMap);
    }

    /**
     * 字符串拼接
     *
     * 如果将所有学生的名字拼接起来，怎么做呢？通常只能创建一个StringBuilder，循环拼接。
     * 使用Stream，使用Collectors.joining()简单容易。
     *
     * joining接收三个参数，第一个是分界符，第二个是前缀符，第三个是结束符。
     * 前缀符和结束符可以不传入
     * Collectors.joining()中也可以不传入参数，这样就是直接拼接。
     */
    public static void testConnectString(){
        List<Student> students = getList();
        String names = students.stream().map(Student::getName).collect(Collectors.joining(",","{","}"));
        System.out.println(names);
    }



    /**
     * 获取学生集合
     */
    public static List getList(){
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞",22,175,SpecialityEnum.SING));
        students.add(new Student("红发",40,180,SpecialityEnum.DANCE));
        students.add(new Student("白胡子",50,185,SpecialityEnum.SING));
        return students;
    }
    public static List getListTwo(){
        List<Student> students = new ArrayList<>(3);
        Student student = new Student("路飞", 22, 175);
        student.setSpecialityEnum(SpecialityEnum.SING);
        students.add(student);
        return students;
    }
}
