import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cable {
    public Widget outWidget;
    public Widget inWidget;
    public AudioComponent outputAudio;
    public AudioComponent inputAudio;
    public Line cableLine;

    public Cable(Widget output, Widget input) {
        outWidget = output;
        inWidget = input;
        outputAudio = output.getAudioComponent();
        inputAudio = input.getAudioComponent();
        outputAudio.connectInput(inputAudio);
        Point2D outPoint = outWidget.getInputPort().localToScene(outWidget.getInputPort().getCenterX(),outWidget.getInputPort().getCenterY());
        Point2D inPoint = inWidget.getOutputPort().localToScene(inWidget.getOutputPort().getCenterX(),inWidget.getOutputPort().getCenterY());
        cableLine = new Line(outPoint.getX(), outPoint.getY(), inPoint.getX(), inPoint.getY());
        cableLine.setStroke(Color.LIGHTSLATEGRAY);
        cableLine.setStrokeWidth(5);
    }

    public AudioComponent getCableOutput(){
        return outputAudio;
    }

    public Line drawLine(){
        return cableLine;
    }
}
