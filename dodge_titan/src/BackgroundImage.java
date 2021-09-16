import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundImage {
	BufferedImage image; //主圖片
	BufferedImage image1,image2; //輪播背景圖片
	Graphics2D g2; //美工
	int image1_x1, image2_x1; //兩張背景圖片的橫坐標
	static final int speed = 8; //背景圖片滾動速度 (使用static靜態 障礙物就不用創建類的對象就可以呼叫)
	
	//建構子方法
	public BackgroundImage() {
		try {
			image1 = ImageIO.read(new File("image/background.png"));
			image2 = ImageIO.read(new File("image/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		image1_x1 = 0;
		image2_x1 = 800;
		g2.drawImage(image1, image1_x1, 0, null);
		g2.drawImage(image2, image2_x1, 0, null);
	}
	
	//方法
	public void roll() { //滾動
		image1_x1 -= speed;
		image2_x1 -= speed;
		
		//當背景圖片一 <= -800 意思是完全在窗體外
		//讓背景圖片一 +800 重回正800 ,背景圖片二一樣
		if (image1_x1 <= -800) {
			image1_x1 = 800;
		}
		if (image2_x1 <= -800) {
			image2_x1 = 800;
		}
		
		g2.drawImage(image1, image1_x1, 0, null);
		g2.drawImage(image2, image2_x1, 0, null);
		
	}
}
