import javax.swing.*;
import java.awt.*;

public class RobotDrawing extends JPanel {

    public RobotDrawing() {
        // 設定畫布大小，而不是整個視窗的大小
        setPreferredSize(new Dimension(400, 400));

        // 畫布背景設為淺灰色
        setBackground(new Color(238, 238, 238));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 先清除舊畫面並繪製背景
        super.paintComponent(g);

        // 天線：先畫線，再畫紅色圓球
        g.setColor(Color.BLACK);
        g.drawLine(120, 20, 150, 60);
        g.drawLine(280, 20, 250, 60);

        g.setColor(Color.RED);
        g.fillOval(110, 10, 20, 20);
        g.fillOval(270, 10, 20, 20);

        // 頭部：灰色圓角矩形，再加上黑色外框
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(100, 60, 200, 180, 40, 40);

        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 60, 200, 180, 40, 40);

        // 眼白：兩個白色橢圓
        g.setColor(Color.WHITE);
        g.fillOval(130, 100, 50, 40);
        g.fillOval(220, 100, 50, 40);

        // 瞳孔：兩個藍色圓形
        g.setColor(Color.BLUE);
        g.fillOval(145, 110, 20, 20);
        g.fillOval(235, 110, 20, 20);

        // 嘴巴：深灰色矩形
        g.setColor(Color.DARK_GRAY);
        g.fillRect(150, 180, 100, 30);

        // 嘴巴分隔線：畫四條直線，分成五格
        g.setColor(Color.WHITE);
        for (int x = 170; x < 250; x += 20) {
            g.drawLine(x, 180, x, 209);
        }

        // 身體：黃色矩形，再加上黑色外框
        g.setColor(new Color(255, 204, 0));
        g.fillRect(130, 250, 140, 120);

        g.setColor(Color.BLACK);
        g.drawRect(130, 250, 140, 120);
    }

    public static void main(String[] args) {
        // 在 Swing 的事件處理執行緒建立視窗
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("畫一個機器人");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new RobotDrawing());

            // 根據畫布需要的 400 × 400 大小，安排視窗尺寸
            frame.pack();

            frame.setResizable(false);       // 固定大小，避免畫布被縮小
            frame.setLocationRelativeTo(null); // 視窗置中
            frame.setVisible(true);
        });
    }
}