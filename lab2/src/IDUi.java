import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class IDUi extends JFrame implements ActionListener{
    Client client;
    JLayeredPane win;
    JButton bAffirm, bCorrect;
    JTextField inputID;
    IDUi(Client client) {
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
        bAffirm = new JButton("<html><font face=\\\"华文行楷\\\">确认<br>Affirm</font></html>");
        bAffirm.setFont(font2);
        bAffirm.addActionListener(this);
        bCorrect = new JButton("<html><font face=\\\"华文行楷\\\">更正<br>Correct</font></html>");
        bCorrect.addActionListener(this);
        bCorrect.setFont(font2);
        inputID = new JTextField("请输入卡号");
        inputID.setFont(font3);
        Image backgroundImage = (new ImageIcon("./images/background2.jpg")).getImage();
        ImageCanvas backgroundImageCanvas = new ImageCanvas(backgroundImage);
        Image insertCardImage = (new ImageIcon("./images/insertCard.png")).getImage();
        ImageCanvas insertCardImageCanvas = new ImageCanvas(insertCardImage);
        Image lineImage = (new ImageIcon("./images/line.png")).getImage();
        ImageCanvas lineImageCanvas = new ImageCanvas(lineImage);
        Image logoImage = (new ImageIcon("./images/logo.png")).getImage();
        ImageCanvas logoImageCanvas = new ImageCanvas(logoImage);
        JButton bLabel1 = new JButton("虫带银行");
        bLabel1.setFont(font1);
        JButton bLabel2 = new JButton("BANK OF CQU");
        bLabel2.setFont(font2);
        JButton bLabel3 = new JButton("欢迎使用");
        bLabel3.setFont(font2);
        JButton bLabel4 = new JButton("Welcome to our Bank");
        bLabel4.setFont(font3);
        JButton bLabel5 = new JButton("请插入磁卡");
        bLabel5.setFont(font2);
        JButton bLabel6 = new JButton("Please Enter the Card");
        bLabel6.setFont(font3);
        JButton bLabel7 = new JButton("24小时服务热线：888-888-8888");
        bLabel7.setFont(font3);
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
        bLabel3.setBounds(450, 150, 300, 32);
        bLabel3.setContentAreaFilled(false);
        bLabel3.setBorder(null);
        bLabel3.setFocusable(false);
        win.add(bLabel4);
        bLabel4.setBounds(450, 175, 300, 20);
        bLabel4.setContentAreaFilled(false);
        bLabel4.setBorder(null);
        bLabel4.setFocusable(false);
        win.add(bLabel5);
        bLabel5.setBounds(475, 265, 250, 32);
        bLabel5.setContentAreaFilled(false);
        bLabel5.setBorder(null);
        bLabel5.setFocusable(false);
        win.add(bLabel6);
        bLabel6.setBounds(475, 300, 250, 20);
        bLabel6.setContentAreaFilled(false);
        bLabel6.setBorder(null);
        bLabel6.setFocusable(false);
        win.add(bLabel7);
        bLabel7.setBounds(5, 110, 325, 20);
        bLabel7.setContentAreaFilled(false);
        bLabel7.setBorder(null);
        bLabel7.setFocusable(false);
        win.add(logoImageCanvas);
        logoImageCanvas.setBounds(5, 10, logoImage.getWidth(this), logoImage.getHeight(this));
        win.add(insertCardImageCanvas);
        insertCardImageCanvas.setBounds(450, 300, insertCardImage.getWidth(this), insertCardImage.getHeight(this));
        win.add(lineImageCanvas);
        lineImageCanvas.setBounds(0, 110, lineImage.getWidth(this), lineImage.getHeight(this));
        win.add(bAffirm);
        bAffirm.setBounds(1025, 180, 140, 68);
        bAffirm.setFocusable(false);
        win.add(bCorrect);
        bCorrect.setBounds(1025, 500, 140, 68);
        bCorrect.setFocusable(false);
        win.add(inputID);
        inputID.setBounds(450, 610, 300, 50);
        win.add(backgroundImageCanvas, JLayeredPane.DEFAULT_LAYER);
        backgroundImageCanvas.setBounds(0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        add(win);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bCorrect) {
            inputID.setText("请输入卡号");
        }
        if (e.getSource() == bAffirm) {
            System.out.println(4);
            if (client.checkID(inputID.getText()) ) {
                System.out.println(5);
                this.dispose();
                PasswordUi passwordUi = new PasswordUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "您输入的卡号不存在", "", JOptionPane.WARNING_MESSAGE);
                inputID.setText("");
            }
        }
    }
}
