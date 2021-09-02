package red.kea.javautilscollection.game.chinese_chess;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author： KeA
 * @date： 2021-09-02 16:05:46
 * @version: 1.0
 * @describe:
 */
public class Listener extends MouseAdapter implements ActionListener {
    Graphics g;
    String action;
    int x1, y1, x2, y2, index, beindex;
    int r = -1;
    int c = -1;
    int chessflag = 1;// 红方先走为1
    DrawUI ui;
    int[][] lianbiao = new int[99999][6];// 棋子初始位置，现在的位置，棋子的编号，棋子占的位本来的棋子的编号
    int[] curchess = new int[3];
    int[] beforechess = new int[3];
    int[][] flag = new int[10][9]; // 初始化棋盘

    // 将画布传递过来
    public void setG(Graphics g) {
        this.g = g;
    }

    public void setUI(DrawUI ui) {
        this.ui = ui;
    }

    // 得到现在点击的位置
    public void getcr() {
        x2 = ((x1 - Init.x0 + Init.size / 2) / Init.size) * Init.size + Init.x0;
        y2 = ((y1 - Init.y0 + Init.size / 2) / Init.size) * Init.size + Init.y0;
        // 当前点击的位置
        c = (x2 - Init.x0) / Init.size;
        r = (y2 - Init.y0) / Init.size;
    }

    // 更新悔棋列表
    public void setLb() {
        lianbiao[index][0] = beforechess[0];
        lianbiao[index][1] = beforechess[1];
        lianbiao[index][2] = r;
        lianbiao[index][3] = c;
        lianbiao[index][4] = beforechess[2];
        lianbiao[index][5] = flag[r][c];
        index++;
    }

    // 更新现在点中的棋子
    public void recurchess() {
        if (r != -1) {
            curchess[0] = r;
            curchess[1] = c;
            curchess[2] = flag[r][c];
        }
    }

    // 更新上一次点中的棋子
    public void rebec() {
        //System.arraycopy(src, srcPos, dest, destPos, length);复制数组
        //%Arrays.copyOf(original, newLength);复制数组
        beforechess[0] = curchess[0];
        beforechess[1] = curchess[1];
        beforechess[2] = curchess[2];
    }

    // 更新黑方红方
    public void rechessflag() {
        if (chessflag == 1) {
            chessflag = 2;
        } else if (chessflag == 2) {
            chessflag = 1;
        }
    }

    public void walk(){
        setLb();// 把此棋子的前后位置保存下来
        flag[r][c] = beforechess[2];
        flag[beforechess[0]][beforechess[1]] = 0;
        ifwin();
        curchess = new int[3]; // 走完一步后curchess变为0
        beforechess = new int[3];
        c = -1;
        r = -1;
        rechessflag();
        ui.repaint();
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("点击");
        x1 = e.getX();
        y1 = e.getY();
        if (x1 > Init.x0 - Init.size / 2 && y1 > Init.y0 - Init.size / 2
                && x1 < Init.x0 + Init.size / 2 + Init.column * Init.size
                && y1 < Init.y0 + Init.row * Init.size + Init.size / 2) {
            x2 = ((x1 - Init.x0 + Init.size / 2) / Init.size) * Init.size + Init.x0;
            y2 = ((y1 - Init.y0 + Init.size / 2) / Init.size) * Init.size + Init.y0;
            // 当前点击的位置
            getcr();// 获得此时点击处的位置
            System.out.println("grtcr"+flag[r][c]);

            rebec();// 更新前一颗棋子
            ui.repaint();
            recurchess();
            if (r != -1) {
                if (curchess[2] == 0 & chessflag == 1 & beforechess[2] > 10 & ifwalk(beforechess[2]) == 1
                        | curchess[2] == 0 & chessflag == 2 & beforechess[2] < 10 & ifwalk(beforechess[2]) == 1) {// 如果此时点的地方没有棋子，直接替换
                    System.out.println("走空位");
                    walk();
                } else if (beforechess[2] > 10 & curchess[2] < 10 & chessflag == 1 & flag[r][c] < 10
                        & ifwalk(beforechess[2]) == 1) {
                    if (curchess[2] != 0) {// 如果手中有棋子
                        System.out.println("红棋吃黑棋");
                        walk();
                    }
                } else if (beforechess[2] < 10 & curchess[2] > 10 & beforechess[2] > 0 & chessflag == 2
                        & flag[r][c] > 10 & ifwalk(beforechess[2]) == 1) {
                    if (curchess[2] != 0) {// 如果手中有棋子
                        System.out.println("黑棋吃红棋");
                        walk();
                    }
                }
            }
        }
    }

