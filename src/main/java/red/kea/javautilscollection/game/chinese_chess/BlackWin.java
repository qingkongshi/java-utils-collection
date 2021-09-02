package red.kea.javautilscollection.game.chinese_chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author： KeA
 * @date： 2021-09-02 16:09:07
 * @version: 1.0
 * @describe:
 */
public class BlackWin extends JFrame {
    private static final long serialVersionUID = 1L;
    private Listener ls;

    public void init() {
        // 设置窗口属性
        this.setSize(400, 100);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.setLocationByPlatform(true);
        this.setResizable(false);

        // 设置窗口居中显示
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);

        // 添加JPanel
        JPanel jp = new JPanel();

        // 添加静态文本并设置其属性
        JLabel jl = new JLabel("游戏结束，黑方胜利");
        jl.setSize(200, 30);
        jl.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jl.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加按钮
        JButton jbt1 = new JButton("退出");
        JButton jbt2 = new JButton("返回");
        Listener re = new Listener();
        // 设置当点击退出时退出游戏
        jbt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 设置当点击确定时重新开始游戏
        jbt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                re.renew();
                setVisible(false); //关闭窗口
                dispose(); //消除进程
            }
        });

        // 把JPanel和文本添加到窗体上，并把按钮添加到JPanel上
        this.add(jl, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);
        jp.add(jbt1);
        jp.add(jbt2);

        // 创建监听器对象
        ls = new Listener();

        // 添加按钮监听器
        jbt1.addActionListener(ls);
        jbt2.addActionListener(ls);

        // 设置窗体可见
        this.setVisible(true);

        // 获取画布命名为g
        Graphics g = this.getGraphics();

        // 设置监听器监听的的画布是g画布
        ls.setG(g);
    }

    public static void mian(String[] args) {
        BlackWin ot = new BlackWin();
        ot.init();
    }
}
