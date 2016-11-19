package com.podts.podspace;

import java.util.List;

/**
A Space that is backed internally by a specific coordinate system.
*/
public interface CoordinateSpace extends Space {
	
	public List<Vector> getBasis(Point p);
	
	public Point getOrigin();
	
}
