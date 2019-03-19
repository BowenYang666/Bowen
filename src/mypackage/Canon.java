package mypackage;

import java.awt.*;

import javax.swing.*;

public class Canon 
{
	static Image image = new ImageIcon("FunctionImages\\canon.png").getImage();
	static int canonWidth = 42,canonHeight = 62;
	int rotatex;
	int rotatey;
	int type;
	double angle;
	public Canon( int ArmType )
	{
		type = ArmType;
		angle = Math.toRadians(0);	//Ĭ����0�ȣ���ָ���м�
		rotatex = GamePanel.canonx+canonWidth/2;
		rotatey = GamePanel.canony+canonHeight/2;
	}
	public void setAngle( int x,int y )
	{
		double up,down;
		up = Math.abs( (double)rotatey-(double)y );
		down = (double)x-(double)rotatex;
		if( (int)down == 0 )
		{
			angle = 0;
			return;
		}
		angle = Math.atan(up/down);
		if( angle > 0 )
			angle = Math.PI/2-angle;
		else angle = Math.abs(angle)-Math.PI/2;	//����30��ƫ��ʱ��30-90����-60��
		//System.out.println("angle: "+angle);
	}
}
