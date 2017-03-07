package com.podts.podspace.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.podts.podspace.CoordinateSpace;
import com.podts.podspace.Direction;
import com.podts.podspace.Point;
import com.podts.podspace.Space;
import com.podts.podspace.Vector;

public class RealCartesianTwoSpace implements CoordinateSpace {
	
	private class RealTwoPoint implements Point {
		
		private final double x,y;
		
		@Override
		public Space getSpace() {
			return RealCartesianTwoSpace.this;
		}
		
		public RealTwoPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static class RealTwoVector implements Vector {
		
		private final RealTwoDirection dir;
		private final double length;
		
		@Override
		public Direction getDirection() {
			return dir;
		}

		@Override
		public double getLength() {
			return length;
		}
		
		public RealTwoVector(RealTwoDirection dir, double length) {
			this.dir = dir;
			this.length = length;
		}
		
		public RealTwoVector(double x, double y) {
			length = Math.sqrt(x*x + y*y);
			dir = new RealTwoDirection(x/length, y/length);
		}
		
	}
	
	private static class RealTwoDirection implements Direction {
		
		protected double x,y;
		
		public RealTwoDirection(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private final static List<Vector> basis;
	
	private final Point origin = new RealTwoPoint(0,0);
	
	static {
		Vector[] basisArray = new Vector[2];
		basisArray[0] = new RealTwoVector(1,0);
		basisArray[1] = new RealTwoVector(0,1);
		basis = Collections.unmodifiableList(Arrays.asList(basisArray));
	}
	
	@Override
	public final int getDimensions() {
		return 2;
	}

	@Override
	public final List<Vector> getBasis(Point p) {
		return basis;
	}

	@Override
	public final Point getOrigin() {
		return origin;
	}

	@Override
	public Point getRelative(Point point, Vector vector) {
		if(point == null || vector == null) throw new NullPointerException();
		if(point.getSpace() != this) throw new IllegalArgumentException();
		RealTwoPoint start = (RealTwoPoint) point;
		RealTwoVector v = (RealTwoVector) vector;
		return new RealTwoPoint(start.x + v.dir.x*v.length, start.y + v.dir.y*v.length);
	}

}
