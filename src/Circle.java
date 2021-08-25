public class Circle extends Shape
{
   private int rad;

   public Circle(int rowBase, int colBase, int radius, char drawingCharacter) {
	   super(rowBase, colBase, drawingCharacter);
	   this.rad = radius;
   }

   public void draw(Window window)
   {
      for(int i = 0; i < 20; i++) {
         double angle = i * Math.PI/10;
         int rdif = (int) Math.round(rad * Math.cos(angle));
         int row = rb + rdif;
         int cdif = (int) Math.round(rad * Math.sin(angle));
         int col = cb + cdif;

         window.setCell(row, col, this.character);
      }
   }

   public int getRadius() { return this.rad; }

}
