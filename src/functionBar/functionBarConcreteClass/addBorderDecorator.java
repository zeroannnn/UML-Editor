package functionBar.functionBarConcreteClass;

import functionBar.functionAction;
import handler.canvasAreaHandler;

import java.awt.event.ActionEvent;

public class addBorderDecorator implements functionAction {
    private final canvasAreaHandler canvasHandler;

    public addBorderDecorator(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
    }

    @Override
    public void execute(ActionEvent e) {
        System.out.println("Adding border decorator to selected object...");
        canvasHandler.addBorderToSelectedObject();
    }
}
