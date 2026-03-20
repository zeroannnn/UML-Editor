package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.addClassButton;
import factory.ShapeFactory;

import java.awt.event.MouseEvent;

public class addBasicClassButtonAction extends addClassButton {
    @Override
    protected void addClass(MouseEvent e, canvasAreaHandler handler) {
        System.out.println("add Class on Canvas");
        handler.objects.add(ShapeFactory.createShape("class", e.getPoint()));
    }
}
