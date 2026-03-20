package factory;

import model.lineObject;
import model.concreteClass.associationLine;
import model.concreteClass.compositionLine;
import model.concreteClass.generalizationLine;

import java.awt.*;

public class LineFactory {
    public static lineObject createLine(String type, Point start, Point end) {
        switch (type) {
            case "association":
                return new associationLine(start, end);
            case "composition":
                return new compositionLine(start, end);
            case "generalization":
                return new generalizationLine(start, end);
            default:
                throw new IllegalArgumentException("Unknown line type: " + type);
        }
    }
}