    public int ifwalk(int who) {
        int ifflag = 0;
        // 将的走法
        if (who == 5) {
            if (r < 3 & c < 6 & c > 2) {
                if(beforechess[0] == curchess[0] & Math.abs(beforechess[1] - curchess[1]) == 1
                        | beforechess[1] == curchess[1] & Math.abs(beforechess[0] - curchess[0]) == 1){
                    ifflag = 1;
                }
            }
        }
        // 帅的走法
        else if (who == 55) {
            if (r > 6 & c < 6 & c > 2) {
                if (beforechess[0] == curchess[0] & Math.abs(beforechess[1] - curchess[1]) == 1
                        | beforechess[1] == curchess[1] & Math.abs(beforechess[0] - curchess[0]) == 1) {
                    ifflag = 1;
                }
            }
        }
        // 車的走法
        else if (who == 1 | who == 11) {
            if (beforechess[0] == curchess[0] | beforechess[1] == curchess[1]) {
                if (findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 0) {
                    ifflag = 1;
                }
            }
        }
        // 马的走法
        else if (who == 2 | who == 22) {
            if(beforechess[0] > 0) {
                if (beforechess[0] - curchess[0] == 2 & Math.abs(beforechess[1] - curchess[1]) == 1
                        & flag[beforechess[0] - 1][beforechess[1]] == 0) {
                    ifflag = 1;// 向上走日
                }
            }
            if(beforechess[0] < 9) {
                if (beforechess[0] - curchess[0] == -2 & Math.abs(beforechess[1] - curchess[1]) == 1
                        & flag[beforechess[0] + 1][beforechess[1]] == 0) {
                    ifflag = 1;// 向下走日
                }
            }
            if(beforechess[1] < 8) {
                if (beforechess[1] - curchess[1] == -2 & Math.abs(beforechess[0] - curchess[0]) == 1
                        & flag[beforechess[0]][beforechess[1] + 1] == 0) {
                    ifflag = 1;// 向右走日
                }
            }
            if(beforechess[1] > 0) {
                if (beforechess[1] - curchess[1] == 2 & Math.abs(beforechess[0] - curchess[0]) == 1
                        & flag[beforechess[0]][beforechess[1] - 1] == 0) {
                    ifflag = 1;// 向左走日
                }
            }
        }
        // 象的走法
        else if (who == 3 | who == 33) {
            if(beforechess[0] > 0&beforechess[1] > 0) {
                if (beforechess[0] - curchess[0] == 2 & beforechess[1] - curchess[1] == 2
                        & flag[beforechess[0] - 1][beforechess[1] - 1] == 0) {
                    ifflag = 1;// 向左上角走田
                }
            }
            if(beforechess[0] < 9&beforechess[1]  > 0) {
                if (beforechess[0] - curchess[0] == -2 & beforechess[1] - curchess[1] == 2
                        & flag[beforechess[0] + 1][beforechess[1] - 1] == 0) {
                    ifflag = 1;// 向左下角走田
                }
            }
            if(beforechess[0] > 0&beforechess[1] < 8) {
                if (beforechess[0] - curchess[0] == 2 & beforechess[1] - curchess[1] == -2
                        & flag[beforechess[0] - 1][beforechess[1] + 1] == 0) {
                    ifflag = 1;// 向右上角走田
                }
            }
            if(beforechess[0] < 9&beforechess[1] < 8) {
                if (beforechess[0] - curchess[0] == -2 & beforechess[1] - curchess[1] == -2
                        & flag[beforechess[0] + 1][beforechess[1] + 1] == 0) {
                    ifflag = 1;// 向右下角走田
                }
            }
        }
        // 士的走法
        else if (who == 4) {
            if (r < 3 & c < 6 & c > 2) {
                if(Math.abs(beforechess[1] - curchess[1])==1 & Math.abs(beforechess[0] - curchess[0])==1) {
                    ifflag = 1;
                }
            }
        }
        // 仕的走法
        else if(who == 44) {
            if (r > 6 & c < 6 & c > 2) {
                if(Math.abs(beforechess[1] - curchess[1])==1 & Math.abs(beforechess[0] - curchess[0])==1) {
                    ifflag = 1;
                }
            }
        }
        // 炮的走法
        else if(who == 6|who == 66) {
            if (beforechess[0] == curchess[0] | beforechess[1] == curchess[1]) {
                if (findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 1&curchess[2]!=0|findnumb(beforechess[0], beforechess[1], curchess[0], curchess[1]) == 0&curchess[2]==0) {
                    ifflag = 1;
                }
            }
        }
        // 卒的走法
        else if(who == 7) {
            if(beforechess[0]<5&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==-1|beforechess[0]>4&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==-1|beforechess[0]>4&beforechess[0]==curchess[0]&Math.abs(beforechess[1]-curchess[1])==1) {
                ifflag = 1;
            }
        }
        // 兵的走法
        else if(who == 77) {
            if(beforechess[0]>4&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==1|beforechess[0]<5&beforechess[1]==curchess[1]&beforechess[0]-curchess[0]==1|beforechess[0]<5&beforechess[0]==curchess[0]&Math.abs(beforechess[1]-curchess[1])==1) {
                ifflag = 1;
            }
        }
        System.out.println("ifflag="+ifflag);
        return ifflag;
    }

