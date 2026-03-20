package button.buttonConcreteClass;

import handler.canvasAreaHandler;
import button.addClassButton;
import factory.ShapeFactory;
import java.awt.event.MouseEvent;

public class addUseClassButtonAction extends addClassButton {
    @Override
    protected void addClass(MouseEvent e, canvasAreaHandler handler) {
        System.out.println("add Use Case on Canvas");
        handler.objects.add(ShapeFactory.createShape("useCase", e.getPoint()));
    }
}
