import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;


public class MyApp extends Application {
    private ArrayList<Widget> activeWidgets = new ArrayList<>();
    private ArrayList<Cable> activeCables = new ArrayList<>();
    private ArrayList<Line> activeLines = new ArrayList<>();
    private Widget outputWidget;
    private Widget inputWidget;
    private SpeakerWidget speaker;
    AudioClip clip;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set pane and background color
        primaryStage.setTitle("Synthesizer");
        BorderPane root = new BorderPane();
        Pane centerPane = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.web("0x070600",1),null,null)));
        //Create buttons
        Button volumeButton = new Button("Volume");
        Button sineWaveButton = new Button("Sine Wave");
        Button linearRampButton = new Button("Linear Ramp");
        Button vfSineWaveButton = new Button("VFSineWave");
        Button mixerButton = new Button("Mixer");
        Button speakerButton = new Button("Speaker");
        Button playButton = new Button("Play");
        //Set button style
        volumeButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        sineWaveButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        linearRampButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        vfSineWaveButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        mixerButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        speakerButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #030301; -fx-font-size: 15");
        playButton.setStyle("-fx-background-color: #FC49AB; -fx-text-fill: #FFFFFF; -fx-font-size: 25");
        //Set button size
        volumeButton.setMinSize(110,40);
        sineWaveButton.setMinSize(110,40);
        linearRampButton.setMinSize(110,40);
        vfSineWaveButton.setMinSize(110,40);
        mixerButton.setMinSize(110,40);
        speakerButton.setMinSize(110,40);
        playButton.setMinSize(150, 40);
        //Set elements and show stage
        HBox playButtonHolder = new HBox(playButton);
        VBox buttonHolder = new VBox();
        buttonHolder.getChildren().addAll(volumeButton, sineWaveButton, linearRampButton, vfSineWaveButton, speakerButton, mixerButton);
        buttonHolder.setSpacing(10);
        playButtonHolder.setAlignment(Pos.BOTTOM_RIGHT);
        buttonHolder.setMinWidth(150);
        buttonHolder.setAlignment(Pos.BASELINE_CENTER);
        buttonHolder.setBackground(new Background(new BackgroundFill(Color.web("0x5FE8FF"),null,null)));
        root.setBottom(playButtonHolder);
        root.setRight(buttonHolder);
        root.setCenter(centerPane);
        Scene scene = new Scene(root,1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        //Action Event listeners to place widgets on scene upon click
        volumeButton.setOnAction(ActionEvent -> {
                VolumeWidget widget = new VolumeWidget();
                activeWidgets.add(widget);
                centerPane.getChildren().add(widget.getWidgeyWidIt());
        });
        sineWaveButton.setOnAction(ActionEvent -> {
                SineWaveWidget widget = new SineWaveWidget();
                activeWidgets.add(widget);
                centerPane.getChildren().add(widget.getWidgeyWidIt());
        });
        linearRampButton.setOnAction(ActionEvent -> {
            LinearRampWidget widget = new LinearRampWidget();
            activeWidgets.add(widget);
            centerPane.getChildren().add(widget.getWidgeyWidIt());
        });
        vfSineWaveButton.setOnAction( ActionEvent -> {
            VFSineWaveWidget widget = new VFSineWaveWidget();
            activeWidgets.add(widget);
            centerPane.getChildren().add(widget.getWidgeyWidIt());
        });
        mixerButton.setOnAction(ActionEvent -> {
            MixerWidget widget = new MixerWidget();
            activeWidgets.add(widget);
            centerPane.getChildren().add(widget.getWidgeyWidIt());
        });
        speakerButton.setOnAction(ActionEvent -> {
            speaker = new SpeakerWidget();
            activeWidgets.add(speaker);
            centerPane.getChildren().add(speaker.getWidgeyWidIt());
        });

//        for(Widget widget: activeWidgets){
//            if(widget.isDragging()){
//                System.out.println("here");
//                for(Cable cable : activeCables){
//                    if(widget == cable.outWidget || widget == cable.inWidget){
//                        System.out.println("I got here");
//                        activeCables.remove(cable);
//                        activeCables.remove(cable.cableLine);
//                        centerPane.getChildren().remove(cable.cableLine);
//                        }
//                    }
//            }
//        }
        //Create Cable when an input and output are clicked
        centerPane.setOnMouseClicked(event -> {
            for (Widget widget : activeWidgets) {
                if (widget.getOutputPort().isHover()){
                    inputWidget = widget;
                }
                if (widget.getInputPort().isHover()){
                    outputWidget = widget;
                }
                if (outputWidget!=null && inputWidget!=null){
                    activeCables.add(new Cable(outputWidget,inputWidget));
                    centerPane.getChildren().add(activeCables.get(activeCables.size()-1).cableLine);
                    activeLines.add(activeCables.get(activeCables.size()-1).cableLine);
                    System.out.println("connected");
                    outputWidget = null;
                    inputWidget = null;
                }
            }
            for (Cable cable: activeCables){
                if(cable.cableLine.isHover()){
                    activeCables.remove(cable);
                    centerPane.getChildren().remove(cable.cableLine);
                }
            }
        });
        //Play Audio on click
        playButton.setOnAction(ActionEvent -> {
            Clip c = null;
            try {
                c = AudioSystem.getClip();

            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            //This is the format that we're following, 44.1KHz mono audio, 16 bits per sample
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
            //Takes audio from speaker
            clip = speaker.audio.getClip();
            try {
                c.open(format16, clip.getData(), 0, AudioClip.NUM_SAMPLES); //reads data from my byte array to play it
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            System.out.println("Button Pressed");
            c.start(); //plays it
            c.setFramePosition(0);
        });





    }

    public static void main(String[] args) {
        MyApp.launch(args);
    }
}

