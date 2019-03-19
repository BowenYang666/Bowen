package mypackage;
/*
 * ªÊ÷∆±≥æ∞Õº∆¨µƒÃÿ ‚panel
 * 
 */
import java.awt.*;

import javax.swing.*;

public class Mypanel extends JPanel
{
	ImageIcon icon = null;
	Image image = null;
	public Mypanel( ImageIcon icon )
	{
		this.icon = icon;
	}
	public Mypanel( Image image )
	{
		this.image = image;
	}
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		if( icon != null ) 
			g.drawImage(icon.getImage(),0,0,null);
		else g.drawImage(image,0,0,null);
	}
	
}
