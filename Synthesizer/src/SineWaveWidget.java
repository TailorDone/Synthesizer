import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class SineWaveWidget extends Widget {
    public SineWave sineWave = new SineWave(500);
    public Slider scale = new Slider (0,1000,500);
    TextField freqText;

    public SineWaveWidget() {
        super(new SineWave(500), "Sine Wave");
        audio = sineWave;
        setTitleColor("#FF7300");
        addSliderControl(makeLabel("Frequency"), makeText(), makeSlider());
    }

    public Control makeSlider(){
        scale.setShowTickMarks(true);
        scale.setMajorTickUnit(250f);
        scale.setBlockIncrement(50f);
        scale.valueProperty().addListener((observable, oldValue, newValue) -> {
            sineWave.setFrequency((int)scale.getValue());
        });
        return scale;
    }

    public Control makeText(){
            freqText = new TextField();
            freqText.setText("500");
            scale.valueProperty().addListener((observable, oldValue, newValue) -> {
                DecimalFormat df = new DecimalFormat("#.#");
                freqText.setText(String.valueOf(df.format(scale.getValue())));
        });
        return freqText;
    }

    public Control makeLabel(String name){
        Label label = new Label(name);
        return label;
    }

    public SineWave getSineWave(){
        return sineWave;
    }
}
