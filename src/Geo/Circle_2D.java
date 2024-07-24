package exe.ex4.geo;

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	public Point_2D _center;
	public double _radius;

	public Circle_2D(Point_2D cen, double rad) { // Constructs the center and the radius
		this._center = new Point_2D(cen);
		this._radius = rad;
	}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}

	@Override
	public String toString() { // Makes a string made of the class name,center of the circle and radius of the circle
		String ans = this.getClass().getSimpleName()+"," + _center.toString()+","+ this._radius;
		return ans;
	}
	@Override
	public boolean contains(Point_2D ot) { // this function check if a given point is inside the circle and returns true/false accordingly
		boolean ans = true;
		if (ot.distance(_center) > _radius)
			ans = false;
		return ans;
	}

	@Override
	public double area() { // computes the area of a given circle
		double ans = Math.pow(_radius, 2) * Math.PI;

		return ans;
	}
	@Override
	public double perimeter() { // computes the perimeter of the circle
		double ans = 2 * Math.PI * _radius;

		return ans;
	}
	@Override
	public void translate(Point_2D vec) { // Moves the circle according to the given vector

		this._center.move(vec);
	}
	@Override
	public GeoShape copy() { // Makes a copy of the given circle

		return new Circle_2D(this._center,this._radius);
	}

	@Override
	public void scale(Point_2D center, double ratio) {//This function scales the circle relative to the specified center point and given ratio
		this._center.scale(center, ratio);
		_radius*=ratio;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {//This function rotates the circle around the specified center point by the given angle.
		_center.rotate(center, angleDegrees);

	}

}
