package model.decorator;

import model.basicObject;
import model.concreteClass.basicClass;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

@DisplayName("Decorator Pattern 測試")
public class DecoratorTest {

    private basicObject originalObject;
    private Point testPoint;

    @BeforeEach
    void setUp() {
        testPoint = new Point(100, 100);
        originalObject = new basicClass(testPoint);
    }

    // ===== BasicObjectDecorator 委派測試 =====

    @Test
    @DisplayName("BorderDecorator 應正確委派 getLocation()")
    void borderDecorator_delegatesGetLocation() {
        basicObject decorated = new BorderDecorator(originalObject, Color.RED, 2);
        assertEquals(testPoint, decorated.getLocation());
    }

    @Test
    @DisplayName("BorderDecorator 應正確委派 getName() / setName()")
    void borderDecorator_delegatesNameMethods() {
        basicObject decorated = new BorderDecorator(originalObject, Color.RED, 2);
        assertEquals("Basic class", decorated.getName());

        decorated.setName("NewName");
        assertEquals("NewName", decorated.getName());
        // 原物件也應同步更新（委派）
        assertEquals("NewName", originalObject.getName());
    }

    @Test
    @DisplayName("BorderDecorator 應正確委派 getSize()")
    void borderDecorator_delegatesGetSize() {
        basicObject decorated = new BorderDecorator(originalObject, Color.RED, 2);
        assertEquals(originalObject.getSize(), decorated.getSize());
    }

    @Test
    @DisplayName("BorderDecorator 應正確委派 setSelect() / getSelect()")
    void borderDecorator_delegatesSelectMethods() {
        basicObject decorated = new BorderDecorator(originalObject, Color.RED, 2);
        assertFalse(decorated.getSelect());

        decorated.setSelect(true);
        assertTrue(decorated.getSelect());
        assertTrue(originalObject.getSelect());
    }

    @Test
    @DisplayName("BorderDecorator 應正確委派 getPorts()")
    void borderDecorator_delegatesGetPorts() {
        basicObject decorated = new BorderDecorator(originalObject, Color.RED, 2);
        Point[] originalPorts = originalObject.getPorts();
        Point[] decoratedPorts = decorated.getPorts();

        assertArrayEquals(originalPorts, decoratedPorts);
    }

    @Test
    @DisplayName("ShadowDecorator 應正確委派 getLocation()")
    void shadowDecorator_delegatesGetLocation() {
        basicObject decorated = new ShadowDecorator(originalObject, 5, Color.GRAY);
        assertEquals(testPoint, decorated.getLocation());
    }

    @Test
    @DisplayName("ShadowDecorator 應正確委派 setLocation()")
    void shadowDecorator_delegatesSetLocation() {
        basicObject decorated = new ShadowDecorator(originalObject, 5, Color.GRAY);
        Point newPoint = new Point(200, 300);
        decorated.setLocation(newPoint);

        assertEquals(newPoint, decorated.getLocation());
        assertEquals(newPoint, originalObject.getLocation());
    }

    // ===== Decorator 可疊加測試 =====

    @Test
    @DisplayName("Decorator 可層層疊加，且委派仍然正確")
    void decorators_canBeStacked() {
        basicObject step1 = new ShadowDecorator(originalObject, 5, Color.GRAY);
        basicObject step2 = new BorderDecorator(step1, Color.RED, 2);

        // 最外層 Decorator 仍然能正確取得原物件的資訊
        assertEquals(testPoint, step2.getLocation());
        assertEquals("Basic class", step2.getName());

        // 修改最外層，原物件也應同步
        step2.setName("Stacked");
        assertEquals("Stacked", originalObject.getName());
    }

    @Test
    @DisplayName("Decorator 包裝後 getSize 與原物件一致")
    void decorator_preservesSize() {
        basicObject decorated = new BorderDecorator(
                new ShadowDecorator(originalObject, 5, Color.GRAY),
                Color.BLUE, 3);
        assertEquals(originalObject.getSize(), decorated.getSize());
    }
}
