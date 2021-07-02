/*
    +-------------------------------------------------------+
    |                     BUTTON                            |
    +-------------------------------------------------------+
    |                                                       |
    |   - btnText           :  string                       |
    |   - coordX            :  int                          |
    |   - coordY            :  int                          |
    |   - empty             :  boolean                      |
    |                                                       |
    +-------------------------------------------------------+
 */
public class Button {

    private String btnText;    // Guarda o texto do botão
    private int coordX;        // Coordenada X
    private int coordY;        // Coordenada Y
    private boolean empty;     // Indica se o botão está ocupado 

    /* ############ CONSTRUTORES ############ */
    public Button() {
    }

    public Button(String btnText, int coordX, int coordY, boolean empty) {
        this.btnText = btnText;
        this.coordX = coordX;
        this.coordY = coordY;
        this.empty = empty;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public String getBtnText() {
        return btnText;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public boolean isEmpty() {
        return empty;
    }

    // Metodos SET
    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}