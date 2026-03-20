package functionBar.functionBarConcreteClass;

import functionBar.functionAction;
import handler.canvasAreaHandler;

import java.awt.event.ActionEvent;

public class changeObjectName  implements functionAction {
    private final canvasAreaHandler canvasHandler;

    public changeObjectName(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
    }

    @Override
    public void execute(ActionEvent e) {
        System.out.println("Changing object name...");
        canvasHandler.changeObjectName();
    }
}