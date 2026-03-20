package model;

import java.awt.*;

public interface basicObject {
    void portInformation (Point point);
    Point[] getPorts();
    void setSelect(boolean select) ;
    boolean getSelect() ;
    Point getLocation() ;
    void setLocation(Point endPoint);
    void setName(String text);
    String getName() ;
    Dimension getSize() ;
    void paintSelected(Graphics g) ;
    void paintComponent(Graphics g);

}
