import java.io.*;

public abstract class Shape
{
    int rb;
    int cb;
    char character;

    public Shape(int rb, int cb, char character)
    {
        this.rb = rb;
        this.cb = cb;
        this.character = character;
    }

    public abstract void draw(Window window);

    public void setCharacter(char ch) {
    	this.character = ch;
    }
    
    public void moveUp(Window window)
	{
    	int newRb = rb - 1;
    	if (newRb <= 0) {
    		return;
    	}
    	this.rb = newRb;
    	this.draw(window);
    }
    
    public void moveDown(Window window)
	{
    	int newRb = rb + 1;
    	if (newRb >= window.cols) {
    		return;
    	}
    	this.rb = newRb;
    	this.draw(window);
    }
    
    public void moveLeft(Window window)
	{
    	int newCb = cb - 1;
    	if (newCb <= 0) {
    		return;
    	}
    	this.cb = newCb;
    	this.draw(window);
    }
    
    public void moveRight(Window window)
	{
    	int newCb = cb + 1;
    	if (newCb >= window.cols) {
    		return;
    	}
    	this.cb = newCb;
    	this.draw(window);
    }
    
    public void increase(Window window)
	{
    	if (this instanceof Line) {
    		int len = ((Line) this).getLength();
    		len++;
    		((Line) this).setLength(len);
    	}
    	this.draw(window);
    }
    
    public void decrease(Window window)
	{
    	if (this instanceof Line) {
    		int len = ((Line) this).getLength();
    		len--;
    		((Line) this).setLength(len);
    	}
    	this.draw(window);
    }

}