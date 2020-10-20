import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {
    public short[] shortArray = {1, 0, -112, 257};
    public AudioClip audio = new AudioClip(shortArray);

    @org.junit.jupiter.api.Test
    void getSampleTest() {
        assertEquals(1, audio.getSample(0), "Testing setting at first index");
        assertEquals(0, audio.getSample(1), "Testing setting a 0 value");
        assertEquals(-112, audio.getSample(2), "Testing setting a negative");
        assertEquals(257, audio.getSample(3), "Testing setting at last index");
    }

    @org.junit.jupiter.api.Test
    void setSampleTest() {
        audio.setSample(0, 32767);
        audio.setSample(1, -32768);
        audio.setSample(2, 0);
        audio.setSample(3, 32768);
        assertEquals(32767, audio.getSample(0), "Testing setting upperbound");
        assertEquals(-32768, audio.getSample(1), "Testing setting lowerbound");
        assertEquals(0, audio.getSample(2), "Testing 0");
        assertEquals(-32768, audio.getSample(3), "Testing that going beyond byte size will wrap back");
        //Test 255 (bit 7 is a one)
    }

    @org.junit.jupiter.api.Test
    void getDataTest() {
        short[] shortTest = {20, -13, 7};
        AudioClip audio = new AudioClip(shortTest);
        byte[] byteTest = {20, 0, -13, -1, 7, 0};
        assertArrayEquals(byteTest, audio.getData(), "Comparing Byte Arrays");
    }
}