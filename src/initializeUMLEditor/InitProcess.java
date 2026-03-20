package initializeUMLEditor;

import handler.buttonBarHandler;
import handler.canvasAreaHandler;
import handler.functionBarHandler;

import javax.swing.*;
import java.awt.*;

public class InitProcess {

    // 設置外觀主題為 Nimbus
    protected static void setAppearanceTheme() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 建立畫布區域
    protected static void createCanvasArea(JFrame frame) {
        canvasAreaHandler canvasHandler = new canvasAreaHandler();
        component.canvas = canvasHandler;
        component.canvas.addMouseListener(canvasHandler);
        component.canvas.setBackground(Color.WHITE);
        frame.add(component.canvas, BorderLayout.CENTER);
    }

    // 建立工具列
    protected static void createToolBar(JFrame frame) {
        createMenuBar();
        createToolBarButtons();
        frame.add(component.toolBarPanel, BorderLayout.NORTH);
    }

    // 建立菜單欄
    protected static void createMenuBar() {
        component.toolBarPanel = new JMenuBar();
        component.fileButton = new JMenu(component.fileButtonName);
        component.editButton = new JMenu(component.editButtonName);
        component.toolBarPanel.add(component.fileButton);
        component.toolBarPanel.add(component.editButton);
    }

    // 建立工具列按鈕
    protected static void createToolBarButtons() {
        functionBarHandler functionBarHandler = new functionBarHandler((canvasAreaHandler) component.canvas);
        component.changeObjectItem = new JMenuItem(component.changeObjectItemName);
        component.groupItem = new JMenuItem(component.groupItemName);
        component.ungroupItem = new JMenuItem(component.ungroupItemName);
        component.editButton.add(component.changeObjectItem);
        component.editButton.add(component.groupItem);
        component.editButton.add(component.ungroupItem);
        component.changeObjectItem.addActionListener(functionBarHandler);
        component.groupItem.addActionListener(functionBarHandler);
        component.ungroupItem.addActionListener(functionBarHandler);
    }

    // 建立左側按鈕面板
    protected static void createButtonPanel(JFrame frame) {
        component.buttonPanel = new JPanel();
        component.buttonPanel.setLayout(new GridLayout(6, 1));
        component.functionButtons = new JButton[6];
        createButtons();
        addButtonsToPanel();
        frame.add(component.buttonPanel, BorderLayout.WEST);
    }

    // 建立按鈕
    protected static void createButtons() {
        buttonBarHandler buttonBarHandler = new buttonBarHandler();
        component.functionButtons[0] = new JButton(component.selecButtonName);
        component.functionButtons[1] = new JButton(component.associationButtonName);
        component.functionButtons[2] = new JButton(component.generalizationButtonName);
        component.functionButtons[3] = new JButton(component.compositionButtonName);
        component.functionButtons[4] = new JButton(component.classButtonName);
        component.functionButtons[5] = new JButton(component.usecaseButtonName);
        for (JButton button : component.functionButtons) {
            button.addActionListener(buttonBarHandler);
        }
    }

    // 將按鈕添加到面板
    protected static void addButtonsToPanel() {
        for (JButton button : component.functionButtons) {
            component.buttonPanel.add(button);
        }
    }

    // 顯示主框架
    protected static void showMainFrame(JFrame frame) {
        frame.setSize(component.frameLength, component.frameWidth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
