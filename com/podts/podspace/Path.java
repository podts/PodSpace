package com.podts.podspace;

public interface Path extends Spatial {
	
	public Point getPoint(double distance);
	public Vector getTangent(double distance);
	public double getLength();
	
}
