package red.kea.javautilscollection.optimize;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @ClassName ExtractMethod(提炼函数)
 * @Author KeA
 * @Date 2019/9/6 15:41
 * @Version 1.0
 *
 * 提炼函数就是将一段代码放进一个独立函数中，并让函数名称解释该函数用途。
 *
 * 一个过于冗长的函数或者一段需要注释才能让人理解用途的代码，
 * 可以考虑把它切分成一个功能明确的函数单元，并定义清晰简短的函数名，这样会让代码变得更加优雅。
 */

//优化案例
public class ExtractMethod {
    private String name;
    private Vector<String> orders = new Vector<String>();

    public void printOwing() {
        //print banner
        System.out.println("****************");
        System.out.println("*****customer Owes *****");
        System.out.println("****************");

        //calculate totalAmount
        Enumeration env = orders.elements();
        String sss = "字符";
        while (env.hasMoreElements()) {
            String order = (String) env.nextElement();
            sss += order;
        }

        //print details
        System.out.println("name:" + name);
        System.out.println("字符:" + sss);
    }
}


class ExtractMethod1 {
    private String name;
    private Vector<String> orders = new Vector<String>();

    public void printOwing() {

        //打印 banner
        printBanner();
        //计算
        String totalAmount = getTotalAmount();
        //打印详情
        printDetail(totalAmount);
    }

    void printBanner(){
        System.out.println("****************");
        System.out.println("*****customer Owes *****");
        System.out.println("****************");
    }

    String getTotalAmount(){
        Enumeration env = orders.elements();
        String sss = "字符";
        while (env.hasMoreElements()) {
            String order = (String) env.nextElement();
            sss += order;
        }
        return sss;
    }

    void printDetail(String totalAmount){
        System.out.println("name:" + name);
        System.out.println("amount:" + totalAmount);
    }
}