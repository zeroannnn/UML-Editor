package factory;

import model.lineObject;
import model.concreteClass.associationLine;
import model.concreteClass.compositionLine;
import model.concreteClass.generalizationLine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

@DisplayName("LineFactory 測試")
public class LineFactoryTest {

    @Test
    @DisplayName("createLine('association') 應回傳 associationLine 實例")
    void createLine_association_returnsAssociationLine() {
        lineObject line = LineFactory.createLine("association", new Point(0, 0), new Point(100, 100));
        assertNotNull(line);
        assertTrue(line instanceof associationLine);
    }

    @Test
    @DisplayName("createLine('composition') 應回傳 compositionLine 實例")
    void createLine_composition_returnsCompositionLine() {
        lineObject line = LineFactory.createLine("composition", new Point(0, 0), new Point(100, 100));
        assertNotNull(line);
        assertTrue(line instanceof compositionLine);
    }

    @Test
    @DisplayName("createLine('generalization') 應回傳 generalizationLine 實例")
    void createLine_generalization_returnsGeneralizationLine() {
        lineObject line = LineFactory.createLine("generalization", new Point(0, 0), new Point(100, 100));
        assertNotNull(line);
        assertTrue(line instanceof generalizationLine);
    }

    @Test
    @DisplayName("createLine 傳入未知類型應丟出 IllegalArgumentException")
    void createLine_unknownType_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            LineFactory.createLine("unknown", new Point(0, 0), new Point(100, 100));
        });
    }

    @Test
    @DisplayName("createLine 建立的連線起終點應正確")
    void createLine_endpointsAreCorrect() {
        Point start = new Point(10, 20);
        Point end = new Point(300, 400);
        lineObject line = LineFactory.createLine("association", start, end);
        assertEquals(start, line.getStartingPoint());
        assertEquals(end, line.getEndingPoint());
    }
}
