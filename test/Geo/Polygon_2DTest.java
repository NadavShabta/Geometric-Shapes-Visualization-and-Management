package exe.ex4.geo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class Polygon_2DTest {
    private final double EPS = 0.0001;
    private final ArrayList<Point_2D> points = new ArrayList<>(Arrays.asList(
            new Point_2D(0, 0),
            new Point_2D(1, 0),
            new Point_2D(1, 1),
            new Point_2D(0, 1)
    ));
    private final Point_2D a =  new Point_2D(0, 0);
    private final Point_2D b =  new Point_2D(1, 0);
    private final Point_2D c = new Point_2D(1, 1);
    private final Point_2D d = new Point_2D(0, 1);

    @Test
    public void Polygon_2DTest1() { //Test for equality between the constructor and the source

        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
    assertTrue(compareArrayLists(points,polygon.verts,EPS));
    }

    @Test
    public void Polygon_2DTest2() {//Test for equality between the constructor and the source
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Polygon_2D polygon1 = new Polygon_2D(polygon);
        assertTrue(compareArrayLists(polygon1.verts,polygon.verts,EPS));
    }

    @Test
    public void getAllPointsTest1() {//Compares the array to the list and checks for equality

        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Point_2D[] pointArray = polygon.getAllPoints();
        assertEquals(pointArray.length, points.size());
        boolean b ;
        for (int i = 0; i < points.size(); i++) {
                if (points.get(i).close2equals(pointArray[i], EPS)){
                    b=true;}
                else{ b = false;
            }
                assertTrue(b);
        }
    }


    @Test
    public void addTest1() { //Test that the addition was done correctly and compares to the test
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        assertEquals(4, polygon.getAllPoints().length);

        Polygon_2D poly1 = new Polygon_2D();
        poly1.add(a);
        poly1.add(b);
        poly1.add(c);
        poly1.add(d);
        compareArrayLists(points,polygon.verts,EPS);
    }

    @Test
    public void  toStringTest1 () { //Tests that the received string is correct
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        String s = polygon.toString();
        String expected =  "Polygon_2D,0.0,0.0,1.0,0.0,1.0,1.0,0.0,1.0";
        assertEquals(expected, s);

    }

    @Test
    public void containsTest1() { // Test that when the given point is inside true will return
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Point_2D insidePoint = new Point_2D(0.5, 0.5);
        assertTrue(polygon.contains(insidePoint));

    }

    @Test
    public void containsTest2() {// Test that when the given point is outside false will return
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Point_2D outsidePoint = new Point_2D(2, 2);
        assertFalse(polygon.contains(outsidePoint));

    }


        @Test
        public void areaTest1() { //Test that the area computed correctly
            Polygon_2D polygon = new Polygon_2D();
            polygon.add(new Point_2D(0, 0));
            polygon.add(new Point_2D(2, 0));
            polygon.add(new Point_2D(2, 2));
            polygon.add(new Point_2D(1, 3));
            polygon.add(new Point_2D(0, 2));

            double expectedArea = 5;

            assertEquals(expectedArea, polygon.area(), EPS);

        }

    @Test
    public void areaTest2() { //Test that the area computed correctly
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(0, 0));
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 0));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(1, 2));
        polygon.add(new Point_2D(1, 3));
        polygon.add(new Point_2D(0, 2));


        double expectedArea = 3.5;

        assertEquals(expectedArea, polygon.area(), EPS);

    }


    @Test
    public void testPerimeter() {//Test that the perimeter computed correctly
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(0, 0));
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 0));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(1, 2));
        polygon.add(new Point_2D(1, 3));
        polygon.add(new Point_2D(0, 2));

        double perimeter = polygon.perimeter();
        double expectedPerimeter = 6+ Math.sqrt(2) + Math.sqrt(2) + Math.sqrt(2) ;


        assertEquals(expectedPerimeter, perimeter, EPS);
    }
    @Test
    public void translateTest1(){ //// Test that the displacement of the polygon is done correctly by the given vector
        Polygon_2D polygonorg = new Polygon_2D();
        polygonorg.add(a);
        polygonorg.add(b);
        polygonorg.add(c);
        polygonorg.add(d);
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(new Point_2D(1, 1));
        polygon.add(new Point_2D(2, 1));
        polygon.add(new Point_2D(2, 2));
        polygon.add(new Point_2D(1, 2));
        Point_2D vec = new Point_2D(1,1);
        polygonorg.translate(vec);
        assertTrue(compareArrayLists(polygonorg.verts,polygon.verts,EPS));


    }

    @Test
    public void copyTest1(){// test that the copy was made correctly
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Polygon_2D polygon2 = (Polygon_2D) polygon.copy();
        assertTrue(compareArrayLists(polygon.verts,polygon2.verts,EPS));

    }

    @Test
    public void scaleTest1(){ // Test that the scale is made correctly for all vertices
        Polygon_2D polygon = new Polygon_2D();
        polygon.add(a);
        polygon.add(b);
        polygon.add(c);
        polygon.add(d);
        Point_2D center = new Point_2D(3,3);
        polygon.scale(center,2);
        Polygon_2D polygon1 = new Polygon_2D();
        polygon1.add(new Point_2D(-3, -3));
        polygon1.add(new Point_2D(-1, -3));
        polygon1.add(new Point_2D(-1, -1));
        polygon1.add(new Point_2D(-3, -1));
        assertTrue(compareArrayLists(polygon.verts,polygon1.verts,EPS));

    }
@Test
    public void rotateTest1(){ // Test that rotate is made correctly for all vertices
        Polygon_2D polygon = new Polygon_2D();
    polygon.add(a);
    polygon.add(b);
    polygon.add(c);
    polygon.add(d);
        Point_2D center = new Point_2D(3,3);
        polygon.rotate(center,45);
        Polygon_2D polygon1 = new Polygon_2D();
        polygon1.add(new Point_2D(3, -1.2426406));
        polygon1.add(new Point_2D(3.70710678, -0.5355339));
        polygon1.add(new Point_2D(3, 0.17157287));
        polygon1.add(new Point_2D(2.2928932, -0.535533905));
        assertTrue(compareArrayLists(polygon.verts,polygon1.verts,EPS));

    }

    public boolean compareArrayLists(ArrayList<Point_2D> list1, ArrayList<Point_2D> list2, double eps) { // Helper function
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            Point_2D point1 = list1.get(i);
            Point_2D point2 = list2.get(i);
            if (!point1.close2equals(point2, eps)) {
                return false;
            }
        }
        return true;
    }
}

