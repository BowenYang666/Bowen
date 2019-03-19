package mypackage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.ImageIcon;

/*
 * 功能：定义各种鱼，实现各种鱼的姿势
 * 
 */
public class Fish implements Runnable
{
	int x,y;			//鱼的坐标
	int speed = 2;			//鱼游的速度
	int type;			//左右出生，还是上下出生
	int direction;		//鱼游的方向
	int status,multi = 3;			//根据状态画图片
	int start_status = 30;
	int deadstatus = 30;
	boolean alive = true;		//鱼被抓后就死了
	int diviation = 0;			//0表示鱼不偏转，-1表示往左偏，1表示往右偏,即-1表示逆时针，1表示顺
	int fishWidth = 40,fishHeight = 30;
	static int d1[]= {0,0,0,-1,1},		//方向数组，下标从1开始，0位置用0补充
			   d2[]= {0,-1,1,0,0};
	int score;
	
	ImageIcon liveicon[] = new ImageIcon[15];
	ImageIcon deadicon[] = new ImageIcon[5];
	
	
	public Fish()		//生成一条鱼，位置是随机的
	{
		score = 1;
		randGenerate();
	}
	private void randGenerate()		//随机生成出生的地方
	{
		Random rand = new Random();
		type = rand.nextInt(2);
		direction = rand.nextInt(2);
		
		if( type == 0 )	//鱼是左右出生
		{
			y = rand.nextInt(MainInterface.height-200)+100;	//y的范围
			if( direction == 0 )	//左出生，方向为4
			{
				direction = 4;
				x = -100;
			}
			else		
			{				//右出生，方向为3
				direction = 3;
				x = MainInterface.width+50;
			}
		}
		else	//鱼是上下出生
		{
			x = rand.nextInt(MainInterface.width-200)+150;	//x的范围
			if( direction == 0 )	//上出生，方向向下
			{
				direction = 2;
				y = -200;
			}
			else
			{
				direction = 1;		//方向向上
				y = MainInterface.height+200;
			}
		}
		slant();	//使鱼能够斜着游
	}
	public void slant()	//鱼可能斜着走，避免单一化
	{
		this.diviation = 0;
		Random rand = new Random();
		int t = rand.nextInt(2)+1;
		if( t == 1 )	//有1/2的概率让鱼斜着走
		{
			int k = rand.nextInt(2);
			if( k == 0 )
				this.diviation = -1;	//往左偏
			else this.diviation = 1;	//往右偏
		}
	}
	public void move()	//走一步
	{
		if( this.direction == 1 )
		{
			if( this.diviation == -1 )	//上左
			{
				x-=speed;
				y-=speed;
			}
			else if( this.diviation == 0 )
				y-=speed;
			else if( this.diviation == 1 )	//上右
			{
				x += speed;
				y -= speed;
			}
		}
		else if( this.direction == 2 )
		{
			y += speed;
			if( this.diviation == -1 )	//右下方游
				x += speed;
			else if( this.diviation == 1 )	//左下游
				x -= speed;
			
		}
		else if( this.direction == 3 )
		{
			x -= speed;
			if( this.diviation == -1 )
				y+=speed;
			else if( this.diviation == 1 )
				y -= speed;
		}
		else if( this.direction == 4 )
		{
			x += speed;
			if( this.diviation == -1 )
				y-=speed;
			else if( this.diviation == 1 )
				y+=speed;
		}
	}
	public void run()	//鱼自动游的方法 
	{
		// TODO Auto-generated method stub
		while( true )
		{
			try 
			{
				Thread.sleep(100);
				move();				//每隔0.1s走一步
				if( outBound() )	//如果出界了，就死了
					alive = false;
				
				if( alive )	//如果是活的
				{
					status--;
					if( status < 0 )	//status还原，从而进行循环
					{
						status = start_status;	
					}
				}
				else	//鱼死了	
					break;
				
				//System.out.println("fish1 坐标 "+x+" "+y);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public boolean outBound()	//检查鱼是否出界
	{
		if( x < fishWidth*(-1) || x > MainInterface.width+fishWidth )
			return true;
		if( y < fishHeight*(-1) || y > MainInterface.height+fishHeight )
			return true;
		return false;
	}
	
	/*
	 * 画鱼的图片，处理有10张图片的类，不足10的覆盖父类，从而实现
	 * 另外，这里没有处理往右游的鱼，因为缺少图片
	 */
	public void drawMyImage( Graphics2D g )	//画一个图片,可能倾斜
	{
		int mx,my;
		mx = x+this.fishWidth/2;
		my = y+this.fishHeight/2;
		
		Image image;
		if( alive )
		{
			int t = status/multi;	//判断显示哪一张图片
			if( t > 10 || t <= 0 )	//避免越界
				t = 10;
			
			image = liveicon[t].getImage();
		}
		else
		{
			if( ( deadstatus&1 )==1 )
				image = deadicon[1].getImage();
			else image = deadicon[2].getImage();
		}
		
		if( direction == 1 ) 	//方向往上需要旋转90度
		{
			g.rotate(Math.toRadians(90), mx, my);	//顺时针旋转90度
			dealDiviation(g, image);  	//判断是否有偏斜，顺便画一下
			g.rotate(Math.toRadians(-90), mx, my);
		}
		else if( direction == 2 )	//方向往下
		{
			g.rotate(Math.toRadians(-90), mx, my);	//逆时针旋转90度
			dealDiviation(g, image);			//判断是否有偏斜，顺便画一下
			g.rotate(Math.toRadians(90), mx, my);
		}
		else if( direction == 3 )
		{
			dealDiviation(g, image);
		}
		/*
		else if( direction == 4 )	//方向往右需要水平翻转
		{
			image = ImgRotate.imageMisro(image, ImgRotate.Left_Right_Reverse);
			g.drawImage(image, this.x, this.y,null);
		}
		*/
		
		
	}
	public void avoidRight()	//避免往右。。。由于图片的原因
	{
		if( this.direction == 4 )
		{
			Random rand = new Random();
			int t = rand.nextInt(3)+1;
			this.direction = t;
		}
	}
	protected void dealDiviation( Graphics2D g,Image image )
	{
		int mx,my;
		mx = x+this.fishWidth/2;
		my = y+this.fishHeight/2;	//使用图片的中点
		
		if( this.diviation == -1 )	//往左偏
		{
			g.rotate(Math.toRadians(-45), mx, my);
			g.drawImage(image, x, y, null);
			g.rotate(Math.toRadians(45), mx, my);
		}
		else if( this.diviation == 1 )	//往右偏
		{
			g.rotate(Math.toRadians(45), mx, my);
			g.drawImage(image, x, y, null);
			g.rotate(Math.toRadians(-45), mx, my);
		}
		else if( this.diviation == 0 )
			g.drawImage(image, x, y, null);
	}
	
	public boolean randDead()			//渔网击中后判断死亡的方法
	{
		int t;
		if( this.fishWidth > 100 )	//大鱼
			t = 6;
		else if( this.fishWidth > 50 )	//中等
			t = 4;
		else t = 3;
		Random rand = new Random();
		int temp = rand.nextInt(t);
		if( temp == 0 )		
		{
			//如果0就表示死亡
			this.alive = false;
			return true;
		}
		return false;
	}
}

