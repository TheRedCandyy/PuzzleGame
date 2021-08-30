/*
    +-------------------------------------------------------+
    |                      PLAYER                           |
    +-------------------------------------------------------+
    |                                                       |
    |   - name              :  String                       |
    |   - gameTime          :  int                          |
    |   - game              :  int                          |
    |   - category          :  String                       |
    |                                                       |
    +-------------------------------------------------------+
 */

public class Player {

    private String name1;       // Nome do Jogador   
    private String name2;       // Apelido do Jogador
    private String gameTime;    // Integer que guarda os segundo do jogo
    private int game;           // ID do jogo
    private String date;        // Data em que o jogo ocorreu    
    private String category;    // Categoria do jogo


    /* ############ CONSTRUTORES ############ */
    public Player(String name1, String name2, String gameTime, int game, String date ,String category) {
        
        this.name1 = name1;
        this.name2 = name2;
        this.gameTime = gameTime;
        this.game = game;
        this.date = date;
        this.category = category;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getGameTime() {
        return gameTime;
    }

    public int getGame() {
        return game;
    }

    public String getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }

    // Metodos SET

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public void setGame(int game) {
        this.game = game;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format(name1 + " " + name2 + " - " + gameTime + " - " + game + " - " + category);
    }
}
