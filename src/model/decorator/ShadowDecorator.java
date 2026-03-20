package model.decorator;

import model.basicObject;

import java.awt.*;

public class ShadowDecorator extends BasicObjectDecorator {
    private int shadowOffset;
    private Color shadowColor;

    public ShadowDecorator(basicObject wrappedObject, int shadowOffset, Color shadowColor) {
        super(wrappedObject);
        this.shadowOffset = shadowOffset;
        this.shadowColor = shadowColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        // 先畫陰影（在原圖形的偏移位置）
        Graphics2D g2d = (Graphics2D) g;
        Color originalColor = g2d.getColor();

        g2d.setColor(shadowColor);
        Point loc = wrappedObject.getLocation();
        Dimension size = wrappedObject.getSize();
        g2d.fillRect(
                loc.x + shadowOffset,
                loc.y + shadowOffset,
                size.width,
                size.height);

        // 還原顏色，再畫原本的圖形（覆蓋在陰影上方）
        g2d.setColor(originalColor);
        wrappedObject.paintComponent(g);
    }
}
