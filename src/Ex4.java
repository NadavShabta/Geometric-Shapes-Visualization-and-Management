package exe.ex4.ex4;

import exe.ex4.geo.*;
import exe.ex4.gui.Ex4_GUI;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import exe.ex4.gui.StdDraw_Ex4;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 *
 * @author boaz.benmoshe
 */
public class Ex4 implements Ex4_GUI {
    private static Ex4 _winEx4 = null;
    private GUI_Shape_Collection _shapes = new ShapeCollection();
    private GUI_Shape _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point_2D _p1;
    private Polygon_2D _polygon;
    private int _tag = 0;

    private Ex4() {
        init(null);
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    private static void drawShape(GUI_Shape g) {
        // * Draws the given GUI_Shape on the StdDraw_Ex4 canvas with the specified color and fill settings.
        // * The shape is drawn based on its underlying GeoShape type.
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) {
            StdDraw_Ex4.setPenColor(Color.gray);
        }
        GeoShape gs = g.getShape();
        boolean isFill = g.isFilled();
        if (gs instanceof Circle_2D) {
            Circle_2D c = (Circle_2D) gs;
            Point_2D cen = c.getCenter();
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }

        if (gs instanceof Segment_2D) {
            Segment_2D c3 = (Segment_2D) gs;
            if (isFill) {
                StdDraw_Ex4.line(c3.start.x(), c3.start.y(), c3.end.x(), c3.end.y());
            } else {
                StdDraw_Ex4.line(c3.start.x(), c3.start.y(), c3.end.x(), c3.end.y());
            }
        }
        if (gs instanceof Rect_2D) {
            Rect_2D c3 = (Rect_2D) gs;
            double[] y = {c3._min.y(), c3.bottomRight.y(), c3._max.y(), c3.topLeft.y()};
            double[] x = {c3._min.x(), c3.bottomRight.x(), c3._max.x(), c3.topLeft.x()};
            if (isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            } else {
                StdDraw_Ex4.polygon(x, y);
            }
        }

        if (gs instanceof Triangle_2D) {
            Triangle_2D c3 = (Triangle_2D) gs;
            double[] y = {c3.ver1.y(), c3.ver2.y(), c3.ver3.y()};
            double[] x = {c3.ver1.x(), c3.ver2.x(), c3.ver3.x()};
            if (isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            } else {
                StdDraw_Ex4.polygon(x, y);
            }
        }

        if (gs instanceof Polygon_2D) {
            Polygon_2D c3 = (Polygon_2D) gs;
            double[][] a = convertPolygonToArray(c3.verts);
            double[] y = convert2DArrToY(a);
            double[] x = convert2DArrToX(a);
            if (isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            } else {
                StdDraw_Ex4.polygon(x, y);
            }
        }
    }



