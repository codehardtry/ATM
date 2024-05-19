import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class PasswordUi extends JFrame implements ActionListener{
    Client client;
    JLayeredPane win;
    JButton bAffirm, bCorrect, bReturnCard;
    JButton [] button;
    JPasswordField inputPassword;
    PasswordUi(Client client) {
        this.client = client;
        init();
        setBounds(300, 100, 1200, 900);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void init() {
        win = new JLayeredPane();
        Font font1 = new Font("华文行楷", Font.PLAIN, 40);
        Font font2 = new Font("Microsoft YaHei", Font.BOLD, 30);
        Font font3 = new Font("Microsoft YaHei", Font.BOLD, 20);
        button = new JButton[12];
        for (int i = 1; i <= 12; i++) {
            if (i <= 9) {
                button[i -1] = new JButton(String.valueOf(i));
            } else if (i == 10) {
                button[i - 1] = new JButton("*");
            } else if (i == 11) {
                button[i - 1] = new JButton("0");
            } else if (i == 12) {
                button[i - 1] = new JButton("#");
            }
            button[i - 1].addActionListener(this);
            button[i - 1].setFont(font2);
        }
        bAffirm = new JButton("<html><font face=\\\"华文行楷\\\">确认<br>Affirm</font></html>");
        bAffirm.setFont(font2);
        bAffirm.addActionListener(this);
        bCorrect = new JButton("<html><font face=\\\"华文行楷\\\">更正<br>Correct</font></html>");
        bCorrect.addActionListener(this);
        bCorrect.setFont(font2);
        bReturnCard = new JButton("<html><font face=\\\"华文行楷\\\">退卡<br>Return</font></html>");
        bReturnCard.addActionListener(this);
        bReturnCard.setFont(font2);
        inputPassword = new JPasswordField();
        inputPassword.setEnabled(false);
        inputPassword.setFont(font3);
        Image backgroundImage = (new ImageIcon("./images/background2.jpg")).getImage();
        ImageCanvas backgroundImageCanvas = new ImageCanvas(backgroundImage);
        Image lineImage = (new ImageIcon("./images/line.png")).getImage();
        ImageCanvas lineImageCanvas = new ImageCanvas(lineImage);
        Image logoImage = (new ImageIcon("./images/logo.png")).getImage();
        ImageCanvas logoImageCanvas = new ImageCanvas(logoImage);
        JButton bLabel1 = new JButton("虫带银行");
        bLabel1.setFont(font1);
        JButton bLabel2 = new JButton("BANK OF CQU");
        bLabel2.setFont(font2);
        JButton bLabel3 = new JButton("24小时服务热线：888-888-8888");
        bLabel3.setFont(font3);
        win.add(bLabel1);
        bLabel1.setBounds(100, 15, 200, 40);
        bLabel1.setContentAreaFilled(false);
        bLabel1.setBorder(null);
        bLabel1.setFocusable(false);
        win.add(bLabel2);
        bLabel2.setBounds(100, 70, 225, 30);
        bLabel2.setContentAreaFilled(false);
        bLabel2.setBorder(null);
        bLabel2.setFocusable(false);
        win.add(bLabel3);
        bLabel3.setBounds(5, 110, 325, 20);
        bLabel3.setContentAreaFilled(false);
        bLabel3.setBorder(null);
        bLabel3.setFocusable(false);
        win.add(logoImageCanvas);
        logoImageCanvas.setBounds(5, 10, logoImage.getWidth(this), logoImage.getHeight(this));
        win.add(lineImageCanvas);
        lineImageCanvas.setBounds(0, 110, lineImage.getWidth(this), lineImage.getHeight(this));
        win.add(bAffirm);
        bAffirm.setBounds(1025, 180, 140, 68);
        bAffirm.setFocusable(false);
        win.add(bCorrect);
        bCorrect.setBounds(1025, 500, 140, 68);
        bCorrect.setFocusable(false);
        win.add(bReturnCard);
        bReturnCard.setBounds(1025, 750, 140, 68);
        bReturnCard.setFocusable(false);
        win.add(inputPassword);
        inputPassword.setBounds(375, 250, 450, 60);
        int count = 0;
        for (int i = 0; i < 12; i++) {
            win.add(button[i]);
            button[i].setBounds(375 + (count % 3) * 150, 330 + (count / 3) * 80, 150, 80);
            button[i].setFocusable(false);
            count++;
        }
        win.add(backgroundImageCanvas, JLayeredPane.DEFAULT_LAYER);
        backgroundImageCanvas.setBounds(0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        add(win);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bCorrect) {
            inputPassword.setText("");
        }
        if (e.getSource() == bAffirm) {
            System.out.println(String.valueOf(inputPassword.getPassword()));
            if (client.checkPassword(String.valueOf(inputPassword.getPassword()).substring(0, inputPassword.getPassword().length))) {
                this.dispose();
                HallUi hallUi = new HallUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "您输入的密码错误", "", JOptionPane.WARNING_MESSAGE);
                inputPassword.setText("");
            }
        }
        if (e.getSource() == bReturnCard) {
            if (client.returnCard()) {
                this.dispose();
                IDUi IDUi = new IDUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "退卡失败，请重新尝试", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                if (inputPassword.getPassword().length == 0) {
                    inputPassword.setText(String.valueOf(i + 1));
                } else if (inputPassword.getPassword().length == 6) {
                } else {
                    inputPassword.setText(String.valueOf(inputPassword.getPassword()).substring(0, inputPassword.getPassword().length) + String.valueOf(i + 1));
                }
            }
        }
        if (e.getSource() == button[9]) {
            if (inputPassword.getPassword().length == 0) {
            } else {
                inputPassword.setText(String.valueOf(inputPassword.getPassword()).substring(0, inputPassword.getPassword().length - 1));
            }
        }
        if (e.getSource() == button[10]) {
            if (inputPassword.getPassword().length == 0) {
                inputPassword.setText("0");
            } else if (inputPassword.getPassword().length == 6) {
            } else {
                inputPassword.setText(String.valueOf(inputPassword.getPassword()).substring(1, inputPassword.getPassword().length) + "0");
            }
        }
        if (e.getSource() == button[11]) {
            if (client.checkPassword(String.valueOf(inputPassword.getPassword()).substring(0, inputPassword.getPassword().length))) {
                this.dispose();
                HallUi hallUi = new HallUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "您输入的密码错误", "", JOptionPane.WARNING_MESSAGE);
                inputPassword.setText("");
            }
        }
    }
}
