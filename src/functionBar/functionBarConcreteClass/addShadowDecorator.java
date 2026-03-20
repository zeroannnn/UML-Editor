package functionBar.functionBarConcreteClass;

import functionBar.functionAction;
import handler.canvasAreaHandler;

import java.awt.event.ActionEvent;

public class addShadowDecorator implements functionAction {
    private final canvasAreaHandler canvasHandler;

    public addShadowDecorator(canvasAreaHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
    }

    @Override
    public void execute(ActionEvent e) {
        System.out.println("Adding shadow decorator to selected object...");
        canvasHandler.addShadowToSelectedObject();
    }
}
