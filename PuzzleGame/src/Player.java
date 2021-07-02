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

    private String name;      // Nome do jogador
    private int gameTime;     // Integer que guarda os segundo do jogo
    private int game;         // ID do jogo
    private String category;  // Categoria do jogo

    /* ############ CONSTRUTORES ############ */
    public Player(String name, int gameTime, int game, String category) {
        this.name = name;
        this.gameTime = gameTime;
        this.game = game;
        this.category = category;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public String getName() {
        return name;
    }

    public int getGameTime() {
        return gameTime;
    }

    public int getGame() {
        return game;
    }

    public String getCategory() {
        return category;
    }

    // Metodos SET
    public void setName(String name) {
        this.name = name;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
