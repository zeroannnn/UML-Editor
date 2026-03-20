package initializeUMLEditor;

import javax.swing.*;
import java.awt.*;

public class component {
    // Frame
    public static JFrame frame;
    public static final int frameLength = 1000;
    public static final int frameWidth = 800;
    public static final String frameTitle = "UML Editor";

    // 顏色變數
    public static final Color whiteColor = Color.WHITE;
    public static final Color blackColor = Color.BLACK;
    public static final Color blueColor = Color.BLUE;
    public static final int selectOffset = 2;

    // 工具列
    public static JMenuBar toolBarPanel;
    public static JMenu fileButton;
    public static JMenu editButton;
    public static JMenuItem groupItem;
    public static JMenuItem ungroupItem;
    public static JMenuItem changeObjectItem;

    public static final String fileButtonName = "File";
    public static final String editButtonName = "Edit";
    public static final String groupItemName = "Group";
    public static final String ungroupItemName = "Ungroup";
    public static final String changeObjectItemName = "Change Object Name";

    // 按鈕列表
    public static JPanel buttonPanel;
    public static JButton[] functionButtons;
    public static final String selecButtonName = "Select";
    public static final String associationButtonName = "Association";
    public static final String generalizationButtonName = "Generalization";
    public static final String compositionButtonName = "Composition";
    public static final String classButtonName = "Class";
    public static final String usecaseButtonName = "Use Case";


    // 畫布
    public static JPanel canvas;
    public static int objectBeingselectlasttime = 0;
    public static int compositeBeingselectlasttime = 0;


}
