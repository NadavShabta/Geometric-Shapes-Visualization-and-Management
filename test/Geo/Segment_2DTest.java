package exe.ex4.geo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
public class Segment_2DTest {
    private static final double EPS = 0.00001;
    private final Point_2D a = new Point_2D(0,0);
    private final Point_2D b = new Point_2D(5,0);
    private final Point_2D c = new Point_2D(2,2);


    @Test
    public void Segment_2DTest1(){ // test that construct correctly the segment from a given 2 points

       Segment_2D seg = new Segment_2D(a,b);
       assertTrue(seg.start.close2equals(a,EPS));
       assertTrue(seg.end.close2equals(b,EPS));

    }

    @Test
    public void Segment_2DTest2(){// test that construct correctly the segment from a given segment

        Segment_2D seg = new Segment_2D(a,b);
        Segment_2D seg1 = new Segment_2D(seg);
        assertTrue(seg1.start.close2equals(a,EPS));
        assertTrue(seg1.end.close2equals(b,EPS));

    }
    @Test
    public void  toStringTest1 () {//Tests that the received string is correct
        Segment_2D seg = new Segment_2D(a,b);
        String s = seg.toString();
        String expected = "Segment_2D,0.0,0.0,5.0,0.0";
        assertEquals(expected, s);

    }

    @Test
    public void get_p1Test1(){// test that getting the correct point

        Segment_2D seg = new Segment_2D(a,b);
        assertTrue(seg.get_p1().close2equals(a,EPS));

    }

    @Test
    public void get_p2Test2(){// test that getting the correct point

        Segment_2D seg = new Segment_2D(a,b);
        assertTrue(seg.get_p2().close2equals(b,EPS));

    }

    @Test
    public void containsTest1(){// test that when the given point is inside true will return
    Point_2D ot = new Point_2D(2,0);

        Segment_2D seg = new Segment_2D(a,b);
        assertTrue(seg.contains(ot));

    }

    @Test
    public void containsTest2(){// test that when the given point is outside false will return

        Point_2D ot = new Point_2D(3,3);
        Segment_2D seg = new Segment_2D(a,b);
        assertFalse(seg.contains(ot));

    }

    @Test
    public void areaTest1(){// test that return 0
        Segment_2D seg = new Segment_2D(a,b);
        assertTrue(seg.area() == 0);

    }

    @Test
    public void perimeterTest1(){// test that a correct perimeter is returned
        Segment_2D seg = new Segment_2D(a,b);
        assertTrue(seg.perimeter() == 10);

    }

    @Test
    public void translateTest1(){ //Test that all vertices translates properly
        Segment_2D seg = new Segment_2D(a,b);
        Point_2D vec = new Point_2D(1,1);
        seg.translate(vec);
        Point_2D anew = new Point_2D(1,1);
        Point_2D bnew = new Point_2D(6,1);
        assertTrue(seg.start.close2equals(anew,EPS));
        assertTrue(seg.end.close2equals(bnew,EPS));

    }

    @Test
    public void translateTest2(){ //Test that there is no change when the vec is 0,0
        Segment_2D seg = new Segment_2D(a,b);
        Point_2D vec = new Point_2D(0,0);
        seg.translate(vec);
        assertTrue(seg.start.close2equals(a,EPS));
        assertTrue(seg.end.close2equals(b,EPS));

    }

    @Test
    public void copyTest1(){ //Test that the copy is made correctly
        Segment_2D seg = new Segment_2D(a,b);
        Segment_2D seg2 = (Segment_2D) seg.copy();
        assertTrue(seg2.start.close2equals(a,EPS));
        assertTrue(seg2.end.close2equals(b,EPS));

    }

    @Test
    public void scaleTest1(){ // Test that the scale is made correctly for all point
        Segment_2D seg = new Segment_2D(a,b);
        Point_2D center = new Point_2D(6,6);
        seg.scale(center,2);
        Point_2D p3 = new Point_2D(-6,-6);
        Point_2D p4 = new Point_2D(4,-6);
        assertTrue(seg.start.close2equals(p3,EPS));
        assertTrue(seg.end.close2equals(p4,EPS));
    }
    @Test
    public void scaleTest2(){ // Test that the points do not move
        Segment_2D seg = new Segment_2D(a,b);
        Point_2D center = new Point_2D(3,0);
        seg.scale(center,1);
        assertTrue(seg.start.close2equals(a,EPS));
        assertTrue(seg.end.close2equals(b,EPS));
    }

    @Test
    public void rotateTest1(){ // Test that rotate is made correctly for all points
        Segment_2D seg = new Segment_2D(a,b);
        Point_2D center = new Point_2D(6,6);
        seg.rotate(center,45);
        Point_2D p3 = new Point_2D(6,-2.485281);
        Point_2D p4 = new Point_2D(9.535533,1.050252);
        assertTrue(seg.start.close2equals(p3,EPS));
        assertTrue(seg.end.close2equals(p4,EPS));
    }













}
