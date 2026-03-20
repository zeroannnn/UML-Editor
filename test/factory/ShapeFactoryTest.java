package factory;

import model.basicObject;
import model.concreteClass.basicClass;
import model.concreteClass.useCase;
import model.decorator.BorderDecorator;
import model.decorator.ShadowDecorator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

@DisplayName("ShapeFactory 測試")
public class ShapeFactoryTest {

    @Test
    @DisplayName("createShape('class') 應回傳 basicClass 實例")
    void createShape_class_returnsBasicClass() {
        basicObject obj = ShapeFactory.createShape("class", new Point(100, 100));
        assertNotNull(obj);
        assertTrue(obj instanceof basicClass);
    }

    @Test
    @DisplayName("createShape('useCase') 應回傳 useCase 實例")
    void createShape_useCase_returnsUseCase() {
        basicObject obj = ShapeFactory.createShape("useCase", new Point(200, 200));
        assertNotNull(obj);
        assertTrue(obj instanceof useCase);
    }

    @Test
    @DisplayName("createShape 傳入未知類型應丟出 IllegalArgumentException")
    void createShape_unknownType_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ShapeFactory.createShape("unknown", new Point(0, 0));
        });
    }

    @Test
    @DisplayName("createShape 建立的物件位置應正確")
    void createShape_positionIsCorrect() {
        Point expectedPoint = new Point(150, 250);
        basicObject obj = ShapeFactory.createShape("class", expectedPoint);
        assertEquals(expectedPoint, obj.getLocation());
    }

    @Test
    @DisplayName("createDecoratedShape 帶 Border 應回傳 BorderDecorator")
    void createDecoratedShape_withBorder_returnsBorderDecorator() {
        basicObject obj = ShapeFactory.createDecoratedShape("class", new Point(100, 100), true, false);
        assertTrue(obj instanceof BorderDecorator);
    }

    @Test
    @DisplayName("createDecoratedShape 帶 Shadow 應回傳 ShadowDecorator")
    void createDecoratedShape_withShadow_returnsShadowDecorator() {
        basicObject obj = ShapeFactory.createDecoratedShape("class", new Point(100, 100), false, true);
        assertTrue(obj instanceof ShadowDecorator);
    }

    @Test
    @DisplayName("createDecoratedShape 帶 Border + Shadow 應回傳 BorderDecorator 包裝 ShadowDecorator")
    void createDecoratedShape_withBorderAndShadow_returnsBorderWrappingShadow() {
        basicObject obj = ShapeFactory.createDecoratedShape("class", new Point(100, 100), true, true);
        assertTrue(obj instanceof BorderDecorator);
    }

    @Test
    @DisplayName("createDecoratedShape 不帶裝飾應回傳原始物件")
    void createDecoratedShape_noDecoration_returnsOriginal() {
        basicObject obj = ShapeFactory.createDecoratedShape("class", new Point(100, 100), false, false);
        assertTrue(obj instanceof basicClass);
    }
}
