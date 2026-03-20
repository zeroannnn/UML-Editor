package model.concreteClass;

import handler.canvasAreaHandler;
import model.basicObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class compositeObject extends JPanel {
    private ArrayList<basicObject> children;
    public boolean				   isSelect		    = false;

    public compositeObject() {
        this.children = new ArrayList<>();
    }

    public void addChild(basicObject child) {
        children.add(child);
    }

    public ArrayList<basicObject> getComposite() {
        return children;
    }

    public boolean isMouseInside(Point point) {
        for (basicObject child : children) {
            if (canvasAreaHandler.isInside(child, point)) {
                return true;
            }
        }
        return false;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    public boolean getSelect() {
        return isSelect;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("composite actual repaint!!!");
        for (basicObject child : children) {
            child.paintComponent(g);
        }
        if (isSelect) {
            System.out.println("paint selected composite");
            for (basicObject child : children) {
                    child.paintSelected(g);
            }
        }
    }
}
