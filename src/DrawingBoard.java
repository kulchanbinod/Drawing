import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class DrawingBoard
{
	private static Scanner kb = new Scanner(System.in);
	private static Window w = null;
	private static ArrayList<Shape> shapes = null;
	private static Shape selectedShape = null;

	public static void main(String [] args) throws Exception
   	{

		System.out.println("Enter the window file name (or NEW): ");
		String name = kb.nextLine().trim();
		if(name.equalsIgnoreCase("NEW")) {
			System.out.println(
				"Enter number of rows, number of columns and character (separated by space): " );
			int rbase = kb.nextInt();
			int cbase = kb.nextInt();
			char ch = kb.nextLine().trim().charAt(0);
			w = new Window(rbase, cbase, ch);
			w.addGrid();
		} else {
			File file = new File(name);
			
			if (file.exists()) {
				w = Window.readSpecFromFile(name);
				w.addGrid();
			} else {
				System.out.println("File not found please try again.");
			}
		}

		boolean repeat = true;
		while(repeat)
		{
			System.out.println("\n");

			w.refreshImage();

			System.out.println("Add Erase Select Write Quit");
			System.out.println("Up Down Left Right + -");

			String cmd = kb.next();
			switch(cmd.toUpperCase().charAt(0))
			{
				case 'U':
					if (selectedShape != null) {
						selectedShape.moveUp(w);
					}
					break;
			 	case 'D':
			 		if (selectedShape != null) {
						selectedShape.moveDown(w);
					}
			 		break;
				case 'L':
					if (selectedShape != null) {
						selectedShape.moveLeft(w);
					}
					break;
				case 'R':
					if (selectedShape != null) {
						selectedShape.moveRight(w);
					}
					break;
				case '+':
					if (selectedShape != null) {
						selectedShape.increase(w);
					}
					break;
				case '-':
					if (selectedShape != null) {
						selectedShape.decrease(w);
					}
					break;

				case 'S':
					selectShape();
					break;
				case 'A':
					addShape(); 
					break;
				case 'E':
					deleteShape(); 
					break;
				case 'W':
					writeSpecToFile(); 
					break;
				case 'Q':
					repeat = false;
					break;

				default: System.out.println("Wrong option chosen!");
			}
		}

		System.out.println("Thank You!");
	}

	public static void selectShape()
	{
		listShapes();
		int choice = kb.nextInt();
		selectedShape = w.getShapes().get(choice);
	}

	public static void addShape()
	{
		
		System.out.println("Enter shape type [line, rectangle, triangle, circle, text]: ");
		
		String userInput = kb.next().trim();
		
		if (userInput.equalsIgnoreCase("line")) {
			System.out.println("Enter rowBase: ");
			int rb = kb.nextInt();
			System.out.println("Enter colBase: ");
			int cb = kb.nextInt();
			System.out.println("Enter length: ");
			int len = kb.nextInt();
			System.out.println("Enter rowIncrement: ");
			int rInc = kb.nextInt();
			System.out.println("Enter colIncrement: ");
			int cInc = kb.nextInt();
			System.out.println("Enter character: ");
			char ch = kb.next().charAt(0);
			
			Line l = new Line( rb, cb, len, rInc, cInc, ch );
			w.addShape(l);
		} else if (userInput.equalsIgnoreCase("rectangle")) {
			
			System.out.println("Enter rowBase: ");
			int rb = kb.nextInt();
			System.out.println("Enter colBase: ");
			int cb = kb.nextInt();
			System.out.println("Enter height: ");
			int h = kb.nextInt();
			System.out.println("Enter width: ");
			int wid = kb.nextInt();
			System.out.println("Enter character: ");
			char ch = kb.next().charAt(0);
			
			Rectangle r = new Rectangle(rb, cb, h, wid, ch);
			w.addShape(r);
			
		} else if (userInput.equalsIgnoreCase("triangle")) {

			System.out.println("Enter rowBase: ");
			int rb = kb.nextInt();
			System.out.println("Enter colBase: ");
			int cb = kb.nextInt();
			System.out.println("Enter length: ");
			int len = kb.nextInt();
			System.out.println("Enter rowIncrement: ");
			int rInc = kb.nextInt();
			System.out.println("Enter colIncrement: ");
			int cInc = kb.nextInt();
			System.out.println("Enter character: ");
			char ch = kb.next().charAt(0);
			
			Triangle t = new Triangle( rb, cb, len, rInc, cInc, ch );
			w.addShape(t);
		} else if (userInput.equalsIgnoreCase("circle")) {
			
			System.out.println("Enter rowBase: ");
			int rb = kb.nextInt();
			System.out.println("Enter colBase: ");
			int cb = kb.nextInt();
			System.out.println("Enter radius: ");
			int rad = kb.nextInt();
			System.out.println("Enter character: ");
			char ch = kb.next().charAt(0);
			
			Circle c = new Circle( rb, cb, rad, ch);
			w.addShape(c);
		} else if (userInput.equalsIgnoreCase("text")) {
			System.out.println("Enter rowBase: ");
			int rb = kb.nextInt();
			System.out.println("Enter colBase: ");
			int cb = kb.nextInt();
			System.out.println("Enter text: ");
			String text = kb.next();
			System.out.println("Enter rowIncrement: ");
			int rInc = kb.nextInt();
			System.out.println("Enter colIncrement: ");
			int cInc = kb.nextInt();
			
			Text t = new Text(rb, cb, text, rInc, cInc);
			w.addShape(t);
		}
		
	}

	public static void deleteShape()
	{
		listShapes();
		int choice = kb.nextInt();
		if(choice > shapes.size()) {
			System.out.println("Invalid choice.");
		} else {
			w.removeShape(choice);
		}
	}

	public static void writeSpecToFile()
	{
		System.out.println("Enter filename: ");
		
		String userInput = kb.next().trim();
		
		w.writeSpecToFile(userInput);
		System.out.println("Save saved successfully ");
	}

	public static void listShapes()
	{
		shapes = w.getShapes();
		for(int i = 0; i < shapes.size(); i++) {
			Shape s = shapes.get(i);
			String content = "";
			content += "";
			
			if (s instanceof Line) {
    		   Line l = (Line) s;
    		   content += "line ("+ l.rb +", "+ l.cb +")("+ l.getLength() +")("+ l.getRowIncrement() +", "+ l.getColIncrement() +")("+ l.character +")";
    	   } else if (s instanceof Circle) {
    		   Circle c = (Circle) s;
    		   content += "circle ("+ c.rb +", "+ c.cb +")("+ c.getRadius() +")("+ c.character +")";
    	   } else if (s instanceof Rectangle) {
    		   Rectangle r = (Rectangle) s;
    		   content += "rectangle ("+ r.rb +", "+ r.cb +")("+ r.getHeight() +", "+ r.getWidth() +")("+ r.character +")";
    	   } else if (s instanceof Triangle) {
    		   Triangle t = (Triangle) s;
    		   content += "triangle ("+ t.rb +", "+ t.cb +")("+ t.getHeight() +")("+ t.getRowIncrement() +", "+ t.getColIncrement() +")("+ t.character +")";
    	   } else if (s instanceof Text) {
    		   Text tx = (Text) s;
    		   content += "text ("+ tx.rb +", "+ tx.cb +")("+ tx.getText() +")("+ tx.getRowIncrement() +", "+ tx.getColIncrement() +")";
    	   }
			
			System.out.println(i+": "+content);
		}
	}

}

