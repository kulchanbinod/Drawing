import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Window
{
   protected int rows;
   protected int cols;
   protected ArrayList<Shape> shapes;
   protected char [][] cells;
   protected char border;
   
   public Window() {
       this.shapes = new ArrayList<Shape>();
       this.cells = new char[2][2];
   }

   public Window(int rows, int cols, char border)
   {
        this.rows = rows;
        this.cols = cols;
        this.shapes = new ArrayList<Shape>();
        this.cells = new char[rows+2][cols+2];
        this.border = border;
        this.addBorders(border);
   }
   
   protected void init() {
	   for (int i = 1; i < rows+1; i++) {
           for (int j = 1; j < cols+1; j++) {
        	   cells[i][j] = ' ';
           }
       }
   }

   protected void addBorders(char ch)
   {
        for (int i = 0; i < rows+2; i++) {
            for (int j = 0; j < cols+2; j++) {
                if (i == 0 || j == 0 || i == (rows + 1) || j == (cols + 1)) {
                	cells[i][j] = ch;
                } else {
                	cells[i][j] = ' ';
                }
            }
        }
   }

   public void display()
   {
       for (int i = 0; i < this.shapes.size(); i++) {
    	   Shape s = this.shapes.get(i);
    	   s.draw(this);
       }

        for (int i = 0; i< rows+2; i++) {
            for (int j = 0; j< cols+2; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.println("");
        }
   }

   public void addShape(Shape shape)
   {
        this.shapes.add(shape);
   }
   
   public void removeShape(int index)
   {
        this.shapes.remove(index);
        this.init();
        for (int i = 0; i < this.shapes.size(); i++) {
     	   Shape s = this.shapes.get(i);
     	   s.draw(this);
        }
   }
   
   public ArrayList<Shape> getShapes() {
	   return this.shapes;
   }

   public void setCell(int row, int col, char ch)
   {
        this.cells[row][col] = ch;
   }
   
   public void addGrid() {
	   char[] list = new char[]{'0','1','2','3','4','5','6','7','8','9'};
	   
	   int counter = 1;
	   
	   cells[0][0] = '*';
	   cells[rows+1][0] = '*';
	   cells[rows+1][cols+1] = '*';
	   cells[0][cols+1] = '*';
	   
	   for (int i = 1; i < rows+1; i++ ) {
		   cells[i][0] = list[counter++];
		   if (counter >= list.length) {
			   counter = 0;
		   }
	   }
	   counter = 1;
	   for (int i = 1; i < cols+1; i++ ) {
		   cells[0][i] = list[counter++];
		   if (counter >= list.length) {
			   counter = 0;
		   }
	   }
	   counter = 1;
	   for (int i = 1; i < cols+1; i++ ) {
		   cells[rows+1][i] = list[counter++];
		   if (counter >= list.length) {
			   counter = 0;
		   }
	   }
	   counter = 1;
	   for (int i = 1; i < rows+1; i++ ) {
		   cells[i][cols+1] = list[counter++];
		   if (counter >= list.length) {
			   counter = 0;
		   }
	   }
	   
   }
   
   public void refreshImage() {
	   this.init();
	   for (int i = 0; i < this.shapes.size(); i++) {
		   Shape s = this.shapes.get(i);
		   s.draw(this);
	   }

	    for (int i = 0; i< rows+2; i++) {
	        for (int j = 0; j< cols+2; j++) {
	            System.out.print(cells[i][j]);
	        }
	        System.out.println("");
	    }
   }


   public void writeSpecToFile(String fileName) {
	   String newLine = System.getProperty("line.separator");
	   String content = "";
	   content += this.rows + " " + this.cols + newLine;
	   content += "*" + newLine;
	   content += "." + newLine;
	   for (int i = 0; i < this.shapes.size(); i++) {
    	   Shape s = this.shapes.get(i);
    	   if (s instanceof Line) {
    		   Line l = (Line) s;
    		   content += "line" + newLine;
    		   content += s.rb + " " + s.cb + " " + l.getLength() + " " + l.getRowIncrement() + " " + l.getColIncrement() + " " + newLine;
    		   content += s.character + newLine;
    		   content += "." + newLine;
    	   } else if (s instanceof Circle) {
    		   Circle c = (Circle) s;
    		   content += "circle" + newLine;
    		   content += s.rb + " " + s.cb + " " + c.getRadius() + newLine;
    		   content += s.character + newLine;
    		   content += "." + newLine;
    	   } else if (s instanceof Rectangle) {
    		   Rectangle r = (Rectangle) s;
    		   content += "rectangle" + newLine;
    		   content += s.rb + " " + s.cb + " " + r.getHeight() + " " + r.getWidth() + newLine;
    		   content += s.character + newLine;
    		   content += "." + newLine;
    	   } else if (s instanceof Triangle) {
    		   Triangle t = (Triangle) s;
    		   content += "triangle" + newLine;
    		   content += s.rb + " " + s.cb + " " + t.getHeight() + " " + t.getRowIncrement() + " " + t.getColIncrement() + newLine;
    		   content += s.character + newLine;
    		   content += "." + newLine;
    	   } else if (s instanceof Text) {
    		   Text tx = (Text) s;
    		   content += "text" + newLine;
    		   content += s.rb + " " + s.cb + newLine;
    		   content += tx.getText() + newLine;
    		   content += tx.getRowIncrement() + " " + tx.getColIncrement() + newLine;
    		   content += "." + newLine;
    	   }
       }
	   try {
		   File file = new File(fileName);
		   file.createNewFile();
		   
		   FileWriter myWriter = new FileWriter(fileName);
		   myWriter.write(content);
		   myWriter.close();
		   
	   } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
   }
   
   static Window readSpecFromFile(String fileName) {
	   Window w = new Window();
	   try {
		   BufferedReader bufferreader = new BufferedReader(new FileReader(fileName));
		   String line = "";
		   
		   boolean firstSection = true;
		   String data[] = null;
		   int row = 0;
		   int col = 0;
		   
		   while ((line = bufferreader.readLine()) != null) {   
			   if (line.equalsIgnoreCase(".")) {
				   firstSection = false;
				   continue;
			   }
			   if (firstSection) {
				   if (data == null) {
					   	data = line.split(" ");
					   	row = Integer.parseInt(data[0]);
				   		col = Integer.parseInt(data[1]);
				   } else {
					   w = new Window(row, col, line.charAt(0));
				   }
			   } else {
				   String shapeName = null;
				   
				   String l1 = "";
				   String l2 = "";
				   String l3 = "";
				   String l4 = "";
				   
				   do {
					   if(shapeName == null) {
						   shapeName = line;
					   }
					   
					   if(shapeName.equalsIgnoreCase("text")) {
						   l1 = line;
						   l2 = (line = bufferreader.readLine());
						   l3 = (line = bufferreader.readLine());
						   l4 = (line = bufferreader.readLine());
					   } else {
						   l1 = line;
						   l2 = (line = bufferreader.readLine());
						   l3 = (line = bufferreader.readLine());
					   }
					   
					   w.addShape(getShape(l1, l2, l3, l4));
				   
				   } while(!(line = bufferreader.readLine()).equalsIgnoreCase("."));
				}
			   
		   }
		   
		   bufferreader.close();
	   } catch (FileNotFoundException ex) {
		   System.out.println("File not found.");
	       ex.printStackTrace(); 
	   } catch (IOException e) {
		   System.out.println("An error occurred.");
		   e.printStackTrace();
	   }
	   
	   return w;
   }
   
   private static Shape getShape(String name, String params, String text, String extra) {
	   Shape s = null;
	   
	   int[] parameters = getParamerets(params);
	   if (name.equalsIgnoreCase("line")) {
		   s = new Line(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], text.charAt(0));
	   } else if (name.equalsIgnoreCase("circle")) {
		   s = new Circle(parameters[0], parameters[1], parameters[2], text.charAt(0));
	   } else if (name.equalsIgnoreCase("rectangle")) {
		   s = new Rectangle(parameters[0], parameters[1], parameters[2], parameters[3], text.charAt(0));
	   } else if (name.equalsIgnoreCase("triangle")) {
		   s = new Triangle(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], text.charAt(0)); 
	   } else if (name.equalsIgnoreCase("text")) {
		   int[] extras = getParamerets(extra);
		   s = new Text(parameters[0], parameters[1], text, extras[0], extras[1]);
	   }
	   
	   return s;
   }
   
   private static int[] getParamerets(String line) {
	   String list[] = line.split(" ");
	   int[] parameters = new int[list.length];
	   for(int i = 0; i < list.length; i++) {
		   parameters[i] = Integer.parseInt(list[i]);
	   }
	   return parameters;
   }

}






















