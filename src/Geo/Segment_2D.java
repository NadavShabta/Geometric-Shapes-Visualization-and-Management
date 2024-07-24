package exe.ex4.geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{

	public Point_2D start,end;

	public Segment_2D(Point_2D a, Point_2D b) { // constructs the segment from a given 2 points
		this.start = a;
		this.end = b;
	}

	public Segment_2D(Segment_2D t1) {// constructs the segment from a given segment
		this(t1.start,t1.end);
	}
	@Override
	public String toString()
	{ return this.getClass().getSimpleName()+","+start+","+end;}

	public Point_2D get_p1() { //Getter for the start point

		return this.start;

	}

	public Point_2D get_p2() {//Getter for the end point


		return this.end;
	}

	@Override
	public boolean contains(Point_2D ot) { // check if a given point is in the segment

		if ((end.distance(start))== (start.distance(ot) + end.distance(ot))) {
			return true;
		}
		else return false;
	}

	@Override
	public double area() {

		return 0;
	}

	@Override
	public double perimeter() {// perimeter of the segment

		return 2*(start.distance(end));
	}

	@Override
	public void translate(Point_2D vec) { // moves the segment by the given vector
		start.move(vec);
		end.move(vec);

	}

	@Override
	public GeoShape copy() { // create a new copy of the segment

		return new Segment_2D (new Point_2D(this.start),new Point_2D(this.end));
	}

	@Override
	//This function scales the rectangle relative to the specified center point and given ratio
	public void scale(Point_2D center, double ratio) {
		start.scale(center,ratio);
		end.scale(center,ratio);
	}

	@Override
	//This function rotates the rectangle around the specified center point by the given angle.
	public void rotate(Point_2D center, double angleDegrees) {
		start.rotate(center,angleDegrees);
		end.rotate(center,angleDegrees);

	}
}