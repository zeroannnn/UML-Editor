package initializeUMLEditor;

import javax.swing.*;

public class UMLEditor {

    public static void main(String[]args) {
        // 建立唯一的主框架實例
        JFrame init = Init.getInstance();

        // 將外觀主題設置為 Nimbus，否則會看不到 button select 後的黑色外觀
        InitProcess.setAppearanceTheme();

        // 建立畫布區域、工具列、按鈕面板並顯示主框架
        InitProcess.createCanvasArea(init);
        InitProcess.createToolBar(init);
        InitProcess.createButtonPanel(init);
        InitProcess.showMainFrame(init);
    }
}
