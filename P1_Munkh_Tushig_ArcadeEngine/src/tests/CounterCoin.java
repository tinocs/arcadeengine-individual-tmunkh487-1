package tests;

import javafx.scene.image.Image;

public class CounterCoin extends TestActor {
	
	private static final Image COIN_IMAGE = new Image(CounterCoin.class.getResource("/testresources/Coin.png").toString());

	public CounterCoin() {
		setImage(COIN_IMAGE);
	}

}
