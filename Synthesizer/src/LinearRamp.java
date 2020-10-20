public class LinearRamp implements AudioComponent{
    private float start;
    private float stop;

    public LinearRamp(float start, float stop){
        this.start = start;
        this.stop = stop;
    }

    @Override
    public AudioClip getClip() {
        AudioClip linearClip = new AudioClip();
        for ( int i = 0; i < AudioClip.NUM_SAMPLES; i++){
            linearClip.setSample(i,(int)((start*(AudioClip.NUM_SAMPLES - i) + stop*i)/AudioClip.NUM_SAMPLES));
        }
        return linearClip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
    }

    public void setStart(float start) {
        this.start = start;
    }

    public void setStop(float stop) {
        this.stop = stop;
    }
}
