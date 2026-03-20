package functionBar.functionBarConcreteClass;
import functionBar.functionAction;
import handler.canvasAreaHandler;

import java.awt.event.ActionEvent;

public class groupItems implements functionAction {
    private final canvasAreaHandler canvasHandler;

    public groupItems(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
    }

    @Override
    public void execute(ActionEvent e) {
        System.out.println("Grouping items...");
        canvasHandler.groupSelectedObjects();
    }
}
