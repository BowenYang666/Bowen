package mypackage;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class getImage 
{
	public static Image get( String path )
	{
		Image image = null;
		try
		{
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return image;
		}
	}
}
