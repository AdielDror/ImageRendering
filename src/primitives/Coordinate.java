package primitives;

public class Coordinate {
	private double coord;
	
	

	public Coordinate(double coord) {
		setCoord(coord);
	}

	@Override
	public boolean equals(Object obj) {
	
		return super.equals(obj);
	}

	public double getCoord() {
		return coord;
	}

	public void setCoord(double coord) {
		this.coord = coord;
	}
	
	
}
