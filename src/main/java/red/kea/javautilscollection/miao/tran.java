package red.kea.javautilscollection.miao;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author： KeA
 * @date： 2021-04-21 17:36:28
 * @version: 1.0
 * @describe:
 */
public class tran {


    public static void main(String[] args) {
        String b64 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/=";
//        String[] codes = {"\u200b", "\u200c","\u200d"};
//        String[] codes = {"a","b","c"};
        String[] codes = {"喵","呜","~"};
        per(new String[4], codes, 4 - 1);


        String a = "你好";
        System.out.println("输入：" +a);
        String encode = Base64Encoder.encode(a);
//        System.out.println("base64:"+encode);
        StringBuffer sb = new StringBuffer();
        sb.append("喵");
        for (char ch :encode.toCharArray()){
            sb.append(list.get(b64.indexOf(ch)));
        }
        sb.append("喵");
        System.out.println("密文："+sb.toString());

        String sub = sb.substring(1, sb.length() - 1);
        Integer[] index = new Integer[sub.length()/4];
        for (int i = 0 ;i<sub.length()/4 ;i++){
            for (int j = 0 ;j<list.size();j++){
                if(list.get(j).equals(sub.substring(i*4,(i+1)*4))){
                    index[i] = j;
                    break;
                }
            }
        }
        StringBuffer result = new StringBuffer()  ;
        for (int in :index){
            result.append(b64.charAt(in));
        }
        System.out.println("解密："+new String(Base64Decoder.decode(result)));
    }
    static List<String> list = new ArrayList();
    public static void per(String[] buf, String[] chs, int len) {
        StringBuffer sb = new StringBuffer();
        if (len == -1) {
            for (int i = buf.length - 1; i >= 0; --i) {
//                System.out.print(buf[i]);
                sb.append(buf[i]);
            }
            list.add(sb.toString());
//            System.out.println();
            return;
        }
        for (int i = 0; i < chs.length; i++) {
            buf[len] = chs[i];
            per(buf, chs, len - 1);
        }
    }
}