    public void init(GUI_Shape_Collection s) {
        if (s == null) {
            _shapes = new ShapeCollection();
        } else {
            _shapes = s.copy();
        }
        _gs = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape sh = _shapes.get(i);

            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }

    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }

    public void actionPerformed(String p) { //* Performs the corresponding actions based on the given parameter.
        _mode = p;
        if (p.equals("Blue")) {
            _color = Color.BLUE;
            setColor(_color);
        }
        if (p.equals("Red")) {
            _color = Color.RED;
            setColor(_color);
        }
        if (p.equals("Green")) {
            _color = Color.GREEN;
            setColor(_color);
        }
        if (p.equals("White")) {
            _color = Color.WHITE;
            setColor(_color);
        }
        if (p.equals("Black")) {
            _color = Color.BLACK;
            setColor(_color);
        }
        if (p.equals("Yellow")) {
            _color = Color.YELLOW;
            setColor(_color);
        }
        if (p.equals("Fill")) {
            _fill = true;
            setFill();
        }
        if (p.equals("Empty")) {
            _fill = false;
            setFill();
        }
        if (p.equals("Clear")) {
            _shapes.removeAll();
            _tag = 0;
        }
        if (p.equals("Load")) {
            load();
        }
        if (p.equals("Save")) {
            save();
        }
        if (_mode.equals("Info")) {
            System.out.println(getInfo());
        }
        if (_mode.equals("All")) {
            selectAll();
        }
        if (_mode.equals("Anti")) {
            selectAnti();
        }
        if (_mode.equals("None")) {
            selectNone();
        }
        if (_mode.equals("Remove")) {
            Remove();
        }
        if (_mode.equals("ByArea")) {
            Comparatori areaComparator = new Comparatori();
            _shapes.sort(areaComparator);
        }
        if(_mode.equals("ByAntiArea")) {
            Comparatori.AntiArea antiArea = new Comparatori.AntiArea();
            _shapes.sort(antiArea);
        }
        if(_mode.equals("ByPerimeter")) {
            Comparatori.Perimeter perimeter = new Comparatori.Perimeter();
            _shapes.sort(perimeter);
        }
        if(_mode.equals("ByAntiPerimeter")) {
            Comparatori.AntiPerimeter antiPerimeter = new Comparatori.AntiPerimeter();
            _shapes.sort(antiPerimeter);
        }
        if(_mode.equals("ByToString")) {
            Comparatori.ToString s = new Comparatori.ToString();
            _shapes.sort(s);
        }
        if(_mode.equals("ByAntiToString")) {
            Comparatori.antiToString s = new Comparatori.antiToString();
            _shapes.sort(s);
        }
        if(_mode.equals("ByTag")) {
            Comparatori.tag tag = new Comparatori.tag();
            _shapes.sort(tag);
        }
        if(_mode.equals("ByAntiTag")) {
            Comparatori.Antitag tag1 = new Comparatori.Antitag();
            _shapes.sort(tag1);
        }
        drawShapes();
        _gs = null;
    }

    public void mouseClicked(Point_2D p) {
        System.out.println("Mode: " + _mode + "  " + p);
        if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point_2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point_2D(p); // Store the first point clicked as one corner of the rectangle
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                System.out.println(_fill + " " + _color);
                _shapes.add(_gs); // Add the rectangle to the collection
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Polygon") || _mode.equals("Triangle")) {

            if (_polygon == null) {
                _polygon = new Polygon_2D();
                _polygon.add(p);
                _p1 = new Point_2D(p);
            }
            _polygon.add(p);
            if (_mode.equals("Triangle") && _polygon.verts.size() == 4) {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _polygon = null;
                _gs = null;
                _p1 = null;
                drawShapes();
            }
        }

        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point_2D(p);
            } else {
                _p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }

        if (_mode.equals("Point")) {
            select(p);
        }

        if(_mode.equals("Copy")) {
            // Activate copy function, in the second click calculate the vector between p1 and the second click
             if(_p1==null) {
                _p1=new Point_2D(p);
                }
                else {
                  this._p1=new Point_2D(p.x()-this._p1.x(),p.y()-this._p1.y());
                  this.copy();
                  _p1=null;
                }
            }
        if(_mode.equals("Scale_90%")) { // change the scale of the point from center point with ration 0.9

                _p1=p;
                Scale(0.9);

            }

            if(_mode.equals("Scale_110%")) {
                // change the scale of the point from center point  with ratio of 1.1

                _p1=p;
                Scale(1.1);


            }

            if(_mode.equals("Rotate")) {
                // this function will activate rotate function on selected shape
                // if this is the first click enter it to p1
                if(_p1==null) {
                    _p1=new Point_2D(p);
                }
                else {

                    this.Rotate(p);
                    this._p1=null;
                }
            }
        if (_mode.equals("Remove")) {
        }
        drawShapes();
    }

    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShape gs = null;
            Point_2D p = new Point_2D(x1, y1);

            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle_2D(_p1, r);
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            drawShapes();

            if (_mode.equals("Segment")) {
                gs = new Segment_2D(_p1, p);
                _gs = new GUIShape(gs, false, Color.pink, 0);
                drawShapes();
            }
            if (_mode.equals("Rect")) {
                gs = new Rect_2D(_p1, p);
                _gs = new GUIShape(gs, false, Color.pink, 0); // Create a temporary GUIShape object
                drawShapes();
            }


            if (_mode.equals("Polygon") || _mode.equals("Triangle")) {
                if (_polygon != null) {      // create a polygon and add each click
                    int a = _polygon.verts.size();
                    _polygon.verts.remove(a - 1); // Remove the last vertex from the temporary polygon
                    _polygon.add(p); // Add the current mouse position as the last vertex of the temporary polygon
                    gs = new Polygon_2D(_polygon);
                    _gs = new GUIShape(gs, false, Color.pink, 0);
                    drawShapes();
                }
            }

        }
    }

    private void select(Point_2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    private void selectAll() {  // this function select all the shapes
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(true);
            }
        }
    }

    private void selectAnti() {  // this function select all the un selected shapes
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(!s.isSelected());
            }
        }
    }

    private void selectNone() { // this function unselected all the shapes
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (g != null) {
                s.setSelected(false);
            }
        }
    }

    public void mouseRightClicked(Point_2D p) {
        System.out.println("right click!");
        System.out.println(_fill + " " + _color);
        _gs.setColor(_color);
        _gs.setFilled(_fill);
        _shapes.add(_gs);
        _polygon = null;
        _gs = null;
        _p1 = null;
        drawShapes();
    }

    @Override
    public GUI_Shape_Collection getShape_Collection() {
        // TODO Auto-generated method stub
        return this._shapes;
    }

    @Override
    public void show() {
        show(Ex4_Const.DIM_SIZE);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }

    private void save() {
        FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Save to Text file", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.save(chooser.getDirectory() + File.separator + chooser.getFile()+ ".txt");
        }
    }

    private void load() {
        FileDialog chooser = new FileDialog(StdDraw_Ex4.getFrame(), "Load from Text file", FileDialog.LOAD);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            _shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            if (s.isSelected() && g != null) {
                g.translate(_p1);
            }
        }
    }

    private void Remove() {
        int i = 0;

        while (i < _shapes.size()) {
            GUI_Shape s = _shapes.get(i);
            GeoShape g = s.getShape();
            i++;
            // if the shape is selected remove it from the collection and set the tag to be minus 1 for each shpae we remove
            if (s.isSelected() && g != null) {
                _tag--;
                _shapes.removeElementAt(i - 1);
                i = 0;

            }
        }
    }
            private void Rotate(Point_2D n) {
                // calculate the degree between the first click to the second
            double d=Math.toDegrees(Math.atan2(n.y()-this._p1.y(), n.x()-_p1.x()));
            for(int i=0;i<_shapes.size();i++) {
                GUI_Shape s = _shapes.get(i);
                GeoShape g = s.getShape();
                if(s.isSelected() && g!=null) {
               // rotate the shape with the center point p1 (first click) and the angle degree that calculated
                    g.rotate(_p1, d);
                }
            }

        }
            private void Scale(double ratio) {
            for(int i=0;i<_shapes.size();i++) {
                GUI_Shape s = _shapes.get(i);
                GeoShape g = s.getShape();
                // if the shape selected change it ration with the center point p1
                if(s.isSelected() && g!=null) {
                    g.scale(_p1,ratio);
                }
            }
            _p1=null;
        }
        private void copy() {

            for(int i=0;i<_shapes.size();i++) {
                GUI_Shape s = _shapes.get(i);
                if(s.isSelected()) {
                    GUI_Shape temp=s.copy();
                    temp.getShape().translate(this._p1);
                    temp.setTag(_tag);
                    _tag++;
                    _shapes.add(temp);

                }

            }

    }

    public static class Comparatori implements Comparator<GUI_Shape> {
        @Override
        public int compare(GUI_Shape shape1, GUI_Shape shape2) {
            double area1 = shape1.getShape().area(); // Calculate the area of shape1
            double area2 = shape2.getShape().area(); // Calculate the area of shape2

            // Compare the areas and return the appropriate integer value
            if (area1 < area2) {
                return -1; // shape1 comes before shape2
            } else if (area1 > area2) {
                return 1; // shape1 comes after shape2
            } else {
                return 0; // shape1 and shape2 have the same area, no order change
            }
        }
        public static class AntiArea implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                double area1 = shape1.getShape().area(); // Calculate the area of shape1
                double area2 = shape2.getShape().area(); // Calculate the area of shape2

                // Compare the areas and return the appropriate integer value
                if (area1 > area2) {
                    return -1; // shape2 comes before shape1
                } else if (area1 < area2) {
                    return 1; // shape2 comes after shape1
                } else {
                    return 0; // shape1 and shape2 have the same area, no order change
                }
            }
        }
        public static class Perimeter implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                double perimeter1 = shape1.getShape().perimeter();
                double perimeter2 = shape2.getShape().perimeter();
                if (perimeter1 < perimeter2) {
                    return -1;
                } else if (perimeter1 > perimeter2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        public static class AntiPerimeter implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                double perimeter1 = shape1.getShape().perimeter();
                double perimeter2 = shape2.getShape().perimeter();
                if (perimeter1 > perimeter2) {
                    return -1;
                } else if (perimeter1 < perimeter2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        public static class tag implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                double tag1 = shape1.getTag();
                double tag2 = shape2.getTag();
                if (tag1 < tag2) {
                    return -1;
                } else if (tag1 > tag2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        public static class Antitag implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                double tag1 = shape1.getTag();
                double tag2 = shape2.getTag();
                if (tag1 > tag2) {
                    return -1;
                } else if (tag1 < tag2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        public static class ToString implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                String string1 = shape1.getShape().toString();
                String string2 = shape2.getShape().toString();
                return -string1.compareTo(string2);
            }
        }

        public static class antiToString implements Comparator<GUI_Shape> {
            public int compare(GUI_Shape shape1, GUI_Shape shape2) {
                String string1 = shape1.getShape().toString();
                String string2 = shape2.getShape().toString();

                return string1.compareTo(string2);
            }
        }

    }
    public static double[][] convertPolygonToArray(ArrayList<Point_2D> polygon) {
        int size = polygon.size();
        double[] xCoordinates = new double[size];
        double[] yCoordinates = new double[size];

        for (int i = 0; i < size; i++) {
            Point_2D vertex = polygon.get(i);
            xCoordinates[i] = vertex.x();
            yCoordinates[i] = vertex.y();
        }

        return new double[][]{xCoordinates, yCoordinates};
    }

    public static double[] convert2DArrToX(double[][] coordinates) {
        double[] xCoordinates = coordinates[0];
        return xCoordinates;
    }

    public static double[] convert2DArrToY(double[][] coordinates) {
        double[] yCoordinates = coordinates[1];
        return yCoordinates;
    }
}
