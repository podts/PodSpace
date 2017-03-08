package com.podts.podspace;

public interface Space {
	
	public int getDimensions();
	
	public Point transport(Point point, Vector vector);
	
	/**
	 * Returns a geodesic from point A to point B.
	 * @param a - The starting point.
	 * @param b - The ending point.
	 */
	public Path getPath(Point a, Point b);
	
}
