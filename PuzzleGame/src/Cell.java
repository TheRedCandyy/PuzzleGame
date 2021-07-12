import javafx.scene.control.Button;

/*
    +-------------------------------------------------------+
    |                     BUTTON                            |
    +-------------------------------------------------------+
    |                                                       |
    |   - btnText           :  string                       |
    |   - position          :  int                          |
    |   - hole              :  boolean                      |
    |                                                       |
    +-------------------------------------------------------+
 */
public class Cell extends Button{

    private String btnText;    // Guarda o texto do botão
    private int position;     // Posicao
    private boolean hole;     // Indica se o botão está ocupado 

    /* ############ CONSTRUTORES ############ */
    public Cell() {
    }

    public Cell(String btnText, int position, boolean hole) {
        this.btnText = btnText;
        this.position = position;
        this.hole = hole;
    }

    /* ############ METODOS GET E SET ############ */
    // Metodos GET
    public String getBtnText() {
        return btnText;
    }

    public int getPosition() {
        return position;
    }

    public boolean ishole() {
        return hole;
    }

    // Metodos SET
    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void sethole(boolean hole) {
        this.hole = hole;
    }
}