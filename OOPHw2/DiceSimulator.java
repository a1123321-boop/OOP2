import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DiceSimulator extends JFrame implements ActionListener {

    // 中央顯示點數；尚未擲骰子時顯示「－」
    private JLabel diceLabel = new JLabel("－", SwingConstants.CENTER);

    // 上方顯示次數、總和與平均
    private JLabel statsLabel = new JLabel(
            "已擲 0 次，總和 0，平均 0.00",
            SwingConstants.CENTER
    );

    private Random random = new Random(); // 產生亂數
    private int count = 0;                 // 累計擲骰子次數
    private long total = 0;                // 累計點數總和

    public DiceSimulator() {
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 將視窗分成上方、中央與下方等區域
        setLayout(new BorderLayout());

        // 中央點數使用 60pt 字體，初始文字為黑色
        diceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
        diceLabel.setForeground(Color.BLACK);

        JButton rollButton = new JButton("擲骰子");

        // 註冊事件傾聽者：按下按鈕後執行 actionPerformed()
        rollButton.addActionListener(this);

        add(statsLabel, BorderLayout.NORTH);
        add(diceLabel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // 開啟時置中
        setVisible(true);            // 完成設定後顯示視窗
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // nextInt(6) 產生 0～5，加 1 後變成 1～6
        int point = random.nextInt(6) + 1;

        count++;        // 次數增加 1
        total += point; // 將本次點數加入總和

        // 先轉成 double，避免整數除法捨去小數
        double average = (double) total / count;

        // 更新中央的點數
        diceLabel.setText(String.valueOf(point));

        // 6 顯示綠色、1 顯示紅色，其餘顯示黑色
        if (point == 6) {
            diceLabel.setForeground(Color.GREEN);
        } else if (point == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }

        // %.2f 表示平均值顯示到小數點後兩位
        statsLabel.setText(String.format(
                java.util.Locale.TAIWAN,
                "已擲 %d 次，總和 %d，平均 %.2f",
                count, total, average
        ));
    }

    public static void main(String[] args) {
        // 在 Swing 的事件處理執行緒中建立視窗
        SwingUtilities.invokeLater(() -> new DiceSimulator());
    }
}