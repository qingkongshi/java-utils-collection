package red.kea.javautilscollection.string_length_utils;

/**
 * @ClassName StringLengthUtils
 * @Description 获取字符串所占的字节长度。一个汉字占用两个字节，一个其他的占用一个字节
 * @Author KeA
 * @Date 2019/11/27 10:02
 * @Version 1.0
 */
public class StringLengthUtils {
    public static int getLength(String param){
        int paramLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < param.length(); i++) {
            String temp = param.substring(i, i + 1);
            if (temp.matches(chinese)) {
                paramLength += 2;
            } else {
                paramLength += 1;
            }
        }
        return paramLength;
    }

}
