package tests;

import javafx.scene.image.Image;

public class Player extends TestActor {
	
    private static final Image STAND_LEFT_IMG = new Image(Player.class.getResource("/testresources/CharacterLeft_Standing.png").toString());
    private static int playerNum = 0;
    private String name;
    
    public Player() {
    	setImage(STAND_LEFT_IMG);
    	setFitWidth(30);
    	setFitHeight(32);
    	playerNum++;
    	name = "player" + playerNum;
    }

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}
    
}
