package com.podts.space;

public interface CoordinateSpace extends Space {
	
	public final List<Vector> getBasis(Point p);
	
	public final Point getOrigin();
	
}
