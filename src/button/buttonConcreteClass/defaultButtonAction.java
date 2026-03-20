package button.buttonConcreteClass;

import button.buttonAction;
import handler.canvasAreaHandler;

import java.awt.event.MouseEvent;

public class defaultButtonAction implements buttonAction {
    @Override
    public void mousePressed(MouseEvent e, canvasAreaHandler handler) {
        // 默認行為，什麼也不做
    }

    @Override
    public void mouseReleased(MouseEvent e, canvasAreaHandler handler) {
        // 默認行為，什麼也不做
    }
}

