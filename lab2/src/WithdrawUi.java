import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WithdrawUi extends JFrame implements ActionListener{
    Client client;
    JLayeredPane win;
    JButton bReturnCard, bBack, bOther;
    JButton bBalance1, bBalance2, bBalance3, bBalance4, bBalance5;
    WithdrawUi(Client client) {
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
        bBalance1 = new JButton("100");
        bBalance1.addActionListener(this);
        bBalance1.setFont(font2);
        bBalance2 = new JButton("300");
        bBalance2.addActionListener(this);
        bBalance2.setFont(font2);
        bBalance3 = new JButton("500");
        bBalance3.addActionListener(this);
        bBalance3.setFont(font2);
        bBalance4 = new JButton("1000");
        bBalance4.addActionListener(this);
        bBalance4.setFont(font2);
        bBalance5 = new JButton("2000");
        bBalance5.addActionListener(this);
        bBalance5.setFont(font2);
        bOther = new JButton("<html><font face=\\\"华文行楷\\\">其他金额<br>other</font></html>");
        bOther.addActionListener(this);
        bOther.setFont(font2);
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
        JButton bLabel4 = new JButton("请选择取款金额");
        bLabel4.setFont(font4);
        JButton bLabel5 = new JButton("Please select the withdrawal amount");
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
        win.add(bBalance1);
        bBalance1.setBounds(35, 180, 140, 68);
        bBalance1.setFocusable(false);
        win.add(bBalance2);
        bBalance2.setBounds(35, 330, 140, 68);
        bBalance2.setFocusable(false);
        win.add(bBalance3);
        bBalance3.setBounds(35, 480, 140, 68);
        bBalance3.setFocusable(false);
        win.add(bBalance4);
        bBalance4.setBounds(35, 630, 140, 68);
        bBalance4.setFocusable(false);
        win.add(bBalance5);
        bBalance5.setBounds(1025, 180, 140, 68);
        bBalance5.setFocusable(false);
        win.add(bOther);
        bOther.setBounds(1025, 330, 140, 68);
        bOther.setFocusable(false);
        win.add(bBack);
        bBack.setBounds(1025, 480, 140, 68);
        bBack.setFocusable(false);
        win.add(bReturnCard);
        bReturnCard.setBounds(1025, 750, 140, 68);
        bReturnCard.setFocusable(false);
        win.add(backgroundImageCanvas, JLayeredPane.DEFAULT_LAYER);
        backgroundImageCanvas.setBounds(0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        add(win);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bBalance1) {
            if (client.checkWithdraw(bBalance1.getText())) {
                this.dispose();
                SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bBalance2) {
            if (client.checkWithdraw(bBalance2.getText())) {
                this.dispose();
                SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bBalance3) {
            if (client.checkWithdraw(bBalance3.getText())) {
                this.dispose();
                SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bBalance4) {
            if (client.checkWithdraw(bBalance4.getText())) {
                this.dispose();
                SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bBalance5) {
            if (client.checkWithdraw(bBalance5.getText())) {
                this.dispose();
                SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
            } else {
                JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == bOther) {
            JTextField inputField = new JTextField();

            InputVerifier verifier = new InputVerifier() {
                @Override
                public boolean verify(JComponent input) {
                    JTextField textField = (JTextField) input;
                    String text = textField.getText();
                    return text.matches("\\d+"); // 使用正则表达式验证是否为数字
                }
            };

            inputField.setInputVerifier(verifier);

            Object[] message = { "请输入取款金额：", inputField };

            int option = JOptionPane.showConfirmDialog(this, message, "", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                if (client.checkWithdraw(inputField.getText())) {
                    this.dispose();
                    SuccessWithdrawUi successWithDrawUi = new SuccessWithdrawUi(client);
                } else {
                    JOptionPane.showConfirmDialog(this, "余额不足", "", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                System.out.println("取消输入");
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
