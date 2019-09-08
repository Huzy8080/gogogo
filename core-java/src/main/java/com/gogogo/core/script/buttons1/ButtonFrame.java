package com.gogogo.core.script.buttons1;

import javax.swing.*;

/**
 * 用脚本处理GUI事件
 *
 * @author HUZHAOYANG
 * @version 1.0
 * @date 2019/9/8 18:18
 */
public class ButtonFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonFrame() {
        setSize(WIDTH, HEIGHT);
        panel = new JPanel();
        panel.setName("panel");
        add(panel);

        yellowButton = new JButton();
        yellowButton.setName("yellowButton");
        blueButton = new JButton();
        blueButton.setName("blueButton");
        redButton = new JButton();
        redButton.setName("redButton");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

    }
}
