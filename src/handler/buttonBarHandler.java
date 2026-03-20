package handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import initializeUMLEditor.component;

public class buttonBarHandler implements ActionListener {
    public static String selectedButtonName = "";
    private static JButton lastClickedButton = null;

    public void actionPerformed(ActionEvent e) {
        canvasAreaHandler.unselectAllObject();
        canvasAreaHandler.unselectAllComposite();
        JButton clickedButton = (JButton) e.getSource();
        handleButtonClick(clickedButton);
    }

    private void handleButtonClick(JButton clickedButton) {
        if (lastClickedButton != null) {
            resetButtonColor(lastClickedButton);
        }

        setButtonColor(clickedButton);

        lastClickedButton = clickedButton;
        selectedButtonName = clickedButton.getText();
    }

    private void resetButtonColor(JButton button) {
        button.setBackground(UIManager.getColor("Button.background"));
        button.setForeground(component.blackColor);
    }

    private void setButtonColor(JButton button) {
        button.setBackground(component.blackColor);
        button.setForeground(component.whiteColor);
    }

    public static String getSelectedMode(){
        return selectedButtonName;
    }
}
