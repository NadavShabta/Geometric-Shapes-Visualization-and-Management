package exe.ex4.ex4;
import exe.ex4.gui.GUIShape;
import exe.ex4.gui.GUI_Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	} // constructor for _shapes ArrayList

	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}// getter for specific shape

	public ArrayList<GUI_Shape> get_shapes() {// getter for the Arraylist
		return _shapes;
	}

	@Override
	public int size() {
		return _shapes.size();
	} // return the size of the ArrayList

	@Override
	public GUI_Shape removeElementAt(int i) {// This method remove a GUI shape at index i in the collection.
		GUI_Shape g = _shapes.get(i);
		_shapes.remove(i);
		return g;
	}

	@Override
	public void addAt(GUI_Shape s, int i) {// This method inserts a GUI shape at index i in the collection.
		_shapes.add(i,s);
	} // Add the gui_shape in a specific index

	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}

	@Override
	public GUI_Shape_Collection copy() { //create a new copy of the collection
		ShapeCollection s = new ShapeCollection();
		for (int i = 0; i <this.size() ; i++) {
			s.addAt(this.get(i),i);
		}
		return s;
	}

	@Override
	// sort the collection by the given comparator
	public void sort(Comparator<GUI_Shape> comp) {//This method sorts the collection using the provided comp Comparator.
		Collections.sort(_shapes,comp);
	}

	@Override
	public void removeAll() {//This method removes all the elements from this collection.
		_shapes.clear();
	} // removes all shapes from _shapes

	@Override
	public void save(String file_name) {
		//The save function saves the collection of GUI shapes to a file specified by file_name.
		// It writes the string representation of each GUI shape to a new line in the file.
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name))) {
			for (GUI_Shape shape : _shapes) {
				writer.write("GUIShape " + "," +shape.getColor().getRGB() + "," + shape.isFilled() + "," + shape.getTag() + "," + shape.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void load(String file) {
		_shapes.clear(); // Clear the existing collection before loading from the file

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Assuming the line represents the string representation of a GUI_Shape object
				// You will need to convert the string back to a GUI_Shape object based on your implementation
				GUI_Shape shape = GUIShape.convertStringToGUI_Shape(line);
				_shapes.add(shape);
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle or log any potential exceptions
		}
	}


	@Override
	public String toString() { // creates a string representing the collection
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}
