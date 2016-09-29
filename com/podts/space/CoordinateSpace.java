package com.podts.space;

/**
A Space that is backed internally by a specific coordinate system.
*/
public interface CoordinateSpace extends Space {
	
	public final List<Vector> getBasis(Point p);
	
	public final Point getOrigin();
	
}
