package Define;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

@DisplayName("findObjectPort 測試")
public class findObjectPortTest {

    @Test
    @DisplayName("getPort 應回傳 4 個端口")
    void getPort_returnsFourPorts() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(100, 100), new Dimension(90, 45));
        assertEquals(4, ports.length);
    }

    @Test
    @DisplayName("getPort 上方端口位置應正確（中上）")
    void getPort_topPort_isCorrect() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(100, 100), new Dimension(90, 45));
        // 上端口 = (x + width/2, y) = (145, 100)
        assertEquals(new Point(145, 100), ports[0]);
    }

    @Test
    @DisplayName("getPort 右方端口位置應正確（右中）")
    void getPort_rightPort_isCorrect() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(100, 100), new Dimension(90, 45));
        // 右端口 = (x + width, y + height/2) = (190, 122)
        assertEquals(new Point(190, 122), ports[1]);
    }

    @Test
    @DisplayName("getPort 下方端口位置應正確（中下）")
    void getPort_bottomPort_isCorrect() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(100, 100), new Dimension(90, 45));
        // 下端口 = (x + width/2, y + height) = (145, 145)
        assertEquals(new Point(145, 145), ports[2]);
    }

    @Test
    @DisplayName("getPort 左方端口位置應正確（左中）")
    void getPort_leftPort_isCorrect() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(100, 100), new Dimension(90, 45));
        // 左端口 = (x, y + height/2) = (100, 122)
        assertEquals(new Point(100, 122), ports[3]);
    }

    @Test
    @DisplayName("getPort 在原點 (0,0) 也應正確計算")
    void getPort_atOrigin_isCorrect() {
        findObjectPort portFinder = new findObjectPort();
        Point[] ports = portFinder.getPort(new Point(0, 0), new Dimension(100, 50));
        assertEquals(new Point(50, 0), ports[0]); // 上
        assertEquals(new Point(100, 25), ports[1]); // 右
        assertEquals(new Point(50, 50), ports[2]); // 下
        assertEquals(new Point(0, 25), ports[3]); // 左
    }
}
