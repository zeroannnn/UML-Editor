package Define;

import java.awt.*;

public class findObjectPort {
    public Point[] getPort(Point point,Dimension defSize) {
        Point[] ports = new Point[4];

        // 上
        ports[0] = new Point((int) (point.getX() + defSize.getWidth() / 2), (int) point.getY());
        // 右
        ports[1] = new Point((int) (point.getX() + defSize.getWidth()), (int) (point.getY() + defSize.getHeight() / 2));
        // 下
        ports[2] = new Point((int) (point.getX() + defSize.getWidth() / 2), (int) (point.getY() + defSize.getHeight()));
        // 左
        ports[3] = new Point((int) point.getX(), (int) (point.getY() + defSize.getHeight() / 2));

        return ports;
    }
}
