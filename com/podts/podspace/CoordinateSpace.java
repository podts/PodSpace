package com.podts.podspace;

/**
A Space that is backed internally by a specific coordinate system.
*/
public interface CoordinateSpace extends Space {
	
	public CoordinateSystem getCoordinateSystem();
	
}
