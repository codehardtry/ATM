import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SuccessWithdrawUi extends JFrame implements ActionListener{
    Client client;
    JLayeredPane win;
    JButton bInquire, bBack, bReturnCard;
    SuccessWithdrawUi(Client client) {
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
        Font font4 = new Font("Microsoft YaHei", Font.BOLD, 60);
        bInquire = new JButton("<html><font face=\\\"华文行楷\\\">查询<br>Inquire</font></html>");
        bInquire.addActionListener(this);
        bInquire.setFont(font2);
        bBack = new JButton("<html><font face=\\\"华文行楷\\\">返回<br>back</font></html>");
        bBack.addActionListener(this);
        bBack.setFont(font2);
        bReturnCard = new JButton("<html><font face=\\\"华文行楷\\\">退卡<br>Return</font></html>");
        bReturnCard.addActionListener(this);
        bReturnCard.setFont(font2);
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
        JButton bLabel4 = new JButton("请取走你的钞票");
        bLabel4.setFont(font4);
        JButton bLabel5 = new JButton("Please take your money");
        bLabel5.setFont(font2);
        JButton bLabel6 = new JButton("提示：如您无需办理其他业务，请取走您的卡片！");
        bLabel6.setFont(font3);
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
        win.add(bLabel4);
        bLabel4.setBounds(250, 430, 600, 80);
        bLabel4.setContentAreaFilled(false);
        bLabel4.setBorder(null);
        bLabel4.setFocusable(false);
        win.add(bLabel5);
        bLabel5.setBounds(250, 480, 600, 80);
        bLabel5.setContentAreaFilled(false);
        bLabel5.setBorder(null);
        bLabel5.setFocusable(false);
        win.add(bLabel6);
        bLabel6.setBounds(320, 700, 500, 50);
        bLabel6.setContentAreaFilled(false);
        bLabel6.setBorder(null);
        bLabel6.setFocusable(false);
        win.add(logoImageCanvas);
        logoImageCanvas.setBounds(5, 10, logoImage.getWidth(this), logoImage.getHeight(this));
        win.add(lineImageCanvas);
        lineImageCanvas.setBounds(0, 110, lineImage.getWidth(this), lineImage.getHeight(this));
        win.add(bInquire);
        bInquire.setBounds(1025, 180, 140, 68);
        bInquire.setFocusable(false);
        win.add(bBack);
        bBack.setBounds(1025, 500, 140, 68);
        bBack.setFocusable(false);
        win.add(bReturnCard);
        bReturnCard.setBounds(1025, 750, 140, 68);
        bReturnCard.setFocusable(false);
        win.add(backgroundImageCanvas, JLayeredPane.DEFAULT_LAYER);
        backgroundImageCanvas.setBounds(0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        add(win);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bInquire) {
            this.dispose();
            int balance = client.inquireBalance();
            if (balance != -1) {
                SuccessInquireUi inquireUi = new SuccessInquireUi(client, balance);
            } else {
                JOptionPane.showConfirmDialog(this, "查询失败，请稍后再试", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bBack) {
            this.dispose();
            HallUi hallUi = new HallUi(client);
        }
        if (e.getSource() == bReturnCard) {
            if (client.returnCard()) {
                this.dispose();
                IDUi IDUi = new IDUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "退卡失败，请重新尝试", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
