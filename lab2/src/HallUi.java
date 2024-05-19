import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HallUi extends JFrame implements ActionListener{
    Client client;
    JLayeredPane win;
    JButton bInquire, bWithdraw, bReturnCard;
    HallUi(Client client) {
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
        bInquire.setFont(font2);
        bInquire.addActionListener(this);
        bWithdraw = new JButton("<html><font face=\\\"华文行楷\\\">取款<br>WithDraw</font></html>");
        bWithdraw.addActionListener(this);
        bWithdraw.setFont(font2);
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
        JButton bLabel4 = new JButton("请选择您需要的服务");
        bLabel4.setFont(font4);
        JButton bLabel5 = new JButton("Please select the service you need");
        bLabel5.setFont(font2);
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
        bLabel4.setBounds(290, 350, 600, 80);
        bLabel4.setContentAreaFilled(false);
        bLabel4.setBorder(null);
        bLabel4.setFocusable(false);
        win.add(bLabel5);
        bLabel5.setBounds(290, 450, 600, 80);
        bLabel5.setContentAreaFilled(false);
        bLabel5.setBorder(null);
        bLabel5.setFocusable(false);
        win.add(logoImageCanvas);
        logoImageCanvas.setBounds(5, 10, logoImage.getWidth(this), logoImage.getHeight(this));
        win.add(lineImageCanvas);
        lineImageCanvas.setBounds(0, 110, lineImage.getWidth(this), lineImage.getHeight(this));
        win.add(bInquire);
        bInquire.setBounds(1025, 180, 140, 68);
        bInquire.setFocusable(false);
        win.add(bWithdraw);
        bWithdraw.setBounds(1025, 500, 140, 68);
        bWithdraw.setFocusable(false);
        win.add(bReturnCard);
        bReturnCard.setBounds(1025, 750, 140, 68);
        bReturnCard.setFocusable(false);
        win.add(backgroundImageCanvas, JLayeredPane.DEFAULT_LAYER);
        backgroundImageCanvas.setBounds(0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        add(win);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bWithdraw) {
            this.dispose();
            WithdrawUi withdrawUi = new WithdrawUi(client);
        }
        if (e.getSource() == bInquire) {
            int balance = client.inquireBalance();
            if (balance != -1) {
                this.dispose();
                SuccessInquireUi successInquireUI = new SuccessInquireUi(client, balance);
            } else {
                JOptionPane.showConfirmDialog(this, "查询失败，请稍后再试", "", JOptionPane.WARNING_MESSAGE);
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
    }
}
