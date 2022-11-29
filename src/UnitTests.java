import static org.junit.jupiter.api.Assertions.assertEquals;
class UnitTests {

    @org.junit.jupiter.api.Test
    void mapFromWorldToPlane() {

    }

    @org.junit.jupiter.api.Test
    void mapRangeToRange() {
        assertEquals(500, Utils.mapRangeToRange(0, 0, 254, 500, 5500));

        assertEquals(0, Utils.mapRangeToRange(0, 0, 10000, 0, 5000));

        assertEquals(2500, Utils.mapRangeToRange(5000, 0, 10000, 0, 5000) );
    }
}