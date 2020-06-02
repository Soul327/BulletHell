package Misc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Assets {
	public static BufferedImage[] assets=new BufferedImage[100];
	public static BufferedImage[][] ani=new BufferedImage[10][50];
	public static ArrayList<ArrayList<BufferedImage>> anim = new ArrayList<ArrayList<BufferedImage> >(); 
	
	public static void init(){
		File actual = new File("res/Top_Down_Survivor/rifle/idle/");
		//for( File f : actual.listFiles()) System.out.println( f.getName()+"\n  "+f.getAbsolutePath());
		for(int x=0;x<=19;x++)
			ani[0][x] = ImageLoader.loadImage("res/Top_Down_Survivor/rifle/idle/survivor-idle_rifle_"+x+".png");
		
		int x=0;
		for( File f : new File("res/Top_Down_Survivor/rifle/idle/").listFiles())
			ani[1][x++] = ImageLoader.loadImage(f.getPath());
		
		x=0;
		for( File f : new File("res/Top_Down_Survivor/rifle/shoot/").listFiles())
			ani[2][x++] = ImageLoader.loadImage(f.getPath());
			
		
		//127 93
		SpriteSheet sheet= new SpriteSheet(ImageLoader.loadImage("res/M484BulletCollection1.png"));
		assets[0] = sheet.crop(127, 93, 16, 16);
		assets[1] = sheet.crop(127, 132, 16, 16);
		//Grenades
		assets[ 2] = sheet.crop(173, 142, 11, 11);
		assets[ 3] = sheet.crop(188, 142, 11, 11);
		assets[ 4] = sheet.crop(203, 142, 11, 11);
		assets[ 5] = sheet.crop(218, 142, 11, 11);
		assets[ 6] = sheet.crop(233, 142, 11, 11);
		assets[ 7] = sheet.crop(248, 142, 11, 11);
		assets[ 8] = sheet.crop(263, 142, 11, 11);
		
		assets[ 9] = sheet.crop(173, 157, 11, 11);
		assets[10] = sheet.crop(188, 157, 11, 11);
		assets[11] = sheet.crop(203, 157, 11, 11);
		assets[12] = sheet.crop(218, 157, 11, 11);
		assets[13] = sheet.crop(233, 157, 11, 11);
		assets[14] = sheet.crop(248, 157, 11, 11);
		assets[15] = sheet.crop(263, 157, 11, 11);
		
		//Explosions
		assets[16] = sheet.crop(252, 301, 18, 17);
		assets[17] = sheet.crop(273, 301, 18, 17);
		assets[18] = sheet.crop(252, 325, 18, 18);
		assets[19] = sheet.crop(273, 325, 18, 18);
		assets[20] = sheet.crop(293, 325, 18, 18);
		assets[21] = sheet.crop(314, 325, 18, 18);
		assets[22] = sheet.crop(335, 325, 18, 18);
		
		//Lazer mid
		assets[23] = sheet.crop(438, 85, 13, 49);//Blue
		assets[23] = sheet.crop(455, 85, 13, 49);//Red
		assets[23] = sheet.crop(472, 85, 13, 49);//Yellow
		assets[23] = sheet.crop(489, 85, 13, 49);//Green
		
		assets[24] = ImageLoader.loadImage("res/Player.png");
		
		sheet= new SpriteSheet(ImageLoader.loadImage("res/player2.png"));
		assets[25] = sheet.crop( 42,41, 91, 115);
		
		sheet= new SpriteSheet(ImageLoader.loadImage("res/smoke.png"));
		
		ani[3][0] = sheet.crop( 0,  0, 16, 16);
		ani[3][1] = sheet.crop(16,  0, 16, 16);
		ani[3][2] = sheet.crop(32,  0, 16, 16);
		ani[3][3] = sheet.crop(48,  0, 16, 16);
		ani[3][4] = sheet.crop( 0, 16, 16, 16);
		ani[3][5] = sheet.crop(16, 16, 16, 16);
		ani[3][6] = sheet.crop(32, 16, 16, 16);
		ani[3][7] = sheet.crop(48, 16, 16, 16);
		
		ani[4][0] = sheet.crop( 0, 32, 16, 16);
		ani[4][1] = sheet.crop(16, 32, 16, 16);
		ani[4][2] = sheet.crop(32, 32, 16, 16);
		ani[4][3] = sheet.crop(48, 32, 16, 16);
		ani[4][4] = sheet.crop( 0, 48, 16, 16);
		ani[4][5] = sheet.crop(16, 48, 16, 16);
		ani[4][6] = sheet.crop(32, 48, 16, 16);
		ani[4][7] = sheet.crop(48, 48, 16, 16);
		
		//New
		name("res/Patches.png",4,5,16);
				
	}
	//Divide up animation sheets & sprite sheets
	public static void name(String s,int width, int height, int numberOfImages) {
		BufferedImage image = ImageLoader.loadImage(s);
		SpriteSheet sheet= new SpriteSheet(image);
		int widthPixel = image.getWidth()/width;
		int heightPixel = image.getHeight()/height;
		
		ArrayList<BufferedImage> a = new ArrayList<BufferedImage>();
		for(int y=0;y<height & numberOfImages>0;y++)
			for(int x=0;x<width & numberOfImages>0;x++) {
				a.add(sheet.crop(x*widthPixel, y*heightPixel, widthPixel, heightPixel));
				numberOfImages--;
			}
		anim.add(a);
	}
	
	public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

    double rads = Math.toRadians(angle);
    double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
    int w = img.getWidth();
    int h = img.getHeight();
    int newWidth = (int) Math.floor(w * cos + h * sin);
    int newHeight = (int) Math.floor(h * cos + w * sin);

    BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = rotated.createGraphics();
    AffineTransform at = new AffineTransform();
    at.translate((newWidth - w) / 2, (newHeight - h) / 2);

    int x = w / 2;
    int y = h / 2;

    at.rotate(rads, x, y);
    g2d.setTransform(at);
    g2d.drawImage(img, 0, 0, null);
    g2d.dispose();

    return rotated;
	}
}

class ImageLoader {
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Graphics not found. Please place graphics in \n"+new File("").getAbsolutePath(), "Fatal Error Occured", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}

class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
}