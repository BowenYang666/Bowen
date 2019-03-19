package mypackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Author Mythos_Q
 *
 * Time 2011-07-20
 *
 * Description ͼƬ��ת����ת����
 */
public class ImgRotate 
{
	static int Up_Down_Reverse = 0;
	static int Left_Right_Reverse = 1;
     /**
     * ��תͼƬΪָ���Ƕ�
     * 
     * @param bufferedimage
     *            Ŀ��ͼ��
     * @param degree
     *            ��ת�Ƕ�
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,final int degree){
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2 + (w>h?(w-h)/2:(h-w)/2));
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * ��תͼƬΪָ���Ƕ�
     *
     * @param file
     *            Ŀ��ͼ��
     * @param degree
     *            ��ת�Ƕ�(90,180,270)
     * @return
     */
    public static File rotateImage(File file,int degree) throws Exception{
        if(degree==90) return rotateImage90(file);
        if(degree==180) return rotateImage180(file);
        if(degree==270) return rotateImage270(file);
        return null;
    }
    public static Image rotateImage(Image Image,int degree) 
    {
        if(degree==90) 
        	return rotateImage90(Image);
        if(degree==180)
        	return rotateImage180(Image);
        if(degree==270)
        	return rotateImage270(Image);
        return null;
    }
    
    private static Image rotateImage90(Image image) 
    {
    	BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = 
        		(img = new BufferedImage(h, w, type) ).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w-h)/2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        
        return BufferedImageToImage(img);
		
	}

	//��ת90��
    public static File rotateImage90(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = 
        		(img = new BufferedImage(h, w, type) ).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), w / 2, h / 2 + (w-h)/2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }

    //��ת90��
    public static File rotateImage270(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(90), w / 2 - (w-h)/2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }
    
    public static Image rotateImage270(Image image)
    {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(90), w / 2 - (w-h)/2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        
        return BufferedImageToImage(img);
    }
    //��ת
    public static File rotateImage180(File file) throws Exception
    {
        BufferedImage bufferedimage = ImageIO.read(file);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(180), w / 2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        ImageIO.write(img,"jpg",file);
        return file;
    }
    public static Image rotateImage180(Image image)
    {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(180), w / 2, h / 2 );
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return BufferedImageToImage(img);
    }
    /***
     * ͼƬ������
     * @param file
     * @param FX 0 Ϊ���·�ת 1 Ϊ���ҷ�ת
     * @return
     */
    public static void imageMisro(File file,int FX)
    {
        try 
        {
            BufferedImage bufferedimage = ImageIO.read(file);
            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();
            
            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            if (FX == 0) {
                for (int i = 0, a = h - 1; i < h; i++, a--) {
                    for (int j = 0; j < w; j++) {
                        tmps[j][a] = datas[j][i];
                    }
                }
            } else if (FX == 1) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0, b = w - 1; j < w; j++, b--) {
                        tmps[b][i] = datas[j][i];
                    }
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }
           
            ImageIO.write(bufferedimage, "jpg", file);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 
     * ����������image�ͷ�ʽ�����ط�ת����image
     * type = 0 ��ʾ���·�ת��type = 1 ��ʾ���ҷ�ת
     */
    public static Image imageMisro(Image image,int type )
    {
        try 
        {
        							//�õ����Լ�д�ķ���
        	BufferedImage  bufferedimage = ImageToBufferedImage(image); 
             
            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();
            
            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            if (type == 0) {
                for (int i = 0, a = h - 1; i < h; i++, a--) {
                    for (int j = 0; j < w; j++) {
                        tmps[j][a] = datas[j][i];
                    }
                }
            } else if (type == 1) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0, b = w - 1; j < w; j++, b--) {
                        tmps[b][i] = datas[j][i];
                    }
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }
           
            
            Image newImage = (Image)bufferedimage;
            return newImage;
            //ImageIO.write(bufferedimage, "jpg", file);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
    
    //Imageת����BufferedImage
    public static BufferedImage ImageToBufferedImage( Image image )
    {
    	BufferedImage  bufferedimage = new BufferedImage
   			 (image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);   
        Graphics g =  bufferedimage.createGraphics();   
        g.drawImage(image, 0, 0, null);	//�����ҿ��ܻ������ʣ��ƺ�û�ж�bufferedimage��ɶ
        g.dispose(); 					//��������ȷ�ģ�g����drawImage���Զ�������
        return bufferedimage;
    }
    //BufferedImage ת����Image����
    public static Image BufferedImageToImage( BufferedImage b )
    {
    	return (Image)b;
    }
}
