package exe.ex4.geo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Triangle2DTest {
    public static final double EPS = 0.00001;
    private final Point_2D ver1 = new Point_2D(0,0);
    private final Point_2D ver2 = new Point_2D(4,0);
    private final Point_2D ver3 = new Point_2D(2,2);

    @Test
    public void Triangle_2DTest1 (){ //Test that the constructor constructs the vertices and the sides correctly with given 3 points
      Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3) ;
      ver1.close2equals(t1.ver1,EPS);
      ver2.close2equals(t1.ver2,EPS);
      ver3.close2equals(t1.ver3,EPS);
      assertEquals(t1.side1,4,EPS);
      assertEquals(t1.side2,Math.sqrt(8),EPS);
      assertEquals(t1.side3,Math.sqrt(8),EPS);

    }
    @Test
    public void Triangle_2DTest2 (){ //Test that the constructor constructs the vertices and the sides correctly with given triangle
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3) ;
        Triangle_2D t2 = new Triangle_2D(t1) ;
        t1.ver1.close2equals(t2.ver1,EPS);
        t1.ver2.close2equals(t2.ver2,EPS);
        t1.ver3.close2equals(t1.ver3,EPS);
        assertEquals(t1.side1,t2.side1,EPS);
        assertEquals(t1.side2,t2.side2,EPS);
        assertEquals(t1.side3,t2.side3,EPS);

    }

    @Test
    public void  toStringTest1 () {//Tests that the received string is correct
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3) ;
        String s = t1.toString();
        String expected = "Triangle_2D,0.0,0.0,4.0,0.0,2.0,2.0";
        assertEquals(expected, s);

    }

    @Test
    public void getAllPoints1() { //Test basic case og getting point and verifies his correctness
        Triangle_2D t1 = new Triangle_2D(ver1, ver2, ver3);
        Point_2D[] allPoints = t1.getAllPoints();
        Point_2D[] expexted = {ver1, ver2, ver3};
        for (int i = 0; i < 3; i++) {
            boolean b = false;
            if (allPoints[i] == expexted[i]) b = true;
            assertTrue(b);
        }
    }

    @Test
     public void contains1 (){ //Test the case when the given point is outside and false is returned
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D out = new Point_2D(10,10);
       assertFalse(t1.contains(out));
    }

    @Test
    public void contains2 (){ //Test the case when the given point is inside and true is returned
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D in = new Point_2D(2,1);
        assertTrue(t1.contains(in));
    }

    @Test
    public void area1 (){ //Test a basic case of computing triangle area
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        double a = t1.area();
        assertEquals(a,4,EPS);
    }

    @Test
    public void area2 (){ //Test a case of computing triangle area with negative points coordinates
        Point_2D p1 = new Point_2D(-2,0);
        Point_2D p2 = new Point_2D(-4,0);
        Point_2D p3 = new Point_2D(-2,-2);
        Triangle_2D t1 = new Triangle_2D(p1,p2,p3);
        double a = t1.area();
        assertEquals(a,2,EPS);
    }

    @Test
    public void perimeter1 (){ //Test a basic case of computing triangle perimeter
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        double a = t1.perimeter();
        assertEquals(a,9.656854,EPS);
    }

    @Test
    public void perimeter2 (){ //Test a case of computing triangle in the second quadrant perimeter
        Point_2D p1 = new Point_2D(-2,0);
        Point_2D p2 = new Point_2D(-4,0);
        Point_2D p3 = new Point_2D(-2,-2);
        Triangle_2D t1 = new Triangle_2D(p1,p2,p3);
        double a = t1.perimeter();
        assertEquals(a,6.828427,EPS);
    }

    @Test
    public void translate1 (){ //Test that all vertices translates properly
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D vec = new Point_2D(1,1);
        t1.translate(vec);
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(5,1);
        Point_2D p3 = new Point_2D(3,3);
        assertTrue(t1.ver1.close2equals(p1,EPS));
        assertTrue( t1.ver2.close2equals(p2,EPS));
        assertTrue(t1.ver3.close2equals(p3,EPS));
    }

    @Test
    public void copy1 (){ //Test that the copy is made correctly
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Triangle_2D t2 =  (Triangle_2D)(t1.copy());
        assertTrue(t1.ver1.close2equals(t2.ver1,EPS));
        assertTrue( t1.ver2.close2equals(t2.ver2,EPS));
        assertTrue(t1.ver3.close2equals(t2.ver3,EPS));
    }

    @Test
    public void scale1 (){ //Test that the scale is made correctly
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D center = new Point_2D(5,5);
        t1.scale(center,2);
        Point_2D p1 = new Point_2D(-5,-5);
        Point_2D p2 = new Point_2D(3,-5);
        Point_2D p3 = new Point_2D(-1,-1);
        assertTrue(t1.ver1.close2equals(p1,EPS));
        assertTrue( t1.ver2.close2equals(p2,EPS));
        assertTrue(t1.ver3.close2equals(p3,EPS));
    }
    @Test
    public void scale2 (){ //Test that scale with ratio 1 will not change the shape
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D center = new Point_2D(5,5);
        t1.scale(center,1);
        assertTrue(t1.ver1.close2equals(ver1,EPS));
        assertTrue( t1.ver2.close2equals(ver2,EPS));
        assertTrue(t1.ver3.close2equals(ver3,EPS));
    }

    @Test
    public void rotate1 (){ //Test that the scale is made correctly in a basic case
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D center = new Point_2D(5,5);
        t1.rotate(center,45);
        Point_2D p1 = new Point_2D(5,-2.0710678);
        Point_2D p2 = new Point_2D(7.828427,0.757359);
        Point_2D p3 = new Point_2D(5,0.757359);
        assertTrue(t1.ver1.close2equals(p1,EPS));
        assertTrue( t1.ver2.close2equals(p2,EPS));
        assertTrue(t1.ver3.close2equals(p3,EPS));
    }

    @Test
    public void rotate2 (){ //Test that the scale is made correctly
        Triangle_2D t1 = new Triangle_2D(ver1,ver2,ver3);
        Point_2D center = new Point_2D(0,0);
        t1.rotate(center,360);
        assertTrue(t1.ver1.close2equals(ver1,EPS));
        assertTrue( t1.ver2.close2equals(ver2,EPS));
        assertTrue(t1.ver3.close2equals(ver3,EPS));
    }


















}
