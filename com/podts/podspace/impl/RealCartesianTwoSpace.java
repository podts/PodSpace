package com.podts.podspace.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.podts.podspace.CoordinateSpace;
import com.podts.podspace.CoordinateSystem;
import com.podts.podspace.Direction;
import com.podts.podspace.Path;
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
		
		public boolean equals(Object o) {
			if(o == null) return false;
			if(!(o instanceof RealTwoVector)) return false;
			RealTwoVector other = (RealTwoVector) o;
			return dir.equals(other.dir) && length == other.length;
		}
		
		public RealTwoVector(double x, double y) {
			length = Math.sqrt(x*x + y*y);
			dir = new RealTwoDirection(x/length, y/length);
		}
		
	}
	
	private static class RealTwoDirection implements Direction {
		
		protected double x,y;
		
		@Override
		public boolean equals(Object o) {
			if(o == null) return false;
			if(!(o instanceof RealTwoDirection)) return false;
			RealTwoDirection other = (RealTwoDirection)o;
			return x == other.x && y == other.y;
		}
		
		public RealTwoDirection(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private class RealTwoPath implements Path {
		
		private final RealTwoPoint start;
		private final double distance;
		private final RealTwoVector relative;
		
		@Override
		public Point getPoint(double distance) {
			return new RealTwoPoint(start.x + relative.dir.x*distance, start.y + relative.dir.y*distance);
		}
		
		@Override
		public RealTwoVector getTangent(double distance) {
			return relative;
		}
		
		@Override
		public double getLength() {
			return distance;
		}
		
		@Override
		public Space getSpace() {
			return RealCartesianTwoSpace.this;
		}
		
		RealTwoPath(final RealTwoPoint start, final RealTwoPoint finish) {
			this.start = start;
			distance = distance(start,finish);
			relative = new RealTwoVector(finish.x-start.x,finish.y-start.y);
		}
		
	}
	
	private final static List<Vector> basis;
	
	static {
		Vector[] basisArray = new Vector[2];
		basisArray[0] = new RealTwoVector(1,0);
		basisArray[1] = new RealTwoVector(0,1);
		basis = Collections.unmodifiableList(Arrays.asList(basisArray));
	}
	
	private double distance(RealTwoPoint a, RealTwoPoint b) {
		return Math.sqrt(Math.pow(b.x-a.x, 2) + Math.pow(b.y-a.y, 2));
	}
	
	private final CartesianCoordinateSystem coordSystem = new CartesianCoordinateSystem();
	private final Point origin = new RealTwoPoint(0,0);
	
	@Override
	public final int getDimensions() {
		return 2;
	}
	
	@Override
	public final CoordinateSystem getCoordinateSystem() {
		return coordSystem;
	}
	
	public final class CartesianCoordinateSystem implements CoordinateSystem {
		
		@Override
		public final List<Vector> getBasis(Point p) {
			return basis;
		}

		@Override
		public final Point getOrigin() {
			return origin;
		}

		@Override
		public Space getSpace() {
			return RealCartesianTwoSpace.this;
		}

		@Override
		public Point getPoint(final double... coordinates) {
			if(coordinates == null) return origin;
			if(coordinates.length != getDimensions())
				throw  new IllegalArgumentException("Invalid number of dimensions, requires "
						+ getDimensions() + " but recieved " + coordinates.length);
			return new RealTwoPoint(coordinates[0],coordinates[1]);
		}
		
	}

	@Override
	public final RealTwoPoint transport(final Point point, final Vector vector) {
		if(point == null || vector == null) throw new NullPointerException();
		if(point.getSpace() != this) throw new IllegalArgumentException();
		RealTwoPoint start = (RealTwoPoint) point;
		RealTwoVector v = (RealTwoVector) vector;
		return new RealTwoPoint(start.x + v.dir.x*v.length, start.y + v.dir.y*v.length);
	}

	@Override
	public final RealTwoPath getPath(final Point a, final Point b) {
		if(a == null || b == null) throw new NullPointerException();
		if(a.getSpace() != this || b.getSpace() != this) throw new IllegalArgumentException();
		return new RealTwoPath((RealTwoPoint)a,(RealTwoPoint)b);
	}

}
