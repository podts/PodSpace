package com.podts.podspace;

import java.util.Set;

public interface SegregatedSpace extends Space {
	
	public Set<Region> getRegions();
	
	public Region getRegionFromPoint(Point p);
	
}
