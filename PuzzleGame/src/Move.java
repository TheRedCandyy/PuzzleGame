/*
    +-------------------------------------------------------+
    |                       MOVE                            |
    +-------------------------------------------------------+
    |                                                       |
    |   - moveId            :  int                          |
    |   - position          :  int                          |
    |   - game              :  int                          |
    |                                                       |
    +-------------------------------------------------------+
 */

public class Move {

    private int moveId;  // Guarda o id da jogada
    private int position;// Posicao
    private int game;    // ID do jogo

    /* ############ CONSTRUTORES ############ */
    public Move(int moveId, int position, int game) {
        this.moveId = moveId;
        this.position = position;
        this.game = game;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public int getMoveId() {
        return moveId;
    }

    public int getPosition() {
        return position;
    }

    public int getGame() {
        return game;
    }

    // Metodos SET
    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGame(int game) {
        this.game = game;
    }
}
