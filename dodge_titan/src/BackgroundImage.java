import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundImage {
	BufferedImage image; //�D�Ϥ�
	BufferedImage image1,image2; //�����I���Ϥ�
	Graphics2D g2; //���u
	int image1_x1, image2_x1; //��i�I���Ϥ������
	static final int speed = 8; //�I���Ϥ��u�ʳt�� (�ϥ�static�R�A ��ê���N���γЫ�������H�N�i�H�I�s)
	
	//�غc�l��k
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
	
	//��k
	public void roll() { //�u��
		image1_x1 -= speed;
		image2_x1 -= speed;
		
		//��I���Ϥ��@ <= -800 �N��O�����b����~
		//���I���Ϥ��@ +800 ���^��800 ,�I���Ϥ��G�@��
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
