import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EntitySpaceTest{
    @Test
    public void setEntityPositionTest(){
        EntitySpace testSpace = new EntitySpace();
        Vector2D testPosition = new Vector2D(50, 50);

        /*Within bounds test*/
        testSpace.setUserPosition(testPosition);
        assertEquals(50, testSpace.getUserPosition().x, 0.01);
        assertEquals(50, testSpace.getUserPosition().y, 0.01);

        /*Out of bounds - left wall*/
        testPosition.setValue(-50, 50);
        testSpace.setUserPosition(testPosition);
        assertEquals(0, testSpace.getUserPosition().x, 0.01);
        assertEquals(50, testSpace.getUserPosition().y, 0.01);

        /*Out of bounds - right wall*/
        testPosition.setValue(100000, 50);
        testSpace.setUserPosition(testPosition);
        assertEquals(800, testSpace.getUserPosition().x, 0.01);
        assertEquals(50, testSpace.getUserPosition().y, 0.01);

        /*Out of bounds - top wall*/
        testPosition.setValue(50, -50);
        testSpace.setUserPosition(testPosition);
        assertEquals(50, testSpace.getUserPosition().x, 0.01);
        assertEquals(0, testSpace.getUserPosition().y, 0.01);

        /*Out of bounds - bottom wall*/
        testPosition.setValue(50, 100000);
        testSpace.setUserPosition(testPosition);
        assertEquals(50, testSpace.getUserPosition().x, 0.01);
        assertEquals(600, testSpace.getUserPosition().y, 0.01);

    }
}
