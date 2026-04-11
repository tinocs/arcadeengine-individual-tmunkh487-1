package tests;

import javafx.scene.image.Image;

public class Coin extends TestActor {
	
	private static final Image COIN_IMAGE = new Image(Coin.class.getResource("/testresources/Coin.png").toString());
	
	public Coin() {
		setImage(COIN_IMAGE);
		setFitWidth(40);
		setFitHeight(40);
	}
}
