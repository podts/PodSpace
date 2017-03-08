package com.podts.podspace;

public interface Path extends Spatial {
	
	public Point getPoint(double distance);
	public double getLength();
	
}
