package model;

import java.awt.*;

public interface lineObject {
    void paintComponent(Graphics g) ;
    Point getStartingPoint();
    Point getEndingPoint();
    void setStartingPoint(Point point);
    void setEndingPoint(Point point);
}
