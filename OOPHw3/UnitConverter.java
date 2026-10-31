import javax.swing.*;
import java.awt.*;

public class UnitConverter extends JFrame {

    // 換算類型下拉選單，String 表示選項是文字
    private JComboBox<String> typeBox =
            new JComboBox<>(new String[]{"長度", "重量", "溫度"});

    // 第一個文字框輸入數值，第二個文字框顯示結果
    private JTextField inputField = new JTextField();
    private JTextField resultField = new JTextField();

    // 來源單位與目標單位的下拉選單
    private JComboBox<String> fromBox = new JComboBox<>();
    private JComboBox<String> toBox = new JComboBox<>();

    // 各類型的單位；索引從 0 開始
    private final String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    private final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private final String[] temperatureUnits = {"攝氏", "華氏", "克氏"};

    // 每個長度單位等於多少公尺，順序對應 lengthUnits
    private final double[] lengthFactors = {1.0, 0.01, 0.0254, 0.3048};

    // 每個重量單位等於多少公斤，順序對應 weightUnits
    private final double[] weightFactors = {
            1.0, 0.001, 0.45359237, 0.028349523125
    };

    public UnitConverter() {
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 主視窗使用 BorderLayout，區域間距為 10
        setLayout(new BorderLayout(10, 10));

        // 上方：換算類型
        add(typeBox, BorderLayout.NORTH);

        // 中央：兩列兩欄，每列一個文字框與一個單位選單
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.add(inputField);
        centerPanel.add(fromBox);
        centerPanel.add(resultField);
        centerPanel.add(toBox);
        add(centerPanel, BorderLayout.CENTER);

        // 結果框可以選取與複製，但不能自行輸入
        resultField.setEditable(false);

        // 下方：換算按鈕
        JButton convertButton = new JButton("換算");
        add(convertButton, BorderLayout.SOUTH);

        // 切換類型時，更新兩個單位選單
        typeBox.addActionListener(e -> updateUnits());

        // 按下按鈕時進行換算
        convertButton.addActionListener(e -> convert());

        // 一開始預設顯示長度單位
        updateUnits();

        setLocationRelativeTo(null); // 視窗置中
        setVisible(true);
    }

    // 依照目前的換算類型，更新來源與目標單位
    private void updateUnits() {
        String[] units;

        int type = typeBox.getSelectedIndex();

        if (type == 0) {
            units = lengthUnits;
        } else if (type == 1) {
            units = weightUnits;
        } else {
            units = temperatureUnits;
        }

        // 兩個選單各自使用獨立的模型，才能分別選擇單位
        fromBox.setModel(new DefaultComboBoxModel<>(units));
        toBox.setModel(new DefaultComboBoxModel<>(units));

        resultField.setText(""); // 切換類型後清除舊結果
    }

    // 取得輸入、進行計算，並顯示結果
    private void convert() {
        try {
            // 文字框取得的是字串，需要轉成 double 才能計算
            double value = Double.parseDouble(inputField.getText().trim());

            // 排除 NaN、Infinity 等非有限數值
            if (!Double.isFinite(value)) {
                throw new NumberFormatException();
            }

            int type = typeBox.getSelectedIndex();
            int from = fromBox.getSelectedIndex();
            int to = toBox.getSelectedIndex();

            double result;

            if (type == 0) {
                // 長度：先換成公尺，再換成目標單位
                result = value * lengthFactors[from] / lengthFactors[to];

            } else if (type == 1) {
                // 重量：先換成公斤，再換成目標單位
                result = value * weightFactors[from] / weightFactors[to];

            } else {
                // 溫度：先換成攝氏，再換成目標單位
                double celsius;

                if (from == 0) {
                    celsius = value;
                } else if (from == 1) {
                    celsius = (value - 32) * 5.0 / 9.0;
                } else {
                    celsius = value - 273.15;
                }

                if (to == 0) {
                    result = celsius;
                } else if (to == 1) {
                    result = celsius * 9.0 / 5.0 + 32;
                } else {
                    result = celsius + 273.15;
                }
            }

            // 避免極大數字換算後超出 double 可表示的範圍
            if (!Double.isFinite(result)) {
                resultField.setText("");
                JOptionPane.showMessageDialog(this, "數值過大，請輸入較小的數值。");
                return;
            }

            // 數字轉成文字，才能放進文字框
            resultField.setText(Double.toString(result));

        } catch (NumberFormatException ex) {
            // 空白、英文字母或其他無效數值都會顯示提示
            resultField.setText("");
            JOptionPane.showMessageDialog(this, "請輸入有效的數字！");
        }
    }

    public static void main(String[] args) {
        // 在 Swing 的事件處理執行緒建立介面
        SwingUtilities.invokeLater(() -> new UnitConverter());
    }
}