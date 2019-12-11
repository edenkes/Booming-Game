package bredesh.actors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationActorTest {

    private LocationActor locationActor;
    private LocationActor locationActor2;
    private LocationActor locationActor3;
    private LocationActor locationActor4;

    @Before
    public void setUp() {
        locationActor = new LocationActor(0, 2, 100, 105);
        locationActor2 = new LocationActor(1, 0, 35, 87);
        locationActor3 = new LocationActor(5, 5, 5, 1);
        locationActor4 = new LocationActor(11, 1, 15, 1);
    }

    @Test
    public void isTouching() {
        assertTrue(locationActor.isTouching(locationActor2));
    }

    @Test
    public void isVerticalNotTouching() {
        assertFalse(locationActor.isTouching(locationActor3));
    }

    @Test
    public void isHorizontalNotTouching() {
        assertFalse(locationActor.isTouching(locationActor4));
    }
}