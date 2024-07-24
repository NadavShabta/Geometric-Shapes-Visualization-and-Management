package exe.ex4.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	public Point_2D _min;
	public Point_2D _max;
	public   Point_2D bottomRight;
	public   Point_2D topLeft ;
	public double dx() {return _max.x() - _min.x();}
	public double dy() {return _max.y() - _min.y();}
	public Rect_2D(Point_2D p1, Point_2D p2) {//Finds the two missing vertices using the two given points
		this._min = new Point_2D(Math.min(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
		this._max = new Point_2D(Math.max(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
		this.bottomRight = new Point_2D(Math.max(_max.x(), _min.x()), Math.min(_max.y(), _min.y()));
		this.topLeft = new Point_2D(Math.min(_max.x(), _min.x()), Math.max(_max.y(), _min.y()));
	}
	public Rect_2D(Rect_2D t1) {//Finds the two missing vertices using the two given rectangle
		this(t1._max, t1._min);
	}



	@Override
	public String toString()
	{ return this.getClass().getSimpleName()+","+_max+","+_min;}

	@Override
	public boolean contains(Point_2D ot) { // check if the given Point_2D is inside the rectangle area

		boolean ans = ot.x() >= _min.x() && ot.x() <= _max.x();
		ans &= ot.y() >= _min.y() && ot.y() <= _max.y();
		return ans;
	}
	@Override
	public double area() { // computes the area of the rectangle
		double ans = dx() *dy();
		return ans;
	}

	@Override
	public double perimeter() { // computes the perimeter of the rectangle
		double ans = dx()*2 + dy()*2;
		return ans;
	}

	@Override
	public void translate(Point_2D vec) { // moves the rectangle by the given vector

		_min.move(vec);
		_max.move(vec);
		bottomRight.move(vec);
		topLeft.move(vec);
	}

	@Override
	public GeoShape copy() { // create a new copied rectangle

		return new Rect_2D(new Point_2D(_min), new Point_2D(_max));
	}

	@Override
	//This function scales the rectangle relative to the specified center point and given ratio
	public void scale(Point_2D center, double ratio) {
		Point_2D bottomRight = new Point_2D(Math.max(_max.x(), _min.x()), Math.min(_max.y(), _min.y()));
		Point_2D topLeft = new Point_2D(Math.min(_max.x(), _min.x()), Math.max(_max.y(), _min.y()));
		_min.scale(center,ratio);
		_max.scale(center,ratio);
		bottomRight.scale(center,ratio);
		topLeft.scale(center,ratio);
	}

	@Override
	//This function rotates the rectangle around the specified center point by the given angle.
	public void rotate(Point_2D center, double angleDegrees) {
		Point_2D bottomRight = new Point_2D(Math.max(_max.x(), _min.x()), Math.min(_max.y(), _min.y()));
		Point_2D topLeft = new Point_2D(Math.min(_max.x(), _min.x()), Math.max(_max.y(), _min.y()));
		_min.rotate(center,angleDegrees);
		_max.rotate(center,angleDegrees);
		bottomRight.rotate(center,angleDegrees);
		topLeft.rotate(center,angleDegrees);
	}

	public Point_2D getMinPoint() {// Getter method for the minimum point
		return _min;
	}

	public Point_2D getMaxPoint() {// Getter method for the maximum point
		return _max;
	}
}
