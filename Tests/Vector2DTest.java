import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class Vector2DTest{
    @Test
    public void normalizeTest(){
        Vector2D testVector = new Vector2D(1, 0);
        testVector.normalize();
        assertEquals(1, testVector.x, 0.01);
        assertEquals(0, testVector.y, 0.01);

        testVector.setValue(0, 1);
        testVector.normalize();
        assertEquals(0, testVector.x, 0.01);
        assertEquals(1, testVector.y, 0.01);

        testVector.setValue(4, 4);
        testVector.normalize();
        assertEquals(0.7, testVector.x, 0.01);
        assertEquals(0.7, testVector.y, 0.01);
    }
}
