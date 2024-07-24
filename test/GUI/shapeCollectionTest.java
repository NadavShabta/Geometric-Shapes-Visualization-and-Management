package exe.ex4.ex4;

import exe.ex4.geo.Circle_2D;
import exe.ex4.geo.GeoShape;
import exe.ex4.geo.Point_2D;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.Assert.*;
import exe.ex4.ex4.Ex4.Comparatori;


public class shapeCollectionTest {


    private final GeoShape geoShape = new Circle_2D(new Point_2D(1,1),1);
    private final GeoShape geoShape2 = new Circle_2D(new Point_2D(4,4),8);
    private  GUIShape gui = new GUIShape(geoShape, false, Color.BLACK,0);
    private  GUIShape gui2 = new GUIShape(geoShape2, false, Color.BLACK,1);
    private ShapeCollection collection = new ShapeCollection();
    private ShapeCollection collection2 = new ShapeCollection();



    @Test
    public void testShapeCollection() { // test that the constructor initialize a new empty ArrayList
        ShapeCollection shapeCollection = new ShapeCollection();
        assertNotNull(shapeCollection.get_shapes());
        assertTrue(shapeCollection.get_shapes().isEmpty());
    }

    @Test
    public void testGet() {// test the getter function at specific index
        ShapeCollection shapeCollection = new ShapeCollection();
        shapeCollection.add(gui);
        GUI_Shape retrievedShape = shapeCollection.get(0);
        assertSame(gui, retrievedShape);
    }

    @Test
    public void testGet_Shapes() {// test the getter function for _shapes
        collection.add(gui);
        collection2.add(gui);
       collection.get_shapes();
       collection2.get_shapes();
       assertTrue(compareArrayLists(collection.get_shapes(),collection2.get_shapes(),0.0001));

    }

    @Test
    public void testSize() {// test the size function
        collection.add(gui);
        assertTrue(collection.size() == 1);

    }


        @Test
        public void testRemoveElementAt() { // test that removes correctly and returns the correct shape

            collection.add(new GUIShape(new Circle_2D(new Point_2D(0, 0), 5), true, Color.RED, 1));
            collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
            collection.add(new GUIShape(new Circle_2D(new Point_2D(2, 2), 7), true, Color.GREEN, 3));
            GUI_Shape removedShape = collection.removeElementAt(1);
            boolean expectedFilled = false;
            Color expectedColor = Color.BLUE;
            int expectedTag = 2;
            collection2.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
            ArrayList<GUI_Shape> arrayList = new ArrayList<>();
            arrayList.add(removedShape);
            assertTrue(compareArrayLists(arrayList,collection2.get_shapes(),0.001));
            assertEquals(expectedFilled, removedShape.isFilled());
            assertEquals(expectedColor, removedShape.getColor());
            assertEquals(expectedTag, removedShape.getTag());
            assertFalse(collection.get_shapes().contains(removedShape));
        }

