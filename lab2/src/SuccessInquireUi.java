import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SuccessInquireUi extends JFrame implements ActionListener{
    Client client;
    int balance;
    JLayeredPane win;
    JButton bWithdraw, bBack, bReturnCard;
    JButton bBalance1, bBalance2;
    SuccessInquireUi(Client client, int balance) {
        this.client = client;
        this.balance = balance;
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
        bWithdraw = new JButton("<html><font face=\\\"华文行楷\\\">取款<br>WithDraw</font></html>");
        bWithdraw.addActionListener(this);
        bWithdraw.setFont(font2);
        bBack = new JButton("<html><font face=\\\"华文行楷\\\">返回<br>back</font></html>");
        bBack.addActionListener(this);
        bBack.setFont(font2);
        bReturnCard = new JButton("<html><font face=\\\"华文行楷\\\">退卡<br>Return</font></html>");
        bReturnCard.addActionListener(this);
        bReturnCard.setFont(font2);
        bBalance1 = new JButton(String.valueOf(balance));
        bBalance1.setFont(font2);
        bBalance2 = new JButton(String.valueOf(balance));
        bBalance2.setFont(font2);
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
        JButton bLabel4 = new JButton("您的账户余额信息");
        bLabel4.setFont(font4);
        JButton bLabel5 = new JButton("Your account balance information");
        bLabel5.setFont(font2);
        JButton bLabel6 = new JButton("余       额:");
        bLabel6.setFont(font2);
        JButton bLabel7 = new JButton("可用余额:");
        bLabel7.setFont(font2);
        JButton bLabel8 = new JButton("提示：如您无需办理其他业务，请取走您的卡片！");
        bLabel8.setFont(font3);
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
        bLabel4.setBounds(230, 250, 600, 80);
        bLabel4.setContentAreaFilled(false);
        bLabel4.setBorder(null);
        bLabel4.setFocusable(false);
        win.add(bLabel5);
        bLabel5.setBounds(230, 320, 600, 80);
        bLabel5.setContentAreaFilled(false);
        bLabel5.setBorder(null);
        bLabel5.setFocusable(false);
        win.add(bLabel6);
        bLabel6.setBounds(250, 460, 300, 50);
        bLabel6.setContentAreaFilled(false);
        bLabel6.setBorder(null);
        bLabel6.setFocusable(false);
        win.add(bLabel7);
        bLabel7.setBounds(250, 530, 300, 50);
        bLabel7.setContentAreaFilled(false);
        bLabel7.setBorder(null);
        bLabel7.setFocusable(false);
        win.add(bLabel8);
        bLabel8.setBounds(250, 700, 500, 50);
        bLabel8.setContentAreaFilled(false);
        bLabel8.setBorder(null);
        bLabel8.setFocusable(false);
        win.add(bBalance1);
        bBalance1.setBounds(350, 460, 500, 50);
        bBalance1.setContentAreaFilled(false);
        bBalance1.setBorder(null);
        bBalance1.setFocusable(false);
        win.add(bBalance2);
        bBalance2.setBounds(350, 530, 500, 50);
        bBalance2.setContentAreaFilled(false);
        bBalance2.setBorder(null);
        bBalance2.setFocusable(false);
        win.add(logoImageCanvas);
        logoImageCanvas.setBounds(5, 10, logoImage.getWidth(this), logoImage.getHeight(this));
        win.add(lineImageCanvas);
        lineImageCanvas.setBounds(0, 110, lineImage.getWidth(this), lineImage.getHeight(this));
        win.add(bWithdraw);
        bWithdraw.setBounds(1025, 180, 140, 68);
        bWithdraw.setFocusable(false);
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
        if (e.getSource() == bWithdraw) {
            this.dispose();
            WithdrawUi withdrawUi = new WithdrawUi(client);
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
