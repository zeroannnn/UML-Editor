package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.addConnectionLineButton;
import factory.LineFactory;

import java.awt.*;

public class addCompositionLineAction extends addConnectionLineButton {
    @Override
    protected void addConnectionLine(Point startPoint, Point endPoint, canvasAreaHandler handler) {
        System.out.println("add Composition on Canvas");
        handler.lines.add(LineFactory.createLine("composition", startPoint, endPoint));
    }
}
