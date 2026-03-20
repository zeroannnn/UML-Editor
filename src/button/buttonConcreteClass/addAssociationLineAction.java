package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.addConnectionLineButton;
import factory.LineFactory;

import java.awt.*;

public class addAssociationLineAction extends addConnectionLineButton {
    @Override
    protected void addConnectionLine(Point startPoint, Point endPoint, canvasAreaHandler handler) {
        System.out.println("add Association Line on Canvas");
        handler.lines.add(LineFactory.createLine("association", startPoint, endPoint));
    }
}
