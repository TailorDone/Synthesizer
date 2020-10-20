public class VFSineWave implements AudioComponent {
    public AudioComponent input;

    public VFSineWave(){}

    @Override
    public AudioClip getClip() {
        float phase = 0;
        int maxValue = Short.MAX_VALUE/300; //TODO change this back
        AudioClip original = input.getClip();
        AudioClip output = new AudioClip();
        for (int i = 0; i < AudioClip.NUM_SAMPLES; i++) {
            phase += ((2 * Math.PI * original.getSample(i)) / AudioClip.SAMPLE_RATE);
            output.setSample(i,(int)(maxValue * Math.sin(phase)));
        }
        return output;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        this.input = input;
    }
}
