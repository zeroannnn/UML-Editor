package handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import initializeUMLEditor.component;
import button.*;
import button.buttonConcreteClass.*;
import model.concreteClass.compositeObject;
import model.lineObject;
import model.basicObject;
import model.decorator.BorderDecorator;
import model.decorator.ShadowDecorator;

public class canvasAreaHandler extends JPanel implements MouseListener {
    public static ArrayList<basicObject> objects = new ArrayList<>();
    public static ArrayList<lineObject> lines = new ArrayList<>();
    public static ArrayList<compositeObject> compositeObjects = new ArrayList<>();
    public Point startPoint = null;
    public Point endPoint = null;
    public Boolean startExist = false;
    public Boolean endExist = false;
    public basicObject connectionLineSelectedObject;

    private buttonAction currentStrategy;

    private static final Map<String, buttonAction> strategyMap = new HashMap<>();
    static {
        strategyMap.put(component.selecButtonName, new selectButtonAction());
        strategyMap.put(component.associationButtonName, new addAssociationLineAction());
        strategyMap.put(component.generalizationButtonName, new addGeneralizationLineAction());
        strategyMap.put(component.compositionButtonName, new addCompositionLineAction());
        strategyMap.put(component.classButtonName, new addBasicClassButtonAction());
        strategyMap.put(component.usecaseButtonName, new addUseClassButtonAction());
    }

    // 重畫畫布
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("reapint()");
        for (basicObject obj : objects) {
            obj.paintComponent(g);
        }
        for (lineObject line : lines) {
            line.paintComponent(g);
        }
        if (!compositeObjects.isEmpty()) {
            System.out.println("paintComposite");
            for (compositeObject compositeObject : compositeObjects) {
                compositeObject.paintComponent(g);
            }
        }
    }

    // objects 被選取
    public boolean selectByClick(MouseEvent e) {
        if (objects.size() <= 0) {
            return false; // 如果列表為空，直接返回
        } else {
            if (component.objectBeingselectlasttime >= 0 && component.objectBeingselectlasttime < objects.size()) {
                objects.get(component.objectBeingselectlasttime).setSelect(false);
            }
            for (int i = 0; i < objects.size(); i++) {
                if (isInside(objects.get(i), e.getPoint())) {
                    objects.get(i).setSelect(true);
                    component.objectBeingselectlasttime = i;
                    return true;
                }
            }
            return false;
        }
    }

    // composite 被選取
    public boolean selectGroupByClick(MouseEvent e) {
        if (!compositeObjects.isEmpty()) {
            compositeObjects.get(component.compositeBeingselectlasttime).setSelect(false);
            for (int i = 0; i < compositeObjects.size(); i++) {
                if (GroupObjectIsInside(compositeObjects.get(i), e.getPoint())) {
                    compositeObjects.get(i).setSelect(true);
                    component.compositeBeingselectlasttime = i;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // 判斷鼠標是否在某一個物件內
    public static boolean isInside(basicObject container, Point point) {
        Point cLocat = container.getLocation();
        Dimension cSize = container.getSize();
        return point.x >= cLocat.x && point.y >= cLocat.y
                && point.x <= cLocat.x + cSize.width
                && point.y <= cLocat.y + cSize.height;
    }

    // 判斷鼠標是否在某一個composite內
    public boolean GroupObjectIsInside(compositeObject composite, Point point) {
        return composite.isMouseInside(point);
    }

    // 更改 basicObject 的 name
    public void changeObjectName() {
        for (basicObject object : objects) {
            if (object.getSelect()) {
                String newName = JOptionPane.showInputDialog(component.frame,
                        "Enter new object name:",
                        object.getName());
                if (newName != null) {
                    // 在這裡處理新名稱，可以將其設置給物件
                    System.out.println("New object name: " + newName);
                    object.setName(newName);
                }
            }
        }
    }

    // 將一群 select object group 成 composite
    public void groupSelectedObjects() {
        ArrayList<basicObject> selectedObjects = new ArrayList<>();
        for (basicObject obj : objects) {
            if (obj.getSelect()) {
                selectedObjects.add(obj);
            }
        }
        if (selectedObjects.size() > 1) {
            // 創建新的 CompositeObject
            compositeObject composite = new compositeObject();
            for (basicObject obj : selectedObjects) {
                composite.addChild(obj);
                obj.setSelect(false);
                objects.remove(obj);
            }
            compositeObjects.add(composite);
            repaint();
        }
    }

    public void ungroupComposite() {
        ArrayList<compositeObject> selectedComposite = new ArrayList<>();
        for (compositeObject composite : compositeObjects) {
            if (composite.getSelect()) {
                selectedComposite.add(composite);
                composite.setSelect(false);
                compositeObjects.remove(composite);
                break;
            }
        }
        if (selectedComposite.size() == 1) {
            for (basicObject obj : selectedComposite.get(0).getComposite()) {
                objects.add(obj);
            }
        } else {
            System.out.println("No selected compositeObject found.");
        }
    }

    // 為選取的物件加上 Border Decorator
    public void addBorderToSelectedObject() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getSelect()) {
                basicObject original = objects.get(i);
                basicObject decorated = new BorderDecorator(original, Color.RED, 2);
                decorated.setSelect(true);
                objects.set(i, decorated);
                System.out.println("Border decorator added to: " + decorated.getName());
                break;
            }
        }
        repaint();
    }

    // 為選取的物件加上 Shadow Decorator
    public void addShadowToSelectedObject() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getSelect()) {
                basicObject original = objects.get(i);
                basicObject decorated = new ShadowDecorator(original, 5, Color.LIGHT_GRAY);
                decorated.setSelect(true);
                objects.set(i, decorated);
                System.out.println("Shadow decorator added to: " + decorated.getName());
                break;
            }
        }
        repaint();
    }

    public static void unselectAllObject() {
        for (basicObject obj : objects) {
            obj.setSelect(false);
        }
        component.canvas.repaint();
    }

    public static void unselectAllComposite() {
        for (compositeObject compositeObject : compositeObjects) {
            compositeObject.setSelect(false);
        }
        component.canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        unselectAllObject();
        unselectAllComposite();
        String selectedMode = buttonBarHandler.getSelectedMode();
        currentStrategy = strategyMap.getOrDefault(selectedMode, new defaultButtonAction());
        currentStrategy.mousePressed(e, this);
        component.canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentStrategy != null) {
            currentStrategy.mouseReleased(e, this);
        }
        component.canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
