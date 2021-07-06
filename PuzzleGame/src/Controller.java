import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Controller implements Initializable{

    // Butões
    Button bt[] = new Button[25];
    String stbtDec[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", ""};
    String stbtRom[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", ""};
    String stbtAlph[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", ""};
    String stbtHex[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "10", "11", "12", "13", "14", "15", "16", "17", ""};
    String stbtCat[];
    List <Cell> lstCell = new ArrayList<Cell>();

    // FXML
    @FXML
    private MenuItem menuDecimal;

    @FXML
    private MenuItem menuHexadecimal;

    @FXML
    private MenuItem menuAlphabet;

    @FXML
    private MenuItem menuRoman;

    @FXML
    private MenuItem menuPlayGame;

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

    //Define o tipo de jogo como decimal e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setDecimalGame(ActionEvent event) {
        stbtCat = stbtDec;
        changeGameType();
    }

    //Define o tipo de jogo como romano e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setRomanGame(ActionEvent event) {
        stbtCat = stbtRom;
        changeGameType();
    }

    //Define o tipo de jogo como alfabeto e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setAlphabetGame(ActionEvent event) {
        stbtCat = stbtAlph;
        changeGameType();
    }

    //Define o tipo de jogo como hexadecimal e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setHexadecimalGame(ActionEvent event) {
        stbtCat = stbtHex;
        changeGameType();
    }
    
    //Começa um jogo
    @FXML
    void startGame(ActionEvent event) {
        for (int i = 0; i < 25; i++) {
                bt[i].setDisable(false);
        }
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
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bt[count] = new Button("");
                bt[count].setPrefSize(120, 90);
                bt[count].setId("gameBtn");
                bt[count].setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                bt[count].setAlignment(Pos.CENTER);
                bt[count].setOnAction(this::btnClick);
                bt[count].setDisable(true);
                gamePagePane.add(bt[count], j, i);
                count++;
            }
        }
    }

    //Faz a reordenação aleatoria da string com o texto para os butoes
    void randomizeBtnData(){
		List<String> intList = Arrays.asList(stbtCat);
		Collections.shuffle(intList);
		intList.toArray(stbtCat);
    }

    //Popula a classe dos butões
    void populateClass(){
        lstCell.clear();
        Cell c;
        for (int i = 0; i < 25; i++) {
            if (stbtCat[i].equals("")) {
                c = new Cell(stbtCat[i], i, true);
            }else{
                c = new Cell(stbtCat[i], i, false);
            }
            lstCell.add(c);
        }
    }

    //Coloca o texto nos butões
    void addButtonText(){
        for (int i = 0; i < 25; i++) {
            bt[i].setText(lstCell.get(i).getBtnText());
        }
    }

    void changeGameType(){
        randomizeBtnData();
        populateClass();
        addButtonText();
    }
    
    void btnClick(ActionEvent e){
        for (int i = 0; i < 25; i++) {
            if (e.getSource() == bt[i]) {
                if (lstCell.get(i).ishole()){
                    return;
                }
                if (lstCell.get(i).getPosition() == 0) {
                    if (lstCell.get(i+1).ishole()) {
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }else if(lstCell.get(i+5).ishole()){
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() == 4){
                    if (lstCell.get(i-1).ishole()) {
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i+5).ishole()){
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() == 20){
                    if (lstCell.get(i+1).ishole()) {
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() == 24){
                    if (lstCell.get(i-1).ishole()) {
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() >= 1 && lstCell.get(i).getPosition() <= 3){
                    if (lstCell.get(i-1).ishole()) {
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i+1).ishole()){
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }else if(lstCell.get(i+5).ishole()){
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() >= 21 && lstCell.get(i).getPosition() <= 23){
                    if (lstCell.get(i-1).ishole()) {
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i+1).ishole()){
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() == 5 || lstCell.get(i).getPosition() == 10 || lstCell.get(i).getPosition() == 15){
                    if (lstCell.get(i+5).ishole()) {
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }else if(lstCell.get(i+1).ishole()){
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }
                }else if(lstCell.get(i).getPosition() == 9 || lstCell.get(i).getPosition() == 14 || lstCell.get(i).getPosition() == 19){
                    if (lstCell.get(i+5).ishole()) {
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }else if(lstCell.get(i-1).ishole()){
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }
                }else{
                    if (lstCell.get(i+5).ishole()) {
                        lstCell.get(i+5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+5].setText(lstCell.get(i+5).getBtnText());
                    }else if(lstCell.get(i-1).ishole()){
                        lstCell.get(i-1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-1].setText(lstCell.get(i-1).getBtnText());
                    }else if(lstCell.get(i-5).ishole()){
                        lstCell.get(i-5).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i-5).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i-5].setText(lstCell.get(i-5).getBtnText());
                    }else if(lstCell.get(i+1).ishole()){
                        lstCell.get(i+1).setBtnText(lstCell.get(i).getBtnText());
                        lstCell.get(i+1).sethole(false);
                        lstCell.get(i).setBtnText("");
                        lstCell.get(i).sethole(true);
                        bt[i].setText(lstCell.get(i).getBtnText());
                        bt[i+1].setText(lstCell.get(i+1).getBtnText());
                    }
                }
            }
        }
    }
}
