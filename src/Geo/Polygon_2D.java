package exe.ex4.geo;

import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	public ArrayList<Point_2D> verts;

	public Polygon_2D() { // Constructs an ArrayList for al the vertices

		this.verts = new ArrayList<>();
	}

	public Polygon_2D(Polygon_2D po) { // Constructs an ArrayList for al the vertices from a given polygon
		this();
		for (int i = 0; i <po.verts.size() ; i++) {
			this.verts.add(new Point_2D(po.verts.get(i)));
		}
	}
	public ArrayList<Point_2D> getVerts () {
		return this.verts;

	}






	public Point_2D[] getAllPoints() {// returns an Array of all the points of the polygon
		Point_2D[] points = new Point_2D[verts.size()];

		for (int i = 0; i < verts.size(); i++) {
			points[i] = verts.get(i);
		}
		return points;
	}

	public void add(Point_2D p) { // adds new point to the polygon
		verts.add(new Point_2D(p));
	}

	@Override
	public String toString(){ //this function generates a concise description of the Polygon_2D object.
		StringBuilder sb = new StringBuilder();
		sb.append("Polygon_2D,");
		for (Point_2D vertex : verts) {
			sb.append(vertex.toString()).append(",");
		}
		if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length() - 1); // Remove trailing comma
		}

		return sb.toString();
	}


	@Override
	//Check if the provided point ot is inside the polygon by using the Ray-Casting algorithm or the Point-in-Polygon algorithm.
	public boolean contains(Point_2D ot) {
		int crossings = 0;
		int n = verts.size();

		for (int i = 0; i < n; i++) {
			Point_2D v1 = verts.get(i);
			Point_2D v2 = verts.get((i + 1) % n);

			// Check if the point is on the same y-coordinate range as the edge
			if ((v1.y() <= ot.y() && v2.y() > ot.y()) ||
					(v1.y() > ot.y() && v2.y() <= ot.y())) {

				// Calculate the x-coordinate where the ray intersects the edge
				double intersectX = (ot.y() - v1.y()) /
						(v2.y() - v1.y()) *
						(v2.x() - v1.x()) +
						v1.x();

				// Count the number of edge crossings
				if (ot.x() < intersectX) {
					crossings++;
				}
			}
		}

// If the number of crossings is odd, the point is inside the polygon
		return crossings % 2 == 1;
	}

	@Override
	public double area() {//Calculates the area of a polygon using the Shoelace formula.
		int n = verts.size();
		double sum = 0;

		for (int i = 0; i < n; i++) {
			Point_2D current = verts.get(i);
			Point_2D next = verts.get((i + 1) % n);
			sum += (current.x() * next.y()) - (current.y() * next.x());
		}

		return 0.5 * Math.abs(sum);
	}

	@Override
	public double perimeter() { // computes the perimeter
		double perimeter = 0;
		int n = verts.size();
		for (int i = 0; i < n; i++) {
			Point_2D currentVertex = verts.get(i);
			Point_2D nextVertex = verts.get((i + 1) % n); // Wrap around to the first vertex
			perimeter += currentVertex.distance(nextVertex);
		}
		return perimeter;
	}

	@Override
	public void translate(Point_2D vec) {// moves the polygon by the given vector
		for (int i = 0; i < verts.size(); i++) {
			verts.get(i).move(vec);

		}
	}

	@Override
	public GeoShape copy() { // create a new copy of the exist polygon

		return new Polygon_2D(this);

	}

	@Override
	//This function scales the rectangle relative to the specified center point and given ratio
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < verts.size(); i++) {
			verts.get(i).scale(center,ratio);

		}
	}

	@Override
	//This function rotates the polygon around the specified center point by the given angle.
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < verts.size(); i++) {
			verts.get(i).rotate(center,angleDegrees);

		}

	}

}
