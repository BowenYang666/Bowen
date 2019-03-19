package mypackage;

import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

public class Bullet implements Runnable
{
	static Image image = new ImageIcon("FunctionImages\\bullet.png").getImage();
	int x,y;
	int speed=20;
	int type = 1;
	double angle;
	boolean alive = true;
	int bulletStartx;
	int bulletStarty;
	private Bullet( int x,int y )
	{
		this.x = x;
		this.y = y;
	}
	public Bullet( Canon canon )	//子弹种类，以及点击的坐标
	{
		
		this.type = canon.type;
		this.angle = canon.angle;
		//System.out.println(" angle "+angle);
		bulletStartx = (int)
				( (double)canon.rotatex-10+( (double)canon.canonHeight)*Math.sin(angle) );
		bulletStarty = (int)
				( (double)canon.rotatey-5-( (double)canon.canonHeight )*Math.cos(angle) );
		this.x = bulletStartx;
		this.y = bulletStarty;
		
		
		
	}
	public void move()
	{
		x = (int)( (double)x+(double)speed*Math.sin(angle) );
		y = (int)( (double)y-(double)speed*Math.cos(angle) );
		if( x > MainInterface.width || x < 0 || y > MainInterface.height || y < 0 )
		{
			alive = false;
		}
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while( true )
		{
			try 
			{
				Thread.sleep(100);
				move();
				if( this.alive == false )
					break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public boolean checkDash( Fish f )
	{
		/*
		int x1,x2,y1,y2;
		x1 = f.x;
		x2 = f.x+f.fishWidth;
		y1 = f.y;
		y2 = f.y+f.fishHeight;
		if( this.x >= x1 && this.x <= x2 && this.y >= y1 && this.y <= y2 )
			return true;
		return false;
		*/
		//根据fish的朝向判断子弹是否打中
		double j1 = Math.atan((double)f.fishHeight/(double)f.fishWidth);
		double j2 = Math.toRadians(45)-j1;
		Point a,b,c,d;
		a = new Point();	//顺时针，从左上角1234
		b = new Point();
		c = new Point();
		d = new Point();
		Point centerPoint = new Point(f.x+f.fishWidth/2,f.y+f.fishHeight/2);
		//System.out.println("Center : "+centerPoint.x+"   "+ centerPoint.y);
		int mx,my;
		int l = (int)( f.fishHeight/2/Math.sin(j1) );
		double sq2 = Math.sqrt(2);
		if( f.direction == 1 )		//方向往上，包括偏移的
		{
			if( f.diviation == 0 )					//上
				return this.normalcheck(centerPoint.x-f.fishHeight/2,
						centerPoint.x+f.fishHeight/2,
						centerPoint.y-f.fishWidth/2,
						centerPoint.y+f.fishWidth/2);
			
			else if( f.diviation == -1 )	//逆时针旋转,上左
			{
				mx = centerPoint.x-(int)(l*Math.cos(j2) );
				my = centerPoint.y-(int)(l*Math.sin(j2) );
				
				a.setLocation(mx, my); 	b.setLocation(mx+f.fishHeight/sq2,my-f.fishHeight/sq2);
				c.setLocation(mx+f.fishHeight/sq2+f.fishWidth/sq2,my+f.fishWidth/sq2-f.fishHeight/sq2);
				d.setLocation(mx+f.fishWidth/sq2, my+f.fishWidth/sq2);
				return specialCheck(a, b, c, d);
			}
			else if( f.diviation == 1 )	//顺时针旋转  上右
			{
				mx = centerPoint.x-(int)(l*Math.cos(j2) );
				my = centerPoint.y+(int)(l*Math.sin(j2) );
				
				a.setLocation(mx, my); 	
				b.setLocation(mx+f.fishWidth/sq2,my-f.fishWidth/sq2);
				c.setLocation(mx+f.fishHeight/sq2+f.fishWidth/sq2,my+f.fishHeight/sq2-f.fishWidth/sq2);
				d.setLocation(mx+f.fishHeight/sq2, my+f.fishHeight/sq2);
				return specialCheck(a, b, c, d);
				
			}
		}
		else if( f.direction == 2 )	//往下
		{
			if( f.diviation == 0 )	//正下方
				return this.normalcheck(centerPoint.x-f.fishHeight/2,
						centerPoint.x+f.fishHeight/2,
						centerPoint.y-f.fishWidth/2,
						centerPoint.y+f.fishWidth/2);
			else if( f.diviation == -1 )	//逆时针旋转,下右
			{
				mx = centerPoint.x-(int)(l*Math.cos(j2) );
				my = centerPoint.y-(int)(l*Math.sin(j2) );
				
				a.setLocation(mx, my); 	b.setLocation(mx+f.fishHeight/sq2,my-f.fishHeight/sq2);
				c.setLocation(mx+f.fishHeight/sq2+f.fishWidth/sq2,my+f.fishWidth/sq2-f.fishHeight/sq2);
				d.setLocation(mx+f.fishWidth/sq2, my+f.fishWidth/sq2);
				return specialCheck(a, b, c, d);
			}
			else if( f.diviation == 1 )	//顺时针旋转  下左
			{
				mx = centerPoint.x-(int)(l*Math.cos(j2) );
				my = centerPoint.y+(int)(l*Math.sin(j2) );
				
				a.setLocation(mx, my); 	b.setLocation(mx+f.fishWidth/sq2,my-f.fishWidth/sq2);
				c.setLocation(mx+f.fishHeight/sq2+f.fishWidth/sq2,my+f.fishHeight/sq2-f.fishWidth/sq2);
				d.setLocation(mx+f.fishHeight/sq2, my+f.fishHeight/sq2);
				return specialCheck(a, b, c, d);
				
			}
		}
		else if( f.direction == 3 )
		{
			if( this.x > f.x && this.x < f.x+f.fishWidth &&
					this.y > f.y && this.y < f.y+f.fishHeight )
				return true;
		}
		
		return false;
	}
	private boolean normalcheck(int x1,int x2,int y1,int y2)
	{
		if( this.x >= x1 && this.x <= x2 && this.y >= y1 && this.y <= y2 )
			return true;
		return false;
	}
	private boolean specialCheck( Point a,Point b,Point c,Point d )
	{
		if( vectorMulti(a.x,b.x,a.y,b.y,this.x,this.y) <= 0 )	//在ab上方
			return false;
		
		if( vectorMulti(b.x,c.x,b.y,c.y,this.x,this.y) <= 0 )	//在bc上方
			return false;
		
		if( vectorMulti(c.x,d.x,c.y,d.y,this.x,this.y) <= 0 )	//在cd下方
			return false;

		if( vectorMulti(d.x,a.x,d.y,a.y,this.x,this.y) <= 0 )	//在ad下方
			return false;
		return true;
	}
	
	//如果大于0，表示点在另外两点形成直线的	下方
	//小于0则在上方
	private int vectorMulti( int x1,int x2,int y1,int y2,int pointx,int pointy)//向量叉乘计算
	{
		int vx1 = x2-x1;
		int vx2 = pointx-x1;
		int vy1 = y2-y1;
		int vy2 = pointy-y1;
		return vx1*vy2-vy1*vx2;	
	}
	
//	public static void main( String args[] )
//	{
//		Fish f = new Fish();
//		f.x = 100;
//		f.y = 100;
//		f.fishWidth = 100;
//		f.fishHeight = 50;
//		f.direction = 1;
//		f.diviation = -1;
//		while( true )
//		{
//			Scanner in = new Scanner(System.in);
//			int mx,my;
//			System.out.println("please input");
//			mx = in.nextInt();
//			my = in.nextInt();
//			Bullet b = new Bullet(mx,my);
//			
//			System.out.println( b.checkDash(f) );
//		}
//		
//	}
	
}
