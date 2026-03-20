package handler;

import initializeUMLEditor.component;
import functionBar.*;
import functionBar.functionBarConcreteClass.changeObjectName;
import functionBar.functionBarConcreteClass.groupItems;
import functionBar.functionBarConcreteClass.ungroupItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class functionBarHandler implements ActionListener {
    private canvasAreaHandler canvasHandler;
    private final Map<String, functionAction> strategyMap;

    public functionBarHandler(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
        this.strategyMap = new HashMap<>();
        initStrategyMap();
    }

    private void initStrategyMap() {
        strategyMap.put(component.groupItemName, new groupItems(canvasHandler));
        strategyMap.put(component.ungroupItemName, new ungroupItems(canvasHandler));
        strategyMap.put(component.changeObjectItemName, new changeObjectName(canvasHandler));
    }

    public void actionPerformed(ActionEvent e) {
        functionAction strategy = strategyMap.get(e.getActionCommand());
        if (strategy != null) {
            strategy.execute(e);
        }
        component.canvas.repaint();
    }
}
