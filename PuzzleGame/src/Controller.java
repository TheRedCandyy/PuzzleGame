import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Controller implements Initializable{

    // Butões
    static Button bt[][] = new Button[5][5];
    static String stbt[][] = {
        {"0", "1", "2", "3", "4"},
        {"5", "6", "7", "8", "9"},
        {"10", "11", "12", "13", "14"},
        {"15", "16", "17", "18", "19"},
        {"20", "21", "22", "23", ""}
    };

    // FXML
    @FXML
    private MenuItem languageMenuItem;

    @FXML
    private MenuItem softwareMenuItem;

    @FXML
    private MenuItem authorsMenuItem;

    @FXML
    private Button testingButton;

    @FXML
    private GridPane gamePagePane;

    @FXML
    void testingAction(ActionEvent event) {
        System.out.println("Testing...");
    }

    @FXML
    void quitGame(ActionEvent event) {
        System.gc();
        System.exit(1);
    }

    @FXML
    void aboutAuthors(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Authors");
        alert.setHeaderText("July 2021\nTPSI PL 1020");
        alert.setContentText("Alexandre Tavares\nDiogo Guedes");
        alert.showAndWait().ifPresent(rs -> {
        });
        
    }

    @FXML
    void aboutLaunguage(ActionEvent event) {
        //Dialogo
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Language");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.setContentText("This software was created using java language.\n\nThis software was developed using javaFX in Version 11.0.2 and was used SceneBuilder in Version 8.2.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    @FXML
    void aboutSoftware(ActionEvent event) {
        //Dialogo
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Language");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.setContentText("This software was created using as a school project in ATEC.\n\nThe aim was to recreate a popular known game, where the objective is to order the pieces of a puzzle.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        drawButtons();
    }

    /*void addBackgroundImage() throws FileNotFoundException {
        ImageView imgv = new ImageView(new Image(new FileInputStream(
                "C:\\Users\\Alexandre Tavares\\OneDrive\\Documentos\\GitHub\\PuzzleGame\\PuzzleGame\\img\\capa.jpg")));
        // mainPageAnchor.getChildren().add(imgv);
    }*/
    
    void drawButtons() {
        // Game Panel Layout
        gamePagePane.setAlignment(Pos.TOP_CENTER);
        gamePagePane.setPadding(new Insets(30, 30, 30, 30));
        //Add buttons to grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bt[i][j] = new Button(stbt[j][i]);
                bt[i][j].setPrefSize(120, 90);
                bt[i][j].setId("gameBtn");
                bt[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                bt[i][j].setAlignment(Pos.CENTER);
                bt[i][j].setOnAction(this::btnClick);
                gamePagePane.add(bt[i][j], i, j);
            }
        }
    }

    void btnClick(ActionEvent e){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == bt[i][j]) {
                    if (i == 0 && j == 0) {
                        if (bt[i+1][j].getText() == "") {
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j+1].getText() == ""){
                            bt[i][j+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(i == 0 && j == 4){
                        if (bt[i+1][j].getText() == "") {
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j-1].getText() == ""){
                            bt[i][j-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(i == 4 && j == 0){
                        if (bt[i-1][j].getText() == "") {
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j+1].getText() == ""){
                            bt[i][j+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(i == 4 && j == 4){
                        if (bt[i-1][j].getText() == "") {
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j-1].getText() == ""){
                            bt[i][j-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(i == 0){
                        if (bt[i+1][j].getText() == "") {
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j+1].getText() == ""){
                            bt[i][+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j-1].getText() == ""){
                            bt[i][-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(i == 4){
                        if (bt[i-1][j].getText() == "") {
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j+1].getText() == ""){
                            bt[i][j+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j-1].getText() == ""){
                            bt[i][j-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(j == 0){
                        if (bt[i][j+1].getText() == "") {
                            bt[i][j+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i+1][j].getText() == ""){
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i-1][j].getText() == ""){
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else if(j == 4){
                        if (bt[i][j-1].getText() == "") {
                            bt[i][j-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i+1][j].getText() == ""){
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i-1][j].getText() == ""){
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }else{
                        if (bt[i+1][j].getText() == "") {
                            bt[i+1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j+1].getText() == ""){
                            bt[i][j+1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i-1][j].getText() == ""){
                            bt[i-1][j].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }else if(bt[i][j-1].getText() == ""){
                            bt[i][j-1].setText(bt[i][j].getText());
                            bt[i][j].setText("");
                        }
                    }
                }
            }
        }
    }
}
