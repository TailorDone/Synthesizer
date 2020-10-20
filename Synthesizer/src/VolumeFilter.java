public class VolumeFilter implements AudioComponent {
    private double scale;
    private AudioComponent input;

    public VolumeFilter(double scale){
        this.scale = scale;
    }

    @Override
    public AudioClip getClip() {
        AudioClip original = input.getClip();
        AudioClip adjustedSample = new AudioClip();
        for (int i = 0; i < AudioClip.NUM_SAMPLES; i++) {
            adjustedSample.setSample(i,(int)(original.getSample(i)*scale));
        }
        return adjustedSample;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
       this.input = input;
    }

    public void setVolumeScale(double scale){
        this.scale = scale;
    }

    public double getVolumeScale(){
        return scale;
    }
}
