package model.decorator;

import model.basicObject;

import java.awt.*;

public abstract class BasicObjectDecorator implements basicObject {
    protected basicObject wrappedObject;

    public BasicObjectDecorator(basicObject wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    @Override
    public void portInformation(Point point) {
        wrappedObject.portInformation(point);
    }

    @Override
    public Point[] getPorts() {
        return wrappedObject.getPorts();
    }

    @Override
    public void setSelect(boolean select) {
        wrappedObject.setSelect(select);
    }

    @Override
    public boolean getSelect() {
        return wrappedObject.getSelect();
    }

    @Override
    public Point getLocation() {
        return wrappedObject.getLocation();
    }

    @Override
    public void setLocation(Point endPoint) {
        wrappedObject.setLocation(endPoint);
    }

    @Override
    public void setName(String text) {
        wrappedObject.setName(text);
    }

    @Override
    public String getName() {
        return wrappedObject.getName();
    }

    @Override
    public Dimension getSize() {
        return wrappedObject.getSize();
    }

    @Override
    public void paintSelected(Graphics g) {
        wrappedObject.paintSelected(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        wrappedObject.paintComponent(g);
    }
}
