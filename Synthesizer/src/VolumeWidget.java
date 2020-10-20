import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class VolumeWidget extends Widget{
    public VolumeFilter volume = new VolumeFilter(1);
    public Slider scale = new Slider (0,1,0.5);
    TextField volText;

    public VolumeWidget(){
        super(new VolumeFilter(1),"Volume");
        audio = volume;
        setTitleColor("#E7ff00");
        addSliderControl(makeLabel("Scale"),  makeText(), makeSlider());
    }

    public Control makeSlider(){
        scale.setShowTickMarks(true);
        scale.setBlockIncrement(.05f);
        scale.setMajorTickUnit(.25f);
        scale.valueProperty().addListener((observable, oldValue, newValue) -> {
            volume.setVolumeScale((scale.getValue()));
        });
        return scale;
    }

    public Control makeText(){
        volText = new TextField();
        volText.setText("0.5");
        scale.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#.##");
            volText.setText(String.valueOf(df.format(scale.getValue())));
        });
        return volText;
    }

    public Control makeLabel(String name){
        Label label = new Label(name);
        return label;
    }

    public VolumeFilter getVolumeFilter(){
        return volume;
    }
}
