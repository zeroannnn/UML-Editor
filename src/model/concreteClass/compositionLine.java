package model.concreteClass;

import initializeUMLEditor.component;
import model.lineObject;

import javax.swing.*;
import java.awt.*;

public class compositionLine extends JPanel implements lineObject {

    private Point       startPoint;
    private Point       endPoint;
    int					arrowSize		= 6;

    public compositionLine(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        setOpaque(false); // 設置為透明，以便繪製箭頭 ????
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

        // 繪製箭頭
        drawArrow(g2d, endPoint);

        g2d.dispose();
    }

    public void drawArrow(Graphics2D g2d, Point point) {
        int x[] = {point.x, point.x - arrowSize, point.x, point.x + arrowSize};
        int y[] = {point.y + arrowSize, point.y, point.y - arrowSize, point.y};
        Polygon polygon = new Polygon(x, y, x.length);
        g2d.setColor(component.whiteColor);
        g2d.fillPolygon(polygon);
        g2d.setColor(component.blackColor);
        g2d.drawPolygon(polygon);
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
