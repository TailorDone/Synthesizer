import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class LinearRampWidget extends Widget {
    public LinearRamp linearRamp = new LinearRamp(0,10000);
    public Slider min = new Slider (0,10000,0);
    public Slider max = new Slider (0,10000, 10000);
    TextField minText;
    TextField maxText;

    public LinearRampWidget() {
        super(new LinearRamp(0,10000), "LinearRamp");
        audio = linearRamp;
        setTitleColor("#FC49AB");
        addSliderControl(makeLabel("Min"), makeMinText(), makeMinSlider());
        addSliderControl(makeLabel("Max"), makeMaxText(), makeMaxSlider());
    }

    public Control makeMinSlider(){
        min.setShowTickMarks(true);
        min.setMajorTickUnit(2500f);
        min.setBlockIncrement(20f);
        min.valueProperty().addListener((observable, oldValue, newValue) -> {
            linearRamp.setStart((int)min.getValue());
        });
        return min;
    }

    public Control makeMinText(){
        minText = new TextField();
        minText.setText("0");
        min.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#");
            minText.setText(String.valueOf(df.format(min.getValue())));
        });
        return minText;
    }

    public Control makeMaxSlider(){
        max.setShowTickMarks(true);
        max.setMajorTickUnit(2500f);
        max.setBlockIncrement(20f);
        max.valueProperty().addListener((observable, oldValue, newValue) -> {
            linearRamp.setStop((int)max.getValue());
        });
        return max;
    }

    public Control makeMaxText(){
        maxText = new TextField();
        maxText.setText("100000");
        max.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#");
            maxText.setText(String.valueOf(df.format(max.getValue())));
        });
        return maxText;
    }

    public Control makeLabel(String name){
        Label label = new Label(name);
        return label;
    }

}
