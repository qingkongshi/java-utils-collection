package red.kea.javautilscollection.game.tic_tac_toe;

import java.util.Scanner;

/**
 * @author： KeA
 * @date： 2021-09-01 10:56:19
 * @version: 1.0
 * @describe: 井字棋
 *
 * 1. 题目
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 *
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 *
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 *   1 . 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 *
 *   2 . 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 *
 *   3 . 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 * 示例:
 * 给定棋盘边长 n = 3, 玩家 1 的棋子符号是 "X"，玩家 2 的棋子符号是 "O"。
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> 函数返回 0 (此时，暂时没有玩家赢得这场对决)
 * |X| | |
 * | | | |    // 玩家 1 在 (0, 0) 落子。
 * | | | |
 *
 * toe.move(0, 2, 2); -> 函数返回 0 (暂时没有玩家赢得本场比赛)
 * |X| |O|
 * | | | |    // 玩家 2 在 (0, 2) 落子。
 * | | | |
 *
 * toe.move(2, 2, 1); -> 函数返回 0 (暂时没有玩家赢得比赛)
 * |X| |O|
 * | | | |    // 玩家 1 在 (2, 2) 落子。
 * | | |X|
 *
 * toe.move(1, 1, 2); -> 函数返回 0 (暂没有玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 2 在 (1, 1) 落子。
 * | | |X|
 *
 * toe.move(2, 0, 1); -> 函数返回 0 (暂无玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 1 在 (2, 0) 落子。
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> 函数返回 0 (没有玩家赢得比赛)
 * |X| |O|
 * |O|O| |    // 玩家 2 在 (1, 0) 落子.
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> 函数返回 1 (此时，玩家 1 赢得了该场比赛)
 * |X| |O|
 * |O|O| |    // 玩家 1 在 (2, 1) 落子。
 * |X|X|X|
 *
 */
public class Test {

    static int[][] a = {{0,0,0},{0,0,0},{0,0,0}};
    static int one,two,three,four,five,six,seven,eight,nine;

    public static void main(String[] args) {
        out:while (true){
            print();
            boolean player = true;
            while (true){

                Scanner scan = new Scanner(System.in);
                if (player){
                    System.out.println("请一号玩家落子");
                }else{
                    System.out.println("请二号玩家落子");
                }

                String inputString = scan.next();
                String[] split = inputString.split(",");
                //落子
                boolean moveSuccess = move(Integer.valueOf(split[0]),Integer.valueOf(split[1]),player);
                if (moveSuccess){
                    print();
                    player = !player;
                }
                //判断胜负
                int win = win();
                boolean remark = false;
                if (win==1){
                    if (!player){
                        System.out.println("一号玩家获胜");
                    }else{
                        System.out.println("二号玩家获胜");
                    }
                    remark = true;
                }else if(win==2){
                    System.out.println("平局");
                    remark = true;
                }
                //重新开始
                if (remark){
                    System.out.println("重新开始? (y/n)");
                    String isRemark = scan.next();
                    if (isRemark.equals("y")||isRemark.equals("Y")){
                        a = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
                        continue out;
                    }else{
                        System.out.println("程序已停止");
                        break out;
                    }
                }
            }
        }
    }

    //打印棋盘
    private static void print(){
        for(int i = 0 ;i<a.length; i++){
            System.out.print("|");
            for (int j=0 ;j<a[i].length;j++){
                if (a[i][j]==1){
                    System.out.print("O|");
                }else if(a[i][j]==2){
                    System.out.print("X|");
                }else{
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }

    /**
     * 落子
     * @param x
     * @param y
     * @param player
     * @return
     */
    private static boolean move(int x, int y,boolean player){
        if (x>2||y>2){
            System.out.println("超出棋盘位置");
            return false;
        }
        if (a[x][y]==0){
            if (player){
                a[x][y] = 1;
            }else{
                a[x][y] = 2;
            }
            return true;
        }else{
            System.out.println("该位置已经有子,请重新落子");
            return false;
        }
    }

    /**
     * 判断胜负
     * 这个方法又繁琐,又扯
     * @return 0-未结束,1-一方获胜,2-平局
     */
    private static int win(){
        one = a[0][0] ;
        two = a[0][1];
        three = a[0][2];
        four = a[1][0];
        five = a[1][1];
        six = a[1][2];
        seven = a[2][0];
        eight = a[2][1];
        nine = a[2][2];
        if ((one==two&&one==three&&one!=0)||(four==five&&four==six&&four!=0)||(seven==eight&&seven==nine&&seven!=0)){

        }else if((one==four&&one==seven&&one!=0)||(two==five&&two==eight&&two!=0)||(three==six&&three==nine&&three!=0)){

        }else if((one==five&&one==nine&&one!=0)||(three==five&&three==seven&&three!=0)) {

        }else if(one!=0&&two!=0&&three!=0&&four!=0&&five!=0&&six!=0&&seven!=0&&eight!=0&&nine!=0){
            return 2;
        }else{
            return 0;
        }
        return 1;
    }
}
