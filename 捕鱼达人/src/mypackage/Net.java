package mypackage;

import java.awt.*;

import javax.swing.*;

public class Net
{
	Image image[] = new Image[10];
	int x,y;
	int life;
	int multi = 1;
	boolean alive = true;
	public Net( int x,int y )
	{
		this.x = x;
		this.y = y;
		life = 8*multi;
		for( int i = 1 ; i <= 8 ; i++ )
		{
			image[i] = new ImageIcon("images\\net_"+String.valueOf(i)+".png").getImage();
		}
	}
	
}
