public class Line extends Shape
{
   private int length;
   private int rInc;
   private int cInc;


   public Line(int rb, int cb, int length, int rInc, int cInc, char ch)
   {
       super(rb, cb, ch);
       this.length = length;
       this.rInc = rInc;
       this.cInc = cInc;
   }
   
   public void draw(Window window)
   {
	  int row = this.rb;
	  int col = this.cb;
      for(int i = 0; i <= length; i++) {
         window.setCell(row, col, this.character);
         row += rInc;
         col += cInc;
      }
   }

   public int setLength(int length) {
	   return this.length = length;
   }
   
   public int getLength() {
	   return this.length;
   }
   
   public int getRowIncrement() {
	   return this.rInc;
   }
   
   public int getColIncrement() {
	   return this.cInc;
   }

}
