package red.kea.javautilscollection.lsb;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author： KeA
 * @date： 2021-07-09 20:25:17
 * @version: 1.0
 * @describe:
 */
public class lsb {

    private static String biao_ = "e:\\lsb\\biao.png";
    private static String li_ = "e:\\lsb\\li.jpg";
    private static String hide_ = "e:\\lsb\\hide.png";

    private static String show_ = "e:\\lsb\\show.jpg";

    public static void main(String[] args) {

        encode();
        decode();

    }

    public static void encode(){
        int[] hideBit = getHideBit();
        int[] bitLength = getBitLength(hideBit);
        BufferedImage bufferedImage = readImageFile();
        hideImage(bufferedImage,hideBit,bitLength);
    }

    /**
     * 读取里图数组
     * @return
     */
    public static int[] getHideBit(){
        File li = new File(li_);
        BufferedImage bi;
        byte[] bytes;
        int[] hideBit = new int[0];
        try {
            bi = ImageIO.read(li);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
            bytes = baos.toByteArray();
            hideBit = new int[bytes.length * 8];
            int i = 0;
            for (byte b :bytes){
                String format = String.format("%02X", b);
                int sint=Integer.valueOf(format, 16);
                String binaryString = String.format("%08d",Integer.valueOf(Integer.toBinaryString(sint)));

                for(int j=0;j<8;j++) {
                    hideBit[i] = Integer.parseInt(String.valueOf(binaryString.charAt(j)));
                    i++;
                };
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return hideBit;
    }

    /**
     * 获取里图字节数组长度，用一个32位的2进制数组保存
     * @param hideBit
     * @return
     */
    public static int[] getBitLength(int[] hideBit){
        String bit_l = Integer.toBinaryString(hideBit.length/8);
        while(bit_l.length()!=32){
            bit_l='0'+bit_l;
        }
        int[] bitLength = new int[32];
        for (int i = 0 ;i<bit_l.length();i++){
            bitLength[i] = bit_l.charAt(i)-'0';
        }
        return bitLength;
    }

    /**
     * 读取表图文件
     * @return
     */
    public static BufferedImage readImageFile(){
        File biao = new File(biao_);
        BufferedImage bufferImage = null;
        try {
            bufferImage = ImageIO.read(biao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferImage;
    }

    /**
     * 隐藏图片
     * @param bufferedImage     表图
     * @param hideBit           里图
     * @param bitLength         里图大小
     */
    public static void hideImage(BufferedImage bufferedImage,int[] hideBit,int[] bitLength){
        File file = new File(hide_);
        try{
            int l_index = 0;
            int b_index = 0;
            int currentBitEntry=8;
            for (int x = 0 ;x<bufferedImage.getWidth();x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    if(x==0&&y<8){
                        int rgb = bufferedImage.getRGB(x, y);
                        Color col = new Color(rgb, true);
                        int r = col.getRed();
                        int g = col.getGreen();
                        int b = col.getBlue();
                        int a = col.getAlpha();

                        // 红色最后一位替换
                        String r_bin = Integer.toBinaryString(r);
                        String r_sub = r_bin.substring(0, r_bin.length()-1);
                        r_sub += bitLength[l_index++];
                        Integer r_new = Integer.valueOf(r_sub, 2);

                        // 绿色最后一位替换
                        String g_bin = Integer.toBinaryString(g);
                        String g_sub = g_bin.substring(0, g_bin.length()-1);
                        g_sub += bitLength[l_index++];
                        Integer g_new = Integer.valueOf(g_sub, 2);

                        // 蓝色最后一位替换
                        String b_bin = Integer.toBinaryString(b);
                        String b_sub = b_bin.substring(0, b_bin.length()-1);
                        b_sub += bitLength[l_index++];
                        Integer b_new = Integer.valueOf(b_sub, 2);

                        // 透明度最后一位替换
                        String a_bin = Integer.toBinaryString(a);
                        String a_sub = a_bin.substring(0, a_bin.length()-1);
                        a_sub += bitLength[l_index++];
                        Integer a_new = Integer.valueOf(a_sub, 2);

                        // 将新颜色封装写入文件
                        Color col_new = new Color(r_new,g_new,b_new,a_new);
                        int rgb_new = col_new.getRGB();
                        bufferedImage.setRGB(x,y,rgb_new);

                    }else if (currentBitEntry < hideBit.length/4+8){
                        int rgb = bufferedImage.getRGB(x, y);
                        Color col = new Color(rgb, true);
                        int r = col.getRed();
                        int g = col.getGreen();
                        int b = col.getBlue();
                        int a = col.getAlpha();

                        String r_bin = Integer.toBinaryString(r);
                        String r_sub = r_bin.substring(0, r_bin.length()-1);
                        r_sub += hideBit[b_index++];
                        Integer r_new = Integer.valueOf(r_sub, 2);

                        String g_bin = Integer.toBinaryString(g);
                        String g_sub = g_bin.substring(0, g_bin.length()-1);
                        g_sub += hideBit[b_index++];
                        Integer g_new = Integer.valueOf(g_sub, 2);

                        String b_bin = Integer.toBinaryString(b);
                        String b_sub = b_bin.substring(0, b_bin.length()-1);
                        b_sub += hideBit[b_index++];
                        Integer b_new = Integer.valueOf(b_sub, 2);

                        String a_bin = Integer.toBinaryString(a);
                        String a_sub = a_bin.substring(0, a_bin.length()-1);
                        a_sub += hideBit[b_index++];
                        Integer a_new = Integer.valueOf(a_sub, 2);

                        // 将新颜色封装写入文件
                        Color col_new = new Color(r_new,g_new,b_new,a_new);
                        int rgb_new = col_new.getRGB();
                        bufferedImage.setRGB(x,y,rgb_new);
                        currentBitEntry++;
                    }
                }
            }
            ImageIO.write(bufferedImage, "png", file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //=====================================================================================================================//


    public static void decode(){
        BufferedImage bufferedImage = readHideImageFile();
        showImage(bufferedImage);
    }

    /**
     * 读取加密后的图片文件
     * @return
     */
    public static BufferedImage readHideImageFile(){
        File biao = new File(hide_);
        BufferedImage bufferImage = null;
        try {
            bufferImage = ImageIO.read(biao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferImage;
    }

    /**
     * 解密隐藏图
     * @param bufferedImage
     */
    public static void showImage(BufferedImage bufferedImage){
        String bx_msg="";
        String b_msg="";
        int len = 0;
        int currentBitEntry=0;
        for (int x = 0 ;x<bufferedImage.getWidth();x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                if(x==0&&y<8){
                    int rgb = bufferedImage.getRGB(x, y);
                    Color col = new Color(rgb, true);
                    int r = col.getRed();
                    int g = col.getGreen();
                    int b = col.getBlue();
                    int a = col.getAlpha();
                    String r_bin = Integer.toBinaryString(r);
                    bx_msg+=r_bin.charAt(r_bin.length()-1);

                    String g_bin = Integer.toBinaryString(g);
                    bx_msg+=g_bin.charAt(g_bin.length()-1);

                    String b_bin = Integer.toBinaryString(b);
                    bx_msg+=b_bin.charAt(b_bin.length()-1);

                    String a_bin = Integer.toBinaryString(a);
                    bx_msg+=a_bin.charAt(a_bin.length()-1);

                    len=Integer.parseInt(bx_msg,2);

                }else if(currentBitEntry < len*4){
                    int rgb = bufferedImage.getRGB(x, y);
                    Color col = new Color(rgb, true);
                    int r = col.getRed();
                    int g = col.getGreen();
                    int b = col.getBlue();
                    int a = col.getAlpha();
                    String r_bin = Integer.toBinaryString(r);
                    b_msg+=r_bin.charAt(r_bin.length()-1);

                    String g_bin = Integer.toBinaryString(g);
                    b_msg+=g_bin.charAt(g_bin.length()-1);

                    String b_bin = Integer.toBinaryString(b);
                    b_msg+=b_bin.charAt(b_bin.length()-1);

                    String a_bin = Integer.toBinaryString(a);
                    b_msg+=a_bin.charAt(a_bin.length()-1);
                    currentBitEntry++;
                }
            }
        }
        byte[] bytes = getBytes(b_msg);
        saveImage(bytes,show_);
    }

    /**
     * 二进制的字符串转为byte数组
     * @param str
     * @return
     */
    private static byte[] getBytes(String str){
        int size = str.length()/8;
        //定义接收数组
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            //每次截取8位计算
            String tmp = str.substring(8*i,8*(i+1));
            int tmpInt = Integer.parseInt(tmp,2);
            byte tmpByte = (byte)(tmpInt & 0xff);
            bytes[i] = tmpByte;
        }
        return bytes;
    }

    /**
     * byte数组保存图片
     * @param data
     * @param path
     */
    public static void saveImage(byte[] data,String path) {
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}
