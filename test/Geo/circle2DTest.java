package exe.ex4.geo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class circle2DTest { // this Junit test Circle_2d class

    public static final double EPS = 0.00001;

    @Test
    void constructorTest1() { // test that the constructor construct correctly radius and center.
        Point_2D center = new Point_2D(1, 2);
        double radius = 3.5;
        Circle_2D circle = new Circle_2D(center, radius);
      assertTrue(center.close2equals(circle._center,EPS));
        assertEquals(radius, circle._radius, EPS);
    }

    @Test
    void getRadiusTest1() { // test the getter
        Point_2D center = new Point_2D(1, 2);
        double radius = 3.5;
        Circle_2D circle = new Circle_2D(center, radius);
        assertEquals(radius, circle.getRadius(), EPS);
    }

    @Test
    void getCenterTest1() {// test the getter
        Point_2D center = new Point_2D(1, 2);
        double radius = 3.5;
        Circle_2D circle = new Circle_2D(center, radius);
        assertTrue(center.close2equals(circle.getCenter(),EPS));
    }

    @Test
    public void  toStringTest1 () {//Tests that the received string is correct
        Point_2D center = new Point_2D(2,2);
    Circle_2D cir = new Circle_2D(center, 4);
        String s = cir.toString();
        String expected = "Circle_2D,2.0,2.0,4.0";
        assertEquals(expected, s);

    }

    @Test
    public void  containsTest1 () { // test that when the point is not in the circle the function returns false
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
       Point_2D ot = new Point_2D(10,10);
       assertFalse(cir.contains(ot));

    }

    @Test
    public void  containsTest2 () { // test that when the point is in the circle the function returns true
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
        Point_2D ot = new Point_2D(2,3);
        assertTrue(cir.contains(ot));

    }

    @Test
    public void  areaTest1 () { // test that computed area is correct
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
        double expected = Math.PI*16;
        double ans = cir.area();
        assertEquals(expected,ans,EPS);

    }

    @Test
    public void  perimeterTest1 () { // test that computed perimeter is correct
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
        double expected = 2*Math.PI*4;
        double ans = cir.perimeter();
        assertEquals(expected,ans,EPS);

    }

    @Test
    public void  translateTest1 () { // test that circle is moving correctly
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
        Point_2D vec = new Point_2D(1,1);
       cir.translate(vec);
        Point_2D center2 = new Point_2D(4,4);
        Circle_2D cir2 = new Circle_2D(center, 4);

       assertTrue(center2.y() == cir.getCenter().y());
       assertTrue(center2.x() == cir.getCenter().x());
       assertTrue(cir.getRadius()==4);
    }

    @Test
    public void  copyTest1 () { // test that the copy created correctly
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);

        Circle_2D cir2 = (Circle_2D) cir.copy();

        assertTrue(cir2.getCenter().y() == cir.getCenter().y());
        assertTrue(cir2.getCenter().x() == cir.getCenter().x());
        assertTrue(cir.getRadius()==cir2.getRadius());
    }
    @Test
    public void  scaleTest1 () { // test that the scale is made correctly
        Point_2D center = new Point_2D(3,3);
        Circle_2D cir = new Circle_2D(center, 4);
        Point_2D center2 = new Point_2D(5,5);
        Point_2D centernew = new Point_2D(2,2);
        Circle_2D cir2 = new Circle_2D(centernew,6);
        cir.scale(center2,1.5);
        assertTrue(cir2.getCenter().y() == cir.getCenter().y());
        assertTrue(cir2.getCenter().x() == cir.getCenter().x());
        assertTrue(cir.getRadius()==cir2.getRadius());
    }

    @Test
    public void  scaleTest2 () { // test that the scale is made correctly when getting equals values
        Point_2D center = new Point_2D(5,5);
        Circle_2D cir = new Circle_2D(center, 4);
        Point_2D center2 = new Point_2D(5,5);
        Circle_2D cir2 = new Circle_2D(center2,4);
        cir.scale(center2,1);
        assertTrue(cir2.getCenter().y() == cir.getCenter().y());
        assertTrue(cir2.getCenter().x() == cir.getCenter().x());
        assertTrue(cir.getRadius()==cir2.getRadius());
    }

        @Test
        void rotateTest1() { // test a basic case of using the rotate function
            Circle_2D circle = new Circle_2D(new Point_2D(3, 4), 5);
            Point_2D rotationCenter = new Point_2D(1, 2);
            double angleDegrees = 45;
            circle.rotate(rotationCenter, angleDegrees);

            Point_2D expectedCenter = new Point_2D(1.000000, 4.828427); // Rounded for simplicity
            double expectedRadius = 5;
            assertEquals(expectedCenter.x(), circle.getCenter().x(), EPS);
            assertEquals(expectedCenter.y(), circle.getCenter().y(), EPS);
            assertEquals(expectedRadius, circle.getRadius(),EPS);

        }

}




