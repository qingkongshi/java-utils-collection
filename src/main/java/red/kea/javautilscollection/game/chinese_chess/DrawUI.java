package red.kea.javautilscollection.game.chinese_chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

/**
 * @author： KeA
 * @date： 2021-09-02 16:04:16
 * @version: 1.0
 * @describe:
 */
public class DrawUI extends JPanel {
    private static final long serialVersionUID = 1L;
    Listener ls = new Listener();
    public void initui() {
        // 创建面板
        JFrame jf = new JFrame();
        // 设置面板属性
        jf.setSize(1240, 860);
        jf.setTitle("中国象棋");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(Color.WHITE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);// 设置窗体不可放缩
        // 添加JPanel
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(450, 1));
        jp.setBackground(Color.white);
        jp.setLayout(null);
        jf.add(jp, BorderLayout.EAST);
        // 添加JLabel
        JLabel jl = new JLabel("中国象棋") {
            private static final long serialVersionUID = 1L;
            Image jli = new ImageIcon("e:\\image\\"+"中国象棋.png").getImage();
            public void paint(Graphics g) {
                g.drawImage(jli, 0, 0,400, 204, null);
            }
        };
        jl.setBounds(0,0, 400, 204);
        jp.add(jl);
        // 把this添加到JFrame中
        this.setBackground(Color.white);
        jf.add(this);
        // 添加按钮
        String[] ShapeBtn = { "开始游戏", "重新开始", "悔棋" };
        for (int i = 0; i < ShapeBtn.length; i++) {
            String name = ShapeBtn[i];
            JButton jbt = new JButton(name) {
                private static final long serialVersionUID = 1L;
                Image jbti = new ImageIcon("e:\\image\\"+name+".png").getImage();
                public void paint(Graphics g) {
                    g.drawImage(jbti, 0, 0,250, 100, null);
                }
            };
            jbt.setBounds(100, 260+150*i, 250, 100);
            jbt.addActionListener(ls);
            jp.add(jbt);
        }
        // 给画板添加监听器
        jf.addMouseListener(ls);
        jf.setVisible(true);
        Graphics g = jf.getGraphics();
        ls.setG(g);
        ls.setUI(this);
    }

    // 重绘
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(new ImageIcon("e:\\image\\"+"棋盘.jpg").getImage(), 90, 60, 625, 700, this);
        // 根据flag画棋子
        for (int i = 0; i < Init.row; i++) {
            for (int j = 0; j < Init.column; j++) {
                if (ls.flag[i][j] > 0) {
                    g.drawImage(new ImageIcon("e:\\image\\"+(Integer.toString(ls.flag[i][j])) + ".png").getImage(), Init.y0 + j * Init.size - Init.chesssize / 2,Init.x00 + i * Init.size - Init.chesssize / 2,Init.chesssize, Init.chesssize, this);
                }
            }
        }

        if(ls.r != -1) {
            if(ls.flag[ls.r][ls.c] > 0) {
                if(ls.chessflag == 1&ls.flag[ls.r][ls.c] > 10 | ls.chessflag == 2&ls.flag[ls.r][ls.c] < 10) {
                    int newexsize = 8;
                    g.drawImage(new ImageIcon("e:\\image\\"+(Integer.toString(ls.flag[ls.r][ls.c]) + ".png")).getImage(), Init.y0 + ls.c * Init.size - (Init.chesssize+newexsize) / 2,Init.x00 + ls.r * Init.size - (Init.chesssize+newexsize) / 2,Init.chesssize+newexsize, Init.chesssize+newexsize, this);
                }
            }
        }
    }

    public static void main(String args[]) {
        DrawUI ui = new DrawUI();
        ui.initui();
    }
}
