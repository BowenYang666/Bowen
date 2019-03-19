package mypackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * 
 * ���ܣ�������Ϸ�����
 */
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class GamePanel extends JPanel implements Runnable,MouseMotionListener,MouseListener
{
	Vector<Fish> fish;
	Vector<Bullet> bullet;
	Vector<Net> net;
	ImageIcon bg = new ImageIcon("images\\bg.jpg");
	ImageIcon close = null;		//close�Ĵ�СΪ40*40
	ImageIcon close1 = new ImageIcon("FunctionImages\\CloseNormal.png");
	ImageIcon close2 = new ImageIcon("FunctionImages\\CloseSelected.png");
	ImageIcon back = new ImageIcon("FunctionImages\\back.png");
	ImageIcon canonBar = new ImageIcon("FunctionImages\\canonBar.png");
	
	ImageIcon plus = new ImageIcon("FunctionImages\\plus.png");
	ImageIcon subtract = new ImageIcon("FunctionImages\\subtract.png");
	//static int canonx,canony;
	static int canonWidth = 42;
	static int maxFishSize = 30;		//��Ļ�������20����	
	static int fishKind = 11;	//�������
	static int startx = MainInterface.width/2-250;	//����̨�ܵ���������
	static int starty = MainInterface.height-168;
	static int canonx = startx+250+5;	
	static int canony=starty-10;		//��̨����������
	Canon canon;
	int number[]= {0,1,2,3,4,5,6,7,8,9,13,14};
	long lastShot = -10000;	//������Ƽ��
	int armType = 1;	//�����ȼ�
	int score;
	boolean over = false;
	public GamePanel()
	{
		score = 0;
		canon = new Canon(this.armType);	//����һ��1������
		close = close1;
		fish = new Vector<Fish>();
		bullet = new Vector<Bullet>();
		net = new Vector<Net>();
				
		Fish f = this.GenerateFish();	//�Ȳ���һ����
		fish.add(f);
		
		/*
		Fish f = new Fish9();
		f.direction = 2;
		f.diviation = 0;
		f.x = 100;
		f.y = 0;
		Thread t = new Thread(f);
		t.start();
		
		fish.add(f);
		*/
	}
	/*
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, MainInterface.width,MainInterface.height,null);
		//������ͼƬ
		
		
	}
	*/
	public void paint( Graphics g )
	{
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(bg.getImage(), 0, 0, MainInterface.width,MainInterface.height,null);
		//������ͼƬ
		
		for( int i = 0 ; i < fish.size() ; i++ )	//����
		{
			Fish f = fish.get(i);
			if( f.alive )
				f.drawMyImage(g2);	
			else	//�������������ж�
			{
				f.drawMyImage(g2);
				f.deadstatus--;
				if( f.deadstatus < 0 )
				{
					fish.remove(i);
					i--;
				}
			}
		}
		
		//����̨��,�涨��̨����СΪ 500*50"
		//System.out.println("canon xy "+canonx+"  "+canony+"  "+canon.rotatex+"  "+canon.rotatey);
		//������
		g.drawImage(canonBar.getImage(), startx, starty,500,50, null);
		
		
		//��Ͳ		��Ͳ�Ĵ�СΪ42*62
		
		//System.out.println(" angle :"+Math.toDegrees(canon.angle) );
		//g2.rotate(Math.toRadians(30), canon.rotatex, canon.rotatey);
		g2.rotate(canon.angle, canon.rotatex, canon.rotatey);//�����������ָ����ת
		g.drawImage(Canon.image,canonx, canony, null);
		g2.rotate(canon.angle*(-1), canon.rotatex, canon.rotatey);
		//g2.rotate(Math.toRadians(-30), canon.rotatex, canon.rotatey);
		
		
		//�Ӽ�����
		g2.drawImage(subtract.getImage(), canonx-50, starty+10,40,40, null);
		g2.drawImage(plus.getImage(), canonx+50, starty+10,40,40, null);
		
		drawBullet(g2);	//���ӵ�
		drawNet(g2);	//���к������Ч��
		
		g.setColor( Color.CYAN );
		g.setFont( new Font("����",Font.BOLD,25) );
		//������
		g.drawString("�÷�:", 30, 50);
		g.drawString(String.valueOf(this.score), 100,50);
		//�����ذ�ť
		g2.drawImage(back.getImage(), MainInterface.width-55, 15,null);
		//��������Ϸ��ť
		g2.drawImage(close.getImage(), MainInterface.width-60, 60, null);
	}
	/*
	 * ����һ����
	 */
	
	public void drawNet( Graphics2D g )
	{
		for( int i = 0 ; i < net.size() ; i++ )
		{
			Net n = net.get(i);
			if( n.alive == false )	//������ˣ�ɾ��
			{
				net.remove(i);
				i--;
				continue;
			}
			//���������ţ���һ��
			int t = (n.life-1)/n.multi+1;
			n.life--;
			if( n.life <= 0 )
				n.alive = false;
			if( t <= 0 || t > 8 )
				t = 8;
			int tx = n.x-n.image[t].getWidth(null)/2;
			int ty = n.y-n.image[t].getHeight(null)/2;
			g.drawImage(n.image[t], tx, ty, null);
		}
		
	}
	public void drawBullet( Graphics2D g2 )
	{
		
		
		for( int i = 0 ; i < bullet.size() ; i++ )
		{
			Bullet b = bullet.get(i);
			if( b.alive )
			{
				g2.rotate(b.angle, b.x, b.y);
				g2.drawImage(b.image,b.x,b.y,null);	
				g2.rotate(b.angle*(-1),b.x, b.y);	//ת��ȥ
			}
			else
			{
				bullet.remove(i);	//�ӵ�������ɾ���ӵ�
				i--;
			}
		}
	}
	public Fish GenerateFish()
	{
		Random rand = new Random();
		int type = rand.nextInt(fishKind)+1;
		type = number[type];
		Fish f = null;
		
		
		if( type == 1  )
			f = new Fish1();
		else if( type == 2 )
			f = new Fish2();
		else if( type == 3 )
			f = new Fish3();
		else if( type == 4 )
			f = new Fish4();
		else if( type == 5 )
			f = new Fish5();
		else if( type == 6 )
			f = new Fish6();
		else if( type == 7 )
			f = new Fish7();
		else if( type == 8 )
			f = new Fish8();
		else if( type == 9 )
			f = new Fish9();
		else if( type == 13 )
			f = new Fish13();
		else f = new Fish14();
		
		
		
		Thread thread = new Thread(f);
		thread.start();
		return f;
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		int cnt = 1;
		while( true )
		{
			try 
			{
				Thread.sleep(30);	//ÿ��30ms �Զ�ˢ��
				cnt++;
				this.checkDash(); 	//����Ƿ�����������
				if( cnt == 30 )
				{							//ÿ��1.5s����2����
					cnt = 1;
					if( this.fish.size() < this.maxFishSize )//��֤������̫����
					{
						for( int i = 1 ; i <= 2 ; i++ )
						{
							Fish f = this.GenerateFish();
							this.fish.add(f);
						}
					}
				}
				
				
				this.repaint();	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) 
	{
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		if( my < canon.rotatey )			//��֧���Ϸ���ת
			canon.setAngle(mx, my); 	//��������λ��ʵʱ���½Ƕ�
		
		if( mx >= MainInterface.width-53 && mx <= MainInterface.width-15 
			&& my >= 60	&& my <= 90 )		//��������������Ϸ��ť
		{
			//if( close.equals(close1) )	//������Ϸ��ť�仯
			close = close2;
		}
		else	//����������ط�
		{
			if( close.equals(close2) )
				close = close1;
		}
		
		//��Ͳ������λ���ƶ�
		
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		//��һ�·���һ���ӵ�
		long t = e.getWhen();
		int mx = e.getX();
		int my = e.getY();
		
		//�����жϱ����ڿ������
		if( mx >= 0 && mx <= MainInterface.width && my >= 0 && my <= MainInterface.height )
			if( t-lastShot >= 300 )	//�ӵ���������С 0.3s
			{
				lastShot = t;
				Bullet b = new Bullet(canon);	//����һ���ӵ�
				Thread thread = new Thread(b);	
				thread.start(); 				//�����߳�
				bullet.add(b);
			}
		
		if( mx >= MainInterface.width-53 && mx <= MainInterface.width-15 
				&& my >= 60	&& my <= 90 )		//��������������Ϸ��ť
			{
				System.exit(1);
			}
		
		if( mx >= MainInterface.width-50 && mx <= MainInterface.width 
				&& my >= 10	&& my <= 50 )		//��������������Ϸ��ť
		{
			over = true;
		}
	}
	public void checkDash()
	{
		for( int i = 0 ; i < bullet.size() ; i++ )
		{
			Bullet b = bullet.get(i);
			if( b == null )
				continue;
			for( int j = 0 ; j < fish.size() ; j++ )
			{	
				Fish f = fish.get(j);
				if( f == null )
					continue;
				if( b.checkDash(f) )	//�����ײ
				{
					Net n = new Net(b.x,b.y);
					net.add(n);
					b.alive = false;
					if( f.randDead() )	//�ж����Ƿ񱻴���
						this.score+=f.score;
					break;
				}
			}
		}
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
