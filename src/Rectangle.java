public class Rectangle extends Shape
{
	private int height;
   	private int width;

   	public Rectangle(int rowBase, int colBase, int height, int width, char drawingCharacter)
	{
   		super(rowBase, colBase, drawingCharacter);
   		this.height = height;
   		this.width = width;
   	}

   	public void draw(Window window)
   	{
   		Line line1 = new Line(rb, cb, width, 0, 1, character);
   		Line line2 = new Line(rb, cb+width, height, 1, 0, character);
   		Line line3 = new Line(rb+height, cb+width, width, 0, -1, character);
   		Line line4 = new Line(rb+height, cb, height, -1, 0, character);

   		line1.draw(window);
   		line2.draw(window);
   		line3.draw(window);
   		line4.draw(window);
	}

   	public int getHeight() {
   		return this.height;
   	}
   	
   	public int getWidth() {
   		return this.width;
   	}

}
