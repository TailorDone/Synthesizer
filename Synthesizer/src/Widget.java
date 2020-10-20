import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

abstract public class Widget {
    protected BorderPane widgetElement;
    protected VBox rightNodes = new VBox();
    protected VBox leftNodes = new VBox();
    protected HBox title = new HBox();
    protected GridPane centerGrid = new GridPane();
    protected AudioComponent audio;
    protected String color;
    private double xMousePosition;
    private double yMousePosition;
    private boolean dragging = true;
    private Circle outputPort = new Circle(8, Color.GRAY);
    private Circle inputPort = new Circle(8, Color.WHITE);

    public Widget(AudioComponent audioComponent, String header) {
        this.audio = audioComponent;
        //Create widget box and size
        widgetElement = new BorderPane();
        widgetElement.setPrefSize(200, 100);
        if (header == "Speaker" || header == "Mixer"){
            widgetElement.setPrefSize(100, 100);
        }
        widgetElement.setBackground(new Background(new BackgroundFill(Color.web("0xFFFFFF", 1), null, null)));
        //Get and set title
        Label widgetName = new Label(header);
        title.getChildren().add(widgetName);
        title.setAlignment(Pos.CENTER);
        widgetElement.setTop(title);
        //Create center of widget
        centerGrid = new GridPane();
        //Create input jack
        if (this.audio.hasInput()) {
            inputPort.setStroke(Color.BLACK);
            inputPort.setStrokeWidth(1.5);
            leftNodes.getChildren().add(inputPort);
            widgetElement.setLeft(leftNodes);
            leftNodes.setMargin(inputPort, new Insets(5, 0, 15, 0));
            leftNodes.setPadding(new Insets(0, 5, 0, 5));
        }
        //Create output jack
        if(header != "Speaker") {
            rightNodes.setMargin(outputPort, new Insets(5, 0, 15, 0));
            rightNodes.setPadding(new Insets(0, 5, 0, 5));
            widgetElement.setRight(rightNodes);
            outputPort.setStroke(Color.BLACK);
            outputPort.setStrokeWidth(1.5);
            rightNodes.getChildren().add(outputPort);
            widgetElement.setRight(rightNodes);
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(30);
            centerGrid.getColumnConstraints().addAll(column1);
        }

        //Moving Widgets
        widgetElement.setOnMousePressed(event -> {
            xMousePosition = event.getX();
            yMousePosition = event.getY();
            if(outputPort.isHover()){
                dragging = false;
            } else {
                dragging = true;
            }
        });
        widgetElement.setOnMouseDragged(event -> {
            if(dragging){
                widgetElement.setLayoutX(event.getSceneX() - xMousePosition);
                widgetElement.setLayoutY(event.getSceneY() - yMousePosition);
            }
        });
    }

    protected void addSliderControl(Control label, Control text, Control slider){
        int getRow = centerGrid.getRowCount();
        int getCol = 0;
        centerGrid.add(label,getCol+1,getRow);
        getRow++;
        centerGrid.add(text,getCol,getRow);
        getCol++;
        centerGrid.add(slider,getCol,getRow);
        centerGrid.setPadding(new Insets(15,0,0,0));
        widgetElement.setCenter(centerGrid);
    }

    protected void setTitleColor(String color) {
        this.color = color;
        title.setBackground(new Background(new BackgroundFill(Color.web(this.color,1),null,null)));
    }

    public BorderPane getWidgeyWidIt(){
        return widgetElement;
    }

    public AudioComponent getAudioComponent(){
        return audio;
    }

    public Circle getOutputPort() {
        return outputPort;
    }

    public Circle getInputPort() {
        return inputPort;
    }

    public boolean isDragging() {
        return dragging;
    }
}