package Define;

import handler.canvasAreaHandler;
import model.basicObject;

import java.awt.*;

public class nearestPort {
    public static Point getAccuratePort(Point point, basicObject obj, canvasAreaHandler handler) {
        Point nearestPort = null;
        double minDistance = Double.MAX_VALUE;

        // 搜索所有物件中的端口，找到距離最短的端口
        for (Point port : obj.getPorts()) {
            double distance = point.distance(port.getLocation());
            if (distance < minDistance) {
                minDistance = distance;
                nearestPort = port;
            }
        }


        return nearestPort;
    }
}