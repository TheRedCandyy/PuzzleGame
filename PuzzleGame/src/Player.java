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

    private String firstName;   // Nome do jogador
    private String lastName;    // Apelido do jogador
    private int gameTime;       // Integer que guarda os segundo do jogo
    private int game;           // ID do jogo
    private String date;        // Data em que o jogo ocorreu    
    private String category;    // Categoria do jogo

    /* ############ CONSTRUTORES ############ */
    public Player(String firstName, String lastName, int gameTime, int game, String date ,String category) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.gameTime = gameTime;
        this.game = game;
        this.date = date;
        this.category = category;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public int getGameTime() {
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
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGameTime(int gameTime) {
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
}
