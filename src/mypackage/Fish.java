package mypackage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.ImageIcon;

/*
 * ���ܣ���������㣬ʵ�ָ����������
 * 
 */
public class Fish implements Runnable
{
	int x,y;			//�������
	int speed = 2;			//���ε��ٶ�
	int type;			//���ҳ������������³���
	int direction;		//���εķ���
	int status,multi = 3;			//����״̬��ͼƬ
	int start_status = 30;
	int deadstatus = 30;
	boolean alive = true;		//�㱻ץ�������
	int diviation = 0;			//0��ʾ�㲻ƫת��-1��ʾ����ƫ��1��ʾ����ƫ,��-1��ʾ��ʱ�룬1��ʾ˳
	int fishWidth = 40,fishHeight = 30;
	static int d1[]= {0,0,0,-1,1},		//�������飬�±��1��ʼ��0λ����0����
			   d2[]= {0,-1,1,0,0};
	int score;
	
	ImageIcon liveicon[] = new ImageIcon[15];
	ImageIcon deadicon[] = new ImageIcon[5];
	
	
	public Fish()		//����һ���㣬λ���������
	{
		score = 1;
		randGenerate();
	}
	private void randGenerate()		//������ɳ����ĵط�
	{
		Random rand = new Random();
		type = rand.nextInt(2);
		direction = rand.nextInt(2);
		
		if( type == 0 )	//�������ҳ���
		{
			y = rand.nextInt(MainInterface.height-200)+100;	//y�ķ�Χ
			if( direction == 0 )	//�����������Ϊ4
			{
				direction = 4;
				x = -100;
			}
			else		
			{				//�ҳ���������Ϊ3
				direction = 3;
				x = MainInterface.width+50;
			}
		}
		else	//�������³���
		{
			x = rand.nextInt(MainInterface.width-200)+150;	//x�ķ�Χ
			if( direction == 0 )	//�ϳ�������������
			{
				direction = 2;
				y = -200;
			}
			else
			{
				direction = 1;		//��������
				y = MainInterface.height+200;
			}
		}
		slant();	//ʹ���ܹ�б����
	}
	public void slant()	//�����б���ߣ����ⵥһ��
	{
		this.diviation = 0;
		Random rand = new Random();
		int t = rand.nextInt(2)+1;
		if( t == 1 )	//��1/2�ĸ�������б����
		{
			int k = rand.nextInt(2);
			if( k == 0 )
				this.diviation = -1;	//����ƫ
			else this.diviation = 1;	//����ƫ
		}
	}
	public void move()	//��һ��
	{
		if( this.direction == 1 )
		{
			if( this.diviation == -1 )	//����
			{
				x-=speed;
				y-=speed;
			}
			else if( this.diviation == 0 )
				y-=speed;
			else if( this.diviation == 1 )	//����
			{
				x += speed;
				y -= speed;
			}
		}
		else if( this.direction == 2 )
		{
			y += speed;
			if( this.diviation == -1 )	//���·���
				x += speed;
			else if( this.diviation == 1 )	//������
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
	public void run()	//���Զ��εķ��� 
	{
		// TODO Auto-generated method stub
		while( true )
		{
			try 
			{
				Thread.sleep(100);
				move();				//ÿ��0.1s��һ��
				if( outBound() )	//��������ˣ�������
					alive = false;
				
				if( alive )	//����ǻ��
				{
					status--;
					if( status < 0 )	//status��ԭ���Ӷ�����ѭ��
					{
						status = start_status;	
					}
				}
				else	//������	
					break;
				
				//System.out.println("fish1 ���� "+x+" "+y);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public boolean outBound()	//������Ƿ����
	{
		if( x < fishWidth*(-1) || x > MainInterface.width+fishWidth )
			return true;
		if( y < fishHeight*(-1) || y > MainInterface.height+fishHeight )
			return true;
		return false;
	}
	
	/*
	 * �����ͼƬ��������10��ͼƬ���࣬����10�ĸ��Ǹ��࣬�Ӷ�ʵ��
	 * ���⣬����û�д��������ε��㣬��Ϊȱ��ͼƬ
	 */
	public void drawMyImage( Graphics2D g )	//��һ��ͼƬ,������б
	{
		int mx,my;
		mx = x+this.fishWidth/2;
		my = y+this.fishHeight/2;
		
		Image image;
		if( alive )
		{
			int t = status/multi;	//�ж���ʾ��һ��ͼƬ
			if( t > 10 || t <= 0 )	//����Խ��
				t = 10;
			
			image = liveicon[t].getImage();
		}
		else
		{
			if( ( deadstatus&1 )==1 )
				image = deadicon[1].getImage();
			else image = deadicon[2].getImage();
		}
		
		if( direction == 1 ) 	//����������Ҫ��ת90��
		{
			g.rotate(Math.toRadians(90), mx, my);	//˳ʱ����ת90��
			dealDiviation(g, image);  	//�ж��Ƿ���ƫб��˳�㻭һ��
			g.rotate(Math.toRadians(-90), mx, my);
		}
		else if( direction == 2 )	//��������
		{
			g.rotate(Math.toRadians(-90), mx, my);	//��ʱ����ת90��
			dealDiviation(g, image);			//�ж��Ƿ���ƫб��˳�㻭һ��
			g.rotate(Math.toRadians(90), mx, my);
		}
		else if( direction == 3 )
		{
			dealDiviation(g, image);
		}
		/*
		else if( direction == 4 )	//����������Ҫˮƽ��ת
		{
			image = ImgRotate.imageMisro(image, ImgRotate.Left_Right_Reverse);
			g.drawImage(image, this.x, this.y,null);
		}
		*/
		
		
	}
	public void avoidRight()	//�������ҡ���������ͼƬ��ԭ��
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
		my = y+this.fishHeight/2;	//ʹ��ͼƬ���е�
		
		if( this.diviation == -1 )	//����ƫ
		{
			g.rotate(Math.toRadians(-45), mx, my);
			g.drawImage(image, x, y, null);
			g.rotate(Math.toRadians(45), mx, my);
		}
		else if( this.diviation == 1 )	//����ƫ
		{
			g.rotate(Math.toRadians(45), mx, my);
			g.drawImage(image, x, y, null);
			g.rotate(Math.toRadians(-45), mx, my);
		}
		else if( this.diviation == 0 )
			g.drawImage(image, x, y, null);
	}
	
	public boolean randDead()			//�������к��ж������ķ���
	{
		int t;
		if( this.fishWidth > 100 )	//����
			t = 6;
		else if( this.fishWidth > 50 )	//�е�
			t = 4;
		else t = 3;
		Random rand = new Random();
		int temp = rand.nextInt(t);
		if( temp == 0 )		
		{
			//���0�ͱ�ʾ����
			this.alive = false;
			return true;
		}
		return false;
	}
}

