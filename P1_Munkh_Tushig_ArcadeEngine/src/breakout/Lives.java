package breakout;


import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lives extends Text {
    private int livesVal;

    public void updateDisplay() {
        setText("Lives: " + livesVal);
    }

    public Lives() {
        livesVal = 3;
        setFont(Font.font(20));

        updateDisplay();
    }

    public int getLivesVal() {
        return livesVal;
    }

    public void setLivesVal(int livesVal) {
        this.livesVal = livesVal;
        updateDisplay();
    }
}
