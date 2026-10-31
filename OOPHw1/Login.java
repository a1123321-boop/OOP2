import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    // 帳號輸入框與密碼輸入框
    private JTextField account = new JTextField(12);
    private JPasswordField password = new JPasswordField(12);

    public Login() {
        setTitle("登入視窗");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 以三列兩欄排列元件，水平與垂直間隔皆為 10
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("帳號："));
        add(account);
        add(new JLabel("密碼："));
        add(password);

        JButton loginButton = new JButton("登入");
        add(new JLabel()); // 空白標籤，讓按鈕放在右下方
        add(loginButton);

        // 將目前的視窗物件註冊為按鈕的事件傾聽者
        loginButton.addActionListener(this);

        pack();                       // 依元件需要決定視窗大小
        setLocationRelativeTo(null);  // 視窗置中
        setVisible(true);             // 元件設定完成後再顯示視窗
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 取得使用者輸入的帳號與密碼
        String inputAccount = account.getText();
        String inputPassword = new String(password.getPassword());

        // 使用 equals() 比較字串內容，兩者都正確才登入成功
        if ("admin".equals(inputAccount)
                && "1234".equals(inputPassword)) {
            JOptionPane.showMessageDialog(this, "登入成功！");
        } else {
            JOptionPane.showMessageDialog(this, "帳號或密碼錯誤！");
        }
    }

    public static void main(String[] args) {
        // 在 Swing 的事件處理執行緒建立視窗
        SwingUtilities.invokeLater(() -> new Login());
    }
}