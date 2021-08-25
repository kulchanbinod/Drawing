# Drawing

JAVA based CLI application for drawing basic shapes using chars.

## Running the program

Main method is located in ```DrawingBoard.java``` file. You can run this file directly no additional steps required.

Program will ask for a filename.
```bash
Enter the window file name (or NEW): 
```
You can enter ```NEW``` or already saved filename. if you enter ```NEW``` you will be presented with this.
```bash
Enter number of rows, number of columns and character (separated by space): 
```
You can enter for example ```10 10 *```.

After you please enter, program will init a blank drawing board. Use the provided commands to draw on the borad.
```bash
*1234567890*
1          1
2          2
3          3
4          4
5          5
6          6
7          7
8          8
9          9
0          0
*1234567890*
Add Erase Select Write Quit
Up Down Left Right + -
```
Let's draw a 3X3 rectangle on coordinates 3,3 using ```*``` char.

```bash
Add Erase Select Write Quit
Up Down Left Right + -
Add
Enter shape type [line, rectangle, triangle, circle, text]: 
rectangle
Enter rowBase: 
3
Enter colBase: 
3
Enter height: 
4
Enter width: 
4
Enter character: 
*
```

Output on the drawing board.
```bash
*1234567890*
1          1
2          2
3  *****   3
4  *   *   4
5  *   *   5
6  *   *   6
7  *****   7
8          8
9          9
0          0
*1234567890*
Add Erase Select Write Quit
Up Down Left Right + -
```
Finally, lets save the file so that we can open this drawing again after we exit this program.
```bash
*1234567890*
1          1
2          2
3  *****   3
4  *   *   4
5  *   *   5
6  *   *   6
7  *****   7
8          8
9          9
0          0
*1234567890*
Add Erase Select Write Quit
Up Down Left Right + -
Write
Enter filename: 
rect.txt
Save saved successfully 
```

Now you can type ```rect.txt``` when asked at the start of the program and this drawing board will load into your console.