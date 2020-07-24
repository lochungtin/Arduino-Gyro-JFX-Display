import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    // javafx components
    private final BorderPane main = new BorderPane();
    private final StackPane holder = new StackPane();

    private static final Canvas c = new Canvas(1000, 720);
    private static final GraphicsContext gc = c.getGraphicsContext2D();
    private static final Environment env = new Environment(gc);

    // serial connection to arduino
    private final SerialConnection serial = new SerialConnection();

    public void init() {
        holder.getStyleClass().add("holder");
        holder.getChildren().add(c);
        main.setCenter(holder);

        serial.start();
    }

    @Override
    public void start(Stage stage){
        env.axisPlot();

        Scene scene = new Scene(main, 1280, 720);
        scene.getStylesheets().add("styles.css");
        stage.setOnCloseRequest( e -> serial.kill() );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }

    public static class ValueHandler {
        public static void update(String packet) {
            double w = Double.parseDouble(packet.substring(1, 5)) / 100;
            double x = Double.parseDouble(packet.substring(5, 9)) / 100;
            double y = Double.parseDouble(packet.substring(9, 13)) / 100;
            double z = Double.parseDouble(packet.substring(13, 17)) / 100;

            // +w -y +x -z is the calibrated specifically for the board configuration
            // _______
            // | [ ] |
            // _o___o_

            // +w +y -x -z is the calibrated specifically for the board configuration
            // _______
            // |o   o|
            // __[_]__

            env.update(new Quaternion(w, -y, x, -z).toAxisAngle());

            System.out.println(x + ", " + y + ", " + y + ", " + z);
        }
    }
}
