package model.concreteClass;

import Define.findObjectPort;
import model.basicObject;

import javax.swing.*;
import java.awt.*;

public class useCase extends JPanel implements basicObject {

    public String                  texts ;
    public boolean				   isSelect		    = false;
    public static Dimension        defSize			= new Dimension(90, 45);
    public int					   selectBoxSize	= 5;
    public Point                   point;
    Point[]                     port ;

    public useCase(Point point) {
        this.point = point;
        this.texts = "use Case";
        portInformation (point);
    }

    @Override
    public void portInformation (Point point) {
        findObjectPort portDefiner = new findObjectPort();
        port = portDefiner.getPort(point, defSize);
    }

    @Override
    public Point[] getPorts() {return port;}

    @Override
    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    @Override
    public boolean getSelect() {
        return isSelect;
    }

    @Override
    public Point getLocation() {
        return point;
    }

    @Override
    public void setLocation(Point point) {
        this.point = point;
        portInformation (point);
    }

    @Override
    public void setName(String newName) {
        this.texts = newName;
    }

    @Override
    public String getName() {
        return texts;
    }

    @Override
    public Dimension getSize() {
        return defSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paint UseCase");
        // 獲取長方形的左上角座標（point）和預設大小（defSize）
        int x = (int) point.getX();
        int y = (int) point.getY();
        int width = (int) defSize.getWidth();
        int height = (int) defSize.getHeight();

        // 繪製橢圓形
        g.drawOval(x, y, width, height);
        // 繪製texts
        // 獲取文本的寬度和高度
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(texts);
        int textHeight = fm.getHeight();
        // 計算文本的位置使其置中於橢圓形內
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - textHeight) / 2 + fm.getAscent();
        // 繪製文本
        g.drawString(texts, textX, textY);

        // 若是被選中
        if(isSelect) {
            paintSelected(g);
        }

    }
    @Override
    public void paintSelected(Graphics g) {
        for (int i = 0; i < 4; i++) {
            g.fillRect((int) port[i].getX() - 2, (int) port[i].getY() - 2, selectBoxSize, selectBoxSize); // 根据端口的信息绘制方形
        }
    }
}
