package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.addConnectionLineButton;
import factory.LineFactory;

import java.awt.*;

public class addGeneralizationLineAction extends addConnectionLineButton {
    @Override
    protected void addConnectionLine(Point startPoint, Point endPoint, canvasAreaHandler handler) {
        System.out.println("add Generalization line on Canvas");
        handler.lines.add(LineFactory.createLine("generalization", startPoint, endPoint));
    }
}
