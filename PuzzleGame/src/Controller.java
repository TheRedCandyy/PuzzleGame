import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.Date;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.*;
import static java.lang.System.*;

public class Controller implements Initializable{

    // Butões
    Button bt[] = new Button[25];
    String stbtDec[] = {"0", "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    List <Cell> lstCell = new ArrayList<Cell>();
    List <Player> lstPlayer = new ArrayList<Player>();

    //Dados realcionados com a base de dados
    static java.sql.Connection conn;
	static Statement st ;
	static ResultSet rs ;
    static int dev;
    static boolean ligacaoDB = false;

    //Date formater - Utilizado para formatar a data no "DB connection Status"
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");  

    //Array List de jogadores - Utilizado para popular a listView
    final ObservableList<Player> jogadores =  FXCollections.observableArrayList();
        
    // FXML
    @FXML
    private MenuItem menuDecimal;

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
    
    @FXML
    private AnchorPane statisticsPageAnchor;

    @FXML
    private Pane statisticsPane;

    @FXML
    private TableView<Player> statsTable;

    @FXML
    private TableColumn<Player, String> tableColFirstName;

    @FXML
    private TableColumn<Player, String> tableColLastName;

    @FXML
    private TableColumn<Player, String> tableColDate;

    @FXML
    private TableColumn<Player, String> tableColCategory;

    @FXML
    private TableColumn<Player, Integer> tableColTime;
    
    @FXML
    private Label labelBdConn;

    @FXML
    private Label labelDbStatus;

    @FXML
    private Label labelLastDbUpdate;

    @FXML
    private Label labelUpdateDate;

    @FXML
    private Button btnRefreshStats;

    @FXML
    private Button btnDeleteStats;


    //Define o tipo de jogo como decimal e corre o metodo `changeGameType` que popula a classe e os butoes
    @FXML
    void setDecimalGame(ActionEvent event) {
        changeGameType("Decimal");
    }
    
    @FXML
    void startGame(ActionEvent event) {
        for (int i = 0; i < 25; i++) {
                bt[i].setDisable(false);
        }
    }

    //Encerra a aplicação
    @FXML
    void quitGame(ActionEvent event) {
        System.gc();
        System.exit(1);
    }

    //Abre um Pop-Up com a informação dos autores da aplicação
    @FXML
    void aboutAuthors(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Authors");
        alert.setHeaderText("July 2021\nTPSI PL 1020");
        alert.setContentText("Alexandre Tavares\nDiogo Guedes");
        alert.showAndWait().ifPresent(rs -> {
        });
        
    }

    //Abre um Pop-Up com a informação da linguagem utilizada para desenvolver a aplicação
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

    //Abre um Pop-Up com infromação sobre a aplicação
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
        //Connecção com a base de dados
        connectToDatabase();
    }

    /*void addBackgroundImage() throws FileNotFoundException {
        ImageView imgv = new ImageView(new Image(new FileInputStream(
                "C:\\Users\\Alexandre Tavares\\OneDrive\\Documentos\\GitHub\\PuzzleGame\\PuzzleGame\\img\\capa.jpg")));
        // mainPageAnchor.getChildren().add(imgv);
    }*/

    //Botão de refresh das estatisticas
    @FXML
    void refreshStats(ActionEvent event){
        //Reconecção com a base de dados e posteriro população da tabela de records
        connectToDatabase();
    }

    
    @FXML
    void DeleteStat(ActionEvent event){

    }

    //Função que realiza a connecção com a base de dados
    void connectToDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/puzzlegame","root","");
            ligacaoDB = true;
            
           if (ligacaoDB){ //Se existir uma conecção com sucesso
                //Alteração da label de status da conecção
                labelDbStatus.setText("● Connected");
                labelDbStatus.setTextFill(Color.web("green", 1));
                //População da tabela de resultados
                drawStatsTable();
                //Aletração da label da data de refresh
                LocalDateTime dateTime = LocalDateTime.now();  
                String data = dateTime.format(dtf); 
                labelUpdateDate.setText(data);
                //Encerra a conecção com a base de dados
                conn.close();
           }

         }catch(Exception exc) { //Se a conecção não obtiver sucesso
            exc.printStackTrace();
            ligacaoDB = false;
            //Alteração da label de status da conecção
            labelDbStatus.setText("● Disconnected");
            labelDbStatus.setTextFill(Color.web("red", 1));
        }
    }

    //Função que popula a tabela das estatisticas com dados da base de dados
    void drawStatsTable(){

        statsTable.setId("statsTable");
        jogadores.clear();
        String queryPlayerStats = "SELECT * FROM playerStats_OrdBy_Name";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            st = conn.createStatement();
            rs = st.executeQuery(queryPlayerStats);
        
            while(rs.next()){
                String nome = rs.getString("firstName");
                String apleido = rs.getString("lastName");
                String data = rs.getDate("date").toString();
                int jogo = rs.getInt("game");
                String categoria = rs.getString("category");
                int tempo = rs.getInt("time")/60;

                Player pl = new Player(nome,apleido,tempo,jogo,data,categoria);
                jogadores.add(pl);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        tableColFirstName.setCellValueFactory(
            new PropertyValueFactory<Player,String>("firstName") );

        tableColLastName.setCellValueFactory(
            new PropertyValueFactory<Player,String>("lastName") );

        tableColDate.setCellValueFactory(
            new PropertyValueFactory<Player,String>("date") );

        tableColCategory.setCellValueFactory(
            new PropertyValueFactory<Player,String>("category") );    

        tableColTime.setCellValueFactory(
            new PropertyValueFactory<Player,Integer>("gameTime") );    
        
        statsTable.setItems(jogadores);

        
    }
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
    void randomizeBtnData(String gameType){
		List<String> intList = Arrays.asList(stbtDec);
		Collections.shuffle(intList);
		intList.toArray(stbtDec);
    }

    //Popula a classe dos butões
    void populateClass(String gameType){
        lstCell.clear();
        Cell c;
        for (int i = 0; i < 25; i++) {
            if (stbtDec[i].equals("")) {
                c = new Cell(stbtDec[i], i, true);
            }else{
                c = new Cell(stbtDec[i], i, false);
            }
            lstCell.add(c);
        }
    }

    //Coloca o texto nos butões
    void addButtonText(String gameType){
        for (int i = 0; i < 25; i++) {
            bt[i].setText(lstCell.get(i).getBtnText());
        }
    }

    void changeGameType(String gameType){
        switch (gameType) {
            case "Decimal":
                //randomizeBtnData(gameType);
                populateClass(gameType);
                addButtonText(gameType);
                break;
            default:
                break;
        }
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
