package mypackage;

import javax.swing.ImageIcon;

/*
 * ����ʵ���˾�����㣬�����в�ͬ��ͼƬ�����̳���Fish��
 * 
 */
public class SpecificFish 
{
}

class Fish1 extends Fish implements Runnable
{
	
	
	public Fish1()
	{
		
		super();
		super.avoidRight();
		this.score = 1;
		this.fishWidth = 48;	//ͼƬ����������ô��
		this.fishHeight = 20;
		multi = 1;		//����
		speed = 3;		//С���εıȽϿ�
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish01_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish01_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish01_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish01_catch_02.png");
		
	}
	

	
}
class Fish2 extends Fish implements Runnable
{
	public Fish2()
	{
		super();
		super.avoidRight();
		this.score = 2;
		this.fishWidth = 55;	//ͼƬ����������ô��
		this.fishHeight = 21;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish02_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish02_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish02_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish02_catch_02.png");
		
	}
}
class Fish3 extends Fish implements Runnable
{
	public Fish3()
	{
		super();
		super.avoidRight();
		this.score = 2;
		this.fishWidth = 73;	//ͼƬ����������ô��
		this.fishHeight = 25;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish03_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish03_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish03_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish03_catch_02.png");
		
	}
}
class Fish4 extends Fish implements Runnable
{
	public Fish4()
	{
		super();
		super.avoidRight();
		this.score = 4;
		this.fishWidth = 93;	//ͼƬ����������ô��
		this.fishHeight = 32;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish04_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish04_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish04_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish04_catch_02.png");
		
	}
}
class Fish5 extends Fish implements Runnable
{
	public Fish5()
	{
		super();
		super.avoidRight();
		this.score = 5;
		this.fishWidth = 58;	//ͼƬ����������ô��
		this.fishHeight = 28;
		multi = 1;		
		speed = 3;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish05_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish05_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish05_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish05_catch_02.png");
		
	}
}
class Fish6 extends Fish implements Runnable
{
	public Fish6()
	{
		super();
		super.avoidRight();
		this.score = 8;
		this.fishWidth = 93;	//ͼƬ����������ô��
		this.fishHeight = 34;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish06_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish06_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish06_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish06_catch_02.png");
		
	}
}
class Fish7 extends Fish implements Runnable
{
	public Fish7()
	{
		super();
		super.avoidRight();
		this.score = 10;
		this.fishWidth = 85;	//ͼƬ����������ô��
		this.fishHeight = 38;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish07_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish07_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish07_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish07_catch_02.png");
		
	}
}
class Fish8 extends Fish implements Runnable
{
	public Fish8()
	{
		super();
		super.avoidRight();
		this.score = 10;
		this.fishWidth = 85;	//ͼƬ����������ô��
		this.fishHeight = 75;
		multi = 1;		
		speed = 1;		//�ڹ��εķǳ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish08_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish08_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish08_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish08_catch_02.png");
		
	}
}
class Fish9 extends Fish implements Runnable
{
	public Fish9()
	{
		super();
		super.avoidRight();
		this.score = 20;
		this.fishWidth = 160;	//ͼƬ����������ô��
		this.fishHeight = 55;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish09_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish09_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish09_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish09_catch_02.png");
		
	}
}
/*
 * Fish13�����㣬�����е��
 */
class Fish13 extends Fish implements Runnable
{
	public Fish13()
	{
		super();
		super.avoidRight();
		this.score = 50;
		this.specialDeal(); 	//����̫����ת����ܳ�����
		this.fishWidth = 300;	//ͼƬ����������ô��
		this.fishHeight = 100;
		multi = 1;		
		speed = 3;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish13_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish13_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish13_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish13_catch_02.png");
		
	}
	private void specialDeal()		//����ʼ�����һ��
	{
		
	}
}
class Fish14 extends Fish implements Runnable
{
	public Fish14()
	{
		super();
		super.avoidRight();
		this.score = 40;
		this.fishWidth = 92;	//ͼƬ����������ô��
		this.fishHeight = 55;
		multi = 1;		
		speed = 2;		//���ε�һ���
		start_status = 10*multi;	//һ��ʼ��״̬10
		status = start_status;	
		for( int i = 1 ; i <= 9 ; i++ )
			liveicon[i] = new ImageIcon("images\\fish14_0"+String.valueOf(i)+".png");
		liveicon[10] = new ImageIcon("images\\fish14_10.png");		//ͼƬ10�Ƚ�����
		
		deadicon[1] = new ImageIcon("images\\fish14_catch_01.png");
		deadicon[2] = new ImageIcon("images\\fish14_catch_02.png");
		
	}
}
