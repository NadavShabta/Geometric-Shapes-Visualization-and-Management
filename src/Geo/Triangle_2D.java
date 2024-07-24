package exe.ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	public  double side1,side2,side3;
	public Point_2D ver1,ver2,ver3;

	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) { //Constructs sides and vertices with given 3 Points
		this.side1 = p1.distance(p2);
		this.side2 = p2.distance(p3);
		this.side3 = p3.distance(p1);
		this.ver1 = p1;
		this.ver2 = p2;
		this.ver3 = p3;
	}

	public Triangle_2D(Triangle_2D t1) {//Constructs sides and vertices with given triangle
		this(t1.ver1 , t1.ver2 , t1.ver3);
	}


	@Override
	public String toString()
	{ return this.getClass().getSimpleName()+","+ver1+","+ver2+","+ver3;}
	public Point_2D[] getAllPoints() { //this function returns Point_2D array with the 3 vertices
		Point_2D[] get = new Point_2D[3];
		get[0] = this.ver1;
		get[1] = this.ver2;
		get[2] = this.ver3;

		return get;
	}

	@Override
	public boolean contains(Point_2D ot) { //Calculates the area of the triangle formed by the given vertices, and verifies if the sum
		// of the areas of the sub-triangles formed by one vertex and two other vertices is equal to the area of the original triangle.
		Triangle_2D tri = new Triangle_2D(ver1,ver2,ver3);
		double all = tri.area ();
		Triangle_2D tri1 = new Triangle_2D(ot,ver2,ver3);
		double a1 = tri1.area ();
		Triangle_2D tri2 = new Triangle_2D(ot,ver1,ver3);
		double a2 = tri2.area ();
		Triangle_2D tri3 = new Triangle_2D(ot,ver1,ver2);
		double a3 = tri3.area ();
		boolean b ;
		if(all == a1 + a2 + a3) {b = true;}
		else b = false;
		return b;
	}


	@Override
	public double area() { // https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
		return Math.abs((ver1.x()*(ver2.y()-ver3.y()) + ver2.x()*(ver3.y()-ver1.y())+ ver3.x()*(ver1.y()-ver2.y()))/2.0);

	}

	@Override
	public double perimeter() { // calculates the perimeter by summing all the sides
		return (side1 + side2 + side3);
	}

	@Override
	public void translate(Point_2D vec) { //moves the triangle by the given vector
		ver1.move(vec);
		ver2.move(vec);
		ver3.move(vec);
	}

	@Override
	public GeoShape copy() { // create a new copy of the given triangle
		Triangle_2D copy = new Triangle_2D(ver1,ver2,ver3);
		return copy ;
	}

	@Override
	//This function scales the triangle relative to the specified center point and given ratio
	public void scale(Point_2D center, double ratio) {
		ver1.scale(center,ratio);
		ver2.scale(center,ratio);
		ver3.scale(center,ratio);
	}

	@Override
	//This function rotates the triangle around the specified center point by the given angle.
	public void rotate(Point_2D center, double angleDegrees) {
		ver1.rotate(center,angleDegrees);
		ver2.rotate(center,angleDegrees);
		ver3.rotate(center,angleDegrees);

	}

}
