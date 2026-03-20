package model.concreteClass;

import initializeUMLEditor.component;
import model.lineObject;

import javax.swing.*;
import java.awt.*;

public class associationLine extends JPanel implements lineObject {

    private Point startPoint;
    private Point endPoint;

    public associationLine(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paint association line");
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(component.blackColor);
        // 設置線條粗細
        g2d.setStroke(new BasicStroke(2));

        // 繪製直線
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        g2d.dispose();
    }

    public Point getStartingPoint() {
        return startPoint;
    }
    public Point getEndingPoint() {
        return endPoint;
    }
    public void setStartingPoint(Point newStartPoint) {
        this.startPoint = newStartPoint;
    }
    public void setEndingPoint(Point newEndPoint) {
        this.endPoint = newEndPoint;
    }
}
