import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) throws LineUnavailableException {
        Clip c = AudioSystem.getClip();
        //This is the format that we're following, 44.1KHz mono audio, 16 bits per sample
        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
        AudioComponent gNote = new SineWave(393); //G
        AudioComponent cNote = new SineWave(262); //C
        AudioComponent eNote = new SineWave(329); //E
        AudioComponent vfClip = new VFSineWave();
        AudioComponent linearRamp = new LinearRamp(50,10000);
        vfClip.connectInput(linearRamp);
        VolumeFilter linearRampVolume = new VolumeFilter(.1);
        linearRampVolume.connectInput(vfClip);
        VolumeFilter gNoteVolumeChange = new VolumeFilter(.2);
        gNoteVolumeChange.connectInput(gNote);
        VolumeFilter cNoteVolumeChange = new VolumeFilter(.4);
        cNoteVolumeChange.connectInput(cNote);
        VolumeFilter eNoteVolumeChange = new VolumeFilter(.3);
        eNoteVolumeChange.connectInput(eNote);
        Mixer sirMixALot = new Mixer();
        sirMixALot.connectInput(gNoteVolumeChange);
        sirMixALot.connectInput(cNoteVolumeChange);
        sirMixALot.connectInput(eNoteVolumeChange);
        sirMixALot.connectInput(vfClip);
        AudioClip clip = sirMixALot.getClip();
        c.open(format16, clip.getData(), 0, AudioClip.NUM_SAMPLES); //reads data from my byte array to play it
        c.start(); //plays it
        c.loop(2); //plays it 2 more times if desired, so 3 seconds total
        while(c.getFramePosition() < AudioClip.SAMPLE_RATE || c.isActive() || c.isRunning()){}
        System.out.println("done");
        c.close();
    }
}