    // 找到某一起点到终点中含有的棋子数
    public int findnumb(int r1, int c1, int r2, int c2) {
        int numb = 0;
        if (r1 == r2) {
            for (int i = Math.min(c1, c2) + 1; i < Math.max(c1, c2); i++) {
                if (flag[r1][i] > 0) {
                    numb++;
                }
            }
        } else if (c1 == c2) {
            for (int i = Math.min(r1, r2) + 1; i < Math.max(r1, r2); i++) {
                if (flag[i][c1] > 0) {
                    numb++;
                }
            }
        }
        return numb;
    }

    public void ifwin() {
        if(chessflag == 1 & curchess[2] == 5) {
            System.out.println("红方胜利");
            new RedWin().init();
        }else if(chessflag == 2 & curchess[2] == 55) {
            System.out.println("黑方胜利");
            new BlackWin().init();
        }
    }

    public void renew() {
        flag = new int[][] { { 1, 2, 3, 4, 5, 4, 3, 2, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 6, 0, 0, 0, 0, 0, 6, 0 }, { 7, 0, 7, 0, 7, 0, 7, 0, 7 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 77, 0, 77, 0, 77, 0, 77, 0, 77 }, { 0, 66, 0, 0, 0, 0, 0, 66, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 11, 22, 33, 44, 55, 44, 33, 22, 11 } };
        chessflag = 1;
        r = -1;
        x1=0;y1=0;x2=0;y2=0;index=0;beindex=0;
        chessflag = 1;// 红方先走为1
        lianbiao = new int[99999][6];// 棋子初始位置，现在的位置，棋子的编号，棋子占的位本来的棋子的编号
        curchess = new int[3];
        beforechess = new int[3];
    }

    public void Regret_Chess(){
        r = -1;
        if (index > 0) {
            flag[lianbiao[index - 1][0]][lianbiao[index - 1][1]] = lianbiao[index - 1][4];
            flag[lianbiao[index - 1][2]][lianbiao[index - 1][3]] = lianbiao[index - 1][5];
            rechessflag();
            index--;
        }
    }

    public void actionPerformed(ActionEvent e) {
        // 获取按钮上的文字
        action = e.getActionCommand();

        if (action.equals("开始游戏")) {
            System.out.println("开始游戏");
            renew();
            ui.repaint();
        } else if (action.equals("重新开始")) {
            System.out.println("重新开始");
            renew();
            ui.repaint();
        } else if (action.equals("悔棋")) {
            System.out.println("悔棋");
            Regret_Chess();
            ui.repaint();
        }
    }
}
