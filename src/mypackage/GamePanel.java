package mypackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * 
 * 功能：进行游戏的面板
 */
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

public class GamePanel extends JPanel implements Runnable,MouseMotionListener,MouseListener
{
	Vector<Fish> fish;
	Vector<Bullet> bullet;
	Vector<Net> net;
	ImageIcon bg = new ImageIcon("images\\bg.jpg");
	ImageIcon close = null;		//close的大小为40*40
	ImageIcon close1 = new ImageIcon("FunctionImages\\CloseNormal.png");
	ImageIcon close2 = new ImageIcon("FunctionImages\\CloseSelected.png");
	ImageIcon back = new ImageIcon("FunctionImages\\back.png");
	ImageIcon canonBar = new ImageIcon("FunctionImages\\canonBar.png");
	
	ImageIcon plus = new ImageIcon("FunctionImages\\plus.png");
	ImageIcon subtract = new ImageIcon("FunctionImages\\subtract.png");
	//static int canonx,canony;
	static int canonWidth = 42;
	static int maxFishSize = 30;		//屏幕里面最多20条鱼	
	static int fishKind = 11;	//鱼的种类
	static int startx = MainInterface.width/2-250;	//放炮台架的两个坐标
	static int starty = MainInterface.height-168;
	static int canonx = startx+250+5;	
	static int canony=starty-10;		//炮台的两个坐标
	Canon canon;
	int number[]= {0,1,2,3,4,5,6,7,8,9,13,14};
	long lastShot = -10000;	//计算设计间隔
	int armType = 1;	//武器等级
	int score;
	boolean over = false;
	public GamePanel()
	{
		score = 0;
		canon = new Canon(this.armType);	//创建一个1级大炮
		close = close1;
		fish = new Vector<Fish>();
		bullet = new Vector<Bullet>();
		net = new Vector<Net>();
				
		Fish f = this.GenerateFish();	//先产生一条鱼
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
		//画背景图片
		
		
	}
	*/
	public void paint( Graphics g )
	{
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(bg.getImage(), 0, 0, MainInterface.width,MainInterface.height,null);
		//画背景图片
		
		for( int i = 0 ; i < fish.size() ; i++ )	//画鱼
		{
			Fish f = fish.get(i);
			if( f.alive )
				f.drawMyImage(g2);	
			else	//鱼死了做特殊判断
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
		
		//画炮台架,规定炮台条大小为 500*50"
		//System.out.println("canon xy "+canonx+"  "+canony+"  "+canon.rotatex+"  "+canon.rotatey);
		//炮塔架
		g.drawImage(canonBar.getImage(), startx, starty,500,50, null);
		
		
		//炮筒		炮筒的大小为42*62
		
		//System.out.println(" angle :"+Math.toDegrees(canon.angle) );
		//g2.rotate(Math.toRadians(30), canon.rotatex, canon.rotatey);
		g2.rotate(canon.angle, canon.rotatex, canon.rotatey);//大炮随着鼠标指针旋转
		g.drawImage(Canon.image,canonx, canony, null);
		g2.rotate(canon.angle*(-1), canon.rotatex, canon.rotatey);
		//g2.rotate(Math.toRadians(-30), canon.rotatex, canon.rotatey);
		
		
		//加减符号
		g2.drawImage(subtract.getImage(), canonx-50, starty+10,40,40, null);
		g2.drawImage(plus.getImage(), canonx+50, starty+10,40,40, null);
		
		drawBullet(g2);	//画子弹
		drawNet(g2);	//击中后的渔网效果
		
		g.setColor( Color.CYAN );
		g.setFont( new Font("楷体",Font.BOLD,25) );
		//画分数
		g.drawString("得分:", 30, 50);
		g.drawString(String.valueOf(this.score), 100,50);
		//画返回按钮
		g2.drawImage(back.getImage(), MainInterface.width-55, 15,null);
		//画结束游戏按钮
		g2.drawImage(close.getImage(), MainInterface.width-60, 60, null);
	}
	/*
	 * 生成一条鱼
	 */
	
	public void drawNet( Graphics2D g )
	{
		for( int i = 0 ; i < net.size() ; i++ )
		{
			Net n = net.get(i);
			if( n.alive == false )	//如果死了，删除
			{
				net.remove(i);
				i--;
				continue;
			}
			//渔网还活着，画一下
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
				g2.rotate(b.angle*(-1),b.x, b.y);	//转回去
			}
			else
			{
				bullet.remove(i);	//子弹死亡，删除子弹
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
				Thread.sleep(30);	//每隔30ms 自动刷新
				cnt++;
				this.checkDash(); 	//检查是否有渔网生成
				if( cnt == 30 )
				{							//每隔1.5s生成2条鱼
					cnt = 1;
					if( this.fish.size() < this.maxFishSize )//保证不能有太多鱼
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
		if( my < canon.rotatey )			//在支点上方旋转
			canon.setAngle(mx, my); 	//根据鼠标的位置实时更新角度
		
		if( mx >= MainInterface.width-53 && mx <= MainInterface.width-15 
			&& my >= 60	&& my <= 90 )		//如果鼠标进入结束游戏按钮
		{
			//if( close.equals(close1) )	//结束游戏按钮变化
			close = close2;
		}
		else	//鼠标在其他地方
		{
			if( close.equals(close2) )
				close = close1;
		}
		
		//炮筒随鼠标的位置移动
		
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		//点一下发射一颗子弹
		long t = e.getWhen();
		int mx = e.getX();
		int my = e.getY();
		
		//首先判断必须在框框里面
		if( mx >= 0 && mx <= MainInterface.width && my >= 0 && my <= MainInterface.height )
			if( t-lastShot >= 300 )	//子弹发射间隔最小 0.3s
			{
				lastShot = t;
				Bullet b = new Bullet(canon);	//创建一颗子弹
				Thread thread = new Thread(b);	
				thread.start(); 				//启动线程
				bullet.add(b);
			}
		
		if( mx >= MainInterface.width-53 && mx <= MainInterface.width-15 
				&& my >= 60	&& my <= 90 )		//如果鼠标进入结束游戏按钮
			{
				System.exit(1);
			}
		
		if( mx >= MainInterface.width-50 && mx <= MainInterface.width 
				&& my >= 10	&& my <= 50 )		//如果鼠标进入结束游戏按钮
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
				if( b.checkDash(f) )	//如果碰撞
				{
					Net n = new Net(b.x,b.y);
					net.add(n);
					b.alive = false;
					if( f.randDead() )	//判断鱼是否被打死
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
