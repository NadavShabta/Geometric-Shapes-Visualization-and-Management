package exe.ex4.gui;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */

import exe.ex4.geo.*;

import java.awt.*;



public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShape g, boolean f, Color c, int t) { // construct the GuiShape
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {// construct the GuiShape from a given GuiShape
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	
	@Override
	public GeoShape getShape() {
		return _g;
	} // Getter that returns the geoShape

	@Override
	public boolean isFilled() {
		return _fill;
	} // returns the _fill flag

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}// sets the _fill flag

	@Override
	public Color getColor() {
		return _color;
	} // Getter that returns the color of the GuiShape

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}// sets the _color field

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
		
	}

	@Override
	public GUI_Shape copy() { // create a new copy of the GuyShape
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}

	@Override
	public String toString() { // create a string representing the geoshape
		return _g.toString();
	}
	public static GUI_Shape convertStringToGUI_Shape(String shapeString) {
		String[] shapeData = shapeString.split(",");

		// Extract the relevant values from the shapeData array
		String type = shapeData[4];
		int colorValue = Integer.parseInt(shapeData[1]);
		boolean filled = Boolean.parseBoolean(shapeData[2]);
		int tag = Integer.parseInt(shapeData[3]);

		// Create the corresponding GeoShape object based on the shape type
		GeoShape geoShape = null;
		if (type.equals("Circle_2D")) {
			double centerY = Double.parseDouble(shapeData[6]);
			double centerX = Double.parseDouble(shapeData[5]);
			double radius = Double.parseDouble(shapeData[7]);
			geoShape = new Circle_2D(new Point_2D(centerX, centerY),radius);

		} else if (type.equals("Rect_2D")) {
			if(shapeData.length == 9) {
				double xmin = Double.parseDouble(shapeData[5]);
				double ymin = Double.parseDouble(shapeData[6]);
				double xmam = Double.parseDouble(shapeData[7]);
				double ymax = Double.parseDouble(shapeData[8]);
				geoShape = new Rect_2D(new Point_2D(xmin, ymin), new Point_2D(xmam, ymax));
			}
			else {
				double xmin = Double.parseDouble(shapeData[5]);
				double ymin = Double.parseDouble(shapeData[6]);
				double xmam = Double.parseDouble(shapeData[9]);
				double ymax = Double.parseDouble(shapeData[10]);
				geoShape = new Rect_2D(new Point_2D(xmin, ymin), new Point_2D(xmam, ymax));
			}
		} else if (type.equals("Polygon_2D")) {
			// Extract the polygon vertices from shapeData based on the number of vertices

			geoShape = new Polygon_2D();
			for (int i = 5; i < shapeData.length -1; i = i+2) {
				double x = Double.parseDouble(shapeData[i]);
				double y = Double.parseDouble(shapeData[i+1]);
				((Polygon_2D) geoShape).getVerts().add(new Point_2D(x, y));
			}

		} else if (type.equals("Triangle_2D")) {
			double x1 = Double.parseDouble(shapeData[5]);
			double y1 = Double.parseDouble(shapeData[6]);
			double x2 = Double.parseDouble(shapeData[7]);
			double y2 = Double.parseDouble(shapeData[8]);
			double x3 = Double.parseDouble(shapeData[9]);
			double y3 = Double.parseDouble(shapeData[10]);
			geoShape = new Triangle_2D(new Point_2D(x1, y1), new Point_2D(x2, y2), new Point_2D(x3, y3));

		} else if (type.equals("Segment_2D")) {
			double x1 = Double.parseDouble(shapeData[5]);
			double y1 = Double.parseDouble(shapeData[6]);
			double x2 = Double.parseDouble(shapeData[7]);
			double y2 = Double.parseDouble(shapeData[8]);
			geoShape = new Segment_2D(new Point_2D(x1, y1), new Point_2D(x2, y2));
		}

		// Create the GUIShape object with the extracted values and GeoShape
		Color color = new Color(colorValue);
		return new GUIShape(geoShape, filled, color, tag);
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	} //returns the _selected flag
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}// sets the _selected field
	@Override
	public void setShape(GeoShape g) { // set the GuiShape according to the given GeoShape
		if (g != null) {
			_g = g.copy();
		}
	  }
	}

