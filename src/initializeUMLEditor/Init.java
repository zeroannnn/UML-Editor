package initializeUMLEditor;

import javax.swing.*;

public class Init {
    private Init() {
        System.out.println("Init instance is being created");
    }
    private static class Holder {
        private static final JFrame INSTANCE = new JFrame(component.frameTitle);
        
        static {
            System.out.println("Main JFrame instance is being created via Bill Pugh Pattern");
        }
    }
    public static JFrame getInstance() {
        return Holder.INSTANCE;
    }
}

