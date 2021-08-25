public class Triangle extends Shape
{
   	private int height;
   	private int rInc;
   	private int cInc;

   	public Triangle(int rowBase, int colBase, int height, int rowIncrement, int colIncrement, char drawingCharacter) {
   		super(rowBase, colBase, drawingCharacter);
   		this.height = height;
   		this.rInc = rowIncrement;
   		this.cInc = colIncrement;
   	}

   	public void draw(Window window)
   	{
      	if(rInc == 0) {
			Line line1 = new Line(rb, cb, height, -1, cInc, character);
			Line line2 = new Line(rb, cb, height, 1, cInc, character); 
			Line line3 = new Line(rb - height, cb + cInc * height, 2 *height, 1, 0, character);
			line1.draw(window);
			line2.draw(window);
			line3.draw(window);

		} else if(cInc == 0) {
			Line line1 = new Line(rb, cb, height, rInc, -1, character);
			Line line2 = new Line(rb, cb,  height, rInc, 1, character);
			Line line3 = new Line(rb + rInc * height, cb - height, 2*height, 0, 1, character);
			line1.draw(window);
			line2.draw(window);
			line3.draw(window);
		}
	}

   	public int getHeight() {
   		return this.height;
   	}
   	
   	public int getRowIncrement() {
 	   return this.rInc;
    }
    
    public int getColIncrement() {
 	   return this.cInc;
    }

}
