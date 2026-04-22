package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {
    private int scoreVal;

    public void updateDisplay() {
        setText("Score: " + scoreVal);
    }

    public Score() {
        scoreVal = 0;

        setFont(Font.font(20));

        updateDisplay();
    }

    public int getScoreVal() {
        return scoreVal;
    }

    public void setScoreVal(int scoreVal) {
        this.scoreVal = scoreVal;
        updateDisplay();
    }
}