    @Test
    public void testAddAt() { // test that add the gui_shape in the specific index

        collection.add(new GUIShape(new Circle_2D(new Point_2D(0, 0), 5), true, Color.RED, 1));
        collection.add(new GUIShape(new Circle_2D(new Point_2D(2, 2), 7), true, Color.GREEN, 3));
        collection.addAt((new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2)),1);
        GUI_Shape removedShape = collection.removeElementAt(1);
        boolean expectedFilled = false;
        Color expectedColor = Color.BLUE;
        int expectedTag = 2;
        collection2.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
        ArrayList<GUI_Shape> arrayList = new ArrayList<>();
        arrayList.add(removedShape);
        assertTrue(compareArrayLists(arrayList,collection2.get_shapes(),0.001));
        assertEquals(expectedFilled, removedShape.isFilled());
        assertEquals(expectedColor, removedShape.getColor());
        assertEquals(expectedTag, removedShape.getTag());
        assertFalse(collection.get_shapes().contains(removedShape));
    }

    @Test
    public void testAdd() { // test that add the gui_shape in the specific index
        collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
        GUI_Shape removedShape = collection.removeElementAt(0);
        boolean expectedFilled = false;
        Color expectedColor = Color.BLUE;
        int expectedTag = 2;
        collection2.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
        ArrayList<GUI_Shape> arrayList = new ArrayList<>();
        arrayList.add(removedShape);
        assertTrue(compareArrayLists(arrayList,collection2.get_shapes(),0.001));
        assertEquals(expectedFilled, removedShape.isFilled());
        assertEquals(expectedColor, removedShape.getColor());
        assertEquals(expectedTag, removedShape.getTag());
        assertFalse(collection.get_shapes().contains(removedShape));
    }

    @Test
    public void testCopy() { // test that copy is made correctly
        collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 3), false, Color.BLUE, 2));
        ShapeCollection copy = (ShapeCollection) collection.copy();
        boolean expectedFilled = false;
        Color expectedColor = Color.BLUE;
        int expectedTag = 2;
        assertTrue(compareArrayLists(copy.get_shapes(),collection.get_shapes(),0.001));

    }

    @Test
    public void testSort1() { // test area sort

        collection.add(gui);
        collection.add(gui2);
        collection2.add(gui);
        collection2.add(gui2);
        Comparator<GUI_Shape> comparator = new Comparatori();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }
    @Test
    public void testSort2() {// test Anti area sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.AntiArea();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }

    @Test
    public void testSort3() {// test Perimeter sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.Perimeter();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }
    @Test
    public void testSort4() {// test Anti Perimeter sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.AntiPerimeter();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }

    @Test
    public void testSort5() {// test TAG sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.Antitag();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }
    @Test
    public void testSort6() {// test AntiTAG sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.Antitag();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }

    @Test
    public void testSort7() {// test toString sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.ToString();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }
    @Test
    public void testSort8() {// test ANTItoString sort

        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection2.addAt(gui,0);
        collection2.addAt(gui2,1);
        assertFalse(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
        Comparator<GUI_Shape> comparator = new Comparatori.antiToString();
        collection.sort(comparator);
        collection2.sort(comparator);
        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }

    @Test
    public void testRemoveAll() {// test that removes all shapes from _shapes
        collection.addAt(gui2,0);
        collection.addAt(gui,1);
        collection.removeAll();

        assertTrue(compareArrayLists(collection.get_shapes(), collection2.get_shapes(), 0.001));
    }

    @Test
    public void testSaveAndLoad() { // test correctness by comparing ArrayLists

        collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 2), false, Color.BLUE, 2));
        collection.add(new GUIShape(new Circle_2D(new Point_2D(3, 4), 3), true, Color.RED, 1));

        // Save the collection to a file
        String filename = "shapes.txt";
        collection.save(filename);

        // Load the shapes from the file into a new collection
        ShapeCollection loadedCollection = new ShapeCollection();
        loadedCollection.load(filename);

        // Compare the original collection with the loaded collection
        assertTrue(compareArrayLists(collection.get_shapes(), loadedCollection.get_shapes(),0.0001));
    }

    @Test
    public void testSaveAndLoad2() { // test correctness by comparing strings

        collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 2), false, Color.BLUE, 2));
        collection.add(new GUIShape(new Circle_2D(new Point_2D(3, 4), 3), true, Color.RED, 1));

        // Save the collection to a file
        String filename = "shapes.txt";
        collection.save(filename);

        // Load the shapes from the file into a new collection
        ShapeCollection loadedCollection = new ShapeCollection();
        loadedCollection.load(filename);

        // Compare the original collection with the loaded collection
        assertTrue(compareStrings(collection.toString(), loadedCollection.toString()));
    }


    @Test
    public void tesToString() { // test correctness by comparing actual and expected strings

        collection.add(new GUIShape(new Circle_2D(new Point_2D(1, 1), 2), false, Color.BLUE, 2));

        // Save the collection to a file
        String  ans =new String("Circle_2D,1.0,1.0,2.0") ;


        // Compare the original collection with the loaded collection
        assertTrue(compareStrings(collection.toString(), ans));
    }








    private static boolean compareStrings(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true; // Both strings are null, considered equal
        }
        if (str1 == null || str2 == null) {
            return false; // One string is null while the other is not, considered not equal
        }
        return str1.equals(str2); // Compare the strings using the equals method
    }




    private boolean compareArrayLists(ArrayList<GUI_Shape> list1, ArrayList<GUI_Shape> list2, double eps) {
        boolean b = false;
        if (list1.size() != list2.size()) {
            return  b;
        }
        if ((list1.size() == 0) && list2.size() == 0 ) {return true;}
            GUI_Shape shape1 = list1.get(0);
            GUI_Shape shape2 = list2.get(0);
            if (shape1.getShape() instanceof Circle_2D && shape2.getShape() instanceof Circle_2D) {
                Circle_2D circle1 = (Circle_2D) shape1.getShape();
                Circle_2D circle2 = (Circle_2D) shape2.getShape();
                if (circle1.getCenter().close2equals(circle2.getCenter(), eps) ||
                        Math.abs(circle1.getRadius() - circle2.getRadius()) < eps) {
                    b = true;
                    return b;
                }
            } else {
                b = false;
               return  b;
            }

        return b;
    }



}

