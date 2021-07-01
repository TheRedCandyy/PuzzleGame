/**
 * ----  Puzzle Game  ----
 * Made By: Alexandre Tavares & Diogo Guedes TPSI1020 PL
 * Github: https://github.com/TheRedCandyy/PuzzleGame
 * Description: A school project for ATEC with the purpose of simulating a puzzle game.
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
 
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./DesignFXML.fxml"));
        Parent root = fxmlLoader.load();
        Scene screen = new Scene(root);
        String style = getClass().getResource("mycss.css").toExternalForm();
        screen.getStylesheets().add(style);

        /*File file = new File("../img/capa.jpg");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        mainPageAnchor.getChildren().add(imageView);*/

        primaryStage.setTitle("Puzzle Game");
        primaryStage.setScene(screen);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Coloca a aplicação no centro do ecrã
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}