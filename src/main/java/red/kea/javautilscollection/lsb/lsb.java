package red.kea.javautilscollection.lsb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author： KeA
 * @date： 2021-07-09 20:25:17
 * @version: 1.0
 * @describe:
 */
public class lsb {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        File biao = new File("e:\\lsb\\biao.png");
//        File li = new File("e:\\lsb\\li.png");
        BufferedImage bi;
        byte[] bytes = new byte[0];
        try {
            bi = ImageIO.read(biao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            bytes = baos.toByteArray();
            int i = 0;
            for (byte b :bytes){
                String format = String.format("%02X", b);
                System.out.println(format);
                i++;
                if (i>10){
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bytes);
    }
}
