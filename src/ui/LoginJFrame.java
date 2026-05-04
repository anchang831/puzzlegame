package ui;

import Account.User;
import Util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();
    static{
        allUsers.add(new User("ZhangSan","123"));
        allUsers.add(new User("LiSi","1234"));
    }



    JButton login = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    JLabel rightCode = new JLabel();

    public LoginJFrame() {

        initJFrame();

        initView();

        this.setVisible(true);

    }

    private void initView() {

        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.add(usernameText);

        username.setBounds(195, 134, 200, 30);
        this.add(username);

        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.add(passwordText);

        password.setBounds(195, 195, 200, 30);
        this.add(password);

        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.add(codeText);

        code.setBounds(195, 256, 100, 30);
        this.add(code);

        String codeStr = CodeUtil.getCode();
        rightCode.setText(codeStr);
        rightCode.addMouseListener(this);
        rightCode.setBounds(300, 256, 50, 30);
        this.add(rightCode);

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.add(login);

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.add(register);

        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

       }

    public void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图游戏 V1.0登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == rightCode) {
            String newCode = CodeUtil.getCode();
            rightCode.setText(newCode);
            return;
        }

        if (e.getSource() == login) {
            String inputUsername = username.getText().trim();
            String inputPassword = new String(password.getPassword()).trim();
            String inputCode = code.getText().trim();
            String correctCode = rightCode.getText().trim();

            if (!inputCode.equals(correctCode)){
                showJDialog("验证码错误");
                rightCode.setText(CodeUtil.getCode());
                return;
            }

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                showJDialog("用户名或密码不能为空！");
                return;
            }

            User inputUser = new User(inputUsername, inputPassword);
            if (contains(inputUser)) {
                showJDialog("登录成功！");
                this.dispose();
                new GameJFrame();
            }else{
                showJDialog("用户名或密码错误！");
                password.setText("");
                code.setText("");
                rightCode.setText(CodeUtil.getCode());
            }

            if (e.getSource() == register) {
                showJDialog("注册功能暂未实现！");
            }
        }
    }

    public void showJDialog(String content){
        JDialog jDialog = new JDialog(this, "提示", true);
        jDialog.setSize(200,150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(null);

        JLabel label = new JLabel(content);
        label.setBounds(50, 50, 100, 30);
        jDialog.add(label);
        jDialog.setVisible(true);
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login){
            login.setIcon(new ImageIcon("image/login/登录按下.png"));
        }else if (e.getSource() == register){
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login){
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        }else if (e.getSource() == register){
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean contains(User userInput){
        for (User user : allUsers) {
            if (user.getUsername().equals(userInput.getUsername()) && user.getPassword().equals(userInput.getPassword())){
                return true;
            }
        }
        return false;
    }
}
