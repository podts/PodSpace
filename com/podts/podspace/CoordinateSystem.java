package com.podts.podspace;

import java.util.List;

public interface CoordinateSystem extends Spatial {
	
	public List<Vector> getBasis(final Point p);
	
	public Point getOrigin();
	
}
