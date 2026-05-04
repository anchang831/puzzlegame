package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] data = new int[4][4];

    int x=0;
    int y=0;

    String path = "image/animal/animal3/";
    Random r = new Random();

    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    int step = 0;

    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {

        initJFrame();

        initJMenuBar();

        initData();

        initImage();


        this.setVisible(true);

    }


    private void initData() {
        int[] tmpArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for (int i=0;i<tmpArr.length;i++){
            int index = r.nextInt(tmpArr.length);
            int tmp = tmpArr[index];
            tmpArr[index] = tmpArr[i];
            tmpArr[i] = tmp;
        }

        for (int i=0;i<tmpArr.length;i++){
            if (tmpArr[i]==0){
                x=i/4;
                y=i%4;
            }
            data[i/4][i%4] = tmpArr[i];
        }
    }

    private void initImage() {

        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount= new JLabel("步数:"+step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                int num = data[i][j];
                JLabel jlabel = new JLabel(new ImageIcon(path+num+".jpg"));
                jlabel.setBounds(105*j+83, 105*i+134, 105,105);
                jlabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jlabel);
            }
        }

        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }


    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeImage = new JMenu("更换图片");



        changeImage.add(girlItem);
        changeImage.add(animalItem);
        changeImage.add(sportItem);


        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()){
            return;
        }

        int KeyCode = e.getKeyCode();
        if (KeyCode == 37) {
            System.out.println("向左移动");
            if (y==3) return;
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            step++;
            initImage();

        }else if(KeyCode == 38){
            System.out.println("向上移动");
            if (x==3) return;
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            step++;
            initImage();

        }else if(KeyCode == 39){
            System.out.println("向右移动");
            if (y==0) return;
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            step++;
            initImage();

        }else if(KeyCode == 40){
            System.out.println("向下移动");
            if (x==0) return;
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            step++;
            initImage();

        }else if(KeyCode == 65){
            initImage();
        }else if(KeyCode == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            step++;
            initImage();
        }


    }

    public boolean victory(){
        for (int i=0;i<data.length;i++){
            for (int j=0;j<data[i].length;j++){
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();

        }else if (obj == reLoginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();

        }else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);

        }else if (obj == accountItem) {
            System.out.println("公众号");
            JDialog jDialog = new JDialog(this, "关于我们", true);
            JLabel jLabel = new JLabel(new ImageIcon("image/aboutyou.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);

            jDialog.setModal(true);

            jDialog.setVisible(true);
        }else if (obj == girlItem) {
            int index  = r.nextInt(data.length)+1;
            path = "image/girl/girl"+index+"/";
            step = 0;
            initData();
            initImage();
        }else if (obj == animalItem) {
            int index  = r.nextInt(data.length)+1;
            path = "image/animal/animal"+index+"/";
            step = 0;
            initData();
            initImage();
        }else if (obj == sportItem) {
            int index  = r.nextInt(data.length)+1;
            path = "image/sport/sport"+index+"/";
            step = 0;
            initData();
            initImage();
        }
    }
}
