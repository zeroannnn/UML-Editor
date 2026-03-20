package button;

import handler.canvasAreaHandler;

import java.awt.event.MouseEvent;

public interface buttonAction {
    void mousePressed(MouseEvent e, canvasAreaHandler handler);
    void mouseReleased(MouseEvent e, canvasAreaHandler handler);
}
