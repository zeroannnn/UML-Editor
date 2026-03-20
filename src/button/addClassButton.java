package button;

import handler.canvasAreaHandler;

import java.awt.event.MouseEvent;


public abstract class addClassButton implements buttonAction {
    @Override
    public void mousePressed(MouseEvent e, canvasAreaHandler handler) {
        addClass(e, handler);
    }

    @Override
    public void mouseReleased(MouseEvent e, canvasAreaHandler handler) {
    }

    protected abstract void addClass(MouseEvent e, canvasAreaHandler handler);
}
