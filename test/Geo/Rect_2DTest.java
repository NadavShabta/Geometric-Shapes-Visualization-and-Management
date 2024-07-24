package exe.ex4.geo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
public class Rect_2DTest {

private Point_2D p1 = new Point_2D(5,5);
private Point_2D p2 = new Point_2D(2,2);
public static final double EPS = 0.00001;


    @Test
    public void Rect_2DTest1(){ // Test that the constructor create the right vertices with two given Points_2D
    Rect_2D rec = new Rect_2D(p1,p2);
    Point_2D max = rec.getMaxPoint();
    Point_2D min = rec.getMinPoint();
    Point_2D min1 = new Point_2D(Math.min(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
    Point_2D max1 = new Point_2D(Math.max(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
    Assert.assertEquals(min.x(),min1.x(),EPS);
    Assert.assertEquals(max.x(),max1.x(),EPS);
}

    @Test
    public void Rect_2DTest2(){ // Test that the constructor create the right vertices with a given Rect_2d
        Rect_2D rec = new Rect_2D(p1,p2);
        Rect_2D rec1 = new Rect_2D(rec);

        Assert.assertEquals(rec1.getMaxPoint().y(),rec.getMaxPoint().y(),EPS);
        Assert.assertEquals(rec1.getMaxPoint().x(),rec.getMaxPoint().x(),EPS);
        Assert.assertEquals(rec1.getMinPoint().y(),rec.getMinPoint().y(),EPS);
        Assert.assertEquals(rec1.getMinPoint().x(),rec.getMinPoint().y(),EPS);
    }
    @Test
    public void  toStringTest1 () { //Tests that the received string is correct
        Rect_2D rec = new Rect_2D(p1, p2);
        String s = rec.toString();
        String expected = "Rect_2D,5.0,5.0,2.0,2.0";
        assertEquals(expected, s);

    }

    @Test
    public void containsTest1(){ // Test that the contain function return true when receives an inside point
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D point = new Point_2D(4,5);
        assertTrue(rec.contains(point));
    }

    @Test
    public void containsTest2(){ // Test that the contain function return false when receives an outside point
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D point = new Point_2D(6,5);
        assertFalse(rec.contains(point));
    }

    @Test
    public void areaTest1(){ // Test that the area computed correctly
        Rect_2D rec = new Rect_2D(p1,p2);
        double expected = 9.0;
        assertEquals(rec.area(),expected,EPS);
    }

    @Test
    public void perimeterTest1(){ // Test that the perimeter computed correctly
        Rect_2D rec = new Rect_2D(p1,p2);
        double expected = 12.0;
        assertEquals(rec.perimeter(),expected,EPS);
    }

    @Test
    public void translateTest1(){ // Test that the displacement of the rectangle is done correctly by the given vector
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D vec = new Point_2D(1,1);
        rec.translate(vec);
        Point_2D p3 = new Point_2D(3,3);
        Point_2D p4 = new Point_2D(6,6);
        Rect_2D rec1 = new Rect_2D(p3,p4);
        assertTrue(equals(rec,rec1));
    }

    @Test
    public void translateTest2(){ // Test the case when the vector is the zero vector (0,0) and no displacement needs to be done
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D vec = new Point_2D(0,0);
        rec.translate(vec);
        assertTrue(equals(rec,rec));
    }

    @Test
    public void copyTest1(){ // Test that the copy of the rectangle is made correctly
        Rect_2D rec = new Rect_2D(p1,p2);
        assertTrue(equals(rec, (Rect_2D) rec.copy()));
    }

    @Test
    public void scaleTest1(){ // Test that the scale is made correctly for all vertices
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D center = new Point_2D(6,6);
       rec.scale(center,2);
       Point_2D p3 = new Point_2D(-2,-2);
       Point_2D p4 = new Point_2D(4,4);
       Rect_2D expected = new Rect_2D(p3,p4);
        assertTrue(equals(rec,expected));
    }

    @Test
    public void rotateTest1(){ // Test that rotate is made correctly for all vertices
        Rect_2D rec = new Rect_2D(p1,p2);
        Point_2D center = new Point_2D(6,6);
        rec.rotate(center,45);
        Point_2D p3 = new Point_2D(6,0.3431457);
        Point_2D p4 = new Point_2D(6,4.5857864);
        Rect_2D expected = new Rect_2D(p3,p4);
        assertTrue(equals(rec,expected));
    }
    public boolean equals(Rect_2D rec1 , Rect_2D rec2) { // Helper function
        Point_2D[] rec1Vertices = vertices(rec1);
        Point_2D[] rec2Vertices = vertices(rec2);
        for (int i = 0; i < 4; i++) {
            if (!rec1Vertices[i].close2equals(rec2Vertices[i],EPS)) {
                return false;
            }
        }
        return true;
    }

    private Point_2D[] vertices(Rect_2D rec){ // Helper function
        Point_2D bottomRight = new Point_2D(Math.max(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
        Point_2D topLeft = new Point_2D(Math.min(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
        Point_2D bottomLeft = new Point_2D(Math.min(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
        Point_2D topRight = new Point_2D(Math.max(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
        Point_2D[] vertices = { bottomRight, topLeft, bottomLeft, topRight };
        return vertices;
    }

}
