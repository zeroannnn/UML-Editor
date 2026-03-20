package factory;

import model.basicObject;
import model.concreteClass.basicClass;
import model.concreteClass.useCase;
import model.decorator.BorderDecorator;
import model.decorator.ShadowDecorator;

import java.awt.*;

public class ShapeFactory {
    public static basicObject createShape(String type, Point position) {
        switch (type) {
            case "class":
                return new basicClass(position);
            case "useCase":
                return new useCase(position);
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }

    // 透過 Decorator 建立帶有額外視覺效果的物件
    public static basicObject createDecoratedShape(String type, Point position,
            boolean withBorder, boolean withShadow) {
        basicObject shape = createShape(type, position);

        if (withShadow) {
            shape = new ShadowDecorator(shape, 5, Color.LIGHT_GRAY);
        }
        if (withBorder) {
            shape = new BorderDecorator(shape, Color.RED, 2);
        }

        return shape;
    }
}
