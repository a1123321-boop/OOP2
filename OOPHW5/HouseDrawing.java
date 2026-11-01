import javax.swing.*;
import java.awt.*;

// JPanel 是畫布；JFrame 則是裝著畫布的視窗。
public class HouseDrawing extends JPanel {

    public HouseDrawing() {
        setPreferredSize(new Dimension(500, 400)); // 題目指定的畫布尺寸
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 先讓面板完成背景繪製

        // 1. 天空與草地：依文字規格，各占畫布一半。
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 500, 200);
        g.setColor(new Color(76, 175, 80));
        g.fillRect(0, 200, 500, 200);

        // 2. 太陽：寬高相同，畫出黃色正圓。
        g.setColor(Color.YELLOW);
        g.fillOval(380, 35, 80, 80); // 圓心 (420, 75)，半徑 40

        // 八條光芒：每隔 45 度畫一條，與太陽留出 10 的間隔。
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            int x1 = 420 + (int) Math.round(50 * Math.cos(angle));
            int y1 = 75 + (int) Math.round(50 * Math.sin(angle));
            int x2 = 420 + (int) Math.round(70 * Math.cos(angle));
            int y2 = 75 + (int) Math.round(70 * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // 3. 雲朵：三個互相重疊的白色橢圓。
        g.setColor(Color.WHITE);
        g.fillOval(60, 55, 80, 50);
        g.fillOval(100, 40, 85, 65);
        g.fillOval(145, 55, 80, 50);

        // 4. 房屋主體：先填色，再畫外框。
        g.setColor(new Color(255, 228, 181));
        g.fillRect(150, 195, 200, 155);
        g.setColor(Color.BLACK);
        g.drawRect(150, 195, 200, 155);

        // 5. 屋頂：只用三條 drawLine，不填滿三角形。
        g.setColor(Color.RED);
        g.drawLine(130, 195, 250, 105);
        g.drawLine(250, 105, 370, 195);
        g.drawLine(370, 195, 130, 195);

        // 6. 門與門把：圓角矩形加小圓形。
        g.setColor(new Color(139, 69, 19));
        g.fillRoundRect(225, 270, 50, 80, 20, 20);
        g.setColor(Color.YELLOW);
        g.fillOval(262, 310, 8, 8);

        // 7. 左窗：白色填滿、黑色外框與十字窗框。
        g.setColor(Color.WHITE);
        g.fillRect(170, 225, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(170, 225, 50, 50);
        g.drawLine(195, 225, 195, 275);
        g.drawLine(170, 250, 220, 250);

        // 右窗：結構與左窗相同，向右移動 110。
        g.setColor(Color.WHITE);
        g.fillRect(280, 225, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(280, 225, 50, 50);
        g.drawLine(305, 225, 305, 275);
        g.drawLine(280, 250, 330, 250);

        // 8. 角落簽名：請換成你自己的學號與姓名。
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        g.drawString("A1123321 張至翔", 15, 385); // y 是文字基準線
    }

    public static void main(String[] args) {
        // Swing 介面在事件處理執行緒中建立。
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("我的小房子");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new HouseDrawing());
            frame.pack(); // 讓內部畫布是 500 × 400，外框另外計算
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
