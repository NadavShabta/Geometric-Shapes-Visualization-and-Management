package exe.ex4.gui;
import exe.ex4.geo.*;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GuiShapeTest {

@Test
 public void GUIShape() { // Test the constructor

    Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
    boolean filled = true;
    Color color = Color.RED;
    int tag = 1;
    GUIShape guiShape = new GUIShape(circle, filled, color, tag);
    GeoShape g = guiShape.getShape();
    Circle_2D c = null;
    if (g instanceof Circle_2D) {
        c = (Circle_2D) g;
    }
    assertTrue((c.getRadius() == circle.getRadius()) && (c.getCenter().close2equals(circle.getCenter(),0.001)));
    assertTrue(guiShape.isFilled() == filled);
    assertTrue(guiShape.getColor().equals(color));
    assertTrue(guiShape.getTag() == tag);
    assertFalse(guiShape.isSelected());
}
@Test
   public void GUIShape2() { // Test the constructor

      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      boolean filled = true;
      Color color = Color.RED;
      int tag = 1;
      GUIShape gui = new GUIShape(circle, filled, color, tag);
      GUIShape guiShape = new GUIShape(gui);
      GeoShape g = guiShape.getShape();
      Circle_2D c = null;
      if (g instanceof Circle_2D) {
         c = (Circle_2D) g;
      }
      assertTrue((c.getRadius() == circle.getRadius()) && (c.getCenter().close2equals(circle.getCenter(),0.001)));
      assertTrue(guiShape.isFilled() == filled);
      assertTrue(guiShape.getColor().equals(color));
      assertTrue(guiShape.getTag() == tag);
      assertFalse(guiShape.isSelected());
   }
   @Test
   public void testGetShape1() {//Test the getShape function for circle
   Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      GeoShape shape = circle;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Circle_2D c = null;
      c = (Circle_2D) newShape;
      assertTrue((c.getRadius() == circle.getRadius()) && (c.getCenter().close2equals(circle.getCenter(),0.001)));
   }

   @Test
   public void testGetShape2() {//Test the getShape function for segment
      Segment_2D seg = new Segment_2D(new Point_2D(0, 0), new Point_2D(2,2));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Segment_2D c = null;
      c = (Segment_2D) newShape;
      assertTrue(((c.get_p1().close2equals(seg.get_p1(),0.001)) && (c.get_p2().close2equals(seg.get_p2(),0.001))));
   }
   @Test
   public void testGetShape3() {//Test the getShape function for rectangle
      Rect_2D seg = new Rect_2D(new Point_2D(0, 0), new Point_2D(2,2));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Rect_2D c = null;
      c = (Rect_2D) newShape;
      assertTrue(((c.getMaxPoint().close2equals(seg.getMaxPoint(),0.001))  ));
      assertTrue((c.getMinPoint().close2equals(seg.getMinPoint(),0.001)));
   }

   @Test
   public void testGetShape4() {//Test the getShape function for triangle
      Triangle_2D seg = new Triangle_2D(new Point_2D(0, 0), new Point_2D(2,2),new Point_2D(1,1));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Triangle_2D c = null;
      c = (Triangle_2D) newShape;
      assertTrue(arraysCompare(seg.getAllPoints(), c.getAllPoints()));
   }

   @Test
   public void testGetShape5() {//Test the getShape function for polygon
      Polygon_2D seg = new Polygon_2D();
      seg.add(new Point_2D(0,0));
      seg.add(new Point_2D(2,0));
      seg.add(new Point_2D(3,0));
      seg.add(new Point_2D(4,0));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Polygon_2D c = null;
      c = (Polygon_2D) newShape;
      assertTrue(compareArrayLists(seg.getVerts(),c.getVerts(),0.001));
   }
@Test
public void testIsFill () { // test the is fill answer
   Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
   boolean filled = true;
   Color color = Color.RED;
   int tag = 1;
   GUIShape guiShape = new GUIShape(circle, filled, color, tag);
   assertTrue(guiShape.isFilled());

}

   @Test
   public void testSetFill () { // test the set the fill flag correctly
      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      boolean filled = true;
      Color color = Color.RED;
      int tag = 1;
      GUIShape guiShape = new GUIShape(circle, filled, color, tag);
      guiShape.setFilled(false);
      assertFalse(guiShape.isFilled());

   }

   @Test
   public void testGetColor() {//Test that the getColor function return the correct color
      Triangle_2D seg = new Triangle_2D(new Point_2D(0, 0), new Point_2D(2,2),new Point_2D(1,1));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.getShape();
      Triangle_2D c = null;
      c = (Triangle_2D) newShape;
      assertEquals(Color.RED,guiShape.getColor());
   }

   @Test
   public void testSetColor () { // test that sets the color correctly
      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      boolean filled = true;
      Color color = Color.RED;
      int tag = 1;
      GUIShape guiShape = new GUIShape(circle, filled, color, tag);
      guiShape.setColor(Color.BLUE);
      assertEquals(Color.BLUE,guiShape.getColor());

   }

   @Test
   public void testCopy1() {//Test the copy function for circle
      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      GeoShape shape = circle;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.copy().getShape();
      Circle_2D c = null;
      c = (Circle_2D) newShape;
      assertTrue((c.getRadius() == circle.getRadius()) && (c.getCenter().close2equals(circle.getCenter(),0.001)));
   }

   @Test
   public void testCopy2() {//Test the copy function for segment
      Segment_2D seg = new Segment_2D(new Point_2D(0, 0), new Point_2D(2,2));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.copy().getShape();
      Segment_2D c = null;
      c = (Segment_2D) newShape;
      assertTrue(((c.get_p1().close2equals(seg.get_p1(),0.001)) && (c.get_p2().close2equals(seg.get_p2(),0.001))));
   }
   @Test
   public void testCopy3() {//Test the copy function for rectangle
      Rect_2D seg = new Rect_2D(new Point_2D(0, 0), new Point_2D(2,2));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.copy().getShape();
      Rect_2D c = null;
      c = (Rect_2D) newShape;
      assertTrue(((c.getMaxPoint().close2equals(seg.getMaxPoint(),0.001))  ));
      assertTrue((c.getMinPoint().close2equals(seg.getMinPoint(),0.001)));
   }

   @Test
   public void testCopy4() {//Test the copy function for triangle
      Triangle_2D seg = new Triangle_2D(new Point_2D(0, 0), new Point_2D(2,2),new Point_2D(1,1));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.copy().getShape();
      Triangle_2D c = null;
      c = (Triangle_2D) newShape;
      assertTrue(arraysCompare(seg.getAllPoints(), c.getAllPoints()));
   }

   @Test
   public void testCopy5() {//Test the copy function for polygon
      Polygon_2D seg = new Polygon_2D();
      seg.add(new Point_2D(0,0));
      seg.add(new Point_2D(2,0));
      seg.add(new Point_2D(3,0));
      seg.add(new Point_2D(4,0));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      GeoShape newShape = guiShape.copy().getShape();
      Polygon_2D c = null;
      c = (Polygon_2D) newShape;
      assertTrue(compareArrayLists(seg.getVerts(),c.getVerts(),0.001));
   }
   @Test
   public void  toStringTest1 () {//Tests that the received string is correct for a circle
      Point_2D center = new Point_2D(2,2);
      Circle_2D cir = new Circle_2D(center, 4);
      String expected = "Circle_2D,2.0,2.0,4.0";
      GeoShape shape = cir;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      assertEquals(expected, guiShape.toString());
   }

   @Test
   public void  toStringTest2 () { //Tests that the received string is correct for a polygon
      Polygon_2D seg = new Polygon_2D();
      seg.add(new Point_2D(0,0));
      seg.add(new Point_2D(2,0));
      seg.add(new Point_2D(3,0));
      seg.add(new Point_2D(4,0));
      String expected =  "Polygon_2D,0.0,0.0,2.0,0.0,3.0,0.0,4.0,0.0";
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);;
      Polygon_2D c = null;
      c = (Polygon_2D) guiShape.getShape();
      assertEquals(expected, c.toString());
   }
   @Test
   public void  toStringTest3 () { //Tests that the received string is correct for a rectangle
      Rect_2D rec = new Rect_2D(new Point_2D(5,5), new Point_2D(2,2));
      String expected = "Rect_2D,5.0,5.0,2.0,2.0";
      GeoShape shape = rec;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      Rect_2D c = null;
      c = (Rect_2D) guiShape.getShape();
      assertEquals(expected, c.toString());

   }
   @Test
   public void  toStringTest4 () {//Tests that the received string is correct for a segment
      Segment_2D seg = new Segment_2D(new Point_2D(0,0),new Point_2D(5,0));
      String expected = "Segment_2D,0.0,0.0,5.0,0.0";
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      Segment_2D c = null;
      c = (Segment_2D) guiShape.getShape();
      assertEquals(expected, c.toString());
   }

   @Test
   public void  toStringTest5 () {//Tests that the received string is correct for a triangle
      Triangle_2D seg = new Triangle_2D(new Point_2D(0,0),new Point_2D(4,0),new Point_2D(2,2)) ;
      String expected = "Triangle_2D,0.0,0.0,4.0,0.0,2.0,2.0";
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      Triangle_2D c = null;
      c = (Triangle_2D) guiShape.getShape();
      assertEquals(expected, c.toString());
   }

   @Test
   public void testSelected () { // test both set selected and is selected functions (the default is false for is selected)
      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      boolean filled = true;
      Color color = Color.RED;
      int tag = 1;
      GUIShape guiShape = new GUIShape(circle, filled, color, tag);
      guiShape.setSelected(true);
      assertTrue(guiShape.isSelected());

   }

   @Test
   public void tesSetShape() {//Test that the shape sets correctly
      Rect_2D seg = new Rect_2D(new Point_2D(0, 0), new Point_2D(2,2));
      GeoShape shape = seg;
      GUI_Shape guiShape = new GUIShape(shape, true, Color.RED, 1);
      Circle_2D circle = new Circle_2D(new Point_2D(0, 0), 5);
      guiShape.setShape(circle);
      GeoShape newShape = guiShape.getShape();
      Circle_2D c = null;
      c = (Circle_2D) newShape;
      assertTrue((c.getRadius() == circle.getRadius()) && (c.getCenter().close2equals(circle.getCenter(),0.001)));
   }


   private boolean compareArrayLists(ArrayList<Point_2D> list1, ArrayList<Point_2D> list2, double eps) { // Helper function
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

   private static boolean arraysCompare (Point_2D [] a , Point_2D [] b) {
      boolean bo = false;
      for (int i = 0; i < 3; i++) {
         if (a[i].close2equals(b[i],0.0001) ) bo = true;
         assertTrue(bo);
      }
      return bo;
     }
    }

