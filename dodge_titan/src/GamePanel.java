import java.awt.image.BufferedImage; //��8��
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; //��31��
import java.awt.Graphics2D; //��9��
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;//��15��
import java.awt.event.KeyListener; //��15��
import javax.imageio.ImageIO; //��27��
import java.io.File; //��27��
import java.io.IOException; //��30��
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel; //�~�ӭ��O ��7��
//import java.awt.Color;




//�D���O���O
public class GamePanel extends JPanel implements KeyListener{
	BufferedImage Image; //�ŧi�D�Ϥ�(�����Opanel�즳�¦⩳) �a�iImage�ܼ�
	Graphics2D G2; //�ŧiø�� �a�iG2�ܼ�
	Human player; //�ŧi�H�����O �a�iplayer�ܼ�
	BackgroundImage background;//�ŧi�I�����O �a�ibackground�ܼ�
	
	boolean finish = false; //�C������
	static final int FRESH = 20;//�`�q ��s�ɶ� static���R�A�H�K�����ե�
	
	//Obstacle o = new Obstacle(); //�ŧi��ê���O �H��ê��ê�غc�l��k �Ы�O����
	//�x�� �b�x�����g�F���w���O list���u��񨺭ӫ��w���O
	java.util.List<Obstacle> list = new ArrayList<Obstacle>(); //��ê���P��
	int addObstacleTimer = 0;
	
	int score = 0; //�ŧi�o��
	int addScoreTimer = 0; //�o���p�ɾ�
	
	//�غc�l��k
	public GamePanel() {
		Image = new BufferedImage(800,300,BufferedImage.TYPE_INT_BGR); //�Ы�Image����]�m�D�Ϥ�(���e)
		G2 = Image.createGraphics(); //�Ы�G2����
		try {
			player = new Human();
		} catch (Exception e) {
			e.printStackTrace();
		} //�Ы�player���� 
		
		background = new BackgroundImage();
		
		list.add(new Obstacle());
		
		FreshThread t = new FreshThread(this); //�ŧiFreshThread���O �Ы�t���� ��s�h�����
		t.start();
	}
	
	//��k
	//�Ϥ�
	private void paintImage() {
		player.move(); //�����a��B (�C�j�Q�����@���@�i��)
		background.roll(); //���I���Ϻu��
		//o.move(); ////����ê������
		
		
		G2.drawImage(background.image, 0, 0, this); //ø�s�I�� background���󪺮y��
		
		
		Rectangle rec = player.getFrontBounds(); 
		G2.setColor(Color.black);
		G2.fillRect(rec.x, rec.y, rec.width, rec.height);
		G2.drawImage(player.image, player.x, player.y, this); //ø�s��ê�� o���󪺮y��
		
		if(addObstacleTimer >= 800) {
			Random r = new Random();
			int a = r.nextInt(100); //100���H���Ʀr
			if(a > 50) {
				list.add(new Obstacle());
			}
			addObstacleTimer = 0;
		}
		for(int i = 0; i<list.size(); i++) {
			Obstacle o = list.get(i);
			o.move();
			
			rec = o.getBounds(); 
			G2.setColor(Color.black);
			G2.fillRect(rec.x, rec.y, rec.width, rec.height);
			G2.drawImage(o.image, o.x, o.y, this); //ø�s��ê�� o���󪺮y��
			
			//�o�͸I��
			if(o.getBounds().intersects(player.getFrontBounds())) {
				gameOver();
			}/*else {
				if (o.x < player.x) {
					score ++;
					
				}
			}*/
		}
		
		addObstacleTimer += FRESH; //���K�[��ê���p�ɾ��p�� 
		addScoreTimer += FRESH; //���K�[�o���p�ɾ��p�� 
		
		//�p��o��
		if (addScoreTimer >= 500) {
			score +=1;
			addScoreTimer = 0;
		}
		G2.setColor(Color.black);
		G2.setFont(new Font("����", Font.BOLD, 24));
		G2.drawString(String.format("%05d",score), 700 ,30);
		
	}
	
	//��k
	//@Override>>�P�@��k���P��@ 
	public void paint(Graphics g/*��2��*/) { //�Ƽgswing.Conponent���O�̪�paint��k
		paintImage(); 
		g.drawImage(Image,0, 0,this); //�D�Ϥ��K�쭱�O�W
	}
	
	//��k
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}
	
	//��k
	public boolean isFinish() {
		return finish;
	}
	//��k
	@Override
	public void keyTyped(KeyEvent e) {//���䪺����
		// TODO Auto-generated method stub
		
	}
	//��k
	@Override
	public void keyPressed(KeyEvent e) {//������U
		//System.out.println(e.getKeyChar()); �����L��J
		int code = e.getKeyChar(); //������U����L�s�X
		
		if (code == KeyEvent.VK_UP); {
			player.jump();
		}
		/*
		if (code == (KeyEvent.VK_RIGHT)); {
			player.doublejump();
		}
		*/
		
	}
	//��k
	@Override
	public void keyReleased(KeyEvent e) {//�����_
		// TODO Auto-generated method stub
		
	}
	
	//��k
	public void gameOver() {
		finish = true;
	}
	
	
	

	
	
	
	
	
	
	
	
}
