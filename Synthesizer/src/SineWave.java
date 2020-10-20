public class SineWave implements AudioComponent{
    private int frequency;

    public SineWave(int frequency){
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        int maxValue = Short.MAX_VALUE;
        AudioClip clip = new AudioClip();
        for (int i = 0; i < AudioClip.NUM_SAMPLES; i++) {
            clip.setSample(i, (short)(maxValue * Math.sin(2 * Math.PI * frequency * i / AudioClip.SAMPLE_RATE)));
        }
        return clip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
}
