/*
    +-------------------------------------------------------+
    |                       MOVE                            |
    +-------------------------------------------------------+
    |                                                       |
    |   - moveId            :  int                          |
    |   - playerName        :  string                       |
    |   - coordX            :  int                          |
    |   - coordY            :  int                          |
    |   - game              :  int                          |
    |                                                       |
    +-------------------------------------------------------+
 */

public class Move {

    private int moveId;  // Guarda o id da jogada
    private int coordX;  // Coordenada X
    private int coordY;  // Coordenada Y
    private int game;    // ID do jogo

    /* ############ CONSTRUTORES ############ */
    public Move(int moveId, int coordX, int coordY, int game) {
        this.moveId = moveId;
        this.coordX = coordX;
        this.coordY = coordY;
        this.game = game;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public int getMoveId() {
        return moveId;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getGame() {
        return game;
    }

    // Metodos SET
    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setGame(int game) {
        this.game = game;
    }
}
