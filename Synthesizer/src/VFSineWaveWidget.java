import javafx.scene.control.Control;
import javafx.scene.control.Label;


public class VFSineWaveWidget extends Widget {

    public VFSineWave vfSineWave = new VFSineWave();

    public VFSineWaveWidget() {
        super(new VFSineWave(), "VF Sine Wave");
        audio = vfSineWave;
        setTitleColor("#5FE8FF");

    }

    public Control makeLabel(String name){
        Label label = new Label(name);
        return label;
    }

}
