package mypackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * 程序的主界面
 * 
 */

public class MainInterface extends JFrame implements ActionListener,Runnable
{
	static int width = 800,height = 600;	//正真的大小是屏幕的 3/4
	static int screenWidth,screenHeight;
	Image bg = null;	//背景图片
	Mypanel mainpanel = null;
	GamePanel gamepanel = null;
	JButton button[] = new JButton[6];
	public MainInterface( )
	{
		bg = getImage.get("images/welcome.jpg");


		mainpanel = new Mypanel(bg);
		mainpanel.setLayout(null);
		
		gamepanel = new GamePanel();	//打渔的面板
		Thread thread = new Thread(gamepanel);
		thread.start();
		gamepanel.addMouseMotionListener(gamepanel);
		gamepanel.addMouseListener(gamepanel);
		
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;	//得到屏幕的大小，以便设置全屏
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		width = 3*screenWidth/5;		//默认是小窗显示，width和height
		height = 2*screenHeight/3;
		//System.out.println(width);
		buttonInitialize();		//初始化button,并且设定button位置
		
		
		//this.add(gamepanel);
		//gamepanel.setVisible(false);
		this.add(mainpanel);
		
		
		
		this.setTitle("捕鱼达人");
		this.setSize(this.width,this.height);	//
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void buttonInitialize()
	{
		button[1] = new JButton("开始游戏");
		button[2] = new JButton("游戏设置");
		button[3] = new JButton("退出游戏");
		button[1].setFont( new Font("楷体",Font.BOLD,20) );
		button[2].setFont( new Font("楷体",Font.BOLD,20) );
		button[3].setFont( new Font("楷体",Font.BOLD,20) );
		button[1].addActionListener(this);
		
		int button_width = width/5;
		int button_height = height/10;
		int y = height/3;	//开始的y是height的1/3
		int x = width/2-button_width/2;	//button的x坐标
		int gap = height/6;				//gap是1/12
//		System.out.println("x y :"+x+"  "+y );
//		System.out.println(" width  height "+width+"  "+height);
//		System.out.println("gap "+gap);
//		System.out.println("button width height "+button_width+"  "+button_height );
		for( int i = 1 ; i <= 3 ; i++ )	//将button 添加到panel中,并且设置位置
		{
			button[i].setSize(button_width,button_height);
			mainpanel.add(button[i]);
			button[i].setBounds(x,y,button_width,button_height);
			y += gap;
		}
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MainInterface f = new MainInterface();
		Thread t = new Thread(f);
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if( e.getSource().equals(button[1] ) )
		{
			//System.out.println("333");
			mainpanel.setVisible(false);
			this.add(gamepanel);
		}
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while( true )
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if( this.gamepanel.over == true )
			{
				this.dispose();
				MainInterface f = new MainInterface();
				Thread t = new Thread(f);
				t.start();
				break;
			}
		}
	}

}
