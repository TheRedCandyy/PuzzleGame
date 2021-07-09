import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.text.html.HTML;

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
    Button bt[] = new Button[25];
    String stbtDec[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", ""};
    String stbtRom[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", ""};
    String stbtAlph[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", ""};
    String stbtHex[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "10", "11", "12", "13", "14", "15", "16", "17", ""};
    String stbtTesting[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "", "23"};
    String stbtCat[];
    String currentGameType;
    List <Cell> lstCell = new ArrayList<Cell>();
    List <Move> lstMove = new ArrayList<Move>();
    List <Player> lstPlayer = new ArrayList<Player>();
    int movesAmount = 0;
    int gameID = 0;
    String playerName = "";

    long tempoInicial;
    long tempoFinal;
    long duracaoJogo;

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
        currentGameType = "Decimal";
    }

    //Define o tipo de jogo como romano e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setRomanGame(ActionEvent event) {
        stbtCat = stbtRom;
        changeGameType();
        currentGameType = "Roman";
    }

    //Define o tipo de jogo como alfabeto e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setAlphabetGame(ActionEvent event) {
        stbtCat = stbtAlph;
        changeGameType();
        currentGameType = "Alphabet";
    }

    //Define o tipo de jogo como hexadecimal e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setHexadecimalGame(ActionEvent event) {
        stbtCat = stbtHex;
        changeGameType();
        currentGameType = "Hexadecimal";
    }
    
    //Começa um jogo
    @FXML
    void startGame(ActionEvent event) {
        tempoInicial = System.currentTimeMillis();
        playerName = "NAME UNDEFINED";
        for (int i = 0; i < 25; i++) {
                bt[i].setDisable(false);
        }
        movesAmount = 0;
        gameID++;
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
        gamePagePane.setPadding(new Insets(5, 25, 5, 25));
        //Add buttons to grid
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bt[count] = new Button("");
                bt[count].setPrefSize(180, 120);
                bt[count].setId("gameBtn");
                bt[count].setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 26));
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
                if(lstCell.get(i).ishole()){
                    Move m  = new Move(movesAmount, lstCell.get(i).getPosition(), gameID);
                    lstMove.add(m);
                    movesAmount++;
                }
            }
        }
        if(checkGame()){
            endGame();
        }
    }

    //Esta função corre quando o jogador completou o jogo.
    void endGame(){
        //Desativação dos butões
        for (int i = 0; i < bt.length; i++) {
            bt[i].setDisable(true);
        }
        
        //Calculo do tempo demorado a concluir o jogo
        tempoFinal = System.currentTimeMillis();
        duracaoJogo = tempoFinal - tempoInicial;
        int segundos = (int) (duracaoJogo / 1000) % 60 ;
        int minutos = (int) ((duracaoJogo / (1000*60)) % 60);
        int horas   = (int) ((duracaoJogo / (1000*60*60)) % 24);
        String gameTime = String.format("%01d:%02d:%02d", horas, minutos, segundos);

        //Adicionar um novo Jogador
        Player p =  new Player(playerName, gameTime,gameID, currentGameType);
        lstPlayer.add(p);

        String text = "\t\t\t\t\tGame Statistics\n\n\tPlayer: " + playerName + "\n\tTime: " + gameTime + "\n\tGame Category: " + currentGameType;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Completed!");
        alert.setHeaderText("You have completed the game!");
        alert.setContentText(text);
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    //Esta função verifica se o jogo está terminado, isto significa que verifica todas as posições e se estão corretamente ordenadas.
    boolean checkGame(){
        String checkArray[] = new String[25];
        for (int i = 0; i < 25; i++) {
            checkArray[i] = lstCell.get(i).getBtnText();
        }
        switch (currentGameType) {
            case "Decimal":
                return checkArray(stbtDec, checkArray);
            case "Roman":
                return checkArray(stbtRom, checkArray);
            case "Alphabet":
                return checkArray(stbtAlph, checkArray);
            case "Hexadecimal":
                return checkArray(stbtHex, checkArray);
            default:
                return false;
        }
    }

    //Esta função compara as 2 arrays de strings
    boolean checkArray(String[] stArray, String[] checkArray){
        for (int i = 0; i < 25; i++) {
            if (checkArray[i] != stArray[i]) {
                return false;
            }
        }
        return true;
    }
}
