import java.util.ArrayList;

public class Mixer implements AudioComponent {
    public ArrayList<AudioComponent> components = new ArrayList<>();

    public Mixer(){}

    @Override
    public AudioClip getClip() {
        AudioClip adjustedSample = new AudioClip();
            for (int i = 0; i < components.size(); i++) {
                AudioClip clip = components.get(i).getClip();
                for (int j = 0; j < AudioClip.NUM_SAMPLES; j++) {
                    adjustedSample.setSample(j, adjustedSample.getSample(j)+clip.getSample(j));
                }
            }
        return adjustedSample;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        components.add(input);
    }
}
