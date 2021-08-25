public class Text extends Shape
{
   private String text;
   private int rInc;
   private int cInc;

   public Text(int rowBase, int colBase, String text, int rowIncrement, int colIncrement)
   {
	   super(rowBase, colBase, ' ');
	   this.text = text;
	   this.rInc = rowIncrement;
	   this.cInc = colIncrement;
   }

   public void draw(Window window)
   {
      for(int i = 0; i < text.length(); i++) {
         char ch = text.charAt(i);
         int row = rb + i * rInc;
         int col = cb + i * cInc;

         window.setCell(row, col, ch);
      }
   }

   public String getText() {
	   return this.text;
   }

   public int getRowIncrement() {
	   return this.rInc;
   }
   
   public int getColIncrement() {
	   return this.cInc;
   }

}
