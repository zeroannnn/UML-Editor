package model.decorator;

import model.basicObject;

import java.awt.*;

public class BorderDecorator extends BasicObjectDecorator {
    private Color borderColor;
    private int borderWidth;

    public BorderDecorator(basicObject wrappedObject, Color borderColor, int borderWidth) {
        super(wrappedObject);
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public void paintComponent(Graphics g) {
        // 先畫原本的圖形
        wrappedObject.paintComponent(g);

        // 再額外畫上彩色邊框（Decorator 新增的行為）
        Graphics2D g2d = (Graphics2D) g;
        Stroke originalStroke = g2d.getStroke();
        Color originalColor = g2d.getColor();

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth));

        Point loc = wrappedObject.getLocation();
        Dimension size = wrappedObject.getSize();
        int padding = borderWidth + 1;
        g2d.drawRect(
                loc.x - padding,
                loc.y - padding,
                size.width + padding * 2,
                size.height + padding * 2);

        // 還原原本的畫筆設定
        g2d.setStroke(originalStroke);
        g2d.setColor(originalColor);
    }
}
