package initializeUMLEditor;

import javax.swing.*;

public class Init {
    private static JFrame uniqueInstance ;
    private Init() {}
    public static JFrame getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance =  new JFrame(component.frameTitle);
        }
        return uniqueInstance;
    }
}

