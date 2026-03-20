package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.buttonAction;
import model.basicObject;
import model.concreteClass.compositeObject;
import model.lineObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class selectButtonAction implements buttonAction {
    private boolean selectObject = false;
    private boolean selectComposite = false;

    @Override
    public void mousePressed(MouseEvent e, canvasAreaHandler handler) {
        System.out.println("mousePressed on Canvas");
        handler.startPoint = e.getPoint();
        selectObject = handler.selectByClick(e);
        selectComposite = handler.selectGroupByClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e, canvasAreaHandler handler) {
        handler.endPoint = e.getPoint();
        System.out.println("mouseReleased on Canvas");
        int dx = handler.endPoint.x - handler.startPoint.x;
        int dy = handler.endPoint.y - handler.startPoint.y;

        if(selectObject) {
            // move object
            for (basicObject obj : handler.objects) {
                if (obj.getSelect()) {
                    moveSelectedObjects(obj, handler.lines, dx, dy);
                }
            }
        } else if(selectComposite) {
            for (compositeObject composite : handler.compositeObjects) {
                if (composite.getSelect()) {
                    // Move basic objects inside composite
                    ArrayList<basicObject> objectsInComposite = composite.getComposite();
                    for (basicObject obj : objectsInComposite) {
                        moveSelectedObjects(obj, handler.lines, dx, dy);
                    }
                }
            }

        } else if (handler.startPoint != null && handler.endPoint != null) {
            Rectangle selectionRect = new Rectangle(
                    Math.min(handler.startPoint.x, handler.endPoint.x),
                    Math.min(handler.startPoint.y, handler.endPoint.y),
                    Math.abs(handler.startPoint.x - handler.endPoint.x),
                    Math.abs(handler.startPoint.y - handler.endPoint.y)
            );

            for (basicObject obj : handler.objects) {
                Rectangle objBounds = new Rectangle(obj.getLocation(), obj.getSize());
                if (selectionRect.contains(objBounds)) {
                    obj.setSelect(true);
                }
            }
        }
        selectObject = false;
        selectComposite = false;
        handler.startPoint = null;
        handler.endPoint = null;
    }

    private void moveSelectedObjects(basicObject obj, ArrayList<lineObject> lines, int dx, int dy) {
        Point[] oldPosition = obj.getPorts();
        Point newObjectPosition = new Point(obj.getLocation().x + dx, obj.getLocation().y + dy);
        obj.setLocation(newObjectPosition);
        Point[] newPosition =  obj.getPorts();
        for (lineObject line : lines) {
            updateLineEndpoints(line, oldPosition, newPosition);
        }
    }

    private void updateLineEndpoints(lineObject line, Point[] oldPositions, Point[] newPositions) {
        Point startPort = line.getStartingPoint();
        Point endPort = line.getEndingPoint();
        for (int i = 0; i < oldPositions.length; i++) {
            if (startPort.equals(oldPositions[i])) {
                line.setStartingPoint(newPositions[i]);
            }
            if (endPort.equals(oldPositions[i])) {
                line.setEndingPoint(newPositions[i]);
            }
        }
    }
}
