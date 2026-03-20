package model.concreteClass;

import model.basicObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

@DisplayName("compositeObject 測試 (Composite Pattern)")
public class CompositeObjectTest {

    private compositeObject composite;
    private basicObject child1;
    private basicObject child2;

    @BeforeEach
    void setUp() {
        composite = new compositeObject();
        child1 = new basicClass(new Point(100, 100));
        child2 = new useCase(new Point(200, 200));
    }

    @Test
    @DisplayName("新建的 compositeObject 應為空")
    void newComposite_isEmpty() {
        assertTrue(composite.getComposite().isEmpty());
    }

    @Test
    @DisplayName("addChild 後 getComposite 應包含該子物件")
    void addChild_increasesSize() {
        composite.addChild(child1);
        assertEquals(1, composite.getComposite().size());

        composite.addChild(child2);
        assertEquals(2, composite.getComposite().size());
    }

    @Test
    @DisplayName("addChild 後 getComposite 應回傳正確的子物件")
    void addChild_containsCorrectChildren() {
        composite.addChild(child1);
        composite.addChild(child2);

        assertTrue(composite.getComposite().contains(child1));
        assertTrue(composite.getComposite().contains(child2));
    }

    @Test
    @DisplayName("setSelect / getSelect 應正確運作")
    void selectMethods_workCorrectly() {
        assertFalse(composite.getSelect());

        composite.setSelect(true);
        assertTrue(composite.getSelect());

        composite.setSelect(false);
        assertFalse(composite.getSelect());
    }

    @Test
    @DisplayName("isMouseInside 在子物件範圍內應回傳 true")
    void isMouseInside_insideChild_returnsTrue() {
        composite.addChild(child1);
        // child1 在 (100,100)，大小 90x45，所以 (120, 120) 應在範圍內
        assertTrue(composite.isMouseInside(new Point(120, 120)));
    }

    @Test
    @DisplayName("isMouseInside 在所有子物件範圍外應回傳 false")
    void isMouseInside_outsideAllChildren_returnsFalse() {
        composite.addChild(child1);
        // (500, 500) 應不在任何子物件範圍內
        assertFalse(composite.isMouseInside(new Point(500, 500)));
    }
}
