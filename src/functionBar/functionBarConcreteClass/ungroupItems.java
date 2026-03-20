package functionBar.functionBarConcreteClass;

import functionBar.functionAction;
import handler.canvasAreaHandler;

import java.awt.event.ActionEvent;

public class ungroupItems implements functionAction {
    private final canvasAreaHandler canvasHandler;

    public ungroupItems(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
    }

    @Override
    public void execute(ActionEvent e) {
        System.out.println("UnGrouping items...");
        canvasHandler.ungroupComposite();
    }
}
