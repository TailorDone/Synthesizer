import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SineWaveTest {

    @Test
    void getClip() {
        SineWave ANote = new SineWave(440);
        AudioClip audio = ANote.getClip();
    }
}