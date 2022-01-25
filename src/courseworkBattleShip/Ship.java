package courseworkBattleShip;

public class Ship {
	
	private String type;
	private int length;
	private int points;
	
	// constructor for Ship
	public Ship (String type, int length, int points) {
		setType(type);
		setLength(length);
		setPoints(points);
	}
	
	// setters and getters
	public void setType(String type) {
		this.type = type;
	}
	public  String getType() {
		return this.type;
	}
	
	public int getLength() {
		return this.length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints() {
		return this.points;
	}
	
}
