package model.game;

public interface IPlayer {
	void setBoard(GameBoard gb);
	GameShip getGameShip();
	void initFighters();
	boolean isFleetDestroyed();
	String showShip();
	void purgeFleet();
	boolean nextPlay();
}
