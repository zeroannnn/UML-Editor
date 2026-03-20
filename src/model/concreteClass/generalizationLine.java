package model.concreteClass;

import initializeUMLEditor.component;
import model.lineObject;

import javax.swing.*;
import java.awt.*;

public class generalizationLine extends JPanel implements lineObject {

    private Point       startPoint;
    private Point       endPoint;
    int					arrowSize		= 6;

    public generalizationLine(Point startPoint, Point endPoint) {
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
        drawArrow(g2d, startPoint, endPoint);

        g2d.dispose();
    }


    public void drawArrow(Graphics2D g2d, Point startPoint, Point endPoint) {
        // 計算箭頭的角度
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);

        // 箭頭兩側的角度
        double arrowAngle1 = angle + Math.toRadians(150);
        double arrowAngle2 = angle - Math.toRadians(150);

        // 箭頭的兩端點
        int x1 = (int) (endPoint.x + arrowSize * Math.cos(arrowAngle1));
        int y1 = (int) (endPoint.y + arrowSize * Math.sin(arrowAngle1));
        int x2 = (int) (endPoint.x + arrowSize * Math.cos(arrowAngle2));
        int y2 = (int) (endPoint.y + arrowSize * Math.sin(arrowAngle2));

        // 繪製箭頭
        int[] xPoints = {endPoint.x, x1, x2};
        int[] yPoints = {endPoint.y, y1, y2};
        Polygon arrowHead = new Polygon(xPoints, yPoints, 3);

        // 填充和繪製三角形
        g2d.setColor(component.whiteColor);
        g2d.fillPolygon(arrowHead);
        g2d.setColor(component.blackColor);
        g2d.drawPolygon(arrowHead);
    }
    int[] removeAt(int arr[], int index)
    {
        int temp[] = new int[arr.length - 1];
        for (int i = 0; i < temp.length; i ++)
        {
            if (i < index)
            {
                temp[i] = arr[i];
            }
            else if (i >= index)
            {
                temp[i] = arr[i + 1];
            }
        }
        return temp;
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
