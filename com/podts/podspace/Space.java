package com.podts.podspace;

public interface Space {
	
	public int getDimensions();
	
	public Point getRelative(Point point, Vector vector);
	
}
