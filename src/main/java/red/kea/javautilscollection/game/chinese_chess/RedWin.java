package red.kea.javautilscollection.game.chinese_chess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
 * @date： 2021-09-02 16:09:53
 * @version: 1.0
 * @describe:
 */
public class RedWin extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Listener ls;

    public void init() {
        this.setSize(400, 100);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.setLocationByPlatform(true);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);

        ls = new Listener();
        Listener re = new Listener();
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        JLabel jl = new JLabel("游戏结束，红方胜利");
        JButton jt1= new JButton("退出");
        JButton jt2= new JButton("返回");
        jl.setFont(new Font("微软雅黑",Font.BOLD,16));
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        jt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                re.renew();
                setVisible(false); //关闭窗口
                dispose(); //消除进程
            }
        });

        this.add(jp, BorderLayout.SOUTH);
        this.add(jl, BorderLayout.NORTH);
        jp.add(jt1);
        jp.add(jt2);
        jt1.addActionListener(ls);
        jt2.addActionListener(ls);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        ls.setG(g);
    }

    public static void mian(String[] args) {
        RedWin ot = new RedWin();
        ot.init();
    }
}
