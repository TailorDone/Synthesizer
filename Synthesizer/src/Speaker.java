public class Speaker implements AudioComponent{
    private AudioComponent input;

    //private Mixer sirMixALot = new Mixer();

    public Speaker(){
    }

    @Override
    public AudioClip getClip() {
        //return sirMixALot.getClip();
        return input.getClip();
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        this.input = input;
        //sirMixALot.connectInput(input);
        //audio.connectInput(input);
    }
}
