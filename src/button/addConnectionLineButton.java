package button;

import handler.canvasAreaHandler;
import model.basicObject;
import Define.nearestPort;

import javax.sound.sampled.Port;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class addConnectionLineButton implements buttonAction {
    @Override
    public void mousePressed(MouseEvent e, canvasAreaHandler handler) {
        handler.startPoint = e.getPoint();
        handler.startExist = false;
        for (basicObject obj : handler.objects) {
            if (handler.isInside(obj, handler.startPoint)) {
                handler.startExist = true;
                handler.connectionLineSelectedObject = obj;
                handler.startPoint = nearestPort.getAccuratePort(handler.startPoint, handler.connectionLineSelectedObject, handler);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, canvasAreaHandler handler) {
        handler.endPoint = e.getPoint();
        handler.endExist = false;
        for (basicObject obj : handler.objects) {
            if (handler.isInside(obj, handler.endPoint)) {
                handler.endExist = true;
                handler.connectionLineSelectedObject = obj;
                handler.endPoint = nearestPort.getAccuratePort(handler.startPoint, handler.connectionLineSelectedObject, handler);
                break;
            }
        }
        if(handler.startExist && handler.endExist) {
            addConnectionLine(handler.startPoint, handler.endPoint, handler);
        }

    }

    protected abstract void addConnectionLine(Point startPoint, Point endPoint, canvasAreaHandler handler);
}